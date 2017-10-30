package com.hoperun.util;

import org.apache.log4j.Logger;

import jodd.props.Props;
import jodd.typeconverter.Convert;
import jodd.util.StringUtil;

/**
 * Jodd属性文件工具类(*.props)
 * @package com.fande.cmserver.common.util
 * @ClassName PropsUtil
 * @author XAFD)ZhouJiao
 * @date 2015年10月30日 下午10:56:11
 */
public class PropsUtil {

	/** PROPS */
	public static final Props PROPS = new Props();
	
	private static final Logger logger = Logger.getLogger(PropsUtil.class);

	static {
		PROPS.setIgnoreMissingMacros(true);
		jodd.props.PropsUtil.loadFromClasspath(PROPS, "*.props");
	}


	public static String get(final String key) {
		return PROPS.getValue(key);
	}

	public static Boolean getBoolean(final String key) {
		String value = PROPS.getValue(key);
		try {
			if (StringUtil.isEmpty(value)) {
					throw new Exception();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("value值为null");
		}
		return Convert.toBoolean(value);
		//return Convert.toBoolean(StringUtil.isEmpty(value) ? null : value);
	}

	public static Integer getInteger(final String key) {
		String value = PROPS.getValue(key);
		try {
			if (StringUtil.isEmpty(value)) {
					throw new Exception();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("value值为null");
		}
		return Convert.toInteger(value);
		//return Convert.toInteger(StringUtil.isEmpty(value) ? null : value);
	}

	public static Long getLong(final String key) {
		String value = PROPS.getValue(key);
		try {
			if (StringUtil.isEmpty(value)) {
					throw new Exception();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("value值为null");
		}
		return Convert.toLong(value);
		//return Convert.toLong(StringUtil.isEmpty(value) ? null : value);
	}
}
