package com.lp.techDemo.db;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.lp.techDemo.AstractTestCase;
import com.lp.techDemo.db.dao.EmployeeDao;
import com.lp.techDemo.db.model.Employees;

@ContextConfiguration(locations={"classpath:mybatis-application.xml"})
public class DaoTest extends AstractTestCase{

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}
	@Resource
	private EmployeeDao dao;

	@Test
	public void test() {
		Employees e = dao.getEmployee(100);
		assertNotNull(e);
		System.err.println(e);
		
	}

}
