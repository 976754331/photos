package com.hoperun.service;

import java.util.List;
import java.util.Map;

import com.hoperun.exception.ServiceException;


public interface ITypeService {
	
	/**
	   * 查询一级分类
	 * @param userId 
	   * @return
	   */
	 public List<Map<String, Object>> selectFirstType(String userId) throws ServiceException;
	  
	 /**
	   * 查询二级分类
	   * @return
	   */
	 public List<Map<String, String>> selectSecondType(Map<String, String> params) throws ServiceException;
	
	 /**
	   * 创建一级分类
	   * @return
	   */
	 public Map<String, String> insertFirstTypes(Map<String, String> params) throws ServiceException;

	 /**
	   * 创建二级分类
	   * @return
	   */
	public Map<String, String> insertSecondTypes(Map<String, String> params) throws ServiceException;

	/**
	 * 删除二级分类
	 * @param params
	 * @return
	 */
	public void deleteSecondTypes(Map<String, String> params) throws ServiceException;

	/**
	 * 删除一级分类
	 * @param params
	 * @return
	 */
	public void deleteFirstTypes(Map<String, String> params) throws ServiceException;
	 
}

