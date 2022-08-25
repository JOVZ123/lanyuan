package com.lanyuan.springboot.service;

import com.github.pagehelper.PageInfo;
import com.lanyuan.springboot.pojo.User;
import io.swagger.models.auth.In;

import java.util.Set;

public interface UserService {
    User login(User user);
    PageInfo<User> show(Integer pageNum,Integer pageSize,String search);
    int delete(Integer[] id);
    int add(User user);
    Set<User> selectById(Integer[] id);
    User selectId(Integer id);
    int update(User user);
    int relation(Integer uid, Integer rid);
 }
