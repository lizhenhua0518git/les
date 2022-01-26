package com.zkzn.les.oms.service.impl;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.common.util.page.PageUtil;
import com.zkzn.les.common.util.response.Ecode;
import com.zkzn.les.common.util.response.Result;
import com.zkzn.les.oms.dao.PurchaseBillDetailDao;
import com.zkzn.les.oms.pojo.*;
import com.zkzn.les.oms.pojo.vo.PurchaseBillVO;
import com.zkzn.les.oms.service.PurchaseBillDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@SuppressWarnings("unchecked")
@Service
public class PurchaseBillDetailServiceImpl implements PurchaseBillDetailService {

	@Autowired
	private PurchaseBillDetailDao purchaseBillDetailDao;
	@Autowired
	private RedisTemplate redisTemplate;

	private final Logger logger = LoggerFactory.getLogger(getClass());


	/**
	 * @param purchaseBillDetail
	 * @return
	 * @Author:luozhihong
	 * @date:2020年9月27日
	 * @Description:分页查询采购入库订单明细
	 */
	public PageInfo<PurchaseBillDetail> listPurchaseBillDetail(PurchaseBillDetail purchaseBillDetail){

		PageUtil.setPageParam(purchaseBillDetail);
		reAssembleParam(purchaseBillDetail);
		List<PurchaseBillDetail> list = purchaseBillDetailDao.listPurchaseBillDetail(purchaseBillDetail);
		PageInfo<PurchaseBillDetail> pageInfo = new PageInfo<PurchaseBillDetail>(list);
		return pageInfo;
	}
	/**
	 * @param purchaseBillRefund
	 * @return
	 * @Author:luozhihong
	 * @date:2020年12月7日
	 * @Description:分页查询退货采购入库订单明细
	 */
	public PageInfo<PurchaseBillRefund> listPurchaseBillDetailTH(PurchaseBillRefund purchaseBillRefund){

		PageUtil.setPageParam(purchaseBillRefund);
		List<PurchaseBillRefund> list = purchaseBillDetailDao.listPurchaseBillDetailTH(purchaseBillRefund);
		PageInfo<PurchaseBillRefund> pageInfo = new PageInfo<PurchaseBillRefund>(list);
		return pageInfo;
	}
	/**
	 * @param purchaseBillDetail
	 * @Author:luozhihong
	 * @date:2020年9月27日
	 * @Description:参数封装
	 */
	public void reAssembleParam(PurchaseBillDetail purchaseBillDetail) {


		String requiredTimeStr = purchaseBillDetail.getRequiredTimeStr();//创建时间
		if (!"".equals(requiredTimeStr) && requiredTimeStr != null){
			String[] split = requiredTimeStr.split(" - ");
			purchaseBillDetail.setStartRequiredTime(split[0]);
			purchaseBillDetail.setEndRequiredTime(split[1]);
		}
	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	public String saveOrder(PurchaseBillVO data) throws Exception {


		try {
//			//送货单初始化,点收单初始化
//			ArrivalNoticePojo arrivalNoticePojo = new ArrivalNoticePojo();
//			ReceiveRecord receiveRecord = new ReceiveRecord();
//			CommonUtils.purchaseBillToMainBean(data, arrivalNoticePojo, receiveRecord);
//			//logger.info(JSON.toJSONString(arrivalNoticePojo));
//			String seqNo = RedisNoUtil.getSeqNo(RedisNoUtil.SHD_ORDER_KEY, redisTemplate);
//			arrivalNoticePojo.setArrivalCode(seqNo);
//			this.purchaseBillDetailDao.saveArrMain(arrivalNoticePojo);
//			//logger.info(JSON.toJSONString(receiveRecord));
//			receiveRecord.setBillCode(seqNo);
//			this.purchaseBillDetailDao.saveRecMain(receiveRecord);
//
//			List<ArrivalNoticeDetailPojo> arrList = new ArrayList<>();
//			List<ReceiveDetailRecord> recList = new ArrayList<>();
//			CommonUtils.purchaseBillDetailToDetailBeanList(data.getDetailList(), arrList, recList, arrivalNoticePojo, receiveRecord);
//			//logger.info(JSON.toJSONString(arrList));
//			//logger.info(JSON.toJSONString(recList));
//
//			this.purchaseBillDetailDao.saveArrDetailList(arrList);
//
//			List<ReceiveDetailRecord> newRecList = new ArrayList<>();
//			if (recList.size()>0){
//				for (int i = 0; i < recList.size(); i++) {
//					ReceiveDetailRecord receiveDetailRecord = recList.get(i);
//					receiveDetailRecord.setReceivedTaskCode(RedisNoUtil.getSeqNo(RedisNoUtil.CGDS_ORDER_KEY,redisTemplate));//任务号
//					receiveDetailRecord.setSapItemNo(i+1);//调用SAP行项目号从1开始
//					newRecList.add(receiveDetailRecord);
//				}
//			}
//			this.purchaseBillDetailDao.saveRecDetailList(newRecList);
//
//			//采购单点收数量信息更新
//			this.purchaseBillDetailDao.updateBatchDetails(data.getDetailList());
//
			return Result.toJson(Ecode.SUCCESS, "操作成功!");
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw new Exception(e);
		}
	}
}
