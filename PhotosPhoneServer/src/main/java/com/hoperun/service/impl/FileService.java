package com.hoperun.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.hoperun.mapper.FileMapper;
import com.hoperun.service.IFileService;

/**
 * 文件下载
 * @author Hoperun
 *
 */
@Service
public class FileService implements IFileService {	
	
	@Resource
	private FileMapper attMapper;
	
	
	/**
	 * 根据附件表的主键id加载附件图片
	 */
	@Override
	public void down(HttpServletResponse rsp,String attId){
		Map<String,String> params = new HashMap<String, String>();
		params.put("attId", attId);
		//查询附件表的信息
		Map<String,Object> map = attMapper.selectPicAtt(params);
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
	

}
