package com.phynos.charger.gateway.filter;

import com.phynos.charger.common.jwt.JwtTokenUtil;
import com.phynos.charger.common.utils.JsonResult;
import com.phynos.charger.common.utils.JsonUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * 网关鉴权
 * <p>通过JWT判断请求是否携带合法的用户信息，并提取用户信息放入到head中</p>
 *
 * @author by lupc
 * @date 2020-09-17 17:57
 */
@Component
public class AuthFilter implements GlobalFilter, Ordered {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.debug("进入全局过滤器");

        String uri = exchange.getRequest().getURI().getPath();

        if (uri.startsWith("/authenticate")) {
            return chain.filter(exchange);
        }

        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        ServerHttpResponse response = exchange.getResponse();

        String username = new JwtTokenUtil().getUsernameFromToken(token);
        boolean valid = StringUtils.isNotEmpty(username);
        if (valid) {
            logger.info("auth success,username={}", username);
            //验证通过后，将用户名写入
            ServerHttpRequest host = exchange.getRequest().mutate()
                    .header("username", username)
                    .build();
            ServerWebExchange build = exchange.mutate().request(host).build();

            return chain.filter(build);
        } else {
            //返回鉴权失败
            return authError(response, "鉴权失败");
        }
    }

    private Mono<Void> authError(ServerHttpResponse response, String msg) {
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("ContentType", "application/json;charset=UTF-8");
        JsonResult result = JsonResult.code(HttpStatus.UNAUTHORIZED.value(), msg);
        String resultStr = JsonUtil.objectToString(result);
        DataBuffer buffer = response.bufferFactory().wrap(resultStr.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Flux.just(buffer));
    }

    @Override
    public int getOrder() {
        return -999;
    }

}
