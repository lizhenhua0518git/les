package com.zkzn.les.basicInfo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.dao.DocumentTypeDao;
import com.zkzn.les.basicInfo.pojo.DocumentType;
import com.zkzn.les.basicInfo.service.DictItemsService;
import com.zkzn.les.basicInfo.service.DocumentTypeService;
import com.zkzn.les.basicInfo.util.PageUtil;
import com.zkzn.les.basicInfo.pojo.DictItems;

@Service
public class DocumentTypeServiceImpl implements DocumentTypeService{

	@Autowired
	private DocumentTypeDao documentTypeDao;
	@Autowired
	private DictItemsService dictItemsService;
	
	@Override
	public int saveDocumentType(DocumentType documentType) {
		// TODO Auto-generated method stub
		return documentTypeDao.saveDocumentType(documentType);
	}

	@Override
	public int updateDocumentType(DocumentType documentType) {
		// TODO Auto-generated method stub
		return documentTypeDao.updateDocumentType(documentType);
	}

	@Override
	public int deleteDocumentType(List<String> ids) {
		// TODO Auto-generated method stub
		return documentTypeDao.deleteDocumentType(ids);
	}

	@Override
	public List<DocumentType> listDocumentType(DocumentType documentType) {
		// TODO Auto-generated method stub
		List<DocumentType>  documentTypeList = documentTypeDao.listDocumentType(documentType);
		
		List<String> dicts = new ArrayList<String>();
		dicts.add("enable");//启用禁用状态
		dicts.add("business_type");//业务类型
		dicts.add("source_of");//数据来源
		List<DictItems> dictItems = dictItemsService.listDictItemsByType(dicts);
		
		if(documentTypeList!=null && dictItems!=null ){
			for(DocumentType tmepDocumentType:documentTypeList){
				for(DictItems dictItem:dictItems){
					if("enable".equals(dictItem.getDictTypeId()) && dictItem.getItemValue().equals(""+tmepDocumentType.getStatus()) ){
						tmepDocumentType.setStatusStr(dictItem.getItemName());
					}
					if("business_type".equals(dictItem.getDictTypeId()) && dictItem.getItemValue().equals(""+tmepDocumentType.getBusinessType()) ){
						tmepDocumentType.setBusinessTypeStr(dictItem.getItemName());
					}
					if("source_of".equals(dictItem.getDictTypeId()) && dictItem.getItemValue().equals(""+tmepDocumentType.getSourceOfDocument()) ){
						tmepDocumentType.setSourceOfDocumentStr(dictItem.getItemName());
					}
				}
			}
		}
		return documentTypeList;
				
				
	}

	@Override
	public PageInfo<DocumentType> listDocumentTypePage(DocumentType documentType) {
		// TODO Auto-generated method stub
		
		PageUtil.setPageParam(documentType);
		List<DocumentType> documentTypeList = listDocumentType(documentType);
		
		PageInfo<DocumentType>  pageInfo = new PageInfo<DocumentType>(documentTypeList);
		
		return pageInfo;
	}

}
