package com.binger.uaa.service;


import com.binger.uaa.domain.User;
import com.binger.uaa.domain.UserExample;

import java.util.List;

public interface UserService {

    long count(UserExample userExample);

    List<User> listByExample(UserExample userExample);

    User findById(Integer id);

    User updateById(User user, Integer id);

    User add(User user);

    Integer deleteById(Integer id);

    User findUserByName(String s);
}
