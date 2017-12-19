package com.hr.platform.update.app;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.hr.platform.application.HRApplication;
import com.hr.platform.db.AppModel;
import com.hr.platform.util.Util;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.util.LogUtils;

import android.content.Context;
import android.os.FileObserver;

/**
 * 文件夹观察者，能够递归监测文件夹及子文件夹变化
 * 
 * @author Administrator
 *
 */
public class DirRecursiveFileObserver extends FileObserver {
	/** 文件夹检测是否启用，true启用，false不启用 */
	public static boolean ENABLED = true;

	public static final int MASK = FileObserver.CREATE | FileObserver.MODIFY | FileObserver.DELETE;

	private final static String TAG = "DirRecursiveFileObserver";

	private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

	private static List<String> mObserverdPaths = new ArrayList<String>();
	List<SingleFileObserver> mObservers;

	private static HashMap<String, List<SingleFileObserver>> mMapPathObservers = new HashMap<String, List<SingleFileObserver>>();

	Context mContext;
	String mPath;
	int mMask;
	AppModel mAppModel;
	List<String> pathWhiteList = new ArrayList<String>();

	public DirRecursiveFileObserver(Context context, AppModel appModel) {
		this(context, appModel, ALL_EVENTS);
		mContext = context;
		mAppModel = appModel;
	}

	public DirRecursiveFileObserver(Context context, AppModel appModel, int mask) {
		super(Util.replacePreFixPath(appModel.getLocal_unzip_path()), mask);
		mPath = Util.replacePreFixPath(appModel.getLocal_unzip_path());
		mMask = mask;
		mContext = context;
		mAppModel = appModel;

		initWhiteList();
	}

	/**
	 * 初始化config文件里配置的白名单，默认db及WhiteDir文件夹处于白名单中
	 */
	private void initWhiteList() {
		pathWhiteList.add("db");
		pathWhiteList.add("WhiteDir");
	}

	@Override
	public void startWatching() {
		if (!ENABLED) {
			LogUtils.w("startWatching not ENABLED now : " + mPath);
			return;
		}

		if (mObservers != null || mObserverdPaths.contains(mPath)) {
			LogUtils.w("already wartching now : " + mPath);
			return;
		}

		mObserverdPaths.add(mPath);
		threadPool.submit(new ObserverLoader());
	}

	public void stopWatchingPath(String path) {
		List<SingleFileObserver> observers = null;
		observers = mMapPathObservers.get(path);

		if (observers == null) {
			LogUtils.w("no files STOP wartching :" + path);
			return;
		}

		synchronized (observers) {
			for (SingleFileObserver sfo : observers) {
				sfo.stopWatching();
			}
		}
		observers.clear();
		observers = null;

		LogUtils.d(mAppModel.app_name + " -- STOP wartching: " + mPath);
		mObserverdPaths.remove(path);
		mMapPathObservers.remove(path);
	}

	class ObserverLoader implements Runnable {

		@Override
		public void run() {
			mObservers = new ArrayList<SingleFileObserver>();
			final Stack<String> stack = new Stack<String>();
			stack.push(mPath);
			LogUtils.w(mAppModel.app_name + " -- START wartching: " + mPath);

			while (!stack.isEmpty()) {
				String parent = (String) stack.pop();

				mObservers.add(new SingleFileObserver(parent, mMask));
				File path = new File(parent);
				File[] files = path.listFiles();
				if (null == files) {
					continue;
				}
				for (File f : files) {
					if (f.isDirectory() && !f.getName().equals(".") && !f.getName().equals("..")) {
						if (!isPathInWhiteList(f.getPath())) {
							stack.push(f.getPath());
						}
					}
				}
			}

			mMapPathObservers.put(mPath, mObservers);
			for (SingleFileObserver sfo : mObservers) {
				sfo.startWatching();
			}
		}
	}

	@Override
	public void onEvent(int event, String path) {
		switch (event) {
		case FileObserver.CREATE:
			LogUtils.d("CREATE: " + path);
			updateApp(path);
			break;
		case FileObserver.ACCESS:
			LogUtils.d("ACCESS: " + path);
			break;
		case FileObserver.ATTRIB:
			LogUtils.d("ATTRIB: " + path);
			break;
		case FileObserver.CLOSE_NOWRITE:
			LogUtils.d("CLOSE_NOWRITE: " + path);
			break;
		case FileObserver.CLOSE_WRITE:
			LogUtils.d("CLOSE_WRITE: " + path);
			break;
		case FileObserver.DELETE:
			LogUtils.d("DELETE: " + path);
			updateApp(path);
			break;
		case FileObserver.DELETE_SELF:
			LogUtils.d("DELETE_SELF: " + path);
			updateApp(path);
			break;
		case FileObserver.MODIFY:
			LogUtils.d("MODIFY: " + path);
			updateApp(path);
			break;
		case FileObserver.MOVE_SELF:
			LogUtils.d("MOVE_SELF: " + path);
			updateApp(path);
			break;
		case FileObserver.MOVED_FROM:
			LogUtils.d("MOVED_FROM: " + path);
			updateApp(path);
			break;
		case FileObserver.MOVED_TO:
			LogUtils.d("MOVED_TO: " + path);
			updateApp(path);
			break;
		case FileObserver.OPEN:
			LogUtils.d("OPEN: " + path);
			break;
		default:
			LogUtils.d("DEFAULT(" + event + "): " + path);
			break;
		}
	}

	private void updateApp(String path) {
		if (isPathInWhiteList(path)) {
			LogUtils.d("yeah yeah, PathInWhiteList: " + path);
			return;
		}
		if (mAppModel.getIsnormal() == 1) {
			return;
		}
		mAppModel.setIsnormal(1);;
		LogUtils.w("updateApp: " + mAppModel.app_name + "isnormal = 1");
		try {
			HRApplication.getInstance().getDb().update(mAppModel,
					WhereBuilder.b(AppModel.COLUMN_APP_ID, "=", mAppModel.getApp_id()), AppModel.COLUMN_ISNORMAL);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	class SingleFileObserver extends FileObserver {
		String mPath;

		public String getPath() {
			return mPath;
		}

		public SingleFileObserver(String path) {
			this(path, ALL_EVENTS);
			mPath = path;
		}

		public SingleFileObserver(String path, int mask) {
			super(path, mask);
			mPath = path;
		}

		@Override
		public void onEvent(int event, String path) {
			String newPath = mPath + "/" + path;
			DirRecursiveFileObserver.this.onEvent(event, newPath);
		}
	}

	public void setPathWhiteList(List<String> pathWhiteList) {
		this.pathWhiteList = pathWhiteList;
	}

	public boolean isPathInWhiteList(String path) {
		if (path == null || pathWhiteList == null || pathWhiteList.size() == 0) {
			return false;
		}
		for (int i = 0; i < pathWhiteList.size(); i++) {
			if (path.contains(pathWhiteList.get(i))) {
				LogUtils.d("PathInWhiteList : " + path);
				return true;
			}
		}

		return false;
	}
}
