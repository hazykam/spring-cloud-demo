package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ConsulConfigController
 * @Description TODO
 * @Author Gray
 * @Date 2022/6/17 17:38
 * @Version 1.0
 **/
@RefreshScope
@RestController
@RequestMapping("/config")
public class ConsulConfigController {

    private static final Logger log = LoggerFactory.getLogger(ConsulConfigController.class);

    @Value("${config.info}")
    private String configInfo;

    @Autowired
    private Environment env;

    @GetMapping("/info")
    public String info() {
        log.info("配置信息：{}", env.getProperty("config.info"));
        return configInfo;
    }

}
