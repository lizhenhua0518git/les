package com.zkzn.les.wms.upperFrame.service.impl;

import com.zkzn.les.common.pojo.MaterialData;
import com.zkzn.les.common.util.response.Result;
import com.zkzn.les.common.util.response.Ecode;
import com.zkzn.les.wms.feign.StockFeignService;
import com.zkzn.les.wms.upperFrame.dao.UpperFrameTaskDao;
import com.zkzn.les.wms.pojo.*;
import com.zkzn.les.wms.upperFrame.pojo.BreakUpperPojo;
import com.zkzn.les.wms.upperFrame.pojo.UpperFrameData;
import com.zkzn.les.wms.upperFrame.pojo.UpperPositionPojo;
import com.zkzn.les.wms.upperFrame.pojo.VerifyPositionPojo;
import com.zkzn.les.wms.upperFrame.service.UpperFrameTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @ClassName UpperFrameTaskServiceImpl
 * @Author zhanglei
 * Date 2021/7/3 15:43
 * @Version 1.0
 **/
@Service
public class UpperFrameTaskServiceImpl implements UpperFrameTaskService {

    private static Logger log = LoggerFactory.getLogger(UpperFrameTaskServiceImpl.class);
    @Autowired
    private StockFeignService stockFeignService;
    @Autowired
    private UpperFrameTaskDao upperFrameTaskDao;
    @Override
    public Map<String, List<GetUpperFrameRecord>> listUpperFrameRecord(String userId, String warehouseCode) {
        List<MaterialData> materialDataList = upperFrameTaskDao.selectUpperFrameRecord(userId, warehouseCode);
        if (materialDataList.size()>0){
            return this.resultMap(materialDataList);
        }else {
            Map<String,List<GetUpperFrameRecord>> map = new HashMap<>();
            map.put("qualifiedUpperList", new ArrayList<>());
            map.put("unqualifiedUpperList", new ArrayList<>());
            return map;
        }

    }

    public Map<String, List<GetUpperFrameRecord>> resultMap(List<MaterialData> materialDataList) {
        Map<String, List<GetUpperFrameRecord>> returnMap = new HashMap<>();
        List<GetUpperFrameRecord> qualifiedUpperList = new ArrayList<>();//合格品集合
        List<MaterialData> qualifiedList = new ArrayList<>();
        List<GetUpperFrameRecord> unqualifiedUpperList = new ArrayList<>();//不合格品集合
        List<MaterialData> unqualifiedList = new ArrayList<>();
        Map<Integer, List<MaterialData>> map = new HashMap<>();

        //集合分组
        for (int i = 0; i < materialDataList.size(); i++) {
            MaterialData materialData = materialDataList.get(i);
            Integer inspectionStatus = materialData.getInspectionStatus();
            if (inspectionStatus == 0) {
                qualifiedList.add(materialData);
            } else if (inspectionStatus == 1){
                unqualifiedList.add(materialData);
            }
        }

        GetUpperFrameRecord getUpperFrameRecord = new GetUpperFrameRecord();
        if (qualifiedList.size()>0){
            getUpperFrameRecord.setStationCode("无工位");
            getUpperFrameRecord.setMaterialDataList(qualifiedList);
            getUpperFrameRecord.setReceiveNumber(qualifiedList.size());
            getUpperFrameRecord.setFlag("0");
            qualifiedUpperList.add(getUpperFrameRecord);
        }else {
            qualifiedUpperList = new ArrayList<>();
        }

        if (unqualifiedList.size()>0) {
            getUpperFrameRecord = new GetUpperFrameRecord();
            getUpperFrameRecord.setStationCode("无工位");
            getUpperFrameRecord.setMaterialDataList(unqualifiedList);
            getUpperFrameRecord.setReceiveNumber(unqualifiedList.size());
            getUpperFrameRecord.setFlag("0");
            unqualifiedUpperList.add(getUpperFrameRecord);
        }else {
            unqualifiedUpperList = new ArrayList<>();
        }

        returnMap.put("qualifiedUpperList", qualifiedUpperList);
        returnMap.put("unqualifiedUpperList", unqualifiedUpperList);
        return returnMap;
    }


