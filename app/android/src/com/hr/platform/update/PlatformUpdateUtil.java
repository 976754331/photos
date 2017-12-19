/** 
 * @ProjectName:  MainActivity 
 * @FileName:  	  UpdateUtil.java 
 * @PackageName:  com.bdtj.update 
 * @Date:         2016年5月13日下午3:47:08 
 * @Copyright:    Copyright (c) 2016, Loong All Rights Reserved. 
 * 
 */
package com.hr.platform.update;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.hr.platform.application.HRApplication;
import com.hr.platform.util.Util;
import com.http.okhttp.builder.PostFormBuilder;
import com.http.okhttp.callback.StringCallback;
import com.lidroid.xutils.http.HttpHandler;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * @ClassName: UpdateUtil <br/>
 * 
 * @Description TODO
 *
 * @date: 2016年5月13日 下午3:47:08 <br/>
 * @author Loong
 * @version
 * @since JDK 1.6
 */
public class PlatformUpdateUtil {
	public Context context;
	public ProgressDialog dialog = null;
	private static HashMap<String, String> httpHandlerMap;
	HttpHandler handler;
	private static PlatformUpdateUtil updateUtil;

	/**
	 * updateUtil.
	 * 
	 * @return the updateUtil
	 * @author Loong
	 * @since JDK 1.6
	 */
	/**
	 * httpHandlerMap.
	 * 
	 * @return the httpHandlerMap
	 * @author Loong
	 * @since JDK 1.6
	 */
	public HashMap<String, String> getHttpHandlerMap() {
		return httpHandlerMap;
	}

	/**
	 * Creates a new instance of UpdateUtil.
	 * 
	 * @Description TODO
	 * 
	 * 
	 * @author Loong
	 * @since JDK 1.6
	 */
	public PlatformUpdateUtil() {
		// TODO Auto-generated constructor stub
		httpHandlerMap = new HashMap<String, String>();
	}

	public static PlatformUpdateUtil getInstance(Context context) {
		if (updateUtil == null) {
			updateUtil = new PlatformUpdateUtil();
			updateUtil.setContext(context);
		}
		return updateUtil;
	}

	/**
	 * context.
	 * 
	 * @param context
	 *            the context to set
	 * @return the context
	 * @author Loong
	 * @since JDK 1.6
	 */
	public void setContext(Context context) {
		this.context = context;
	}

	public void checkUpdate(String url) {
		if (context == null) {
			return;
		}

		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("bic", 901 + "");
			jsonObject.put("versionType", 1 + "");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Q", jsonObject.toString());

		PostFormBuilder requestPost = HRApplication.getInstance().getNetWorkRequestUtil().requestPost(context,url);

		requestPost.addParams("Q", jsonObject.toString()).url("").build()
				.execute(new StringCallback() {

					@Override
					public void onResponse(String response) {
						// TODO Auto-generated method stub
						if (response != null && response.contains("000000")) {
							String versionName = Util.getVersion(context).versionName;
							JSONObject jsonObject = null;
							try {
								jsonObject = new JSONObject(response);
								JSONObject jsondata = jsonObject.getJSONObject("data").getJSONArray("records")
										.getJSONObject(0);
								String downloadUrl = jsondata.optString("FILE_URL");
								String version_code = jsondata.optString("VERSION_CODE");
								String update_time = jsondata.optString("UPLOAD_TIME");
								String update_content = jsondata.optString("UPDATE_CONTENT", "暂无说明");
								boolean isForce = jsondata.optString("isForce", "1").equals("1");
								isForce = false;
								if (!response.contains(versionName)) {
									// 如果正在下载，则返回
									if (isDownloadTaskRuning(version_code)) {
										return;
									}
									showDialog(context,update_content, isForce, downloadUrl, version_code);
								}
							} catch (Exception e) {
								// TODO: handle exception
								e.printStackTrace();
							}

						}
					}

					@Override
					public void onError(okhttp3.Request request, Exception e) {
						// TODO Auto-generated method stub
						// System.out.println(e.getMessage());
					}
				});

	}

	public static void showDialog(final Context context,String message, final boolean isForce, final String url, final String versionCode) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("新版本" + versionCode);
		builder.setMessage("【软件特性】\n" + message).setCancelable(false).setPositiveButton("确定",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						if (isForce) {
							PlatformForceDownload.getInstance(context).downloadAPK(url,versionCode);
						} else {
							DownloadBySystem.getInstance(context).download(url,versionCode);
						}
					}
				});

		if (!isForce) {
			builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
				}
			});
		}
		AlertDialog alert = builder.create();
		alert.show();
	}

	/**
	 * 
	 * @Description TODO 判断下载线程是否在运行
	 *
	 * @param url
	 * @return
	 *
	 * @author Loong
	 * @since JDK 1.6
	 */
	public static boolean isDownloadTaskRuning(String url) {
		String handler = httpHandlerMap.get(url);
		if (handler == null) {
			return false;
		} else {
			return true;

		}

	}

}
