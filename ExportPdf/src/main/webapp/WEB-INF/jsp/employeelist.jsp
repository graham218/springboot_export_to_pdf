<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<h1 align="center">Employee List</h1>
<hr>

<a href="/exportpdf">Export PDF</a>

<table border="1" align="center">
	<tr>
		<th>Employee Name</th>
		<th>Address</th>
		<th>Email</th>
	</tr>
	<c:forEach items="${employees}" var="employee">
		<tr>
			<td>${employee.emp_name}</td>
			<td>${employee.emp_address}</td>
			<td>${employee.emp_email}</td>
		</tr>
	</c:forEach>
</table>