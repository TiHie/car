package com.car.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @Author FDH
 * @Date 2020/12/22 14:01
 **/
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {

    private Integer id;
    private String username;
    private String avatar;
    private String role;
    private String createBy;
    private boolean isDeleted;
    private String remark;
    private Date createTime;
    private Date updateTime;
}
