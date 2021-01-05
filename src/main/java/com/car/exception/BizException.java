package com.car.exception;

import io.swagger.models.auth.In;

/**
 * @author wjz
 * @date 2020/12/24
 */
public class BizException extends RuntimeException{
    /**
     * 错误码
     */
    protected Integer errCode;

    /**
     * 错误消息
     */
    protected String msg;

    public BizException(){
        super("未知业务异常");
    }

    public BizException(String msg){
        super(msg);
        this.msg = msg;
    }

    public BizException(Integer errCode, String msg){
        super(msg);
        this.errCode = errCode;
        this.msg = msg;
    }

}
