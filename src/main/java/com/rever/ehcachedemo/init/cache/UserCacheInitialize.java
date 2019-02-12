package com.rever.ehcachedemo.init.cache;

import com.rever.ehcachedemo.cache.AbstractCacheFactory;
import com.rever.ehcachedemo.commoncache.ICache;
import com.rever.ehcachedemo.entity.User;
import com.rever.ehcachedemo.exception.InitializeException;
import com.rever.ehcachedemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;


/**
 * @Description:
 * @Author: gaoyakang
 * @Version: 1.0
 * @Create Date Time: 2019-02-12 11:07
 * @Update Date Time:
 * @see
 */
@Component
public class UserCacheInitialize {
    private ICache<User> userCache;
    private UserService userService;
    @Autowired
    public UserCacheInitialize(AbstractCacheFactory abstractCacheFactory,UserService userService) {
        this.userCache = abstractCacheFactory.getUserCache();
        this.userService=userService;
    }
    @PostConstruct
    public void initialize() throws InitializeException {
      List<User> userList=userService.findUserList();
      userList.forEach(user -> userCache.put(String.valueOf(user.getId()),user));
    }
}
