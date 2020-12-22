package com.car.service;

import com.car.entity.TbCameraGunEntity;
import com.car.entity.vo.CameraGunVo;
import com.car.util.RStatic;

import java.util.Map;

/**
 * @author mowuwalixilo
 * @date2020/12/16 15:06
 */
public interface CameraGunService {

    /***
     * 摄像枪接口-添加
     * @param cameraGunVo
     * @return
     */
    public RStatic addCameraGun(CameraGunVo cameraGunVo);

    /***
     * 摄像枪接口-删除
     * @param map
     * @return
     */
    public RStatic deleteCamerGun(Map<String,Object> map);

    /***
     * 摄像枪接口-修改
     * @param cameraGunVo
     * @return
     */
    public RStatic updateCameraGun(CameraGunVo cameraGunVo);

    /***
     * 摄像枪接口-查询
     * @param page
     * @param items
     * @return
     */
    public RStatic selectCameraGun(String parameter,int page,int items);
}
