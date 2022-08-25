package com.lanyuan.springboot.controller;

import com.github.pagehelper.PageInfo;
import com.lanyuan.springboot.pojo.Role;
import com.lanyuan.springboot.pojo.User;
import com.lanyuan.springboot.service.RoleService;
import com.lanyuan.springboot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@Api(value = "测试controller",tags = {"测试操作接口"}) //用于类 表示标识这个类是swagger资源
public class TestController {
    @Autowired
    UserService us;
    @Autowired
    RoleService rs;
    @RequestMapping("/hello")
    public String hello(Map map){
        map.put("sss","hello world!");
        return "test";
    }
    @PostMapping("/getUser")
    public User getUser(){
        return new User();
    }

    @RequestMapping(value = "/users/{id}",method = RequestMethod.GET)
    @ResponseBody
    public User get(@PathVariable(name="id")Integer id){
        User user = us.selectId(id);
        return user;
    }
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable(name = "id")Integer[] id){
        int i = us.delete(id);
        if (i>0){
            return "删除成功";
        }
        return "删除失败";
    }
    //表示一个http请求的操作
    //@ApiOperation(value = "获取用户信息",tags = {"获取用户信息"},notes = "注意问题")
    @GetMapping("/getApi")
    @ResponseBody
    //@ApiParam 表示对参数的添加元数据 属性 name-参数名 value-参数说明 required-是否必填
    public User getApi(@ApiParam(name = "id",value = "用户id",required = true)Integer id){
        User user = us.selectId(id);
        return user;
    }

    @GetMapping("/testShow")
    public String show(HttpSession session,Map map, String search, @RequestParam(defaultValue = "1")Integer pageNum, @RequestParam(defaultValue = "8")Integer pageSize){
        //Map<String ,Object> map = new HashMap<>();
        map.put("sss","hello world");
        if (search==null){
            search = (String) session.getAttribute("search");
        }else {
            session.setAttribute("search",search);
        }
        PageInfo<User> p = us.show(pageNum,pageSize,search);
        PageInfo<Role> pr = rs.show(pageNum, pageSize, search);
        map.put("p1",p);

        map.put("prr",pr);
        return "test";
    }
    @RequestMapping("/one")
    public String one(){
        return "/one";
    }

    @GetMapping(value = "/user")
    @ResponseBody
    public Map<String ,Object> list(){
        PageInfo<User> show = us.show(1, 4, null);
        Map<String , Object> map = new HashMap<>();
        map.put("角色信息",show);
        return map;
    }
    @PostMapping("/user")
    @ResponseBody
    public String  add(User user){
        int add = us.add(user);
      if (add>0) return "新增成功";
      return "新增失败";
    }
    @DeleteMapping(value = "/user/{id}")
    @ResponseBody
    public Set<User> deleteAll(@PathVariable(name = "id")Integer[] id){
        int delete = us.delete(id);
        Set<User> users = us.selectById(id);
        return users;
    }

    @PutMapping(value = "/user/{id}")
    @ResponseBody
    public User updateUser(@PathVariable(name="id")Integer id){
        User user = us.selectId(id);
        int update = us.update(user);
        User user1 = us.selectId(id);
        return user1;
    }
}
