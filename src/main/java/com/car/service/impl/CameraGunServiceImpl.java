package com.car.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.car.entity.TbCameraGunEntity;
import com.car.entity.TbChannelEntity;
import com.car.entity.bean.OneSpeed;
import com.car.entity.vo.CameraGunVo;
import com.car.mapper.TbCameraGunMapper;
import com.car.service.CameraGunService;
import com.car.service.TbCameraGunService;
import com.car.service.TbChannelService;
import com.car.util.RStatic;
import com.car.util.RuntimeDataUtil;
import com.sun.javafx.util.Logging;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author mowuwalixilo
 * @date2020/12/16 15:09
 */
@Service
public class CameraGunServiceImpl implements CameraGunService{

    @Autowired
    TbCameraGunService tbCameraGunService;

    @Autowired
    TbChannelService tbChannelService;

    @Autowired
    TbCameraGunMapper tbCameraGunMapper;
    /**
     * 摄像枪接口-添加-具体实现
     * @param cameraGunVo
     * @return
     */
    @Override
    public RStatic addCameraGun(CameraGunVo cameraGunVo) {

        if (checkCameraGunName(cameraGunVo.getName())){

            TbChannelEntity channelEntity = checkChannel(cameraGunVo.getChannelName());
            if (channelEntity != null){

                TbCameraGunEntity addCameraGunEntity = new TbCameraGunEntity();
                BeanUtils.copyProperties(cameraGunVo,addCameraGunEntity);
                addCameraGunEntity.setChannelId(channelEntity.getId());
                boolean save = tbCameraGunService.save(addCameraGunEntity);
                if (save){
                    //更新全局数据域
                    RuntimeDataUtil.cameraGunEntityMap.put(addCameraGunEntity.getId(),addCameraGunEntity);
                    RuntimeDataUtil.speedMap.put(addCameraGunEntity.getId(),new OneSpeed(
                            addCameraGunEntity.getId(),
                            channelEntity.getId(),
                            channelEntity.getSpeed()
                    ));
                    if (null != addCameraGunEntity.getChannelId() && 0 != addCameraGunEntity.getChannelId()){
                        RuntimeDataUtil.speedMap.put(addCameraGunEntity.getId(),new OneSpeed(
                                addCameraGunEntity.getId(),
                                addCameraGunEntity.getChannelId(),
                                channelEntity.getSpeed()
                        ));
                    }
                    return RStatic.ok("插入成功");
                }else {
                    return RStatic.error("插入失败");
                }
            }else {
                return RStatic.error("通道名错误");
            }
        }else {
            return RStatic.error("摄像枪名已存在");
        }
    }

    /***
     * 摄像枪接口-删除-具体实现
     * @param map
     * @return
     */
    @Override
    public RStatic deleteCamerGun(Map<String, Object> map) {
        ArrayList<Integer> list = (ArrayList<Integer>)map.get("ids");
        try {
            if (list != null){
                boolean removeByIds = tbCameraGunService.removeByIds(list);
                if (removeByIds){
                    list.forEach(id->{
                        //更新全局数据域
                        RuntimeDataUtil.cameraGunEntityMap.remove(Integer.valueOf(id));
                        RuntimeDataUtil.speedMap.remove(Integer.valueOf(id));
                    });
                    return RStatic.ok("删除成功");
                }else {
                    return RStatic.error("删除失败");
                }
            }else {
                return RStatic.error("摄像枪id为空");
            }
        }catch (Exception e){
            return RStatic.error("操作有误"+e);
        }
    }

    /***
     * 摄像枪接口-修改-具体实现
     * @param cameraGunVo
     * @return
     */
    @Override
    public RStatic updateCameraGun(CameraGunVo cameraGunVo) {
        if (checkCameraGunName(cameraGunVo.getName())){

            TbChannelEntity channelEntity = checkChannel(cameraGunVo.getChannelName());
            if (channelEntity != null){

                TbCameraGunEntity updateCameraGunEntity = new TbCameraGunEntity();
                BeanUtils.copyProperties(cameraGunVo,updateCameraGunEntity);
                updateCameraGunEntity.setChannelId(channelEntity.getId());
                boolean save = tbCameraGunService.saveOrUpdate(updateCameraGunEntity);
                if (save){
                    //更新全局数据域
                    TbCameraGunEntity byId = tbCameraGunService.getById(updateCameraGunEntity.getId());
                    RuntimeDataUtil.cameraGunEntityMap.put(byId.getId(),byId);

                    return RStatic.ok("修改成功");
                }else {
                    return RStatic.error("修改失败");
                }
            }else {
                return RStatic.error("通道名错误");
            }
        }else {
            return RStatic.error("摄像枪名已存在");
        }
    }
    /***
     * 摄像枪接口-查询-具体实现
     * @param parameter
     * @param page
     * @param items
     * @return
     */
    @Override
    public RStatic selectCameraGun(String parameter, int page, int items) {
        try {
            Integer cameraGunCount = tbCameraGunMapper.getCameraGunCount(parameter);
            Integer startInteger = (page-1)*items;
            List<CameraGunVo> cameraGunVo = tbCameraGunMapper.getCameraGunVo(parameter, startInteger, items);
            return RStatic.ok("查询成功").data("cameraGunList",cameraGunVo).data("total",cameraGunCount);
        }catch (Exception e){
            return RStatic.error("操作异常"+e);
        }
    }
    /**
     * 检查摄像枪名是否重复
     * @param cameraGunName
     * @return
     */
    private boolean checkCameraGunName(String cameraGunName){
        QueryWrapper<TbCameraGunEntity> nameCheck = new QueryWrapper<>();
        nameCheck.eq("name",cameraGunName);
        TbCameraGunEntity cameraGunCheck = tbCameraGunService.getOne(nameCheck);
        if (cameraGunCheck == null || cameraGunCheck.getName().equals(cameraGunName) ){
            return true;
        }else {
            return false;
        }
    }

    /***
     * 检查通道是否存在
     * @return
     */
    private TbChannelEntity checkChannel(String channelName){
        QueryWrapper<TbChannelEntity> channelQw = new QueryWrapper<>();
        channelQw.eq("name",channelName);
        TbChannelEntity channelEntity = tbChannelService.getOne(channelQw);
        if (channelEntity != null){
            return channelEntity;
        }else {
            return null;
        }
    }
}
