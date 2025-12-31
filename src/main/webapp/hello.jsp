<html lang="en">
	<head>
		<title>Hello World</title>
	</head>
	<%-- JSP Comment --%>
	<body>
		<h1>Hello World!</h1>
		<%@ include file="header.jsp"%>
		<p>
			<% out.println("Your IP Address is "); %>
			<span style="color: red"><%= request.getRemoteAddr() %></span>
		</p>
	</body>
</html>