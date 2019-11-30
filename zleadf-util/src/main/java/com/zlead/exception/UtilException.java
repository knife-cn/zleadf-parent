package com.zlead.exception;

/**
 * 工具异常，直接继承自BaseRuntimeException
 */
public class UtilException extends BaseRuntimeException {

    public UtilException(String msg) {
        super(msg);
    }

    public UtilException(String msg, Throwable ex) {
        super(msg, ex);
    }

    public UtilException(Throwable ex) {
        super(ex);
    }
}
