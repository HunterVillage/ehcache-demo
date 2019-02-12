package com.rever.ehcachedemo.cache.ehcache;

import com.rever.ehcachedemo.cache.CacheConstant;
import com.rever.ehcachedemo.commoncache.ehcache.AbstractEhcache;

import com.rever.ehcachedemo.entity.User;
import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



/**
 * @Description:
 * @Author: gaoyakang
 * @Version: 1.0
 * @Create Date Time: 2019-02-12 10:36
 * @Update Date Time:
 * @see
 */
@Component
public class UserCache extends AbstractEhcache<User> {

    @Autowired
    public UserCache(CacheManager cacheManager) {
        super(cacheManager);
    }

    @Override
    protected String cacheName() {
        return CacheConstant.EHCACHE_COMMON_NAME;
    }

    @Override
    protected String keyPrefix() {
        return "user";
    }


}
