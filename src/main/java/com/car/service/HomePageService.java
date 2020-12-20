package com.car.service;

import com.car.util.RStatic;
import io.swagger.models.auth.In;

/**
 * @author mowuwalixilo
 * @date2020/12/18 15:26
 */
public interface HomePageService {

    /***
     * 通过车辆ID查询汽车信息
     * @param carId
     * @return
     */
    public RStatic selectCarMassageById(Integer carId);

    /***
     * 首页通道展示
     * @param page
     * @param items
     * @return
     */
    public RStatic homePage(Integer page,Integer items);

}
