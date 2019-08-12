### rabbitmq-stream

基于spring cloud stream binder集成rabbitmq消息队列,自带生产、消费端示例

生产者配置：

````yaml
spring.cloud:
  stream:
    rabbit:
      binder:
        admin-addresses: 127.0.0.1:5672
    bindings:
      output-rabbit:
        destination: rabbit-topic-test
        content-type: application/json
        group: producer-test
````

启动生产者：
```cmd
java -jar xxx.jar --spring.profiles.active=producer
```

启动消费者：
```cmd
java -jar xxx.jar --spring.profiles.active=producer
```