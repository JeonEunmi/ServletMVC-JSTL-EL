package com.persistance;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.connection.MySQLConnection80;
import com.domain.Employee;

//(데이터베이스) 저장소 운영 클래스
public class EmployeeDAO {
	
	//정렬 출력
	//주의) order 변수는 정렬 기준을 제시한다
	public List<Employee> list(String order) {
		List<Employee> list = new ArrayList<Employee>();
		
		/*
		//1차 뷰
		CREATE OR REPLACE VIEW empView
		AS
		SELECT E.empId, name_, ssn, phone, hiredate
				, reg_name, dept_name, job_title, basicpay, extrapay
				, (basicpay + extrapay) pay
				, (SELECT COUNT(*) FROM employeePictureList
						WHERE empId = E.empId) pictureCount
				FROM employees E, departments D, jobs j, regions R
				WHERE E.deptid = D.deptid
					AND E.jobid = j.jobid
					AND E.regid = R.regid;

		//2차 뷰
		CREATE OR REPLACE VIEW picEmpView
		AS
		SELECT e.empId, name_, ssn, phone, hiredate
			, reg_name, dept_name, job_title, basicpay, extrapay,  pay
		    , pictureCount, filename
			FROM empView e LEFT JOIN employeePictureList ep
		    ON e.empId = ep.empId;
		   
		//2차 뷰를 이용한 SELECT 쿼리    
		SELECT empId, name_, ssn, phone, hiredate
			, reg_name, dept_name, job_title, basicpay, extrapay,  pay
		    , pictureCount, filename
			FROM picEmpView
			ORDER BY empId;
		*/
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {

			conn = MySQLConnection80.connect();

			String sql = "SELECT empId, name_, ssn, phone, hiredate\r\n" + 
					"	, reg_name, dept_name, job_title, basicpay, extrapay,  pay\r\n" + 
					"    , pictureCount, filename\r\n" + 
					"	FROM picEmpView\r\n";
			sql += " ORDER BY " + order;
			
			System.out.println("---------------");
			System.out.println(sql);
			
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String empId = rs.getString("empId");
				String name_ = rs.getString("name_");
				String ssn = rs.getString("ssn");
				String phone = rs.getString("phone");
				Date hiredate = rs.getDate("hiredate");
				String reg_name = rs.getString("reg_name");
				String dept_name = rs.getString("dept_name");
				String job_title = rs.getString("job_title");
				int basicpay = rs.getInt("basicpay");
				int extrapay = rs.getInt("extrapay");
				int pay = rs.getInt("pay");
				int pictureCount = rs.getInt("pictureCount");
				String filename = rs.getString("filename");

				list.add(new Employee(empId, name_, ssn, phone
						, hiredate, reg_name, dept_name, job_title
						, basicpay, extrapay, pay
						, pictureCount, filename));
			}
			rs.close();

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
		
		return list;
	}
	
