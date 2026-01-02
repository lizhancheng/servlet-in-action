package com.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.servlet.bean.User;
import com.servlet.bean.School;

@WebServlet(urlPatterns = "/user")
public class UserService extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		School school = new School("Conghua Middle School", "Conghua road");
		User user = new User(1010, "Mike", school);
		
		// 放到request中
		request.setAttribute("user", user);
		// forward给user.jsp
		request.getRequestDispatcher("/user.jsp")
			.forward(request, response);
	}
}
