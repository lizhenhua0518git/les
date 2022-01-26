package com.zkzn.les.common.pojo.post;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassTitle: ProductionPosting
 * @ProjectName les
 * @Description: 生产订单领、退、补料过账item实体类
 * @Author Sangsang
 * @Date 2020/11/17
 * @Time 11:15
 */
@Data
@Accessors(chain = true)
public class ProductionPosting {


	@JSONField(name = "LFBNR")
	private String LFBNR;//参考凭证 不用填写

	@JSONField(name = "ZH01")
	private String ZH01;//"备用字段 不用填写"

	@JSONField(name = "MENGE")
	private String MENGE;//"物料数量"

	@JSONField(name = "BUDAT")
	private String BUDAT;//"凭证中的凭证日期"

	/*
	 * "业务类型 ：11-计划内领料12-计划外领料 13-内部订单领料 21-计划内退料 22-计划外退料23-内部订单退料31-生产入库32-生产入库冲销"
	 * */
	@JSONField(name = "ZTYPE")
	private String ZTYPE;//业务类型

	/*
		12计划外领料	Z51	0001	车间损失
		生产领退补料接口业务类型12计划外领料	Z51	0002	材料不合格
		生产领退补料接口业务类型12计划外领料	Z51	0003	设计变更
		生产领退补料接口业务类型12计划外领料	Z51	0004	BOM有误（工艺变更）
		生产领退补料接口业务类型12计划外领料	Z51	0006	车间丢失
		生产领退补料接口业务类型12计划外领料	Z51	0007	营销（客户变更）
		生产领退补料接口业务类型12计划外领料	Z51	0008	售后（整改）
		采购收、退货接口业务类型22不合格退料	124	0001	质量低劣
		采购收、退货接口业务类型22不合格退料	124	0002	未完成的
		采购收、退货接口业务类型22不合格退料	124	0003	损坏的
		调拨接口业务类型22	343	0001	设计变更
		调拨接口业务类型22	343	0002	工艺变更
		调拨接口业务类型22	343	0003	试制车剩余
		调拨接口业务类型22	343	0004	改制车剩余
		调拨接口业务类型22	343	0005	营销订单改变
		调拨接口业务类型22	343	0006	原旅游车物料
		调拨接口业务类型22	343	0007	三一老物料
		调拨接口业务类型22	343	0008	采购变更
		调拨接口业务类型22	343	0009	多采购物料
		调拨接口业务类型21	344	0001	设计变更
		调拨接口业务类型21	344	0002	工艺变更
		调拨接口业务类型21	344	0003	试制车剩余
		调拨接口业务类型21	344	0004	改制车剩余
		调拨接口业务类型21	344	0005	营销订单改变
		调拨接口业务类型21	344	0006	原旅游车物料
		调拨接口业务类型21	344	0007	三一老物料
		调拨接口业务类型21	344	0008	采购变更
		调拨接口业务类型21	344	0009	多采购物料
	* */
	@JSONField(name = "GRUND")
	private String GRUND;//生产领退补料接口业务类型

	@JSONField(name = "SGTXT")
	private String SGTXT;//"备注 不用填写"

	@JSONField(name = "ZH03")
	private String ZH03;//备用字段 不用填写

	@JSONField(name = "ZI03")
	private String ZI03;//备用字段 不用填写

	@JSONField(name = "ZH02")
	private String ZH02;//备用字段 不用填写

	@JSONField(name = "KDAUF")
	private String KDAUF;//销售订单 特殊库存为E，则必输

	@JSONField(name = "ZI04")
	private String ZI04;//备用字段 不用填写

	@JSONField(name = "BWTAR")
	private String BWTAR;//评估类型 不用填写

	@JSONField(name = "BATCH")
	private String BATCH;//批次 不用填写

	@JSONField(name = "ZI01")
	private String ZI01;//备用字段 不用填写

	@JSONField(name = "ZI02")
	private String ZI02;//备用字段 不用填写

	@JSONField(name = "ZI05")
	private String ZI05;//备用字段 不用填写

