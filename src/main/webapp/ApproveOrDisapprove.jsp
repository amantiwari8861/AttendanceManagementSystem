<%@page import="com.nobious.dao.impl.ConnectionProvider"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.nobious.dao.impl.AdminDaoImpl"%>
<%@page import="com.nobious.dao.AdminDao"%>
<%
String leaveType=request.getParameter("ltype");
Connection con=ConnectionProvider.getConnection();
PreparedStatement ps=null;
AdminDao dao=new AdminDaoImpl();
if(request.getParameter("status").equals("approve"))
{
	dao.approveRequest(request.getParameter("req_id"),request.getParameter("username"));
	if(leaveType.equalsIgnoreCase("Sick Leave"))
	{
	ps=con.prepareStatement("update leave_details set sick_leave=(select sick_leave from leave_details where username=? )-1  where username=?;");
	}
	else if(leaveType.equalsIgnoreCase("Paid Leave"))
	{
		ps=con.prepareStatement("update leave_details set paid_leave=(select paid_leave from leave_details where username=? )-1 where username=?;");
		
	}
	else
	{
		
	ps=con.prepareStatement("update leave_details set casual_leave=(select casual_leave from leave_details where username=?  )-1 where username=?;");
	}
}
else
{
	dao.disApproveRequest(request.getParameter("req_id"),request.getParameter("username"));
	if(leaveType.equalsIgnoreCase("Sick Leave"))
	{
	ps=con.prepareStatement("update leave_details set sick_leave=(select sick_leave from leave_details where username=? )+1  where username=?;");
	}
	else if(leaveType.equalsIgnoreCase("Paid Leave"))
	{
		ps=con.prepareStatement("update leave_details set paid_leave=(select paid_leave from leave_details where username=? )+1 where username=?;");
		
	}
	else
	{
		
	ps=con.prepareStatement("set @cas=(select casual_leave from leave_details where username=?  ) ;update leave_details set casual_leave=@cas+1 where username=?;");
	}
}

ps.setString(1,(String) session.getAttribute("uname"));
ps.setString(2,(String) session.getAttribute("uname"));
	int status=ps.executeUpdate();
	con.close();
	if(status>0)
	{
		out.print("Work done!!");
	}
%>
<jsp:include page="Approveleave.jsp"/>