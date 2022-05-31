package com.example.controller;

import com.example.domain.CommonResult;
import com.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserResilience4jController
 * @Description TODO
 * @Author Gray
 * @Date 2022/5/31 11:29
 * @Version 1.0
 **/
@RestController
@RequestMapping("/user")
public class UserResilience4jController {

    @Autowired
    private UserService userService;

    Logger logger = LoggerFactory.getLogger(UserResilience4jController.class);

    @GetMapping("/fallback/{id}")
    public CommonResult get(@PathVariable Long id) {
        return userService.byId(id);
    }

}
