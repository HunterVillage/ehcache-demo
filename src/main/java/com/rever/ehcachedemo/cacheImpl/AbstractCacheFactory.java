package com.rever.ehcachedemo.cacheImpl;


import lombok.Getter;

import java.util.Map;

/**
 * @Description: 抽象缓存工厂，主要用于统一管理缓存。主要为以后更改缓存工具时提供方便。
 * @Author: gaoyakang
 * @Version: 1.0
 * @Create Date Time: 2018-08-13 16:53
 * @Update Date Time:
 *
 * @see
 */
@Getter
public abstract class AbstractCacheFactory {

    /**
     * 资源缓存
     */
   // private ICache<Attribute> attributeCache;



    public AbstractCacheFactory(//ICache<Attribute> attributeCache,
                               ) {
      //  this.attributeCache = attributeCache;

    }
}
