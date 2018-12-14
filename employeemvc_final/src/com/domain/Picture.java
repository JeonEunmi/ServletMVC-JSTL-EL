package com.domain;

public class Picture {
	
	private String pid, filename, content, empId;

	public Picture(String filename, String content, String empId) {
		this.filename = filename;
		this.content = content;
		this.empId = empId;
	}

	public Picture(String pid, String filename, String content, String empId) {
		this.pid = pid;
		this.filename = filename;
		this.content = content;
		this.empId = empId;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}


}
