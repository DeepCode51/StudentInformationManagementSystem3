package com.school.service;

import com.school.pojo.Teacher;
import com.school.util.ResultUtil;

import java.util.Map;

public interface TeacherService {
    int add(Teacher teacher);
    int update(Teacher teacher);
    int delete(Integer id);
    ResultUtil findAllToSys(Map map);
    Teacher findById(Integer id);
}
