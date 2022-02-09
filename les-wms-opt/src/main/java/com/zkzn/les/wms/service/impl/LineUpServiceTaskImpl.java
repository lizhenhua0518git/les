package com.zkzn.les.wms.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zkzn.les.common.exception.EmptyExamine;
import com.zkzn.les.common.util.date.DateUtil;
import com.zkzn.les.common.util.idUtil.IdUtil;
import com.zkzn.les.common.util.json.JsonUtil;
import com.zkzn.les.common.util.lang.BatchCodeUtil;
import com.zkzn.les.common.util.redis.RedisNoUtil;
import com.zkzn.les.common.util.str.StrUtil;
import com.zkzn.les.wms.constant.WmsConstants;
import com.zkzn.les.wms.dao.AdmissionTaskDao;
import com.zkzn.les.wms.dao.LineUpTaskDao;
import com.zkzn.les.wms.feign.StrategyFeignService;
import com.zkzn.les.wms.pojo.UploadAddress;
import com.zkzn.les.wms.pojo.admission.AdmissionTask;
import com.zkzn.les.wms.pojo.arrivalNotice.ArrivalNoticePojo;
import com.zkzn.les.wms.receiveRecord.dao.ReceiveTaskDao;
import com.zkzn.les.wms.service.LineUpTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
/***
 * @Discription: 排号service
 * @Author: zhanglei on 2021/6/10 10:52
 */
@Service
public class LineUpServiceTaskImpl implements LineUpTaskService {

    private final Logger LOGGER = LoggerFactory.getLogger(LineUpServiceTaskImpl.class);

    @Autowired
    private LineUpTaskDao lineUpDao;

    @Autowired
    private ReceiveTaskDao receiveTaskDao;
    @Autowired
    private AdmissionTaskDao admissionTaskDao;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StrategyFeignService strategyFeignService;

    /***
     * @Discription: 送货单信息查询判断是否可以生成排队任务号
     *               1.检验送货单是否存在
     *               2.是否适应排号策略，判断是否可以生成排队号
     * @param param
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @Author: zhanglei on 2021/6/9 15:52
     */
    @Override
    public Map<String, Object> listArrivalNotice(Map<String, Object> param) throws Exception {
        //1.送货单参数校验
        String billCode = null;
        if (StrUtil.isBlank(billCode = param.get(WmsConstants.BILL_CODE).toString())) {
            throw new Exception("缺少参数billCode");
        }
        //1.检验送货单是否存在
        Map<String, Object> resultMap = null;
        resultMap = lineUpDao.listArrivalNotice(param);
        if (null == resultMap || resultMap.isEmpty()) {
            throw new Exception(String.format("送货单%s不存在", billCode));
        } else if (WmsConstants.NUMBER_1.equals(resultMap.get(WmsConstants.QUEUE_STATE))) {
            throw new Exception(String.format("送货单%s已经排过号", billCode));
        }
        //2.是否使用策略判断
        return resultMap;
    }

