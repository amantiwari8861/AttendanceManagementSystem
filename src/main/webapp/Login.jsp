<%@page import="com.nobious.dao.LoginDao"%>
<%@page import="java.io.IOException"%>
<%@page import="com.nobious.bean.User"%>
<%@page import="com.nobious.bean.LoginBean"%>
<%@page import="com.nobious.dao.impl.AdminDaoImpl"%>
<%@page import="com.nobious.dao.AdminDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<%@include file="components/allcsscdn.jsp"%>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
	integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
	crossorigin="anonymous"></script>
</head>
<body>

	<%
		String uname=request.getParameter("uname1");
		String pass=request.getParameter("pass1");
		LoginBean loginbean=new LoginBean();
		loginbean.setUserName(uname);
		loginbean.setPassword(pass);
		AdminDao dao=new AdminDaoImpl();
		

		try
		
		    {
		
		        String Validateuser = LoginDao.authenticateUser(loginbean);
		
		 
			        if(Validateuser.equals("Admin_Role"))
		
		        {
		
		          session.setAttribute("uname",uname);
				session.setAttribute("role","Admin");
		            session.setAttribute("Admin", uname); //setting session attribute
		
		            request.setAttribute("userName", uname);
		
		 
		
		            request.getRequestDispatcher("AWelcome.jsp").forward(request, response);
		
		        }
		
		        
		
		        else if(Validateuser.equals("User_Role"))
		
		        {
			          session.setAttribute("uname",uname);
						session.setAttribute("role","User");
		
		            System.out.println("User's Home");
		
		 
		
		            session.setMaxInactiveInterval(10*60);
			            session.setAttribute("User", uname);
		
		            request.setAttribute("userName", uname);
		
		 
		
		            request.getRequestDispatcher("UWelcome.jsp").forward(request, response);
		
		        }
		
		        else
		
		        {
		
		            System.out.println("Error message = "+Validateuser);
			            request.setAttribute("errMessage", Validateuser);
		
		 
	
		            //request.getRequestDispatcher("Login.jsp").forward(request, response);
		
		        }
	
		    }
		
		    catch (IOException e1)
		
		    {
		
		        e1.printStackTrace();
		
		    }
		
		    catch (Exception e2)
		
		    {
		
		        e2.printStackTrace();
		
		    }
		
			
	%>









</body>
</html>













