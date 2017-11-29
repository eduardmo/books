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
	<a class="btn" href="logout">Logout</a>
	<br />
	<form:form action="user.get" method="get" modelAttribute="book">
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
	<div id="qtError" class="error">${qtError}</div>
	<br>
	<br>
	<table border="1">
		<tr>
			<th>Title</th>
			<th>Author</th>
			<th>Genre</th>
			<th>In Stock</th>
			<th>Price</th>
			<th>Quantity</th>
			<th>Action</th>
		</tr>
		<c:forEach items="${bookList}" var="bookl">
			<form:form id="formSell" action="user.do" method="post"
				modelAttribute="book">
				<input id="id${bookl.id}" type="hidden" name="id"
					value="${bookl.id}" />
				<tr>
					<td style="width: 151px;">${bookl.title}</td>
					<td style="width: 151px;">${bookl.author}</td>
					<td style="width: 151px;">${bookl.genre}</td>
					<td style="width: 151px;">${bookl.quantity}</td>
					<td style="width: 151px;">${bookl.price}</td>
					<td><input id="qt${bookl.id}" name="qt" type="text"
						value="1" onkeyup="check();"/></td>
					<td><input id="bt${bookl.id}" class="btn" type="submit" name="action"
						value="Sell" /></td>
				</tr>
			</form:form>
		</c:forEach>
	</table>
	<script type="text/javascript">
	function check(){
		var i = 0;
		while ($("#qt"+i).length) {
			if(Math.floor($('#qt'+i).val()) == $('#qt'+i).val() && $.isNumeric($('#qt'+i).val()))
			{
				console.log("is ok");
				$('#bt'+i).prop('disabled', false);
				$('#qt'+i).css('color', 'black');
			}
			else{
				console.log($('#qt'+i).val());
				$('#bt'+i).prop('disabled', true);
				$('#qt'+i).css('color', 'red');
				$('#qtError').text("Please Enter A Number");
			}
			i++;
		}
	}
	</script>
</body>
</html>