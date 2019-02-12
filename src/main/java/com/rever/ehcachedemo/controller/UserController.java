package com.rever.ehcachedemo.controller;


import com.rever.ehcachedemo.entity.User;
import com.rever.ehcachedemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
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
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public List<User> getUserList() {
        return userService.findUserList();
    }

    @GetMapping("/cache")
    public List<User> getUserListInCache() {
        return userService.findUserListInCache();
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        String now=LocalDate.now().toString();
        user.setCreate_time(now);
        user.setUpdate_time(now);
        boolean result=this.userService.insert(user);
        return result?user:null;
    }
}

