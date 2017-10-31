package com.hoperun.service.impl;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoperun.service.IFileService;

@Transactional
@Service
public class FileServiceImpl  implements IFileService{

	@Override
	public void downPic(HttpServletResponse rsp, String picId) {
		//通过picId去查询路径和文件名
		
		
		//创建文件并以流形式输出到rsp中
		
	}

	
}
