package com.hoperun.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hoperun.service.IFileService;

@Controller
@RequestMapping("/file")
public class FileController {
	
	@Autowired
	private IFileService fileService;
	
	/**
	 * 根据附件表的主键id加载附件图片
	 * http://127.0.0.1:8080/bookcity/file/downPic.do?attId=1
	 * @param rsp
	 * @param req
	 * @param attId
	 */
	@RequestMapping(value="/downPic")
	public void view(HttpServletResponse rsp,HttpServletRequest req, String attId){
		this.fileService.down(rsp, attId);
	}
	
}
