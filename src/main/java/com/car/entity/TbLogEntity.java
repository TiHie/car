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
@TableName("tb_log")
public class TbLogEntity implements Serializable {

    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "")
	@TableId(value = "id", type =  IdType.UUID)
	private Integer id;
	@ApiModelProperty(value = "操作备注，如xxx导入了图片，xxx登录")
	private String note;
	@ApiModelProperty(value = "登录 ip")
	private String ip;
	@ApiModelProperty(value = "")
	private Date createTime;
	@ApiModelProperty(value = "")
	private Date updateTime;

}