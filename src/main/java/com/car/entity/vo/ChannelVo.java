package com.car.entity.vo;

import com.car.entity.TbChannelEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author mowuwalixilo
 * @date2020/12/18 16:59
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChannelVo {

    private TbChannelEntity channelEntity;
    private String image;
}
