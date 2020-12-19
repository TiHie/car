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
@TableName("tb_channel")
public class TbChannelEntity implements Serializable {

    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "")
	@TableId(value = "id", type =  IdType.AUTO)
	private Integer id;
	@ApiModelProperty(value = "通道名称")
	private String name;
	@ApiModelProperty(value = "限速")
	private Integer speed;
	@ApiModelProperty(value = "备注")
	private String note;
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