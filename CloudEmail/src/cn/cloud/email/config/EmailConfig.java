package cn.cloud.email.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class EmailConfig {
	
	private Properties properties;
	
	/**
	 * 加载配置信息并载入进内存中，之后将采用单例模式
	 * Load configuration information and load it into memory, then use singleton mode
	 */
	public EmailSeeting loadConfig() {
		properties = new Properties();
		InputStream in = EmailConfig.class.getResourceAsStream("/cloud-mail-config.properties");
		try {
			//加载信息 -> Load Info Config
			properties.load(new InputStreamReader(in, "utf-8"));
			EmailSeeting es = new EmailSeeting();
			Object debug = properties.getProperty("debug");
			if ( null == debug ) {
				System.out.println("配置文件错误");
				System.out.println("Configuration file error");
			}else {
				if ( "true".equals(properties.get(debug)) ) {
					es.setDebug(true);
				}else {
					es.setDebug(false);
				}
				es.setHost(properties.getProperty("host"));
				es.setProtocol(properties.getProperty("protocol"));
				es.setSenderAddress(properties.getProperty("senderAddress"));
				es.setSenderPassword(properties.getProperty("senderPassword"));
				es.setSmtpPort(properties.getProperty("smtpPort"));
				es.setSenderName(properties.getProperty("senderName"));
				es.setSubject(properties.getProperty("subject"));
				return es;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
