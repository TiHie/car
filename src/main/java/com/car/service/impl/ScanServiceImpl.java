package com.car.service.impl;

import com.car.entity.TbCameraGunEntity;
import com.car.entity.TbCarEntity;
import com.car.entity.bean.OneImg;
import com.car.entity.bean.OneSpeed;
import com.car.exception.BizException;
import com.car.service.ScanService;
import com.car.service.TbCameraGunService;
import com.car.service.TbCarService;
import com.car.service.TbChannelService;
import com.car.util.FileUtil;
import com.car.util.LinuxApiUtil;
import com.car.util.RuntimeDataUtil;
import com.car.util.WindowsApiUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
@Service
@PropertySource(value = "classpath:application.properties",encoding = "utf-8")
public class ScanServiceImpl implements ScanService {

    @Value("${file.scan}")
    private String scanPath;
    @Value("${file.uploadFolder}")
    private String uploadFolder;
    @Value("${file.ip}")
    private String ip;
    @Value("${file.stitch}")
    private String stitch;

    @Autowired
    private TbCarService tbCarService;
    @Autowired
    private MatchLXLServiceImpl matchLXLService;
    @Autowired
    private MatchWJZServiceImpl matchWJZService;
    @Autowired
    private TbCameraGunService tbCameraGunService;
    @Autowired
    private TbChannelService tbChannelService;

    /**
     * 扫描所有的未操作过的文件
     * 复制文件到指定路径
     * 修改文件名
     */
    @Override
    public void scanAndUpload(boolean scanAll) {
        RuntimeDataUtil.cameraGunEntityMap.forEach((k,v)->{
            List<OneImg> oneImgs = uploadAll(k, scanAll, v);

            if(oneImgs.size() != 0){
                log.warn("自动扫描文件失败，失败文件个数：{},文件名：{}",oneImgs.size(),oneImgs);
                new BizException("自动扫描文件失败，失败个数"+oneImgs.size()+";通道值："+k);
            }else {
                log.info("自动扫描文件正常");
            }
        });
    }

    @Override
    public List<OneImg> uploadAll(int gunId, boolean scanAll,TbCameraGunEntity tbCameraGunEntity) {
//        String files = null;
//        if (scanAll){
//            files = LinuxApiUtil.treeDir(//扫描所有日期
//                    "tree -f -J  "+scanPath+tbCameraGunEntity.getFileDir(),
//                    "\\\"type\\\":\\\"file\\\" ",
//                    " -v plate",
//                    " -v alreadyScan");
//            files = "["+files+"]";
//        }else {
//            files = LinuxApiUtil.treeDir(
//                    "tree -f -J  "+scanPath+tbCameraGunEntity.getFileDir(),
//                    "\\\"type\\\":\\\"file\\\" ",
//                    " -v plate",
//                    RuntimeDataUtil.today,
//                    " -v alreadyScan");
//            files = "["+files+"]";
//        }
//        List<OneImg> oneImgs = JSON.parseArray(files, OneImg.class);
        //==以上是原来的实现，替换为 java 的实现==========================
        String gunPath = scanPath+tbCameraGunEntity.getFileDir();
        File file = new File(gunPath);
        List<OneImg> oneImgs = new ArrayList<>();

        FileUtil.getAllMatchFile(file,oneImgs,
                //匹配文件夹的正则
                Arrays.asList(RuntimeDataUtil.today),
                //匹配文件的正则
                Arrays.asList("plate","alreadyScan"));

        //==java实现==================================================
        //批量存储到数据库
        List<OneImg> error = new ArrayList<>();
        List<TbCarEntity> tbCarEntities = new ArrayList<>();

        for (OneImg img : oneImgs) {
            try{
                //
                String[] split = img.getName().split(
                        RuntimeDataUtil.connectStr
                );
                String fileName = split[split.length-1];//拿到最后一个元素作为文件名

                if (RuntimeDataUtil.environment.equals("linux")) {
                    LinuxApiUtil.shell(
                            "cp " + img.getName() + " " + uploadFolder + fileName + " && " +
                                    "mv " + img.getName() + " " + img.getName().replaceAll("\\." + fileName.split("\\.")[1], "_alreadyScan.jpg"));
                }else {
                    WindowsApiUtil.shell(
                            "copy " + img.getName() + " " + uploadFolder + fileName + " && " +
                                    "move " + img.getName() + " " + img.getName().replaceAll("\\." + fileName.split("\\.")[1], "_alreadyScan.jpg"));
                }
                // TODO: 2020/12/27 这里有修改，将lxl英文解析器换成中文解析器,db的rule已经修改，需要前端配合修改rule为中文rule
//                TbCarEntity match = matchLXLService.match(fileName, gunId);
                TbCarEntity match = matchWJZService.match(fileName, gunId);
                match.setCarImage(ip+stitch+"/"+fileName);
                match.setCameraGunId(gunId);
                //true超速，false没有
                if (null != match.getSpeed()) {
                    match.setStatus(match.getSpeed() > RuntimeDataUtil.speedMap.get(gunId).getSpeed());
                }
                tbCarEntities.add(match);
            }catch (Exception e){
                e.printStackTrace();
                error.add(img);
            }
        }
        tbCarService.saveBatch(tbCarEntities);
        return error;
    }

