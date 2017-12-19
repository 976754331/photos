package com.hoperun.util;

public class Test {
	
	public static void main(String[] args) {
		
		String chiIds="F:/eclipse/test/photos/123/test.jpg";
		chiIds = chiIds.substring(chiIds.lastIndexOf("/")+1, chiIds.length());
		System.out.println(chiIds);
//		String dirName = "F:/test";
//		dirName = dirName.replaceAll("\\\\", "/");
//		System.out.println(dirName);
	}

}