	//검색 및 정렬 출력
	public List<Employee> list(String key, String value, String order) {
		List<Employee> list = new ArrayList<Employee>();
		
		/*
		//1차 뷰
		CREATE OR REPLACE VIEW empView
		AS
		SELECT E.empId, name_, ssn, phone, hiredate
				, reg_name, dept_name, job_title, basicpay, extrapay
				, (basicpay + extrapay) pay
				, (SELECT COUNT(*) FROM employeePictureList
						WHERE empId = E.empId) pictureCount
				FROM employees E, departments D, jobs j, regions R
				WHERE E.deptid = D.deptid
					AND E.jobid = j.jobid
					AND E.regid = R.regid;

		//2차 뷰
		CREATE OR REPLACE VIEW picEmpView
		AS
		SELECT e.empId, name_, ssn, phone, hiredate
			, reg_name, dept_name, job_title, basicpay, extrapay,  pay
		    , pictureCount, filename
			FROM empView e LEFT JOIN employeePictureList ep
		    ON e.empId = ep.empId;
		   
		//2차 뷰를 이용한 SELECT 쿼리    
		SELECT empId, name_, ssn, phone, hiredate
			, reg_name, dept_name, job_title, basicpay, extrapay,  pay
		    , pictureCount, filename
			FROM picEmpView
			ORDER BY empId;
		*/
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {

			conn = MySQLConnection80.connect();

			String sql = "SELECT empId, name_, ssn, phone, hiredate\r\n" + 
					"	, reg_name, dept_name, job_title, basicpay, extrapay,  pay\r\n" + 
					"    , pictureCount, filename\r\n" + 
					"	FROM picEmpView\r\n";

			if (key.equals("all")) {
			} else if (key.equals("empId")) {
				sql += " WHERE empId = ?\r\n";
			} else if (key.equals("name_")) {
				sql += " WHERE INSTR(name_,?) > 0 \r\n";
			} else if (key.equals("ssn")) {
				sql += " WHERE INSTR(ssn,?) > 0\r\n";
			} else if (key.equals("phone")) {
				sql += " WHERE INSTR(phone,?) > 0\r\n";
			} else if (key.equals("hiredate")) {
				sql += " WHERE INSTR(hiredate,?) > 0\r\n";
			} else if (key.equals("reg_name")) {
				sql += " WHERE INSTR(reg_name,?) > 0\r\n";
			} else if (key.equals("dept_name")) {
				sql += " WHERE INSTR(dept_name,?) > 0\r\n";
			} else if (key.equals("job_title")) {
				sql += " WHERE INSTR(job_title,?) > 0\r\n";
			}
			sql += " ORDER BY " + order;
			System.out.println("---------------");
			System.out.println(sql);
			
			pstmt = conn.prepareStatement(sql);
			if (key.equals("all")) {
			} else {
				pstmt.setString(1, value);
			}
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String empId = rs.getString("empId");
				String name_ = rs.getString("name_");
				String ssn = rs.getString("ssn");
				String phone = rs.getString("phone");
				Date hiredate = rs.getDate("hiredate");
				String reg_name = rs.getString("reg_name");
				String dept_name = rs.getString("dept_name");
				String job_title = rs.getString("job_title");
				int basicpay = rs.getInt("basicpay");
				int extrapay = rs.getInt("extrapay");
				int pay = rs.getInt("pay");
				int pictureCount = rs.getInt("pictureCount");
				String filename = rs.getString("filename");

				list.add(new Employee(empId, name_, ssn, phone
						, hiredate, reg_name, dept_name, job_title
						, basicpay, extrapay, pay
						, pictureCount, filename));
			}
			rs.close();

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
		
		return list;
	}
	
