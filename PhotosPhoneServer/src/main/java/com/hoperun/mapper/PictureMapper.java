package com.hoperun.mapper;

import java.util.List;
import java.util.Map;

public interface PictureMapper{
	
	 /**
	   * 根据二级类型获取图片列表
	   * @return
	   */
	 public List<Map<String, Object>> selectPicLists(Map<String, String> params);
	 
	  /**
	   * 从本地读入图片并插入到图片表中
	   * @return
	   */
	 public void insertPicFromDisk(Map<String, String> params);
}
