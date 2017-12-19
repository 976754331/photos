/** 
 * @ProjectName:  MainActivity 
 * @FileName:  	  UpdateUtil.java 
 * @PackageName:  com.bdtj.update 
 * @Date:         2016年5月13日下午3:47:08 
 * @Copyright:    Copyright (c) 2016, Loong All Rights Reserved. 
 * 
 */
package com.hr.platform.update.app;

import java.io.File;
import java.util.HashMap;

import org.apache.cordova.CordovaActivity;

import com.hr.platform.application.HRApplication;
import com.hr.platform.db.AppModel;
import com.hr.platform.util.Util;
import com.hr.platform.util.ZipUtil;
import com.http.okhttp.callback.FileCallBack;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.util.LogUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;
import okhttp3.Request;

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
public class AppForceDownload {
	public Context context;
	public ProgressDialog dialog = null;
	HttpHandler handler;
	private static AppForceDownload forceDownload;
	private static HashMap<String, String> httpHandlerMap;

	public static HashMap<String, String> getHttpHandlerMap() {
		return httpHandlerMap;
	}

	public static AppForceDownload getInstance(Context context) {
		if (forceDownload == null) {
			forceDownload = new AppForceDownload();
			forceDownload.setContext(context);
			forceDownload.initProgressDailog();
		}
		return forceDownload;
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

	public void initProgressDailog() {
		((Activity) context).runOnUiThread(new Runnable() {

			@Override
			public void run() {
				dialog = new ProgressDialog(context);
				dialog.setCancelable(false);
				// dialog.setTitle("正在下载");

				// dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				dialog.setMessage("下载进度:0.00%");
			}
		});
		
	}

	public void downloadApp(final AppModel appModel, final String destFileDir, final String fileName) {
		HRApplication.getInstance().getNetWorkRequestUtil().requestGet(context,appModel.getApp_download_url()).url(appModel.getApp_download_url()).build()
				.execute(new FileCallBack(destFileDir, fileName) {
					@Override
					public void onResponse(File response) {
						// TODO Auto-generated method stub
						((Activity) context).runOnUiThread(new Runnable() {

							@Override
							public void run() {
								dialog.dismiss();
							}
						});

						try {
							
							
							
							AppModel appModel1=appModel;
							String localPath = ZipUtil.unzip(destFileDir + fileName, destFileDir);
							LogUtils.d("localPath==" + localPath);
							appModel1.setLocal_zip_path(Util.getPreFixPath()+destFileDir + fileName);
							appModel1.setLocal_unzip_path(Util.getPreFixPath()+localPath +File.separator);
							
							
							AppModel appModelLocal = HRApplication.getInstance().getDb().findFirst(
									Selector.from(AppModel.class).where(AppModel.COLUMN_APP_ID, "=", appModel1.getApp_id()));
							
							if (null == appModelLocal) {
								HRApplication.getInstance().getDb().save(appModel1);
							} else {
								HRApplication.getInstance().getDb().update(appModel1,
										WhereBuilder.b(AppModel.COLUMN_APP_ID, "=", appModel1.getApp_id()),

										AppModel.COLUMN_APP_DOWNLOAD_URL, AppModel.COLUMN_APP_NAME,
										AppModel.COLUMN_APP_VERSION_CODE, AppModel.COLUMN_APP_VERSION_NAME,
										AppModel.COLUMN_LOCAL_UNZIP_PATH, AppModel.COLUMN_LOCAL_ZIP_PATH);
							}
							
							
//							HRApplication.getInstance().getDb().update(appModel1,
//									WhereBuilder.b(AppModel.COLUMN_APP_ID, "=", appModel1.getApp_id()),
//
//									AppModel.COLUMN_LOCAL_UNZIP_PATH, AppModel.COLUMN_LOCAL_ZIP_PATH);

							Util.loadPath((CordovaActivity)context, appModel1, Util.DEBUG_MODE);
							
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

					@Override
					public void onError(Request request, Exception e) {
						// TODO Auto-generated method stub
						Toast.makeText(context, "访问报错了", Toast.LENGTH_SHORT).show();
						((Activity) context).runOnUiThread(new Runnable() {

							@Override
							public void run() {
								dialog.dismiss();
							}
						});
					}

					@Override
					public void inProgress(final float progress) {
						// TODO Auto-generated method stub
						dialog.setMessage("下载进度:" + Util.getDecimal(progress * 100, 2) + "%");
						((Activity) context).runOnUiThread(new Runnable() {

							@Override
							public void run() {
								dialog.setMessage("下载进度:" + Util.getDecimal(progress * 100, 2) + "%");
							}
						});
						// dialog.setMax((int)total);
						// dialog.setProgress((int)(current));

					}

					/**
					 * 
					 * @Description TODO
					 *
					 * @see com.zhy.http.okhttp.callback.Callback#onBefore(okhttp3.Request)
					 * 
					 * @author Loong
					 * @param request
					 * @since JDK 1.6
					 */
					@Override
					public void onBefore(Request request) {
						// TODO Auto-generated method stub
						super.onBefore(request);
						dialog.show();
					}
				});

	}

	/**
	 * 
	 * @Description 弹出退出对话框
	 *
	 * @param isForce
	 *            是否强制退出
	 *
	 * @author Loong
	 * @since JDK 1.6
	 */
	public void showExitDialog(boolean isForce) {
		if (context == null) {
			return;
		}
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("提示");
		builder.setMessage("您的账号已在其它设备登录!").setCancelable(false).setPositiveButton("确定",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						((Activity) context).finish();
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

}
