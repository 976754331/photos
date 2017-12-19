package com.hr.platform.db;

import java.io.Serializable;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;

@Table(name = "AppModel")
public class AppModel implements Serializable {
	public static final String COLUMN_TID="Tid";
	public static final String COLUMN_APP_ID="app_id";
	public static final String COLUMN_APP_NAME="app_name";
	public static final String COLUMN_STATE="state";
	public static final String COLUMN_LOCAL_ZIP_PATH="local_zip_path";
	public static final String COLUMN_LOCAL_UNZIP_PATH="local_unzip_path";
	public static final String COLUMN_APP_DOWNLOAD_URL="app_download_url";
	public static final String COLUMN_DOWNLOAD_ID="download_id";	
	public static final String COLUMN_APP_VERSION_CODE="app_version_code";
	public static final String COLUMN_APP_VERSION_NAME="app_version_name";
	public static final String COLUMN_SIZE="SIZE";
	public static final String COLUMN_ISNORMAL="isnormal";
//	public static final String COLUMN_PLATFORM_VERSION_NAME="platform_version_name";
//	public static final String COLUMN_PLATFORM_VERSION_CODE="platform_version_code";
//	public static final String COLUMN_PLATFORM_DOWNLOAD_URL="platform_download_url";

	
	/**
	 * 
	 * @Description TODO
	 *
	 * @author Loong
	 * @since JDK 1.6
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private int Tid = 1;

	/**
	 * 应用ID(唯一标识)
	 */
	@Column(column = "app_id")
	public String app_id;

	/**
	 * 应用名称
	 */
	@Column(column = "app_name")
	public String app_name;

	/**
	 * 应用状态（0:准备，1：下载中，2：安装中，3：打开，4：更新）
	 */
	@Column(column = "state")
	public int state;

	/**
	 * 本地存储地址(类似于file:///test/test/100132.zip)
	 */
	@Column(column = "local_zip_path")
	public String local_zip_path;

	/**
	 * 本地存储地址(类似于file:///test/test/100132.zip)
	 */
	@Column(column = "local_unzip_path")
	public String local_unzip_path;
	
	
	/**
	 * 下载地址
	 */
	@Column(column = "app_download_url")
	public String app_download_url;
	
	/**
	 * 下载任务的ID
	 */
	@Column(column = "download_id")
	public long download_id;
	
	/**
	 */
	@Column(column = "app_version_code")
	public long app_version_code;
	/**
	 */
	@Column(column = "app_version_name")
	public String app_version_name;


	/**
	 * 大小
	 */
	@Column(column = "size")
	public String size;
	/**
	 * 是否被修改了 0 是未修改 1是已修改
	 */
	@Column(column = "isnormal")
	public int isnormal=0;

	

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	public String getApp_name() {
		return app_name;
	}

	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getLocal_zip_path() {
		return local_zip_path;
	}

	public void setLocal_zip_path(String local_zip_path) {
		this.local_zip_path = local_zip_path;
	}

	public String getLocal_unzip_path() {
		return local_unzip_path;
	}

	public void setLocal_unzip_path(String local_unzip_path) {
		this.local_unzip_path = local_unzip_path;
	}

	public void setApp_download_url(String app_download_url) {
		this.app_download_url = app_download_url;
	}
	
	public String getApp_download_url() {
		return app_download_url;
	}

	public long getDownload_id() {
		return download_id;
	}

	public void setDownload_id(long download_id) {
		this.download_id = download_id;
	}

	public long getApp_version_code() {
		return app_version_code;
	}

	public void setApp_version_code(long app_version_code) {
		this.app_version_code = app_version_code;
	}

	public String getApp_version_name() {
		return app_version_name;
	}

	public void setApp_version_name(String app_version_name) {
		this.app_version_name = app_version_name;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getIsnormal() {
		return isnormal;
	}

	public void setIsnormal(int isnormal) {
		this.isnormal = isnormal;
	}

	
}
