package com.rever.ehcachedemo.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.rever.ehcachedemo.entity.User;
import com.rever.ehcachedemo.mapper.UserMapper;
import com.rever.ehcachedemo.service.UserService;

import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gaoyakang
 * @since 2019-01-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Override
    public List<User> findUserList() {
        Wrapper<User> wrapper=new EntityWrapper<>();
        return this.selectList(wrapper);
    }

}
