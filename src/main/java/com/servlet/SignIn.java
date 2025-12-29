package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/signin")
public class SignIn extends HttpServlet {
	private Map<String, String> users = Map.of("admin", "adminpwd", "test", "testpwd", "guest", "");
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		String ip = request.getRemoteAddr();
		response.setContentType("text/html");
		
		try {
			response.setContentType("text/html");
			PrintWriter printWriter = response.getWriter();
			String output = """
						<h1>UserIp: %s</h1>
						<form action="/signin" method="post">
							<label id="username">username: <input name="username"></label>
							<label id="password">password: <input type="password" name="password"></label>
							<button type="submit">Submit</button>
							<button type="reset">Reset</button>
						</form>
					""".formatted(ip);
			printWriter.write(output);
			printWriter.flush();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException { 
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		String correctedPwd = users.get(username.toLowerCase());
		if (correctedPwd.equals(password)) {
			// 登录
			request.getSession().setAttribute("username", username);
			response.sendRedirect("/");
		} else {
			// 不正确则不可登录
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
		}
	}
}
