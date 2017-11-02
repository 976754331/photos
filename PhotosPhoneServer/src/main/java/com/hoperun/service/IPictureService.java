package com.hoperun.service;

import java.util.List;
import java.util.Map;

import com.hoperun.exception.ServiceException;


public interface IPictureService {
	
	/**
	   * 根据二级类型获取图片列表
	   * @return
	   */
	 public List<Map<String, Object>> selectPicLists(Map<String, String> params) throws ServiceException;
	
}

