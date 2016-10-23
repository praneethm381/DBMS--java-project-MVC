<%@page import="studentController.*"%>
<%@page import="studentModel.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>Students details</title>

<link rel="stylesheet" type="text/css" href="style.css">
</head>

<body>
<div id="header">
		<h1>Database Application</h1>
	</div>
	<div id="nav">
		<br> <br> <br>
		<center>
				<form action="FetchPage.html">
					<table align="center">
						<tr>
							<td><input type="submit" value="Fetch students"><br></td>
						</tr>
					</table>
				</form>

				<form action="Metadata.html">
					<table align="center">
						<tr>
							<td><input type="submit" value="Get metadata"><br></td>
						</tr>
					</table>
				</form>
		</center>
	</div>
	<div id="section">
	<center>
		<% StudentBean sb = new StudentBean(); 
			ArrayList<StudentBean> al = (ArrayList<StudentBean>) session
					.getAttribute("al");
		%>
		<br><br>
<h3 align="center"> Students details with department as  " <%out.println(al.get(0).getDept_name());%>" and total-credits as " <%out.println(al.get(0).getTotal_credits());%>"</h3>
	
	<br><table align="center" border = "1px solid black" border-collapse = "collapse" width="500">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Department-Name</th>
			<th>Total-credits</th>
		</tr>

		<%
			for (StudentBean i : al) {
		%>
		<tr>
			<td>
				<%
					out.println(i.getId());
				%>
			</td>
			<td>
				<%
					out.println(i.getName());
				%>
			</td>
			<td>
				<%
					out.println(i.getDept_name());
				%>
			</td>
			<td>
				<%
					out.println(i.getTotal_credits());
				%>
			</td>
		</tr>
		<%
			}
		%>
	</table>
	</center>
	</div>
	
	<div id="footer"><h3>Copyright © praneeth</h3></div>

</body>
</html>
