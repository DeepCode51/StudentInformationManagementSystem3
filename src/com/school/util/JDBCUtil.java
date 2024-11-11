package com.school.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.Properties;

public class JDBCUtil {

	private static String DriverClass=null;
	private static String URL=null;
	private static String userName=null;
	private static String passWord=null;
	private static final ThreadLocal thread_local_db=new ThreadLocal();
	
	static{
		try {
			Properties ps = new Properties();
			String path = JDBCUtil.class.getClassLoader().getResource("").toURI().getPath();
			InputStream is = new FileInputStream(path+"jdbc.properties");
			ps.load(is);
			DriverClass = ps.getProperty("driverClass");
			URL = ps.getProperty("url");
			userName = ps.getProperty("userName");
			passWord = ps.getProperty("passWord");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	// 获取连接
	public static Connection getConn(){
		Connection Conn=null;
		try {
			if(Conn == null){//&& Conn.isClosed()
			    Class.forName(DriverClass);
				Conn = DriverManager.getConnection(URL, userName, passWord);
				//Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sales", "root", "123");
				thread_local_db.set(Conn);// 把连接对象放入线程池
			}
            Conn = (Connection) thread_local_db.get();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Conn;
	}
	
	// 关闭资源
	public static void release(Connection Conn, Statement st, ResultSet rs){
		try {
			if(rs != null){
				rs.close();
			}
			if(st != null){
				st.close();
			}
			if(Conn != null){
				Conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			rs=null;
			st=null;
			Conn=null;
		}
	}
	
}
