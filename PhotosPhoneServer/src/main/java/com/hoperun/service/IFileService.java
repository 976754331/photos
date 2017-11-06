package com.hoperun.service;

import javax.servlet.http.HttpServletResponse;

public interface IFileService {
	
	public void down(HttpServletResponse rsp,String attId) ;

	public void insertPic(String dirPath) throws Exception;
	
}
