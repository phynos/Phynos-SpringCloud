## gateway
网关作用：反向代理、鉴权、熔断降级（防止请求堆积在网关）、流量控制、日志监控等

### 过滤器
参考：
https://cloud.spring.io/spring-cloud-gateway/2.2.x/reference/html/#gatewayfilter-factories

### 路由
路由=路由ID+目标URI+一组断言+一组过滤器


### 断言