package com.hoperun.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoperun.model.vo.ReturnInfo;
import com.hoperun.service.IFileService;

/**
 * 上传下载图片
 * @author Wang_wei
 *
 */
@Controller
@RequestMapping("/file")
public class FileController{
	
	@Autowired
	private IFileService fileService;
	
	/**
	 * 下载图片
	 * 根据图片表的主键id加载图片
	 * /rest/fileServer/view/123456
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "downPic/{picId}")
	@ResponseBody
	public ReturnInfo downPic(HttpServletResponse rsp, HttpServletRequest request, @PathVariable String picId) {
		ReturnInfo rtn = new ReturnInfo();
		fileService.downPic(rsp, picId);
		rtn.setData("success");
		return rtn;
	}

}
