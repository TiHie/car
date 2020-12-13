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
@TableName("tb_user")
public class TbUserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "")
	@TableId(value = "id", type =  IdType.UUID)
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
	private Date createTime;
	@ApiModelProperty(value = "")
	private Date updateTime;

}