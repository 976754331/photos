/** 
 * @ProjectName:  MainActivity 
 * @FileName:  	  ScreenSize.java 
 * @PackageName:  com.bdtj.statics 
 * @Date:         2016年5月30日下午4:01:44 
 * @Copyright:    Copyright (c) 2016, Loong All Rights Reserved. 
 * 
 */
package com.hr.platform.statics;

/**
 * @ClassName: ScreenSize <br/>
 * 
 * @Description TODO
 *
 * @date: 2016年5月30日 下午4:01:44 <br/>
 * @author Loong
 * @version
 * @since JDK 1.6
 */
public class ScreenSize {
	private int screenHeight;
	private int screenWidth;

	/**
	 * Creates a new instance of ScreenSize.
	 * 
	 * @Description TODO
	 * 
	 * 
	 * @author Loong
	 * @since JDK 1.6
	 */
	public ScreenSize(int screenHeight, int screenWidth) {
		// TODO Auto-generated constructor stub
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
	}

	/**
	 * screenHeight.
	 * 
	 * @return the screenHeight
	 * @author Loong
	 * @since JDK 1.6
	 */
	public int getScreenHeight() {
		return screenHeight;
	}

	/**
	 * screenHeight.
	 * 
	 * @param screenHeight
	 *            the screenHeight to set
	 * @return the screenHeight
	 * @author Loong
	 * @since JDK 1.6
	 */
	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}

	/**
	 * screenWidth.
	 * 
	 * @return the screenWidth
	 * @author Loong
	 * @since JDK 1.6
	 */
	public int getScreenWidth() {
		return screenWidth;
	}

	/**
	 * screenWidth.
	 * 
	 * @param screenWidth
	 *            the screenWidth to set
	 * @return the screenWidth
	 * @author Loong
	 * @since JDK 1.6
	 */
	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}

}
