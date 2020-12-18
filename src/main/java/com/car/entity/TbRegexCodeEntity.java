package com.car.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author wjz
 * @date 2020/12/15
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_regex_code")
public class TbRegexCodeEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "")
    @TableId(value = "id", type =  IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value = "正则中文名")
    private String regexName;
    @ApiModelProperty(value = "正则表达式")
    private String regexCode;
//    @ApiModelProperty(value = "正则表达式字段对应的代码实体名")
//    private String regFieldName;

    @ApiModelProperty(value = "")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @ApiModelProperty(value = "")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
