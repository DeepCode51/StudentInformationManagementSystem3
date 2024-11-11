package com.school.service;

import com.school.pojo.Student;
import com.school.util.ResultUtil;

import java.util.Map;

public interface StudentService {
    int add(Student student);
    int update(Student student);
    int delete(Integer id);
    ResultUtil findAllToSys(Map map);
    Student findById(Integer id);
}
