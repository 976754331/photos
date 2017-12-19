/** 
 * @ProjectName:  MainActivity 
 * @FileName:  	  DeviceUtil.java 
 * @PackageName:  com.bdtj.statics 
 * @Date:         2016年5月18日下午4:11:55 
 * @Copyright:    Copyright (c) 2016, Loong All Rights Reserved. 
 * 
 */
package com.hr.platform.util;

import com.hr.platform.statics.Type;

import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;

/**
 * @ClassName: DeviceUtil <br/>
 * 
 * @Description TODO
 *
 * @date: 2016年5月18日 下午4:11:55 <br/>
 * @author Loong
 * @version
 * @since JDK 1.6
 */
public class DeviceUtil {
	/**
	 * 强制帮用户打开GPS
	 * 
	 * @param context
	 */
	public static final void openGPS(Context context) {
		Intent GPSIntent = new Intent();
		GPSIntent.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
		GPSIntent.addCategory("android.intent.category.ALTERNATIVE");
		GPSIntent.setData(Uri.parse("custom:3"));
		try {
			PendingIntent.getBroadcast(context, 0, GPSIntent, 0).send();
		} catch (CanceledException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 判断GPS是否开启，返回 GPS AGPS GPS_AGPS  NONE
	 * 
	 * @param context
	 * @return true 表示开启
	 */
	public static final String isOPen(final Context context) {
		LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		// 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
		boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		// 通过WLAN或移动网络(3G/2G)确定的位置（也称作AGPS，辅助GPS定位。主要用于在室内或遮盖物（建筑群或茂密的深林等）密集的地方定位）
		boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
		if (gps && network) {
			return Type.GPS_AGPS;
		}
		if (gps) {
			return Type.GPS;
		}
		if (network) {
			return Type.AGPS;
		}
		return Type.NONE;
	}
}
