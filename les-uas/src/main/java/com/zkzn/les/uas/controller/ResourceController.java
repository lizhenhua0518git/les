package com.zkzn.les.uas.controller;

import com.zkzn.les.uas.pojo.Resource;
import com.zkzn.les.uas.service.ResourceService;
import com.zkzn.les.uas.util.Ecode;
import com.zkzn.les.uas.util.Result;
import com.zkzn.les.uas.util.SecurityUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/resource")
public class ResourceController {

	@Autowired
	private ResourceService resourceService;

	/**.
	 *
	 * 创建人: wangzhou
	 * 时间:2020年4月8日下午2:06:33
	 * String
	 * @param request
	 * @param response
	 * @return
	 * 功能描述:获取用户菜单
	 */
	@RequestMapping(value="/getResoureList",produces="application/json; charset=UTF-8")
	@ResponseBody
	public String getResoureList(HttpServletRequest request, HttpServletResponse response){
		response.setCharacterEncoding("utf-8");

		List<Resource> resourceList = null;

		StringBuffer dataBuffer = new StringBuffer();
		dataBuffer.append("[");
		String userId = SecurityUserUtil.getCurrentUserId(request);
		String userName = SecurityUserUtil.getCurrentUserName(request);
		String type = "P";
		//if("admin".equals(userName)){//超级管理员，无视权限
			resourceList = resourceService.listResourceByType("1","1","P");
//		}else{
//			resourceList = resourceService.listResourceByUserId(userId, "1","1",type);
//		}
		//当前用户对应的模块资源
		List<Resource> blockResList = null;
		//if("admin".equals(userName)){//超级管理员，无视权限
			blockResList = resourceService.listResourceByType("0","1","P");
//		}else{
//			blockResList = resourceService.listResourceByUserId(userId,"0","1",type);
//		}
		if(blockResList!=null && !blockResList.isEmpty()){
			for(int i=0;i<blockResList.size();i++){
				if(i>0) {
                    dataBuffer.append(",");
                }
				dataBuffer.append("{\"id\":"+(i+1)+",\"title\":\""+blockResList.get(i).getResourceName()+"\""
						+ ",\"icon\":\""+blockResList.get(i).getResourceIcon()+"\"");
				insterDataBuffer(dataBuffer,resourceList,blockResList.get(i).getId());
				dataBuffer.append("}");
			}
		}
		String tempStr = dataBuffer.toString();
		tempStr = tempStr.replaceAll(",]", "]");//去除字符中多余的，
		StringBuffer resutBuffer = new StringBuffer(tempStr);
		resutBuffer.append("]");
		return Result.toJson(Ecode.SUCCESS, resutBuffer.toString());
	}

	/**.
	 *
	 * 创建人: wangzhou
	 * 时间:2020年4月8日下午2:06:02
	 * void
	 * @param buffer
	 * @param resourceList
	 * @param parentId
	 * 功能描述:递归拼接菜单
	 */
	public void insterDataBuffer(StringBuffer buffer,List<Resource> resourceList,String parentId){
		int tempNum = 0;
		for(int i=0;i<resourceList.size();i++){
			if(parentId.equals(resourceList.get(i).getResourceParentId())){
				if(tempNum==0){
					buffer.append(",\"children\":[");
					tempNum++;
				}
				buffer.append("{\"id\":\""+resourceList.get(i).getId()+"\",\"title\":\""+resourceList.get(i).getResourceName()+"\"")
						.append(",\"url\":\""+resourceList.get(i).getResourceUrl()+"\",\"icon\":\""+resourceList.get(i).getResourceIcon()+"\"");
				insterDataBuffer(buffer,resourceList,resourceList.get(i).getId());
				buffer.append("},");
			}
		}
		if(tempNum>0){
			buffer.append("]");
		}
	}

}
