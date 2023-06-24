	<%
	out.print("<span style='color:red;'> Logout Successfully</span>");
	request.getSession(false);
	if(session != null)
	{
		session.invalidate();
		request.getRequestDispatcher("index.jsp").include(request, response);
	}
	
	
	%>