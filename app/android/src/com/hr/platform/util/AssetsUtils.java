package com.hr.platform.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;

public class AssetsUtils {
	/**
	 * 从assets目录下读取文本文件
	 * 
	 * @param context
	 *            上下午
	 * @param assetName
	 *            文件名称
	 * @return 文本内容
	 */
	public static String readTextFileFromAsset(Context context, String assetName) {
		StringBuilder sb = new StringBuilder("");
		InputStreamReader inputStreamReader = null;
		InputStream is = null;
		BufferedReader d = null;
		String content = "";
		try {
			is = context.getAssets().open(assetName);
			inputStreamReader = new InputStreamReader(is, "UTF-8");
			if (is != null) {
				d = new BufferedReader(inputStreamReader);
				while (d.ready()) {
					sb.append(d.readLine() + "\n");
				}
				content = sb.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			Util.safeClose(d);
			Util.safeClose(inputStreamReader);
			Util.safeClose(is);
		}
		return content;

	}
}
