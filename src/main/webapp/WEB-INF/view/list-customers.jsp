<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer List</title>
<link type="text/css" 
	rel="stylesheet" 
	href= "${pageContext.request.contextPath}/resources/css/style.css" />
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h3>Customer List</h3>
		</div>
	</div>
	
	<div id="container">
		<div id="content">
			<input type="button" value="Add new Customer"
					onclick="window.location.href='showFormForAdd';return false;"
					class="add button"
			/>
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
				<c:forEach var="temp" items="${customers}">
				
				<!-- construct an update link with customer id -->
				<c:url var="updateLink" value="/customers/showFormForUpdate">
					<c:param name="id" value="${temp.id }"/>
				</c:url>
				<!-- construct an delete link with customer id -->
				<c:url var="deleteLink" value="/customers/delete">
					<c:param name="id" value="${temp.id }"/>
				</c:url>
				<tr>
					<td>${temp.firstName }</td>
					<td>${temp.lastName }</td>
					<td>${temp.email }</td>
					<td>
						<!--  display the update link -->
						<a href="${updateLink }">Update</a>
						|
						<a href="${deleteLink }"
						onclick="if(!(confirm('Are you sure you want to delete this customer?')))return false">Delete</a>
					</td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	
</body>
</html>