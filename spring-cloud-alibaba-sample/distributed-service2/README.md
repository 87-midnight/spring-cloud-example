## distributed-service2子模块说明
- 结合distributed-service1使用，seatas框架+mybatis框架+nacos配置存储实现分布式事务的功能
- spring cloud binder rocketmq 消费者发送消息实现
- nacos 服务治理，feign负载均衡使用
- spring cloud alibaba bus rocketmq消息总线示例

### seata-mybatis-nacos 使用教程
- 先看distributed-service1的配置
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

### restTemplate、feign配置负载均衡使用
```java
@FeignClient("service1-provider")
    public interface FeignClientDemo{

        @GetMapping(value = "/test")
        String test();
    }
```
使用时，直接使用注解@Autowired定义FeignClientDemo即可。

使用restTemplate时，先定义bean,加上负载均衡注解
```java
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
```

### spring cloud rocketmq bus消息总线配置使用
配置属性
```properties
spring.cloud.stream.rocketmq.binder.name-server=127.0.0.1:9876
spring.cloud.bus.id=${spring.application.name}:${server.port}
spring.cloud.bus.trace.enabled=true
```
定义事件类后，使用ApplicationEventPublisher发布事件，在使用@EventListener监听事件
