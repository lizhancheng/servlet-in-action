package com.servlet.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.servlet.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/user/info")
public class UserServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		User user = new User("2026001", "张三", 22);
		
		boolean ageValid = user.isAgeValid();
		
		request.setAttribute("user", user);
		request.setAttribute("ageValid", ageValid);
		
		request.getRequestDispatcher("/WEB-INF/jsp/userInfo.jsp")
			.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
