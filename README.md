# Phynos-SpringCloud
微服务，基于SpringCloud（Netflix + Openfeign + Gateway + Sleuth + Zipkin）构建

## 版本说明

| 模块  | 版本  | 备注  |
| ------------ | ------------ | ------------ |
| SpringBoot  | 2.4.2  | -- |
| SpringCloud  | 2020.0.0  | --  |
| Zipkin  | 2.12.9  | --  |

## 基础内容

| 模块  | 端口  | 功能  |
| ------------ | ------------ | ------------ |
| 应用监控  | 6700  | springcloud-actuator  |
| 入口网关  | 6701  | nginx  |
| 服务网关  | 6710  | springcloud-gateway  |
| 配置中心  | 6720  | springcloud-config  |
| 统一登录  | 6730  | jwt  |
| 服务治理  | 8761  | netflix-eureka  |
| 链路跟踪  | 9411  | 外部  |
| 生产服务  | 6801~6809  | 用户  |
| 生产服务  | 6810~6819  | 系统  |
| 生产服务  | 6820~6829  | 设备  |
| 生产服务  | 6830~6839  | 订单  |
| 生产服务  | 6840~6849  | 报表  |
| 消费服务  | 6901~6909  | 用户  |
| 消费服务  | 6910~6919  | 设备  |
| 预留预留  | ----  | --  |

## 应用必备
### 黑盒测试
- postman测试用例
- 脚本测试

### 构建、Docker和K8S

### 云部署

## 分布式

### 分布式ID

1. 基于UUID
2. 基于数据库自增id
3. 数据库多主模式
4. 号段模式
5. redis
6. 基于雪花算法
7. 基于zookeeper实现


### 分布式锁

1. 基于redis实现
2. 基于zookeeper实现——curator自带工业级实现

### 分布式事务

1. TCC分布式事务

