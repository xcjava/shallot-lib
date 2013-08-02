/**
 * properties属性文件工具
 * @author xcjava@gmail.com
 */
package com.gmail.xcjava.base.io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;   
import java.util.*;   
   

public class PropertyReader {   
   
 
   public static Properties getProperties(String propertyFile) {   
      try {   
         URL url = getPropertiesURL(propertyFile);   
         return getProperties(url);   
      }   
      catch (Exception e) {   
         //System.out.println("Error ocurred during properties retrieval");   
         //System.out.println(e.getMessage());   
         return null;   
      }   
   }   
 
   public static URL getPropertiesURL(String fileName) {   
      try {   
         //System.out.println("Getting the properties URL");   
         URL url = null;   
         url = PropertyReader.class.getResource("/" + fileName);   
         String s = url.toString();   
         //System.out.println("Filename of the  properties file is: " + s);   
         if (s.indexOf("file://") != -1) {   
            int indexOf = s.indexOf("file://") + 6;   
            String temp = s.substring(0, indexOf);   
            //System.out.println("temp = " + temp + " moet zijn file:/");   
            url = new URL(temp + "//" + s.substring(indexOf));   
            //System.out.println("The url is now: " + url);   
         }   
         return url;   
      }   
      catch (Exception e) {   
         //System.out.println("Error ocurred during properties retrieval");   
         //System.out.println(e.getMessage());   
         return null;   
      }   
   }   

   public static Properties getProperties(URL url) {   
      try {   
         Properties props = new Properties();   
         // Check for Solaris compatibility.   
         // A // in the file protocol won't be found in Solaris.   
         props.load(url.openStream());   
         //System.out.println("Properties have been loaded: " + props);   
         return props;   
      }   
      catch (Exception e) {   
         //System.out.println("Error ocurred during properties retrieval");   
         //System.out.println(e.getMessage());   
         return null;   
      }   
   }
   
   public static String readValue(String filePath,String key) {
	  Properties props = new Properties();
	        try {
	         InputStream in = new BufferedInputStream (new FileInputStream(filePath));
	         props.load(in);
	         String value = props.getProperty (key);
	            //System.out.println(key+value);
	            return value;
	        } catch (Exception e) {
	         e.printStackTrace();
	         return null;
	        }
	 }
		 
	public static Map<String,String> readProperties(Class object,String filePath) 
	{
		Map<String,String> map = new HashMap<String,String>();
		Properties props = new Properties();
		try
		{
			//InputStream in = new BufferedInputStream (new FileInputStream(filePath));
			InputStream is = object.getResourceAsStream(filePath);
			props.load(is);
			Enumeration en = props.propertyNames();
			while (en.hasMoreElements()) 
			{
				String key = (String) en.nextElement();
				String value = props.getProperty (key);
				map.put(key, value);
			}
			return map;
		}
		catch (Exception e)
		{
			System.out.println("Read Properties Error::"+e.getMessage());
			return null;
		}
	}

    public static void writeProperties(String filePath,String parameterName,String parameterValue) {
     Properties prop = new Properties();
     try {
      InputStream fis = new FileInputStream(filePath);

            prop.load(fis);

            OutputStream fos = new FileOutputStream(filePath);
            prop.setProperty(parameterName, parameterValue);

            prop.store(fos, "Update '" + parameterName + "' value");
        } catch (IOException e) {
         System.err.println("Visit "+filePath+" for updating "+parameterName+" value error");
        }
    }
}   
