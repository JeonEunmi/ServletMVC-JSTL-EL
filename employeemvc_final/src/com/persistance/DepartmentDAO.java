package com.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.connection.MySQLConnection80;
import com.domain.*;

public class DepartmentDAO {

	// 출력1
	public List<Department> list1() {
		List<Department> list = new ArrayList<Department>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {

			conn = MySQLConnection80.connect();

			String sql = "SELECT deptId, dept_name\r\n" 
					+ "        FROM departments\r\n" 
					+ "     ORDER BY deptId";
			System.out.println("---------------");
			System.out.println(sql);
			
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String deptId = rs.getString("deptId");
				String dept_name = rs.getString("dept_name");
				list.add(new Department(deptId, dept_name));
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

	// 출력2
	public List<Department> list2() {
		List<Department> list = new ArrayList<Department>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {

			conn = MySQLConnection80.connect();

			String sql = "SELECT deptId, dept_name\r\n"
					+ "  , (SELECT COUNT(*) FROM employees WHERE deptId = d.deptId) count_\r\n"
					+ "  FROM departments d\r\n" 
					+ "  ORDER BY deptId";
			System.out.println("---------------");
			System.out.println(sql);
			
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String deptId = rs.getString("deptId");
				String dept_name = rs.getString("dept_name");
				int count_ = rs.getInt("count_");
				list.add(new Department(deptId, dept_name, count_));
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

	// 입력
	public int add(Department d) {
		int result = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {

			conn = MySQLConnection80.connect();

			String sql = "INSERT INTO departments (deptId, dept_name) \r\n"
					+ "     VALUES (( (SELECT CONCAT('DEPT', LPAD( IFNULL(SUBSTR(MAX(deptId),5),0)+1, 2, 0 )) AS newId FROM departments D) ), ?)";
			System.out.println("---------------");
			System.out.println(sql);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, d.getDept_name());
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

	// 삭제
	public int remove(Department d) {
		int result = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = MySQLConnection80.connect();

			String sql = "DELETE FROM departments\r\n" 
					+ "  WHERE deptId = ?";
			System.out.println("---------------");
			System.out.println(sql);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, d.getDeptId());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
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
	public int modify(Department d) {
		int result = 0;
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = MySQLConnection80.connect();

			String sql = "UPDATE departments SET dept_name=?\r\n"
					+ "	WHERE deptId=?";
			System.out.println("---------------");
			System.out.println(sql);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, d.getDept_name());
			pstmt.setString(2, d.getDeptId());
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