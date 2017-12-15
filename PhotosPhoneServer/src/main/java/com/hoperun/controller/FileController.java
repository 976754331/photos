package com.hoperun.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoperun.exception.ServiceException;
import com.hoperun.model.vo.ReturnInfo;
import com.hoperun.service.IFileService;
import com.hoperun.util.PropsUtil;
import com.hoperun.util.UserUtil;

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
	public void downPic(HttpServletResponse rsp,HttpServletRequest req, String attId){
		this.fileService.down(rsp, attId);
	}
	
	/**
	 * 往数据库中写入本地文件的数据
	 * String testDir = "F:/test";
	 * @throws Exception 
	 */
	@RequestMapping(value="/insertPic")
	@ResponseBody
	public ReturnInfo insertPic(HttpServletRequest req, String dirPath, String userId){
		ReturnInfo rtn = new ReturnInfo();
		try {
			Map<String,String> rtnMap = fileService.insertPic(dirPath, userId);
			rtn.setData(rtnMap);
		} catch (Exception e) {
			ReturnInfo errRtn = new ReturnInfo();
			errRtn.setRtnCode(888);
			errRtn.setMsg("sql异常，执行操作失败");
		}
		return rtn;
	}
	
	/**
	 * 上传图片
	 */
	@RequestMapping(value="/uploadPic")
	@ResponseBody
	public ReturnInfo uploadPic(HttpServletRequest req, String dirPath){
		String userId = UserUtil.getUserId(req);
		String path = PropsUtil.get("imgUrl");
	
		return null;
	}
	
}
