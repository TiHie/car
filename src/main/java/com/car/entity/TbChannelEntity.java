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
@TableName("tb_channel")
public class TbChannelEntity implements Serializable {

    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "")
	@TableId(value = "id", type =  IdType.UUID)
	private Integer id;
	@ApiModelProperty(value = "通道名称")
	private String name;
	@ApiModelProperty(value = "限速")
	private Integer speed;
	@ApiModelProperty(value = "备注")
	private String note;
	@ApiModelProperty(value = "")
	private Date createTime;
	@ApiModelProperty(value = "")
	private Date updateTime;

}