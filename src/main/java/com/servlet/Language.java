package com.servlet;

import java.io.IOException;
import java.util.Iterator;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/uselng")
public class Language extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String language = request.getParameter("lang");
		if (language != null) {
			// 创建新的cookie
			Cookie cookie = new Cookie("lang", language);
			// 该cookie生效的路径范围
			cookie.setPath("/");
			// 该cookie有效期
			cookie.setMaxAge(8640000);
			// 将该cookie添加到响应
			response.addCookie(cookie);
		}
		
		response.sendRedirect("/");
	}
	
	private String parseLanguageFromCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("lang")) {
					return cookie.getValue();
				}
			}
		}
		
		return "zh";
	}
}
