package com.nobious.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.nobious.bean.LoginBean;
import com.nobious.dao.impl.*;

public class LoginDao {

	public static String authenticateUser(LoginBean loginBean)

	{

		String userName = loginBean.getUserName();

		String password = loginBean.getPassword();

		Connection con = null;

		Statement statement = null;
		ResultSet resultSet = null;

		String userNameDB = "";

		String passwordDB = "";

		String roleDB = "";

		try

		{

			con = ConnectionProvider.getConnection();

			statement = con.createStatement();

			resultSet = statement.executeQuery("select username,password,role from user");

			while (resultSet.next())

			{

				userNameDB = resultSet.getString("username");

				passwordDB = resultSet.getString("password");

				roleDB = resultSet.getString("role");

				if (userName.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals("Admin"))

					return "Admin_Role";

				else if (userName.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals("User"))

					return "User_Role";

			}

		} catch (SQLException e)

		{

			e.printStackTrace();

		}

		return "Invalid user credentials";

	}

}
