package com.cordova.hr.https;

import java.util.concurrent.TimeUnit;

import com.http.okhttp.OkHttpUtils;
import com.http.okhttp.builder.GetBuilder;
import com.http.okhttp.builder.PostFormBuilder;
import com.lidroid.xutils.HttpUtils;

import android.annotation.SuppressLint;
import android.content.Context;

public class NetWorkRequestUtil {
	OkHttpUtils okHttpUtils;
	HttpUtils xUtilsHttpUtils;
	GetBuilder getBuilder;
	PostFormBuilder postFormBuilder;
	static NetWorkRequestUtil netWorkRequestUtil = new NetWorkRequestUtil();
	// token 超时时间
	public static final long TOKEN_TIME_OUT = 5 * 60 * 1000L;

	public   long net_work_time = 0L;

	public static NetWorkRequestUtil getInstance() {
		if (null == netWorkRequestUtil) {
			return new NetWorkRequestUtil();
		}
		return netWorkRequestUtil;
	}

	public NetWorkRequestUtil() {
		// TODO Auto-generated constructor stub

		getOkHttpUtils();
	}

	/**
	 * okhttputils.
	 * 
	 * @return the okhttputils
	 * @author Loong
	 * @since JDK 1.6
	 */
	@SuppressLint("NewApi")
	public OkHttpUtils getOkHttpUtils() {
		if (okHttpUtils == null) {
			okHttpUtils = OkHttpUtils.getInstance();
			okHttpUtils.setConnectTimeout(5, TimeUnit.MINUTES);
			getBuilder = okHttpUtils.get();
			postFormBuilder = okHttpUtils.post();
		}
		return okHttpUtils;
	}

	/**
	 * okhttputils.
	 * 
	 * @return the okhttputils
	 * @author Loong
	 * @since JDK 1.6
	 */
	public GetBuilder requestGet(Context context, String url) {
		getOkHttpUtils();
		return (GetBuilder) getBuilder;
	}


	/**
	 * okhttputils.
	 * 
	 * @return the okhttputils
	 * @author Loong
	 * @since JDK 1.6
	 */
	public PostFormBuilder requestPost(Context context, String url) {
		getOkHttpUtils();
		return (PostFormBuilder) postFormBuilder;
	}

}
