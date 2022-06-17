package com.example.service.impl;

import com.example.domain.User;
import com.example.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author Gray
 * @Date 2022/5/30 10:26
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    private List<User> users;

    /**
     * 初始化数据
     * PostConstruct: Constructor(构造方法) -> @Autowired(依赖注入) -> @PostConstruct(注释的初始化方法)
     */
    @PostConstruct
    public void initData() {
        users = Stream.of(
                new User(1L, "user1", "123456"),
                new User(2L, "user2", "123456"),
                new User(3L, "user3", "123456")
        ).collect(Collectors.toList());
    }

    @Override
    public Boolean create(User user) {
        return users.add(user);
    }

    @Override
    public Boolean update(User user) {
        return Collections.replaceAll(users, byId(user.getId()), user);
    }

    @Override
    public Boolean delete(Long id) {
        return users.remove(byId(id));
    }

    @Override
    public User byId(Long id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public User byUsername(String username) {
        return users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> list() {
        return users;
    }

    @Override
    public List<User> list(List<Long> ids) {
        return users.stream()
                .filter(u -> ids.contains(u.getId()))
                .collect(Collectors.toList());
    }

}
