## spring-cloud-alibaba
*基于alibaba集成的框架，快速启动微服务，包揽热门使用的组件*
*link：https://github.com/alibaba/spring-cloud-alibaba*
### 优点

- alibaba生态，包含其业务组件，类似OSS，SMS
- 使用阿里生态下的seata分布式事务，糅合度高
- 基于nacos分布式架构
- nacos 服务治理中心、配置中心

### 该模块应用的组件
- nacos,config,gateway
- seata
- oss
- sms
- nacos-dubbo
- rocketMq
- SchedulerX
- Sentinel

### spring-cloud-alibaba模块说明
- distributed-service1~2 基于nacos集成所有的组件(不包含dubbo)
  - sentinel哨兵模式，类似于hystrix组件
  - oss组件
  - sentinel spring cloud gateway结合
  - sentinel组件独立部署，作为springboot应用启动，下载链接看sentinel github release版本
- distributed-service3~4 nacos-dubbo使用

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
- distributed-service2配置
```file
spring.cloud.alibaba.seata.tx-service-group=service2-consumer-fescar-group
```
- 在nacos界面上手动添加seatas 应用服务的配置文件或者修改seatas-server/conf/nacos-config.txt
    - nacos手动添加（***推荐第一种***）
    - 添加一个配置文件，名字为service.vgroup_mapping.service2-consumer-fescar-group，group
    为SEATA-GROUP，内容为default。类型为txt
    - 添加一个配置文件，名字为service.vgroup_mapping.service1-provider-fescar-group，group
    为SEATA-GROUP，内容为default。类型为txt
    1. 或者如下
    2. 修改seatas-server/conf/nacos-config.txt
    3. 去掉默认的属性
    4. 添加以下属性：
    ```file
    service.vgroup_mapping.service1-provider-fescar-group=default
    service.vgroup_mapping.service2-consumer-fescar-group=default
    ```
- 启动nacos server
- 在bash窗口执行seatas-server/conf下的sh命令：./nacos-config.sh localhost
- 启动 seatas-server

### rocketmq binder使用
- 启动mqnamesrv
- 启动 broker /mqbroker -n localhost:9876
- 创建topic：./mqadmin updateTopic -n localhost:9876 -c DefaultCluster -t test-topic
