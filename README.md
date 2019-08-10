### spring cloud sample
- 添加版本说明是为了未来的学习方向，有取有舍
#### spring cloud Greenwich 版本变更说明
所有项目都进行了更新，以实现Java 11的兼容性。

Greenwich Release系列包括两个新项目，Spring Cloud GCP（提供与Google Cloud Platform的集成）和Spring Cloud Kubernetes（提供与Kubernetes的集成）。

Spring Cloud Netflix项目进入维护模式

最近，Netflix 宣布 Hystrix正在进入维护模式。自2016年以来，Ribbon已处于类似状态。虽然Hystrix和Ribbon现在处于维护模式，但它们仍然在Netflix上大规模部署。

Hystrix的Dashboard和Turbine已被Atlas取代。这些项目的最后提交分别是两年和四年前。Zuul 1和Archaius 1都被不能向后兼容的更高版本所取代。Zuul 1和Archaius 1都被不能向后兼容的更高版本所取代。

以下Spring Cloud Netflix模块和相应的启动器将进入维护模式：

spring-cloud-netflix-archaius
spring-cloud-netflix-hystrix-contract
spring-cloud-netflix-hystrix-dashboard
spring-cloud-netflix-hystrix-stream
spring-cloud-netflix-hystrix
spring-cloud-netflix-ribbon
spring-cloud-netflix-turbine-stream
spring-cloud-netflix-turbine
spring-cloud-netflix-zuul
这不包括Eureka或并发限制模块。

什么是维护模式？

将模块置于维护模式意味着Spring Cloud团队将不再向模块添加新功能。我们将修复阻止程序错误和安全问题，我们还将考虑并审查来自社区的小拉请求。

我们打算继续支持这些模块，从 Greenwich release train版本普遍可用后开始至少一年。

替换

我们建议使用以下内容替换为上面模块提供的功能。

Hystrix 替换为 Resilience4j

Hystrix Dashboard / Turbine替换为Micrometer + Monitoring System

Ribbon替换为Spring Cloud Loadbalancer

Zuul 1替换为Spring Cloud Gateway

Archaius 1替换为Spring Boot external config + Spring Cloud Config

#### 模块功能描述

- reactive webflux programming 响应式编程 
- eureka集群实现
- consul集群使用
- feign 集成
- config 配置中心集成
- stream消息驱动
- oauth2实现
- api-gateway使用
- hystrix集成
- resilience4j 集成
- spring-cloud-alibaba全家桶集成
