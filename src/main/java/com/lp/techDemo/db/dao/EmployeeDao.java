package com.lp.techDemo.db.dao;

import org.springframework.transaction.annotation.Transactional;

import com.lp.techDemo.db.model.Employees;

public interface EmployeeDao{

//	List<Employees> getAllEmployee();
	
	@Transactional
	void updateObj(Employees obj);
	
	@Transactional(readOnly=true)
	Employees getEmployee(int empId);
}
