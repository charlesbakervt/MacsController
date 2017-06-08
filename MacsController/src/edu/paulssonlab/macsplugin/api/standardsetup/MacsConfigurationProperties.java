package edu.paulssonlab.macsplugin.api.standardsetup;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MacsConfigurationProperties {

	private Properties prop;
	private Properties defaultProp;
	private InputStream inputStream = null;
	private InputStream defaultInputStream = null;
	private String defaultPropFileName = "macs_default_config.properties";
	private String propFileName = "macs_config.properties";

	public MacsConfigurationProperties() {
	}

	public MacsConfigurationProperties(String propFileName) {
		this.propFileName = propFileName;
	}

	public void openPropertiesFile() {
		try {
			prop = new Properties();
			defaultProp = new Properties();

			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			defaultInputStream = getClass().getClassLoader().getResourceAsStream(defaultPropFileName);

			if (defaultInputStream != null) {
				defaultProp.load(defaultInputStream);
			}
			else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}

			if (inputStream != null) {
				prop.load(inputStream);
			} 

		} 
		catch (Exception e) {
			System.out.println("Exception: " + e);
		}
	}
	
	public int getIntegerProperty(String propertyName, int defaultValue) {
		return Integer.parseInt(getStringProperty(propertyName, String.valueOf(defaultValue)));
	}
	
	public float getFloatProperty(String propertyName, float defaultValue) {
		return Float.parseFloat(getStringProperty(propertyName, String.valueOf(defaultValue)));
	}
	
	public String getStringProperty(String propertyName, String defaultValue) {
		if (prop.containsKey(propertyName)) {
			return prop.getProperty(propertyName, defaultValue);
		}
		else {
			return defaultProp.getProperty(propertyName,defaultValue);
		}
	}
	
	public void closePropertiesFile() {
		if (inputStream != null) {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			defaultInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
