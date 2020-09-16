package com.phynos.cloud.gateway;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * 自定义断言
 * @author by lupc
 * @date 2020-09-01 10:17
 */
@Component
public class TokenRoutePredicateFactory extends AbstractRoutePredicateFactory<TokenRoutePredicateFactory.TokenConfig> {

    public TokenRoutePredicateFactory() {
        super(TokenConfig.class);
    }

    //要告诉转换为list,默认转换为map数据类型
    @Override
    public ShortcutType shortcutType() {
        return ShortcutType.GATHER_LIST;
    }

    //重写方法,将args的参数转换到那个list中("Config中指定的list容器命名"),如果指定,会匹配不到,所以一下mothods会没有值
    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("methods");
    }

    //自定义判断逻辑
    @Override
    public Predicate<ServerWebExchange> apply(TokenConfig config) {
        return ((param) -> {
            if (!CollectionUtils.isEmpty(config.methods) && config.methods.stream().anyMatch(method -> method.equalsIgnoreCase(param.getRequest().getMethod().name()))) {
                return true;
            }
            return false;
        });
    }

    static class TokenConfig {
        private List<String> methods = new ArrayList();

        public List<String> getMethods() {
            return methods;
        }

        public void setMethods(List<String> methods) {
            this.methods = methods;
        }
    }
}
