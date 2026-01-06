package com.servlet.filter;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = "/pages/upload")
public class UploadLogFilter implements Filter {
	private static final Logger log = LoggerFactory.getLogger(UploadLogFilter.class);
	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		String uri = request.getRequestURI();
		String method = request.getMethod();
		
		if ("POST".equalsIgnoreCase(method)) {
			String clientIp = request.getRemoteAddr();
			String userAgent = request.getHeader("User-Agent");
			String fileName = extractFileName(request);
			
			String timestamp = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
					.format(LocalDateTime.now());
			
			// 记录日志
			log.info(
				"Upload Request - Time: {}, IP: {}, URI: {}, User-Agent: {}, File: {}",
				timestamp,
				clientIp,
				uri,
				userAgent,
				fileName
			);
			
			// 继续执行后续 Filter 或 Servlet
			chain.doFilter(request, response);
		}
	}
	
	private String extractFileName(HttpServletRequest request) throws ServletException, IOException {
		return Paths.get(request.getPart("filelist").getSubmittedFileName()).getFileName().toString();
	}
}
