package com.car.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author mowuwalixilo
 * @date2020/12/20 16:39
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HomePageVo {

    //SELECT tcg.id AS camera_id,tcg.name AS camera_name,
    //       channel_id,location,
    //       tc.id AS car_id,license_plate,car_image,shooting_time
    //FROM
    //    tb_camera_gun tcg,tb_car tc,tb_channel tcl
    //    ,(SELECT MAX(tc.shooting_time) maxtime,tcg.id
    //    FROM tb_camera_gun tcg,tb_car tc,tb_channel tcl
    //    WHERE tcg.channel_id=tcl.id AND tc.camera_gun_id=tcg.id
    //    AND tc.shooting_date='2020-12-26'
    //    GROUP BY tcg.id) result
    //WHERE
    //    tcg.channel_id=tcl.id AND tc.camera_gun_id=tcg.id
    //    AND tc.shooting_date='2020-12-26'
    //    AND tc.camera_gun_id=result.id AND tc.shooting_time=result.maxtime;
    private Integer cameraId;
    private String cameraName;
    private Integer channelId;
    private String location;
    private String channelName;
    private Integer carId;
    private String licensePlate;
    private String carImage;
    private Date shootingTime;
}
