
package com.zlead.exception;

/**
 * 参数找不到，属于数据异常 DataException
 */
public class ParameterNotFoundException extends DataException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * @param arg0
     */
    public ParameterNotFoundException(String arg0) {
        super(arg0);
    }

    /**
     * @param arg0
     * @param arg1
     */
    public ParameterNotFoundException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    /**
     * @param arg0
     */
    public ParameterNotFoundException(Throwable arg0) {
        super(arg0);
    }

}

