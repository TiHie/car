package com.car.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.Date;

/**
 * @Author FDH
 * @Date 2020/12/17 0:29
 **/
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SpeedVO {
    private Integer carId;
    private Integer cameraGunId;
    private Integer channelId;
    private String channelName;
    private String cameraGunName;
    private Date shootingDate;
    private Date shootingTime;
    private String licensePlate;
    private String licensePlateColor;
    private Integer status;
    private Integer carSpeed;
    private Integer channelSpeed;
    private String cameraGunLocation;
}
