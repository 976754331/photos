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
	
	//暂定本地文件的一级分类id为5
	private String DISK_TYPE_ID = "5";
	
	/**
	 * 根据附件表的主键id加载附件图片
	 */
	@Override
	public void down(HttpServletResponse rsp,String attId){
		Map<String,String> params = new HashMap<String, String>();
		params.put("attId", attId);
		//查询附件表的信息
		Map<String,Object> map = fileMapper.selectPicAtt(params);
		File file = new File(map.get("pic_path").toString()+map.get("pic_name").toString()) ;
		if(!file.isFile()){
			return ;
		}
	    InputStream is = null;
		OutputStream os= null;
		rsp.reset();
		rsp.setHeader("Content-Disposition", "attachment;filename="+file.getName());  
		
		try {
			is = new FileInputStream(file);
			os= rsp.getOutputStream();
			byte[] buf = new byte[1024<<4];
			int len = is.read(buf);
			while(len!=-1){
				os.write(buf, 0, len);
				len=is.read(buf);
			}
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				is.close();
				os.close();
			} catch (IOException e) {
			}
		} 
		 
	}


	/**
	 * 往数据库中写入本地文件的数据
	 * String testDir = "F:/test";
	 */
	@Override
	public void insertPic(String dirPath) throws Exception{		
		//首先读文件
		if (dirPath == null) {
			return;
		}
		dirPath = dirPath.replaceAll("\\\\", "/");
		File dir = new File(dirPath);
		if (!dir.exists()) {
			return;
		}
		
		//见到文件夹就创建新的分类类型
		Map<String, String> typeParams = new HashMap<String, String>();
		//{typeId},#{typeName},#{parentId}
		String typeId = UUID.randomUUID().toString().replaceAll("-", "");
		typeParams.put("typeId", typeId);
		typeParams.put("typeName", dir.getName());
		typeParams.put("parentId", DISK_TYPE_ID);
		typeMapper.insertDiskType(typeParams);
		
		File[] files = dir.listFiles();
		
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			if (file.isDirectory()) {	 
				//文件夹递归遍历
				insertPic(dirPath+"/"+file.getName());				
				
			} else {
				String attId = UUID.randomUUID().toString().replaceAll("-", "");
				//往附件表中插数据
				Map<String, String> params = new HashMap<String, String>();
				params.put("picPath", dirPath+"/");
				params.put("picName", file.getName());
				params.put("attId", attId);
				fileMapper.inserPic(params);
				//往图片表中加数据
				Map<String, String> paramPic = new HashMap<String, String>();
				//values(#{picId},#{picName},#{attId},#{shootDate},#{uploadDate})
				String picId = UUID.randomUUID().toString().replaceAll("-", "");
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Long time =file.lastModified();
				String shootDate = df.format(new Date(time));
				String uploadDate = df.format(new Date());
				paramPic.put("picId", picId);
				paramPic.put("picName", file.getName());
				paramPic.put("attId", attId);
				paramPic.put("shootDate", shootDate);
				paramPic.put("uploadDate", uploadDate);
				paramPic.put("typeId", typeId);
				pictureMapper.insertPicFromDisk(paramPic);
				
				//往分类表中插入数据				
				typeMapper.insertTypesFromDisk(paramPic);
				
			}

		}
		
	}
	

}
