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

##### log4j2集成skywalking，该版在控制台，日志文件里打印json格式正常

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
***注：*** *使用CustomerJsonLayout的重写类，在Elastic+kibana+fluntd日志采集系统中无法正确采集json日志，*
*需要把log4j的JsonLayout源码提取出来继承才能被日志系统采集到，问题暂时无法定位*

##### log4j2集成skywalking修正版，基于EKF日志采集系统的json重写类用法
***AbstractJacksonLayout.java log4j的源码***
```java
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.impl.Log4jLogEvent;
import org.apache.logging.log4j.core.impl.MutableLogEvent;
import org.apache.logging.log4j.core.impl.ThrowableProxy;
import org.apache.logging.log4j.core.jackson.JsonConstants;
import org.apache.logging.log4j.core.jackson.Log4jJsonObjectMapper;
import org.apache.logging.log4j.core.layout.AbstractStringLayout;
import org.apache.logging.log4j.core.util.StringBuilderWriter;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.util.Strings;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


abstract class AbstractJacksonLayout extends AbstractStringLayout {

    protected static final String DEFAULT_EOL = "\r\n";
    protected static final String COMPACT_EOL = Strings.EMPTY;

    protected final String eol;
    protected final ObjectWriter objectWriter;
    protected final boolean compact;
    protected final boolean complete;

    protected AbstractJacksonLayout(final Configuration config, final ObjectWriter objectWriter, final Charset charset,
                                    final boolean compact, final boolean complete, final boolean eventEol, final Serializer headerSerializer,
                                    final Serializer footerSerializer) {
        super(config, charset, headerSerializer, footerSerializer);
        this.objectWriter = objectWriter;
        this.compact = compact;
        this.complete = complete;
        this.eol = compact && !eventEol ? COMPACT_EOL : DEFAULT_EOL;
    }


    @Override
    public String toSerializable(final LogEvent event) {
        final StringBuilderWriter writer = new StringBuilderWriter();
        try {
            toSerializable(event, writer);
            //这里插入skywalking的traceId
            writer.getBuilder().insert(1, "\"traceId\": \"" + TraceContext.traceId() +"\",");
            return writer.toString();
        } catch (final IOException e) {
            // Should this be an ISE or IAE?
            LOGGER.error(e);
            return Strings.EMPTY;
        }
    }

    private static LogEvent convertMutableToLog4jEvent(final LogEvent event) {
        // TODO Jackson-based layouts have certain filters set up for Log4jLogEvent.
        // TODO Need to set up the same filters for MutableLogEvent but don't know how...
        // This is a workaround.
        return event;
    }

    public void toSerializable(final LogEvent event, final Writer writer)
            throws JsonGenerationException, JsonMappingException, IOException {
        objectWriter.writeValue(writer, convertMutableToLog4jEvent(event));
        writer.write(eol);
        markEvent();
    }

}
```

***JacksonFactory.java log4j源码***
```java

import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.apache.logging.log4j.core.impl.Log4jLogEvent;
import org.apache.logging.log4j.core.jackson.*;
import org.apache.logging.log4j.core.layout.XmlLayout;
import org.codehaus.stax2.XMLStreamWriter2;

import javax.xml.stream.XMLStreamException;
import java.util.HashSet;
import java.util.Set;

abstract class JacksonFactory {

    static class JSON extends org.weya.common.layout.JacksonFactory {

        private final boolean encodeThreadContextAsList;
        private final boolean includeStacktrace;

        public JSON(final boolean encodeThreadContextAsList, final boolean includeStacktrace) {
            this.encodeThreadContextAsList = encodeThreadContextAsList;
            this.includeStacktrace = includeStacktrace;
        }

        @Override
        protected String getPropertNameForContextMap() {
            return JsonConstants.ELT_CONTEXT_MAP;
        }

        @Override
        protected String getPropertNameForSource() {
            return JsonConstants.ELT_SOURCE;
        }

        @Override
        protected String getPropertNameForNanoTime() {
            return JsonConstants.ELT_NANO_TIME;
        }

        @Override
        protected PrettyPrinter newCompactPrinter() {
            return new MinimalPrettyPrinter();
        }

        @Override
        protected ObjectMapper newObjectMapper() {
            return new Log4jJsonObjectMapper(encodeThreadContextAsList, includeStacktrace);
        }

        @Override
        protected PrettyPrinter newPrettyPrinter() {
            return new DefaultPrettyPrinter();
        }
    }


    abstract protected String getPropertNameForContextMap();

    abstract protected String getPropertNameForSource();

    abstract protected String getPropertNameForNanoTime();

    abstract protected PrettyPrinter newCompactPrinter();

    abstract protected ObjectMapper newObjectMapper();

    abstract protected PrettyPrinter newPrettyPrinter();

    ObjectWriter newWriter(final boolean locationInfo, final boolean properties, final boolean compact) {
        final SimpleFilterProvider filters = new SimpleFilterProvider();
        final Set<String> except = new HashSet<>(2);
        if (!locationInfo) {
            except.add(this.getPropertNameForSource());
        }
        if (!properties) {
            except.add(this.getPropertNameForContextMap());
        }
        except.add(this.getPropertNameForNanoTime());
        filters.addFilter(Log4jLogEvent.class.getName(), SimpleBeanPropertyFilter.serializeAllExcept(except));
        final ObjectWriter writer = this.newObjectMapper().writer(compact ? this.newCompactPrinter() : this.newPrettyPrinter());
        return writer.with(filters);
    }

}

```

