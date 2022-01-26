package com.zkzn.les.basicInfo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zkzn.les.basicInfo.pojo.DocumentType;

/**
 * 
 * @author wangzhou
 * @date 2020年7月28日
 * @Description:单据类型dao层
 */
@Mapper
public interface DocumentTypeDao {

	/**.
	 * 
	 * @param documentType
	 * @return
	 * @Author:wangzhou
	 * @date:2020年7月28日
	 * @Description:保存单据类型
	 */
	int saveDocumentType(DocumentType documentType);
	/**.
	 * 
	 * @param documentType
	 * @return
	 * @Author:wangzhou
	 * @date:2020年7月28日
	 * @Description:修改单据类型
	 */
	int updateDocumentType(DocumentType documentType);
	
	/**.
	 * 
	 * @param ids
	 * @return
	 * @Author:wangzhou
	 * @date:2020年7月28日
	 * @Description:删除单据类型
	 */
	int deleteDocumentType(List<String> ids);
	/**.
	 * 
	 * @param documentType
	 * @return
	 * @Author:wangzhou
	 * @date:2020年7月28日
	 * @Description:查询单据类型
	 */
	List<DocumentType> listDocumentType(DocumentType documentType);
}
