#### spring-cloud 使用skywalking教程
- elasticsearch
   - 版本 5.6.0
   - 修改config/elasticsearch.yml
      - cluster.name: CollectorDBCluster
      - network.host: 0.0.0.0
   - 启动elastic
- skywalking下载和配置
   - 下载skywalking-5.0.0-GA
   - skywalking webapp默认端口是8080，如需修改请看webapp/webapp.yml
   - 启动/bin/startup.cmd。不需要看窗口日志
   - 访问http://localhost:port
   - 登录默认是admin
- 非侵入式链路追踪配置
   - 在启动web应用时，加上参数：
   ```
   -javaagent:E:\apache-skywalking-apm-incubating-5.0.0-GA\apache-skywalking-apm-incubating\agent\skywalking-agent.jar -Dskywalking.agent.service_name=server-two -Dskywalking.collector.backend_service=localhost:11800
   ```
   - 完成配置。
   - 请求接口，登录skywalking后台查看数据
   
#### 高级配置(待更新)