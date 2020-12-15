CREATE DATABASE car;
USE car;
#通道表
CREATE TABLE tb_channel(
                           id INT(8) PRIMARY KEY AUTO_INCREMENT,
                           name VARCHAR(256) COMMENT '通道名称',
                           speed INT(8) COMMENT '限速',
                           note VARCHAR(256) COMMENT '备注',
                           create_time DATETIME,
                           update_time DATETIME
)CHARSET=utf8 ENGINE=InnoDb;
#摄像枪表
CREATE TABLE tb_camera_gun(
                              id INT(8) PRIMARY KEY AUTO_INCREMENT,
                              channel_id INT(8) COMMENT '通道id',
                              name VARCHAR(256) COMMENT '摄像枪名称',
                              note VARCHAR(256) COMMENT '备注',
                              rule VARCHAR(256) COMMENT '摄像枪匹配规则',
                              split_str VARCHAR(32) COMMENT '分隔字符串',
                              file_dir VARCHAR(128) COMMENT '摄像枪目录',
                              create_time DATETIME,
                              update_time DATETIME
)CHARSET=utf8 ENGINE=InnoDb;
#车辆表
CREATE TABLE tb_car(
                       id INT(8) PRIMARY KEY AUTO_INCREMENT,
                       camera_gun_id INT(8) COMMENT '摄像枪id',
                       license_plate VARCHAR(64) COMMENT '车牌号',
                       speed INT(8) COMMENT '车辆速度',
                       status BOOLEAN COMMENT '是否超速',
                       shooting_date DATETIME COMMENT '拍摄日期',
                       hour_time INT(8) COMMENT '具体是在这天的哪个小时',
                       shooting_time DATETIME COMMENT '拍摄的具体时间，具体到时分秒',
                       car_image VARCHAR(256) COMMENT '车辆图片',
                       create_time DATETIME,
                       update_time DATETIME
)CHARSET=utf8 ENGINE=InnoDb;
#用户表
CREATE TABLE tb_user(
                        id INT(8) PRIMARY KEY AUTO_INCREMENT,
                        username VARCHAR(64),
                        password VARCHAR(256) COMMENT 'md5加密',
                        avatar VARCHAR(255) COMMENT '用户头像',
                        role VARCHAR(64) COMMENT '角色',
                        create_by VARCHAR(64),
                        create_time DATETIME,
                        update_time DATETIME
)CHARSET=utf8 ENGINE=InnoDb;
#日志表
CREATE TABLE tb_log(
                       id INT(8) PRIMARY KEY AUTO_INCREMENT,
                       note VARCHAR(256) COMMENT '操作备注，如xxx导入了图片，xxx登录',
                       ip VARCHAR(256) COMMENT '登录 ip',
                       create_time DATETIME,
                       update_time DATETIME
)CHARSET=utf8 ENGINE=InnoDb;