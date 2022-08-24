package com.lanyuan.springboot.service;

import com.github.pagehelper.PageInfo;
import com.lanyuan.springboot.pojo.Role;


import java.util.Set;

public interface RoleService {
    PageInfo<Role> show(Integer pageNum,Integer pageSize,String search);
    int delete(Integer[] id);
    int add(Role role);
    Set<Role> selectById(Integer[] id);
    Role selectId(Integer id);
    int update(Role role);
}
