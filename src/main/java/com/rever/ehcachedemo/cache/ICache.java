package com.rever.ehcachedemo.cache;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Description: 缓存接口
 * Author: zhangzhuo
 * Version: 1.0
 * Create Date Time: 2018-08-13 15:41
 * Update Date Time:
 *
 * @see
 */
public interface ICache<T> {

    /**
     * 是否存在key
     * @param key 缓存key
     * @return true存在, false不存在
     */
    Boolean exists(String key);

    /**
     * 通过key获取数据
     * @param key 缓存key
     * @return 返回通过key获取到的数据
     */
    T get(String key);

    /**
     * 批量获取数据
     * @param keys 存key集合
     * @return 返回通过key集合获取到的数据集合
     */
    List<T> get(Set<String> keys);

    /**
     * 获取到所有的key集合
     * @return 返回当前缓存所有的keys
     */
    Set<String> getKeys();

    /**
     * 向缓存中存储数据, 如果存在将会替换
     * @param key 缓存key
     * @param t 具体缓存的数据
     * @return 是否成功, true 成功,false 不成功
     */
    Boolean put(String key, T t);

    /**
     * 批量向缓存中存储数据, 如果存在将会替换
     * @param map 缓存键值对
     * @return 是否成功, true 成功,false 不成功
     */
    Boolean put(Map<String, T> map);

    /**
     * 向缓存中存储数据, 如果存在，则不插入
     * @param key 缓存key
     * @param t 具体缓存的数据
     * @return 是否成功, true 成功,false 不成功
     */
    Boolean putIfAbsent(String key, T t);

    /**
     * 批量向缓存中存储数据, 如果存在，则不插入
     * @param map 缓存键值对
     * @return 是否成功, true 成功,false 不成功
     */
    Boolean putIfAbsent(Map<String, T> map);

    /**
     * 通过key删除数据
     * @param key 缓存key
     * @return 是否成功, true 成功,false 不成功
     */
    Boolean remove(String key);

    /**
     * 通过key批量删除数据
     * @param keys 缓存key集合
     * @return 是否成功, true 成功,false 不成功
     */
    Boolean remove(Set<String> keys);


    /**
     * 更新缓存
     * @param key 缓存key集合
     * @param t 新的缓存数据
     * @return 是否成功
     */
    Boolean update(String key, T t);

    /**
     * 批量更新缓存数据
     * @param map 新缓存键值对
     * @return 是否成功, true 成功,false 不成功
     */
    Boolean update(Map<String, T> map);

    /**
     * 清除缓存
     * @return 是否成功, true 成功,false 不成功
     */
    Boolean clean();

    /**
     * 返回原始操作对象
     * @param <E> 对象类型
     * @return 原始操作对象
     */
    <E> E getOriginal();

}
