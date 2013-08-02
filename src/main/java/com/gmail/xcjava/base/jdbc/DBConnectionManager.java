package com.gmail.xcjava.base.jdbc;


import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * 链接池管理类
 *
 * @author xiaocong
 * @email xcjava@gmail.com
 * @version 3.0
 * 			1.0 稳定运行
 * 			2.0 支持多数据库
 * 			3.0 日志输出改用log4j
 */
public class DBConnectionManager {
	static private DBConnectionManager instance; // 唯一实例
	static private int clients;

	private Vector drivers = new Vector();
	//private PrintWriter log;
	private Hashtable pools = new Hashtable();
	private static Logger logger = Logger.getLogger(DBConnectionManager.class);

	/**
	 * 返回唯一实例.如果是第一次调用此方法,则创建实例
	 *
	 * @return DBConnectionManager 唯一实例
	 */
	static synchronized public DBConnectionManager getInstance() {
		if (instance == null) {
			instance = new DBConnectionManager();
		}
		clients++;
		return instance;
	}

	/**
	 * 建构函数私有以防止其它对象创建本类实例
	 */
	private DBConnectionManager() {
		init();
	}

	/**
	 * 将连接对象返回给由名字指定的连接池
	 *
	 * @param name 在属性文件中定义的连接池名字
	 * @param con 连接对象
	 */
	public void freeConnection(String name, Connection con) {
		DBConnectionPool pool = (DBConnectionPool) pools.get(name);
		if (pool != null) {
			pool.freeConnection(con);
		}
	}

	/**
	 * 获得一个可用的(空闲的)连接.如果没有可用连接,且已有连接数小于最大连接数
	 * 限制,则创建并返回新连接
	 *
	 * @param name 在属性文件中定义的连接池名字
	 * @return Connection 可用连接或null
	 */
	public Connection getConnection(String name) {
		DBConnectionPool pool = (DBConnectionPool) pools.get(name);
		if (pool != null) {
			return pool.getConnection();
		}
		return null;
	}

	/**
	 * 获得一个可用连接.若没有可用连接,且已有连接数小于最大连接数限制,
	 * 则创建并返回新连接.否则,在指定的时间内等待其它线程释放连接.
	 *
	 * @param name 连接池名字
	 * @param time 以毫秒计的等待时间
	 * @return Connection 可用连接或null
	 */
	public Connection getConnection(String name, long time) {
		DBConnectionPool pool = (DBConnectionPool) pools.get(name);
   
		if (pool != null) {
			return pool.getConnection(time);
		}
		return null;
	}

	/**
	 * 关闭所有连接,撤销驱动程序的注册
	 */
	public synchronized void release() {
		if (--clients != 0) {
			return;
		}

		Enumeration allPools = pools.elements();
		while (allPools.hasMoreElements()) {
			DBConnectionPool pool = (DBConnectionPool) allPools.nextElement();
			pool.release();
		}
		Enumeration allDrivers = drivers.elements();
		while (allDrivers.hasMoreElements()) {
			Driver driver = (Driver) allDrivers.nextElement();
			try {
				DriverManager.deregisterDriver(driver);
				logger.debug("撤销JDBC驱动程序 " + driver.getClass().getName()+"的注册");
			}catch (SQLException e) {
				logger.error("无法撤销下列JDBC驱动程序的注册: " + driver.getClass().getName() + e);
			}
		}
	}

	/**
	 * 根据指定属性创建连接池实例.
	 *
	 * @param props 连接池属性
	 */
	private void createPools(Properties props) {
		Enumeration propNames = props.propertyNames();
		while (propNames.hasMoreElements()) {
			String name = (String) propNames.nextElement();
			if (name.endsWith(".url")) {
				String poolName = name.substring(0, name.lastIndexOf("."));
				String url = props.getProperty(poolName + ".url");
				if (url == null) {
					logger.debug("没有为连接池" + poolName + "指定URL");
					continue;
				}
				String user = props.getProperty(poolName + ".user");
				String password = props.getProperty(poolName + ".password");
				String maxconn = props.getProperty(poolName + ".maxconn", "0");
				int max;
				try {
					max = Integer.valueOf(maxconn).intValue();
				}catch (NumberFormatException e) {
					logger.warn("错误的最大连接数限制: " + maxconn + " .连接池: " + poolName);
					max = 0;
				}
				DBConnectionPool pool = new DBConnectionPool(poolName, url, user, password, max);
				pools.put(poolName, pool);
				logger.debug("成功创建连接池" + poolName);
			}
		}
	}

	/**
	 * 读取属性完成初始化
	 */
	private void init() {
		InputStream is = getClass().getResourceAsStream("/db.properties");
		Properties dbProps = new Properties();
  
		try {
			dbProps.load(is);
		}catch (Exception e) {
			System.err.println("不能读取属性文件. " +
			"请确保db.properties在CLASSPATH指定的路径中");
			return;
		}
  
		//String logFilePath = dbProps.getProperty("logfile", "/log/DBConnectPool");
		//String logFilePath = PropertyReader.getProperties("db.properties").getProperty("file");
		//if(logFilePath.indexOf("file:/")== 0)
			//logFilePath = logFilePath.substring(6);
		
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		//String logFile = dbProps.getProperty("logfile", "/log/DBConnectPool"+sdf.format(new Date()));
		/*try {
			log = new PrintWriter(new FileWriter(logFile, true), true);
		}catch (IOException e) {
			System.err.println("无法打开日志文件: " + logFile);
			log = new PrintWriter(System.err);
		}*/
  
		loadDrivers(dbProps);
		createPools(dbProps);
	}

