package com.example.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.domain.R;
import com.example.handler.CustomBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName RateLimitController
 * @Description TODO
 * @Author Gray
 * @Date 2022/7/6 11:03
 * @Version 1.0
 **/
@RestController
@RequestMapping("/rate-limit")
public class RateLimitController {

    @GetMapping("/by-resource")
    @SentinelResource(value = "by-resource", blockHandler = "handleException")
    public R byResource() {
        return new R("按资源名称限流", 200);
    }

    @GetMapping("/by-url")
    @SentinelResource(value = "by-url", blockHandler = "handleException")
    public R byUrl() {
        return new R("按URL限流", 200);
    }

    @GetMapping("/block-handle")
    @SentinelResource(value = "block-handle", blockHandler = "handleException", blockHandlerClass = {CustomBlockHandler.class})
    public R blockHandle() {
        return new R("限流测试", 200);
    }

    public R handleException(BlockException exception) {
        return new R(exception.getClass().getCanonicalName(), 200);
    }

}
