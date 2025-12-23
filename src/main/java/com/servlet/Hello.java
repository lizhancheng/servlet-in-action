package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/hello")
public class Hello extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 设置响应类型
			response.setContentType("text/html");
			// 获取输出流
			PrintWriter printWriter = response.getWriter();
			// 写入响应
			printWriter.write("<h1>Hello World!</h1>");
			// flush强制输出
			printWriter.flush();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
}
