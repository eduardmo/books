<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="resources/js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="resources/js/jquery-ui.js"></script>
<script type="text/javascript" src="resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="resources/js/employeeValidation.js"></script>
<link href="resources/css/MyCSS.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employees Management</title>
</head>
<body>
	<h1>Employee Data</h1>
	<a class="btn" href="admin">Back</a>
	<a class="btn" href="logout">Logout</a>
	<br />
	<br />
	<form:form id="formAdd" action="employee.do" method="post"
		modelAttribute="employee">
		<div>Personal Numerical Code:</div>
		<form:input maxlength="13" id="employeePersonalNumericalCode"
			name="employeePersonalNumericalCode"
			path="employeePersonalNumericalCode" value="" />
		<div>First Name:</div>
		<form:input maxlength="30" name="employeeFirstName"
			path="employeeFirstName" />
		<div>Last Name:</div>
		<form:input maxlength="30" name="employeeLastName"
			path="employeeLastName" />
		<div>ID Card Number</div>
		<form:input maxlength="8" name="employeeIdCardNumber"
			path="employeeIdCardNumber" />
		<div>Username:</div>
		<form:input maxlength="15" name="username" path="username" />
		<div>Password:</div>
		<form:input name="password" path="password" />
		<br />
		<br />
		<input class="btn" type="submit" name="action" value="Add" />
	</form:form>
	<form:form action="employee.get" method="get" modelAttribute="employee">
		<table>
			<tr>
				<td class="error">${message}</td>
			</tr>
			<tr>
				<td>Personal Numerical Code</td>
				<td><form:input path="employeePersonalNumericalCode" /></td>
				<td><input class="btn" type="submit" name="action"
					value="Search" /></td>
				<td><input class="btn" type="submit" name="action"
					value="Show All" /></td>
			</tr>
		</table>
	</form:form>
	<c:set var="inputDisplay" value="${showEdit}" />
	<c:choose>
		<c:when test="${inputDisplay == false}">
			<div style="color: blue; font-size:24px;">${successED}</div>
			<table border="1">
				<tr>
					<th>Personal Numerical Code</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>ID Card Number</th>
					<th>Username</th>
					<th>Role</th>
				</tr>
				<c:forEach items="${employeeList}" var="employeel">
					<tr>
						<td style="width: 151px;">${employeel.employeePersonalNumericalCode}</td>
						<td style="width: 151px;">${employeel.employeeFirstName}</td>
						<td style="width: 151px;">${employeel.employeeLastName}</td>
						<td style="width: 151px;">${employeel.employeeIdCardNumber}</td>
						<td style="width: 151px;">${employeel.username}</td>
						<td style="width: 151px;">${employeel.employeeRole}</td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
			<form:form id="formEdit" action="employee.do" method="post"
				modelAttribute="employee">
				<div style="color: red; font-size:24px;">${failED}</div>
				<br />
				<br />
				<table border="1">
					<tr>
						<th>Personal Numerical Code</th>
						<th>First Name</th>
						<th>Last Name</th>
						<th>ID Card Number</th>
						<th>Username</th>
						<th>Password</th>
						<th>Actions</th>
					</tr>
					<tr>
						<td class="cell"><form:input
								path="employeePersonalNumericalCode" readonly="true"
								value="${employeel.employeePersonalNumericalCode}"
								cssStyle="border:0px; cursor:default;" /></td>
						<td style="width: 151px;"><form:input
								name="employeeFirstName" path="employeeFirstName"
								value="${employeel.employeeFirstName}" />
							<div id="fnameError" class="error"></div></td>
						<td style="width: 151px;"><form:input name="employeeLastName"
								path="employeeLastName" value="${employeel.employeeLastName}" />
							<div id="lnameError" class="error"></div></td>
						<td style="width: 151px;"><form:input
								name="employeeIdCardNumber" path="employeeIdCardNumber"
								value="${employeel.employeeIdCardNumber}" />
							<div id="idcardError" class="error"></div></td>
						<td style="width: 151px;"><form:input name="username"
								path="username" value="${employeel.username}" />
							<div id="usernameError" class="error">${usernameError}</div></td>
						<td style="width: 151px;"><form:input name="password"
								path="password" />
							<div id="passwordError" class="error"></div></td>
						<td><input class="btn" type="submit" name="action"
							value="Edit" /><input class="btn" type="submit" name="action"
							value="Delete" /></td>
					</tr>
				</table>
			</form:form>
		</c:otherwise>
	</c:choose>
</body>
</html>