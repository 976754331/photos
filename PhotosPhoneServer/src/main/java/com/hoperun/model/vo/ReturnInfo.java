package com.hoperun.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;
import com.hoperun.util.PropsUtil;



/**
 * 返回值信息
 * 
 * @author Hoperun
 * @param <E>
 *
 */
public class ReturnInfo {
	
	/**
	 * 返回码
	 */
	@JsonProperty("rtn_code")
	private int rtnCode;

	/**
	 * 说明
	 */
	@JsonProperty("msg")
	private String msg;
	/**
	 * 真实数据
	 */
	@JsonProperty("data")
	private Object data = new Object();

	public ReturnInfo() {
	}

	public ReturnInfo(int code) {
		this.rtnCode = code;
		this.msg = PropsUtil.get(Integer.toString(code));
	}

	public int getRtnCode() {
		return rtnCode;
	}

	public ReturnInfo setRtnCode(int rtnCode) {
		this.rtnCode = rtnCode;
		return this;
	}

	/**
	 *
	 * @return
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 *
	 * @param msg
	 */
	public ReturnInfo setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
