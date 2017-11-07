package com.hoperun.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	
	public static List<Map<String,String>> list;
	

	// 读取文件夹下的所有文件名
	public static List<Map<String,String>> ReadFileName(String dirName) {		
		System.out.println("dirName: " + dirName);
		if (dirName == null) {
			return list;
		}
		dirName = dirName.replaceAll("\\\\", "/");
		
		File dir = new File(dirName);
		if (!dir.exists()) {
			return list;
		}
		File[] files = dir.listFiles();
		
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			if (file.isDirectory()) {
				ReadFileName(dirName+"\\"+file.getName());
				//System.out.println(file.getName() + " [目录]");
			} else {
				Map<String,String> map = new HashMap<String, String>();
				map.put("picPath", dirName);
				map.put("picName", file.getName());
				list.add(map);
			}

		}
		return list;

	}

	// 测试
	public static void main(String[] args) {
		String testFile = "F:/test/fj001001.png";
		//ReadFileName(testDir);
		File opusFile = new File(testFile);
		if(matchFileInject(opusFile)){
			System.out.println("true");
		}else{
			System.out.println("false");
		}


	}
	
	//测试是否为图片格式
	public static Boolean matchPictureFormat(File opusFile){
		Boolean flag = false;
		String [] regex = new String[]{"bmp","jpg","jpeg","png","tiff","gif","pcx","tga","exif",
				"fpx","svg","psd","cdr","pcd","dxf","ufo","eps","ai","raw","wmf"};
		if(opusFile == null){
			return flag;
		}
		String fileName = opusFile.getName();
		try {
			fileName = fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();
			if(fileName == null){
				return flag;
			}
			for(int i = 0; i < regex.length; i++){
				if(fileName.equals(regex[i])){
					flag = true;
					return flag;
				}
			}			
		} catch (Exception e) {
			return flag;
		}
		
		return flag;
	}

	//测试是否为文件注入
	public static Boolean matchFileInject(File opusFile){
		Boolean flag = false;
		if(opusFile == null){
			return flag;
		}
		String fileName = opusFile.getName();
		try {
			if(fileName == null){
				return flag;
			}
			String [] strs = fileName.split("\\.");
			int sums = strs.length;
			if(sums == 2){
				flag = true;
				return flag;
			}				
		} catch (Exception e) {
			return flag;
		}		
		return flag;
	}
}
