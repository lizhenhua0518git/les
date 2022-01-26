package com.zkzn.les.common.util.wms;

import com.github.pagehelper.util.StringUtil;

import java.util.Map;

/**
 * @ClassName ParamUtil
 * @Description TODO
 * @Author zhanglei
 * Date 2020/11/5 14:20
 * @Version 1.0
 **/
public class ParamUtil {
    /***
     * @Discription: 必传参 参数校验
     * @param param
 * @param keys
     * @return boolean
     * @Author: zhanglei on 2020/11/5 14:21
     */
    public static void paramsCheck(Map<String,Object> param, String[] keys) throws Exception{
        for(String key :keys){
            if(!param.containsKey(key) || StringUtil.isEmpty((""+param.get(key))) ){
                throw new  Exception(key+":参数为空");
            }
        }
    }
}