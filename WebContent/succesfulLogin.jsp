<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>You have successfully logged in</title>
</head>
<body>
	<table>
		<tr>
			<td>User name: <%=(String)request.getParameter("username")%></td>
		</tr>
		<tr>
			<td>Password: <%=(String)request.getParameter("password")%></td>
		</tr>
	</table>
</body>
</html>