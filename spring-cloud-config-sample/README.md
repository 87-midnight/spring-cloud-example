#### 统一配置中心
- 使用spring-cloud-starter-bus-amqp实现无需重启应用刷新资源文件
- 集成rabbitmq，依赖bus总线机制刷新。
- 当有属性文件提交时，在gitlab仓库setting配置请求config server的监控bus总线刷新地址：http://xxx:port/actuator/bus-refresh POST
- config server 则会自动拉取gitlab仓库最新配置文件，通过bus消息总线更新到每个config client。
- 使用webflux接口进行前后请求对比即可看到动态刷新应用环境上下文属性的变化
- bus.event.RefreshListener 打印出变更后的属性值