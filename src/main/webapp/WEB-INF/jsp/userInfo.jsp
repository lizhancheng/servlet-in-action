<%-- WEB-INF/jsp/userInfo.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>用户信息展示（Web MVC示例）</title>
		<style>
			.container {
				width: 400px;
				margin: 50px auto;
				padding: 20px;
				border: 1px solid #ccc;
				border-radius: 8px;
			}
			.valid {
				color: green;
			}
			.invalid {
				color: red;
			}
		</style>
	</head>
	<body>
		<div class="container">
			<h2>用户详情</h2>
			<p>
				<strong>用户学号：</strong>
				${user.userId}
			</p>
			<p>
				<strong>用户姓名：</strong>
				${user.userName}
			</p>
			<p>
				<strong>用户年龄：</strong>
				${user.userAge}
			</p>
			<hr>
			<p class="${ageValid ? 'valid' : 'invalid'}">
				<strong>年龄合法性：</strong>${ageValid ? '年龄合法' : '年龄非法'}
			</p>
		</div>
	</body>
</html>