***LogJsonLayout.java 修正后的插件类***
```java
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.DefaultConfiguration;
import org.apache.logging.log4j.core.config.Node;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginConfiguration;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;

@Plugin(name = "LogJsonLayout", category = Node.CATEGORY, elementType = Layout.ELEMENT_TYPE, printObject = true)
public final class LogJsonLayout extends AbstractJacksonLayout {

    private static final String DEFAULT_FOOTER = "]";

    private static final String DEFAULT_HEADER = "[";

    static final String CONTENT_TYPE = "application/json";

    protected LogJsonLayout(final Configuration config, final boolean locationInfo, final boolean properties,
                         final boolean encodeThreadContextAsList,
                         final boolean complete, final boolean compact, final boolean eventEol, final String headerPattern,
                         final String footerPattern, final Charset charset, final boolean includeStacktrace) {
        super(config, new JacksonFactory.JSON(encodeThreadContextAsList, includeStacktrace).newWriter(
                locationInfo, properties, compact),
                charset, compact, complete, eventEol,
                PatternLayout.createSerializer(config, null, headerPattern, DEFAULT_HEADER, null, false, false),
                PatternLayout.createSerializer(config, null, footerPattern, DEFAULT_FOOTER, null, false, false));
    }

    /**
     * Returns appropriate JSON header.
     *
     * @return a byte array containing the header, opening the JSON array.
     */
    @Override
    public byte[] getHeader() {
        if (!this.complete) {
            return null;
        }
        final StringBuilder buf = new StringBuilder();
        final String str = serializeToString(getHeaderSerializer());
        if (str != null) {
            buf.append(str);
        }
        buf.append(this.eol);
        return getBytes(buf.toString());
    }

    /**
     * Returns appropriate JSON footer.
     *
     * @return a byte array containing the footer, closing the JSON array.
     */
    @Override
    public byte[] getFooter() {
        if (!this.complete) {
            return null;
        }
        final StringBuilder buf = new StringBuilder();
        buf.append(this.eol);
        final String str = serializeToString(getFooterSerializer());
        if (str != null) {
            buf.append(str);
        }
        buf.append(this.eol);
        return getBytes(buf.toString());
    }

    @Override
    public Map<String, String> getContentFormat() {
        final Map<String, String> result = new HashMap<>();
        result.put("version", "2.0");
        return result;
    }

    @Override
    /**
     * @return The content type.
     */
    public String getContentType() {
        return CONTENT_TYPE + "; charset=" + this.getCharset();
    }

    /**
     * Creates a JSON Layout.
     * @param config
     *           The plugin configuration.
     * @param locationInfo
     *            If "true", includes the location information in the generated JSON.
     * @param properties
     *            If "true", includes the thread context map in the generated JSON.
     * @param propertiesAsList
     *            If true, the thread context map is included as a list of map entry objects, where each entry has
     *            a "key" attribute (whose value is the key) and a "value" attribute (whose value is the value).
     *            Defaults to false, in which case the thread context map is included as a simple map of key-value
     *            pairs.
     * @param complete
     *            If "true", includes the JSON header and footer, and comma between records.
     * @param compact
     *            If "true", does not use end-of-lines and indentation, defaults to "false".
     * @param eventEol
     *            If "true", forces an EOL after each log event (even if compact is "true"), defaults to "false". This
     *            allows one even per line, even in compact mode.
     * @param headerPattern
     *            The header pattern, defaults to {@code "["} if null.
     * @param footerPattern
     *            The header pattern, defaults to {@code "]"} if null.
     * @param charset
     *            The character set to use, if {@code null}, uses "UTF-8".
     * @param includeStacktrace
     *            If "true", includes the stacktrace of any Throwable in the generated JSON, defaults to "true".
     * @return A JSON Layout.
     */
    @PluginFactory
    public static LogJsonLayout createLayout(
            // @formatter:off
            @PluginConfiguration final Configuration config,
            @PluginAttribute(value = "locationInfo", defaultBoolean = false) final boolean locationInfo,
            @PluginAttribute(value = "properties", defaultBoolean = false) final boolean properties,
            @PluginAttribute(value = "propertiesAsList", defaultBoolean = false) final boolean propertiesAsList,
            @PluginAttribute(value = "complete", defaultBoolean = false) final boolean complete,
            @PluginAttribute(value = "compact", defaultBoolean = false) final boolean compact,
            @PluginAttribute(value = "eventEol", defaultBoolean = false) final boolean eventEol,
            @PluginAttribute(value = "header", defaultString = DEFAULT_HEADER) final String headerPattern,
            @PluginAttribute(value = "footer", defaultString = DEFAULT_FOOTER) final String footerPattern,
            @PluginAttribute(value = "charset", defaultString = "UTF-8") final Charset charset,
            @PluginAttribute(value = "includeStacktrace", defaultBoolean = true) final boolean includeStacktrace
            // @formatter:on
    ) {
        final boolean encodeThreadContextAsList = properties && propertiesAsList;
        return new LogJsonLayout(config, locationInfo, properties, encodeThreadContextAsList, complete, compact, eventEol,
                headerPattern, footerPattern, charset, includeStacktrace);
    }

    /**
     * Creates a JSON Layout using the default settings. Useful for testing.
     *
     * @return A JSON Layout.
     */
    public static LogJsonLayout createDefaultLayout() {
        return new LogJsonLayout(new DefaultConfiguration(), false, false, false, false, false, false,
                DEFAULT_HEADER, DEFAULT_FOOTER, StandardCharsets.UTF_8, true);
    }

    @Override
    public void toSerializable(final LogEvent event, final Writer writer) throws IOException {
        super.toSerializable(event, writer);
    }
```