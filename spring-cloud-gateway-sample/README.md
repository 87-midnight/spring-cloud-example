#### spring-cloud-gateway
- 	Spring Cloud Gateway is built upon Spring Boot 2.0, Spring WebFlux, and Project Reactor. As a consequence many of the familiar synchronous libraries (Spring Data and Spring Security, for example) and patterns you may not apply when using Spring Cloud Gateway. If you are unfamiliar with these projects we suggest you begin by reading their documentation to familiarize yourself with some of the new concepts before working with Spring Cloud Gateway.
- 	Spring Cloud Gateway requires the Netty runtime provided by Spring Boot and Spring Webflux. It does not work in a traditional Servlet Container or built as a WAR.
#### 特性
- 基于spring boot 2.x 及 webflux，不支持传统的servlet模式
- 包括undertow,tomcat,netty,jetty等，可运行在servlet3.1+以上的web容器
- 协议转换，路由转发
- 流量聚合，对流量进行监控，日志输出
- 作为整个系统的前端工程，对流量进行控制，有限流的作用
- 作为系统的前端边界，外部流量只能通过网关才能访问系统
- 可以在网关层做权限的判断
- 可以在网关层做缓存
- https://blog.csdn.net/forezp/article/details/83792388