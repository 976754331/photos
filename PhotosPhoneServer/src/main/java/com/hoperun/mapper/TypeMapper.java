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
	 
	  /**
	   * 读取本地文件时插入新的文件夹分类
	   * @return
	   */
	 public void insertDiskType(Map<String, String> params);
	 
	 /**
	   * 读取本地文件时往分类与图片关联表中插入数据
	   * @return
	   */
	 public void insertTypesFromDisk(Map<String, String> params);
	 
}
