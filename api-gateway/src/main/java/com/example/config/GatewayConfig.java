package com.example.config;

import com.example.filter.ElapsedFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName GatewayConfig
 * @Description gateway 配置类，用于统一管理 gateway 配置，包括路由规则、过滤器等
 * @Author Gray
 * @Date 2022/6/6 16:51
 * @Version 1.0
 **/
@SuppressWarnings("all")
@Configuration
public class GatewayConfig {

    // 通过配置类管理过滤器
//    @Bean
//    @Order(1) // 使用 @Order 注解指定优先级
//    public GlobalFilter a() {
//        return new AFilter();
//    }
//
//    @Bean
//    @Order(-1)
//    public GlobalFilter b() {
//        return new BFilter();
//    }
//
//    @Bean
//    @Order(0)
//    public GlobalFilter c() {
//        return new CFilter();
//    }

    /**
     * 配置路由规则
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("custom-filter",
                r -> r.path("/filter/**")
                      .filters(f -> f.stripPrefix(1).filter(new ElapsedFilter())) // 调用自定义的过滤器
                      .uri("lb://user-service")
            ).build();
    }

}
