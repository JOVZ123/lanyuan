package com.lanyuan.springboot.controller;

import com.github.pagehelper.PageInfo;
import com.lanyuan.springboot.pojo.Role;
import com.lanyuan.springboot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService rs;
    @PostMapping("/show")
    public Map<String ,Object> show(HttpSession session, @RequestParam(defaultValue = "1")Integer pageNum, @RequestParam(defaultValue = "4") Integer pageSize, String search){
        Map<String,Object> map = new HashMap<>();
        if (search==null){
             search = (String) session.getAttribute("search");
        }else {
            session.setAttribute("search",search);
        }
        PageInfo<Role> p = rs.show(pageNum, pageSize, search);
        map.put("展示角色信息",p.getList());
        return map;
    }
    @PostMapping("/delete")
    public Map<String ,Object> delete(Integer[] id){
        Map<String ,Object> map = new HashMap<>();
        Set<Role> roles = rs.selectById(id);
        int i = rs.delete(id);
        if (i>0){
            map.put("删除的用户 信息",roles);
        }else {
            map.put("删除失败","error");
        }
        return map;
    }
    @PostMapping("/update")
    public Map<String,Object> update(Role role){
        Map<String ,Object> map = new HashMap<>();
        int i = rs.update(role);
        Role r = rs.selectId(role.getId());
        if (i>0){
            map.put("修改的用户信息",r);
        }else {
            map.put("修改失败","error");
        }
        return map;
    }
    @PostMapping("/insert")
    public Map<String,Object> insert(Role role){
        Map<String ,Object> map = new HashMap<>();
        int i = rs.add(role);
        if (i>0){
            map.put("新增成功","success");
        }else {
            map.put("新增失败","error");
        }
        return map;
    }
}
