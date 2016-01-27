package com.lp.techDemo.db.dao;

import com.lp.techDemo.db.model.Employees;

public class ProdEmployeeDao implements EmployeeDao {

	@Override
	public void updateObj(Employees obj) {
		System.err.println("ProdEmployeeDao update");

	}

	@Override
	public Employees getEmployee(int empId) {
		// TODO Auto-generated method stub
		return null;
	}

}
