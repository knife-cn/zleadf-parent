package com.zlead.exception;

/**
 * 系统定义公用异常，所以异常都继承自该异常.
 */
public abstract class BaseRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 5374159890497984842L;

    /**
     * Create a new BaseRuntimeException with the specified message.
     *
     * @param msg the detail message
     */
    public BaseRuntimeException(String msg) {
        super(msg);
    }

    /**
     * Create a new BaseRuntimeException with the specified message
     * and root cause.
     *
     * @param msg the detail message
     * @param ex  the root cause
     */
    public BaseRuntimeException(String msg, Throwable ex) {
        super(msg, ex);
    }

    public BaseRuntimeException(Throwable ex) {
        super(ex);
    }

}
