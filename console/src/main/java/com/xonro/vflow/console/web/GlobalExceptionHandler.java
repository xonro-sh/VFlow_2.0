package com.xonro.vflow.console.web;

import com.xonro.vflow.bases.bean.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * 全局异常捕获
 * @author louie
 * @date created in 2018-4-17 15:06
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BaseResponse exceptionHandler(Exception e){
        String msg = e.getMessage();
        if (e instanceof ConstraintViolationException){
            ConstraintViolationException violationException = (ConstraintViolationException)e;
            Set<ConstraintViolation<?>> violations = violationException.getConstraintViolations();
            StringBuilder builder = new StringBuilder();
            for (ConstraintViolation<?> violation : violations) {
                builder.append(violation.getMessage()+";");
            }
            msg = builder.toString();
        }else if (e instanceof BindException){
            BindException bindException = (BindException) e;
            List<ObjectError> errors = bindException.getAllErrors();
            StringBuilder builder = new StringBuilder();
            for (ObjectError error : errors) {
                builder.append(error.getObjectName()).append(" validate error,message:").append(error.getDefaultMessage()).append("\n");
            }
            msg = builder.toString();
        }
        log.error(msg,e);
        return new BaseResponse(false,"error",msg);
    }
}
