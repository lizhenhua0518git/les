package com.zkzn.les.basicInfo.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.pojo.LowerRule;

public interface LowerRuleService {

	/**.
	 * 
	 * 创建人: wangzhou
	 * 时间:2020年5月22日下午1:03:44
	 * List<LowerRule>
	 * @param lowerRule
	 * @return
	 * 功能描述:下架规则列表
	 */
	List<LowerRule> listLowerRule(LowerRule lowerRule);
	
	/**.
	 * 
	 * 创建人: wangzhou
	 * 时间:2020年5月22日下午1:10:10
	 * PageInfo<LowerRule>
	 * @param lowerRule
	 * @return
	 * 功能描述:下架规则分页查询
	 */
	PageInfo<LowerRule> listLowerRulePage(LowerRule lowerRule);
	
	/**.
	 * 
	 * 创建人: wangzhou
	 * 时间:2020年5月22日下午1:04:31
	 * int
	 * @param lowerRule
	 * @return
	 * 功能描述:保存下架规则
	 */
	int saveLowerRule(LowerRule lowerRule);
	
	/**.
	 * 
	 * 创建人: wangzhou
	 * 时间:2020年5月22日下午1:05:06
	 * int
	 * @param lowerRule
	 * @return
	 * 功能描述:更新下架规则
	 */
	int updateLowerRule(LowerRule lowerRule);
	/**.
	 * 
	 * 创建人: wangzhou
	 * 时间:2020年5月22日下午1:06:00
	 * int
	 * @param id
	 * @return
	 * 功能描述:删除下架规则
	 */
	int removeLowerRule(List<String> id);
}
