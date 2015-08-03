<%@ page language="java" 
	contentType="text/html; charset=ISO-8859-1"
	import="com.cc.cad.controller.*" 
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="pink">
	<center>
	
	<% 
		String error = request.getParameter("errCode"); 
		
		if(error!=null){
			if(error.equals("1")){
				out.println("<font color=red> Invalid Login. Please login!</font><br>"); 
			}
		}
	%>
		<h3>User Login</h3>
		<hr>
		<form action="LoginServlet" method="post">
			<table>
				<tr>
					<td>User Name:</td>
					<td><input type="text" size="20" name="username"></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" size="20" name="passwd"></td>
				</tr>
			</table>
			<br>
			<input type="submit" value="Sign In">
			<input type="reset" value="Reset">		
		</form>
	</center>
</body>
</html>