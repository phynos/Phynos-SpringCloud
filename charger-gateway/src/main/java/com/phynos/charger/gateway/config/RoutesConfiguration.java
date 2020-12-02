package com.phynos.charger.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * gateway配置或在yaml里面配置
 * <p>
 *     路由routes：
 *     唯一标识（ID）、
 *     目标服务地址（uri）、
 *     一组断言（predicates）
 *     一组过滤器组成（filters）,filters 不是必需参数
 * </p>
 * @author by lupc
 * @date 2020-06-09 11:40
 */
@Configuration
@Profile("test")
public class RoutesConfiguration {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("path_route",
                        r -> r.path("/").uri("https://www.baidu.com"))
                .build();
    }

}
