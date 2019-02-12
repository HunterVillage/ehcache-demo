package com.rever.ehcachedemo.cache;


import com.rever.ehcachedemo.cache.ehcache.UserCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: ehcache 工厂
 * @Author: gaoyakang
 * @Version: 1.0
 * @Create Date Time: 2018-08-29 10:33
 * @Update Date Time:
 * @see
 */
@Component
public class EhcacheCacheFactory extends AbstractCacheFactory {

    @Autowired
    public EhcacheCacheFactory(UserCache userCache) {
        super(userCache);
    }

}
