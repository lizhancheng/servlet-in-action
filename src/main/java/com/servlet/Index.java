package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/")
public class Index extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String user = (String) request.getSession().getAttribute("username");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("X-Powered-By", "JavaEE Servlet");
		
		PrintWriter printWriter = response.getWriter();
		
		printWriter.write("""
				<h1>Welcome home, %s!</h1>
				""".formatted(user));
		
		if (user == null) {
			printWriter.write("<a href=\"/signin\">Sign In</a>");
		} else {
			printWriter.write("<a href=\"/signout\">Sign Out</a>");
		}
		printWriter.write("<hr>");
		printWriter.write("<a href=\"/uselng?lang=en\">English</a> | <a href=\"/uselng?lang=zh\">中文</a>");
	}
}
