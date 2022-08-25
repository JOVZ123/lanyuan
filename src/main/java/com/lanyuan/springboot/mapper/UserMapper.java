package com.lanyuan.springboot.mapper;

import com.lanyuan.springboot.pojo.User;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    User login(User user);
    List<User> show(User user);

    int insertRelation(@Param("uid") Integer uid, @Param("rid") Integer rid);
}