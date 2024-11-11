package com.school.daoImp;

import com.school.dao.StudentDao;
import com.school.pojo.Student;
import com.school.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudentDaoImp  implements StudentDao {
    @Override
    public int add(Student student) {
        Connection conn = JDBCUtil.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql ="insert into student(name,no,age,sex,classes,address,nation) values(?,?,?,?,?,?,?)";
        int affectedLine = 0;//受影响的行数
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, student.getName());
            ps.setString(2, student.getNo());
            ps.setInt(3, student.getAge());
            ps.setInt(4, student.getSex());
            ps.setString(5, student.getClasses());
            ps.setString(6, student.getAddress());
            ps.setString(7, student.getNation());
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
    public int update(Student student) {
        Connection conn = JDBCUtil.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql ="update student set name = ?,no = ?,age =? ,sex = ?,classes = ?,address = ?,nation = ? where id = ? ";
        int affectedLine = 0;//受影响的行数
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, student.getName());
            ps.setString(2, student.getNo());
            ps.setInt(3, student.getAge());
            ps.setInt(4, student.getSex());
            ps.setString(5, student.getClasses());
            ps.setString(6, student.getAddress());
            ps.setString(7, student.getNation());
            ps.setInt(8, student.getId());
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
        String sql ="delete from student where id = ? ";
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
    public List<Student> findAllToSys(Map map) {
        Connection conn = JDBCUtil.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;

        StringBuffer sql = new StringBuffer( "select id,name,no,age,sex,classes,address,nation from student where 1=1") ;
        if(map.get("noSearch")!=null&& !"".equals(map.get("noSearch"))){
            sql.append("  and (no LIKE concat(concat(\"%\",?),\"%\")) ");
        }
        sql.append(" limit ? , ? ");

        Student student=null;
        List<Student> studentList=new ArrayList<>();
        try {

            ps = conn.prepareStatement(sql.toString());
            if(map.get("noSearch")!=null&& !"".equals(map.get("noSearch"))){
                ps.setString(1,map.get("noSearch").toString());
                ps.setInt(2, Integer.valueOf(map.get("page").toString()));
                ps.setInt(3, Integer.valueOf(map.get("limit").toString()));
            }else{
                ps.setInt(1, Integer.valueOf(map.get("page").toString()));
                ps.setInt(2, Integer.valueOf(map.get("limit").toString()));
            }
            rs = ps.executeQuery();
            while(rs.next()){
                student=new Student();
                student.setId(rs.getInt(1));
                student.setName(rs.getString(2));
                student.setNo(rs.getString(3));
                student.setAge(rs.getInt(4));
                student.setSex(rs.getInt(5));
                student.setClasses(rs.getString(6));
                student.setAddress(rs.getString(7));
                student.setNation(rs.getString(8));
                studentList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new  RuntimeException("出现错误！");
        }finally{
            JDBCUtil.release(conn, ps, rs);
        }
        return studentList;

    }

    @Override
    public Integer findAllToSysCount(Map map) {

        Connection conn = JDBCUtil.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;

        StringBuffer sql = new StringBuffer( "select count(*) from student where 1=1") ;
        if(map.get("noSearch")!=null&& !"".equals(map.get("noSearch"))){
            sql.append("  and (no LIKE concat(concat(\"%\",?),\"%\")) ");
        }
      Integer count=0;
        try {

            ps = conn.prepareStatement(sql.toString());
            if(map.get("noSearch")!=null&& !"".equals(map.get("noSearch"))){
                ps.setString(1,map.get("noSearch").toString());
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
    public Student findById(Integer id) {
        Connection conn = JDBCUtil.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "select id,name,no,age,sex,classes,address,nation from student where id = ? ";
        Student student=null;
        try {

            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            rs = ps.executeQuery();
            if(rs.next()){
                student=new Student();
                student.setId(rs.getInt(1));
                student.setName(rs.getString(2));
                student.setNo(rs.getString(3));
                student.setAge(rs.getInt(4));
                student.setSex(rs.getInt(5));
                student.setClasses(rs.getString(6));
                student.setAddress(rs.getString(7));
                student.setNation(rs.getString(8));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new  RuntimeException("出现错误！");
        }finally{
            JDBCUtil.release(conn, ps, rs);
        }
        return student;
    }
}
