<%@ include file="header.jsp" %>
<%@ page import="com.servlet.UserService.*" %>

	<h1>
		Hello <%= user.name %>
	</h1>
	<p>
		School Name:
		<span style="color:red">
			<%= user.school.name %>
		</span>
	</p>
	<p>
		School Address:
		<span style="color:red">
			<%= user.school.address %>
		</span>
	</p>
<%@ include file="footer.jsp" %>