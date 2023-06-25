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
			PreparedStatement ps = con.prepareStatement("insert into users(name,email,phoneno,password) values(?,?,?,?);");
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setLong(3, user.getPhoneno());
			ps.setString(4, user.getPassword());
			int status = ps.executeUpdate();

			PreparedStatement ps3 = con.prepareStatement("insert into login_details(username,role,password) values(?,'User',?);");
			ps3.setString(1, user.getName());
			ps3.setString(2, user.getPassword());
			int status3=ps3.executeUpdate();

			PreparedStatement ps2 = con.prepareStatement("insert into leave_details(username) values(?);");
			ps2.setString(1, user.getName());
			int status2 = ps2.executeUpdate();

			con.close();
			
			return (status>0 && status2 >0 && status3>0) ?true:false;
		} 
		catch (SQLException e) 
		{
			System.out.println("unable to insert data or initialize the leave details table !");
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean updateUser(User newuser) {

		try 
		{
			PreparedStatement ps = con.prepareStatement("update users set name=?,email=?,password=?,phoneno=?,date_updated=current_timestamp() where user_id=?;");
			ps.setString(1, newuser.getName());
			ps.setString(2, newuser.getEmail());
			ps.setString(3, newuser.getPassword());
			ps.setLong(4, newuser.getPhoneno());
			ps.setInt(5, newuser.getUserId());
			
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
				User user=new User(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getLong(4),rs.getString(5));
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
	public String validateUser(String uname, String pass) 
	{
		try 
		{
			PreparedStatement ps = con.prepareStatement("select * from login_details where username=? and password=?");
			ps.setString(1, uname);
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			String username=rs.getString(1);
			String role=rs.getString(3);
			con.close();
			if(username!=null)
				return role;
							
		} catch (SQLException e) 
		{
//			e.printStackTrace();
			return null;
		}
		return null;
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
//	@Override
//	public boolean approveRequest(String username, String date) throws SQLException 
//	{
//			PreparedStatement ps = con.prepareStatement("update leave_request set is_approved=true where username=? and ldate=?;");
//			ps.setString(1, username);
//			ps.setString(2, date);
//			
//			boolean status=(ps.executeUpdate()>0) ? true :false;
//			con.close();
//			return status;
//	}
//	@Override
//	public boolean disApproveRequest(String username, String date) throws SQLException 
//	{
//		System.out.println("disapproved!");
//
//			PreparedStatement ps = con.prepareStatement("update leave_request set is_approved=false where username=? and ldate=?;");
//			ps.setString(1, username);
//			ps.setString(2, date);
//			
//			boolean status= (ps.executeUpdate()>0) ? true :false;
//			con.close();
//			return status;
//		
//	}
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
				Leave lea=new Leave(rs.getInt(1), rs.getString(2),rs.getInt(3),rs.getInt(4), rs.getInt(5));
				System.out.println(lea);
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
	public boolean approveLeaveTransaction(String username,String leaveType,String date)
	{
		try
		{
			con.setAutoCommit(false);
			int rowAffected1=0,rowAffected2=0,leaveCount=0;
				PreparedStatement ps = con.prepareStatement("update leave_request set is_approved=true where username=? and ldate=?;");
				ps.setString(1, username);
				ps.setString(2, date);
				
				rowAffected1=ps.executeUpdate();
				if(rowAffected1==1)
				{
					PreparedStatement ps2=null;
						
					if(leaveType.equalsIgnoreCase("casual leave"))
					{
						PreparedStatement ps3=con.prepareStatement("select casual_leave from leave_details where username=?;");
						ps3.setString(1, username);
						ResultSet rs = ps3.executeQuery();
						rs.next();
						leaveCount=rs.getInt(1);
						ps3.close();
						rs.close();
						if(leaveCount>0)
						{
							ps2=con.prepareStatement("update leave_details set casual_leave=?-1 where username=?;");
						}
						else
						{
							System.out.println("No leaves left");
						}
						
					}
					else if(leaveType.equalsIgnoreCase("sick leave"))
					{
						PreparedStatement ps3=con.prepareStatement("select sick_leave from leave_details where username=?;");
						ps3.setString(1, username);
						ResultSet rs = ps3.executeQuery();
						rs.next();
						leaveCount=rs.getInt(1);
						ps3.close();
						rs.close();
						if(leaveCount>0)
						{
							ps2=con.prepareStatement("update leave_details set sick_leave=?-1 where username=?;");
						}
						else
						{
							System.out.println("No leaves left");
						}
					}
					else if(leaveType.equalsIgnoreCase("paid leave"))
					{
						PreparedStatement ps3=con.prepareStatement("select earned_leave from leave_details where username=?;");
						ps3.setString(1, username);
						ResultSet rs = ps3.executeQuery();
						rs.next();
						leaveCount=rs.getInt(1);
						ps3.close();
						rs.close();
						if(leaveCount>0)
						{
							ps2=con.prepareStatement("update leave_details set earned_leave=?-1 where username=?;");
						}
						else
						{
							System.out.println("No leaves left");
						}
					}
					ps2.setInt(1, leaveCount);
					ps2.setString(2, username);
					rowAffected2 = ps2.executeUpdate();
					ps2.close();
				}
				if(rowAffected1==rowAffected2)
				{					
					con.commit();
					con.setAutoCommit(true);
					return true;
				}
				else
				{
					con.rollback();
					con.setAutoCommit(true);
					return false;
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean disApproveLeaveTransaction(String username,String leaveType,String date)
	{
		try
		{
			con.setAutoCommit(false);
			int rowAffected1=0,rowAffected2=0,leaveCount=0;
				PreparedStatement ps = con.prepareStatement("update leave_request set is_approved=false where username=? and ldate=?;");
				ps.setString(1, username);
				ps.setString(2, date);
				
				rowAffected1=ps.executeUpdate();
				if(rowAffected1==1)
				{
					PreparedStatement ps2=null;
						
					if(leaveType.equalsIgnoreCase("casual leave"))
					{
						PreparedStatement ps3=con.prepareStatement("select casual_leave from leave_details where username=?;");
						ps3.setString(1, username);
						ResultSet rs = ps3.executeQuery();
						rs.next();
						leaveCount=rs.getInt(1);
						ps3.close();
						rs.close();
						if(leaveCount<10)
						{
							ps2=con.prepareStatement("update leave_details set casual_leave=?+1 where username=?;");
						}
						else
						{
							System.out.println("No leaves left");
						}
						
					}
					else if(leaveType.equalsIgnoreCase("sick leave"))
					{
						PreparedStatement ps3=con.prepareStatement("select sick_leave from leave_details where username=?;");
						ps3.setString(1, username);
						ResultSet rs = ps3.executeQuery();
						rs.next();
						leaveCount=rs.getInt(1);
						ps3.close();
						rs.close();
						if(leaveCount<15)
						{
							ps2=con.prepareStatement("update leave_details set sick_leave=?+1 where username=?;");
						}
						else
						{
							System.out.println("No leaves left");
						}
					}
					else if(leaveType.equalsIgnoreCase("paid leave"))
					{
						PreparedStatement ps3=con.prepareStatement("select earned_leave from leave_details where username=?;");
						ps3.setString(1, username);
						ResultSet rs = ps3.executeQuery();
						rs.next();
						leaveCount=rs.getInt(1);
						ps3.close();
						rs.close();
						if(leaveCount<15)
						{
							ps2=con.prepareStatement("update leave_details set earned_leave=?+1 where username=?;");
						}
						else
						{
							System.out.println("No leaves left");
						}
					}
					ps2.setInt(1, leaveCount);
					ps2.setString(2, username);
					rowAffected2 = ps2.executeUpdate();
					ps2.close();
				}
				if(rowAffected1==rowAffected2)
				{					
					con.commit();
					con.setAutoCommit(true);
					return true;
				}
				else
				{
					con.rollback();
					con.setAutoCommit(true);
					return false;
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return false;
	}

}
