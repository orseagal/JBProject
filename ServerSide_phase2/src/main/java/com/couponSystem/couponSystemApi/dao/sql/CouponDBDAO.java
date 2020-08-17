package com.couponSystem.couponSystemApi.dao.sql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import com.couponSystem.couponSystemApi.beans.Coupon;
import com.couponSystem.couponSystemApi.core.ConnectionPool;
import com.couponSystem.couponSystemApi.dao.ICouponDAO;
import com.couponSystem.couponSystemApi.enums.CouponType;
import com.couponSystem.couponSystemApi.enums.ErrorType;
import com.couponSystem.couponSystemApi.exceptions.CouponSystemException;


public class CouponDBDAO implements ICouponDAO {

	

	@Override
	public long create(Coupon coupon) throws CouponSystemException {
		String sql = "INSERT INTO Coupon( comp_id, title, start_date, end_date, amount, type, message, price, image ) VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ? )";
		Connection connection = ConnectionPool.getinstance().getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setLong(1, coupon.getCompanyId());
			preparedStatement.setString(2, coupon.getTitle());
			preparedStatement.setDate(3, new Date(coupon.getStartDate().getTime()));
			preparedStatement.setDate(4, new Date(coupon.getEndDate().getTime()));
			preparedStatement.setInt(5, coupon.getAmount());
			preparedStatement.setString(6, String.valueOf(coupon.getType()));
			preparedStatement.setString(7, coupon.getMessage());
			preparedStatement.setDouble(8, coupon.getPrice());
			preparedStatement.setString(9, coupon.getImage());
			preparedStatement.executeUpdate();

			resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();

			System.out.println("Create Coupon id: " + resultSet.getLong(1) + " Complete");
			return resultSet.getLong(1);

		} catch (SQLException e) {
			throw new CouponSystemException(ErrorType.DATE_BASE_ERROR, e);

		} finally {
			DAOUtil.closeResources(preparedStatement, resultSet);
			ConnectionPool.getinstance().returnConnection(connection);
		}

	}

	@Override
	public Coupon read(long couponId) throws CouponSystemException {
		String sql = "SELECT * FROM Coupon WHERE id = ?";
		Connection connection = ConnectionPool.getinstance().getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setLong(1, couponId);

			resultSet = preparedStatement.executeQuery();

			Coupon coupon = null;

			while (resultSet.next()) {
				coupon = new Coupon();
				coupon.setId(resultSet.getLong("id"));
				coupon.setCompanyId(resultSet.getLong("comp_id"));
				coupon.setTitle(resultSet.getString("title"));
				coupon.setStartDate(resultSet.getDate("start_date"));
				coupon.setEndDate(resultSet.getDate("end_date"));
				coupon.setAmount(resultSet.getInt("amount"));
				coupon.setType(CouponType.valueOf(resultSet.getString("type")));
				coupon.setMessage(resultSet.getString("message"));
				coupon.setPrice(resultSet.getDouble("price"));
				coupon.setImage(resultSet.getString("image"));
			}
			
			return coupon;

		} catch (SQLException e) {
			throw new CouponSystemException(ErrorType.DATE_BASE_ERROR, e);
		} finally {
			DAOUtil.closeResources(preparedStatement, resultSet);
			ConnectionPool.getinstance().returnConnection(connection);

		}
	}

	@Override
	public void update(Coupon coupon) throws CouponSystemException {
		String sql = "UPDATE Coupon SET "
				+ "title = ?, start_date = ?, end_date = ?, amount = ?, type = ?, message = ?, price = ?, image = ?"
				+ "WHERE id = ?";
		Connection connection = ConnectionPool.getinstance().getConnection();
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, coupon.getTitle());
			preparedStatement.setDate(2, new Date(coupon.getStartDate().getTime()));
			preparedStatement.setDate(3, new Date(coupon.getEndDate().getTime()));
			preparedStatement.setInt(4, coupon.getAmount());
			preparedStatement.setString(5, String.valueOf(coupon.getType()));
			preparedStatement.setString(6, coupon.getMessage());
			preparedStatement.setDouble(7, coupon.getPrice());
			preparedStatement.setString(8, coupon.getImage());
			preparedStatement.setLong(9, coupon.getId());

			preparedStatement.executeUpdate();
			System.out.println("Update Coupon id : " + coupon.getId() + " Complete");

		} catch (SQLException e) {
			throw new CouponSystemException(ErrorType.DATE_BASE_ERROR, e);
		} finally {
			DAOUtil.closeResources(preparedStatement);
			ConnectionPool.getinstance().returnConnection(connection);
		}

	}

	@Override
	public void delete(long couponId) throws CouponSystemException {
		String sql = "DELETE FROM Coupon WHERE id = ?";
		Connection connection = ConnectionPool.getinstance().getConnection();
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setLong(1, couponId);
			preparedStatement.executeUpdate();

			System.out.println("Delete Coupon id : " + couponId + " Complete");

		} catch (SQLException e) {
			throw new CouponSystemException(ErrorType.DATE_BASE_ERROR, e);
		} finally {
			DAOUtil.closeResources(preparedStatement);
			ConnectionPool.getinstance().returnConnection(connection);

		}

	}

	@Override
	public void updateAmount(long couponId, int amountDifferense) throws CouponSystemException {
		String sql = "UPDATE Coupon SET amount =amount+? WHERE id=? and amount+?>=0";

		Connection connection = ConnectionPool.getinstance().getConnection();
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, amountDifferense);
			preparedStatement.setLong(2, couponId);
			preparedStatement.setInt(3, amountDifferense);

			int intResult = preparedStatement.executeUpdate();
			if (intResult != 0) {

				System.out.println("UpdateAmount Coupon id : " + couponId + " Complete");
				return;

			} else {

				throw new CouponSystemException(ErrorType.OUT_OFF_STOCK);
			}

		} catch (SQLException e) {
			throw new CouponSystemException(ErrorType.DATE_BASE_ERROR, e);
		} finally {
			DAOUtil.closeResources(preparedStatement);
			ConnectionPool.getinstance().returnConnection(connection);

		}

	}

	@Override
	public Collection<Coupon> getAllCoupons() throws CouponSystemException {

		String sql = "SELECT * FROM Coupon";

		Connection connection = ConnectionPool.getinstance().getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			Set<Coupon> coupons = new LinkedHashSet<>();

			while (resultSet.next()) {
				Coupon coupon = new Coupon();
				coupon = new Coupon();
				coupon.setId(resultSet.getLong("id"));
				coupon.setCompanyId(resultSet.getLong("comp_id"));
				coupon.setTitle(resultSet.getString("title"));
				coupon.setStartDate(resultSet.getDate("start_date"));
				coupon.setEndDate(resultSet.getDate("end_date"));
				coupon.setAmount(resultSet.getInt("amount"));
				coupon.setType(CouponType.valueOf(resultSet.getString("type")));
				coupon.setMessage(resultSet.getString("message"));
				coupon.setPrice(resultSet.getDouble("price"));
				coupon.setImage(resultSet.getString("image"));
				coupons.add(coupon);
			}

			return coupons;

		} catch (SQLException e) {
			throw new CouponSystemException(ErrorType.DATE_BASE_ERROR, e);

		} finally {
			DAOUtil.closeResources(preparedStatement, resultSet);
			ConnectionPool.getinstance().returnConnection(connection);
		}

	}

	@Override
	public void removeExpiredCoupons(java.util.Date date) throws CouponSystemException {
		String sql_customer = "DELETE customer_coupon FROM customer_coupon INNER JOIN coupon ON coupon.id = customer_coupon.coupon_id WHERE coupon.end_date<?";
		String sql_coupon = "DELETE FROM coupons WHERE end_date<?";

		Connection connection = ConnectionPool.getinstance().getConnection();
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(sql_customer);
			preparedStatement.setDate(1, (java.sql.Date) new Date(date.getTime()));
			preparedStatement.executeUpdate();
			preparedStatement.close();

			preparedStatement = connection.prepareStatement(sql_coupon);
			preparedStatement.setDate(1, (java.sql.Date) new Date(date.getTime()));
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new CouponSystemException(ErrorType.DATE_BASE_ERROR, e);
		} finally {
			DAOUtil.closeResources(preparedStatement);
			ConnectionPool.getinstance().returnConnection(connection);
		}

	}

	@Override
	public boolean existByTitle(String title) throws CouponSystemException {

		String sql = "SELECT * FROM coupon WHERE title=?";
		Connection connection = ConnectionPool.getinstance().getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, title);
			resultset = preparedStatement.executeQuery();

			if (resultset.next()) {
				return true;

			}
			return false;

		} catch (SQLException e) {
			throw new CouponSystemException(ErrorType.DATE_BASE_ERROR, e);
		} finally {
			DAOUtil.closeResources(preparedStatement);
			ConnectionPool.getinstance().returnConnection(connection);

		}

	}

