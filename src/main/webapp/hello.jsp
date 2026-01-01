<%@ include file="header.jsp"%>
<p>
	<% out.println("Your IP Address is "); %>
	<span style="color: red"><%= request.getRemoteAddr() %></span>
</p>
<%@ include file="footer.jsp"%>