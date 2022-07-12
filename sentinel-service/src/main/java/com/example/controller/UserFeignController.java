package com.example.controller;

import com.example.domain.R;
import com.example.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName UserFeignController
 * @Description TODO
 * @Author Gray
 * @Date 2022/7/12 16:43
 * @Version 1.0
 **/
@RestController
@RequestMapping("/feign")
public class UserFeignController {

    @Resource
    private UserService userService;

    @GetMapping("/{id}")
    public R getUser(@PathVariable Long id) {
        return userService.byId(id);
    }

}
