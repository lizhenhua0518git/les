package com.zkzn.les.basicInfo.config.exception;

import com.google.common.collect.ImmutableMap;

import com.zkzn.les.basicInfo.util.Ecode;
import com.zkzn.les.basicInfo.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName 全局异常捕获
 * @Author zhanglei
 * Date 2020/8/31 16:28
 * @Version 1.0
 **/
@ControllerAdvice
public class ExceptionCatch {
    //sl4j异常日志
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionCatch.class);
    //不可预知异常集合,Immutable 只读线程安全
    private static ImmutableMap<Class<? extends Throwable>, Ecode> EXCEPTIONS;

    protected static ImmutableMap.Builder<Class<? extends Throwable>, Ecode> builder = ImmutableMap.builder();

    static {
        //在这里加入一些基础的异常类型判断
        builder.put(HttpMessageNotReadableException.class,Ecode.FAIL);
    }

    /***
     * @Discription: 自定义异常
     * @param customException
     * @return java.lang.String
     * @Author: zhanglei on 2020/8/31 16:48
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public String customException(CustomException customException) {
        LOGGER.error("catch exception : {}\r\nexception: ", customException.getMessage(), customException);
        Ecode resultCode = customException.getResult();

        return Result.toJson(resultCode, null);
    }

    /***
     * @Discription: 系统异常
     * @param exception
     * @return java.lang.String
     * @Author: zhanglei on 2020/8/31 16:50
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String exception(Exception exception) {
        LOGGER.error("catch exception : {}\r\nexception: ", exception.getMessage(), exception);
        if (EXCEPTIONS == null) {
            EXCEPTIONS = builder.build();
        }
        final Ecode resultCode = EXCEPTIONS.get(exception.getClass());
        String result;
        if (resultCode == null) {
            //返回全局异常，服务器异常
            result = Result.toJson(Ecode.INTER, null);
        } else {
            //返回定义的异常code码
             result = Result.toJson(resultCode, null);
        }
       return result;
    }
}