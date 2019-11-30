package com.zlead.dto;

/**
 * Created by hejie on 2015/5/22.
 */
import java.io.Serializable;

import com.zlead.exception.BaseException;
import com.zlead.constant.ErrorCode;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 更新结果类：service 或者 action 返回值
 * @author up72
 */
public class Result<T extends Object> implements Serializable {
    private static final long serialVersionUID = -6681069801029150974L;

    static Log log = LogFactory.getLog(Result.class);

    /**
     * 操作 是否成功
     */
    private boolean success;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 返回附加信息 可以是String、List、JSON、Map 类型
     */
    private T data;

    /**
     * 执行命令
     * @param <D>
     */
    public interface Command<D> { D execute(); }

    public Result() {
        super();
    }
    public Result(boolean success) {
        this.success = success;
        if(success){
            this.setMessage("操作成功");//操作成功
        }else{
            this.setMessage("操作失败");//操作失败
        }

    }
    public Result(boolean success, String message) {
        super();
        this.success = success;
        this.message = message;
    }

    public static <D> Result<D> build(Command<D> cmd) {
        D data = null;
        Result<D> result = new Result<D>();
        try {
            data = cmd.execute();
            result.setSuccess(true);
            result.setData(data);
        }
        catch (BaseException e) {
            log.error(e.getMessage(), e);
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
            result.setSuccess(false);
            result.setMessage((new BaseException(ErrorCode.FR_011)).getMessage());
        }
        return result;
    }

    public boolean isSuccess() {
        return success;
    }
    public Result setSuccess(boolean success) {
        this.success = success;
        return this;
    }
    public String getMessage() {
        return message;
    }
    public Result setMessage(String message) {
        this.message = message;
        return this;
    }
    public T getData() {
        if(this.data == null) {
            this.data = (T) ""; // 当data为null时，返回""，否则IOS接口会报错
        }
        return data;
    }
    public Result setData(T data) {
        this.data = data;
        return this;
    }
}
