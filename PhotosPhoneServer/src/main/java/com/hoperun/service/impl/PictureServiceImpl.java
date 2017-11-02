package com.hoperun.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoperun.exception.ServiceException;
import com.hoperun.mapper.PictureMapper;
import com.hoperun.service.IPictureService;

@Transactional
@Service
public class PictureServiceImpl  implements IPictureService{

	@Autowired
	private PictureMapper pictureMapper;
	
	/**
	   * 根据二级类型获取图片列表
	   * @return
	   */
	 public List<Map<String, Object>> selectPicLists(Map<String, String> params) throws ServiceException{
		 return pictureMapper.selectPicLists(params);
	 }
	

	
}
