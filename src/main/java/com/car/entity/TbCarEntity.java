package com.car.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Date;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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
	@ApiModelProperty(value = "摄像枪id")
	private Integer cameraGunId;
	@ApiModelProperty(value = "车牌号")
	private String licensePlate;
	@ApiModelProperty(value = "车牌颜色")
	private String licensePlateColor;
	@ApiModelProperty(value = "车辆速度")
	private Integer speed;
	@ApiModelProperty(value = "是否超速")
	private Boolean status;
	@ApiModelProperty(value = "拍摄日期")
	private Date shootingDate;
	@ApiModelProperty(value = "具体是在这天的哪个小时")
	private Integer hourTime;
	@ApiModelProperty(value = "拍摄的具体时间，具体到时分秒")
	@DateTimeFormat(pattern = "yyyMMddHHmmss")
	private Date shootingTime;
	@ApiModelProperty(value = "车辆图片")
	private String carImage;
	@ApiModelProperty(value = "通道名称车速（用于暂存解析通道名称和车速）")
	private transient String channelNameAndSpeed;
	@ApiModelProperty(value = "通道名称(暂存)")
	private transient String channelName;
	@TableLogic
	@ApiModelProperty(value = "")
	private boolean isDeleted;
	@ApiModelProperty(value = "")
	private String remark;

	@ApiModelProperty(value = "")
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;
	@ApiModelProperty(value = "")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;

}