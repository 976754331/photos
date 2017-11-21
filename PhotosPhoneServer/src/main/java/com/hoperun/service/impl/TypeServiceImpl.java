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
	 public List<Map<String, String>> selectSecondType(Map<String, String> params) throws ServiceException{
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

	/**
	 * 删除二级分类
	 * @param params
	 * @return
	 */
	@Override
	public void deleteSecondTypes(Map<String, String> params) throws ServiceException {
		//删除二级分类
		deleteTypeByTypeId(params);		
	}

	/**
	 * 删除一级分类
	 * @param params
	 * @return
	 */
	@Override
	public void deleteFirstTypes(Map<String, String> params) throws ServiceException {
		//查询二级分类，然后遍历删除
		List<Map<String, String>> result = typeMapper.selectSecondType(params);
		//删除一级分类
		params.put("type_id", params.get("parentId").toString());
		typeMapper.deletePicType(params);
		if(result == null){
			return;
		}		
		for(Map<String, String> map: result){
			deleteTypeByTypeId(map);
		}		
	}
	
	/**
	 * 根据类型id删除类型及相关联的数据
	 * //删除分类表tb_pic_types,tb_types,tb_pic,tb_comment.tb_attachment
	 * @param typeId
	 */
	private void deleteTypeByTypeId (Map<String, String> params){
		typeMapper.deleteAttachment(params);
		typeMapper.deleteComment(params);
		typeMapper.deletePic(params);
		typeMapper.deletePicType(params);
		typeMapper.deleteTypes(params);
	}

	
}
