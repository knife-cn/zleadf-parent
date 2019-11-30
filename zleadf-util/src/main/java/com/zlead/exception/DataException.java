package com.zlead.exception;

/**
 * 数据异常，继承自BaseRuntimeException
 */
public class DataException extends BaseRuntimeException {
    public DataException(String msg, Throwable e) {
        super(msg, e);
    }

    public DataException(Throwable e) {
        super(e);
    }

    public DataException(String msg) {
        super(msg);
    }
}
