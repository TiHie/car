package com.car.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.car.exception.BizException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.beans.Transient;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static java.util.regex.Pattern.compile;

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

	public void setChannelNameAndSpeed(String channelNameAndSpeed) {
		this.channelNameAndSpeed = channelNameAndSpeed;
		Matcher channelNameAndSpeedMatcher = compile("([\\u4e00-\\u9fa5]{2,})(\\d{1,3})").matcher(channelNameAndSpeed);
		if (channelNameAndSpeedMatcher.find()) {
			System.out.println("匹配通道速度成功");
			this.setChannelName(channelNameAndSpeedMatcher.group(1));
			this.setSpeed(Integer.parseInt(channelNameAndSpeedMatcher.group(2)));
		}else {
			throw new BizException("文件名和匹配规则字段不对应");
		}
	}

	public void setShootingTime(Date shootingTime) {
		this.shootingTime = shootingTime;
	}

	/**
	 * 这种情况只会文件名解析器set到shootingTime,因此需要在这里同时手动set到shootingDate以及HourTime
	 * @param shootingTime
	 */
	public void setShootingTime(String shootingTime) {
		Date yyyyMMddHHmmss = DateTime.parse(shootingTime, org.joda.time.format.DateTimeFormat.forPattern("yyyyMMddHHmmss")).toDate();
		this.shootingTime = yyyyMMddHHmmss;
		if(shootingTime.length() > 8){
			shootingTime = shootingTime.substring(0,8);
		}
		this.setHourTime(yyyyMMddHHmmss.getHours());
		this.shootingDate = DateTime.parse(shootingTime,org.joda.time.format.DateTimeFormat.forPattern("yyyyMMdd")).toDate();
		System.out.println("setShootingTime中设置shootingDate,结果为:"+this.shootingDate.toString());
	}




}