package com.nobious.dao;

import java.util.ArrayList;

import com.nobious.bean.Attendance;
import com.nobious.bean.Leave;
import com.nobious.bean.User;

public interface AdminDao 
{
	boolean addUser(User user);
	boolean validateUser(String uname,String pass);
	boolean updateUser(User newuser);
	boolean deleteUser(int id);
	ArrayList<User> getAllUsers();
	ArrayList<Attendance> getAllAttendance();
	ArrayList<Object[]> getAllLeaveRequest();
	boolean approveRequest(String username,String date);
	boolean disApproveRequest(String username,String date);
	ArrayList<Leave> getAllLeaveDetails();
	ArrayList<Leave> getLeaveDetails(String username);
	
}