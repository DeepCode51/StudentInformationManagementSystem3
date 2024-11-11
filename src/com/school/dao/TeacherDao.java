package com.school.dao;

import com.school.pojo.Teacher;

import java.util.List;
import java.util.Map;

public interface TeacherDao {
    int add(Teacher teacher);
    int update(Teacher teacher);
    int delete(Integer id);
    List<Teacher> findAllToSys(Map map);
    Integer findAllToSysCount(Map map);
    Teacher findById(Integer id);
}
