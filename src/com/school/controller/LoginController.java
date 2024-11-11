package com.school.controller;


import com.alibaba.fastjson.JSON;
import com.school.pojo.Login;
import com.school.service.LoginService;
import com.school.serviceImp.LoginServiceImp;
import com.school.util.ResultUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loginController")
public class LoginController extends HttpServlet  {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/javascript;charset=UTF-8");
        LoginService loginService=new LoginServiceImp();

        String method = (String)request.getParameter("method");
        try {
            if("login".equals(method)){
                String username=(String)request.getParameter("username");
                String password=(String)request.getParameter("password");
                Login login=  loginService.login(username,password);
                if(login!=null){
                    request.getSession().setAttribute("user",login);
                    response.getWriter().write(JSON.toJSONString( ResultUtil.success("登录成功!")));
                }else{
                    response.getWriter().write(JSON.toJSONString( ResultUtil.error("登录失败!")));
                }
            }
            if("register".equals(method)){
                String username=(String)request.getParameter("username");
                String password=(String)request.getParameter("password");
                Login login=  loginService.login(username,password);
                if(login!=null){
                    response.getWriter().write(JSON.toJSONString(  ResultUtil.error("用户名已经被注册!")));
                }else{
                    Integer num=  loginService.register(username,password);
                    if(num ==1){
                        response.getWriter().write(JSON.toJSONString(  ResultUtil.success("注册成功!")));
                       ;
                    }else{
                        response.getWriter().write(JSON.toJSONString( ResultUtil.success("注册失败!")));
                        ;
                    }
                }
            }
            if("loginOut".equals(method)){
                request.getSession().removeAttribute("user");
                response.getWriter().write(JSON.toJSONString(  ResultUtil.success("退出成功!")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


}
