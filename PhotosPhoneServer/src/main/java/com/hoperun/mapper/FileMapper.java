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
}
