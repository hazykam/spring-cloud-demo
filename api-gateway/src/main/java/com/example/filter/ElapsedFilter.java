package com.example.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @ClassName ElapsedFilter
 * @Description 自定义过滤器，需要实现 GatewayFilter 和 Ordered
 * @Author Gray
 * @Date 2022/6/6 17:16
 * @Version 1.0
 **/
@Slf4j
public class ElapsedFilter implements GatewayFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getAttributes().put("start", System.currentTimeMillis());
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            log.info("{}: {}ms", exchange.getRequest().getURI(), System.currentTimeMillis() - Long.parseLong(exchange.getAttribute("start").toString()));
        }));
    }

    /**
     * 该方法可以设置优先级，值越大则优先级越低
     * @return 优先级整型
     */
    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

}
