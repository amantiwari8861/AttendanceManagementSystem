package com.nobious.dao;

import java.util.ArrayList;

import com.nobious.bean.Attendance;

public interface UserDao {
	

	ArrayList<Attendance> getAllAttendance();
	Attendance getAttendanceByUsername(String username);
	
}
