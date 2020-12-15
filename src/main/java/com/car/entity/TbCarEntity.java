package com.car.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import java.io.Serializable;
import java.util.Date;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_car")
public class TbCarEntity implements Serializable {

    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "")
	@TableId(value = "id", type =  IdType.AUTO)
	private Integer id;
	@ApiModelProperty(value = "车牌号")
	private String licensePlate;
	@ApiModelProperty(value = "车辆速度")
	private Integer speed;
	@ApiModelProperty(value = "是否超速")
	private Integer status;
	@ApiModelProperty(value = "拍摄日期")
	private Date shootingDate;
	@ApiModelProperty(value = "具体是在这天的哪个小时")
	private Integer hourTime;
	@ApiModelProperty(value = "拍摄的具体时间，具体到时分秒")
	private Date shootingTime;
	@ApiModelProperty(value = "车辆图片")
	private String carImage;
	@ApiModelProperty(value = "")
	private Date createTime;
	@ApiModelProperty(value = "")
	private Date updateTime;

}