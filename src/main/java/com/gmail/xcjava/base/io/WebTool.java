package com.gmail.xcjava.base.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletResponse;

//import org.apache.struts2.ServletActionContext;

/**
 * 网页读取工具
 * @author xcjava@gmail.com
 *
 */
public class WebTool {

	/*public static String downloadPage(String strUrl,String strCharSet){
		try {
			URL pageUrl = new URL(strUrl);
			BufferedReader reader = new BufferedReader(new InputStreamReader(pageUrl.openStream(),Charset.forName(strCharSet)));
			String line;
			StringBuffer pageBuffer = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				pageBuffer.append(line+"\r\n");
			}
			return pageBuffer.toString();
		} catch (Exception e) {
			return "";
		}
	}
	
	public static boolean fileUpload(String filePath, String fileName, FileInputStream fis){
		try {
			FileOutputStream fos = new FileOutputStream(filePath + fileName);
			byte[] buffer = new byte[1024*1024*10];
			int len = 0;
			if((len=fis.read(buffer))>0){
				fos.write(buffer,0,len);
			}
			fis.close();
			fos.close();
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static void fileDownload(String filePath, String fileName, String contentType){
		HttpServletResponse response = ServletActionContext.getResponse();
		File file = new File(filePath + fileName);
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis);
			OutputStream os;
			os = response.getOutputStream();
			BufferedOutputStream bos = new BufferedOutputStream(os);
			response.reset();
		    response.setContentType("UTF-8");
		    response.setContentType(contentType);
		    
		    response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		    response.setHeader("Content-Length", String.valueOf(bis.available()));

		    int bytesRead = 0;
		    byte[] buffer = new byte[1024];
		    while ((bytesRead = bis.read(buffer)) != -1) {
		      bos.write(buffer, 0, bytesRead);
		    }

		    bos.flush();
		    bos.close();
		    bis.close();
		    os.flush();
		    os.close();
		    fis.close();
			
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			
		}

	}*/
}
