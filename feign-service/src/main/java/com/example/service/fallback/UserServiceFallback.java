package com.example.service.fallback;

import com.example.domain.CommonResult;
import com.example.domain.User;
import com.example.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ClassName UserServiceFallback
 * @Description 通过实现 UserService 接口重写方法，并在 UserService 的 @FeignClient 注解中指定 fallback 处理类，实现熔断降级；
 *              注意 fallback 类需要使用 @Component 注解，被 Spring 接管。
 * @Author Gray
 * @Date 2022/6/1 16:16
 * @Version 1.0
 **/
@Slf4j
@Component
public class UserServiceFallback implements UserService {

    @Override
    public CommonResult byId(Long id) {
        CommonResult<User> fallback = new CommonResult<>("用户服务当前不可用", CommonResult.FAIL);
        log.error("用户服务不可用，进入降级方法，返回：{}", fallback);

        return fallback;
    }

    @Override
    public CommonResult list() {
        log.error("用户服务不可用，进入降级方法");
        return new CommonResult<>("用户服务当前不可用", CommonResult.FAIL);
    }

    @Override
    public CommonResult create(User user) {
        log.error("用户服务不可用，进入降级方法");
        return new CommonResult<>("用户服务当前不可用", CommonResult.FAIL);
    }

}
