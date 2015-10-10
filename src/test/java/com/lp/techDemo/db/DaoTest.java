package com.lp.techDemo.db;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lp.techDemo.db.dao.EmployeeDao;
import com.lp.techDemo.db.model.Employees;

public class DaoTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("mybatis-application.xml");
		EmployeeDao dao = (EmployeeDao) ac.getBean("employeeDao");
		Employees e = dao.getEmployee(100);
		System.err.println(e);
	}

}
