package com.school.controller;


import com.alibaba.fastjson.JSON;
import com.school.pojo.Student;
import com.school.service.StudentService;
import com.school.serviceImp.StudentServiceImp;
import com.school.util.ResultUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/studentController")
public class StudentController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/javascript;charset=UTF-8");
        StudentService studentService = new StudentServiceImp();

        String method = (String) request.getParameter("method");
        try {
            if ("add".equals(method)) {
                Student student = new Student();
                student.setName(request.getParameter("name"));
                student.setAddress(request.getParameter("address"));
                student.setClasses(request.getParameter("classes"));
                student.setNation(request.getParameter("nation"));
                student.setNo(request.getParameter("no"));
                student.setSex(Integer.valueOf(request.getParameter("sex")));
                student.setAge(Integer.valueOf(request.getParameter("age")));

                Integer num = studentService.add(student);
                if (num == 1) {
                    response.getWriter().write(JSON.toJSONString(ResultUtil.success("添加成功!")));
                } else {
                    response.getWriter().write(JSON.toJSONString(ResultUtil.success("添加失败!")));
                }
            }
            if ("update".equals(method)) {
                Student student = new Student();
                student.setName(request.getParameter("name"));
                student.setAddress(request.getParameter("address"));
                student.setClasses(request.getParameter("classes"));
                student.setNation(request.getParameter("nation"));
                student.setNo(request.getParameter("no"));
                student.setSex(Integer.valueOf(request.getParameter("sex")));
                student.setAge(Integer.valueOf(request.getParameter("age")));
                student.setId(Integer.valueOf(request.getParameter("id")));
                Integer num = studentService.update(student);
                if (num == 1) {
                    response.getWriter().write(JSON.toJSONString(ResultUtil.success("更新成功!")));
                } else {
                    response.getWriter().write(JSON.toJSONString(ResultUtil.success("更新失败!")));
                }
            }
            if ("delete".equals(method)) {
                Integer id = Integer.valueOf(request.getParameter("id"));
                Integer num = studentService.delete(id);
                if (num == 1) {
                    response.getWriter().write(JSON.toJSONString(ResultUtil.success("删除成功!")));
                } else {
                    response.getWriter().write(JSON.toJSONString(ResultUtil.success("删除失败!")));
                }
            }
            if ("findAllToSys".equals(method)) {
                Integer page = Integer.valueOf(request.getParameter("page"));
                Integer limit = Integer.valueOf(request.getParameter("limit"));
                String noSearch = (String)request.getParameter("noSearch");
                Map map=new HashMap();
                map.put("page", (page-1)*limit);
                map.put("limit",limit);
                map.put("noSearch",noSearch);
                ResultUtil resultUtil = studentService.findAllToSys(map);
                response.getWriter().write(JSON.toJSONString(resultUtil));
            }

            if ("findById".equals(method)) {
                Integer id = Integer.valueOf(request.getParameter("id"));
                Student student = studentService.findById(id);
                response.getWriter().write(JSON.toJSONString(ResultUtil.success(student)));
            }

            if("updateStudentList".equals(method)){
                Integer page = 1;
                Integer limit = 60000;
                String noSearch = (String)request.getParameter("noSearch");
                Map map=new HashMap();
                map.put("page", (page-1)*limit);
                map.put("limit",limit);
                map.put("noSearch",noSearch);
                ResultUtil resultUtil = studentService.findAllToSys(map);
                List<Student> studentList=(List<Student>)resultUtil.getData();


                StringBuffer stringBuffer = new StringBuffer();
                //添加表头
                stringBuffer.append("姓名,学号,年龄,性别,班级,出生地,民族");
                stringBuffer.append("\r\n");
                for (Student student : studentList) {
                    stringBuffer.append(student.getName()+ ",");
                    stringBuffer.append(student.getNo()+ ",");
                    stringBuffer.append(student.getAge()+ ",");
                    if ("0".equals(student.getSex().toString())) {
                        stringBuffer.append("男,");
                    } else if ("1".equals(student.getSex().toString())) {
                        stringBuffer.append("女,");
                    }
                    stringBuffer.append(student.getClasses()+ ",");
                    stringBuffer.append(student.getAddress()+ ",");
                    stringBuffer.append(student.getNation()+ ",");
                    stringBuffer.append("\r\n");
                }



                response.setCharacterEncoding("gbk");
                response.setContentType("text/csv");
                response.setHeader("Content-Disposition", "attachment; filename=student.csv");
                try {
                    OutputStreamWriter outputStream =  new OutputStreamWriter(response.getOutputStream(), "gbk");


                    outputStream.write(new String(new byte[] { (byte) 0xEF, (byte) 0xBB,(byte) 0xBF }));
                    outputStream.write(stringBuffer.toString()); //result是要导出的csv的字符串
                    outputStream.flush();
                    outputStream.close();
                } catch (Exception e) {
                    System.out.println(e.toString());
                }

                response.getWriter().write(JSON.toJSONString(resultUtil));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


}
