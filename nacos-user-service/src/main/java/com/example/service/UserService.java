package com.example.service;

import com.example.domain.User;

import java.util.List;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author Gray
 * @Date 2022/5/30 10:13
 * @Version 1.0
 **/
public interface UserService {

    Boolean create(User user);

    Boolean update(User user);

    Boolean delete(Long id);

    User byId(Long id);

    User byUsername(String username);

    List<User> list();

    List<User> list(List<Long> ids);

}
