package com.smallbell.teambition.base.exception;

import com.smallbell.teambition.common.entity.Result;
import com.smallbell.teambition.common.entity.StatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result exception(Exception e){
        e.printStackTrace();
        return new Result(false, StatusCode.ERROR,e.getMessage());

    }
}
