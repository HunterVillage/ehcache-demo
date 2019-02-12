package com.rever.ehcachedemo.service;


import com.baomidou.mybatisplus.service.IService;
import com.rever.ehcachedemo.entity.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gaoyakang
 * @since 2019-01-28
 */
public interface UserService extends IService<User> {
    /**
     * 缓存中获取所有用户
     * @return
     */
    List<User> findUserListInCache();

    /**
     * 获取所有用户
     *
     * @return
     */
    List<User> findUserList();
}
