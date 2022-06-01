package com.example.service;

import com.example.domain.CommonResult;
import com.example.domain.User;
import com.example.service.fallback.UserServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @ClassName UserService
 * @Description Feign 是声明式的服务调用工具，它封装了负载均衡和断路器；
 *              只需创建一个接口并用注解的方式来配置它，就可以实现对某个服务接口的调用，免去了直接使用 RestTemplate 来调用服务接口。
 *              同时可以指定 fallback 熔断处理类，fallback 类需要实现 Service 接口，当发生服务熔断时，会调用 fallback 类中重写的实现。
 * @Author Gray
 * @Date 2022/6/1 15:08
 * @Version 1.0
 **/
@FeignClient(value = "user-service", path = "/user", fallback = UserServiceFallback.class)  // 使用 fallback 属性指定熔断处理类
public interface UserService {

    @GetMapping("/id/{id}")
    CommonResult byId(@PathVariable Long id);

    @GetMapping("/list")
    CommonResult list();

    @PostMapping("/create")
    CommonResult create(@RequestBody User user);

}
