### distributed-service3
- spring cloud dubbo集成，主要依赖web容器启动服务。
- 角色：生产者
- 包含依赖如下：
```xml
        <!-- Spring Boot dependencies -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-actuator</artifactId>
        </dependency>

        <!-- Dubbo Spring Cloud Starter -->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-dubbo</artifactId>
        </dependency>

        <!-- Spring Cloud Nacos Service Discovery -->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>
```
dubbo核心依赖：spring-cloud-starter-dubbo
dubbo注册nacos依赖：spring-cloud-starter-alibaba-nacos-discovery
dubbo web服务依赖：spring-boot-starter-web