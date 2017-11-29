<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="resources/js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="resources/js/jquery-ui.js"></script>
<script type="text/javascript" src="resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="resources/js/bookValidation.js"></script>
<link href="resources/css/MyCSS.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Books Management</title>
</head>
<body>
	<h1>Book Data</h1>
	<a class="btn" href="admin">Back</a>
	<a class="btn" href="logout">Logout</a>
	<br />
	<br />
	<form:form id="formAdd" action="adminbooks.do" method="post"
		modelAttribute="book">
		<input type="hidden" name="id" value="0" />
		<div>Title:</div>
		<form:input maxlength="30" name="title" path="title" />
		<div>Author:</div>
		<form:input maxlength="30" name="author" path="author" />
		<div>Genre:</div>
		<form:input maxlength="8" name="genre" path="genre" />
		<div>Quantity:</div>
		<form:input maxlength="15" name="quantity" path="quantity" />
		<div>Price:</div>
		<form:input name="price" path="price" />
		<br />
		<br />
		<input class="btn" type="submit" name="action" value="Add" />
	</form:form>
	<form:form action="adminbooks.get" method="get" modelAttribute="book">
		<table>
			<tr>
				<td class="error">${message}</td>
			</tr>
			<tr>
				<td>Title</td>
				<td><form:input path="title" /></td>
				<td>Author</td>
				<td><form:input path="author" /></td>
				<td>Genre</td>
				<td><form:input path="genre" /></td>
				<td><input class="btn" type="submit" name="action"
					value="Search" /></td>
				<td><input class="btn" type="submit" name="action"
					value="Show All" /></td>
			</tr>
		</table>
	</form:form>

	<table border="1">
		<tr>
			<th>Title</th>
			<th>Author</th>
			<th>Genre</th>
			<th>Quantity</th>
			<th>Price</th>
			<th>Action</th>
		</tr>
		<tr>
			<td class="error" style="width: 151px;">${titleError}</td>
			<td class="error" style="width: 151px;">${authorError}</td>
			<td class="error" style="width: 151px;">${genreError}</td>
			<td class="error" style="width: 151px;">${quantityError}</td>
			<td class="error" style="width: 151px;">${priceError}</td>
		</tr>
		<c:forEach items="${bookList}" var="bookl">
			<form:form id="formEdit" action="adminbooks.do" method="post"
				modelAttribute="book">
				<input type="hidden" name="id" value="${bookl.id}" />
				<tr>
					<td style="width: 151px;"><form:input path="title"
							value="${bookl.title}" /></td>
					<td style="width: 151px;"><form:input path="author"
							value="${bookl.author}" /></td>
					<td style="width: 151px;"><form:input path="genre"
							value="${bookl.genre}" /></td>
					<td style="width: 151px;"><form:input path="quantity"
							value="${bookl.quantity}" /></td>
					<td style="width: 151px;"><form:input path="price"
							value="${bookl.price}" /></td>
					<td><input class="btn" type="submit" name="action"
						value="Edit" /><input class="btn" type="submit" name="action"
						value="Delete" /></td>
				</tr>
			</form:form>
		</c:forEach>
	</table>
</body>
</html>