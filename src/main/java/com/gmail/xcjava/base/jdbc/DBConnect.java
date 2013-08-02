package com.gmail.xcjava.base.jdbc;


import java.sql.*;

/**
 * 链接类，从链接池管理类中获取空闲链接，用户不用和链接池管理类打交道
 * 
 * 配置文件 db.properties
 * driver = oracle.jdbc.driver.OracleDriver
 * CCTDB.url = jdbc:oracle:thin:@59.41.103.137:1521:CCT01
 * CCTDB.user = cctuser
 * CCTDB.password = cctuser%@**&!**cctuser
 * CCTDB.maxconn = 10
 *
 * @author xiaocong
 * @email xcjava@gmail.com
 */
public class DBConnect {
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement prepstmt = null;
	private DBConnectionManager dcm=null;


	void init(String strDB) {
        dcm = DBConnectionManager.getInstance();
        conn = dcm.getConnection(strDB);
        
    }


	public DBConnect(String strDB) throws Exception {
        init(strDB);
        stmt = conn.createStatement();
        
	}

    public DBConnect(String strDB, int resultSetType, int resultSetConcurrency) throws Exception {
        init(strDB);
        stmt = conn.createStatement(resultSetType, resultSetConcurrency);
    }

	public DBConnect(String strDB, String sql) throws Exception {
        init(strDB);
		this.prepareStatement(sql);
	}

	public DBConnect(String strDB, String sql, int resultSetType, int resultSetConcurrency) throws Exception {
        init(strDB);
		this.prepareStatement(sql, resultSetType, resultSetConcurrency);
	}

	public Connection getConnection() {
		return conn;
	}

	public void prepareStatement(String sql) throws SQLException {
		prepstmt = conn.prepareStatement(sql);
	}

	public void prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
            throws SQLException {
		prepstmt = conn.prepareStatement(sql, resultSetType, resultSetConcurrency);
	}


	public void setString(int index,String value) throws SQLException {
		prepstmt.setString(index, value);
	}
	public void setInt(int index,int value) throws SQLException {
		prepstmt.setInt(index,value);
	}
	public void setBoolean(int index,boolean value) throws SQLException {
		prepstmt.setBoolean(index,value);
	}
	public void setDate(int index,Date value) throws SQLException {
		prepstmt.setDate(index,value);
	}
	public void setLong(int index,long value) throws SQLException {
		prepstmt.setLong(index,value);
	}
	public void setFloat(int index,float value) throws SQLException {
		prepstmt.setFloat(index,value);
	}
	public void setBytes(int index,byte[] value) throws SQLException{
		prepstmt.setBytes(index,value);
	}
	public void setObject(int index,Object object) throws SQLException{
		prepstmt.setObject(index, object);
	}



    public void clearParameters()
        throws SQLException
    {
        prepstmt.clearParameters();
        prepstmt=null;
    }

	public PreparedStatement getPreparedStatement() {
		return prepstmt;
	}

	public Statement getStatement() {
		return stmt;
	}

	public ResultSet executeQuery(String sql) throws SQLException {
		if (stmt != null) {
			return stmt.executeQuery(sql);
		}
		else return null;
	}

	public ResultSet executeQuery() throws SQLException {
		if (prepstmt != null) {
			return prepstmt.executeQuery();
		}
		else return null;
	}

	public void executeUpdate(String sql) throws SQLException {
		if (stmt != null)
			stmt.executeUpdate(sql);
	}
	public void executeUpdate() throws SQLException {
		if (prepstmt != null)
			prepstmt.executeUpdate();
	}

	public void close(String strDB) throws Exception {
		if (stmt != null)  {
			stmt.close();
			stmt = null;
		}
		if (prepstmt != null) {
			prepstmt.close();
			prepstmt = null;
		}
		if (conn!=null){
			dcm.freeConnection(strDB,conn);
		}
	}
}
