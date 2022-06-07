package com.example.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @ClassName AFilter
 * @Description Gateway 的 GlobalFilter 可以直接在过滤器类上使用 @Component 自动配置，并通过 @Order 指定优先级；
 *              也可以不使用这两个注解，在配置类中直接配置并管理；
 *              但两种配置方式不能同时使用，否则过滤器会被重复调用执行。
 * @Author Gray
 * @Date 2022/6/6 16:47
 * @Version 1.0
 **/
@Slf4j
//@Component
//@Order(1)
public class AFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("AFilter - 前置逻辑");
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            log.info("AFilter - 后置逻辑");
        }));
    }

}
