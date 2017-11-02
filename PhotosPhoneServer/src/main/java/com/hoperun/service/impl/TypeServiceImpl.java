package com.hoperun.service.impl;

import java.util.List;
import java.util.Map;

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
	 public List<Map<String, Object>> selectFirstType() throws ServiceException{
		 return typeMapper.selectFirstType();
	 }
	  
	 /**
	   * 查询二级分类
	   * @return
	   */
	 public List<Map<String, Object>> selectSecondType(Map<String, String> params) throws ServiceException{
		 return typeMapper.selectSecondType(params);
	 }
	

	
}
