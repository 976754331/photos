package com.cordova.hr.imgcompass;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hr.platform.statics.Key;
import com.hr.platform.statics.State;
import com.hr.platform.statics.Value;

import android.graphics.Bitmap;

/**
 * 
 * @ClassName: OpenFile.java <br/>
 * 
 * @Description TODO native调用js
 *
 * @date: 2015年12月8日 下午5:28:33 <br/>
 * @author Loong
 * @version
 * @since JDK 1.6
 */
public class ImageCompass extends CordovaPlugin {

	/**
	 * 注意 构造方法不能为
	 * 
	 * OpenFile(){}
	 * 
	 * 可以不写或者 定义为如下
	 * 
	 */
	public ImageCompass() {
	}

	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext)
			throws org.json.JSONException {
		JSONObject jsonObject = new JSONObject();
		JSONObject jsonObjectParam = new JSONObject();
		try {
			if (action.equals("imageScale")) {
				try {
					jsonObjectParam = args.getJSONObject(0);
					String srcPath = jsonObjectParam.getString("srcPath").replace("file://", "");
					String dataType = jsonObjectParam.has("dataType") ? jsonObjectParam.getString("dataType")
							: Value.DATA_TYPE_IMG;
					int hh = jsonObjectParam.getInt("height");
					int ww = jsonObjectParam.getInt("width");
					int bitmapSize = jsonObjectParam.getInt("bitmapSize");
					int comquantity = jsonObjectParam.getInt("comquantity");
					Bitmap bitmap = com.cordova.hr.imgcompass.ImageCompassHelper.imageScale(cordova, srcPath, hh, ww, bitmapSize, comquantity);
					String filePath = ImageCompassHelper.saveBitmapInSdcard(cordova, comquantity, bitmap);
					successCallBack(callbackContext, jsonObject, dataType, bitmap, filePath);
					return true;
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					errorCallBack(callbackContext, jsonObject, e);
				}

			} else if (action.equals("imageCompressQuantity")) {
				jsonObjectParam = args.getJSONObject(0);
				String dataType = jsonObjectParam.has("dataType") ? jsonObjectParam.getString("dataType")
						: Value.DATA_TYPE_IMG;
				String srcPath = jsonObjectParam.getString("srcPath").replace("file://", "");
				int bitmapSize = jsonObjectParam.getInt("bitmapSize");
				int comquantity = jsonObjectParam.getInt("comquantity");
				Bitmap bitmap;
				try {
					bitmap = ImageCompassHelper.getBitmapByPath(srcPath);
					bitmap = ImageCompassHelper.imageScale(cordova, srcPath, 200, 200, bitmapSize, comquantity);
					bitmap = ImageCompassHelper.imageCompressQuantity(bitmap, bitmapSize, comquantity);
					String filePath = ImageCompassHelper.saveBitmapInSdcard(cordova, comquantity, bitmap);
					successCallBack(callbackContext, jsonObject, dataType, bitmap, filePath);

					return true;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					errorCallBack(callbackContext, jsonObject, e);
				}
			} else if (action.equals("scaleAndCompress")) {
				try {
					jsonObjectParam = args.getJSONObject(0);
					String dataType = jsonObjectParam.has("dataType") ? jsonObjectParam.getString("dataType")
							: Value.DATA_TYPE_IMG;
					String srcPath = jsonObjectParam.getString("srcPath").replace("file://", "");
					int hh = jsonObjectParam.getInt("height");
					int ww = jsonObjectParam.getInt("width");
					int bitmapSize = jsonObjectParam.getInt("bitmapSize");
					int comquantity = jsonObjectParam.getInt("comquantity");
					Bitmap bitmap = ImageCompassHelper.imageScale(cordova, srcPath, hh, ww, bitmapSize, comquantity);
					bitmap = ImageCompassHelper.imageCompressQuantity(bitmap, bitmapSize, comquantity);
					String filePath = ImageCompassHelper.saveBitmapInSdcard(cordova, comquantity, bitmap);
					successCallBack(callbackContext, jsonObject, dataType, bitmap, filePath);
					return true;
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorCallBack(callbackContext, jsonObject, e);
		}
		return true;

	}

	/**  
	 * @Description TODO
	 *
	 * @param callbackContext
	 * @param jsonObject
	 * @param e
	 * @throws JSONException  
	 *
	 * @author Loong 
	 * @since JDK 1.6 
	 */  
	public void errorCallBack(CallbackContext callbackContext, JSONObject jsonObject, Exception e)
			throws JSONException {
		jsonObject.put(State.STATE, State.FAILED);
		jsonObject.put(Key.PATH, "");
		jsonObject.put(Key.EXCEPTION_MESSAGE, e.getMessage());
		callbackContext.success(jsonObject);
	}

	/**  
	 * @Description TODO
	 *
	 * @param callbackContext
	 * @param jsonObject
	 * @param dataType
	 * @param bitmap
	 * @param filePath
	 * @throws JSONException  
	 *
	 * @author Loong 
	 * @since JDK 1.6 
	 */  
	public void successCallBack(CallbackContext callbackContext, JSONObject jsonObject, String dataType, Bitmap bitmap,
			String filePath) throws JSONException {
		if (Value.DATA_TYPE_BASE64.equals(dataType)) {
			String base64Data = ImageCompassHelper.bitmapToBase64(bitmap);
			jsonObject.put(Key.IMG_COMPASS_DATA, Value.BASE64_PNG_PREFIX + base64Data);
			base64Data = null;
		}
		jsonObject.put(State.STATE, State.SUCCESS);
		jsonObject.put(Key.PATH, Value.CORDOVA_FILE_PREFIX + filePath);
		jsonObject.put(Key.DATA_TYPE, dataType);
		callbackContext.success(jsonObject);
	}

}
