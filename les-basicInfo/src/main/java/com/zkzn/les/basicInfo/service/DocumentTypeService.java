package com.zkzn.les.basicInfo.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.pojo.DocumentType;

public interface DocumentTypeService {

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
	/**.
	 * 
	 * @param documentType
	 * @return
	 * @Author:wangzhou
	 * @date:2020年7月28日
	 * @Description:分页查询单据类型
	 */
	PageInfo<DocumentType> listDocumentTypePage(DocumentType documentType);
}
