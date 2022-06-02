package com.example.service.impl;

import com.example.domain.CommonResult;
import com.example.domain.User;
import com.example.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author Gray
 * @Date 2022/5/31 11:33
 * @Version 1.0
 **/
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Value("${custom-service-url.user-service}")
    private String userServiceUrl;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 使用 @CircuitBreaker 和 @Retry 等注解
     * 可以指定在调用的服务出现异常时，进行熔断、重试，以及应急的备用调用等
     *
     * @param id id
     * @return
     */
    @Override
    @CircuitBreaker(name = "myFallback", fallbackMethod = "fallback")
    public CommonResult byId(Long id) {
        // 通过关闭 user-service 服务，测试熔断
        CommonResult result = restTemplate.getForObject(userServiceUrl.concat("/user/id/").concat(id.toString()), CommonResult.class);

        log.info("用户服务调用成功，返回信息：{}", result);
        return result;
    }

    public CommonResult fallback(Long id, Exception e) {
        CommonResult result = new CommonResult(new User(-1L, "fallback", "123"), "该服务暂时无法使用", CommonResult.FAIL);

        log.error("用户服务调用失败，进入 fallback 方法，返回信息：{}", result);
        return result;
    }

}
