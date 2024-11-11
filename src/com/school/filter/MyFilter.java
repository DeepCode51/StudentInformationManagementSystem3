package com.school.filter;

import com.alibaba.fastjson.JSON;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class MyFilter implements Filter{
	@Override
	public void destroy() {
		
	}
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filter)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Header", "x-requested-with");
		response.setHeader("Access-Control-Allow-Credentials", "true");//是否支持cookie

		String sessionid = (String) request.getSession().getAttribute("userid");
		System.out.println("sessionID"+sessionid);
		
		String userid = request.getHeader("userid");
		if(userid != null && userid != ""){// 判断是否有userid 
			filter.doFilter(req, res);
		}else{
			PrintWriter writer = response.getWriter();
			Map<String,String> map = new HashMap<String,String>();
			map.put("flag", "false");
			writer.write(JSON.toJSONString(map));
		}
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
