/** 
 * @ProjectName:  TestCordova 
 * @FileName:  	  NetWorkRequest.java 
 * @PackageName:  com.hoperun.tj.https 
 * @Date:         2016年4月16日上午9:35:35 
 * @Copyright:    Copyright (c) 2016, Loong All Rights Reserved. 
 * 
 */
package com.cordova.hr.https;

import java.io.File;
import java.net.URLEncoder;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hr.platform.application.HRApplication;
import com.hr.platform.statics.Key;
import com.hr.platform.statics.State;
import com.hr.platform.encrypt.PlatFormEncrypt;
import com.http.okhttp.builder.GetBuilder;
import com.http.okhttp.builder.PostFormBuilder;
import com.http.okhttp.callback.FileCallBack;
import com.http.okhttp.callback.StringCallback;

import okhttp3.Request;

/**
 * @ClassName: NetWorkRequest <br/>
 * 
 * @Description TODO
 *
 * @date: 2016年4月16日 上午9:35:35 <br/>
 * @author Loong
 * @version
 * @since JDK 1.6
 */
public class NetWorkRequest extends CordovaPlugin {
	public JSONObject jsonObjectResponse;

	/**
	 * 注意 构造方法不能为
	 * 
	 * OpenFile(){}
	 * 
	 * 可以不写或者 定义为如下
	 * 
	 */
	public NetWorkRequest() {
	}

