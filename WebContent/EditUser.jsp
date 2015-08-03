<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="pink">
	<center>
		<h3>Update User Detail</h3>
		<hr>
		<form action="UserServlet?Operate=update" method="post">
			<table border="1">
				<tr bgcolor="lime"><td>USERID</td><td><input type="text" readonly="readonly" name="txtId" value="<%=request.getParameter("uId") %>"/></td></tr>
				<tr bgcolor="silver"><td>USERNAME</td><td><input type="text" name="txtName" value="<%=request.getParameter("uName") %>"/></td></tr>
				<tr bgcolor="lime"><td>PASSWORD</td><td><input type="text" name="txtPass" value="<%=request.getParameter("uPass") %>" /></td></tr>
				<tr bgcolor="silver"><td>EMAIL</td><td><input type="text" name="txtEmail" value="<%=request.getParameter("uEmail") %>" /></td></tr>
				<tr bgcolor="lime"><td>GROUP</td><td><input type="text" name="txtGroup" value="<%=request.getParameter("uGroup") %>" /></td></tr>
			</table>
			<input type="submit" value="Update"/>&nbsp;&nbsp;<input type="reset" value="Reset"/>
		</form>
		
	</center>
</body>
</html>