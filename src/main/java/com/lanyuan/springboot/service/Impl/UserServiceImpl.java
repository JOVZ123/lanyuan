package com.lanyuan.springboot.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanyuan.springboot.mapper.UserMapper;
import com.lanyuan.springboot.pojo.User;
import com.lanyuan.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper mapper;
    @Override
    public User login(User user) {
        return mapper.login(user);
    }

    @Override
    public int add(User user) {
        return mapper.insertSelective(user);
    }

    @Override
    public PageInfo<User> show(Integer pageNum, Integer pageSize,String search) {
        PageHelper.startPage(pageNum,pageSize);
        User user = new User();
        user.setAccount(search);
        user.setName(search);
        user.setPassword(search);
        List<User> p= mapper.show(user);
        return new PageInfo<>(p);
    }

    @Override
    public int delete(Integer[] id) {
        int i1 =0;
        for (Integer i : id) {
          i1+=mapper.deleteByPrimaryKey(i);
        }
        if (i1>0){
            return i1;
        }
        return -1;
    }

    @Override
    public Set<User> selectById(Integer[] id) {
        Set<User> set = new HashSet<>();
        for (Integer i : id) {
            User user = mapper.selectByPrimaryKey(i);
            set.add(user);
        }
        return set;
    }

    @Override
    public User selectId(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(User user) {
        return mapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int relation(Integer uid, Integer rid) {
        return mapper.insertRelation(uid,rid);
    }
}
