package com.school.dao;

import com.school.pojo.Student;

import java.util.List;
import java.util.Map;

public interface StudentDao {
    int add(Student student);
    int update(Student student);
    int delete(Integer id);
    List<Student> findAllToSys(Map map);
    Integer findAllToSysCount(Map map);
    Student findById(Integer id);
}
