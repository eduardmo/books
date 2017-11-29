<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="resources/css/MyCSS.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form:form action="adminactions" method="get">
		<a class="btn" href="logout">Logout</a>
		<input class="btn" type="submit" name="action" value="Book Management" />
		<input class="btn" type="submit" name="action"
			value="Employees Management" />
		<input class="btn" type="submit" name="action" value="Reports" />
	</form:form>
</body>
</html>