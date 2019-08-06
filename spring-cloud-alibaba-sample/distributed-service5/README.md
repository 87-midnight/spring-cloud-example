## distributed-service5子模块说明
- sentinel-Gateway集成使用
- sentinel-webflux集成

### sentinel-gateway 网关限流配置
- 定义spring cloud Gateway route，sentinel-nacos加载Gateway和api定义配置文件
```yaml
cloud:
    gateway:
      enabled: true
      discovery:
        locator:
          lower-case-service-id: true
      routes:
      # Add your routes here.
      - id: aliyun_route
        uri: https://www.aliyun.com/
        predicates:
        - Path=/product/**
      - id: httpbin_route
        uri: https://httpbin.org
        predicates:
        - Path=/httpbin/**
        filters:
        - RewritePath=/httpbin/(?<segment>.*), /$\{segment}
sentinel:
      transport:
        dashboard: 127.0.0.1:8080
      datasource:
        ds1:
          nacos:
            server-addr: 127.0.0.1:8848
            groupId: DEFAULT_GROUP
            dataId: ${spring.application.name}-sentinel
            ruleType: flow
        ds2:
          nacos:
            server-addr: 127.0.0.1:8848
            groupId: DEFAULT_GROUP
            dataId: ${spring.application.name}-sentinel-gateway
            ruleType: gw-flow
        ds3:
          nacos:
            server-addr: 127.0.0.1:8848
            groupId: DEFAULT_GROUP
            dataId: ${spring.application.name}-sentinel-api
            ruleType: gw-api-group
      filter:
        enabled: true
```
- 在nacos服务界面添加配置文件service5-gateway-webflux-sentinel-unite-sentinel-gateway，内容如下
```json
[
  {
    "resource": "some_customized_api",
    "count": 1
  },
  {
    "resource": "httpbin_route",
    "count": 0,
    "paramItem": {
      "parseStrategy": 2,
      "fieldName": "Spring-Cloud-Alibaba"
    }
  },
  {
    "resource": "httpbin_route",
    "count": 0,
    "paramItem": {
      "parseStrategy": 3,
      "fieldName": "name"
    }
  }
]
```
- 继续添加配置文件service5-gateway-webflux-sentinel-unite-sentinel-api
```json
[
  {
    "apiName": "some_customized_api",
    "predicateItems": [
      {
        "pattern": "/product/baz"
      },
      {
        "pattern": "/product/foo/**",
        "matchStrategy": 1
      },
      {
        "items": [
          {
            "pattern": "/spring-cloud/**"
          },
          {
            "pattern": "/spring-cloud-alibaba/**"
          }
        ]
      }
    ]
  },
  {
    "apiName": "another_customized_api",
    "predicateItems": [
      {
        "pattern": "/ahas"
      }
    ]
  }
]
```
### sentinel-webflux集成配置
- 基本和web的集成差不多
- 定义配置文件

- 在nacos服务界面添加名为service5-gateway-webflux-sentinel-unite-webflux的配置文件,内容如下
```json
[
  {
    "resource": "/mono",
    "controlBehavior": 0,
    "count": 0,
    "grade": 1,
    "limitApp": "default",
    "strategy": 0
  },
  {
    "resource": "/flux",
    "controlBehavior": 0,
    "count": 0,
    "grade": 1,
    "limitApp": "default",
    "strategy": 0
  }
]
```
- 访问http://localhost:9014/httpbin?name=3可以得到sentinel限流错误信息