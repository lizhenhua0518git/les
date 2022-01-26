package com.zkzn.les.common.exception;

import com.zkzn.les.common.util.str.StrUtil;


import java.util.Map;

/**
 * @ClassName EmtyExamine
 * @Description TODO
 * @Author zhanglei
 * Date 2020/9/1 17:38
 * @Version 1.0
 **/
public class EmptyExamine {

    private EmptyExamine(){};

    public static void examine(Map<String,Object> paraMap,String... paramKeys) throws Exception{
        int size = 0;
        if ((size = paramKeys.length) > 0) {
            for (int i = 0; i < size; i++) {
                if ( null == paraMap.get(paramKeys[i]) || StrUtil.isBlank(paraMap.get(paramKeys[i]).toString())) {
                    throw new NoMappingParamException(String.format("缺少必传参数:%s",paramKeys[i]));
                }
            }
        }
    }
}