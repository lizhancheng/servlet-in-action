package com.servlet;

import java.io.PrintWriter;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/signin")
public class SignIn extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		String ip = request.getRemoteAddr();
		response.setContentType("text/html");
		
		try {
			
			PrintWriter printWriter = response.getWriter();
			
			printWriter.write("<h1>Your Ip Address is: " + ip + "</h1>");
			printWriter.flush();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
