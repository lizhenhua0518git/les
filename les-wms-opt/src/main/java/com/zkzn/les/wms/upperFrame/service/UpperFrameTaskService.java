package com.zkzn.les.wms.upperFrame.service;

import com.zkzn.les.common.pojo.MaterialData;
import com.zkzn.les.wms.pojo.GetUpperFrameRecord;
import com.zkzn.les.wms.pojo.StoragePosition;
import com.zkzn.les.wms.pojo.upperFrameTask.ReceiveDetailRecord;
import com.zkzn.les.wms.upperFrame.pojo.BreakUpperPojo;
import com.zkzn.les.wms.upperFrame.pojo.UpperPositionPojo;
import com.zkzn.les.wms.upperFrame.pojo.VerifyPositionPojo;

import java.util.List;
import java.util.Map;

/**
 * @ClassName UpperFrameTaskService
 * @Description 上架服务
 * @Author zhanglei
 * Date 2021/7/3 15:43
 * @Version 1.0
 **/
public interface UpperFrameTaskService {

    /**
     * 查询上架表信息
     * @param userId
     * @return
     */
    Map<String, List<GetUpperFrameRecord>> listUpperFrameRecord(String userId, String warehouseCode);

    /**
     * 上架操作数据
     * @param materialData
     * @return
     */
    BreakUpperPojo breakUpperFrameRecord(MaterialData materialData);

    /**
     * 上架校验仓位
     * @param verifyPositionPojo
     * @return
     */
    String verifyPosition(VerifyPositionPojo verifyPositionPojo);

    /**
     * 上架校验仓位
     * @param verifyPositionPojo
     * @return
     */
    String verifyCar(VerifyPositionPojo verifyPositionPojo);

    /**
     * 提交上架数据
     * @param upperPositionPojos
     * @return
     */
    String submitUpperFrameRecord(List<UpperPositionPojo> upperPositionPojos, String userId, String currentUserName, String wareHoseCode, String wareHoseName) throws Exception;
}
