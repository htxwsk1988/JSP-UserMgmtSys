<%@ page language="java" 
		contentType="text/html; charset=ISO-8859-1"
    	import="java.util.*, java.sql.*, com.cc.cad.model.*, com.cc.cad.controller.*" 
    	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
	function alertDel(){
		return window.confirm("Continue delete.");
	}
</script>
</head>
<body bgcolor="pink">
	<%	
			String u=null; 
			if(session.getAttribute("seName")!=null){
				u= (String)session.getAttribute("seName"); 
			}
			if(u==null){
				response.sendRedirect("Login.jsp?errCode=1");
				return;
			}
	%>
	Welcome! <%=u %><br>
	<hr>
	<center>
		<h2>USER INFO</h2>
		<%
			// Return users by paging. 	
			ArrayList<UserBean> lstUser = (ArrayList<UserBean>)request.getAttribute("UserList"); 
			
			%>
			<table border="1">
				<tr bgcolor="silver">
					<td>USERID</td>
					<td>USERNAME</td>
					<td>PASSWORD</td>
					<td>EMAIL</td>
					<td>GROUP</td>
					<td>EDIT</td>
					<td>DELETE</td>
				</tr>
				<% 
					for(int i=0; i<lstUser.size(); i++){
						
						UserBean user = lstUser.get(i); 
						%>
						<tr>
							<td><%=user.getUserId() %></td>
							<td><%=user.getUserName() %></td>
							<td><%=user.getPasswd() %></td>
							<td><%=user.getEmail() %></td>
							<td><%=user.getGroup() %></td>
							<td><a href="EditUser.jsp?uId=<%=user.getUserId() %>&uName=<%=user.getUserName()%>&uPass=<%=user.getPasswd() %>&uEmail=<%=user.getEmail() %>&uGroup=<%=user.getGroup() %>">EDIT</a></td>
							<td><a onclick="return alertDel();" href="UserServlet?Operate=delete&userId=<%=user.getUserId()%>">DELETE</a></td>
						</tr>
						<% 
					}
				%>
			</table>
			<%	
				int pageCount = (int)request.getAttribute("PageCount");
				int pageNow = (int)request.getAttribute("PageNow"); 	
			
				if(pageNow!=1){
					out.println("<a href=UserServlet?pageNow="+(pageNow-1)+"&Operate=paging>Previous</a>"); 
				}

			
				for(int i=1; i<=pageCount; i++){
					out.println("<a href=UserServlet?pageNow="+i+"&Operate=paging>["+i+"]</a>");
				}
				
				if(pageNow!=pageCount){
					out.println("<a href=UserServlet?pageNow="+ (pageNow+1)+"&Operate=paging>Next</a>"); 
				}
	
		%>
		<hr>
		<a href="Login.jsp">Return to Login Page</a>
	</center>
</body>
</html>