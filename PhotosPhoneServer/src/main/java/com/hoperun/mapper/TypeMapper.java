package com.hoperun.mapper;

import java.util.List;
import java.util.Map;

public interface TypeMapper{
		  
	  /**
	   * 删除分类-删除tb_pic_type表数据
	   * @return
	   */
	 public void deletePicType(Map<String, String> params);
	    
	  /**
	   * 删除分类-删除tb_types表数据
	   * @return
	   */
	 public void deleteTypes(Map<String, String> params);
	  
	 /**
	   * 删除分类-删除tb_pic表数据
	   * @return
	   */
	 public void deletePic(Map<String, String> params);
	  
	 /**
	   * 删除分类-删除tb_attachment表数据
	   * @return
	   */
	 public void deleteAttachment(Map<String, String> params);
	  
	 /**
	   * 删除分类-删除tb_comment表数据
	   * @return
	   */
	 public void deleteComment(Map<String, String> params);

	  /**
	   * 查询一级分类
	   * @return
	   */
	 public List<Map<String, Object>> selectFirstType(Map<String, String> params);
	  
	 /**
	   * 查询二级分类
	   * @return
	   */
	 public List<Map<String, String>> selectSecondType(Map<String, String> params);
	 
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
	 
	  /**
	   * 创建一级分类
	   * @return
	   */
	 public void insertFirstTypes(Map<String, String> params);
}
