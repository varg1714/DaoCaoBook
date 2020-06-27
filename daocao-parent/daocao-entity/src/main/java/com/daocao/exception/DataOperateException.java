package com.daocao.exception;

/**
 * @author varg
 * @date 2020/5/15 16:49
 * 如果对数据库的增加或修改操作影响行数为0的话，抛出数据操作失败异常
 */
public class DataOperateException extends RuntimeException {

    public DataOperateException() {
    }

    public DataOperateException(String message) {
        super(message);
    }
}
