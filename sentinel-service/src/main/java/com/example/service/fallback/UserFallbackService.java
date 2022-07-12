package com.example.service.fallback;

import com.example.domain.R;
import com.example.domain.User;
import com.example.service.UserService;
import org.springframework.stereotype.Component;

/**
 * @ClassName UserFallbackService
 * @Description TODO
 * @Author Gray
 * @Date 2022/7/12 16:41
 * @Version 1.0
 **/
@Component
public class UserFallbackService implements UserService {

    @Override
    public R byId(Long id) {
        return new R(new User(-1L, "defaultUser", "123456"), "服务降级返回", 200);
    }

}
