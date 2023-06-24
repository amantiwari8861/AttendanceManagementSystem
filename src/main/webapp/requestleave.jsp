<%@page import="com.nobious.util.DateTimeFomatter"%>
<%@page import="com.nobious.bean.Attendance"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.nobious.dao.impl.ConnectionProvider"%>
<%

String leaveType=request.getParameter("ltype");
String selectedDate=request.getParameter("current_date");
System.out.println(selectedDate);
Connection con=ConnectionProvider.getConnection();
PreparedStatement ps=null;

ps=con.prepareStatement("insert into leave_request(username,ldate,leave_type) values(?,?,?);");
if(leaveType.equals("Paid Leave"))
{
	ps.setString(3, leaveType);
}
else if(leaveType.equals("Sick Leave"))
{
	ps.setString(3, leaveType);
}
else
{
	ps.setString(3, leaveType);
}
ps.setString(1,(String) session.getAttribute("uname"));
ps.setString(2,selectedDate);
int status=ps.executeUpdate();

	if(status>0)
	{
		out.print("applied for leave successfully!!");
	}
	else
	{
		out.print("unable to apply leave !!");
	}

%>
<jsp:include page="./components/Calender1.jsp"></jsp:include>