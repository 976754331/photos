package com.hoperun.mapper;

import java.util.List;
import java.util.Map;

public interface TypeMapper{

	  /**
	   * 查询一级分类
	   * @return
	   */
	 public List<Map<String, Object>> selectFirstType();
	  
	 /**
	   * 查询二级分类
	   * @return
	   */
	 public List<Map<String, Object>> selectSecondType(Map<String, String> params);
}
