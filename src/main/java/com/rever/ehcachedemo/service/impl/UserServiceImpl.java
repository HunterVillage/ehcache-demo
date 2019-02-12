package com.rever.ehcachedemo.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.rever.ehcachedemo.cache.AbstractCacheFactory;
import com.rever.ehcachedemo.commoncache.ICache;
import com.rever.ehcachedemo.entity.User;
import com.rever.ehcachedemo.mapper.UserMapper;
import com.rever.ehcachedemo.service.UserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gaoyakang
 * @since 2019-01-28
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private ICache<User> userCache;

    @Autowired
    public UserServiceImpl(AbstractCacheFactory abstractCacheFactory){
        this.userCache=abstractCacheFactory.getUserCache();
    }

    @Override
    public List<User> findUserListInCache() {
        Set<String> userKeys=userCache.getKeys();
        List<User> userList=userCache.get(userKeys);
        if(userList!=null&&userList.size()>0){
            log.info("Finding all users in cache successfully");
            return userList;
        }else {
            Wrapper<User> wrapper = new EntityWrapper<>();
            return this.selectList(wrapper);
        }
    }
    @Override
    public List<User> findUserList(){
        Wrapper<User> wrapper = new EntityWrapper<>();
        return this.selectList(wrapper);
    }

}
