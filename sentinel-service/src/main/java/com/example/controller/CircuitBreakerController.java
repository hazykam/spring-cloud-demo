package com.example.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.example.domain.R;
import com.example.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName CircuitBreakerController
 * @Description TODO
 * @Author Gray
 * @Date 2022/7/12 15:20
 * @Version 1.0
 **/
@Slf4j
@RestController
@RequestMapping("/breaker")
public class CircuitBreakerController {

    @Autowired
    private RestTemplate rest;

    @Value("${service-url.user-service}")
    private String userServiceUrl;

    @GetMapping("/fallback/{id}")
    @SentinelResource(value = "fallback", fallback = "handleFallback")
    public R fallback(@PathVariable Long id) {
        R r = rest.getForObject(userServiceUrl + "/user/id/{1}", R.class, id);

        assert r != null && r.getData() != null;
        return r;
    }

    @GetMapping("/fallbackException/{id}")
    @SentinelResource(value = "fallbackException", fallback = "handleFallback2", exceptionsToIgnore = {NullPointerException.class})
    public R fallbackException(@PathVariable Long id) {
        if (id == 1) {
            throw new IndexOutOfBoundsException();
        } else if (id == 2) {
            throw new NullPointerException();
        }

        return rest.getForObject(userServiceUrl + "/user/id/{1}", R.class, id);
    }

    public R handleFallback(Long id) {
        return new R(new User(-1L, "defaultUser", "123456"), "服务降级返回", 200);
    }

    public R handleFallback2(@PathVariable Long id, Throwable e) {
        log.error("handleFallback2 id:{},throwable class:{}", id, e.getClass());
        return new R(new User(-2L, "defaultUser2", "123456"), "服务降级返回", 200);
    }

}
