package com.zkzn.les.tactics.config;

import com.google.common.collect.Maps;
import com.zkzn.les.tactics.dao.UploadStrategyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @ClassName UploadAddressRunner
 * @Description  卸货点队列信息初始化
 * @Author zhanglei
 * Date 2021/6/28 15:02
 * @Version 1.0
 **/
@Component
public class UploadAddressQueue  {

    private  Queue<Map<String,Object>> uploadQueue = new LinkedBlockingDeque<>();


   @Autowired
   private UploadStrategyDao uploadStrategyDao;

    public  Map<String,Object> getQueueInfo () {
        Map<String, Object> peek = uploadQueue.poll();
        uploadQueue.add(peek);
        return peek;
    }

    @PostConstruct // 构造函数之后执行
    public void init(){
        List<Map<String, Object>> uploadInfos = uploadStrategyDao.listFreeAddresses(Maps.newHashMap());
        if (!uploadInfos.isEmpty()) {
            for (Map<String, Object> uploadInfo : uploadInfos) {
                uploadQueue.add(uploadInfo);
            }
        }
    }
}