package com.rever.ehcachedemo.controller;


import com.rever.ehcachedemo.entity.User;
import com.rever.ehcachedemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author gaoyakang
 * @since 2019-01-28
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping
    public List<User> getUserList() {
        return userService.findUserList();
    }
}

