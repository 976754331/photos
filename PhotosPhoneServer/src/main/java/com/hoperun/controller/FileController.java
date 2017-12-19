package com.hoperun.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jodd.util.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoperun.exception.ServiceException;
import com.hoperun.model.vo.ReturnInfo;
import com.hoperun.service.IFileService;
import com.hoperun.util.Img2Base64Util;
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
			return errRtn;
		}
		return rtn;
	}
	
	/**
	 * 上传图片
	 * @param req
	 * @param imgUrl  base64字节
	 * @param imgPath  手机保存图片路径
	 * @return
	 */
	@RequestMapping(value="/uploadPic")
	@ResponseBody
	public ReturnInfo uploadPic(HttpServletRequest req, String imgUrl, String imgPath, String typeId){
		String userId = UserUtil.getUserId(req);
		String path = PropsUtil.get("imgUrl");  //服务器图片存储路径
		ReturnInfo rtn = new ReturnInfo();
		ReturnInfo errRtn = new ReturnInfo();
		// 判断图片不为空
		if (!StringUtil.isEmpty(imgUrl)) {
			// 上传图片		
			File folder = new File(PropsUtil.get("imgUrl")+userId+"/");
			if(!folder.isDirectory()&&!folder.mkdirs()){
				errRtn.setRtnCode(888);
				errRtn.setMsg("img upload error");
				return errRtn;
			}
			String FileImgUrl = PropsUtil.get("imgUrl") +userId+"/"+System.currentTimeMillis()
					+ imgPath.substring(imgPath.lastIndexOf("."), imgPath.length());
			File target = new File(FileImgUrl);
			
			//将img的base64转为服务器文件
			boolean imgUpdateReturn = Img2Base64Util.generateImage(imgUrl.split(",")[1], FileImgUrl);
			if (!imgUpdateReturn) {
				errRtn.setRtnCode(888);
				errRtn.setMsg("img upload error");
				return errRtn;
			}			
			try {
				fileService.uploadPic(userId, FileImgUrl, typeId, imgPath);
				rtn.setData(0);
			} catch (Exception e) {	
				if(e instanceof RuntimeException){
					errRtn.setRtnCode(888);
					errRtn.setMsg(e.getMessage());
					return errRtn;
				}
				errRtn.setRtnCode(888);
				errRtn.setMsg("sql异常，执行操作失败");
				return errRtn;
			}
		}else{
			errRtn.setRtnCode(888);
			errRtn.setMsg("imgUrl为空，执行操作失败");
			return errRtn;
		}
		return rtn;
	}
	
}
