package com.zkzn.les.oms.pojo;

import java.util.Date;
import java.util.List;

import com.zkzn.les.common.util.page.PageCondition;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * .
 *
 * @author luozhihong
 * @date 2020年9月5日
 * @Description 出库任务派发列表字段封装类
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class StationOrderWithDetail extends PageCondition {

    /**
     *
     */
    private static final long serialVersionUID = -4742209465953069668L;

    @ApiModelProperty(name = "id", value = "主键id")
    private String id;
    @ApiModelProperty(name = "orderCode", value = "订单号")
    private String orderCode;
    @ApiModelProperty(name = "requiredDate", value = "需求日期")
    private String requiredTimeStr;//创建生产时间 查询条件
    private String startRequiredTime;//开始生产时间
    private String endRequiredTime;//结束生产时间
    private Date requiredDate;
    @ApiModelProperty(name = "workshopCode", value = "车间")
    private String workshopCode;
    private String workShopName;
    @ApiModelProperty(name = "lineCode", value = "产线")
    private String lineCode;
    private String lineName;
    @ApiModelProperty(name = "stationCode", value = "工位号")
    private String stationCode;
    private String stationName;
    @ApiModelProperty(name = "warehouseName", value = "仓库名称")
    private String warehouseName;
    @ApiModelProperty(name = "reserveCode", value = "预留号")
    private String reserveCode;
    @ApiModelProperty(name = "reserveRow", value = "预留行")
    private String reserveRow;
    @ApiModelProperty(name = "materialRow", value = "组件行项号")
    private String materialRow;
    @ApiModelProperty(name = "materialCode", value = "组件物料号")
    private String materialCode;
    private String materialDesc;
    @ApiModelProperty(name = "positionCode", value = "仓位号")
    private String positionCode;
    @ApiModelProperty(name = "pickNum", value = "拣配数量")
    private Double pickNum;
    @ApiModelProperty(name = "status", value = "派发状态")
    private Integer status;
    @ApiModelProperty(name = "specialName", value = "特殊库存名称")
    private String specialName;
    @ApiModelProperty(name = "specialType", value = "特殊库存标记")
    private String specialType;
    @ApiModelProperty(name = "stationOrderId", value = "工位订单id")
    private String stationOrderId;
    @ApiModelProperty(name = "workshopCodes", value = "车间集合 列表查询用")
    private List<String> workshopCodes;
    @ApiModelProperty(name = "stationCodes", value = "工位号集合 列表查询用")
    private List<String> stationCodes;

    @ApiModelProperty(name = "lineCodes", value = "产线集合 列表查询用")
    private List<String> lineCodes;
    @ApiModelProperty(name = "storageLocation", value = "仓库编码 列表查询用")
    private String storageLocation;
    @ApiModelProperty(name = "storageLocations", value = "仓库编码集合 列表查询用")
    private List<String> storageLocations;
    @ApiModelProperty(name = "customDesc", value = "订单描述")
    private String customDesc;
    @ApiModelProperty(name = "statusStr", value = "派发状态")
    private String statusStr;
    @ApiModelProperty(name = "busiType",value = "业务类型")
    private Integer busiType;
    @ApiModelProperty(name = "busiTypeStr",value = "业务类型")
    private String busiTypeStr;
    @ApiModelProperty(name = "processDetailId",value = "生产订单详情表id")
    private String processDetailId;
    @ApiModelProperty(name = "isOffShelf",value = "是否下架")
    private String isOffShelf;
    @ApiModelProperty(name = "busiTypes",value = "类型集合")
    private List<String> busiTypes;
    private String busiTypeCodes;
    @ApiModelProperty(name = "materialStorageId",value = "库存id")
    private String materialStorageId;
    @ApiModelProperty(name = "warehouseCode",value = "仓库编码")
    private String warehouseCode;
    private String taskcode;

    public String getBusiTypeCodes() {
        return busiTypeCodes;
    }

    public void setBusiTypeCodes(String busiTypeCodes) {
        this.busiTypeCodes = busiTypeCodes;
    }

    public List<String> getBusiTypes() {
        return busiTypes;
    }

    public void setBusiTypes(List<String> busiTypes) {
        this.busiTypes = busiTypes;
    }

    public String getIsOffShelf() {
        switch (status) {
            case 10:
                isOffShelf = "已下架";
                break;
            case 15:
                isOffShelf = "已下架";
                break;
            case 20:
                isOffShelf = "已下架";
                break;
            case 25:
                isOffShelf = "已下架";
                break;
            default:
                isOffShelf = "未下架";
                break;
        }
        return isOffShelf;
    }

    public void setIsOffShelf(String isOffShelf) {
        this.isOffShelf = isOffShelf;
    }

    //	状态 0-待派发 5-待拣配 10-待出库 15-待送达 20-待交接 25-交接完成
    public String getStatusStr() {
        switch (status) {
            case 0:
                statusStr = "待派发";
                break;
//			case 5:
//				statusStr = "待拣配";
//				break;
//			case 10:
//				statusStr = "待出库";
//				break;
//			case 15:
//				statusStr = "待送达";
//				break;
			case 20:
				statusStr = "待交接";
				break;
			case 25:
				statusStr = "已交接";
				break;
            default:
                statusStr = "已派发";
                break;
        }
        return statusStr;
    }

    public String getBusiTypeStr() {
        if(busiType != null) {
            switch(busiType){
                case 1:
                    busiTypeStr = "计划拉动";
                    break;
                case 2:
                    busiTypeStr = "缺料呼叫";
                    break;
                case 3:
                    busiTypeStr = "补料(计划外)";
                    break;
                case 4:
                    busiTypeStr = "退补料";
                    break;
                default:
                    busiTypeStr = "";
                    break;
            }
        }else{
            busiTypeStr = "";
        }

        return busiTypeStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }
}
