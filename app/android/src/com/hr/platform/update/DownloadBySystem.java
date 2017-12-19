package com.hr.platform.update;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import com.hr.platform.util.PreferencesUtils;
import com.hr.platform.util.Util;
import com.lidroid.xutils.util.LogUtils;

import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.text.Html;
import android.util.Log;
import android.webkit.MimeTypeMap;

public class DownloadBySystem {
	private static final String DOWNLOAD_MANAGER_ID = "DOWNLOAD_MANAGER_ID";
	private static DownloadBySystem downloadBySystem;
	private Context context;

	public void setContext(Context context) {
		this.context = context;
	}

	public DownloadBySystem(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
		registerDownloadManagerReceiver();
	}

	public static DownloadBySystem getInstance(Context context) {
		if (null == downloadBySystem) {
			downloadBySystem = new DownloadBySystem(context);
		}
		return downloadBySystem;
	}

	public void download(String url, String fileName) {
		try {
			downloadByBrowser(url);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			try {
				downloadByGoogle(url);
			} catch (Exception e2) {
				// TODO: handle exception
				try {
					downloadByDownloadManager(url, fileName);
				} catch (Exception e3) {
					// TODO: handle exception
				}

			}

		}
	}

	public void registerDownloadManagerReceiver() {
		try {
			context.registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void downloadByGoogle(String url) throws Exception {
		if (url != null) {
			Intent intent = new Intent(Intent.ACTION_VIEW);
			Uri data = Uri.parse(Html.fromHtml(url).toString());
			intent.setData(data);
			intent.setPackage("com.google.android.browser");
			intent.addCategory("android.intent.category.BROWSABLE");
			intent.setComponent(new ComponentName("com.android.browser", "com.android.browser.BrowserActivity"));
			context.startActivity(intent);
		}
	}

	public void downloadByBrowser(String url) throws Exception {
		Uri uri = Uri.parse(url);
		Intent downloadIntent = new Intent(Intent.ACTION_VIEW, uri);
		context.startActivity(downloadIntent);
	}

	public void downloadByDownloadManager(String url, String fileName) throws Exception {
		if (!PreferencesUtils.contains(context, DOWNLOAD_MANAGER_ID)) {
			DownloadManager downloadManager = (DownloadManager) context.getSystemService(context.DOWNLOAD_SERVICE);
			// 开始下载
			Uri resource = Uri.parse(encodeGB(url));
			DownloadManager.Request request = new DownloadManager.Request(resource);
			request.setAllowedNetworkTypes(Request.NETWORK_MOBILE | Request.NETWORK_WIFI);
			request.setAllowedOverRoaming(false);
			// 设置文件类型
			MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
			String mimeString = mimeTypeMap.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(url));
			request.setMimeType(mimeString);
			// 在通知栏中显示
			request.setShowRunningNotification(true);
			request.setVisibleInDownloadsUi(true);
			// sdcard的目录下的download文件夹
			String appName = Util.getProgramNameByPackageName(context, context.getPackageName());
			
			//String filePath = getDownloadPath();

//			if (filePath!=null&&new File(filePath)!=null&&new File(filePath).exists()) {
//				Util.installApk(context, filePath);
//				return;
//			}

			request.setDestinationInExternalPublicDir("/download/", appName + fileName + ".apk");
			long id = downloadManager.enqueue(request);
			PreferencesUtils.putLong(context, DOWNLOAD_MANAGER_ID, id);
		} else {
			queryDownloadStatus();
		}
	}

	private void queryDownloadStatus() throws Exception {
		long downloadId = PreferencesUtils.getLong(context, DOWNLOAD_MANAGER_ID, 0);
		DownloadManager downloadManager = (DownloadManager) context.getSystemService(context.DOWNLOAD_SERVICE);
		DownloadManager.Query query = new DownloadManager.Query();
		query.setFilterById(downloadId);
		Cursor c = downloadManager.query(query);
		try {
			if (c.moveToFirst()) {
				int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
				String local_path = c.getString(c.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
				switch (status) {
				case DownloadManager.STATUS_PAUSED:
					LogUtils.v("STATUS_PAUSED");
				case DownloadManager.STATUS_PENDING:
					LogUtils.v("STATUS_PENDING");
				case DownloadManager.STATUS_RUNNING:
					// 正在下载，不做任何事情
					LogUtils.v("STATUS_RUNNING");
					break;
				case DownloadManager.STATUS_SUCCESSFUL:
					// 完成
					LogUtils.v("下载完成");
					Util.installApk(context, local_path);
					break;
				case DownloadManager.STATUS_FAILED:
					// 清除已下载的内容，重新下载
					LogUtils.v("STATUS_FAILED");
					File file = new File(local_path);
					if (file != null && file.exists()) {
						file.delete();
					}
					downloadManager.remove(downloadId);
					PreferencesUtils.remove(context, DOWNLOAD_MANAGER_ID);
					break;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			Util.safeClose(c);
		}

	}

	/**
	 * 如果服务器不支持中文路径的情况下需要转换url的编码。
	 * 
	 * @param string
	 * @return
	 */
	private String encodeGB(String string) {
		// 转换中文编码
		String split[] = string.split("/");
		for (int i = 1; i < split.length; i++) {
			try {
				split[i] = URLEncoder.encode(split[i], "GB2312");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			split[0] = split[0] + "/" + split[i];
		}
		split[0] = split[0].replaceAll("\\+", "%20");// 处理空格
		return split[0];
	}

	private BroadcastReceiver receiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			// 这里可以取得下载的id，这样就可以知道哪个文件下载完成了。适用与多个下载任务的监听
			try {
				queryDownloadStatus();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};

}
