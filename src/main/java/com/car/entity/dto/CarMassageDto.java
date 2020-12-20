package com.car.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author mowuwalixilo
 * @date2020/12/18 11:49
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CarMassageDto {

    private String carId;
    private Integer cameraGunId;
    private Integer channelId;
    private Date createTime;
    private String channelName;
    private String cameraName;
    private String channelSpeed;
    private String licensePlate;
    private String licensePlateColor;
    private Integer carSpeed;
    private Boolean status;
    private String carImage;
}
