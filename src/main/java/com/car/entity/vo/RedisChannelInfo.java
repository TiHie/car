package com.car.entity.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author wjz
 * @date 2021/1/4
 */
@Data
@ToString
@Getter
@Setter
public class RedisChannelInfo {
    private Integer channelId;
    private String channelName;
    private Date channelCreateDate;
    private String picUrl;
    private Integer latestCameraId;
    private SpeedVO speedVO;

}
