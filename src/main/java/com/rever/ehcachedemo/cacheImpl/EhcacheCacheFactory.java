package com.rever.ehcachedemo.cacheImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: ehcache 工厂
 * @Author: zhangzhuo
 * @Version: 1.0
 * @Create Date Time: 2018-08-29 10:33
 * @Update Date Time:
 * @see
 */
@Component
public class EhcacheCacheFactory extends AbstractCacheFactory{

    @Autowired
    public EhcacheCacheFactory(/*AttributeCache attributeCache*/) {
      //  super(attributeCache);
    }

}
