#### spring cloud feign模块说明

##### 服务提供者多副本的实现
- spring.application.name为同一名称，端口不同
- 两个服务提供者的同一个web接口，输出不同的日志，接收同一个请求参数
- 根据日志即可判断是feign负载均衡的情况