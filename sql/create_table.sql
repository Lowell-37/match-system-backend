-- auto-generated definition
create table user
(
    id           bigint auto_increment comment 'id'
        primary key,
    username     varchar(256)                       null comment '用户昵称',
    userAccount  varchar(256)                       null comment '账号',
    avatarUrl    varchar(1024)                      null comment '用户头像
',
    gender       tinyint                            null comment '性别',
    userPassword varchar(512)                       not null comment '密码
',
    phone        varchar(128) charset utf8          null comment '电话',
    email        varchar(512)                       null comment '邮箱
',
    userStatus   int      default 0                 not null comment '用户状态 0 - 正常',
    createTime   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint  default 0                 not null comment '是否删除',
    userRole     int      default 0                 not null comment '用户角色 0 - 普通用户 1 - 管理员',
    planetCode   varchar(512)                       null comment '星球编号',
    tags         varchar(1024)                      null comment '标签 json 列表',
    column_name  int                                null
)
    comment '用户' charset = 'utf-8';

create table team
(
    id           bigint auto_increment comment 'id'
        primary key,
    name         varchar(256)                       null comment '队伍昵称',
    description  varchar(1024)                      null comment '描述',
    maxNum       int      default 1                 null comment '最大人数',
    expireTime   datetime                           null comment '过期时间',
    userPassword varchar(512)                       not null comment '密码',
    userId       bigint comment '用户id',
    email        varchar(512)                       null comment '邮箱',
    status       int      default 0                 not null comment '队伍状态 0 - 公开, 1-私有，2-加密',
    password     varchar(512)                       null comment '密码',
    createTime   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint  default 0                 not null comment '是否删除'
) comment '队伍';

create table user_team
(
    id          bigint auto_increment comment 'id'
        primary key,
    userId      bigint comment '用户id',
    teamId      bigint comment '队伍id',
    description varchar(1024)                      null comment '描述',
    maxNum      int      default 1 null comment '最大人数',
    joinTime    datetime                           null comment '加入时间',
    createTime  datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime  datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete    tinyint  default 0                 not null comment '是否删除'
) comment '用户队伍关系表';
