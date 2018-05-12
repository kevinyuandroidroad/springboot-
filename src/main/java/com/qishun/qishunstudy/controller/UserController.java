package com.qishun.qishunstudy.controller;


import com.github.pagehelper.PageHelper;
import com.qishun.qishunstudy.model.UserDomain;
import com.qishun.qishunstudy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2017/8/16.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping("/add")
    public int addUser(UserDomain user){
        return userService.addUser(user);
    }

    @GetMapping("/all")
    public String findAllUser(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                    int pageSize,
            Model model){
        //开始分页
        PageHelper.startPage(pageNum,pageSize);
       List<UserDomain> userDomains= userService.findAllUser(pageNum,pageSize);
       model.addAttribute("userDomains",userDomains);
        return "user_list";

    }
}