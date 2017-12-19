/** 
 * @ProjectName:  TestCordova 
 * @FileName:  	  FileMineTypeUtils.java 
 * @PackageName:  com.hoperun.tj.utils 
 * @Date:         2016年4月13日下午3:54:07 
 * @Copyright:    Copyright (c) 2016, Loong All Rights Reserved. 
 * 
 */  
package com.hr.platform.update;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.cordova.CordovaInterface;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Base64;

/** 
 * @ClassName:  FileMineTypeHelper <br/>
 * 
 * @Description  把图片压缩，并保存到目录
 *
 * @date:       2016年4月13日 下午3:54:07 <br/> 
 * @author      Loong 
 * @version      
 * @since       JDK 1.6 
 */
public class ImageCompassHelper {
	/**
	 * 
	 * @Description 我们先看下质量压缩方法
	 *
	 * @param image
	 * @param bitmapSize 需要把图片压缩到多大 单位为kb
	 * @return  
	 *
	 * @author Loong 
	 * @since JDK 1.6
	 */
	public static Bitmap imageCompressQuantity(Bitmap image,int bitmapSize,int comquantity) {

		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			
			image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
			if((baos.size()/1024/bitmapSize)>10){
				image.compress(Bitmap.CompressFormat.JPEG, 20, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
			}
			int options = 100;
			while ( baos.toByteArray().length / 1024>bitmapSize) {	//循环判断如果压缩后图片是否大于100kb,大于继续压缩		
				baos.reset();//重置baos即清空baos
				image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
				options -= 10;//每次都减少10
				if(options<=5){
					options=5;
				}
			}
			ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
			BitmapFactory.Options options1 = new BitmapFactory.Options();
			//options1.inJustDecodeBounds = true;
		   // Bitmap	bitmap = BitmapFactory.decodeStream(isBm, null, options1);
			options1.inJustDecodeBounds = false;
			options1.inPreferredConfig = Config.RGB_565;
			Bitmap bitmap1 = BitmapFactory.decodeStream(isBm, null, options1);//把ByteArrayInputStream数据生成图片
			isBm.close();
			baos.close();
			return bitmap1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 图片按比例大小压缩方法（根据路径获取图片并压缩）
	 * @Description TODO
	 *
	 * @param srcPath
	 * @return  
	 *
	 * @author Loong 
	 * @since JDK 1.6
	 */
	public static Bitmap imageScale(CordovaInterface cordovaInterface,String srcPath,float hh,float ww,int bitmapSize,int comquantity) {
		try {
			BitmapFactory.Options newOpts = new BitmapFactory.Options();
			//开始读入图片，此时把options.inJustDecodeBounds 设回true了
			newOpts.inJustDecodeBounds = true;
			Bitmap bitmap = BitmapFactory.decodeFile(srcPath,newOpts);//此时返回bm为空
			
			newOpts.inJustDecodeBounds = false;
			int w = newOpts.outWidth;
			int h = newOpts.outHeight;
			//现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
			//float hh = 800f;//这里设置高度为800f
			//float ww = 480f;//这里设置宽度为480f
			//缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
			int be = 1;//be=1表示不缩放
			if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
				be = (int) (newOpts.outWidth / ww);
			} else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
				be = (int) (newOpts.outHeight / hh);
			}
			if (be <= 0)
				be = 1;
			newOpts.inSampleSize = be;//设置缩放比例
			//重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
			bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
			saveBitmapInSdcard( cordovaInterface,comquantity, bitmap);
			return bitmap;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 
	 * @Description 把bitmap保存到sdcard 
	 *
	 * @param comquantity 0~100
	 * @param mBitmap  
	 *
	 * @author Loong 
	 * @since JDK 1.6
	 */
	public static String saveBitmapInSdcard(CordovaInterface cordovaInterface,int comquantity,Bitmap mBitmap){
		  File f = new File(getExternalSdCardPath(cordovaInterface.getActivity())+getRandomFileNameByTime() + ".png");
		  try {
			  if(!f.getParentFile().exists()){
				  f.getParentFile().mkdirs(); 
			  }
		   f.createNewFile();
		  } catch (IOException e) {
		  e.printStackTrace();
		  }
		  FileOutputStream fOut = null;
		  try {
		   fOut = new FileOutputStream(f);
		  } catch (FileNotFoundException e) {
		   e.printStackTrace();
		  }
		  mBitmap.compress(Bitmap.CompressFormat.PNG, comquantity, fOut);
		  try {
		   fOut.flush();
		  } catch (IOException e) {
		   e.printStackTrace();
		  }
		  try {
		   fOut.close();
		  } catch (IOException e) {
		   e.printStackTrace();
		  }
		  return f.getAbsolutePath();
		 }

    /**
     * 获取扩展SD卡存储目录
     * 
     * 如果有外接的SD卡，并且已挂载，则返回这个外置SD卡目录
     * 否则：返回内置SD卡目录
     * 
     * @return
     */
    public static String getExternalSdCardPath(Context context) {
    	        File cache = null;

    	        // SD Card Mounted
    	        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
    	            cache = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +
    	                    "/Android/data/" + context.getPackageName() + "/cache/");
    	        }
    	        // Use internal storage
    	        else {
    	            cache =context.getCacheDir();
    	        }

    	        // Create the cache directory if it doesn't exist
    	        cache.mkdirs();
    	        return cache.getAbsolutePath();
    }
    /**
     * 
     * @Description 得到一个文件名
     *
     * @return  
     *
     * @author Loong 
     * @since JDK 1.6
     */
    public static String getRandomFileNameByTime(){
    	  String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
    	  return timeStamp;
    }
    /**
     * 
     * @Description 根据图片路径得到bitmap对象
     *
     * @param srcPath
     * @return
     * @throws Exception  
     *
     * @author Loong 
     * @since JDK 1.6
     */
    public static Bitmap getBitmapByPath(String srcPath) throws Exception{
    	BitmapFactory.Options newOpts = new BitmapFactory.Options();
		//开始读入图片，此时把options.inJustDecodeBounds 设回true了
		newOpts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeFile(srcPath,newOpts);//此时返回bm为空
		newOpts.inJustDecodeBounds = false;
		bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
		return bitmap;
    }
    
    public static String bitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        if(bitmap!=null&&bitmap.isRecycled()){
        	bitmap.recycle();
        }
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }
}
