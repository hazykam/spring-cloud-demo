package com.example.controller;

import com.example.domain.CommonResult;
import com.example.domain.User;
import com.example.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName UserFeignController
 * @Description TODO
 * @Author Gray
 * @Date 2022/6/1 15:12
 * @Version 1.0
 **/
@Slf4j
@RestController
@RequestMapping("/user")
public class UserFeignController {

//    @Autowired
//    @Qualifier(value = "com.example.service.UserService")
    /**
     * 当对 Service 接口实现了 fallback 熔断处理类时；
     * 单独使用 @Autowired 注解时 IDEA 会报错，但不影响 Service 和 断路器 正常运行；
     * 可以使用 @Qualifier 指定具体的 Bean 实例，也可以使用 @Resource 代替 @Autowired，都不会影响正常运行。
     */
    @Resource
    private UserService userService;

    @GetMapping("/id/{id}")
    public CommonResult get(@PathVariable Long id) {
        return userService.byId(id);
    }

    @GetMapping("/list")
    public CommonResult list() {
        return userService.list();
    }

    @PostMapping("/create")
    public CommonResult create(@RequestBody User user) {
        return userService.create(user);
    }

}
