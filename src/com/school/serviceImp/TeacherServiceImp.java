package com.school.serviceImp;

import com.school.dao.TeacherDao;
import com.school.daoImp.TeacherDaoImp;
import com.school.pojo.Teacher;
import com.school.service.TeacherService;
import com.school.util.ResultUtil;

import java.util.List;
import java.util.Map;

public class TeacherServiceImp implements TeacherService {
    private TeacherDao teacherDao=new TeacherDaoImp() ;

    @Override
    public int add(Teacher teacher) {
        return teacherDao.add(teacher);
    }

    @Override
    public int update(Teacher teacher) {
        return teacherDao.update(teacher);
    }

    @Override
    public int delete(Integer id) {
        return teacherDao.delete(id);
    }

    @Override
    public ResultUtil findAllToSys(Map map) {

        List<Teacher> teacherList = teacherDao.findAllToSys(map);
        Integer count = teacherDao.findAllToSysCount(map);
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(0);
        resultUtil.setCount(count);
        resultUtil.setData(teacherList);
        return resultUtil;
    }


    @Override
    public Teacher findById(Integer id) {
        return teacherDao.findById(id);
    }
}
