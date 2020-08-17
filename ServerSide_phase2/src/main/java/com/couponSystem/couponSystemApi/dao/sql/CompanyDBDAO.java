package com.couponSystem.couponSystemApi.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import com.couponSystem.couponSystemApi.beans.Company;
import com.couponSystem.couponSystemApi.core.ConnectionPool;
import com.couponSystem.couponSystemApi.dao.ICompanyDAO;
import com.couponSystem.couponSystemApi.enums.ErrorType;
import com.couponSystem.couponSystemApi.exceptions.CouponSystemException;



public class CompanyDBDAO implements ICompanyDAO {


	@Override
	public long create(Company company) throws CouponSystemException {
		String sql = "INSERT INTO Users(user_name, password, email, user_type) VALUES (?,?,?,?)";
		Connection connection = ConnectionPool.getinstance().getConnection();

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, company.getName());
			preparedStatement.setString(2, company.getPassword());
			preparedStatement.setString(3, company.getEmail());
			preparedStatement.setString(4, company.getType());

			preparedStatement.executeUpdate();

			resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();

			System.out.println("Create company " + company.getName() + " Complete");

			return resultSet.getLong(1);

		} catch (SQLException e) {
			throw new CouponSystemException(ErrorType.DATE_BASE_ERROR, e);
		} finally {
			DAOUtil.closeResources(preparedStatement, resultSet);
			ConnectionPool.getinstance().returnConnection(connection);
		}

	}

	@Override
	public Company read(long companyId) throws CouponSystemException {
		String sql = "SELECT * FROM Users WHERE id = ?";
		Connection connection = ConnectionPool.getinstance().getConnection();

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, companyId);

			resultSet = preparedStatement.executeQuery();

			Company company = null;

			while (resultSet.next()) {
				company = new Company();
				company.setEmail(resultSet.getString("email"));
				company.setId(resultSet.getLong("id"));
				company.setPassword(resultSet.getString("password"));
				company.setName(resultSet.getString("user_name"));
				company.setType(resultSet.getString("user_type"));
			}

			return company;

		} catch (SQLException e) {
			throw new CouponSystemException(ErrorType.DATE_BASE_ERROR, e);
		} finally {
			DAOUtil.closeResources(preparedStatement, resultSet);
			ConnectionPool.getinstance().returnConnection(connection);

		}

	}

	@Override
	public void update(Company company) throws CouponSystemException {
		String sql = "UPDATE Users SET user_name = ?, password = ?, email = ? WHERE id = ?";
		Connection connection = ConnectionPool.getinstance().getConnection();

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, company.getName());
			preparedStatement.setString(2, company.getPassword());
			preparedStatement.setString(3, company.getEmail());
			preparedStatement.setLong(4, company.getId());

			preparedStatement.executeUpdate();

			System.out.println("Update Company " + "'" + company.getName() + "'" + " complete");

		} catch (SQLException e) {
			throw new CouponSystemException(ErrorType.DATE_BASE_ERROR, e);
		} finally {
			DAOUtil.closeResources(preparedStatement);
			ConnectionPool.getinstance().returnConnection(connection);
		}

	}

	@Override
	public void delete(long companyId) throws CouponSystemException {

		String sql = "DELETE FROM Users WHERE id = ?";
		Connection connection =ConnectionPool.getinstance().getConnection();

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, companyId);

			preparedStatement.executeUpdate();

			System.out.println("Delete Company ID : " + "'" + companyId + "'" + " Complete");

		} catch (SQLException e) {
			throw new CouponSystemException(ErrorType.DATE_BASE_ERROR, e);
		} finally {
			DAOUtil.closeResources(preparedStatement);
			ConnectionPool.getinstance().returnConnection(connection);
		}

	}

	@Override
	public Collection<Company> getAllCompanies() throws CouponSystemException {
		String sql = "SELECT * FROM Users WHERE user_type='Company' ";

		Connection connection = ConnectionPool.getinstance().getConnection();

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Set<Company> companies = new LinkedHashSet<>();
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Company company = new Company();
				company.setId(resultSet.getLong("id"));
				company.setEmail(resultSet.getString("email"));
				company.setPassword(resultSet.getString("password"));
				company.setName(resultSet.getString("user_name"));

				companies.add(company);
			}

			return companies;

		} catch (SQLException e) {
			throw new CouponSystemException(sql, e, ErrorType.DATE_BASE_ERROR);
		} finally {
			DAOUtil.closeResources(preparedStatement, resultSet);
			ConnectionPool.getinstance().returnConnection(connection);
		}
	}

	@Override
	public Long login(String user, String password) throws CouponSystemException {
		String sql = "SELECT id FROM company WHERE comp_name=? AND password=?";

		Connection connection =ConnectionPool.getinstance().getConnection();

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

	@Override
	public boolean existByName(String name) throws CouponSystemException {

		String sql = "SELECT * FROM Users WHERE Users.user_name =?";

		Connection connection =ConnectionPool.getinstance().getConnection();

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
			return true;	
			}
			
			return false;
		} catch (SQLException e) {
			throw new CouponSystemException(ErrorType.DATE_BASE_ERROR, e);
		} finally {
			DAOUtil.closeResources(preparedStatement, resultSet);
			ConnectionPool.getinstance().returnConnection(connection);
		}

	}

	@Override
	public boolean existsById(long id) throws CouponSystemException {
		String sql = "SELECT * FROM Users WHERE Users.id =?";

		Connection connection =ConnectionPool.getinstance().getConnection();

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
			return true;	
			}
			
			return false;
		} catch (SQLException e) {
			throw new CouponSystemException(ErrorType.DATE_BASE_ERROR, e);
		} finally {
			DAOUtil.closeResources(preparedStatement, resultSet);
			ConnectionPool.getinstance().returnConnection(connection);
		}

	}

}
