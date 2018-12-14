package com.domain;

//자료형 클래스
public class Region {
	
	//지역번호, 지역명, 삭제가능여부
	private String regId, reg_name;
	private int count_;
	
	public Region(String reg_name) {
		this.reg_name = reg_name;
	}

	public Region(String regId, String reg_name) {
		this.regId = regId;
		this.reg_name = reg_name;
	}

	public Region(String regId, String reg_name, int count_) {
		this.regId = regId;
		this.reg_name = reg_name;
		this.count_ = count_;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getReg_name() {
		return reg_name;
	}

	public void setReg_name(String reg_name) {
		this.reg_name = reg_name;
	}

	public int getCount_() {
		return count_;
	}

	public void setCount_(int count_) {
		this.count_ = count_;
	}

}
