package com.lp.techDemo.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.lp.techDemo.db.model.Employees;

public class UtilTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void encryptTest() throws Exception {
		String wenjian = "sdfa3232";
        
        String mw = EncryptUtil.encrypt(wenjian);
        System.out.println("密文:" + mw);
 
        String jm = EncryptUtil.decrypt(mw);
        assertEquals(wenjian, jm);
        System.out.println("明文:" + jm);
	}

	@Test
	public void toStringTest(){
		String str = ToStringUtil.generateToString(Employees.class);
		assertNotNull(str);
		System.err.println(str);
	}
}
