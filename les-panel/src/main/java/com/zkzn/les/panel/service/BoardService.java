package com.zkzn.les.panel.service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName InBoundBoardService
 * @Description
 * @Author 刘松山
 * @date 2021/3/15 14:59
 **/
public interface BoardService {
    /* *
     * @Author 刘松山
     * @Description 获取入库物料信息
     * @Date 15:04 2021/3/15
     * @Param
     * @return
     **/

    List<Map<String, Object>> listAllInBoundInfo();
    /* *
     * @Author 刘松山
     * @Description 获取出库物料信息
     * @Date 10:44 2021/3/17
     * @Param
     * @return
     **/

    List<Map<String, Object>> listAllOutBoundInfo();
    /* *
     * @Author 刘松山
     * @Description 获取库存库存信息
     * @Date 15:29 2021/6/2
     * @Param 
     * @return 
     **/
    
    List<Map<String, Object>> listInventory();

}
