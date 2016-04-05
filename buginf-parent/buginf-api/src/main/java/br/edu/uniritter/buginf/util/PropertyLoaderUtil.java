package br.edu.uniritter.buginf.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoaderUtil {
	private InputStream inputStream;
	private Properties prop;
	private String result;
	private String propFileName;
	
	public PropertyLoaderUtil(String propFileName) {
		this.propFileName = propFileName;
	}
	
	public String get(String campo) throws IOException {
		
		try {
			prop = new Properties();
		
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			
			result = prop.getProperty(campo);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			
			inputStream.close();
		}
		
		return result;
	}

}
