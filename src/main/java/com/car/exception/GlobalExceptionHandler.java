package com.car.exception;

import com.car.util.RStatic;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wjz
 * @date 2020/12/24
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler((Value = BizException.class))
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public RStatic bizExceptionHandler(HttpRequest req,BizException bizException){

    }

}
