package com.car.service;

import com.car.entity.TbChannelEntity;
import com.car.util.RStatic;

import java.util.Map;

/**
 * @author mowuwalixilo
 * @date2020/12/16 20:12
 */
public interface ChannelService {

    /***
     * 通道表接口-添加
     * @param channelEntity
     * @return
     */
    public RStatic addChannel(TbChannelEntity channelEntity);

    /***
     * 通道表接口-删除
     * @param map
     * @return
     */
    public RStatic deleteChannel(Map<String, Object> map);

    /***
     * 通道表接口-修改
     * @param channelEntity
     * @return
     */
    public RStatic updateChannel(TbChannelEntity channelEntity);

    /***
     * 通道表接口-查询
     * @param limit
     * @param page
     * @param items
     * @return
     */
    public RStatic selectChannel(String limit,int page,int items);
}