    /***
     * @Discription: 保存排号信息
     *           前提：送货单可使用
     *             1.生成排号任务分配卸货点 [策略决定]  {"uploadCode":"卸货点编码","useStatus":"使用状态","id":"卸货地点id"}
     *             2.生成排队号[排号策略],每天任务从1开始
     *             3.生成入库初始化信息
     *             4.保存车辆入场信息
     *             5.修改到货通知单状态
     * @param param
     * @return java.lang.String
     * @Author: zhanglei on 2021/6/9 17:28
    //	 */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public long saveLineUpInfo(Map<String, Object> param) throws Exception {
        //1.判断是否可以进行排号
        Map<String, Object> arrivalNotice = this.listArrivalNotice(param);
        //2.获取卸货点[策略驱动]
        //数据格式 {"uploadPlat":"卸货点Code","id":"卸货点主表id","useStatus":"卸货点使用状态 1 可用 0 不可用"}
        //TODO 调用策略接口获取卸货点信息
        param.put(WmsConstants.STRATEGY_TYPE,WmsConstants.XHD_STRATEGY);
        String uploadStrategyResult = strategyFeignService.getUploadStrategyResult(param);
        Map<String,Object> uploadResult = JsonUtil.getJsonToBean(uploadStrategyResult, Map.class);
        if (WmsConstants.NUMBER_1_STR_.equals(uploadResult.get(WmsConstants.CODE).toString())) {
            throw new Exception(String.format("卸货点信息获取错误:s%",uploadResult.get(WmsConstants.DATA)));
        }
        Map<String,Object> uploadInfo = (Map)uploadResult.get(WmsConstants.DATA);
        LOGGER.info("获取卸货点信息:{}",uploadInfo);
        //JSONObject uploadInfo = new JSONObject();
        //test 测试数据
//        uploadInfo.put("uploadPlat","cxz002");
//        uploadInfo.put("id",1);
//        uploadInfo.put("useStatus",1);
//        uploadInfo.put("uploadPlatName","卸货点1");
        //3.生成排队号[策略驱动]
        //排队号数据格式 {"queueNum":12}
        long queueNum = RedisNoUtil.getSeqNoByExpireTime(RedisNoUtil.QUEUE_CODE,redisTemplate);
        param.put(WmsConstants.QUEUE_NUM,queueNum);
        //queueCode arrivalNoticeId 到货通知单id
        EmptyExamine.examine(param, WmsConstants.QUEUE_NUM, WmsConstants.ARRIVAL_NOTICE_ID);
        //排队号
        String arrivalNoticeId = param.get(WmsConstants.ARRIVAL_NOTICE_ID).toString();
        //4.保存排队任务
        param.put(WmsConstants.QUEUE_CODE, queueNum);
        param.put(WmsConstants.ARRIVAL_NOTICE_ID, arrivalNoticeId);
        param.put(WmsConstants.UPLOAD_PLAT_ID,uploadInfo.get(WmsConstants.ID));
        param.put(WmsConstants.UPLOAD_PLAT_CODE, uploadInfo.get(WmsConstants.UPLOAD_CODE));
        param.put(WmsConstants.UPLOAD_PLAT_NAME, uploadInfo.get(WmsConstants.UPLOAD_NAME));
        admissionTaskDao.saveAdmissionTaskInfo(param);
        //5.修改到货通知单状态
        param.put(WmsConstants.QUEUE_STATUS, WmsConstants.NUMBER_1);
        param.put(WmsConstants.UPLOAD_STATUS, WmsConstants.NUMBER_0);
        param.put(WmsConstants.ACTUAL_ARRIVAL_TIME, new Date());
        lineUpDao.modifyArrival(param);
        return queueNum;
    }

