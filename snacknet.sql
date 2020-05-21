create database snacknet default character set utf8 collate utf8_bin;

use snacknet;

-- 创建管理员信息表
create table if not exists adminInfo(
	aid int primary key auto_increment,
	aname varchar(100) not null unique, -- 管理员昵称
	pwd varchar(100) not null, -- 密码
	tel varchar(15) unique, -- 联系方式
	status int
) ENGINE = InnoDB AUTO_INCREMENT=101 default charset=utf8 collate=utf8_bin;

insert into adminInfo values(0, 'navy', md5('123321'), '15096098088', 1);

-- 创建会员信息表
create table if not exists memberInfo(
	mno int primary key auto_increment,
	nickName varchar(100) not null unique, -- 昵称
	realName varchar(100), -- 真是姓名
	pwd varchar(100) not null,
	tel varchar(15) unique, -- 联系方式
	email varchar(100) not null unique, -- 邮箱
	photo varchar(100), -- 图像
	regDate datetime, -- 注册日期
	status int -- 状态
) ENGINE = InnoDB AUTO_INCREMENT=1 default charset=utf8 collate=utf8_bin;

-- 创建商品类型表
create table if not exists goodsType(
	tno int primary key auto_increment,
	tname varchar(100) not null unique,
	status int -- 状态
)ENGINE = InnoDB AUTO_INCREMENT=101 default charset=utf8 collate=utf8_bin;

-- 创建商品信息表
create table if not exists goodsInfo(
	gno int primary key auto_increment,
	gname varchar(100) not null, -- 商品名称
	tno int, -- 所属类型
	price decimal(8,2), -- 价格
	intro varchar(200), -- 商品简介
	balance int, -- 库存
	pics varchar(500), -- 图片
	unit varchar(50), -- 单位
	qperied varchar(50), -- 保质期
	weight varchar(50), -- 净重
	descr text, -- 详细介绍
	status int, -- 商品状态
	constraint FK_goodsInfo_tno foreign key(tno) references goodsType(tno)
)ENGINE = InnoDB AUTO_INCREMENT=1 default charset=utf8 collate=utf8_bin;


-- 创建收货地址表
create table if not exists addrInfo(
	ano varchar(100) primary key,
	mno int, -- 会员编号
	name varchar(100) not null, -- 收货人姓名
	tel varchar(15) not null, -- 联系方式
	province varchar(100) not null, -- 省份
	city varchar(100) not null, -- 城市
	area varchar(100) not null, -- 地区
	addr varchar(100) not null, -- 详细地址
	flag int, -- 是否为默认收货地址
	status int, -- 状态
	constraint FK_addrInfo_mno foreign key(mno) references memberInfo(mno)
)ENGINE = InnoDB default charset=utf8 collate=utf8_bin;

-- 创建购物车表
create table if not exists cartInfo(
	cno varchar(100) primary key,
	mno int, -- 会员编号
	gno int, -- 商品编号
	num int, -- 数量
	constraint FK_cartInfo_mno foreign key(mno) references memberInfo(mno),
	constraint FK_cartInfo_gno foreign key(gno) references goodsInfo(gno)
)ENGINE = InnoDB default charset=utf8 collate=utf8_bin;

-- 创建订单表
create table if not exists orderInfo(
	ono varchar(100) primary key,
	odate datetime, -- 下单日期
	ano varchar(100), -- 收货地址
	sdate datetime, -- 发货日期
	rdate datetime, -- 收货日期
	status int, -- 订单状态
	price decimal(10,2), -- 订单总额
	invoice int, -- 是否已开发票 
	constraint FK_orderInfo_ano foreign key(ano) references addrInfo(ano)
)ENGINE = InnoDB default charset=utf8 collate=utf8_bin;


-- 订单详细表
create table if not exists orderItemInfo(
	ino int primary key auto_increment,
	ono varchar(100), -- 所属订单
	gno int, -- 商品编号
	nums int, -- 商品数量
	price decimal(8,2), -- 购买价
	status int,
	constraint FK_orderItemInfo_ono foreign key(ono) references orderInfo(ono),
	constraint FK_orderItemInfo_gno foreign key(gno) references goodsInfo(gno)
)ENGINE = InnoDB AUTO_INCREMENT=1 default charset=utf8 collate=utf8_bin;

