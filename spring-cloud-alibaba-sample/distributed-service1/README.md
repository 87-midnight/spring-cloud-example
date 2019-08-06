## distributed-service1子模块说明
- seatas框架+mybatis框架+nacos配置存储实现分布式事务的功能
- spring cloud binder rocketmq 生产者发送消息实现
- nacos config配置中心实现配置文件存储于nacos，动态更新应用运行时的属性
- oss商业化服务框架接入示例

### seata-mybatis-nacos 使用教程
- 准备好nacos server
- 下载seatas release版本，解压
- 修改seatas-server目录下的conf的registry.conf文件，type改为nacos存储
```file
registry {
  # file 、nacos 、eureka、redis、zk、consul、etcd3、sofa
  type = "nacos"
```
- 创建数据库表，包含示例的表
```mysql
CREATE TABLE account
(
    id               INT AUTO_INCREMENT PRIMARY KEY,
    balance          INT,
    last_update_time DATETIME DEFAULT now() ON UPDATE now()
);

CREATE TABLE product
(
    id               INT AUTO_INCREMENT PRIMARY KEY,
    price            INT,
    stock            INT,
    last_update_time DATETIME DEFAULT now() ON UPDATE now()
);

CREATE TABLE orders
(
    id               INT AUTO_INCREMENT PRIMARY KEY,
    user_id          INT,
    product_id       INT,
    pay_amount       INT,
    status           VARCHAR(100),
    add_time         DATETIME DEFAULT now(),
    last_update_time DATETIME DEFAULT now() ON UPDATE now()
);

INSERT INTO account (id, balance)
VALUES (1, 10);
INSERT INTO product (id, price, stock)
VALUES (1, 5, 10);
CREATE TABLE undo_log
(
    id            BIGINT(20)   NOT NULL AUTO_INCREMENT,
    branch_id     BIGINT(20)   NOT NULL,
    xid           VARCHAR(100) NOT NULL,
    context       VARCHAR(128) NOT NULL,
    rollback_info LONGBLOB     NOT NULL,
    log_status    INT(11)      NOT NULL,
    log_created   DATETIME     NOT NULL,
    log_modified  DATETIME     NOT NULL,
    ext           VARCHAR(100) DEFAULT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY ux_undo_log (xid, branch_id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;
```
- distributed-service1配置
```file
spring.cloud.alibaba.seata.tx-service-group=service1-provider-fescar-group
```

### sentinel接口限流+nacos存储配置

- 定义配置文件属性，定义sentinel服务、nacos服务连接地址
- 定义nacos存储的配置名，组，类型
```yaml
 spring:
  cloud:
    sentinel:
          transport:
            dashboard: 127.0.0.1:8080
          datasource:
            ds:
              nacos:
                server-addr: 127.0.0.1:8848
                groupId: DEFAULT_GROUP
                dataId: ${spring.application.name}-sentinel
                ruleType: flow
```
- 在nacos服务界面上添加配置文件，对接口级别的/test路径进行限流
```json
[
     {
     "resource": "/test",
     "limitApp": "default",
     "grade": 1,
     "count": 2,
     "strategy": 0,
     "controlBehavior": 0,
     "clusterMode": false
     }
]
```
- 启动distributed-service2，访问几次/test接口，打开sentinel服务界面，即可看到对该服务下的接口限流情况及nacos配置文件同步

### rocketmq binder使用
- 启动mqnamesrv
- 修改conf目录下的broker.conf，添加属性：brokerIP1=127.0.0.1；手动绑定ip地址
- 确保本地网卡没有多余的存在，否则可能导致应用无法正常启动
- 启动 broker,自动创建topic. mqbroker.cmd -n localhost:9876 autoCreateTopicEnable=true
