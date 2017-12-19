/** 
 * @ProjectName:  MainActivity 
 * @FileName:  	  Param.java 
 * @PackageName:  com.bdtj.statics 
 * @Date:         2016年5月23日下午5:04:06 
 * @Copyright:    Copyright (c) 2016, Loong All Rights Reserved. 
 * 
 */
package com.hr.platform.statics;

/**
 * @ClassName: Param <br/>
 * 
 * @Description TODO
 *
 * @date: 2016年5月23日 下午5:04:06 <br/>
 * @author Loong
 * @version
 * @since JDK 1.6
 */
public class Param {
	public Param(String key, String value) {
		// TODO Auto-generated constructor stub
		this.key = key;
		this.value = value;

	}

	private String key;
	private String value;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
