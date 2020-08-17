package com.couponSystem.couponSystemApi.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import com.couponSystem.couponSystemApi.beans.Customer;
import com.couponSystem.couponSystemApi.core.ConnectionPool;
import com.couponSystem.couponSystemApi.dao.ICustomerDAO;
import com.couponSystem.couponSystemApi.enums.ErrorType;
import com.couponSystem.couponSystemApi.exceptions.CouponSystemException;




public class CustomerDBDAO implements ICustomerDAO {

	ConnectionPool connectionPool;


	@Override
	public long create(Customer customer) throws CouponSystemException {
		String sql = "INSERT INTO Users(user_name, password, email, user_type) VALUES( ?, ?, ?,?)";
		Connection connection = ConnectionPool.getinstance().getConnection();

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, customer.getName());
			preparedStatement.setString(2, customer.getPassword());
			preparedStatement.setString(3, customer.getEmail());
			preparedStatement.setString(4, customer.getType());
			
			preparedStatement.executeUpdate();

			resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();

			System.out.println("Create customer " + "'" + customer.getName() + "'" + " complete");

			System.out.println(resultSet.getLong(1));
			return resultSet.getLong(1);

		} catch (SQLException e) {
			throw new CouponSystemException(ErrorType.DATE_BASE_ERROR, e);
		} finally {
			DAOUtil.closeResources(preparedStatement, resultSet);
			ConnectionPool.getinstance().returnConnection(connection);
		}

	}

	@Override
	public Customer read(long customerId) throws CouponSystemException {
		String sql = "SELECT * FROM Users WHERE id = ?";

		Connection connection = ConnectionPool.getinstance().getConnection();

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, customerId);
			resultSet = preparedStatement.executeQuery();

			Customer customer = null;

			while (resultSet.next()) {
				customer = new Customer();
				customer.setId(resultSet.getLong("id"));
				customer.setEmail(resultSet.getString("email"));
				customer.setName(resultSet.getString("user_name"));
				customer.setPassword(resultSet.getString("password"));
				customer.setType(resultSet.getString("user_type"));
				System.out.println("customer "+customer);
			}

			return customer;

		} catch (SQLException e) {
			throw new CouponSystemException(ErrorType.DATE_BASE_ERROR, e);
		} finally {
			DAOUtil.closeResources(preparedStatement, resultSet);
			ConnectionPool.getinstance().returnConnection(connection);

		}

	}

	@Override
	public void update(Customer customer) throws CouponSystemException {

		String sql = "UPDATE Users SET user_name = ?, password = ?, email = ? WHERE id = ?";

		Connection connection = ConnectionPool.getinstance().getConnection();
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, customer.getName());
			preparedStatement.setString(2, customer.getPassword());
			preparedStatement.setString(3, customer.getEmail());
			preparedStatement.setLong(4, customer.getId());
			preparedStatement.executeUpdate();

			System.out.println("Update Customer id: " + "'" + customer.getName() + "'" + " Complete");

		} catch (SQLException e) {
			throw new CouponSystemException(ErrorType.DATE_BASE_ERROR, e);
		} finally {
			DAOUtil.closeResources(preparedStatement);
			ConnectionPool.getinstance().returnConnection(connection);
		}

	}

	@Override
	public void delete(long customerId) throws CouponSystemException {

		String sql = "DELETE FROM Users WHERE id = ?";

		Connection connection = ConnectionPool.getinstance().getConnection();

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setLong(1, customerId);
			preparedStatement.executeUpdate();

			System.out.println("Delete Customer ID : " + "'" + customerId + "'" + " Complete");

		} catch (SQLException e) {
			throw new CouponSystemException(ErrorType.DATE_BASE_ERROR, e);
		} finally {
			DAOUtil.closeResources(preparedStatement);
			ConnectionPool.getinstance().returnConnection(connection);
		}
	}

	@Override
	public Collection<Customer> getAllCustomers() throws CouponSystemException {
		String sql = "SELECT * FROM Users WHERE user_type='Customer' ";

		Connection connection = ConnectionPool.getinstance().getConnection();

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();

			Set<Customer> customers = new LinkedHashSet<>();
			while (resultSet.next()) {
				Customer customer = new Customer();
				customer.setEmail(resultSet.getString("email"));
				customer.setId(resultSet.getLong("id"));
				customer.setName(resultSet.getString("user_name"));
				customer.setPassword(resultSet.getString("password"));

				customers.add(customer);

			}

			return customers;
			// cust_name, password, email

		} catch (SQLException e) {
			throw new CouponSystemException(ErrorType.DATE_BASE_ERROR, e);
		} finally {
			DAOUtil.closeResources(preparedStatement, resultSet);
			ConnectionPool.getinstance().returnConnection(connection);
		}
	}

	@Override
	public Customer getByName(String CustomerName) throws CouponSystemException {
		String sql = "SELECT * FROM Users WHERE user_name = ?";

		Connection connection = ConnectionPool.getinstance().getConnection();

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, CustomerName);

			resultSet = preparedStatement.executeQuery();

			Customer customer = null;
			while (resultSet.next()) {
				customer = new Customer();
				customer.setEmail(resultSet.getString("email"));
				customer.setId(resultSet.getLong("id"));
				customer.setName(resultSet.getString("user_name"));
				customer.setPassword(resultSet.getString("password"));
				
			}

			return customer;

		} catch (SQLException e) {
			throw new CouponSystemException(ErrorType.DATE_BASE_ERROR, e);
		} finally {
			DAOUtil.closeResources(preparedStatement, resultSet);
			ConnectionPool.getinstance().returnConnection(connection);
		}

	}

	private Object getByEmail(String email) throws CouponSystemException {
		String sql = "SELECT * FROM Users WHERE email = ?";

		Connection connection = ConnectionPool.getinstance().getConnection();

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, email);

			resultSet = preparedStatement.executeQuery();

			Customer customer = null;
			while (resultSet.next()) {
				customer = new Customer();
				customer.setEmail(resultSet.getString("email"));
				customer.setId(resultSet.getLong("id"));
				customer.setName(resultSet.getString("user_name"));
				customer.setPassword(resultSet.getString("password"));
				
			}

			return customer;

		} catch (SQLException e) {
			throw new CouponSystemException(ErrorType.DATE_BASE_ERROR, e);
		} finally {
			DAOUtil.closeResources(preparedStatement, resultSet);
			ConnectionPool.getinstance().returnConnection(connection);
		}

	}
	
	
	
	@Override
	public boolean existByname(String name) throws CouponSystemException {
		return (getByName(name) != null);
	}

	public boolean existByEmail(String name) throws CouponSystemException {
		return (getByEmail(name) != null);
	}
	

	@Override
	public boolean existByid(long id) throws CouponSystemException {
		return (read(id) != null);
	}

	@Override
	public Long login(String user, String password) throws CouponSystemException {
		String sql = "SELECT id FROM Customer WHERE cust_name=? AND password=?";

		Connection connection = ConnectionPool.getinstance().getConnection();

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, user);
			preparedStatement.setString(2, password);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				return resultSet.getLong("id");
			}

			return null;

		} catch (SQLException e) {
			throw new CouponSystemException(ErrorType.DATE_BASE_ERROR, e);
		} finally {
			DAOUtil.closeResources(preparedStatement, resultSet);
			ConnectionPool.getinstance().returnConnection(connection);

		}
	}

}
