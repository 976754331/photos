package com.hoperun.service;

import java.util.List;
import java.util.Map;

import com.hoperun.exception.ServiceException;


public interface ITypeService {
	
	/**
	   * 查询一级分类
	   * @return
	   */
	 public List<Map<String, Object>> selectFirstType() throws ServiceException;
	  
	 /**
	   * 查询二级分类
	   * @return
	   */
	 public List<Map<String, Object>> selectSecondType(Map<String, String> params) throws ServiceException;
	
}

