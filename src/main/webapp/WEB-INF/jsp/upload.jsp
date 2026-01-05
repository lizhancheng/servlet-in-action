<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>
	<script>${fileNotFound == "1" ? "alert('文件名为空')" : ""}</script>
	<form action="/pages/upload" method="post" enctype="multipart/form-data">
		<label id="filelist">上传文件：</label>
		<input type="file" name="filelist">
		<button type="submit">上传</button>
	</form>
<%@ include file="foot.jsp" %>