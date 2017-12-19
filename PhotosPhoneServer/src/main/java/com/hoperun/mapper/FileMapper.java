package com.hoperun.mapper;

import java.util.Map;

public interface FileMapper{
	
	 /**
	   * 根据attId查询附件信息
	   * @return
	   */
	 public Map<String, Object> selectPicAtt(Map<String, String> params);
	 
	 /**
	   * 往附件表中插入数据
	   * @return
	   */
	 public void inserPic(Map<String, String> params);
	 
	 /**
	   * 查询本地磁盘导入数据记录表
	   * @return
	   */
	 public Map<String, Object> selectDiskRecord(Map<String, String> params);
	 
	 /**
	   * 往本地磁盘导入数据记录表中插入数据
	   * @return
	   */
	 public void insertDiskRecord(Map<String, String> params);
	 
	  /**
	   * 查询上传图片记录表
	   * @return
	   */
	 public Map<String, Object> selectUploadRecord(Map<String, String> params);
	 
	 /**
	   * 往上传记录表中插入数据
	   * @return
	   */
	 public void insertUploadRecord(Map<String, String> params);

}
