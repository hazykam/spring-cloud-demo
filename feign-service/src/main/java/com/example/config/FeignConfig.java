package com.example.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName FeignConfig
 * @Description TODO
 * @Author Gray
 * @Date 2022/6/1 16:40
 * @Version 1.0
 **/
@Configuration
public class FeignConfig {

    /**
     * Feign 提供了日志打印功能，我们可以通过配置来调整日志级别，从而了解 Feign 中的请求细节；
     *
     * @return
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

}
