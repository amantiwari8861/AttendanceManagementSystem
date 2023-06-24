package com.nobious.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.nobious.bean.Attendance;
import com.nobious.bean.Leave;
import com.nobious.bean.User;
import com.nobious.dao.AdminDao;

public class AdminDaoImpl implements AdminDao
{
	private Connection con=null;
	public AdminDaoImpl() 
	{
		 con = ConnectionProvider.getConnection();
	}
	public boolean addUser(User user)
	{
		try 
		{
			PreparedStatement ps = con.prepareStatement("insert into users(user_id,name,email,phoneno) values(?,?,?,?);");
			ps.setInt(1, user.getUserId());
			ps.setString(2, user.getName());
			ps.setString(3, user.getEmail());
			ps.setLong(4, user.getPhoneno());

			
			int status = ps.executeUpdate();
			con.close();
			return status>0?true:false;
		} 
		catch (SQLException e) 
		{
			System.out.println("unable to insert data!");
			e.printStackTrace();
		}
		return false;
	}

	
	@Override
	public boolean updateUser(User newuser) {

		try 
		{
			PreparedStatement ps = con.prepareStatement("update users set name=?,email=?,phoneno=?,date_updated=current_timestamp() where user_id=?;");
			ps.setString(1, newuser.getName());
			ps.setString(2, newuser.getEmail());
			ps.setLong(3, newuser.getPhoneno());
			ps.setInt(4, newuser.getUserId());
			
			boolean status=(ps.executeUpdate()>0) ? true :false;
			con.close();
			return status;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean deleteUser(int id) {

		try 
		{
			PreparedStatement ps = con.prepareStatement("delete from users where user_id=?;");
			ps.setInt(1, id);
			boolean status= (ps.executeUpdate()>0) ? true :false;
			con.close();
			return status;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return false;
	}

	
	@Override
	public ArrayList<User> getAllUsers() 
	{
		
		try 
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from users;");
			ArrayList<User> list=new ArrayList<>();
			while(rs.next())
			{
				User user=new User(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getLong(4));
				list.add(user);
			}
			con.close();
			return list;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		
		return null;
	}
	@Override
	public boolean validateUser(String uname, String pass) 
	{
		try 
		{
			PreparedStatement ps = con.prepareStatement("select count(*) from alogin where uname1=? and pass1=?");
			ps.setString(1, uname);
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();
			
			
			rs.next();
			int status=rs.getInt(1);
			con.close();
			return status>0 ? true :false;
							
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	return false;
	
	}
	@Override
	public ArrayList<Attendance> getAllAttendance() {
		try 
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from attendance;");
			ArrayList<Attendance> list=new ArrayList<>();
			while(rs.next())
			{
				Attendance att=new Attendance(rs.getInt(1), rs.getString(2),rs.getString(3));
				list.add(att);
			}
			con.close();
			return list;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return null;
	}
	@Override
	public ArrayList<Object[]> getAllLeaveRequest() {

		try 
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from leave_request;");
			ArrayList<Object[]> list=new ArrayList<>();
			while(rs.next())
			{
				Object att[]= {rs.getInt(1), rs.getString(2),rs.getDate(3),rs.getBoolean(4), rs.getString(5)};
				list.add(att);
			}
			con.close();
			return list;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return null;
	}
	@Override
	public boolean approveRequest(String username, String date) {
		System.out.println("approved!");
		try 
		{
			PreparedStatement ps = con.prepareStatement("update leave_request set is_approved=true where username=? and ldate=?;");
			ps.setString(1, username);
			ps.setString(2, date);
			
			boolean status=(ps.executeUpdate()>0) ? true :false;
			con.close();
			return status;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		
		return false;
	}
	@Override
	public boolean disApproveRequest(String username, String date) {
		System.out.println("disapproved!");

		try 
		{
			PreparedStatement ps = con.prepareStatement("update leave_request set is_approved=false where username=? and ldate=?;");
			ps.setString(1, username);
			ps.setString(2, date);
			
			boolean status= (ps.executeUpdate()>0) ? true :false;
			con.close();
			return status;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return false;
	}
	@Override
	public ArrayList<Leave> getAllLeaveDetails() {
		try 
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from leave_details;");
			ArrayList<Leave> list=new ArrayList<>();
			while(rs.next())
			{
				Leave lea=new Leave(rs.getInt(1), rs.getString(2),rs.getInt(3),rs.getInt(4), rs.getInt(5));
				list.add(lea);
			}
			con.close();
			return list;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return null;
	}
	@Override
	public ArrayList<Leave> getLeaveDetails(String username) {
		try 
		{
			PreparedStatement ps = con.prepareStatement("select * from leave_details where username=?;");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			ArrayList<Leave> list=new ArrayList<>();
			while(rs.next())
			{
				Leave lea=new Leave(rs.getInt(1), rs.getString(2),rs.getInt(4),rs.getInt(5), rs.getInt(6));
				list.add(lea);
			}
			con.close();
			return list;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
}
