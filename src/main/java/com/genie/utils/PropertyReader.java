package com.genie.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.util.Properties;

/**
 * Desc:properties文件获取工具类 
 */
public class PropertyReader {
	private static Logger logger = LoggerFactory.getLogger(PropertyReader.class);
	private Properties props;
	private String propertiesFile;
	/*static {
		loadProps();
	}*/

	public void loadProps(String propFile) {
		//logger.info("开始加载properties文件内容.......");
		setPropertiesFile(propFile);
		props = new Properties();
		InputStream in = null;
		try {
			in = PropertyReader.class.getResourceAsStream(propFile);
			props.load(in);
		} catch (FileNotFoundException e) {
			logger.error("properties文件未找到");
		} catch (IOException e) {
			logger.error("出现IOException");
		} finally {
			try {
				if (null != in) {
					in.close();
				}
			} catch (IOException e) {
				logger.error("properties文件流关闭出现异常");
			}
		}
		logger.info("加载properties文件内容完成");
		logger.debug("properties文件内容：" + props);
	}

	public String getProperty(String key) {
		if (null == props) {
			loadProps(propertiesFile);
		}
		return props.getProperty(key);
	}

	public String getProperty(String key, String defaultValue) {
		if (null == props) {
			loadProps(propertiesFile);
		}
		return props.getProperty(key, defaultValue);
	}

	public int getPropertyInt(String key){
		if (null == props) {
			loadProps(propertiesFile);
		}
		return Integer.parseInt(props.getProperty(key));
	}
	
	public String getPropertiesFile() {
		return propertiesFile;
	}

	public void setPropertiesFile(String propertiesFile) {
		this.propertiesFile = propertiesFile;
	}
	
	
}