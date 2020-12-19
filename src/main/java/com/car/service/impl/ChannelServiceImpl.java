package com.car.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.car.entity.TbChannelEntity;
import com.car.service.ChannelService;
import com.car.service.TbChannelService;
import com.car.util.RStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author mowuwalixilo
 * @date2020/12/16 20:13
 */
@Service
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    TbChannelService tbChannelService;

    /***
     * 通道表接口-添加-具体实现
     * @param channelEntity
     * @return
     */
    @Override
    public RStatic addChannel(TbChannelEntity channelEntity) {
        String channelName = channelEntity.getName();
        QueryWrapper<TbChannelEntity> channelCheckQw = new QueryWrapper<>();
        channelCheckQw.eq("name",channelName);
        TbChannelEntity channelCheck = tbChannelService.getOne(channelCheckQw);
        if (channelCheck == null){
            boolean save = tbChannelService.save(channelEntity);
            if (save){
                return RStatic.ok("添加成功");
            }else {
                return RStatic.error("添加失败");
            }
        }else {
            return RStatic.error("通道名已存在");
        }
    }

    /***
     * 通道表接口-删除-具体实现
     * @param map
     * @return
     */
    @Override
    public RStatic deleteChannel(Map<String, Object> map) {
        ArrayList<String> channelList = (ArrayList<String>)map.get("ids");
        try {
            if (channelList != null){
                boolean removeByIds = tbChannelService.removeByIds(channelList);
                if (removeByIds){
                    return RStatic.ok("删除成功");
                }else {
                    return RStatic.error("删除失败");
                }
            }else {
                return RStatic.error("通道id为空");
            }
        }catch (Exception e){
            return RStatic.error("操作有误");
        }
    }

    /***
     * 通道表接口-修改-具体实现
     * @param channelEntity
     * @return
     */
    @Override
    public RStatic updateChannel(TbChannelEntity channelEntity) {
        String channelName = channelEntity.getName();
        QueryWrapper<TbChannelEntity> channelCheckQw = new QueryWrapper<>();
        channelCheckQw.eq("name",channelName);
        TbChannelEntity channelCheck = tbChannelService.getOne(channelCheckQw);
        if (channelCheck == null || channelCheck.getName().equals(channelName)){
            boolean saveOrUpdate = tbChannelService.saveOrUpdate(channelEntity);
            if (saveOrUpdate){
                return RStatic.ok("修改成功");
            }else {
                return RStatic.error("修改失败");
            }
        }else {
            return RStatic.error("通道名已存在");
        }
    }

    /***
     * 通道表接口-查询-具体实现
     * @param limit
     * @param page
     * @param items
     * @return
     */
    @Override
    public RStatic selectChannel(String limit, int page, int items) {

        IPage<TbChannelEntity> channelPageList = null;
        try {
            Page<TbChannelEntity> channelPage = new Page<>(page,items);
            if (limit == null){
                channelPageList = tbChannelService.page(channelPage);
                return RStatic.ok("查询成功").data("channelPageList",channelPageList);
            }else {
                QueryWrapper<TbChannelEntity> channelQw = new QueryWrapper<>();
                channelQw
                        .like("name",limit)
                        .or()
                        .like("speed",limit)
                        .or()
                        .like("note",limit);
                channelPageList = tbChannelService.page(channelPage,channelQw);
                return RStatic.ok("查询成功").data("channelPageList",channelPageList);
            }
        }catch (Exception e){
            return RStatic.error("操作有误");
        }
    }
}