//	@Override // not working..
//	public Collection<Coupon> getCustomerCouponsNotPurchase(long idCustomer) throws CouponSystemException {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public void purchaseCoupon(long customerId, long couponId) throws CouponSystemException {

		String sql = "INSERT INTO Customer_Coupon VALUES(?,?)";

		Connection connection = ConnectionPool.getinstance().getConnection();
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, customerId);
			preparedStatement.setLong(2, couponId);
			preparedStatement.executeUpdate();

			System.out.println("purchase coupon: " + couponId + " customer id: " + customerId);

		} catch (SQLException e) {
			throw new CouponSystemException(ErrorType.DATE_BASE_ERROR, e);
		} finally {
			DAOUtil.closeResources(preparedStatement);
			ConnectionPool.getinstance().returnConnection(connection);
		}

	}

	@Override
	public Collection<Coupon> getCustomerCoupons(long customerId) throws CouponSystemException {

		String sql = "SELECT * FROM coupon INNER JOIN customer_coupon "
				+ "ON coupon.id = customer_coupon.coupon_id WHERE customer_coupon.cust_id =?";
		Connection connection = ConnectionPool.getinstance().getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, customerId);
			resultSet = preparedStatement.executeQuery();
			Coupon coupon;
			Set<Coupon> coupons = new LinkedHashSet<>();
			while (resultSet.next()) {
				coupon = new Coupon();
				coupon.setId(resultSet.getLong("id"));
				coupon.setCompanyId(resultSet.getLong("comp_id"));
				coupon.setTitle(resultSet.getString("title"));
				coupon.setStartDate(resultSet.getDate("start_date"));
				coupon.setEndDate(resultSet.getDate("end_date"));
				coupon.setAmount(resultSet.getInt("amount"));
				coupon.setType(CouponType.valueOf(resultSet.getString("type")));
				coupon.setMessage(resultSet.getString("message"));
				coupon.setPrice(resultSet.getDouble("price"));
				coupon.setImage(resultSet.getString("image"));
				coupons.add(coupon);

			}
			return coupons;

		} catch (SQLException e) {
			throw new CouponSystemException(ErrorType.DATE_BASE_ERROR, e);
		} finally {
			DAOUtil.closeResources(preparedStatement, resultSet);
			ConnectionPool.getinstance().returnConnection(connection);
		}

	}

	@Override
	public Collection<Coupon> getCustomerCouponsByType(CouponType type, long customerId) throws CouponSystemException {

		String sql = "SELECT * FROM coupon INNER JOIN  customer_coupon"
				+ " ON coupon.id = customer_coupon.coupon_id WHERE customer_coupon.cust_id =? AND coupon.type =?";

		Connection connection = ConnectionPool.getinstance().getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Set<Coupon> coupons = new LinkedHashSet<>();
		Coupon coupon;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, customerId);
			preparedStatement.setString(2, String.valueOf(type));
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				coupon = new Coupon();
				coupon.setId(resultSet.getLong("id"));
				coupon.setCompanyId(resultSet.getLong("comp_id"));
				coupon.setTitle(resultSet.getString("title"));
				coupon.setStartDate(resultSet.getDate("start_date"));
				coupon.setEndDate(resultSet.getDate("end_date"));
				coupon.setAmount(resultSet.getInt("amount"));
				coupon.setType(CouponType.valueOf(resultSet.getString("type")));
				coupon.setMessage(resultSet.getString("message"));
				coupon.setPrice(resultSet.getDouble("price"));
				coupon.setImage(resultSet.getString("image"));
				coupons.add(coupon);

			}
			return coupons;

		} catch (SQLException e) {
			throw new CouponSystemException(ErrorType.DATE_BASE_ERROR, e);
		}

	}

	@Override
	public Collection<Coupon> getCustomerCouponsByPrice(double price, long customerId) throws CouponSystemException {
		String sql = "SELECT * FROM coupon INNER JOIN  customer_coupon"
				+ " ON coupon.id = customer_coupon.coupon_id WHERE customer_coupon.cust_id =? AND coupon.price <=?";

		Connection connection = ConnectionPool.getinstance().getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Set<Coupon> coupons = new LinkedHashSet<>();
		Coupon coupon;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, customerId);
			preparedStatement.setDouble(2, price);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				coupon = new Coupon();
				coupon.setId(resultSet.getLong("id"));
				coupon.setCompanyId(resultSet.getLong("comp_id"));
				coupon.setTitle(resultSet.getString("title"));
				coupon.setStartDate(resultSet.getDate("start_date"));
				coupon.setEndDate(resultSet.getDate("end_date"));
				coupon.setAmount(resultSet.getInt("amount"));
				coupon.setType(CouponType.valueOf(resultSet.getString("type")));
				coupon.setMessage(resultSet.getString("message"));
				coupon.setPrice(resultSet.getDouble("price"));
				coupon.setImage(resultSet.getString("image"));
				coupons.add(coupon);

			}
			return coupons;

		} catch (SQLException e) {
			throw new CouponSystemException(ErrorType.DATE_BASE_ERROR, e);
		}

	}

	@Override
	public void removeCustomerCouponByCoupon(long couponId) throws CouponSystemException {
		String sql = "DELETE FROM customer_coupon WHERE coupon_id=?";
		Connection connection = ConnectionPool.getinstance().getConnection();
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, couponId);
			preparedStatement.executeUpdate();

			System.out.println("Coupon: " + couponId + " has been removed");
		} catch (SQLException e) {
			throw new CouponSystemException(ErrorType.DATE_BASE_ERROR, e);
		} finally {
			DAOUtil.closeResources(preparedStatement);
			ConnectionPool.getinstance().returnConnection(connection);
		}

	}

	@Override
	public void removeCustomerCouponsByCustomer(long customerId) throws CouponSystemException {
		String sql = "DELETE FROM customer_coupon WHERE cust_id=?";
		Connection connection = ConnectionPool.getinstance().getConnection();
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, customerId);
			preparedStatement.executeUpdate();

			System.out.println("Coupons of customer id: " + customerId + " has been removed");
		} catch (SQLException e) {
			throw new CouponSystemException(ErrorType.DATE_BASE_ERROR, e);
		} finally {
			DAOUtil.closeResources(preparedStatement);
			ConnectionPool.getinstance().returnConnection(connection);
		}

	}

	@Override
	public void removeCompanyCouponsBycompany(long companyId) throws CouponSystemException {
		String sql = "DELETE FROM coupon WHERE comp_id=?";

		Connection connection = ConnectionPool.getinstance().getConnection();
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, companyId);
			preparedStatement.executeUpdate();

			System.out.println("Coupons of company id: " + companyId + " has been removed");
		} catch (SQLException e) {
			throw new CouponSystemException(ErrorType.DATE_BASE_ERROR, e);
		} finally {
			DAOUtil.closeResources(preparedStatement);
			ConnectionPool.getinstance().returnConnection(connection);
		}

	}

	@Override
	public void removePurchasedCouponsByCompany(long customerId) throws CouponSystemException {
		String sql = "DELETE customer_coupon FROM customer_coupon INNER JOIN coupon ON coupon.id = customer_coupon.coupon_id  WHERE coupon.comp_id=?";

		Connection connection = ConnectionPool.getinstance().getConnection();
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, customerId);
			preparedStatement.executeUpdate();

			System.out.println("Customer's coupons of company id: " + customerId + " has been removed");
		} catch (SQLException e) {
			throw new CouponSystemException(ErrorType.DATE_BASE_ERROR, e);
		} finally {
			DAOUtil.closeResources(preparedStatement);
			ConnectionPool.getinstance().returnConnection(connection);
		}

	}

	@Override
	public Collection<Coupon> getCompanyCoupons(long customerId) throws CouponSystemException {
		String sql = "SELECT * FROM coupon WHERE comp_id=?";
		Connection connection = ConnectionPool.getinstance().getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, customerId);
			resultSet = preparedStatement.executeQuery();

			Set<Coupon> coupons = new LinkedHashSet<>();

			while (resultSet.next()) {
				Coupon coupon = new Coupon();
				coupon = new Coupon();
				coupon.setId(resultSet.getLong("id"));
				coupon.setCompanyId(resultSet.getLong("comp_id"));
				coupon.setTitle(resultSet.getString("title"));
				coupon.setStartDate(resultSet.getDate("start_date"));
				coupon.setEndDate(resultSet.getDate("end_date"));
				coupon.setAmount(resultSet.getInt("amount"));
				coupon.setType(CouponType.valueOf(resultSet.getString("type")));
				coupon.setMessage(resultSet.getString("message"));
				coupon.setPrice(resultSet.getDouble("price"));
				coupon.setImage(resultSet.getString("image"));
				coupons.add(coupon);
			}

			return coupons;

		} catch (SQLException e) {
			throw new CouponSystemException(ErrorType.DATE_BASE_ERROR, e);

		} finally {
			DAOUtil.closeResources(preparedStatement, resultSet);
			ConnectionPool.getinstance().returnConnection(connection);
		}

	}

	@Override
	public Collection<Coupon> getCompanyCouponsByPrice(long comapnyId, double price) throws CouponSystemException {
		String sql = "SELECT * FROM coupon WHERE comp_id=? AND price<=?";
		Connection connection = ConnectionPool.getinstance().getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, comapnyId);
			preparedStatement.setDouble(2, price);
			resultSet = preparedStatement.executeQuery();

			Set<Coupon> coupons = new LinkedHashSet<>();

			while (resultSet.next()) {
				Coupon coupon = new Coupon();
				coupon = new Coupon();
				coupon.setId(resultSet.getLong("id"));
				coupon.setCompanyId(resultSet.getLong("comp_id"));
				coupon.setTitle(resultSet.getString("title"));
				coupon.setStartDate(resultSet.getDate("start_date"));
				coupon.setEndDate(resultSet.getDate("end_date"));
				coupon.setAmount(resultSet.getInt("amount"));
				coupon.setType(CouponType.valueOf(resultSet.getString("type")));
				coupon.setMessage(resultSet.getString("message"));
				coupon.setPrice(resultSet.getDouble("price"));
				coupon.setImage(resultSet.getString("image"));
				coupons.add(coupon);
			}

			return coupons;

		} catch (SQLException e) {
			throw new CouponSystemException(ErrorType.DATE_BASE_ERROR, e);

		} finally {
			DAOUtil.closeResources(preparedStatement, resultSet);
			ConnectionPool.getinstance().returnConnection(connection);
		}

	}

	@Override
	public Collection<Coupon> getCompanyCouponsByType(CouponType type, long comapnyId) throws CouponSystemException {
		String sql = "SELECT * FROM coupon WHERE comp_id=? AND type=?";
		Connection connection = ConnectionPool.getinstance().getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, comapnyId);
			preparedStatement.setString(2, String.valueOf(type));
			resultSet = preparedStatement.executeQuery();

			Set<Coupon> coupons = new LinkedHashSet<>();

			while (resultSet.next()) {
				Coupon coupon = new Coupon();
				coupon = new Coupon();
				coupon.setId(resultSet.getLong("id"));
				coupon.setCompanyId(resultSet.getLong("comp_id"));
				coupon.setTitle(resultSet.getString("title"));
				coupon.setStartDate(resultSet.getDate("start_date"));
				coupon.setEndDate(resultSet.getDate("end_date"));
				coupon.setAmount(resultSet.getInt("amount"));
				coupon.setType(CouponType.valueOf(resultSet.getString("type")));
				coupon.setMessage(resultSet.getString("message"));
				coupon.setPrice(resultSet.getDouble("price"));
				coupon.setImage(resultSet.getString("image"));
				coupons.add(coupon);
			}

			return coupons;

		} catch (SQLException e) {
			throw new CouponSystemException(ErrorType.DATE_BASE_ERROR, e);

		} finally {
			DAOUtil.closeResources(preparedStatement, resultSet);
			ConnectionPool.getinstance().returnConnection(connection);
		}

	}

	@Override
	public Collection<Coupon> getCompanyCouponsByDate(java.util.Date date, long comapnyId)
			throws CouponSystemException {
		String sql = "SELECT * FROM coupon WHERE comp_id=? AND end_date<=?";
		Connection connection = ConnectionPool.getinstance().getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, comapnyId);
			preparedStatement.setDate(2, new Date(date.getTime()));
			resultSet = preparedStatement.executeQuery();

			Set<Coupon> coupons = new LinkedHashSet<>();

			while (resultSet.next()) {
				Coupon coupon = new Coupon();
				coupon = new Coupon();
				coupon.setId(resultSet.getLong("id"));
				coupon.setCompanyId(resultSet.getLong("comp_id"));
				coupon.setTitle(resultSet.getString("title"));
				coupon.setStartDate(resultSet.getDate("start_date"));
				coupon.setEndDate(resultSet.getDate("end_date"));
				coupon.setAmount(resultSet.getInt("amount"));
				coupon.setType(CouponType.valueOf(resultSet.getString("type")));
				coupon.setMessage(resultSet.getString("message"));
				coupon.setPrice(resultSet.getDouble("price"));
				coupon.setImage(resultSet.getString("image"));
				coupons.add(coupon);
			}

			return coupons;

		} catch (SQLException e) {
			throw new CouponSystemException(ErrorType.DATE_BASE_ERROR, e);

		} finally {
			DAOUtil.closeResources(preparedStatement, resultSet);
			ConnectionPool.getinstance().returnConnection(connection);
		}

	}

	@Override
	public Collection<Coupon> getCouponsByType(CouponType type) throws CouponSystemException {
		String sql = "SELECT * FROM coupon WHERE type=?";
		Connection connection = ConnectionPool.getinstance().getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, String.valueOf(type));
			resultSet = preparedStatement.executeQuery();

			Set<Coupon> coupons = new LinkedHashSet<>();

			while (resultSet.next()) {
				Coupon coupon = new Coupon();
				coupon = new Coupon();
				coupon.setId(resultSet.getLong("id"));
				coupon.setCompanyId(resultSet.getLong("comp_id"));
				coupon.setTitle(resultSet.getString("title"));
				coupon.setStartDate(resultSet.getDate("start_date"));
				coupon.setEndDate(resultSet.getDate("end_date"));
				coupon.setAmount(resultSet.getInt("amount"));
				coupon.setType(CouponType.valueOf(resultSet.getString("type")));
				coupon.setMessage(resultSet.getString("message"));
				coupon.setPrice(resultSet.getDouble("price"));
				coupon.setImage(resultSet.getString("image"));
				coupons.add(coupon);
			}

			return coupons;

		} catch (SQLException e) {
			throw new CouponSystemException(ErrorType.DATE_BASE_ERROR, e);

		} finally {
			DAOUtil.closeResources(preparedStatement, resultSet);
			ConnectionPool.getinstance().returnConnection(connection);
		}

	}

}
