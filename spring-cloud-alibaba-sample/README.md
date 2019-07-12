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
- distributed-service3~4 nacos-dubbo使用