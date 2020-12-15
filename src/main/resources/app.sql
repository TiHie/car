CREATE DATABASE car;
USE car;
#正则码表
CREATE TABLE tb_regex_code(
                           id INT(8) PRIMARY KEY,
                           reg_name VARCHAR(64) COMMENT '正则名:前端传来的例如抓拍时间',
                           reg_code VARCHAR(256) COMMENT '正则码例如\\d{12}',
--                            reg_field_name VARCHAR(256) COMMENT '该正则字段对应代码实体属性名,例如抓拍时间对应实体名为shootingTime',
                           note VARCHAR(256) COMMENT '备注',
                           create_time DATETIME,
                           update_time DATETIME
)CHARSET=utf8 ENGINE=InnoDb;
#通道表
CREATE TABLE tb_channel(
                           id INT(8) PRIMARY KEY,
                           name VARCHAR(256) COMMENT '通道名称',
                           speed INT(8) COMMENT '限速',
                           note VARCHAR(256) COMMENT '备注',
                           create_time DATETIME,
                           update_time DATETIME
)CHARSET=utf8 ENGINE=InnoDb;
#摄像枪表
CREATE TABLE tb_camera_gun(
                              id INT(8) PRIMARY KEY ,
                              channel_id INT(8) COMMENT '通道id',
                              name VARCHAR(256) COMMENT '摄像枪名称',
                              note VARCHAR(256) COMMENT '备注',
                              rule VARCHAR(256) COMMENT '摄像枪匹配规则',
                              split_str VARCHAR(32) COMMENT '分隔字符串',
                              file_dir VARCHAR(128) COMMENT '摄像枪目录',
                              auto_scan BOOLEAN COMMENT '是否自动扫描通道文件',
                              create_time DATETIME,
                              update_time DATETIME
)CHARSET=utf8 ENGINE=InnoDb;
#车辆表
CREATE TABLE tb_car(
                       id INT(8) PRIMARY KEY ,
                       camera_gun_id INT(8) COMMENT '摄像枪id',
                       license_plate VARCHAR(64) COMMENT '车牌号',
                       license_palte_color VARCHAR(4) COMMENT '车牌颜色',
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
                        id INT(8) PRIMARY KEY ,
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
                       id INT(8) PRIMARY KEY ,
                       note VARCHAR(256) COMMENT '操作备注，如xxx导入了图片，xxx登录',
                       ip VARCHAR(256) COMMENT '登录 ip',
                       create_time DATETIME,
                       update_time DATETIME
)CHARSET=utf8 ENGINE=InnoDb;