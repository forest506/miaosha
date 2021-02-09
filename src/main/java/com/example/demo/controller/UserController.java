package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.example.demo.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("user")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/get")
    @ResponseBody
    public UserModel getUser(@RequestParam(name = "id") Integer id) {
        //调用service服务获取对应id的用户对象，返回

        try {
            UserModel userModel = userService.getUserById(id);
            return userModel;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
