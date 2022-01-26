package com.zkzn.les.basicInfo.pojo;

import com.zkzn.les.basicInfo.util.PageCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;


@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ClientManage extends PageCondition {
    private static final long serialVersionUID = 1L;

    private Integer clientManageId;//客户管理主键id
    private String clientName;//客户名称
    private String warehouseCode;//仓库编号
    private String warehouseName;//仓库名称
    private String categoryName;//货品名称
    private Double contractPrice;//合同单价
    private String usageArea;//使用面积
    private String contractPeriod;//合同期限
    private String paymentCycle;//付费周期
    private Date createTime;//创建时间
    private Integer operateUserId;//操作人id
    private String operateUserName;//操作人名称
    private Integer createUserId;//创建人id
    private String createUserName;//创建人名称
    private Date updateTime;//修改时间
    private Integer updateUserId;//修改人id
    private String updateUserName;//修改人名称

}