    //上传图片
    @Override
    public TbCarEntity uploadOne(int gunId, String fileName) {
//        TbCarEntity match = matchLXLService.match(fileName, gunId);
        // TODO: 2020/12/27 这里也修改了解析器
        TbCarEntity match = matchWJZService.match(fileName, gunId);
        match.setCarImage(ip+stitch+"/"+fileName);
        match.setCameraGunId(gunId);
        if(RuntimeDataUtil.speedMap == null){
            List<OneSpeed> speedList = tbCameraGunService.getSpeed();
            RuntimeDataUtil.speedMap =
                    new ConcurrentHashMap<>(speedList.stream().collect(Collectors.toMap(e -> e.getId(), e -> e)));
        }
        //true超速，false没有
        match.setStatus(match.getSpeed() > RuntimeDataUtil.speedMap.get(gunId).getSpeed());
        tbCarService.save(match);
        return match;
    }

    @Override
    public List<String> uploadAll(int gunId, List<String> fileNames) {
        List<String> error = new ArrayList<>();
        List<TbCarEntity> result = new ArrayList<>();
        for (String file : fileNames) {
            try{
                String[] split = file.split("/");
                String fileName = split[split.length-1];//拿到最后一个元素作为文件名
                TbCarEntity match = matchLXLService.match(fileName, gunId);
                match.setCarImage(ip+stitch+"/"+fileName);
                match.setCameraGunId(gunId);
                //true超速，false没有
                match.setStatus(match.getSpeed() > RuntimeDataUtil.speedMap.get(gunId).getSpeed());
                result.add(match);
            }catch (Exception e){
                e.printStackTrace();
                error.add(file);
                throw new BizException("文件解析错误");
            }
        }
        tbCarService.saveBatch(result);
        return error;
    }

    @Override
    public boolean checkLegitimacy(String substring, Integer gunId) {
        // TODO: 2020/12/27 这里也修改了解析器
        TbCameraGunEntity gunById = tbCameraGunService.getById(gunId);
//        TbCarEntity match = matchLXLService.match(substring, gunId);
        TbCarEntity match = matchWJZService.match(substring, gunId);
        boolean isMatches = true;
        if(StringUtils.isEmpty(match.getShootingTime())  || StringUtils.isEmpty(match.getLicensePlate()) || StringUtils.isEmpty(match.getSpeed()) || StringUtils.isEmpty(match.getChannelName() == null)){
            isMatches = false;
        }
        return isMatches;
    }

}
