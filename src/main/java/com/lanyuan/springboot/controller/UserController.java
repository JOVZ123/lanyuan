package com.lanyuan.springboot.controller;

import com.github.pagehelper.PageInfo;
import com.lanyuan.springboot.pojo.User;
import com.lanyuan.springboot.service.UserService;
import com.lanyuan.springboot.util.CodeUtil;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService us;
    @PostMapping("/login")
    public Map<String,Object> login(User user){
        Map<String ,Object> map = new HashMap<>();
        User u = us.login(user);
        map.put("admin",u);
        if (u!=null) {
            map.put("登录成功","success");
        }else {
            map.put("登录失败","error");
        }
        return map;
    }
    @PostMapping("/register")
    public Map<String,Object> register(User user, String code, HttpSession session){
        String  ran = (String) session.getAttribute("randomCode");
        Map<String ,Object> map = new HashMap<>();
        if (ran.equalsIgnoreCase(code)){
            map.put("验证码","匹配成功");
            int add = us.add(user);
            if (add>0){
                map.put("注册信息",user.toString());
            }else {
                map.put("注册信息", "注册失败");
            }
        }
        return map;
    }
    @GetMapping("/getCode")
    public void getCode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CodeUtil.processRequest(req, resp);
    }

    @PostMapping("/show")
    public Map<String,Object> show(HttpSession session, String search, @RequestParam(defaultValue = "1")Integer pageNum,@RequestParam(defaultValue = "4")Integer pageSize){
        Map<String ,Object> map = new HashMap<>();
        if (search==null){
            search = (String) session.getAttribute("search");
        }else {
            session.setAttribute("search",search);
        }
        PageInfo<User> p = us.show(pageNum,pageSize,search);
        map.put("展示信息",p.getList());
        return map;
    }
    @PostMapping("/delete")
    public Map<String ,Object> delete(Integer[] id){
        Map<String ,Object> map = new HashMap<>();
        Set<User> users = us.selectById(id);
        int i = us.delete(id);
        if (i>0){
            map.put("删除的用户 信息",users);
        }else {
            map.put("删除失败","error");
        }
        return map;
    }
    @RequestMapping("/getId")
    @ResponseBody
    public User getId(Integer id){
        User user = us.selectId(id);
        return user;
    }
    @PostMapping("/update")
    public Map<String,Object> update(User user){
        Map<String ,Object> map = new HashMap<>();
        int i = us.update(user);
        User u = us.selectId(user.getId());
        if (i>0){
            map.put("修改的用户信息",u);
        }else {
            map.put("修改失败","error");
        }
        return map;
    }
    @PostMapping("/insert")
    public Map<String,Object> insert(User user,Integer rid){
        Map<String ,Object> map = new HashMap<>();
        int i = us.add(user);
        int relation = us.relation(user.getId(), rid);
        if (i>0){
            map.put("新增成功","success");
        }else {
            map.put("新增失败","error");
        }
        return map;
    }
}
