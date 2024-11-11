package com.school.serviceImp;

import com.school.dao.StudentDao;
import com.school.daoImp.StudentDaoImp;
import com.school.pojo.Student;
import com.school.service.StudentService;
import com.school.util.ResultUtil;

import java.util.List;
import java.util.Map;

public class StudentServiceImp implements StudentService {
    private StudentDao studentDao=new StudentDaoImp();

    @Override
    public int add(Student student) {
        return studentDao.add(student);
    }

    @Override
    public int update(Student student) {
        return studentDao.update(student);
    }

    @Override
    public int delete(Integer id) {
        return studentDao.delete(id);
    }

    @Override
    public ResultUtil findAllToSys(Map map) {

        List<Student> studentList = studentDao.findAllToSys(map);
        Integer count = studentDao.findAllToSysCount(map);
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(0);
        resultUtil.setCount(count);
        resultUtil.setData(studentList);
        return resultUtil;
    }


    @Override
    public Student findById(Integer id) {
        return studentDao.findById(id);
    }
}
