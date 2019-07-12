#### 消息驱动微服务
- spring cloud stream 使用Spring Integration提供连接到消息代理的Spring应用
- spring-cloud-bus-amqp和spring-cloud-bus-kafka实则是引用spring-cloud-stream上层应用以达到目的
- 事件驱动的优势：高度解耦

## 2019-07-01 update log

- init stream project 
- run application step
    - vm params: --spring.profiles.active=producer/consumer
    