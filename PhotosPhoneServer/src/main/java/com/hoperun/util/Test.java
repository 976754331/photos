package com.hoperun.util;

public class Test {
	
	public static void main(String[] args) {
		
//		String chiIds="23";
//		chiIds = chiIds.substring(1,chiIds.lastIndexOf("'"));
//		System.out.println(chiIds);
		String dirName = "F:/test";
		dirName = dirName.replaceAll("\\\\", "/");
		System.out.println(dirName);
	}

}
