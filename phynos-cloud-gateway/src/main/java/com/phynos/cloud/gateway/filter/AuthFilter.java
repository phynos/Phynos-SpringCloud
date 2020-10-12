package com.phynos.cloud.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 网关鉴权
 * <p>通过JWT判断请求是否携带合法的用户信息，并提取用户信息放入到head中</p>
 *
 * @author by lupc
 * @date 2020-09-17 17:57
 */
@Component
public class AuthFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String uri = exchange.getRequest().getURI().getPath();

        boolean valid = uri.contains("/public");
        if (valid) {
            chain.filter(exchange);
        } else {
            //返回鉴权失败


        }
        return null;
    }

    @Override
    public int getOrder() {
        return -999;
    }

}
