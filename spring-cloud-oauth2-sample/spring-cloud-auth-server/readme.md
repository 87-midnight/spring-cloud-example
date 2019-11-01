## 1.启动异常

postgrepsql配置启动异常
```
java.lang.reflect.InvocationTargetException: null
    at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:1.8.0_161]
    ...
    at org.springframework.boot.SpringApplication.run(SpringApplication.java:1234) ~[spring-boot-2.0.0.RELEASE.jar:2.0.0.RELEASE]
    at com.divergent.demo.DemoApplication.main(DemoApplication.java:12) ~[classes/:na]
Caused by: java.sql.SQLFeatureNotSupportedException: Method org.postgresql.jdbc.PgConnection.createClob() is not yet implemented.
    at org.postgresql.Driver.notImplemented(Driver.java:683) ~[postgresql-42.2.1.jar:42.2.1]
    at org.postgresql.jdbc.PgConnection.createClob(PgConnection.java:1252) ~[postgresql-42.2.1.jar:42.2.1]
    ... 44 common frames omitted
```
This is an issue of Hibernate then as a workaround you can use the following flag. Set the following config in your properties:

spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
For Spring boot 2.x.x this flag simply disable contextual LOB creation, but if you really want to know the properly answer check UPDATE V1.

UPDATED V1

This was a Hibernate issue. If you are using Spring Boot latest version from 2.0.x until 2.1.x includes Hibernate 5.3.10.final you can take a look here but this issue was fixed on Hibernate version 5.4.0.CR1 then you need to add that dependency or if it is possible the latest version:

For Gradle:

compile('org.hibernate:hibernate-core:5.4.2.Final')
For Maven:

<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-core</artifactId>
    <version>5.4.2.Final</version>
</dependency>
UPDATED V2 "SPRING BOOT 2.2.0.M(1-4)"

In addition Spring boot v2.2.0.Mx includes now Hibernate v5.4.x then this issue was fixed for these versions.

## 2.密码认证时
```$xslt
2019-10-31 15:07:52.923  WARN 13648 --- [nio-9010-exec-7] com.zaxxer.hikari.pool.PoolBase          : HikariPool-1 - Failed to validate connection org.postgresql.jdbc.PgConnection@57dd4854 (This connection has been closed.). Possibly consider using a shorter maxLifetime value.
2019-10-31 15:07:52.928  WARN 13648 --- [nio-9010-exec-7] o.h.engine.jdbc.spi.SqlExceptionHelper   : SQL Error: 0, SQLState: 08003
2019-10-31 15:07:52.928 ERROR 13648 --- [nio-9010-exec-7] o.h.engine.jdbc.spi.SqlExceptionHelper   : HikariPool-1 - Connection is not available, request timed out after 30662ms.
2019-10-31 15:07:52.928 ERROR 13648 --- [nio-9010-exec-7] o.h.engine.jdbc.spi.SqlExceptionHelper   : This connection has been closed.
```
重启后就好了。