    /**
     * @param param
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @Description : 排号主表信息查询
     * @Author : leizhang
     * @Date 8:23 下午 2021/6/10
     **/
    @Override
    public List<Map<String, Object>> listLineUpMainInfo(Map<String, Object> param) {
        List<Map<String, Object>> list = lineUpDao.listLineUpMainInfo(param);
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = list.get(i);
                getQueueTime(map);
            }
        }
        return list;
    }

    private void getQueueTime(Map<String, Object> map) {
        Date queueTime = (Date)map.get(WmsConstants.QUEUE_TIME);
        int minute = DateUtil.dateTimeMinus(new SimpleDateFormat(WmsConstants.YYMMDDHHMMSS).format(queueTime), DateUtil.getCurrentDateWithFormat(WmsConstants.YYMMDDHHMMSS), 3);
        int hour = 0;
        if (minute / 60 > 0) {
            hour = minute / 60;
            minute = minute % 60;
        }
        if (hour != 0) {
            if (minute != 0) {
                map.put(WmsConstants.TIME_VALUE, hour + WmsConstants.HOUR + minute + WmsConstants.MIN);
            } else {
                map.put(WmsConstants.TIME_VALUE, hour + WmsConstants.HOUR);
            }
        } else {
            if (minute != 0) {
                map.put(WmsConstants.TIME_VALUE, minute + WmsConstants.MIN);
            } else {
                map.put(WmsConstants.TIME_VALUE, WmsConstants.NUMBER_1_STR.concat(WmsConstants.MIN));
            }
        }
    }

    /***
     * @Discription: 排号详情信息获取
     * @param param
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @Author: zhanglei on 2021/6/11 9:37
     */
    @Override
    public ArrivalNoticePojo getLineUpInfo(Map<String, Object> param) {
        ArrivalNoticePojo pojo = lineUpDao.getLineUpMainInfo(param);
        Date queueTime = pojo.getQueueTime();
        int minute = DateUtil.dateTimeMinus(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(queueTime), DateUtil.getCurrentDateWithFormat(WmsConstants.YYMMDDHHMMSS), 3);
        int hour = 0;
        if (minute / 60 > 0) {
            hour = minute / 60;
            minute = minute % 60;
        }
        if (hour != 0) {
            if (minute != 0) {
                pojo.setTimeValue(hour + WmsConstants.HOUR + minute + WmsConstants.MIN);
            } else {
                pojo.setTimeValue(hour + WmsConstants.HOUR);
            }
        } else {
            if (minute != 0) {
                pojo.setTimeValue(minute + WmsConstants.MIN);
            } else {
                pojo.setTimeValue(WmsConstants.NUMBER_1_STR.concat(WmsConstants.MIN));
            }
        }
        return pojo;
    }

    /***
     * @Discription: 叫号
     * 				1.修改卸货点
     * 			    2.修改排号车辆
     * 			   del 3.修改到货通知单
     * 			   4.更新月台使用情况
     * @param param
     * @return int
     * @Author: zhanglei on 2021/6/11 9:59
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void callNum(Map<String, Object> param) throws Exception {
        //1.修改卸货点
        // 必传参数参数校验 receiveId点收表Id admissionId 排号任务主表id  arrivalNoticeId 到货通知单表id uploadCode 月台编码  uploadPlatId 月台主键id
        EmptyExamine.examine(param, WmsConstants.UPLOAD_PLAT_ID, WmsConstants.ADMISSION_ID);
        //如果卸货点未发生改变则不需要修改点收表中的卸货点编码
//		if (!StrUtil.isBlank(param.get(WmsConstants.UPLOAD_CODE).toString())) {
//			EmptyExamine.examine(param,WmsConstants.RECEIVE_ID);
//			lineUpDao.modifyUploadPlat(param);
//		}
        //2.修改排号车辆为入场
        param.put(WmsConstants.IN_FACTORY_TIME, new Date());
        param.put(WmsConstants.ADMISSION_STATUS, WmsConstants.NUMBER_1);
        param.put(WmsConstants.STATUS, WmsConstants.NUMBER_1);
        param.put(WmsConstants.ID, param.get(WmsConstants.ADMISSION_ID));
        admissionTaskDao.modifyAdmissionInfo(param);
        //3.更新月台使用情况
        param.put(WmsConstants.USE_STATUS, WmsConstants.NUMBER_1);
        lineUpDao.modifyUploadPlatInfo(param);
    }

    /***
     * @Discription: 卸货点查询
     * @param uploadAddress
     * @return java.util.List<UploadAddress>
     * @Author: zhanglei on 2021/6/12 0:00
     */
    public List<UploadAddress> listUploadAddress(UploadAddress uploadAddress) {
        List<UploadAddress> list = lineUpDao.listUploadAddress(uploadAddress);
        return list;
    }

    /***
     * @Discription: 取消排号接口
     *               1.修改到货通知单状态 1->0
     *               2.修改排号任务表中的排号状态 (当前状态大于2的不可取消排号)
     *               3.修改月台状态
     * @param params
     * @return int
     * @Author: zhanglei on 2021/6/12 0:30
     */
    @Override
	@Transactional(rollbackFor = Exception.class)
	public void delLineUpInfo(Map<String, Object> params) throws Exception{
        EmptyExamine.examine(params, WmsConstants.ARRIVAL_NOTICE_ID,WmsConstants.ADMISSION_ID,WmsConstants.UPLOAD_PLAT_ID);
		//1.修改到货通知单状态 1->0 到货通知单id arrivalNoticeId
        params.putIfAbsent(WmsConstants.QUEUE_STATUS,WmsConstants.NUMBER_0);
		lineUpDao.modifyArrival(params);
		//2.修改排号任务表中的排号状态
        List<AdmissionTask> admissionTasks = admissionTaskDao.listAdmissionTasks(params);
        if (admissionTasks.size() != WmsConstants.NUMBER_1) {
            throw new Exception("排号任务列表数据可能重复或者不存在");
        }
        AdmissionTask admissionTask = admissionTasks.get(0);
        if (admissionTask.getStatus() > WmsConstants.NUMBER_2) {
            throw new Exception("当前排号任务不可取消");
        }
        params.putIfAbsent(WmsConstants.STATUS,WmsConstants.NUMBER_3);
        params.putIfAbsent(WmsConstants.UPLOAD_FINISH_TIME,new Date());
        admissionTaskDao.modifyAdmissionInfo(params);
        //修改卸货点状态
        params.put(WmsConstants.USE_STATUS,WmsConstants.NUMBER_0);
        lineUpDao.modifyUploadPlatInfo(params);
	}

	/***
	 * @Discription: 卸货完成，生成点收任务
     *               1.校验到货通知单id
     *               2.保存点收任务主表字表任务详情(同一个到货通知单下，不同的库存地点物料在点收任务中分行存取)
     *               {"arrivalNoticeId":"","admissionId":"","shipperCode":"","shipperName":"",
     *                "storageLocations":[{"arrivalItemNo":,"materialCode":"","storageLocation":"","arrivalItemNo":}]
     *               }
     *               3.修改月台占用情况
	 * @param param
	 * @return void
	 * @Author: zhanglei on 2021/6/14 16:27
	 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveReceiveTaskInfo(Map<String, Object> param) throws Exception{
        //1.检验必传参数 到货通知单id 排号任务主表aid
        EmptyExamine.examine(param, WmsConstants.ARRIVAL_NOTICE_ID,WmsConstants.ADMISSION_ID,WmsConstants.UPLOAD_PLAT_ID);
        //2.查询到货通知单物料信息
        List<Map<String, Object>> listArrivalNoticeDetail = lineUpDao.getListArrivalNoticeDetail(param);
        if (listArrivalNoticeDetail.isEmpty()) {
            throw new Exception("到货通知单物料信息为空");
        }
        Map<String,Object> storageLocationMap = Maps.newHashMap();

        //库存地点修改
        List<Map<String,Object>> storageLocations = null;
        if (param.get(WmsConstants.STORAGE_LOCATIONS) instanceof List) {
            storageLocations = (List)param.get(WmsConstants.STORAGE_LOCATIONS);
        }

        //子表数据
        int size = listArrivalNoticeDetail.size();

        for (int i = 0; i < size; i++) {
            Map<String,Object> arrivalNoticeDetail = listArrivalNoticeDetail.get(i);
            //一个库存地点一行记录
            String storageLocation = null;
            if (!StrUtil.isBlank( storageLocation = arrivalNoticeDetail.get(WmsConstants.STORAGE_LOCATION).toString())) {
                if (!storageLocations.isEmpty()) {
                    for (Map<String, Object> location : storageLocations) {
                        if ( null !=arrivalNoticeDetail.get(WmsConstants.MATERIAL_CODE) &&
                                arrivalNoticeDetail.get(WmsConstants.MATERIAL_CODE).toString().equals(location.get(WmsConstants.MATERIAL_CODE).toString())
                             && location.get(WmsConstants.ARRIVAL_ITEM_NO).toString().equals(arrivalNoticeDetail.get(WmsConstants.ARRIVAL_ITEM_NO).toString())
                        ) {
                            storageLocation = location.get(WmsConstants.STORAGE_LOCATION).toString();
                        }
                    }
                }
                //库存地点不为空
                if (!storageLocationMap.containsKey(storageLocation)) {
                    String receiveDetailId = IdUtil.fastSimpleUUID();
                    storageLocationMap.put(storageLocation, receiveDetailId);
                }
                arrivalNoticeDetail.put(WmsConstants.RECEIVE_ID,storageLocationMap.get(storageLocation));
                arrivalNoticeDetail.put(WmsConstants.STORAGE_LOCATION,storageLocation);
                arrivalNoticeDetail.put(WmsConstants.RECEIVE_TASK_CODE, RedisNoUtil.getSeqNo(RedisNoUtil.DS_ORDER_KEY,redisTemplate));
                arrivalNoticeDetail.put(WmsConstants.SAP_ITEM_NO,i);
                arrivalNoticeDetail.put(WmsConstants.ITEM_NO,arrivalNoticeDetail.get(WmsConstants.ARRIVAL_ITEM_NO));
                arrivalNoticeDetail.put(WmsConstants.BATCH_NO, BatchCodeUtil.crateBatchCode());
                arrivalNoticeDetail.put(WmsConstants.ARRIVAL_DETAIL_ID, arrivalNoticeDetail.get(WmsConstants.SOURCE_ID));
                //依据当前登录信息 保存创建者id与创建者姓名
//            arrivalNoticeDetail.put(WmsConstants.CREATER_ID,);
//            arrivalNoticeDetail.put(WmsConstants.SUPPLIER_CODE,);
            }
        }
        List<Map<String,Object>> receiveTasks = Lists.newArrayList();
        Map<String,Object> receiveTaskMap = null;
        //主表数据
        Map<String, Object> arrivalNotice = listArrivalNoticeDetail.get(WmsConstants.NUMBER_0);
        for (Map.Entry<String, Object> storageLocation : storageLocationMap.entrySet()) {
            receiveTaskMap = Maps.newHashMap();
            //id
            receiveTaskMap.put(WmsConstants.ID,storageLocation.getValue());
            receiveTaskMap.put(WmsConstants.STORAGE_LOCATION,storageLocation.getKey());
            receiveTaskMap.put(WmsConstants.FACTORY,arrivalNotice.get(WmsConstants.FACTORY));
            receiveTaskMap.put(WmsConstants.WAREHOUSE_CODE,storageLocation.getKey());
            //业务类型 业务类型默认是1 采购入库
            receiveTaskMap.put(WmsConstants.BILL_TYPE,param.get(WmsConstants.BILL_TYPE)==null?WmsConstants.NUMBER_1:param.get(WmsConstants.BILL_TYPE));
            receiveTaskMap.put(WmsConstants.ADMISSION_ID,param.get(WmsConstants.ADMISSION_ID));
            receiveTaskMap.put(WmsConstants.WAREHOUSE_NAME,param.get(WmsConstants.WAREHOUSE_NAME));
            receiveTaskMap.put(WmsConstants.RECEIVE_TASK_CODE, RedisNoUtil.getSeqNo(RedisNoUtil.DS_ORDER_KEY_M,redisTemplate));
            receiveTaskMap.put(WmsConstants.SHIPPER_NAME,arrivalNotice.get(WmsConstants.SHIPPER_NAME));
            receiveTaskMap.put(WmsConstants.SHIPPER_CODE,arrivalNotice.get(WmsConstants.SHIPPER_CODE));
            receiveTaskMap.put(WmsConstants.ARRIVAL_NOTICE_ID,arrivalNotice.get(WmsConstants.ARRIVAL_NOTICE_ID));
            receiveTaskMap.put(WmsConstants.ORDER_CODE,arrivalNotice.get(WmsConstants.ORDER_CODE));
            receiveTaskMap.put(WmsConstants.BILL_CODE,arrivalNotice.get(WmsConstants.ARRIVAL_CODE));
            receiveTaskMap.put(WmsConstants.BILL_NAME,arrivalNotice.get(WmsConstants.BILL_NAME));
            receiveTaskMap.put(WmsConstants.UPLOAD_PLAT_ID,param.get(WmsConstants.UPLOAD_PLAT_ID));
            receiveTaskMap.put(WmsConstants.UPLOAD_PLAT_CODE,param.get(WmsConstants.UPLOAD_PLAT_CODE));
            receiveTaskMap.put(WmsConstants.PURCHASE_CODE,arrivalNotice.get(WmsConstants.PURCHASE_CODE));
            //依据当前登录信息 保存创建者id与创建者姓名
//            receiveTaskMap.put(WmsConstants.CREATER_ID,);
//            receiveTaskMap.put(WmsConstants.SUPPLIER_CODE,);
            receiveTasks.add(receiveTaskMap);
        }
        receiveTaskDao.saveReceiveInfo(receiveTasks);
        receiveTaskDao.saveReceiveRecordDetails(listArrivalNoticeDetail);
        //修改月台使用情况
        param.put(WmsConstants.USE_STATUS,WmsConstants.NUMBER_0);
        lineUpDao.modifyUploadPlatInfo(param);
        //修改排号任务状态 完成
        param.putIfAbsent(WmsConstants.STATUS,WmsConstants.NUMBER_4);
        Date finishTime = new Date();
        param.putIfAbsent(WmsConstants.UPLOAD_FINISH_TIME,finishTime);
      AdmissionTask admissionTask = admissionTaskDao.listAdmissionTasks(param).get(WmsConstants.NUMBER_0);
        param.put(WmsConstants.TOTAL_TIME,finishTime.getTime() - admissionTask.getCreateTime().getTime());
        admissionTaskDao.modifyAdmissionInfo(param);
    }

    /***
     * @Discription: 库存地点信息获取
     * @param params
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: zhanglei on 2021/7/8 10:20
     */
    @Override
    public List<Map<String, Object>> listStorageLocations(Map<String, Object> params) {
        return lineUpDao.listStorageLocations(params);
    }
}
