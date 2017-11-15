package com.hoperun.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoperun.exception.ServiceException;
import com.hoperun.mapper.TypeMapper;
import com.hoperun.service.ITypeService;

@Transactional
@Service
public class TypeServiceImpl  implements ITypeService{

	@Autowired
	private TypeMapper typeMapper;
	
	/**
	   * 查询一级分类
	   * @return
	   */
	 public List<Map<String, Object>> selectFirstType(String userId) throws ServiceException{
		 Map<String, String> params = new HashMap<String, String>();
		 params.put("userId", userId);
		 return typeMapper.selectFirstType(params);
	 }
	  
	 /**
	   * 查询二级分类
	   * @return
	   */
	 public List<Map<String, Object>> selectSecondType(Map<String, String> params) throws ServiceException{
		 return typeMapper.selectSecondType(params);
	 }
	
	 /**
	   * 创建一级分类
	   * @return
	   */
	 public Map<String, String> insertFirstTypes(Map<String, String> params) throws ServiceException{
		 String typeId = UUID.randomUUID().toString().replaceAll("-", "");
		 params.put("typeId", typeId);
		 typeMapper.insertFirstTypes(params);
		 return params;
		 
	 }
	 
	 /**
	   * 创建二级分类
	   * @return
	   */
	public Map<String, String> insertSecondTypes(Map<String, String> params) throws ServiceException{
		String typeId = UUID.randomUUID().toString().replaceAll("-", "");
		params.put("typeId", typeId);
		typeMapper.insertDiskType(params);
		return params;
	}

	
}
