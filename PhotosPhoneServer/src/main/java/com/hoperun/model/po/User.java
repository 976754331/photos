package com.hoperun.model.po;

import java.io.Serializable;

public class User implements Serializable{

	private static final long serialVersionUID = 1924769484220593557L;
	
	private String id;  //用户ID
	
	private String name; //名称
	
	private String sex; //性别
	
	private String url; //头像
	
	private String email; //邮箱
	
	private String createTime; //创建时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	

}
