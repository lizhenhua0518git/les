package com.zkzn.les.basicInfo.appUpload.pojo;
import lombok.Data;
import lombok.ToString;
import java.util.Date;
import java.util.List;
/**
 * @ClassName AppPackage
 * @Description app升级包信息
 * @Author zhanglei
 * Date 2021/1/24 17:48
 * @Version 1.0
 **/
@Data
@ToString
public class AppPackage {
    /**
     * id
     */
    String id;

    /**
     * 版本名称
     */
    String versionName;

    /**
     * 版本号
     */
    String versionCode;

    /**
     * 升级描述
     */
    String remark;

    /**
     * 升级范围（已作废）
     */
    String updateScope;

    /** 创建者
     *
     */
    String createrName;

    /**
     * 创建时间
     */
    Date createTime;

    /**
     * 资源id
     */
    String mediaId;

    /**
     * 是否为平板 0 手持端  1 平板 默认手持端
     */
    int isPad = 0;

    /**
     * 创建者id
     */
    String createrId;

    /**
     * 文件路径
     */
    String fileUrl;

    /**
     * 更新范围
     */
    List<String> wareHouseId;

    String wareHouseStr;
}
