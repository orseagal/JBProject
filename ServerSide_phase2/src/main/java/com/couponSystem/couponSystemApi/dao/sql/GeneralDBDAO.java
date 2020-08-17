package com.couponSystem.couponSystemApi.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.couponSystem.couponSystemApi.beans.UserLogin;
import com.couponSystem.couponSystemApi.core.ConnectionPool;
import com.couponSystem.couponSystemApi.enums.ErrorType;
import com.couponSystem.couponSystemApi.exceptions.CouponSystemException;

public class GeneralDBDAO {

	ConnectionPool connectionPool;

	public UserLogin login(String user, String password) throws CouponSystemException {

		// String sql = "SELECT * FROM Users WHERE user_name=? AND password=?";

		String sql = "SELECT * FROM users WHERE user_name=? OR email =? and password=?";
		Connection connection = ConnectionPool.getinstance().getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user);
			preparedStatement.setString(2, user);
			preparedStatement.setString(3, password);

			resultSet = preparedStatement.executeQuery();

			UserLogin userLogin = null;

			while (resultSet.next()) {
				userLogin = new UserLogin();
				userLogin.setId(resultSet.getLong("id"));
				userLogin.setType(resultSet.getString("user_type"));
				userLogin.setName(resultSet.getString("user_name"));
				userLogin.setEmail(resultSet.getString("email"));

				return userLogin;
			}
			return null;

		} catch (SQLException e) {
			throw new CouponSystemException(sql, e, ErrorType.DATE_BASE_ERROR);

		} finally {
			DAOUtil.closeResources(preparedStatement, resultSet);
			ConnectionPool.getinstance().returnConnection(connection);
		}

	}

	
	public UserLogin createUser(String userName, String password, String email) {
	
		
		
		return null;
	}

}
