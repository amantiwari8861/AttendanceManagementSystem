<%@page import="com.nobious.dao.impl.AdminDaoImpl"%>
<%@page import="com.nobious.dao.AdminDao"%>
<%@page import="com.nobious.bean.Attendance"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.nobious.dao.impl.ConnectionProvider"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>All Employees</title>
<!-- <link rel="stylesheet" type="text/css"	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/css/bootstrap.min.css" /> -->
<link rel="stylesheet" type="text/css"	href="https://cdn.datatables.net/v/bs4-4.6.0/dt-1.12.1/datatables.min.css" />
<%@include file="components/allcsscdn.jsp"%>
<%
    
    	if(session.getAttribute("role") != "Admin"){
    		response.sendRedirect("index.jsp");
    	}
    %>
</head>
<body  background="Images/Admin.jpg">
<%@include file="components/navbar.jsp" %>
	<h1>
		<i><center>All Employees Attendance</center></i>
	</h1>
	<div class="container">
		<br> <br>
		<table id="mytable" class="table table-striped" style="width: 100%">
			<% AdminDao dao=new AdminDaoImpl();
   			 ArrayList<Attendance> list=dao.getAllAttendance();%>

			<thead>
				<tr>
					<th>Attendance ID</th>
					<th>User Name</th>
					<th>Date</th>
					

				</tr>
			</thead>
			<tbody>
				<% for(Attendance att:list)
        	{
        %>
				<tr>
					<td><%=att.getAttendanceId()%></td>
					<td><%=att.getUsername()%></td>
					<td><%=att.getDate()%></td>
					
					

				</tr>
				<%}%>
			</tbody>
			<tfoot>
				<tr>
						    <th>Attendance ID</th>
							<th>User Name</th>
							<th>Date</th>
					
				</tr>
			</tfoot>
		</table>

		

	</div>
	
</body>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
<script type="text/javascript"	src="https://cdn.datatables.net/v/bs4-4.6.0/dt-1.12.1/datatables.min.js"></script>
<!-- <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.min.js"></script> -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>


<script>
    $(document).ready(function() {
        $('#mytable').DataTable();
    } );

</script>
</html>