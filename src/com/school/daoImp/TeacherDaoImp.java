package com.school.daoImp;

import com.school.dao.TeacherDao;
import com.school.pojo.Teacher;
import com.school.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TeacherDaoImp implements TeacherDao {
    @Override
    public int add(Teacher teacher) {
        Connection conn = JDBCUtil.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql ="insert into teacher(name,professional,degree,sex,course) values(?,?,?,?,?)";
        int affectedLine = 0;//受影响的行数
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, teacher.getName());
            ps.setString(2, teacher.getProfessional());
            ps.setString(3, teacher.getDegree());
            ps.setInt(4, teacher.getSex());
            ps.setString(5, teacher.getCourse());
            affectedLine = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new  RuntimeException("出现错误！");
        }finally{
            JDBCUtil.release(conn, ps, rs);
        }
        return affectedLine;
    }

    @Override
    public int update(Teacher teacher) {
        Connection conn = JDBCUtil.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql ="update teacher set name = ?,professional = ?,degree =? ,sex = ?,course = ? where id = ? ";
        int affectedLine = 0;//受影响的行数
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, teacher.getName());
            ps.setString(2, teacher.getProfessional());
            ps.setString(3, teacher.getDegree());
            ps.setInt(4, teacher.getSex());
            ps.setString(5, teacher.getCourse());
            ps.setInt(6, teacher.getId());
            affectedLine = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new  RuntimeException("出现错误！");
        }finally{
            JDBCUtil.release(conn, ps, rs);
        }
        return affectedLine;
    }

    @Override
    public int delete(Integer id) {
        Connection conn = JDBCUtil.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql ="delete from teacher where id = ? ";
        int affectedLine = 0;//受影响的行数
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            affectedLine = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new  RuntimeException("出现错误！");
        }finally{
            JDBCUtil.release(conn, ps, rs);
        }
        return affectedLine;
    }

    @Override
    public List<Teacher> findAllToSys(Map map) {
        Connection conn = JDBCUtil.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;

        StringBuffer sql = new StringBuffer( "select id,name,professional,degree,course,sex from teacher where 1=1") ;
        if(map.get("nameSearch")!=null&& !"".equals(map.get("nameSearch"))){
            sql.append("  and (name LIKE concat(concat(\"%\",?),\"%\")) ");
        }
        sql.append(" limit ? , ? ");

        Teacher teacher=null;
        List<Teacher> teacherList=new ArrayList<>();
        try {

            ps = conn.prepareStatement(sql.toString());
            if(map.get("nameSearch")!=null&& !"".equals(map.get("nameSearch"))){
                ps.setString(1,map.get("nameSearch").toString());
                ps.setInt(2, Integer.valueOf(map.get("page").toString()));
                ps.setInt(3, Integer.valueOf(map.get("limit").toString()));
            }else{
                ps.setInt(1, Integer.valueOf(map.get("page").toString()));
                ps.setInt(2, Integer.valueOf(map.get("limit").toString()));
            }
            rs = ps.executeQuery();
            while(rs.next()){
                teacher=new Teacher();
                teacher.setId(rs.getInt(1));
                teacher.setName(rs.getString(2));
                teacher.setProfessional(rs.getString(3));
                teacher.setDegree(rs.getString(4));
                teacher.setCourse(rs.getString(5));
                teacher.setSex(rs.getInt(6));

                teacherList.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new  RuntimeException("出现错误！");
        }finally{
            JDBCUtil.release(conn, ps, rs);
        }
        return teacherList;

    }

    @Override
    public Integer findAllToSysCount(Map map) {

        Connection conn = JDBCUtil.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;

        StringBuffer sql = new StringBuffer( "select count(*) from teacher where 1=1") ;
        if(map.get("nameSearch")!=null&& !"".equals(map.get("nameSearch"))){
            sql.append("  and (name LIKE concat(concat(\"%\",?),\"%\")) ");
        }
      Integer count=0;
        try {

            ps = conn.prepareStatement(sql.toString());
            if(map.get("nameSearch")!=null&& !"".equals(map.get("nameSearch"))){
                ps.setString(1,map.get("nameSearch").toString());
            }
            rs = ps.executeQuery();
            while(rs.next()){
                count=rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new  RuntimeException("出现错误！");
        }finally{
            JDBCUtil.release(conn, ps, rs);
        }
        return count;
    }

    @Override
    public Teacher findById(Integer id) {
        Connection conn = JDBCUtil.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "select id,name,professional,degree,course,sex  from teacher where id = ? ";
        Teacher teacher=null;
        try {

            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            rs = ps.executeQuery();
            if(rs.next()){
                teacher=new Teacher();
                teacher.setId(rs.getInt(1));
                teacher.setName(rs.getString(2));
                teacher.setProfessional(rs.getString(3));
                teacher.setDegree(rs.getString(4));
                teacher.setCourse(rs.getString(5));
                teacher.setSex(rs.getInt(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new  RuntimeException("出现错误！");
        }finally{
            JDBCUtil.release(conn, ps, rs);
        }
        return teacher;
    }
}
