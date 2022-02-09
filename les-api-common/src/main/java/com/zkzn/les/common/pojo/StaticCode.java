/*
 * 文 件 名:  StaticCode.java
 * 版    权:  
 * 描    述:  描述:
 * 修 改 人:  liusongshan 
 * 修改时间:  2018年8月23日
 * 跟踪单号:  跟踪单号:
 * 修改单号:  修改单号:
 * 修改内容:  修改内容:
 */
package com.zkzn.les.common.pojo;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

/**
 * . 功能描述:常用参数配置类 
 * 时间: 2018年8月23日
 * 
 * @author liusongshan
 * 
 */
public class StaticCode {
	//总装立库的库存地点
	public static String gawStorageLocation = "Z101";
	//型材立库的库存地点
	public static String pshStorageLocation = "X102,X103";
	//板材立库的库存地点
	public static String plateStorageLocation = "B104";
    //型材立库的库存地点
	public static List<String>  PSHSTORAGELOCATION = Arrays.asList("X102","X103");
    //板材立库的库存地点
	public static List<String>  PLATESTORAGELOCATION= Arrays.asList("B104");
}
