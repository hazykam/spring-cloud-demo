package com.example.controller;

import com.example.domain.CommonResult;
import com.example.domain.User;
import com.example.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Gray
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public CommonResult<User> create(@RequestBody User user) {
        log.info("创建用户：{}", user);
        if (userService.create(user)) {
            return new CommonResult<>(user);
        }

        return new CommonResult<>("创建失败", CommonResult.FAIL);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResult<Long> delete(@PathVariable Long id) {
        if (userService.delete(id)) {
            return new CommonResult<>(id);
        }

        return new CommonResult<>("删除失败", CommonResult.FAIL);
    }

    @PutMapping("/update")
    public CommonResult<User> update(@RequestBody User user) {
        if (userService.update(user)) {
            return new CommonResult<>(user);
        }

        return new CommonResult<>("更新失败", CommonResult.FAIL);
    }

    @GetMapping("/id/{id}")
    public CommonResult<User> get(@PathVariable Long id) {
        User user = userService.byId(id);
        log.info("根据 ID 获取用户信息，用户信息：{}", user);
        if (user != null) {
            return new CommonResult<>(user);
        }

        return new CommonResult<>("查询失败", CommonResult.FAIL);
    }

    @GetMapping("/username/{username}")
    public CommonResult<User> getByUsername(@PathVariable String username) {
        User user = userService.byUsername(username);
        log.info("根据用户名获取用户信息，用户信息：{}", user);
        if (user != null) {
            return new CommonResult<>(user);
        }

        return new CommonResult<>("查询失败", CommonResult.FAIL);
    }

    @GetMapping("/list")
    public CommonResult<List<User>> list() {
        List<User> users = userService.list();
        log.info("获取用户列表，用户列表：{}", users);
        if (users != null) {
            return new CommonResult<>(users);
        }

        return new CommonResult<>("查询失败", CommonResult.FAIL);
    }

    // 不允许存在相同的请求路径
//    @GetMapping("/list")
//    public CommonResult<List<User>> list(@RequestBody List<Long> ids) {
//        List<User> users = userService.list(ids);
//        if (users != null) {
//            return new CommonResult<>(users);
//        }
//
//        return new CommonResult<>("查询失败", CommonResult.FAIL);
//    }

}
