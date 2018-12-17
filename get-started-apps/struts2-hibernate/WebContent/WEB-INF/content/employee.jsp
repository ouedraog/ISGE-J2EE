<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE>
<html>
<head>
<title>Struts 2 and Hibernate Integration Example</title>
</head>
<body>
	<!-- registering employees -->
	<h1>Struts 2 and Hibernate Integration Example</h1>
	<s:form action="registerEmployee" method="POST">
		<s:textfield label="Emp Name" name="empName"></s:textfield>
		<s:textfield label="Emp Email" name="empEmail"></s:textfield>
		<s:textfield label="Emp Dept" name="empDept"></s:textfield>
		<s:textfield label="Emp Salary" name="empSalary"></s:textfield>
		<s:submit value="Submit"></s:submit>
	</s:form>
	<!-- listing employees -->
	<s:if test="employeeList.size() > 0">
		<h2>List of All Employees</h2>
		<table border="1px" cellpadding="8px">
			<tr>
				<th>Emp Id</th>
				<th>Name</th>
				<th>Email</th>
				<th>Department</th>
				<th>Salary</th>
			</tr>
			<s:iterator value="employeeList">
				<tr>
					<td><s:property value="empId" /></td>
					<td><s:property value="empName" /></td>
					<td><s:property value="empEmail" /></td>
					<td><s:property value="empDept" /></td>
					<td><s:property value="empSalary" /></td>
				</tr>
			</s:iterator>
		</table>
	</s:if>
	<s:else>
		<p> No employee found </p>
	</s:else>
</body>
</html>