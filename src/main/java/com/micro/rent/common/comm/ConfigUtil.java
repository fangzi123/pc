/**
 *
 */
package com.micro.rent.common.comm;

import com.micro.rent.common.support.ApplicationContextUtil;
import org.springframework.web.context.WebApplicationContext;

import java.util.Properties;

/**
 * @Title: ConfigUtil
 * @Package:
 * @Description: 获取配置数据
 * @author:
 * @date:
 * @version: 1.0
 */
public class ConfigUtil {
    private static Properties prop = null;
    private static Properties prop_xml = null;

    public static void init() throws Exception {
        prop = new Properties();
        prop_xml = new Properties();
        //
        String currentEnvironment = ((WebApplicationContext) ApplicationContextUtil.getContext()).getServletContext().getInitParameter("spring.profiles.default");
        prop.load(ConfigUtil.class.getResourceAsStream("/env/configurations-" + currentEnvironment + ".properties"));
    }

    public static String getConfig(String key) throws Exception {
        if (prop == null) {
            init();
        }
        return prop.getProperty(key);
    }

    public static String getConfig(String key, String defaultValue) throws Exception {
        if (prop == null) {
            init();
        }
        return prop.getProperty(key, defaultValue);
    }

    public static String getXmlConfig(String key) throws Exception {
        if (prop_xml == null) {
            init();
        }
        return prop_xml.getProperty(key);
    }

    public static String getXmlConfig(String key, String defaultValue) throws Exception {
        if (prop_xml == null) {
            init();
        }
        return prop_xml.getProperty(key, defaultValue);
    }
}
