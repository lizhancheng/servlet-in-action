package com.servlet;

import java.io.IOException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/redirect")
public class Redirect extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		String redirectUrl = "/hello" + (name == null ? "" : "?name=" + name);
		// 发送重定向响应
		response.sendRedirect(redirectUrl);
	}
}
