package com.car.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author mowuwalixilo
 * @date2020/12/19 15:05
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CameraGunVo {

    @ApiModelProperty(value = "摄像枪id")
    private Integer id;
    @ApiModelProperty(value = "通道名称")
    private String channelName;
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
    @ApiModelProperty(value = "获取方式-是否自动扫描该通道")
    private boolean autoScan;
    @ApiModelProperty(value = "摄像枪地址")
    private String location;
}
