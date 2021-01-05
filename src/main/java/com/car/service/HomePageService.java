package com.car.service;

import com.car.util.RStatic;

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
     * @return
     * @param limit
     */
    public RStatic homePage(String limit);

}
