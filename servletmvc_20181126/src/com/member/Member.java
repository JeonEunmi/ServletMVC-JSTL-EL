package com.member;

import java.sql.Date;

public class Member {

	private String mid_, name_, phone;

	// 매개변수 있는 생성자
	public Member(String mid_, String name_, String phone) {
		this.mid_ = mid_;
		this.name_ = name_;
		this.phone = phone;
	}

	// getter, setter
	public String getMid_() {
		return mid_;
	}

	public void setMid_(String mid_) {
		this.mid_ = mid_;
	}

	public String getName_() {
		return name_;
	}

	public void setName_(String name_) {
		this.name_ = name_;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
