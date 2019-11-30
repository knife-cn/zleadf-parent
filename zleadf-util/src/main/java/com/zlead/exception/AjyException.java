package com.zlead.exception;

//业务异常类，请和ResultEnum搭配使用
public class AjyException extends RuntimeException{
    
    private Integer code;
    
    public AjyException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        
        this.code = resultEnum.getCode();
    }
    
    public AjyException(Integer code, String message) {
        super(message);
        this.code = code;
    }
    
    public Integer getCode() {
        return code;
    }
}
