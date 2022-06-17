package com.example.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName LoadbalancerConfig
 * @Description loadbalancer 配置类
 * @Author Gray
 * @Date 2022/5/30 14:31
 * @Version 1.0
 **/
@Configuration
public class LoadbalancerConfig {

    /**
     * 使用 @LoadBalanced 注解，RestTemplate 可以自动选择一个服务实例，进行调用
     *
     * @return RestTemplate
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
