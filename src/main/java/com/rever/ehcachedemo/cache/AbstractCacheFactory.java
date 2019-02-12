package com.rever.ehcachedemo.cache;


import com.rever.ehcachedemo.commoncache.ICache;
import com.rever.ehcachedemo.entity.User;
import lombok.Getter;

/**
 * @Description: 抽象缓存工厂，主要用于统一管理缓存。主要为以后更改缓存工具时提供方便。
 * @Author: gaoyakang
 * @Version: 1.0
 * @Create Date Time: 2018-08-13 16:53
 * @Update Date Time:
 * @see
 */
@Getter
public abstract class AbstractCacheFactory {

    private ICache<User> userCache;

    public AbstractCacheFactory(ICache<User> userCache) {
        this.userCache = userCache;
    }
}
