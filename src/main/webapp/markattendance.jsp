
<%@page import="com.nobious.util.DateTimeFomatter"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.nobious.dao.impl.ConnectionProvider"%>
<%
	Connection con=ConnectionProvider.getConnection();
	Statement st=con.createStatement();
	System.out.print(session.getAttribute("uname"));
 	st.executeUpdate("insert into attendance(username,Adate) values('"+session.getAttribute("uname")+"','"+DateTimeFomatter.getCurrentTimeStamp()+"');");
	con.close();
	System.out.print("attendance marked");
	out.print("Attendance Marked Succesfully!!!");
%>
<jsp:include page="components/Calender1.jsp"/>