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
@TableName("tb_user")
public class TbUserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "")
	@TableId(value = "id", type =  IdType.AUTO)
	private Integer id;
	@ApiModelProperty(value = "")
	private String username;
	@ApiModelProperty(value = "md5加密")
	private String password;
	@ApiModelProperty(value = "用户头像")
	private String avatar;
	@ApiModelProperty(value = "角色")
	private String role;
	@ApiModelProperty(value = "")
	private String createBy;

	@ApiModelProperty(value = "")
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;
	@ApiModelProperty(value = "")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;

}