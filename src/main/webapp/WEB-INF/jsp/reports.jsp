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
	<a class="btn" href="admin">Back</a>
	<a class="btn" href="logout">Logout</a>
	<br />
	<br />
	<form:form action="download" method="post">
		<input class="btn" id="submitId" name="action" type="submit" value="Download PDF">
		<input class="btn" id="submitId" name="action" type="submit" value="Download CSV">
	</form:form>
</body>
</html>	