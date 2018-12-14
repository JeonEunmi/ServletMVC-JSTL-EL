package com.domain;

//자료형 클래스
public class Job {

	private String jobId, job_title; // 직위번호, 직위명
	private int min_basicPay, count_; // 최소급여, 삭제가능여부

	public Job(String job_title, int min_basicPay) {
		this.job_title = job_title;
		this.min_basicPay = min_basicPay;
	}

	public Job(String jobId, String job_title, int min_basicPay) {
		this.jobId = jobId;
		this.job_title = job_title;
		this.min_basicPay = min_basicPay;
	}

	public Job(String jobId, String job_title, int min_basicPay, int count_) {
		this.jobId = jobId;
		this.job_title = job_title;
		this.min_basicPay = min_basicPay;
		this.count_ = count_;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJob_title() {
		return job_title;
	}

	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}

	public int getMin_basicPay() {
		return min_basicPay;
	}

	public void setMin_basicPay(int min_basicPay) {
		this.min_basicPay = min_basicPay;
	}

	public int getCount_() {
		return count_;
	}

	public void setCount_(int count_) {
		this.count_ = count_;
	}

}