package com.car.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.car.entity.TbCameraGunEntity;
import com.car.service.CameraGunService;
import com.car.service.TbCameraGunService;
import com.car.util.RStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author mowuwalixilo
 * @date2020/12/16 15:09
 */
@Service
public class CameraGunServiceImpl implements CameraGunService{

    @Autowired
    TbCameraGunService tbCameraGunService;

    /**
     * 摄像枪接口-添加-具体实现
     * @param cameraGunEntity
     * @return
     */
    @Override
    public RStatic addCameraGun(TbCameraGunEntity cameraGunEntity){
        String cameraGunName = cameraGunEntity.getName();
        QueryWrapper<TbCameraGunEntity> cameraGunCheckQw = new QueryWrapper<>();
        cameraGunCheckQw.eq("name",cameraGunName);
        TbCameraGunEntity cameraGunCheck = tbCameraGunService.getOne(cameraGunCheckQw);
        if (cameraGunCheck == null){
            boolean save = tbCameraGunService.save(cameraGunEntity);
            if (save){
                return RStatic.ok("添加完成");
            }else {
                return RStatic.error("添加失败");
            }
        }else {
            return RStatic.error("摄像枪名字已存在");
        }
    }

    /***
     * 摄像枪接口-删除-具体实现
     * @param map
     * @return
     */
    @Override
    public RStatic deleteCamerGun(Map<String, Object> map){
        ArrayList<String> list = (ArrayList<String>)map.get("ids");
        try {
            if (list != null){
                boolean removeByIds = tbCameraGunService.removeByIds(list);
                if (removeByIds){
                    return RStatic.ok("删除成功");
                }else {
                    return RStatic.error("删除失败");
                }
            }else {
                return RStatic.error("内容为空");
            }
        }catch (Exception e){
            return RStatic.error("操作有误");
        }

    }

    /***
     * 摄像枪接口-修改-具体实现
     * @param cameraGunEntity
     * @return
     */
    @Override
    public RStatic updateCameraGun(TbCameraGunEntity cameraGunEntity) {
        String cameraGunName = cameraGunEntity.getName();
        QueryWrapper<TbCameraGunEntity> cameraGunCheckQw = new QueryWrapper<>();
        cameraGunCheckQw.eq("name",cameraGunName);
        TbCameraGunEntity cameraGunCheck = tbCameraGunService.getOne(cameraGunCheckQw);
        if (cameraGunCheck == null){
            boolean save = tbCameraGunService.saveOrUpdate(cameraGunEntity);
            if (save){
                return RStatic.ok("添加完成");
            }else {
                return RStatic.error("添加失败");
            }
        }else {
            return RStatic.error("摄像枪名字已存在");
        }
    }

    /***
     * 摄像枪接口-查询-具体实现
     * @param limit
     * @param page
     * @param items
     * @return
     */
    @Override
    public RStatic selectCameraGun(String limit,int page, int items) {
        IPage<TbCameraGunEntity> cameraGunList = null;
        try {
            Page<TbCameraGunEntity> cameraGunPage = new Page<>(page,items);
            if (limit == null){
                cameraGunList = tbCameraGunService.page(cameraGunPage,null);
                return RStatic.ok("查询成功").data("cameraGunList",cameraGunList);
            }else {
                QueryWrapper<TbCameraGunEntity> cameraGunQw = new QueryWrapper<>();
                cameraGunQw
                        .like("channel_id",limit)
                        .or()
                        .like("name",limit)
                        .or()
                        .like("note",limit)
                        .or()
                        .like("file_dir",limit);
                cameraGunList = tbCameraGunService.page(cameraGunPage,cameraGunQw);
                return RStatic.ok("查询成功").data("cameraGunList",cameraGunList);
            }

        }catch (Exception e){
            return RStatic.error("操作异常");
        }
    }
}
