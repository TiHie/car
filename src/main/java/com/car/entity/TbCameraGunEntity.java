package com.car.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import java.io.Serializable;
import java.util.Date;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_camera_gun")
public class TbCameraGunEntity implements Serializable {

    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "")
	@TableId(value = "id", type =  IdType.AUTO)
	private Integer id;
	@ApiModelProperty(value = "通道id")
	private Integer channelId;
	@ApiModelProperty(value = "摄像枪名称")
	private String name;
	@ApiModelProperty(value = "备注")
	private String note;
	@ApiModelProperty(value = "摄像枪匹配规则")
	private String rule;
	@ApiModelProperty(value = "分隔字符串")
	private String splitStr;
	@ApiModelProperty(value = "摄像枪目录")
	private String fileDir;
	@ApiModelProperty(value = "是否自动扫描该通道")
	private boolean autoScan;

	@ApiModelProperty(value = "")
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;
	@ApiModelProperty(value = "")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;

}