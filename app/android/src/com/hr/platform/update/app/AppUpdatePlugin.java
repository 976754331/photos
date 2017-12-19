package com.hr.platform.update.app;

import java.io.File;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaActivity;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hr.platform.application.HRApplication;
import com.hr.platform.db.AppModel;
import com.hr.platform.statics.State;
import com.hr.platform.update.PlatformUpdateUtil;
import com.hr.platform.util.PreferencesUtils;
import com.hr.platform.util.Util;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;

public class AppUpdatePlugin extends CordovaPlugin {
	public static final String PLATFORM_UPDATE_TYPE_STR="PLATFORM_UPDATE_TYPE_STR";
	@Override
	public boolean execute(String action, CordovaArgs args, CallbackContext callbackContext) throws JSONException {
		// TODO Auto-generated method stub
		if ("appToUpdate".equals(action)) {
			try {
				
				String jsonData =new String(args.optString(0).getBytes(),"UTF-8");;
				
				JSONObject jsonObject = new JSONObject(jsonData);
				JSONObject jsonObjectData = jsonObject.optJSONObject("data");
				JSONObject jsonObjectApp = jsonObjectData.optJSONObject("app");
				AppModel appModel = new AppModel();
				appModel.setApp_id(jsonObjectApp.optString("app_id"));
//				appModel.setApp_name(jsonObjectApp.optString("app_name"));
				appModel.setApp_version_code(jsonObjectApp.optInt("app_version_code"));
//				appModel.setApp_version_name(jsonObjectApp.optString("app_version_name"));
				appModel.setApp_download_url(jsonObjectApp.optString("app_download_url"));
				AppModel appModelLocal = HRApplication.getInstance().getDb().findFirst(
						Selector.from(AppModel.class).where(AppModel.COLUMN_APP_ID, "=", appModel.getApp_id()));
				
				boolean appIsToUpdate = null == appModelLocal
						|| appModel.getApp_version_code() != appModelLocal.getApp_version_code();
				if (appIsToUpdate || null == appModelLocal.getLocal_unzip_path()) {
					String appFilePath = Util.getExternalSdCardPath(cordova.getActivity()) + File.separator
							+ appModel.getApp_id() + File.separator + appModel.getApp_version_code() + File.separator;
					String appName = appModel.getApp_id() + ".zip";
					Util.createNewFileByPath(appFilePath + appName, false);
					AppForceDownload.getInstance(cordova.getActivity()).downloadApp(appModel, appFilePath, appName);

				} else {
					Util.loadPath((CordovaActivity) cordova.getActivity(), appModelLocal, Util.DEBUG_MODE);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if ("platformToUpdate".equals(action)) {
			try {
				String jsonData =new String(args.optString(0).getBytes(),"UTF-8");;
				JSONObject jsonObject = new JSONObject(jsonData);
				JSONObject jsonObjectData = jsonObject.optJSONObject("data");
				JSONObject jsonObjectPerform = jsonObjectData.optJSONObject("platform");

				int platform_version_code = jsonObjectPerform.optInt("platform_version_code");
				//String platform_version_name = jsonObjectPerform.optString("platform_version_name");
				String platform_download_url = jsonObjectPerform.optString("platform_download_url");
				String platform_update_detail = jsonObjectPerform.optString("platform_update_detail");
				//String platform_update_time = jsonObjectPerform.optString("platform_update_time");
				boolean platform_update_force = jsonObjectPerform.optBoolean("platform_update_force");
				int platform_update_type = jsonObjectPerform.optInt("platform_update_type");
				PreferencesUtils.putInt(cordova.getActivity(), PLATFORM_UPDATE_TYPE_STR, platform_update_type);
				
				boolean platformIsToUpdate = platform_version_code > Util.getVersion(cordova.getActivity()).versionCode;

				if (platformIsToUpdate) {
					PlatformUpdateUtil.getInstance(cordova.getActivity()).showDialog(cordova.getActivity(),
							platform_update_detail, platform_update_force, platform_download_url,
							platform_version_code+"");
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if ("getPlatformVersion".equals(action)) {
			JSONObject jsonObjectVersion = new JSONObject();
			try {
				jsonObjectVersion.put(State.STATE, State.SUCCESS);
				jsonObjectVersion.put("platform_version_code", Util.getVersion(cordova.getActivity()).versionCode);
				jsonObjectVersion.put("platform_version_name", Util.getVersion(cordova.getActivity()).versionName);
				jsonObjectVersion.put(State.STATE, State.FAILED);
				PluginResult progressResult = new PluginResult(PluginResult.Status.OK, jsonObjectVersion);
				progressResult.setKeepCallback(true);
				callbackContext.sendPluginResult(progressResult);
			} catch (Exception e) {
				// TODO: handle exception
				jsonObjectVersion.put(State.STATE, State.FAILED);
				PluginResult progressResult = new PluginResult(PluginResult.Status.ERROR, jsonObjectVersion);
				progressResult.setKeepCallback(true);
				callbackContext.sendPluginResult(progressResult);
			}

		} else if ("getAppVersion".equals(action)) {
			JSONObject jsonObjectVersion = new JSONObject();
			try {
				JSONObject jsonObject = args.getJSONObject(0);
				String app_id = jsonObject.optString("app_id");

				AppModel appModelLocal = HRApplication.getInstance().getDb()
						.findFirst(Selector.from(AppModel.class).where(AppModel.COLUMN_APP_ID, "=", app_id));
				if (null != appModelLocal) {
					jsonObjectVersion.put(State.STATE, State.SUCCESS);
					jsonObjectVersion.put("platform_version_code", appModelLocal.getApp_version_code());
					jsonObjectVersion.put("platform_version_name", appModelLocal.getApp_version_name());
					jsonObjectVersion.put("app_id", appModelLocal.getApp_id());
					PluginResult progressResult = new PluginResult(PluginResult.Status.OK, jsonObjectVersion);
					progressResult.setKeepCallback(true);
					callbackContext.sendPluginResult(progressResult);
				} else {
					jsonObjectVersion.put(State.STATE, State.FAILED);
					PluginResult progressResult = new PluginResult(PluginResult.Status.ERROR, jsonObjectVersion);
					progressResult.setKeepCallback(true);
					callbackContext.sendPluginResult(progressResult);
				}

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				jsonObjectVersion.put(State.STATE, State.FAILED);
				PluginResult progressResult = new PluginResult(PluginResult.Status.ERROR, jsonObjectVersion);
				progressResult.setKeepCallback(true);
				callbackContext.sendPluginResult(progressResult);
			}

		}
		return true;
	}
}
