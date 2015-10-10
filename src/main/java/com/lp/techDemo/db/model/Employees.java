package com.lp.techDemo.db.model;

import java.io.Serializable;
import java.util.Date;

public class Employees implements Serializable{
	private static final long serialVersionUID = 1045252010508424207L;
	
	private int EMPLOYEE_ID;
	private String FIRST_NAME;
	private String LAST_NAME;
	private String EMAIL;
	private String PHONE_NUMBER;
	private Date HIRE_DATE;
	private String JOB_ID;
	private double SALARY;
	private double COMMISSION_PCT;
	private String MANAGER_ID;
	private String DEPARTMENT_ID;
	
	public int getEMPLOYEE_ID() {
		return EMPLOYEE_ID;
	}
	public void setEMPLOYEE_ID(int eMPLOYEE_ID) {
		EMPLOYEE_ID = eMPLOYEE_ID;
	}
	public String getFIRST_NAME() {
		return FIRST_NAME;
	}
	public void setFIRST_NAME(String fIRST_NAME) {
		FIRST_NAME = fIRST_NAME;
	}
	public String getLAST_NAME() {
		return LAST_NAME;
	}
	public void setLAST_NAME(String lAST_NAME) {
		LAST_NAME = lAST_NAME;
	}
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	public String getPHONE_NUMBER() {
		return PHONE_NUMBER;
	}
	public void setPHONE_NUMBER(String pHONE_NUMBER) {
		PHONE_NUMBER = pHONE_NUMBER;
	}
	public Date getHIRE_DATE() {
		return HIRE_DATE;
	}
	public void setHIRE_DATE(Date hIRE_DATE) {
		HIRE_DATE = hIRE_DATE;
	}
	public String getJOB_ID() {
		return JOB_ID;
	}
	public void setJOB_ID(String jOB_ID) {
		JOB_ID = jOB_ID;
	}
	public double getSALARY() {
		return SALARY;
	}
	public void setSALARY(double sALARY) {
		SALARY = sALARY;
	}
	public double getCOMMISSION_PCT() {
		return COMMISSION_PCT;
	}
	public void setCOMMISSION_PCT(double cOMMISSION_PCT) {
		COMMISSION_PCT = cOMMISSION_PCT;
	}
	public String getMANAGER_ID() {
		return MANAGER_ID;
	}
	public void setMANAGER_ID(String mANAGER_ID) {
		MANAGER_ID = mANAGER_ID;
	}
	public String getDEPARTMENT_ID() {
		return DEPARTMENT_ID;
	}
	public void setDEPARTMENT_ID(String dEPARTMENT_ID) {
		DEPARTMENT_ID = dEPARTMENT_ID;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Employees [")
		.append("MANAGER_ID=").append(MANAGER_ID).append(",")
		.append("SALARY=").append(SALARY).append(",")
		.append("DEPARTMENT_ID=").append(DEPARTMENT_ID).append(",")
		.append("HIRE_DATE=").append(HIRE_DATE).append(",")
		.append("FIRST_NAME=").append(FIRST_NAME).append(",")
		.append("COMMISSION_PCT=").append(COMMISSION_PCT).append(",")
		.append("EMAIL=").append(EMAIL).append(",")
		.append("EMPLOYEE_ID=").append(EMPLOYEE_ID).append(",")
		.append("PHONE_NUMBER=").append(PHONE_NUMBER).append(",")
		.append("LAST_NAME=").append(LAST_NAME).append(",")
		.append("JOB_ID=").append(JOB_ID)
		.append("]");
		return sb.toString();
	}
	
}
