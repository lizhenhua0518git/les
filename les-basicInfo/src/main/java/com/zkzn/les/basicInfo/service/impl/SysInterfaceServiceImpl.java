package com.zkzn.les.basicInfo.service.impl;

import com.zkzn.les.basicInfo.dao.SysInterfaceDao;
import com.zkzn.les.basicInfo.service.SysInterfaceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SysInterfaceServiceImpl
 * @Description TODO
 * @Author 刘松山
 * @date 2021/6/18 15:32
 **/
@Service
public class SysInterfaceServiceImpl  implements SysInterfaceService {
    @Resource
    private SysInterfaceDao sysInterfaceDao;


    /**.
     * 重载方法
     * @param map
     * @return
     */
    @Override
    public int save(Map<String,Object> map) {
        String dictId = map.get("dictId")+"";
        this.sysInterfaceDao.remove(dictId);
        map.put("createDate",new Date());
        return this.sysInterfaceDao.save(map);
    }




    /**.
     * 重载方法
     * @return
     */
    @Override
    public List<Map<String,Object>> listSysInterfaceList(Map<String,Object> map) {

        return this.sysInterfaceDao.listSysInterfaceList(map);
    }
}
