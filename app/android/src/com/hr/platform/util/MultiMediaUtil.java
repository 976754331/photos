/** 
 * @ProjectName:  MainActivity 
 * @FileName:  	  Multimedia.java 
 * @PackageName:  com.bdtj.statics 
 * @Date:         2016年5月18日下午4:19:29 
 * @Copyright:    Copyright (c) 2016, Loong All Rights Reserved. 
 * 
 */  
package com.hr.platform.util;

import java.io.ByteArrayOutputStream;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.util.Base64;

/** 
 * @ClassName:  Multimedia <br/>
 * 
 * @Description  TODO
 *
 * @date:       2016年5月18日 下午4:19:29 <br/> 
 * @author      Loong 
 * @version      
 * @since       JDK 1.6 
 */
public class MultiMediaUtil {
	@SuppressLint("NewApi")
	public Bitmap getVideoThumbnail(String filePath) {
		Bitmap bitmap = null;
		MediaMetadataRetriever retriever = new MediaMetadataRetriever();
		try {
			retriever.setDataSource(filePath);
			bitmap = retriever.getFrameAtTime();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			try {
				retriever.release();
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		}
		return bitmap;
	}
	
	/**
	 * 
	 * @Description 把bitmap转为base64
	 *
	 * @param bitmap
	 * @return
	 *
	 * @author Loong
	 * @since JDK 1.6
	 */
	public static String bitmapToBase64(Bitmap bitmap) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
		byte[] byteArray = byteArrayOutputStream.toByteArray();
		return Base64.encodeToString(byteArray, Base64.DEFAULT);
	}
}