	@SuppressWarnings("static-access")
	@Override
	public boolean execute(final String action, final JSONArray args1, final CallbackContext callbackContext)
			throws org.json.JSONException {

		jsonObjectResponse = new JSONObject();
		cordova.getThreadPool().submit(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				try {
					JSONObject jsonObject = new JSONObject();
					jsonObject = args1.getJSONObject(0);
					String url = jsonObject.optString("url");
					if ("get".equals(action)) {
						GetBuilder getBuilder = HRApplication.getInstance().getNetWorkRequestUtil()
								.requestGet(cordova.getActivity(), url);

						if (jsonObject.has("P")) {
							JSONObject jsonObjectP = jsonObject.getJSONObject("P");
							 String data =
							 PlatFormEncrypt.encodeData(jsonObjectP.toString());
							 data = URLEncoder.encode(data, "UTF-8");
							 getBuilder.addParams("P", data);
						} else {
							JSONObject jsonObjectP = jsonObject.getJSONObject("Q");
							getBuilder.addParams("Q", jsonObjectP.toString());
						}

						getBuilder.url(url).build().execute(new StringCallback() {

							@Override
							public void onResponse(String response) {
								// TODO Auto-generated method stub
								try {
									response = decodeResponse(response);
									responseSuccess(callbackContext, response);
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}

							@Override
							public void onError(okhttp3.Request request, Exception e) {
								// TODO Auto-generated method stub
								try {
									responseError(callbackContext, e);
								} catch (JSONException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						});
					} else if ("post".equals(action)) {

						PostFormBuilder okHttpUtils = HRApplication.getInstance().getNetWorkRequestUtil()
								.requestPost(cordova.getActivity(), url);
						if (jsonObject.has("P")) {
							 JSONObject jsonObjectP =
							 jsonObject.getJSONObject("P");
							okHttpUtils.addParams("P",
							 PlatFormEncrypt.encodeData(jsonObjectP.toString()));
						} else {
							JSONObject jsonObjectP = jsonObject.getJSONObject("Q");
							okHttpUtils.addParams("Q", jsonObjectP.toString());
						}

						okHttpUtils.url(url).build().execute(new StringCallback() {

							@Override
							public void onResponse(String response) {
								// TODO Auto-generated method stub
								try {
									response = decodeResponse(response);
									responseSuccess(callbackContext, response);
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}

							@Override
							public void onError(okhttp3.Request request, Exception e) {
								// TODO Auto-generated method stub
								try {
									responseError(callbackContext, e);
								} catch (JSONException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						});
					} else if ("uploadFile".equals(action))

					{
						PostFormBuilder postFormBuilder = HRApplication.getInstance().getNetWorkRequestUtil()
								.requestPost(cordova.getActivity(), url);

						if (jsonObject.has("P")) {
							JSONObject jsonObjectP =
							jsonObject.getJSONObject("P");
							postFormBuilder.addParams("P",
							PlatFormEncrypt.encodeData(jsonObjectP.toString()));
						} else {
							JSONObject jsonObjectP = jsonObject.getJSONObject("Q");
							postFormBuilder.addParams("Q", URLEncoder.encode(jsonObjectP.toString(), "UTF-8"));
						}

						// 文件对象
						JSONArray pathsArr = jsonObject.getJSONArray("fileParam");
						for (int i = 0; i < pathsArr.length(); i++) {
							JSONObject pathJsonObj = pathsArr.getJSONObject(i);
							String formName = pathJsonObj.getString("formName");
							String fileName = pathJsonObj.getString("fileName");
							String filePath = pathJsonObj.getString("filePath").replaceAll("file://", "");
							postFormBuilder.addFile(formName, fileName, new File(filePath));
						}
						postFormBuilder.url(url).build().execute(new StringCallback() {

							@Override
							public void onResponse(String response) {
								// TODO Auto-generated method stub
								try {
									responseSuccess(callbackContext, response);
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}

							@Override
							public void onError(Request request, Exception e) {
								// TODO Auto-generated method stub
								try {
									responseError(callbackContext, e);
								} catch (JSONException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}

							@Override
							public void inProgress(float progress) {
								// TODO Auto-generated method stub
								super.inProgress(progress);
								try {
									responseSuccessProgress(callbackContext, progress + "");
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						});
					} else if ("downloadFile".equals(action))

					{
						GetBuilder getBuilder = HRApplication.getInstance().getNetWorkRequestUtil()
								.requestGet(cordova.getActivity(), url);
						if (jsonObject.has("P")) {
							// JSONObject jsonObjectP =
							// jsonObject.getJSONObject("P");
							// getBuilder.addParams("P",
							// PlatFormEncrypt.encodeData(jsonObjectP.toString()));
						} else {
							JSONObject jsonObjectP = jsonObject.getJSONObject("Q");
							getBuilder.addParams("Q", URLEncoder.encode(jsonObjectP.toString(), "UTF-8"));
						}

						String fileName = null;
						String filePath = null;
						// 文件对象
						JSONArray pathsArr = jsonObject.getJSONArray("fileParam");
						if (pathsArr.length() > 0) {
							// for (int i = 0; i < pathsArr.length(); i++) {
							JSONObject pathJsonObj = pathsArr.getJSONObject(0);
							fileName = pathJsonObj.getString("fileName");
							filePath = pathJsonObj.getString("filePath").replaceAll("file://", "");
							// }
						}

						getBuilder.url(url).build().execute(new FileCallBack(filePath, fileName) {

							@Override
							public void inProgress(float progress) {
								// TODO Auto-generated method stub
								try {
									responseSuccessProgress(callbackContext, progress + "");
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}

							@Override
							public void onError(Request request, Exception e) {
								// TODO Auto-generated method stub
								try {
									responseError(callbackContext, e);
								} catch (JSONException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}

							@Override
							public void onResponse(File response) {
								// TODO Auto-generated method stub
								try {
									responseSuccess(callbackContext, response.getAbsolutePath());
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}

						});
					}
				} catch (

				Exception e)

				{
					// TODO: handle exception
					try {
						responseError(callbackContext, e);
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		return true;
	}

	/**
	 * @Description TODO
	 *
	 * @param e
	 * @throws JSONException
	 *
	 * @author Loong
	 * @since JDK 1.6
	 */
	public void responseError(CallbackContext callbackContext, Exception e) throws JSONException {
		e.printStackTrace();
		jsonObjectResponse = new JSONObject();
		jsonObjectResponse.put(State.STATE, State.FAILED);
		jsonObjectResponse.put(Key.RESPONSE_MESSAGE, "");
		jsonObjectResponse.put(Key.EXCEPTION_MESSAGE, e.getMessage());
		PluginResult progressResult = new PluginResult(PluginResult.Status.ERROR, jsonObjectResponse);
		progressResult.setKeepCallback(true);
		callbackContext.sendPluginResult(progressResult);
		// callbackContext.error(jsonObjectResponse);
	}

	/**
	 * @Description TODO
	 *
	 * @param response
	 *
	 * @author Loong
	 * @throws JSONException
	 * @since JDK 1.6
	 */
	public void responseSuccess(CallbackContext callbackContext, String response) throws JSONException {
		try {

			jsonObjectResponse = new JSONObject();
			jsonObjectResponse.put(State.STATE, State.SUCCESS);
			jsonObjectResponse.put(Key.RESPONSE_MESSAGE, response);

			PluginResult progressResult = new PluginResult(PluginResult.Status.OK, jsonObjectResponse);
			progressResult.setKeepCallback(true);
			callbackContext.sendPluginResult(progressResult);
			// callbackContext.success(jsonObjectResponse);
		} catch (Exception e) {
			// TODO: handle exception
			responseError(callbackContext, e);
		}
	}

	public void responseSuccessProgress(CallbackContext callbackContext, String progress) throws JSONException {
		try {
			jsonObjectResponse = new JSONObject();
			jsonObjectResponse.put(State.STATE, "progress");
			jsonObjectResponse.put(Key.RESPONSE_MESSAGE, progress);

			PluginResult progressResult = new PluginResult(PluginResult.Status.OK, jsonObjectResponse);
			progressResult.setKeepCallback(true);
			callbackContext.sendPluginResult(progressResult);
			// callbackContext.success(jsonObjectResponse);
		} catch (Exception e) {
			// TODO: handle exception
			responseError(callbackContext, e);
		}
	}

	public static String decodeResponse(String response) {
		String data = null;
//		try {
//			JSONObject jsonObject = new JSONObject(response);
//			data = jsonObject.toString();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
			// try {
			// response = PlatFormEncrypt.decode(response);
			// JSONObject jsonObject = new JSONObject(response);
			// data = jsonObject.toString();
			// } catch (Exception e1) {
			// // TODO Auto-generated catch block
			// e1.printStackTrace();
			// data = response;
			// }

//		}
		return response;
	}

}
