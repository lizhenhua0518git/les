package com.zkzn.les.basicInfo.appUpload.dao;
import com.zkzn.les.basicInfo.appUpload.pojo.AppPackage;
import com.zkzn.les.basicInfo.appUpload.pojo.MediaFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName 文件操作
 * @Author zhanglei
 * Date 2021/1/22 11:30
 * @Version 1.0
 **/
@Mapper
public interface MediaFileDao {

    /**
     * 根据md5查询文件信息
     */
    MediaFile findFileByMd5(String md5);

    void saveMediaFile(@Param("mediaFile") MediaFile mediaFile);

    /**
     * app升级包记录查询
     */
     List<Map<String,Object>> findAppPackages(@Param("requestMap") Map<String,Object> requestMap,@Param("wareHouseCode")String wareHouseCode);

    /**
     * app升级包删除ById
     * @param list
     */
    void delAppPackageInfo(List<String> list);

    void saveAppPackage(@Param("appPackage") AppPackage appPackage);

    void sevaAppPackageWareHouse(@Param("uuid") String uuid,  @Param("wareHouseList")List<String> wareHouseId);

    /***
     * app 端 app信息查询
     */
    Map<String, Object> findApp(@Param("isPad") int isPad, @Param("wareHouseCode") String wareHouseCode);
}
