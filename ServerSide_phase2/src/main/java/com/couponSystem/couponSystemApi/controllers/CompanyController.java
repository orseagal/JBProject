package com.couponSystem.couponSystemApi.controllers;

import java.util.Collection;

import com.couponSystem.couponSystemApi.beans.Company;
import com.couponSystem.couponSystemApi.beans.PasswordBean;
import com.couponSystem.couponSystemApi.dao.ICompanyDAO;
import com.couponSystem.couponSystemApi.dao.ICouponDAO;
import com.couponSystem.couponSystemApi.dao.sql.CompanyDBDAO;
import com.couponSystem.couponSystemApi.dao.sql.CouponDBDAO;
import com.couponSystem.couponSystemApi.enums.ClientType;
import com.couponSystem.couponSystemApi.enums.ErrorType;
import com.couponSystem.couponSystemApi.exceptions.CouponSystemException;


public class CompanyController {

	ICompanyDAO companyDAO;
	ICouponDAO couponDAO;

	public CompanyController() throws CouponSystemException {
		this.companyDAO = new CompanyDBDAO();
		this.couponDAO = new CouponDBDAO();
	}

	public long createCompany(Company company) throws CouponSystemException {

		ValidateBeansUtil.companyValidate(company);

		if (companyDAO.existByName(company.getName())) {
			throw new CouponSystemException(ErrorType.NAME_EXIST);
		}

		return companyDAO.create(company);
	}

	public void removeCompany(long companyId) throws CouponSystemException {

		if (companyDAO.existsById(companyId)) {

			couponDAO.removeCompanyCouponsBycompany(companyId);

			couponDAO.removePurchasedCouponsByCompany(companyId);

			companyDAO.delete(companyId);

		} else {

			throw new CouponSystemException(ErrorType.ID_NOT_EXISTS);
		}

	}

	public void updateCompany(Company company, ClientType clientType) throws CouponSystemException {

		Company companyUpdate = companyDAO.read(company.getId());

		if (companyUpdate != null) {
			if (clientType.equals(ClientType.ADMIN)) {

				companyUpdate.setEmail(company.getEmail());
				companyUpdate.setPassword(company.getPassword());

				ValidateBeansUtil.companyValidate(companyUpdate);
				companyDAO.update(companyUpdate);
			} else if (clientType.equals(ClientType.COMPANY)) {

				companyUpdate.setEmail(company.getEmail());
				ValidateBeansUtil.companyValidate(companyUpdate);
				companyDAO.update(companyUpdate);
			}

		} else {

			throw new CouponSystemException(ErrorType.COMPANY_NOT_EXISTS);
		}

	}

	public void updateComapnyPasswordByCompany(PasswordBean passwordBean)
			throws CouponSystemException {

		if (passwordBean!=null) {

			Company passwordUpdae = companyDAO.read(passwordBean.getId());

			if (passwordUpdae != null) {

				if (passwordUpdae.getPassword().equals(passwordBean.getPassword())) {

					ValidateBeansUtil.validatePassword(passwordBean.getNewPassword());
					passwordUpdae.setPassword(passwordBean.getNewPassword());

					companyDAO.update(passwordUpdae);
				} else {

					throw new CouponSystemException(ErrorType.UPDATE_BY_CUSTOMER_PASSWORD_NOT_MATCHING);
				}

			} else {

				throw new CouponSystemException("The id you inserted don't exists", ErrorType.COMPANY_NOT_EXISTS);

			}

		} else {
			throw new CouponSystemException(ErrorType.UPDATE_BY_COMPANY);

		}

	}

	public Company getComapny(long companyId) throws CouponSystemException {

		if (companyDAO.existsById(companyId)) {

			return companyDAO.read(companyId);

		} else {

			throw new CouponSystemException("Wrong ID",ErrorType.COMPANY_NOT_EXISTS);
		}
	}

	public Collection<Company> getAllCompanies() throws CouponSystemException {
		// check if admin?
		return companyDAO.getAllCompanies();

	}

	public long login(String user, String password) throws CouponSystemException {

		return companyDAO.login(user, password);

	}

}