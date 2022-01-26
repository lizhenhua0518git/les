package com.zkzn.les.common.pojo.panel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassTitle: InspectScreenModel
 * @ProjectName les
 * @Description:
 * @Author Sangsang
 * @Date 2020/10/20
 * @Time 11:04
 */
@Data
@NoArgsConstructor                 //无参构造
@AllArgsConstructor                //有参构造
public class InspectScreenModel implements Serializable {
	private String area;
	private String areaName;
	private List<InspectPanel> inspectPanel;

}
