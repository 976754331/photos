/** 
 * @ProjectName:  MainActivity 
 * @FileName:  	  Util.java 
 * @PackageName:  com.bdtj.application 
 * @Date:         2016年5月9日下午4:51:03 
 * @Copyright:    Copyright (c) 2016, Loong All Rights Reserved. 
 * 
 */
package com.hr.platform.util;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;

import org.apache.cordova.CordovaActivity;
import org.json.JSONException;
import org.json.JSONObject;

import com.hr.platform.application.HRApplication;
import com.hr.platform.db.AppModel;
import com.hr.platform.statics.ScreenSize;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * @ClassName: Util <br/>
 * 
 * @Description TODO
 *
 * @date: 2016年5月9日 下午4:51:03 <br/>
 * @version
 * @since JDK 1.6
 */
public class Util {
	public static int DEBUG_MODE = 1;// 0 插件测试环境 1生产环境
	private final static int BYTE_SIZE = 1024;

	/**
	 * 
	 * @Description TODO
	 *
	 * @param activity
	 * @param debug_mode
	 *            调试模式 // 0 插件测试环境 1 调试沙盒环境 2 生产环境
	 *
	 * @author Loong
	 * @since JDK 1.6
	 */
	public static void loadPath(CordovaActivity activity, AppModel appModel, int debug_mode) {
		int platform_update_type = -1;
		try {
			if (debug_mode == 0) {
//				File file = new File(Environment.getExternalStorageDirectory().getAbsoluteFile() + "/hr/yongdian/index.html");
//				if (file.exists()) {
//					activity.loadUrl("file://" + file.getAbsolutePath());
//					return;
//				} else {
//					Toast.makeText(activity, file.exists() + "", Toast.LENGTH_SHORT).show();
//				}
				activity.loadUrl("file:///android_asset/qf/index.html");
			} else if (debug_mode == 1) {
				if (platform_update_type == 1) {
					activity.loadUrl("file:///android_asset/www/index.html");
				} else {
					String path = appModel.getLocal_unzip_path() + "index.html";
					File file = new File(replacePreFixPath(path));
					if (file.exists() && appModel.getIsnormal() == 0) {
						activity.loadUrl(path);
						return;
					} else {
						if (!file.exists()) {
							Toast.makeText(activity, "文件不存在！", Toast.LENGTH_SHORT).show();
						} else if (appModel.getIsnormal() == 1) {
							Toast.makeText(activity, "文件已修改！", Toast.LENGTH_SHORT).show();
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public static String replacePreFixPath(String path) {
		try {
			path = path.replace(getPreFixPath(), "");
			return path;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public static String getPreFixPath() {
		return "file://";
	}

	/**
	 * 获取扩展SD卡存储目录
	 * 
	 * 如果有外接的SD卡，并且已挂载，则返回这个外置SD卡目录 否则：返回内置SD卡目录
	 * 
	 * @return
	 */
	public static String getExternalSdCardPath(Context context) {
		File cache = null;

		// SD Card Mounted
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			cache = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "HRPlatform"
					+ File.separator);
		}
		// Use internal storage
		else {
			cache = context.getFilesDir();
		}
		return cache.getAbsolutePath();
	}

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

	public static String jsonToParamstr(JSONObject jsonObject) {
		try {
			if (jsonObject.toString().length() > 0) {
				Iterator it = jsonObject.keys();
				String data = "";
				while (it.hasNext()) {
					String key = (String) it.next();
					if (!"url".equals(key)) {
						String value = jsonObject.getString(key);
						data += "&" + key + "=" + value;
					}
				}
				data = data.substring(1, data.length());
				return data;
			}
			return "";
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	public static void installApk(Context context, String path) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setDataAndType(Uri.parse("file://" + path), "application/vnd.android.package-archive");
		context.startActivity(intent);
	}

	/**
	 * 获取版本号
	 * 
	 * @return 当前应用的版本号
	 */
	public static PackageInfo getVersion(Context context) {
		try {
			PackageManager manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
			return info;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getDecimal(float scale, int d) {
		String data = "";
		for (int i = 0; i < d; i++) {
			data += "0";
		}
		DecimalFormat fnum = new DecimalFormat("##0." + data);
		String dd = fnum.format(scale);
		return dd;
	}

	public static Properties loadProtitys(Context context) {
		try {
			Properties pro = new Properties();
			InputStream is = context.getAssets().open("config.properties");
			pro.load(is);
			return pro;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	public static ScreenSize getScreenSize(Context context) {
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		int width = wm.getDefaultDisplay().getWidth();
		int height = wm.getDefaultDisplay().getHeight();
		ScreenSize screenSize = new ScreenSize(height, width);
		return screenSize;
	}

	/**
	 * 将字符串str按子字符串separatorChars 分割成数组
	 * 
	 * @param str
	 *            要拆分的字符串
	 * @param separatorChars
	 *            用来拆分的分割字符
	 * @return 拆分后的字符串
	 */
	public static String[] split2(String str, String separatorChars) {
		return splitWorker(str, separatorChars, -1, false);
	}

	/**
	 * 拆分字符串
	 * 
	 * @param str
	 *            要拆分的字符串
	 * @param separatorChars
	 *            用来拆分的分割字符
	 * @param max
	 *            要拆分字符串的最大长度
	 * @param preserveAllTokens
	 * @return 拆分后的字符串
	 */
	private static String[] splitWorker(String str, String separatorChars, int max, boolean preserveAllTokens) {
		if (str == null) {
			return null;
		}
		int len = str.length();
		if (len == 0) {
			return new String[] { "" };
		}
		Vector<String> vector = new Vector<String>();
		int sizePlus1 = 1;
		int i = 0;
		int start = 0;
		boolean match = false;
		boolean lastMatch = false;
		if (separatorChars == null) {
			while (i < len) {
				if (str.charAt(i) == '\r' || str.charAt(i) == '\n' || str.charAt(i) == '\t') {
					if (match || preserveAllTokens) {
						lastMatch = true;
						if (sizePlus1++ == max) {
							i = len;
							lastMatch = false;
						}
						vector.addElement(str.substring(start, i));
						match = false;
					}
					start = ++i;
				} else {
					lastMatch = false;
					match = true;
					i++;
				}
			}
		} else if (separatorChars.length() == 1) {
			char sep = separatorChars.charAt(0);
			while (i < len) {
				if (str.charAt(i) == sep) {
					if (match || preserveAllTokens) {
						lastMatch = true;
						if (sizePlus1++ == max) {
							i = len;
							lastMatch = false;
						}
						vector.addElement(str.substring(start, i));
						match = false;
					}
					start = ++i;
				} else {
					lastMatch = false;
					match = true;
					i++;
				}
			}
		} else {
			while (i < len) {
				int id = i + separatorChars.length() < len ? i + separatorChars.length() : len;
				if (separatorChars.indexOf(str.charAt(i)) >= 0 && separatorChars.equals(str.substring(i, id))) {
					if (match || preserveAllTokens) {
						lastMatch = true;
						if (sizePlus1++ == max) {
							i = len;
							lastMatch = false;
						}
						vector.addElement(str.substring(start, i));
						match = false;
					}
					i += separatorChars.length();
					start = i;
				} else {
					lastMatch = false;
					match = true;
					i++;
				}
			}
		}
		if (match || preserveAllTokens && lastMatch) {
			vector.addElement(str.substring(start, i));
		}
		String[] ret = new String[vector.size()];
		vector.copyInto(ret);
		return ret;
	}

	/**
	 * 关闭流对象
	 * 
	 * @param stream
	 */
	public static void safeClose(Closeable stream) {
		if (null != stream) {
			try {
				if (stream instanceof OutputStream) {
					OutputStream outputStream = (OutputStream) stream;
					outputStream.flush();
				}
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String sizeToM(String size1) {
		if (TextUtils.isEmpty(size1)) {
			return null;
		}
		try {
			Float size = Float.valueOf(size1);

			DecimalFormat df = new DecimalFormat("###.##");
			float f;
			if (size < BYTE_SIZE * BYTE_SIZE) {
				f = (float) ((float) size / (float) BYTE_SIZE);
				return df.format(new Float(f).doubleValue()) + "KB";
			} else if (size < BYTE_SIZE * BYTE_SIZE || size >= BYTE_SIZE * BYTE_SIZE * BYTE_SIZE) {
				f = (float) ((float) size / (float) (BYTE_SIZE * BYTE_SIZE * BYTE_SIZE));
				return df.format(new Float(f).doubleValue()) + "G";
			} else {
				f = (float) ((float) size / (float) (BYTE_SIZE * BYTE_SIZE));
				return df.format(new Float(f).doubleValue()) + "MB";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 
	 * @Description 创建新文件 包含目录和文件
	 *
	 * @param path
	 *            文件路径
	 * @return
	 *
	 * @author Loong
	 * @since JDK 1.6
	 */
	public static boolean createNewFileByPath(String path, boolean isDir) {
		File file = new File(path);
		if (file.exists()) {
			return true;
		}
		if (isDir) {
			return file.mkdirs();

		}
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		try {
			file.createNewFile();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static void exitApp() {
		try {
			HRApplication.getInstance().mainActivity.finish();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static TelephonyManager getPhoneInformation(Context context) {
		TelephonyManager mTm = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
		return mTm;
	}
	
	/**
	 * 通过包名获取应用程序的名称。
	 * @param context
	 *            Context对象。
	 * @param packageName
	 *            包名。
	 * @return 返回包名所对应的应用程序的名称。
	 */
	public static String getProgramNameByPackageName(Context context,
			String packageName) {
		PackageManager pm = context.getPackageManager();
		String name = null;
		try {
			name = pm.getApplicationLabel(
					pm.getApplicationInfo(packageName,
							PackageManager.GET_META_DATA)).toString();
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return name;
	}
}
