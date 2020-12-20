#sql文件在 classpath:app.sql 里
## 功能点

- 管理员登录，管理员创建账号，用户登录，管理员注销账号
- 导入车辆图片(单张)，扫描目录具体情况待定
- 首页预览
- 根据日期统计单个窗口的超速/未超速数量，反馈给前端绘图
- 按照小时统计单个窗口的超速/未超速数量，反馈给前端绘图
- 综合统计，按照日期段，摄像枪，路段....

##注意
- 摄像枪目录不用加前缀/，所有配置的文件目录要加后缀/

shootingTime : 拍摄时间

carColor     : 车辆颜色

carPlate     : 车牌号

channelName  : 通道名称

carSpeed     : 车速

如果设置分隔符为_

那么可能有如下表达式

shootingTime_carColor_carPlate_channelName_carSpeed

如果俩个字段连接在一起，例如通道名称和车速

channelName:通道名称的正则表达式#carSpeed:车速的正则表达式

(正则表达式最好不要包含分隔符，:，以及#)

shootingTime_carColor_carPlate_channelName:卡口车速#carSpeed:[1-9]

shootingTime_carColor_carPlate_channelName:卡口车速#carSpeed:[1-9]
