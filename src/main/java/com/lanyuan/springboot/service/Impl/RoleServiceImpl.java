package com.lanyuan.springboot.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanyuan.springboot.mapper.RoleMapper;
import com.lanyuan.springboot.pojo.Role;
import com.lanyuan.springboot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper mapper;

    @Override
    public PageInfo<Role> show(Integer pageNum, Integer pageSize, String search) {
        PageHelper.startPage(pageNum,pageSize);
        Role role = new Role();
        role.setReloname(search);
        List<Role> show = mapper.show(role);
        return new PageInfo<>(show);
    }

    @Override
    public int delete(Integer[] id) {
        int i1=0;
        for (Integer i : id) {
            i1+=mapper.deleteByPrimaryKey(i);
        }
        if (i1>0){
            return i1;
        }
        return -1;
    }

    @Override
    public int add(Role role) {
        return mapper.insertSelective(role);
    }

    @Override
    public Set<Role> selectById(Integer[] id) {
        Set<Role> set = new HashSet<>();
        for (Integer i : id) {
            Role role = mapper.selectByPrimaryKey(i);
            set.add(role);
        }
        return set;
    }

    @Override
    public Role selectId(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(Role role) {
        return mapper.updateByPrimaryKeySelective(role);
    }

}
