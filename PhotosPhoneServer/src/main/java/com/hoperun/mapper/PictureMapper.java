package com.hoperun.mapper;

import java.util.List;
import java.util.Map;

public interface PictureMapper{
	
	 /**
	   * 根据二级类型获取图片列表
	   * @return
	   */
	 public List<Map<String, Object>> selectPicLists(Map<String, String> params);
}
