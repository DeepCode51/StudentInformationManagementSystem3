package com.school.daoImp;

import com.school.dao.LoginDao;
import com.school.pojo.Login;
import com.school.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginDaoImp implements LoginDao {



    @Override
    public Login login(String username, String password) {
        Connection conn = JDBCUtil.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "select username,password from login where username=? and password=?";
        Login login=null;
        try {

            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if(rs.next()){
               login=new Login();
               login.setUsername(rs.getString(1));
               login.setPassword(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new  RuntimeException("出现错误！");
        }finally{
            JDBCUtil.release(conn, ps, rs);
        }
        return login;
    }

    @Override
    public Integer register(String username, String password) {

        Connection conn = JDBCUtil.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql ="insert into login(username,password) values(?,?)";
        int affectedLine = 0;//受影响的行数
        try {

            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            affectedLine = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new  RuntimeException("出现错误！");
        }finally{
            JDBCUtil.release(conn, ps, rs);
        }
        return affectedLine;

    }
}
