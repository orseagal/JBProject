package com.SecureApp.dao;

import com.SecureApp.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncomeRepositry extends JpaRepository<Income, Integer> {

	List<Income> getAllByName(String name);

}
