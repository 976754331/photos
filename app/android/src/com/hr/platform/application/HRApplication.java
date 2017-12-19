package com.hr.platform.application;

import com.cordova.hr.https.NetWorkRequestUtil;
import com.hoperun.cma.MainActivity;
import com.hr.platform.exception.CrashHandler;
import com.hr.platform.util.PreferencesUtils;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.DbUtils.DaoConfig;

import android.app.Application;

/**
 * 
 * 
 * @author guojing09
 * 
 */
public class HRApplication extends Application {
	public static MainActivity mainActivity;
	private DbUtils db;
	public static HRApplication bDTJApplication;
	private NetWorkRequestUtil netWorkRequestUtil;

	public static HRApplication getInstance() {
		return bDTJApplication;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		bDTJApplication = this;
//		SDKInitializer.initialize(this);
		getNetWorkRequestUtil();
		//initDB();
		initExceptionHandler();
	}

	public void initExceptionHandler() {
		// 在这里为应用设置异常处理程序，然后我们的程序才能捕获未处理的异常
		CrashHandler crashHandler = CrashHandler.getInstance();
		crashHandler.init(this);
	}

	public NetWorkRequestUtil getNetWorkRequestUtil() {
		if (null == netWorkRequestUtil) {
			netWorkRequestUtil =NetWorkRequestUtil.getInstance();
		}
		return netWorkRequestUtil;
	}

	/**
	 * db.
	 * 
	 * @return the db
	 * @author Loong
	 * @since JDK 1.6
	 */
	public DbUtils getDb() {
		return db;
	}

	public void initDB() {
		DaoConfig config = new DaoConfig(this);
		config.setDbName("TJQX_DB"); // db名
		config.setDbVersion(5); // db版本
		db = DbUtils.create(config);// db还有其他的一些构造方法，比如含有更新表版本的监听器的
	}

}