	//입력
	public int add(Employee emp) {
		/*
		INSERT INTO employees  (empId, name_, ssn, hiredate, phone
			    , regId, deptId, jobId, basicpay, extrapay)
			    VALUES ((SELECT CONCAT('EMP', LPAD(IFNULL(SUBSTR(MAX(empId), 4), 0) + 1, 3, 0)) 
			        AS newId FROM employees E), ?, ?, ?, ?, ?, ?, ?, ?, ?)
		*/
		int result = 0;
		

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {

			conn = MySQLConnection80.connect();

			String sql = "INSERT INTO employees  (empId, name_, ssn, hiredate, phone\r\n"
					+ "    , regId, deptId, jobId, basicpay, extrapay)\r\n"
					+ "    VALUES ((SELECT CONCAT('EMP', LPAD(IFNULL(SUBSTR(MAX(empId), 4), 0) + 1, 3, 0)) \r\n"
					+ "        AS newId FROM employees E), ?, ?, ?\r\n"
					+ "        , ?, ?, ?, ?, ?, ?)";
			System.out.println("---------------");
			System.out.println(sql);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, emp.getName_());
			pstmt.setString(2, emp.getSsn());
			pstmt.setDate(3, emp.getHiredate());
			pstmt.setString(4, emp.getPhone());
			pstmt.setString(5, emp.getRegId());
			pstmt.setString(6, emp.getDeptId());
			pstmt.setString(7, emp.getJobId());
			pstmt.setInt(8, emp.getBasicpay());
			pstmt.setInt(9, emp.getExtrapay());
			result = pstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
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
	
	//삭제
	public int remove(String empId) {
		/*
		DELETE FROM employees WHERE empId = ?
		*/
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = MySQLConnection80.connect();

			String sql = "DELETE FROM employees WHERE empId = ?";
			System.out.println("---------------");
			System.out.println(sql);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, empId);
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
	
	
	//전체 인원수 출력
	public int totalcount() {
		int result = 0;
		
		/*
		//1차 뷰
		CREATE OR REPLACE VIEW empView
		AS
		SELECT E.empId, name_, ssn, phone, hiredate
				, reg_name, dept_name, job_title, basicpay, extrapay
				, (basicpay + extrapay) pay
				, (SELECT COUNT(*) FROM employeePictureList
						WHERE empId = E.empId) pictureCount
				FROM employees E, departments D, jobs j, regions R
				WHERE E.deptid = D.deptid
					AND E.jobid = j.jobid
					AND E.regid = R.regid;

		//2차 뷰
		CREATE OR REPLACE VIEW picEmpView
		AS
		SELECT e.empId, name_, ssn, phone, hiredate
			, reg_name, dept_name, job_title, basicpay, extrapay,  pay
		    , pictureCount, filename
			FROM empView e LEFT JOIN employeePictureList ep
		    ON e.empId = ep.empId;
		   
		//2차 뷰를 이용한 SELECT 쿼리    
		SELECT empId, name_, ssn, phone, hiredate
			, reg_name, dept_name, job_title, basicpay, extrapay,  pay
		    , pictureCount, filename
			FROM picEmpView
			ORDER BY empId;
		*/
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {

			conn = MySQLConnection80.connect();

			String sql = "SELECT COUNT(*) count_\r\n" + 
					"		    FROM picEmpView";
			System.out.println("---------------");
			System.out.println(sql);
			
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				result = rs.getInt("count_");
			}
			rs.close();

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
	
	
	//개인정보 수정 액션을 위한 개인정보 확인하는 메소드
	public Employee employeeInfo(String empId) {
		Employee result = null;
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {

			conn = MySQLConnection80.connect();

			String sql = "SELECT empId, name_, ssn, hiredate, phone\r\n" + 
					"    , regId, deptId, jobId, basicpay, extrapay\r\n" + 
					"    FROM employees\r\n" + 
					"    WHERE empId = ?";
			System.out.println("---------------");
			System.out.println(sql);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, empId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String empId_ = rs.getString("empId");
				String name_ = rs.getString("name_");
				String ssn = rs.getString("ssn");
				String phone = rs.getString("phone");
				Date hiredate = rs.getDate("hiredate");
				String regId = rs.getString("regId");
				String deptId = rs.getString("deptId");
				String jobId = rs.getString("jobId");
				int basicpay = rs.getInt("basicpay");
				int extrapay = rs.getInt("extrapay");
				result = new Employee(empId_, name_, ssn, phone, hiredate, regId, deptId, jobId, basicpay, extrapay);
			}
			rs.close();

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
	
	//직원 정보 수정 메소드
	public int modify(Employee emp) {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = MySQLConnection80.connect();

			String sql = "UPDATE employees\r\n" + 
					"	SET name_=?, ssn=?, hiredate=?, phone=?\r\n" + 
					"		, regId=?, deptId=?, jobId=?\r\n" + 
					"       , basicpay=?, extrapay=?\r\n" + 
					"	WHERE empId=?";
			System.out.println("---------------");
			System.out.println(sql);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, emp.getName_());
			pstmt.setString(2, emp.getSsn());
			pstmt.setDate(3, emp.getHiredate());
			pstmt.setString(4, emp.getPhone());
			pstmt.setString(5, emp.getRegId());
			pstmt.setString(6, emp.getDeptId());
			pstmt.setString(7, emp.getJobId());
			pstmt.setInt(8, emp.getBasicpay());
			pstmt.setInt(9, emp.getExtrapay());
			pstmt.setString(10, emp.getEmpId());
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
	
	

}
