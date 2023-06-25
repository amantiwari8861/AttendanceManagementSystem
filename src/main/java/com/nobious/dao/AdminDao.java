package com.nobious.dao;

import java.util.ArrayList;
import com.nobious.bean.Attendance;
import com.nobious.bean.Leave;
import com.nobious.bean.User;

public interface AdminDao 
{
	boolean addUser(User user);
	String validateUser(String uname,String pass);
	boolean updateUser(User newuser);
	boolean deleteUser(int id);
	ArrayList<User> getAllUsers();
	ArrayList<Attendance> getAllAttendance();
	ArrayList<Object[]> getAllLeaveRequest();
	ArrayList<Leave> getAllLeaveDetails();
	ArrayList<Leave> getLeaveDetails(String username);
	boolean approveLeaveTransaction(String username,String leaveType,String date);
	boolean disApproveLeaveTransaction(String username,String leaveType,String date);

	
}