/** 
 * @ProjectName:  MainActivity 
 * @FileName:  	  BDTJException.java 
 * @PackageName:  com.bdtj.application 
 * @Date:         2016年4月22日下午1:01:51 
 * @Copyright:    Copyright (c) 2016, Loong All Rights Reserved. 
 * 
 */
package com.hr.platform.exception;

/**
 * @ClassName: BDTJException <br/>
 * 
 * @Description TODO
 *
 * @date: 2016年4月22日 下午1:01:51 <br/>
 * @author Loong
 * @version
 * @since JDK 1.6
 */
public class HRException extends Exception {

	/**
	 * 
	 * @Description TODO
	 *
	 * @author Loong
	 * @since JDK 1.6
	 */
	private static final long serialVersionUID = 1903999074253941464L;

	/**
	 * Creates a new instance of BDTJException.
	 * 
	 * @Description TODO
	 * 
	 * 
	 * @author Loong
	 * @since JDK 1.6
	 */
	public HRException(String message, Throwable cause) {
		// TODO Auto-generated constructor stub
		super(message, cause);
	}
	/** 
	 * Creates a new instance of BDTJException. 
	 * 
	 * @Description  TODO
	 * 
	 * 
	 * @author Loong 
	 * @since JDK 1.6 
	 */
	public HRException(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}
}
