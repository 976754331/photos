package com.hr.platform.db;

import java.io.Serializable;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Table;

@Table(name = "PlatformBean")
public class PlatformBean implements Serializable {
	private static final long serialVersionUID = 4080101682530089659L;
	@Column(column = "platformUpdateUrl")
	public String platformUpdateUrl;

	public String getPlatformUpdateUrl() {
		return platformUpdateUrl;
	}

	public void setPlatformUpdateUrl(String platformUpdateUrl) {
		this.platformUpdateUrl = platformUpdateUrl;
	}

}
