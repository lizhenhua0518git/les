package com.zkzn.les.basicInfo.config.exception;


import com.zkzn.les.basicInfo.util.Ecode;

/**
 * @ClassName ExceptionCasts
 * @Description TODO
 * @Author zhanglei
 * Date 2020/9/1 13:05
 * @Version 1.0
 **/
public class ExceptionCasts {
    //自定义异常抛出工具类
    public static void cast(Ecode resultCode){
        throw  new CustomException(resultCode);
    }
}