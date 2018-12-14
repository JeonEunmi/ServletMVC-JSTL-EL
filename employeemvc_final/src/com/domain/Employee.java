package com.domain;

import java.sql.Date;

//자료형 클래스
public class Employee {
	
	//필드 구성
	private String empId, name_, ssn, phone
    , reg_name, dept_name, job_title
    , regId, deptId, jobId, filename;
	private int basicpay, extrapay, pay, pictureCount;
	private Date hiredate;
	
	//직원 정보 입력용
	public Employee(String name_, String ssn
			, String phone, Date hiredate
			, String regId, String deptId, String jobId
			, int basicpay, int extrapay) {
		this.name_ = name_;
		this.ssn = ssn;
		this.phone = phone;
		this.hiredate = hiredate;
		this.regId = regId;
		this.deptId = deptId;
		this.jobId = jobId;
		this.basicpay = basicpay;
		this.extrapay = extrapay;
	}
	
	//직원 정보 수정용
	public Employee(String empId, String name_, String ssn
			, String phone, Date hiredate
			, String regId, String deptId, String jobId
			, int basicpay, int extrapay) {
		this.empId = empId;
		this.name_ = name_;
		this.ssn = ssn;
		this.phone = phone;
		this.hiredate = hiredate;
		this.regId = regId;
		this.deptId = deptId;
		this.jobId = jobId;
		this.basicpay = basicpay;
		this.extrapay = extrapay;
	}

	//직원 정보 출력용
	public Employee(String empId, String name_, String ssn
			, String phone, Date hiredate
			, String reg_name, String dept_name, String job_title
			, int basicpay, int extrapay, int pay
			, int pictureCount, String filename) {
		this.empId = empId;
		this.name_ = name_;
		this.ssn = ssn;
		this.phone = phone;
		this.hiredate = hiredate;
		this.reg_name = reg_name;
		this.dept_name = dept_name;
		this.job_title = job_title;
		this.basicpay = basicpay;
		this.extrapay = extrapay;
		this.pay = pay;
		this.pictureCount = pictureCount;
		this.filename = filename;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getName_() {
		return name_;
	}

	public void setName_(String name_) {
		this.name_ = name_;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getReg_name() {
		return reg_name;
	}

	public void setReg_name(String reg_name) {
		this.reg_name = reg_name;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getJob_title() {
		return job_title;
	}

	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getBasicpay() {
		return basicpay;
	}

	public void setBasicpay(int basicpay) {
		this.basicpay = basicpay;
	}

	public int getExtrapay() {
		return extrapay;
	}

	public void setExtrapay(int extrapay) {
		this.extrapay = extrapay;
	}

	public int getPay() {
		return pay;
	}

	public void setPay(int pay) {
		this.pay = pay;
	}

	public int getPictureCount() {
		return pictureCount;
	}

	public void setPictureCount(int pictureCount) {
		this.pictureCount = pictureCount;
	}

	public Date getHiredate() {
		return hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

}
