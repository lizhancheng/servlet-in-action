package com.servlet.controller;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/statics/*")
public class FileServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ServletContext context = request.getServletContext();
		
		String urlPath = request.getRequestURI().substring(context.getContextPath().length());
		
		String filePath = context.getRealPath(urlPath);
		
		if (filePath == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		Path path = Paths.get(filePath);
		if (!path.toFile().isFile()) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		// 根据文件名猜测Content-Type:
		String mime = Files.probeContentType(path);
		if (mime == null) {
			mime = "application/octet-stream";
		}
		
		response.setContentType(mime);
		
		OutputStream output = response.getOutputStream();
		try (InputStream inputStream = new BufferedInputStream(new FileInputStream(filePath))) {
			inputStream.transferTo(output);
		}
		
		output.flush();
	}
}
