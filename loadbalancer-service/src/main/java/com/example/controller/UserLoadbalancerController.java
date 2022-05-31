package com.example.controller;

import com.example.domain.CommonResult;
import com.example.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName UserLoadbalancerController
 * @Description TODO
 * @Author Gray
 * @Date 2022/5/30 14:35
 * @Version 1.0
 **/
@RestController
@RequestMapping("/user")
public class UserLoadbalancerController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 服务提供者的地址
     */
    @Value("${custom-service-url.user-service}")
    private String userServiceUrl;

    @PostMapping("/create")
    public CommonResult create(@RequestBody User user) {
        return restTemplate.postForObject(userServiceUrl + "/user/create", user, CommonResult.class);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        restTemplate.delete(userServiceUrl + "/user/delete/".concat(id.toString()), id);
    }

    @PutMapping("/update")
    public void update(@RequestBody User user) {
        restTemplate.put(userServiceUrl + "/user/update", user);
    }

    @GetMapping("/id/{id}")
    public CommonResult get(@PathVariable Long id) {
        return restTemplate.getForObject(userServiceUrl + "/user/id/".concat(id.toString()), CommonResult.class, id);
    }

    @GetMapping("/username/{username}")
    public CommonResult getByUsername(@PathVariable String username) {
        return restTemplate.getForObject(userServiceUrl + "/user/username/".concat(username), CommonResult.class, username);
    }

    @GetMapping("/list")
    public CommonResult list() {
        return restTemplate.getForObject(userServiceUrl + "/user/list", CommonResult.class);
    }

    // 不允许存在相同的请求路径
//    @GetMapping("/list")
//    public CommonResult list(@RequestBody List<Long> ids) {
//        return restTemplate.postForObject(userServiceUrl.concat("/user/list"), ids, CommonResult.class);
//    }

}
