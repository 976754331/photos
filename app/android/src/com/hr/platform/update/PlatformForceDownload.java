/** 
 * @ProjectName:  MainActivity 
 * @FileName:  	  UpdateUtil.java 
 * @PackageName:  com.bdtj.update 
 * @Date:         2016年5月13日下午3:47:08 
 * @Copyright:    Copyright (c) 2016, Loong All Rights Reserved. 
 * 
 */
package com.hr.platform.update;

import java.io.File;

import com.hr.platform.application.HRApplication;
import com.hr.platform.util.Util;
import com.http.okhttp.callback.FileCallBack;
import com.lidroid.xutils.http.HttpHandler;

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
public class PlatformForceDownload {
	public Context context;
	public ProgressDialog dialog = null;
	HttpHandler handler;
	private static PlatformForceDownload forceDownload;

	public static PlatformForceDownload getInstance(Context context) {
		if (forceDownload == null) {
			forceDownload = new PlatformForceDownload();
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

	public void downloadAPK(final String url, String versionCode) {
		if (context == null) {
			return;
		}
		// url =
		// "http://ftp-apk.pconline.com.cn/e4cb03b03857a20b573f6414d1a44f12/pub/download/201010/appsearch_AndroidPhone_1002212s_v0510.apk";
		final String destFileDir = ImageCompassHelper.getExternalSdCardPath(context) + "" + "/qf/";
		final String fileName = Util.getProgramNameByPackageName(context, context.getPackageName())
				// + ImageCompassHelper.getRandomFileNameByTime() + ".apk";
				+ versionCode + ".apk";
		final File file = new File(destFileDir + fileName);
		if (file != null && file.exists()) {
			Util.installApk(context, destFileDir + fileName);
			return;
		}

		HRApplication.getInstance().getNetWorkRequestUtil().requestGet(context, url).url(url).build()
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
						Util.installApk(context, destFileDir + fileName);
					}

					@Override
					public void onError(Request request, Exception e) {
						// TODO Auto-generated method stub
						Toast.makeText(context, "访问报错了", Toast.LENGTH_SHORT).show();
						((Activity) context).runOnUiThread(new Runnable() {

							@Override
							public void run() {
								try {
									dialog.dismiss();
									if (file != null && file.exists()) {
										file.delete();
									}
								} catch (Exception e2) {
									// TODO: handle exception
								}

							}
						});
					}

					@Override
					public void inProgress(final float progress) {
						// TODO Auto-generated method stub
						((Activity) context).runOnUiThread(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								if (!dialog.isShowing() && !((Activity) context).isFinishing()) {
									dialog.show();
								}
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
						((Activity) context).runOnUiThread(new Runnable() {

							@Override
							public void run() {
								if ((!((Activity) context).isFinishing())) {
									dialog.show();
								}
							}
						});
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
