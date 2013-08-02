package com.gmail.xcjava.base.log;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import com.gmail.xcjava.base.date.DateUtil;

/**
 * <p>Log4j工具类</p>
 *
 * @author xiaocong
 * @email xcjava@gmail.com
 * @version 1.0
 */
public class Log4jUtil {
	
	/**
	 * 获取日志记录器
	 * @param clazz 日志记录类
	 * @param logPath 日志文件存放的路径
	 * @param logFileName 日志文件名,不需带扩展名
	 * @param logLevel 日志记录级别，如org.apache.log4j.Level.INFO
	 * @param encoding 日志文件编码
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static Logger getLogger(Class clazz,String logPath,String logFileName,Level logLevel,String encoding) throws IOException{
		Logger logger = Logger.getLogger(clazz);
		
		logger.removeAllAppenders();
		logger.setLevel(logLevel);
		
		String fileName = logFileName + DateUtil.formatDate(new Date(), "yyyyMMdd");
		String filePath = logPath + fileName + ".log";
		
	    File d = new File(logPath);
	    if (!d.exists()) {
	        d.mkdirs();
	    }
		
		File logFile = new File(filePath);
		if(!logFile.exists()){
			logFile.createNewFile();
		}
		
		DailyRollingFileAppender dailyAppender = new DailyRollingFileAppender(new PatternLayout("%m%n"),filePath,"'.'yyyy-MM-dd");
		dailyAppender.setEncoding(encoding);
		logger.addAppender(dailyAppender);
		
		return logger;
	}
	
}
