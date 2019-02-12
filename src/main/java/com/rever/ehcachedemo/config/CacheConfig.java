package com.rever.ehcachedemo.config;

import com.rever.ehcachedemo.commoncache.ehcache.EhcacheConfigReader;
import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @Description: cache 配置
 * @Author: gaoyakang
 * @Version: 1.0
 * @Create Date Time: 2019-02-12 14:21
 * @Update Date Time:
 * @see
 */
@Configuration
public class CacheConfig {

    @Value("${ehcache-config:classpath:ehcache/ehcache.xml}")
    private String ehcacheConfig;

    @Bean
    public CacheManager cacheManager() throws FileNotFoundException {
        InputStream inputStream= EhcacheConfigReader.getConfigStream(ehcacheConfig);
        return new CacheManager(inputStream);
    }

}
