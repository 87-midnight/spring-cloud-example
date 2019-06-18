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
   
#### 高级配置

##### log4j2集成skywalking

- 把agent目录下的optional-plugins目录里的apm-trace-ignore-plugin-6.0.0-GA.jar拷贝到plugins
- 项目添加以下skywalking引用
 ```xml
     <dependencies>
     		<dependency>
     			<groupId>org.springframework.boot</groupId>
     			<artifactId>spring-boot-starter</artifactId>
     			<exclusions>
     				<exclusion>
     					<groupId>org.springframework.boot</groupId>
     					<artifactId>spring-boot-starter-logging</artifactId>
     				</exclusion>
     			</exclusions>
     		</dependency>
     
     		<dependency>
     			<groupId>org.springframework.boot</groupId>
     			<artifactId>spring-boot-starter-log4j2</artifactId>
     		</dependency>
     
     		<!-- asynchronous loggers 异步日志需要用到-->
     		<dependency>
     			<groupId>com.lmax</groupId>
     			<artifactId>disruptor</artifactId>
     			<version>3.4.2</version>
     		</dependency>
     
     		<!-- for log4j2.yml, need jackson-databind and jackson-dataformat-yaml -->
     		<!-- spring-boot-starter-web -> spring-boot-starter-json -> jackson-databind-->
     		<!-- included by spring boot
             <dependency>
                 <groupId>com.fasterxml.jackson.core</groupId>
                 <artifactId>jackson-databind</artifactId>
             </dependency>
             -->
     
     		<dependency>
     			<groupId>com.fasterxml.jackson.dataformat</groupId>
     			<artifactId>jackson-dataformat-yaml</artifactId>
     		</dependency>
     		<dependency>
     			<groupId>org.springframework.boot</groupId>
     			<artifactId>spring-boot-starter-web</artifactId>
     		</dependency>
     		<dependency>
     			<groupId>com.alibaba</groupId>
     			<artifactId>fastjson</artifactId>
     			<version>1.2.15</version>
     		</dependency>
     		<dependency>
     			<groupId>org.projectlombok</groupId>
     			<artifactId>lombok</artifactId>
     			<optional>true</optional>
     		</dependency>
     		<dependency>
     			<groupId>org.springframework.boot</groupId>
     			<artifactId>spring-boot-starter-test</artifactId>
     			<scope>test</scope>
     		</dependency>
     		<dependency>
     			<groupId>org.apache.skywalking</groupId>
     			<artifactId>apm-toolkit-log4j-2.x</artifactId>
     			<version>6.0.0-GA</version>
     		</dependency>
     		<dependency>
     			<groupId>org.apache.skywalking</groupId>
     			<artifactId>apm-toolkit-trace</artifactId>
     			<version>6.0.0-GA</version>
     		</dependency>
     		<dependency>
     			<groupId>org.apache.skywalking</groupId>
     			<artifactId>apm-toolkit-opentracing</artifactId>
     			<version>6.0.0-GA</version>
     		</dependency>
     	</dependencies>
```
- vm option设置
   - -javaagent:D:\apache-skywalking-apm-incubating\agent\skywalking-agent.jar -Dskywalking.agent.service_name=socket-sample
- log4j2.xml配置
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--package指向自定义layout类的路径-->
<Configuration packages="com.example.apmtest" status="DEBUG">
    <Appenders>
        <Console name="LogToConsole" target="SYSTEM_OUT">
            <!--<PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level [%traceId] %logger{36} - %msg%n"/>-->
            <CustomerJsonLayout compact="true" locationInfo="true" complete="false" eventEol="true" properties="true" propertiesAsList="true"/>
        </Console>
        <File name="LogToFile" fileName="logs/app.log">
            <PatternLayout>
                <Pattern>%d %p %c{1.} [%t] %m%n</Pattern>
            </PatternLayout>
        </File>
    </Appenders>
    <Loggers>
        <Logger name="com.example" level="debug" additivity="false">
            <AppenderRef ref="LogToFile"/>
            <AppenderRef ref="LogToConsole"/>
        </Logger>
        <Logger name="org.springframework.boot" level="INFO" additivity="false">
            <AppenderRef ref="LogToConsole"/>
        </Logger>
        <Root level="error">
            <AppenderRef ref="LogToFile"/>
            <AppenderRef ref="LogToConsole"/>
        </Root>
    </Loggers>
</Configuration>
```
- 在需要打印traceId的地方添加注解
```java
@Slf4j
@Component
public class HelloWorld {

    private final UserTest test;

    @Autowired
    public HelloWorld(UserTest test) {
        this.test = test;
    }

    @Trace
    public void print(){
        log.info("{} i'm running",this.getClass().getName());
        test.start();
    }
}
```
- log4j2 自定义layout
```java
@Plugin(name = "customerJsonLayout", category = Node.CATEGORY, elementType = Layout.ELEMENT_TYPE, printObject = true)
public class CustomerJsonLayout extends AbstractStringLayout {

    private static final String KEY = "Converter";

    private CustomerJsonLayout(Charset charset) {
        super(charset);
    }

    @PluginFactory
    public static CustomerJsonLayout createLayout(@PluginAttribute(value = "charset", defaultString = "UTF-8") final Charset charset) {
        return new CustomerJsonLayout(charset);
    }

    @Override
    public String toSerializable(LogEvent logEvent) {
        JSONObject data = new JSONObject();
        data.put("traceId", TraceContext.traceId());//加入traceId，json化输出
        data.put("msg",logEvent);
        StringBuilder builder = new StringBuilder(1024);
        builder.append(data.toJSONString()).append("\n");
        return builder.toString();
    }
}
```
