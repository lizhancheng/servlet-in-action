<%@ page import="java.time.LocalDateTime" %>
<c:out value="${user.username}"/>
<% out.println(LocalDateTime.now().toString()); %>