package com.hoperun.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoperun.mapper.FileMapper;
import com.hoperun.mapper.PictureMapper;
import com.hoperun.mapper.TypeMapper;
import com.hoperun.service.IFileService;
import com.hoperun.util.FileUtil;

/**
 * 文件下载
 * 
 * @author Hoperun
 *
 */
@Service
@Transactional
public class FileService implements IFileService {

	@Resource
	private PictureMapper pictureMapper;

	@Resource
	private FileMapper fileMapper;

	@Resource
	private TypeMapper typeMapper;

	// 暂定本地文件的一级分类id为5
	private String DISK_TYPE_ID = "5";

	/**
	 * 根据附件表的主键id加载附件图片
	 */
	@Override
	public void down(HttpServletResponse rsp, String attId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("attId", attId);
		// 查询附件表的信息
		Map<String, Object> map = fileMapper.selectPicAtt(params);
		File file = new File(map.get("pic_path").toString()
				+ map.get("pic_name").toString());
		if (!file.isFile()) {
			return;
		}
		InputStream is = null;
		OutputStream os = null;
		rsp.reset();
		rsp.setHeader("Content-Disposition",
				"attachment;filename=" + file.getName());

		try {
			is = new FileInputStream(file);
			os = rsp.getOutputStream();
			byte[] buf = new byte[1024 << 4];
			int len = is.read(buf);
			while (len != -1) {
				os.write(buf, 0, len);
				len = is.read(buf);
			}
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				os.close();
			} catch (IOException e) {
			}
		}

	}

	/**
	 * 往数据库中写入本地文件的数据
	 */
	@Override
	public Map<String, String> insertPic(String dirPath, String userId) throws Exception {
		Map<String, String> rtnMap = new HashMap<String, String>();
		if (dirPath == null) {
			rtnMap.put("msg", "输入的路径为空");
			return rtnMap;
		}
		dirPath = dirPath.replaceAll("\\\\", "/");
		//查询用户插入本地磁盘数据记录表
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("filePath", dirPath);
		Map<String, Object> result = fileMapper.selectDiskRecord(params);
		if(result.get("count(1)") != null && Integer.parseInt(result.get("count(1)").toString()) == 1){ //已插入则不让操作并提示上次操作时间
			rtnMap.put("msg", "你已导入此磁盘，上次导入时间为： "+ result.get("uplode_date"));
			return rtnMap;
		}else{//未插入调下面私有方法插入数据
			//#{recordId},#{userId},#{filePath},#{uplodeDate}
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String uplodeDate = df.format(new Date());
			String recordId = UUID.randomUUID().toString().replaceAll("-", "");
			params.put("uplodeDate", uplodeDate);
			params.put("recordId", recordId);
			fileMapper.insertDiskRecord(params);
			rtnMap = insertIntoPic(dirPath, userId);			
			return rtnMap;			
		}		
	}

	/**
	 * 往数据库中写入本地文件的数据 String testDir = "F:/test";
	 */
	private Map<String, String> insertIntoPic(String dirPath, String userId) throws Exception {
		// 首先读文件
		Map<String, String> rtnMap = new HashMap<String, String>();
		dirPath = dirPath.replaceAll("\\\\", "/");
		File dir = new File(dirPath);
		if (!dir.exists()) {
			rtnMap.put("msg", "输入的路径不存在");
			return rtnMap;
		}

		// 见到文件夹就创建新的分类类型
		Map<String, String> typeParams = new HashMap<String, String>();
		// {typeId},#{typeName},#{parentId}
		String typeId = UUID.randomUUID().toString().replaceAll("-", "");
		typeParams.put("typeId", typeId);
		typeParams.put("typeName", dir.getName());
		typeParams.put("parentId", DISK_TYPE_ID);
		typeParams.put("userId", userId);
		typeMapper.insertDiskType(typeParams);

		File[] files = dir.listFiles();

		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			if (file.isDirectory()) {
				// 文件夹递归遍历
				insertIntoPic(dirPath + "/" + file.getName(), userId);

			} else {
				String attId = UUID.randomUUID().toString().replaceAll("-", "");
				// 往附件表中插数据
				Map<String, String> params = new HashMap<String, String>();
				params.put("picPath", dirPath + "/");
				params.put("picName", file.getName());
				params.put("attId", attId);
				fileMapper.inserPic(params);
				// 往图片表中加数据
				Map<String, String> paramPic = new HashMap<String, String>();
				// values(#{picId},#{picName},#{attId},#{shootDate},#{uploadDate})
				String picId = UUID.randomUUID().toString().replaceAll("-", "");
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Long time = file.lastModified();
				String shootDate = df.format(new Date(time));
				String uploadDate = df.format(new Date());
				paramPic.put("picId", picId);
				paramPic.put("picName", file.getName());
				paramPic.put("attId", attId);
				paramPic.put("shootDate", shootDate);
				paramPic.put("uploadDate", uploadDate);
				paramPic.put("typeId", typeId);
				pictureMapper.insertPicFromDisk(paramPic);

				// 往分类表中插入数据
				typeMapper.insertTypesFromDisk(paramPic);

			}

		}
		rtnMap.put("msg", "ok");
		return rtnMap;
	}

	/**
	 * 上传图片
	 * userId  用户id
	 * fileImgUrl  上传文件名称+路径
	 * typeId  二级类型id
	 * imgPath  手机保存图片路径
	 */
	public void uploadPic(String userId, String fileImgUrl, String typeId, String imgPath)throws Exception{
		//先判断是否上传过，上传过则不再上传
		Map<String, String> paramPic = new HashMap<String, String>();
		paramPic.put("imgPath", imgPath);
		paramPic.put("userId", userId);
		paramPic.put("typeId", typeId);
		Map<String, Object> result = fileMapper.selectUploadRecord(paramPic);
		if(result.get("count") != null && Integer.parseInt(result.get("count").toString()) == 1){ //已插入则不让操作并提示上次操作时间
			throw new RuntimeException("你已上传此图片，上传时间为： "+ result.get("uplode_date"));
		}
		
		String dirPath = fileImgUrl.substring(0, fileImgUrl.lastIndexOf("/")+1);
		String fileName = fileImgUrl.substring(fileImgUrl.lastIndexOf("/")+1, fileImgUrl.length());
		
		String attId = UUID.randomUUID().toString().replaceAll("-", "");
		// 往附件表中插数据
		Map<String, String> params = new HashMap<String, String>();
		params.put("picPath", dirPath);
		params.put("picName", fileName);
		params.put("attId", attId);
		fileMapper.inserPic(params);
		// 往图片表中加数据
		
		// values(#{picId},#{picName},#{attId},#{shootDate},#{uploadDate})
		String picId = UUID.randomUUID().toString().replaceAll("-", "");
		String recordId = UUID.randomUUID().toString().replaceAll("-", "");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		File target = new File(fileImgUrl);
		Long time = target.lastModified();
		String shootDate = df.format(new Date(time));
		String uploadDate = df.format(new Date());
		paramPic.put("picId", picId);
		paramPic.put("picName", fileName);
		paramPic.put("attId", attId);
		paramPic.put("shootDate", shootDate);
		paramPic.put("uploadDate", uploadDate);		
		paramPic.put("recordId", recordId);
		
		pictureMapper.insertPicFromDisk(paramPic);

		//未上传则加入一条上传记录
		fileMapper.insertUploadRecord(paramPic);
		
		// 往分类表中插入数据
		typeMapper.insertTypesFromDisk(paramPic);
	}
}
