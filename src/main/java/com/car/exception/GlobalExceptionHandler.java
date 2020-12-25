package com.car.exception;

import com.car.util.RStatic;
import jdk.Exported;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpRequest;
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
    public RStatic bizExceptionHandler(HttpRequest req, BizException bizException) {
        log.error("业务异常，原因：" + bizException.getMessage());
        return RStatic.error(bizException.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public RStatic npeExceptionHandler(HttpRequest req,NullPointerException e){
        log.error("空指针异常,原因："+e.getMessage());
        return RStatic.error("空指针异常，原因："+e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RStatic commonExceptionHandler(HttpRequest req,Exception e){
        log.error("未知异常，原因："+e.getMessage());
        return RStatic.error("未知异常，原因："+e.getMessage());
    }

}
