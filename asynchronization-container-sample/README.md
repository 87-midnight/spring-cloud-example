## reactive webflux
## Spring WebFlux

The original web framework included in the Spring Framework, Spring Web MVC, was purpose-built for the Servlet API and Servlet containers. The reactive-stack web framework, Spring WebFlux, was added later in version 5.0. It is fully non-blocking, supports Reactive Streams back pressure, and runs on such servers as Netty, Undertow, and Servlet 3.1+ containers.

Both web frameworks mirror the names of their source modules (spring-webmvc and spring-webflux) and co-exist side by side in the Spring Framework. Each module is optional. Applications can use one or the other module or, in some cases, both — for example, Spring MVC controllers with the reactive WebClient.

### sub-module introduce
- reactive-elastic-sample
  
  elastic数据库的增删查改。
  
- reactive-mongo-sample

  MongoDB数据库的响应式操作，基础增删查改
  
- reactive-redis-sample
  
  readis的响应式编程，包囊所有readistemplate的基础操作

- reactive-webflux-sample

  基础web容器服务，包含接口定义，调用，响应。接口拦截，权限验证等