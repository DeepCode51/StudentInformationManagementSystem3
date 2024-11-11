package com.school.controller;


import com.alibaba.fastjson.JSON;
import com.school.pojo.Teacher;
import com.school.service.TeacherService;
import com.school.serviceImp.TeacherServiceImp;
import com.school.util.ResultUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/teacherController")
public class TeacherController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/javascript;charset=UTF-8");
        TeacherService teacherService = new TeacherServiceImp();

        String method = (String) request.getParameter("method");
        try {
            if ("add".equals(method)) {
                Teacher teacher = new Teacher();
                teacher.setName(request.getParameter("name"));
                teacher.setProfessional(request.getParameter("professional"));
                teacher.setDegree(request.getParameter("degree"));
                teacher.setCourse(request.getParameter("course"));
                teacher.setSex(Integer.valueOf(request.getParameter("sex")));
                Integer num = teacherService.add(teacher);
                if (num == 1) {
                    response.getWriter().write(JSON.toJSONString(ResultUtil.success("添加成功!")));
                } else {
                    response.getWriter().write(JSON.toJSONString(ResultUtil.success("添加失败!")));
                }
            }
            if ("update".equals(method)) {
                Teacher teacher = new Teacher();
                teacher.setName(request.getParameter("name"));
                teacher.setProfessional(request.getParameter("professional"));
                teacher.setDegree(request.getParameter("degree"));
                teacher.setCourse(request.getParameter("course"));
                teacher.setSex(Integer.valueOf(request.getParameter("sex")));
                teacher.setId(Integer.valueOf(request.getParameter("id")));
                Integer num = teacherService.update(teacher);
                if (num == 1) {
                    response.getWriter().write(JSON.toJSONString(ResultUtil.success("更新成功!")));
                } else {
                    response.getWriter().write(JSON.toJSONString(ResultUtil.success("更新失败!")));
                }
            }
            if ("delete".equals(method)) {
                Integer id = Integer.valueOf(request.getParameter("id"));
                Integer num = teacherService.delete(id);
                if (num == 1) {
                    response.getWriter().write(JSON.toJSONString(ResultUtil.success("删除成功!")));
                } else {
                    response.getWriter().write(JSON.toJSONString(ResultUtil.success("删除失败!")));
                }
            }
            if ("findAllToSys".equals(method)) {
                Integer page = Integer.valueOf(request.getParameter("page"));
                Integer limit = Integer.valueOf(request.getParameter("limit"));
                String nameSearch = (String)request.getParameter("nameSearch");
                Map map=new HashMap();
                map.put("page", (page-1)*limit);
                map.put("limit",limit);
                map.put("nameSearch",nameSearch);
                ResultUtil resultUtil = teacherService.findAllToSys(map);
                response.getWriter().write(JSON.toJSONString(resultUtil));
            }

            if ("findById".equals(method)) {
                Integer id = Integer.valueOf(request.getParameter("id"));
                Teacher teacher = teacherService.findById(id);
                response.getWriter().write(JSON.toJSONString(ResultUtil.success(teacher)));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


}
