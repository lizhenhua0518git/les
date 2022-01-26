package com.zkzn.les.wms.pojo;


import com.zkzn.les.common.util.page.PageCondition;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName AppTransferSlipRequest.java
 * @Description 调拨单下架请求实体类
 * @createTime 2020年10月22日 09:30:00
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AppTransferSlipRequest  extends PageCondition {
    @ApiModelProperty(name = "status", value = "产品状态(0 合格  1不合格)")
    private String status;
}
