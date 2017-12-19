package com.hoperun.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public interface IFileService {
	
	public void down(HttpServletResponse rsp,String attId) ;

	public Map<String,String> insertPic(String dirPath, String userId) throws Exception;

	public void uploadPic(String userId, String fileImgUrl, String typeId, String imgPath) throws Exception;
	
}
