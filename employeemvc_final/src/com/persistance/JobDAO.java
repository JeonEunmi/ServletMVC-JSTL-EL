package com.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.connection.MySQLConnection80;
import com.domain.Job;

//데이터베이스 저장소 운영 클래스
public class JobDAO {

	// 출력1
	public List<Job> list1() {
		List<Job> list = new ArrayList<Job>();

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = MySQLConnection80.connect();

			String sql = "SELECT jobId, job_title, min_basicPay\r\n" 
						+ "          FROM jobs\r\n" 
						+ "          ORDER BY jobId";
			System.out.println("---------------");
			System.out.println(sql);
			
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String jobId = rs.getString("jobId");
				String job_title = rs.getString("job_title");
				int min_basicPay = rs.getInt("min_basicPay");
				list.add(new Job(jobId, job_title, min_basicPay));
			}
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				MySQLConnection80.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	// 출력2
	public List<Job> list2() {
		List<Job> list = new ArrayList<Job>();

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = MySQLConnection80.connect();

			String sql = "SELECT jobId, job_title, min_basicPay\r\n" + "          ,(SELECT COUNT(*) FROM employees \r\n"
					+ "          WHERE jobId = j.jobId) count_\r\n" 
					+ "          FROM jobs j\r\n"
					+ "          ORDER BY jobId";
			System.out.println("---------------");
			System.out.println(sql);

			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String jobId = rs.getString("jobId");
				String job_title = rs.getString("job_title");
				int min_basicPay = rs.getInt("min_basicPay");				
				int count_ = rs.getInt("count_");
				list.add(new Job(jobId, job_title, min_basicPay, count_));
			}
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				MySQLConnection80.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	// 출력3
	public Job list3(String jobId) {
		Job result = null;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = MySQLConnection80.connect();

			String sql = "SELECT jobId, job_title, min_basicPay\r\n" 
					+ "          FROM jobs j\r\n"
					+ "          WHERE jobId=?";
			System.out.println("---------------");
			System.out.println(sql);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, jobId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String jobId_ = rs.getString("jobId");
				String job_title = rs.getString("job_title");
				int min_basicPay = rs.getInt("min_basicPay");				
				result = new Job(jobId_, job_title, min_basicPay);
			}
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				MySQLConnection80.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// 입력
	public int add(Job j) {
		int result = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = MySQLConnection80.connect();

			String sql = "INSERT INTO jobs (jobId, job_title, min_basicPay)\r\n"
					+ "          VALUES ((SELECT CONCAT('JOB', LPAD(IFNULL(SUBSTR(MAX(jobId), 4), 0) + 1, 2, 0))\r\n"
					+ "          AS newId FROM jobs J), ?, ?)";
			System.out.println("---------------");
			System.out.println(sql);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, j.getJob_title());
			pstmt.setInt(2, j.getMin_basicPay());
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				MySQLConnection80.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// 삭제
	public int remove(Job j) {
		int result = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = MySQLConnection80.connect();

			String sql = "DELETE FROM jobs\r\n" 
						+ "          WHERE jobId = ?";
			System.out.println("---------------");
			System.out.println(sql);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, j.getJobId());
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				MySQLConnection80.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	
	//수정 메소드
	public int modify(Job j) {
		int result = 0;
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = MySQLConnection80.connect();

			String sql = "UPDATE jobs SET job_title=?, min_basicPay=?\r\n"
					+ "	WHERE jobId=?";
			System.out.println("---------------");
			System.out.println(sql);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, j.getJob_title());
			pstmt.setInt(2, j.getMin_basicPay());
			pstmt.setString(3, j.getJobId());
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				MySQLConnection80.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}	
		
		return result;
	}

}