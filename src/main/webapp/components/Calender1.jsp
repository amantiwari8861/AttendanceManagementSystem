<%@page import="com.nobious.bean.Attendance"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.nobious.dao.impl.ConnectionProvider"%>
<%
    
    	if(session.getAttribute("role") != "User"){
    		response.sendRedirect("../index.jsp");
    	}
%>
<style>
<jsp:include page="../css/bootstrap_4.1.3.min.css"/>
<jsp:include page="../css/Calender1.css"/>
<jsp:include page="../css/datepicker.css"/>
<jsp:include page="../css/fontawesome.min.css"/>
</style>

<link rel="stylesheet" href="https://maxcdn.icons8.com/fonts/line-awesome/1.1/css/line-awesome-font-awesome.min.css">

 <div class="marks d-flex flex-row-reverse m-2"> <a href="../markattendance.jsp"><button class="btn btn-success">Mark Attendance</button></a></div>
 <div class="container">
 <div class="p-5">
  <h2 class="mb-4">Calendar</h2>
  <div class="card">
    <div class="card-body p-0">
      <div id="calendar"></div>
    </div>
  </div>
</div>

<!-- calendar modal -->
<div id="modal-view-event" class="modal modal-top fade calendar-modal">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-body">
					<h4 class="modal-title"><span class="event-icon"></span><span class="event-title"></span></h4>
					<div class="event-body"></div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
<%
Attendance att=null;
	try{
		
	//String leaveType=request.getParameter("ltype");
	Connection con=ConnectionProvider.getConnection();
	PreparedStatement ps=con.prepareStatement("select * from leave_details where username=?");
	ps.setString(1,(String) session.getAttribute("uname"));
	ResultSet rs=ps.executeQuery();
	rs.next();
	
	att=new Attendance(rs.getInt(1),rs.getString(2),null,rs.getInt(3),rs.getInt(4),rs.getInt(5));
	}
catch(Exception e)
{
	out.print("login krle bsdk");
	e.printStackTrace();
}

%>
<div id="modal-view-event-add" class="modal modal-top fade calendar-modal">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <form id="add-event" action="../requestleave.jsp" method="get">
        <div class="modal-body">
        <input type="hidden" id="current_date" name="current_date">
        <h4>Request for leave</h4>             
          <div class="form-group">
            <label for="ltype">Leave type </label>   <br>         
            <select id="ltype" name="ltype">
	            <option value="Casual Leave">Casual (<%=att.getCasualLeave() %>)</option>
	            <option value="Paid Leave">Paid (<%=att.getEarnedLeave() %>)</option>
	            <option value="Sick Leave">Sick (<%=att.getSickLeave() %>)</option>
            </select>
            
          </div>
          <div class="form-group">
            <label>Event Color</label>
            <select class="form-control" name="ecolor">
              <option value="fc-bg-default">fc-bg-default</option>
              <option value="fc-bg-blue">fc-bg-blue</option>
              <option value="fc-bg-lightgreen">fc-bg-lightgreen</option>
              <option value="fc-bg-pinkred">fc-bg-pinkred</option>
              <option value="fc-bg-deepskyblue">fc-bg-deepskyblue</option>
            </select>
          </div>
          <div class="form-group">
            <label>Event Icon</label>
            <select class="form-control" name="eicon">
              <option value="circle">circle</option>
              <option value="cog">cog</option>
              <option value="group">group</option>
              <option value="suitcase">suitcase</option>
              <option value="calendar">calendar</option>
            </select>
          </div>        
      </div>
        <div class="modal-footer">
        <button type="submit" class="btn btn-primary" >Save</button>
        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>        
      </div>
      </form>
    </div>
  </div>
</div>
</div>
<script>
<jsp:include page="../js/jquery3.1.min.js"/>
<jsp:include page="../js/popper.min.js"/>
<jsp:include page="../js/bootstrap4.min.js"/>
<jsp:include page="../js/moment.js"/>
<jsp:include page="../js/fullcalender.min.js" />
<jsp:include page="../js/datepicker.js"/>
<jsp:include page="../js/datepicker.en.js"/>
<jsp:include page="../js/Calender1.js" />
</script>