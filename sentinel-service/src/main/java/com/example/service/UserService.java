package com.example.service;

import com.example.domain.R;
import com.example.service.fallback.UserFallbackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author Gray
 * @Date 2022/7/12 16:39
 * @Version 1.0
 **/
@FeignClient(value = "nacos-user-service", path = "/user", fallback = UserFallbackService.class)
public interface UserService {

    @GetMapping("/id/{id}")
    R byId(@PathVariable Long id);

}
