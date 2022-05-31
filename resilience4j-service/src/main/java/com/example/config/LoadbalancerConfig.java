package com.example.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName LoadbalancerConfig
 * @Description TODO
 * @Author Gray
 * @Date 2022/5/31 11:37
 * @Version 1.0
 **/
@Configuration
public class LoadbalancerConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