	@JSONField(name = "MATNR")
	private String MATNR;//物料编号

	@JSONField(name = "KUNNR")
	private String KUNNR;//客户 特殊库存为B，则必输

	@JSONField(name = "KOSTL")
	private String KOSTL;//成本中心编码

	@JSONField(name = "BKTXT")
	private String BKTXT;//凭证抬头文本 不用填写

	@JSONField(name = "ZEILE")
	private String ZEILE;//行项目号

	/*
	 * E-订单库存O-外协库存K-供应商寄售库存B-客户库存 业务类型为32-K、42-O、52-E必填
	 * */
	@JSONField(name = "SOBKZ")
	private String SOBKZ;//特殊库存

	@JSONField(name = "LGORT")
	private String LGORT;// 库存地点编码

	@JSONField(name = "BLDAT")
	private String BLDAT;//凭证中的过帐日期

	@JSONField(name = "LFPOS")
	private String LFPOS;//参考凭证项目 填写 0000

	@JSONField(name = "MEINS")
	private String MEINS;//物料单位


	@JSONField(name = "AUFNR")
	private String AUFNR;//生产订单号

	@JSONField(name = "RSNUM")
	private String RSNUM;// 预留 业务类型11、13必输

	@JSONField(name = "ZSYS")
	private String ZSYS;//来源系统 必须填写 LES

	@JSONField(name = "RSPOS")
	private String RSPOS;//预留项目 业务类型11、13必输

	@JSONField(name = "KDPOS")
	private String KDPOS;//销售订单项目 特殊库存为E，则必输

	@JSONField(name = "LIFNR")
	private String LIFNR;//供应商 特殊库存为O/K，则必输

	@JSONField(name = "ZDONR")
	private String ZDONR;//来源单据号

	@JSONField(name = "WERKS")
	private String WERKS;//来源单据号

	/*
	{
    "IT_INPUT":[
        {
            "item":{
                "AUFNR":"O222201",
                "BATCH":"",
                "BKTXT":"",
                "BLDAT":"2020-11-17",
                "BUDAT":"2020-11-17",
                "BWTAR":"",
                "GRUND":"",
                "KDAUF":"",
                "KDPOS":"",
                "KOSTL":"",
                "KUNNR":"",
                "LFBNR":"",
                "LFPOS":"",
                "LGORT":"ZXK-DSQ-A001",
                "LIFNR":"",
                "MATNR":"M000000004",
                "MEINS":"PC",
                "MENGE":"5.0",
                "RSNUM":"",
                "RSPOS":"",
                "SGTXT":"",
                "SOBKZ":"",
                "ZDONR":"",
                "ZEILE":"1",
                "ZH01":"",
                "ZH02":"",
                "ZH03":"",
                "ZI01":"",
                "ZI02":"",
                "ZI03":"",
                "ZI04":"",
                "ZI05":"",
                "ZSYS":"LES",
                "ZTYPE":"21"
            }
        },
        {
            "item":{
                "AUFNR":"O222201",
                "BATCH":"",
                "BKTXT":"",
                "BLDAT":"2020-11-17",
                "BUDAT":"2020-11-17",
                "BWTAR":"",
                "GRUND":"",
                "KDAUF":"",
                "KDPOS":"",
                "KOSTL":"",
                "KUNNR":"",
                "LFBNR":"",
                "LFPOS":"",
                "LGORT":"ZXK-DSQ-A002",
                "LIFNR":"",
                "MATNR":"M000000012",
                "MEINS":"个",
                "MENGE":"5.0",
                "RSNUM":"",
                "RSPOS":"",
                "SGTXT":"",
                "SOBKZ":"",
                "ZDONR":"",
                "ZEILE":"2",
                "ZH01":"",
                "ZH02":"",
                "ZH03":"",
                "ZI01":"",
                "ZI02":"",
                "ZI03":"",
                "ZI04":"",
                "ZI05":"",
                "ZSYS":"LES",
                "ZTYPE":"21"
            }
        }
    ]
}

	* */


}