    @Override
    public BreakUpperPojo  breakUpperFrameRecord(MaterialData materialData) {
        BreakUpperPojo breakUpperPojo = upperFrameTaskDao.breakUpperFrameRecord(Integer.parseInt(materialData.getUpperId()));
        List<UpperPositionPojo> list = upperFrameTaskDao.getUpperPositionById(Integer.parseInt(materialData.getUpperId()));
        breakUpperPojo.setStorageList(list);
        //log.info("物料的上架数据为:{}", JSON.toJSONString(map));//{"supplierName":"供应商1","materialSerialNumList":["203","202","201"],"batchNo":"2020123113140001","serialNum":"1","materialDesc":"物料M000000004","storageLocation":"Z102","materialCode":"M000000004","supplierCode":"SP00001","upperId":"bf8758305f2f476f82b35594e2107d1a","upperType":1,"storageList":[],"inspectionStatus":1,"inspectPositionCode":"ZJQ","receiveNum":3.0}
        return breakUpperPojo;
    }

    private String receiveDetailStatus(MaterialData materialData) {
        //{"batchNo":"2020123113140001","inspectionStatus":1,"upperId":"bf8758305f2f476f82b35594e2107d1a"}
        String result = "1";
        //不合格状态时 检查是否已拆盘
        if (materialData.getInspectionStatus()==1) {
            Integer data = upperFrameTaskDao.selectDetailStatusByUpperData(materialData);
            if (!StringUtils.isEmpty(data)&&data>0){
                result = "2";
            }
        }
        return result;
    }


    @Override
    public String verifyPosition(VerifyPositionPojo verifyPositionPojo) {
        VerifyPositionPojo getVerifyPositionPojo = upperFrameTaskDao.getPositionByPositionCode(verifyPositionPojo);
        if (getVerifyPositionPojo == null){
            return Result.toJsonUseApp(Ecode.FAIL,"仓位不存在，请选择正确仓位");
        }
//        Double sumCount = getVerifyPositionPojo.getSumCount();
//        if (sumCount == null || !(sumCount>0)){//占用仓位
//            getVerifyPositionPojo.setPositionStatus(1);//当app操作当前仓位修改为0，传值默认为非占用
//            getVerifyPositionPojo.setDeviantData(0);
//            upperFrameTaskDao.updatePosition(getVerifyPositionPojo.getStoragePositionId(),0);
//            return Result.toJsonUseApp(Ecode.SUCCESS, getVerifyPositionPojo);
//        }else {
//            String clientName = verifyPositionPojo.getClientName();
//            String materialDesc = verifyPositionPojo.getMaterialDesc();
//            String positionCode = verifyPositionPojo.getPositionCode();
//            String batchNo = verifyPositionPojo.getBatchNo();
//            Integer upperType = verifyPositionPojo.getUpperType();
//            String key = clientName+materialDesc+positionCode+batchNo+upperType;
//            String clientName1 = getVerifyPositionPojo.getClientName();
//            String materialDesc1 = getVerifyPositionPojo.getMaterialDesc();
//            String positionCode1 = getVerifyPositionPojo.getPositionCode();
//            String batchNo1 = getVerifyPositionPojo.getBatchNo();
//            Integer upperType1 = getVerifyPositionPojo.getUpperType();
//            String getKey = clientName1+materialDesc1+positionCode1+batchNo1+upperType1;
//            if (key.equals(getKey)){
//                getVerifyPositionPojo.setDeviantData(0);
//                getVerifyPositionPojo.setPositionStatus(2);
//                return Result.toJsonUseApp(Ecode.SUCCESS, getVerifyPositionPojo);
//            }else {
//                return Result.toJsonUseApp(Ecode.FAIL,"仓位已存在货物，不符合存储规则");
//            }
//        }
        return Result.toJsonUseApp(Ecode.SUCCESS, getVerifyPositionPojo);
    }

