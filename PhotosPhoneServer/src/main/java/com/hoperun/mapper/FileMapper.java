package com.hoperun.mapper;

import java.util.Map;

public interface FileMapper{
	
	 /**
	   * 根据attId查询附件信息
	   * @return
	   */
	 public Map<String, Object> selectPicAtt(Map<String, String> params);
}
