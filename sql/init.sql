-- 创建库
create database if not exists light_bi;

-- 切换库
use light_bi;

-- 用户表
create table if not exists user
(
    user_id       bigint auto_increment comment 'user_id' primary key,
    username      varchar(256)                           null comment '用户名',
    user_password varchar(512)                           null comment '密码',
    phone         varchar(128)                           null comment '电话',
    email         varchar(256)                           null comment '邮箱',
    user_avatar   varchar(1024)                          null comment '用户头像',
    user_profile  varchar(512)                           null comment '用户简介',
    user_role     varchar(256) default 'USER'            not null comment '用户角色：USER/ADMIN/BAN',
    create_time   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted       tinyint      default 0                 not null comment '是否删除'
) comment '用户' collate = utf8mb4_unicode_ci;

-- 图表信息表
create table if not exists chart
(
    chart_id         bigint auto_increment comment 'chart_id' primary key,
    goal             text                               null comment '分析目标',
    chart_data       text                               null comment '图表数据',
    chart_type       varchar(128)                       null comment '图表类型',
    gen_chart_data   text                               null comment '生成的图表数据',
    gen_chart_result text                               null comment '生成的分析结论',
    user_id          bigint                             not null comment '提交用户 id',
    create_time      datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time      datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted          tinyint  default 0                 not null comment '是否删除'
) comment '图表信息表' collate = utf8mb4_unicode_ci;
