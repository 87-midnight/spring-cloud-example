#### spring-cloud-consul子模块说明
- 分布一致性协议实现 
- 健康检查 
- Key/Value存储 
- 多数据中心方案 
- 不再需要依赖其他工具（比如ZooKeeper等）
#### consul功能说明
- 消息总线，提供配置实时刷新，不再依赖中间件，比如rabbitmq
- 配置中心功能
- 服务注册和发现功能 
- 使用consul后，可以取代spring cloud全家桶里的eureka,config,bus。