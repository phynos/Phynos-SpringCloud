package com.phynos.charger.gateway.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.phynos.charger.common.jwt.Auth0JwtUtil;
import com.phynos.charger.common.utils.JsonUtil;
import com.phynos.charger.common.utils.R;
import com.phynos.charger.common.utils.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
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
@Slf4j
@Component
public class AuthFilter implements GlobalFilter, Ordered {

    String tokenHead = "Bearer ";
    String tokenHeader = "Authorization";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.debug("进入全局过滤器");

        String uri = exchange.getRequest().getURI().getPath();

        if (uri.startsWith("/auth")) {
            return chain.filter(exchange);
        }

        String authHeader = exchange.getRequest().getHeaders().getFirst(tokenHeader);
        ServerHttpResponse response = exchange.getResponse();

        final String authToken = authHeader.substring(tokenHead.length()); // The part after "Bearer "
        DecodedJWT decodedJWT = Auth0JwtUtil.decodeToken(authToken);
        String username = decodedJWT.getClaim("username").asString();
        boolean valid = StringUtils.isNotEmpty(username);
        if (valid) {
            log.info("auth success,username={}", username);
            //验证通过后，将用户名写入
            exchange.getRequest().getHeaders().add("username", username);

            return chain.filter(exchange);
        } else {
            //返回鉴权失败
            return authError(response, "鉴权失败");
        }
    }

    private Mono<Void> authError(ServerHttpResponse response, String msg) {
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("ContentType", "application/json;charset=UTF-8");
        R<?> result = R.error(ResultCodeEnum.UNAUTHORIZED, msg);
        String resultStr = JsonUtil.objectToString(result);
        DataBuffer buffer = response.bufferFactory().wrap(resultStr.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Flux.just(buffer));
    }

    @Override
    public int getOrder() {
        return -999;
    }

}