	/**
	 * 装载和注册所有JDBC驱动程序
	 *
	 * @param props 属性
	 */
	@SuppressWarnings("unchecked")
	private void loadDrivers(Properties props) {
		String driverClasses = props.getProperty("driver");
		StringTokenizer st = new StringTokenizer(driverClasses);
		while (st.hasMoreElements()) {
			String driverClassName = st.nextToken().trim();
			try {
				Driver driver = (Driver)
				Class.forName(driverClassName).newInstance();
				DriverManager.registerDriver(driver);
				drivers.addElement(driver);
				logger.debug("成功注册JDBC驱动程序" + driverClassName);
			}catch (Exception e) {
				logger.error("无法注册JDBC驱动程序: " + driverClassName + ", 错误: " + e);
			}
		}
	}

	/**
	 * 将文本信息写入日志文件
	
	private void log(String msg) {
		log.println(new Date() + ": " + msg);
	} */

	/**
	 * 将文本信息与异常写入日志文件
	
	private void log(Throwable e, String msg) {
		log.println(new Date() + ": " + msg);
		e.printStackTrace(log);
	} */


	class DBConnectionPool {
		private int checkedOut;
		private Vector freeConnections = new Vector();
		private int maxConn;
		private String name;
		private String password;
		private String URL;
		private String user;
		private String databaseUrl;

		public DBConnectionPool(String name, String URL, String user, String password, int maxConn) {
			this.name = name;
			this.URL = URL;
			this.user = user;
			this.password = password;
			this.maxConn = maxConn;
		}

		/**
		 * 将不再使用的连接返回给连接池
		 *
		 * @param con 客户程序释放的连接
		 */
		public synchronized void freeConnection(Connection con) {
			
			try {
				if(con != null && !con.isClosed()){
					//System.out.println(con != null);
					//System.out.println(con.isClosed());
					freeConnections.addElement(con);
					//System.out.println("freeConnection.freeConnections.归还链接:");
				}
				else{
					con.close();
					logger.debug("归还连接池" + name+"删除一个无效连接");
				}
			} catch (SQLException e) {
				logger.error("归还连接池" + name+"删除一个无效连接" + e);
			}
			
			if(checkedOut>0)
				checkedOut--;
			
			//System.out.println("freeConnection.freeConnections.size:" + freeConnections.size());
			notifyAll();
			
			
			/*freeConnections.addElement(con);
			if(checkedOut>0)
				checkedOut--;
			notifyAll();*/
		}

		public synchronized Connection getConnection() {
			
			Connection con = null;
			//System.out.println("freeConnections.size:"+freeConnections.size());
			//System.out.println("maxConn:"+maxConn);
			//System.out.println("checkedOut:"+checkedOut);
			if (freeConnections.size() > 0) {
				//System.out.println("搞糊涂已经体育台与体育");
				con = (Connection) freeConnections.firstElement();
				freeConnections.removeElementAt(0);
        
				try {
					if (con==null || con.isClosed()) {
						logger.debug("从连接池" + name+"删除一个无效连接");
						con.close();
						con = getConnection();
					}
				}catch (SQLException e) {
					logger.error("从连接池" + name+"删除一个无效连接" + e);
					con = getConnection();
				}
			}else if (maxConn == 0 || checkedOut < maxConn){
				//System.out.println("checkedOut的事发生的发生:"+checkedOut);
				con = newConnection();
			}
			if (con != null)
				checkedOut++;
			else{
				logger.warn("未能从连接池" + name+"获取到一个有效连接");
				//con = getConnection(10000);
				return null;
			}
    
			return con;
		}

		public synchronized Connection getConnection(long timeout) {
		    long startTime = new Date().getTime();
		    Connection con;
		    while ((con = getConnection()) == null) {
		        try {
		            wait(timeout);
		        }catch (InterruptedException e) {}
		        
		        if ((new Date().getTime() - startTime) >= timeout) {
		            return null;
		        }
		    }
		    return con;
		}

		/**
		* 关闭所有连接
		*/
		public synchronized void release() {
		    Enumeration allConnections = freeConnections.elements();
		    while (allConnections.hasMoreElements()) {
		        Connection con = (Connection) allConnections.nextElement();
		        try {
		            con.close();
		            logger.debug("关闭连接池" + name+"中的一个连接");
		        }catch (SQLException e) {
		        	logger.error("无法关闭连接池" + name+"中的连接" + e);
		        }
		    }
		    
		    freeConnections.removeAllElements();
		}

		/**
		 * 创建新的连接
		 */
		private Connection newConnection() {
			//System.out.println("DBConnectionPool.newConnection");
			Connection con = null;
			try {
			    if (user == null) {
			        con = DriverManager.getConnection(URL);
			    }else {
			        con = DriverManager.getConnection(URL, user, password);
			    }
			    logger.debug("连接池" + name+"创建一个新的连接");
		    }catch (SQLException e) {
		    	logger.error("无法创建下列URL的连接: " + URL + e);
		        System.err.println(e);
		        return null;
		    }
		    return con;
		}
	}
}
