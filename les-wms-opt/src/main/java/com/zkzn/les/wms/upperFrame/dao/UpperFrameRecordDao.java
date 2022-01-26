package com.zkzn.les.wms.upperFrame.dao;

import com.zkzn.les.wms.upperFrame.pojo.UpperFrameRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**.
 * @author luozhihong
 * @date 2020年9月29日
 * @Description 上架记录Dao
 */
@Mapper
public interface UpperFrameRecordDao {
    int deleteByPrimaryKey(String id);

    int insert(UpperFrameRecord record);

    UpperFrameRecord selectByPrimaryKey(String id);

    List<UpperFrameRecord> selectAll();

    int updateByPrimaryKey(UpperFrameRecord record);

    /**
     * @param upperFrameRecord
     * @return
     * @Author:luozhihong
     * @date:2020年9月29日
     * @Description:查询上架记录
     */
    List<UpperFrameRecord> listUpperFrameRecord(@Param("upperFrameRecord") UpperFrameRecord upperFrameRecord);
}
