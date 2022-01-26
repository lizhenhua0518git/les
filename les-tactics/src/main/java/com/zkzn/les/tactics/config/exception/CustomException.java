package com.zkzn.les.tactics.config.exception;
import com.zkzn.les.common.util.response.Ecode;

/**
 * @ClassName 全局异常
 * @Description TODO
 * @Author zhanglei
 * Date 2020/8/31 16:21
 * @Version 1.0
 **/
public class CustomException extends RuntimeException  {

    /***
     * @Discription: 返回值，Result应和Ecode关联
     * 数据格式：{"code":"","msg":""}
     * @Author: zhanglei on 2020/8/31 16:23
     */
    Ecode result;

    CustomException(Ecode result){
        super("错误代码："+result.code()+"错误信息："+result.msg());
        this.result = result;
    }

    public Ecode getResult(){
        return this.result;
    }
}