    @Override
    public String verifyCar(VerifyPositionPojo verifyPositionPojo) {
        VerifyPositionPojo getVerifyPositionPojo = upperFrameTaskDao.verifyCar(verifyPositionPojo);
        if (getVerifyPositionPojo == null){
            return Result.toJsonUseApp(Ecode.FAIL,"载具不存在，请选择正确载具");
        }
        return Result.toJsonUseApp(Ecode.SUCCESS, getVerifyPositionPojo);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public String submitUpperFrameRecord(List<UpperPositionPojo> upperPositionPojos, String userId, String currentUserName, String wareHoseCode, String wareHoseName) throws Exception {
        try {
            UpperPositionPojo upperPositionPojo = upperPositionPojos.get(0);
            //修改上架任务状态
            UpperFrameData upperFrameData = new UpperFrameData();
            upperFrameData.setUpperFrameId(upperPositionPojo.getUpperFrameId());
            upperFrameData.setUpperUserId(Integer.parseInt(userId));
            upperFrameData.setUpperUpperName(currentUserName);
            upperFrameData.setUpperUpperTime(new Date());
            upperFrameData.setStatus(1);
            upperFrameTaskDao.updateUpperFrameRecord(upperFrameData);
            //添加使用仓位情况
            upperFrameTaskDao.addActualPosition(upperPositionPojos);
            //仓位占用接触
            for (int i = 0; i < upperPositionPojos.size(); i++) {
                UpperPositionPojo upperPositionPojo1 = upperPositionPojos.get(i);
                upperPositionPojo1.setWarehouseCode(wareHoseCode);
                upperPositionPojo1.setWarehouseName(wareHoseName);
//                Integer positionStatus = upperPositionPojo1.getPositionStatus();
//                if (positionStatus == 1){//若还是闲置状态则说明没有使用当前仓位，仓位状态改为闲置
//                    upperFrameTaskDao.updatePosition(upperPositionPojo1.getStoragePositionId(),1);
//                }
            }
            //新增仓位库存
            stockFeignService.updateMaterialStorageBin(upperPositionPojos);
            //修改到货通知单状态

            return null;
        } catch (Exception e) {
            log.error("上架失败:" + e.getMessage());
            e.printStackTrace();
            throw new Exception(e);
        }
    }

    public UpperFrameRecord cloneUpperFrameRecord(UpperFrameRecord upperFrameRecord) {
        UpperFrameRecord upperFrameRecord1 = new UpperFrameRecord();
        upperFrameRecord1.setUpperRecordId(upperFrameRecord.getUpperRecordId());
        upperFrameRecord1.setReceiveRecordId(upperFrameRecord.getReceiveRecordId());
        upperFrameRecord1.setRecevieDetailId(upperFrameRecord.getRecevieDetailId());
        upperFrameRecord1.setRecommendedPositionId(upperFrameRecord.getRecommendedPositionId());
        upperFrameRecord1.setRecommendedPositionCode(upperFrameRecord.getRecommendedPositionCode());
        upperFrameRecord1.setPositionCode(upperFrameRecord.getPositionCode());
        upperFrameRecord1.setPositionId(upperFrameRecord.getPositionId());
        upperFrameRecord1.setMaterialCode(upperFrameRecord.getMaterialCode());
        upperFrameRecord1.setFactory(upperFrameRecord.getFactory());
        upperFrameRecord1.setMaterialUnit(upperFrameRecord.getMaterialUnit());
        upperFrameRecord1.setMaterialNum(upperFrameRecord.getMaterialNum());
        upperFrameRecord1.setUpperType(upperFrameRecord.getUpperType());
        upperFrameRecord1.setStorageLocation(upperFrameRecord.getStorageLocation());
        upperFrameRecord1.setUpperTime(upperFrameRecord.getUpperTime());
        upperFrameRecord1.setUpperName(upperFrameRecord.getUpperName());
        upperFrameRecord1.setUpperId(upperFrameRecord.getUpperId());
        upperFrameRecord1.setCreateTime(upperFrameRecord.getCreateTime());
        upperFrameRecord1.setStatus(upperFrameRecord.getStatus());
        upperFrameRecord1.setCarrierCode(upperFrameRecord.getCarrierCode());
        upperFrameRecord1.setCarrierId(upperFrameRecord.getCarrierId());
        upperFrameRecord1.setUpperTaskCode(upperFrameRecord.getUpperTaskCode());
        upperFrameRecord1.setSupplierCode(upperFrameRecord.getSupplierCode());
        upperFrameRecord1.setSupplierName(upperFrameRecord.getSupplierName());
        upperFrameRecord1.setBatchNo(upperFrameRecord.getBatchNo());
        upperFrameRecord1.setOrderCode(upperFrameRecord.getOrderCode());
        upperFrameRecord1.setCustormer(upperFrameRecord.getCustormer());
        upperFrameRecord1.setCustomName(upperFrameRecord.getCustomName());
        upperFrameRecord1.setStationCode(upperFrameRecord.getStationCode());
        upperFrameRecord1.setUpperOrigin(upperFrameRecord.getUpperOrigin());
        return upperFrameRecord1;
    }

}
