package com.car.exception;

import com.car.util.RStatic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author wjz
 * @date 2020/12/24
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BizException.class)
    @ResponseBody
    public RStatic bizExceptionHandler(Exception bizException) {
        log.error("业务异常，原因：" + bizException.getMessage());
        bizException.printStackTrace();
        return RStatic.error(bizException.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public RStatic npeExceptionHandler(Exception e){
        log.error("空指针异常,原因："+e.getMessage());
        e.printStackTrace();
        return RStatic.error("空指针异常，原因："+e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RStatic commonExceptionHandler(Exception e){
        log.error("未知异常，原因："+e.getMessage());
        e.printStackTrace();
        return RStatic.error("未知异常，原因："+e.getMessage());
    }

}
