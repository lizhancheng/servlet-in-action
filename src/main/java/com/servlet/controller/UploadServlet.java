package com.servlet.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet(urlPatterns = "/pages/upload")
@MultipartConfig(
	fileSizeThreshold = 1024 * 1024 * 2, // 2MB - 多大文件开始写入磁盘
	maxFileSize = 1024 * 1024 * 10, // 10MB - 单个文件最大大小
	maxRequestSize = 1024 * 1024 * 50 // 50MB - 整个请求最大大小
)
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIR = "uploads";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setAttribute("title", "文件上传");
		request.getRequestDispatcher("/WEB-INF/jsp/upload.jsp")
			.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Part fileList = request.getPart("filelist");
		if (fileList == null) {
			response.getWriter().println("未选择文件");
			return;
		}
		
		String fileName = Paths.get(fileList.getSubmittedFileName()).getFileName().toString();
		if (fileName == null || fileName.equals("")) {
			request.setAttribute("fileNotFound", "1");
			doGet(request, response);
			return;
		}
		if (fileName.contains("..")) {
			response.getWriter().println("非法文件名！");
			return;
		}
		
		String applicationPath = request.getServletContext().getRealPath("");
		String uploadPath = applicationPath + UPLOAD_DIR;
		
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		
		String fullPath = uploadPath + File.separator + fileName;
		try (InputStream input = fileList.getInputStream()) {
			FileOutputStream output = new FileOutputStream(fullPath);
			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = input.read(buffer)) != -1) {
				output.write(buffer, 0, bytesRead);
			}
		}
		
		response.getWriter().println("文件上传成功！<br>");
		response.getWriter().println("保存路径：" + fullPath);
	}
}
