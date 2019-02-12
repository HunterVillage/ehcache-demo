package com.rever.ehcachedemo.exception;

/**
 * @Description: 初始化接口
 * @Author: gaoyakang
 * @Version: 1.0
 * @Create Date Time: 2019-02-12 11:15
 * @Update Date Time:
 * @see
 */
public class InitializeException extends RuntimeException{

    public InitializeException() {
        super();
    }

    public InitializeException(String message) {
        super(message);
    }

    public InitializeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InitializeException(Throwable cause) {
        super(cause);
    }

    protected InitializeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
