package com.hoperun.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		String testDir = "F:/test";
		//ReadFileName(testDir);

	}

}
