package com.rever.ehcachedemo.commoncache.ehcache;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Objects;

/**
 * @Description: ehcache 配置读取工具类
 * @Author: gaoyakang
 * @Version: 1.0
 * @Create Date Time: 2019-01-31 16:47
 * @Update Date Time:
 * @see
 */
public class EhcacheConfigReader {

    public static InputStream getConfigStream(String config) throws FileNotFoundException {
        Objects.requireNonNull(config, "config can't be null");
        final int first = config.indexOf(":");
        if(first == -1){
            throw new RuntimeException("Invalid format of config");
        }
        final String type = config.substring(0, first);
        final String path = config.substring(first+1, config.length());
        if("classpath".equals(type)){
            return EhcacheConfigReader.class.getClass().getResourceAsStream("/" + path);
        } else if("file".equals(type)){
            File file = new File(path);
            return new FileInputStream(file);
        } else {
            throw new RuntimeException("Invalid format of config : " + type);
        }
    }

}
