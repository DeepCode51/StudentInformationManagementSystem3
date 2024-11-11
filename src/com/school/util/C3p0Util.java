package com.school.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class C3p0Util {
    
    //C3p0工具类单列
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource("mysql");
	public static DataSource getDataSource(){
		return dataSource;
	}
	private C3p0Util() {
	}
	
	// 命名一个公共的获取连接方法
	public static Connection getConnection(){
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void release(Connection conn, Statement st, ResultSet rs){
		// 关闭结果集资源
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// 关闭st对象
		if(st != null){
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// 关闭连接
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	} 
}
