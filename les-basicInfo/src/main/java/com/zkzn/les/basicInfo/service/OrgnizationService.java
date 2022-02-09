package com.zkzn.les.basicInfo.service;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.pojo.Orgnization;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;



/**.
 *
 * @author wangzhou
 *
 */
public interface OrgnizationService {


	/**.
	 *
	 * 功能描述：新增组织结构
	 * 作者：wangzhou
	 * 时间:2018年6月8日
	 * @param orgn
	 * @return
	 */
	int saveOrgnization(Orgnization orgn);
	/**.
	 *
	 * 功能描述：修改组织结构
	 * 作者：wangzhou
	 * 时间:2018年6月8日
	 * @param orgn
	 * @return
	 */
	int updateOrgnization(Orgnization orgn);
	/**.
	 *
	 * 功能描述：删除组织结构
	 * 作者：wangzhou
	 * 时间:2018年6月8日
	 * @param id
	 * @return
	 */
	int deleteOrgnization(List<String> id);

	/**.
	 *
	 * 功能描述：查询所有的组织结构
	 * 作者：wangzhou
	 * 时间:2018年6月8日
	 * @return
	 */
	List<Orgnization> listAll();

	/**.
	 *
	 * 功能描述：通过id查询信息
	 * 作者：wangzhou
	 * 时间:2018年6月7日
	 * @param id
	 * @return
	 */
	Orgnization getById(String id);


	List<Orgnization> listListByParam(Orgnization orgn);
	/**.
	 *
	 * 功能描述：列表过滤查询
	 * 作者：wangzhou
	 * 时间:2018年6月8日
	 * @param orgn
	 * @return
	 */
	PageInfo<Orgnization> listByParam(Orgnization orgn);

	/**.
	 *
	 * 功能描述：批量修改组织信息的状态
	 * 作者：wangzhou
	 * 时间：2018年7月2日
	 * @param id
	 * @param status
	 * @return
	 */
	int updateStateById(@Param("id")List<String> id,@Param("status")int status);
	/**.
	 *
	 * 功能描述：查询仓库名称及对应的编码
	 * 作者：wangzhou
	 * 时间：2018年10月17日
	 * @return
	 */
	List<Map<String,Object>> listWarehouse(String orgCode);
	/**.
	 *
	 * 功能描述：查询用户对应的组织类型
	 * 作者：wangzhou
	 * 时间：2018年10月17日
	 * @param id
	 * @return
	 */
	Map<String,Object> listUserOrgType(String id);
	/**.
	 * 通过用户id获取角色信息
	 * @param userid
	 * @return
	 */
	List<Map<String, Object>> getUserRole(String userid);
	/**.
	 *
	 * 功能描述：登录时默认存仓库
	 * 作者：wangzhou
	 * 时间：2018年11月6日
	 * @param currentUid
	 * @param type
	 */
	void defualtWarehouse(String currentUid,String type);

	/**.
	 *
	 * 创建人: wangzhou
	 * 时间:2020年6月3日下午2:47:57
	 * List<Map<String,Object>>
	 * @param userId
	 * @return
	 * 功能描述:通过用户查询对应仓库权限
	 */
	List<Map<String, Object>> listWarehouseByUserId(String userId);

	List<Map<String, Object>> initWarehouseSelect(Integer userId);


	List<Map<String, Object>> listWarehouseTwo(String orgCode);
}
