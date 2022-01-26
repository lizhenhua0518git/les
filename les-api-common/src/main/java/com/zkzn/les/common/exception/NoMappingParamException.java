package com.zkzn.les.common.exception;

/**
 * @ClassName NoMappingParamString
 * @Description TODO
 * @Author zhanglei
 * Date 2021/6/11 10:38
 * @Version 1.0
 **/
public class NoMappingParamException extends Exception {
    /*无参构造函数*/
    public NoMappingParamException(){
        super();
    }

    //用详细信息指定一个异常
    public NoMappingParamException(String message){
        super(message);
    }

    //用指定的详细信息和原因构造一个新的异常
    public NoMappingParamException(String message, Throwable cause){
        super(message,cause);
    }

    //用指定原因构造一个新的异常
    public NoMappingParamException(Throwable cause) {
        super(cause);
    }
}