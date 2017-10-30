package com.hoperun.util;

import com.alibaba.druid.filter.config.ConfigTools;
import com.alibaba.druid.util.DruidPasswordCallback;




import jodd.exception.ExceptionUtil;
import jodd.util.StringUtil;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Properties;

/**
 * @author Joe Chow
 * @since 2017/6/28
 */
public class HRDBPasswordCallback extends DruidPasswordCallback {

    protected final Logger LOG = Logger.getLogger(HRDBPasswordCallback.class);

    public void setProperties(Properties properties) {
        super.setProperties(properties);
        String part1 = properties.getProperty("woc");
        if (StringUtil.isNotBlank(part1)) {
            try {
                String part2 = PropsUtil.get("db.woc");
                String password = ConfigTools.decrypt(RSAEncrypt.getPublicKey(), part1 + part2);
                setPassword(password.toCharArray());
            } catch (Exception e) {
                LOG.error(ExceptionUtil.exceptionChainToString(e));
                setPassword(part1.toCharArray());
            }
        }
    }
}
