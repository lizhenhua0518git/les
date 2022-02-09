package com.zkzn.les.uas.util;

import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.Map;

public class Result {

	private Integer code;
	private String msg;
	private int count;
	private Object data;
	private Object other;
	public Result() {

	}
	/**.
	 * 支持原生对象返回
	 * @param code
	 * @param obj
	 */
	public Result(Ecode code, Object obj) {
		this.setCode(Integer.parseInt(code.code()));
		this.setMsg(code.msg());
		this.setData(obj);
	}
	
	/**.
	 * 结果集（对外强制使用这个）
	 * 
	 * @param code
	 *            错误码
	 * @param obj
	 *            对象
	 * @return
	 */
	public static String toJson(Ecode code, Object obj) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", Integer.parseInt(code.code()));
		map.put("msg", code.msg());
		map.put("data", obj);
		if(obj instanceof PageInfo) {
			map.put("count", ((PageInfo) obj).getTotal());
			map.put("data", ((PageInfo) obj).getList());
		}
		return JsonUtil.toJson(map);
	}
	/**.
	 * 
	 * 功能描述：app端接口 结果集使用
	 * 作者：wangzhou
	 * 时间：2018年9月29日
	 * @param code
	 * @param obj
	 * @return
	 */
	public static String toJsonUseApp(Ecode code, Object obj){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", Integer.parseInt(code.code()));
		if(Integer.parseInt(code.code())==-1){
			map.put("msg",obj);
		}else{
			map.put("msg", code.msg());
			map.put("data", obj);
		}
		return JsonUtil.toJson(map);
	}
	
	public static String toJsonOther(Ecode code, Object obj,Object other) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", Integer.parseInt(code.code()));
		map.put("msg", code.msg());
		map.put("data", obj);
		map.put("other", other);
		if(obj instanceof PageInfo) {
			map.put("count", ((PageInfo) obj).getTotal());
			map.put("data", ((PageInfo) obj).getList());
		}
		return JsonUtil.toJson(map);
	}
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public Object getData() {
		return data;
	}

	public void setData(Object obj) {
		this.data = obj;
	}
	public Object getOther() {
		return other;
	}
	public void setOther(Object other) {
		this.other = other;
	}
}
