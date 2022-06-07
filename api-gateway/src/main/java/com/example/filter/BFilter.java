package com.example.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @ClassName AFilter
 * @Description TODO
 * @Author Gray
 * @Date 2022/6/6 16:47
 * @Version 1.0
 **/
@Slf4j
//@Component
//@Order(-1)
public class BFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("BFilter - 前置逻辑");
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            log.info("BFilter - 后置逻辑");
        }));
    }

}
