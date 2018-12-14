package com.persistance;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.connection.MySQLConnection80;

import com.domain.Region;

//(데이터베이스) 저장소 운영 클래스
public class RegionDAO {
	
	//출력1
	public List<Region> list1() {
		List<Region> list = new ArrayList<Region>();
		
		/*
		SELECT regId, reg_name
		    FROM regions
		    ORDER BY regId
		*/
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = MySQLConnection80.connect();

			String sql = "SELECT regId, reg_name\r\n" + 
						" FROM regions\r\n" + 
						" ORDER BY regId ";
			System.out.println("---------------");
			System.out.println(sql);
			
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String regId = rs.getString("regId");
				String reg_name = rs.getString("reg_name");
				list.add(new Region(regId, reg_name));
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
	
	//출력2
	public List<Region> list2() {
		List<Region> list = new ArrayList<Region>();
		
		/*
		SELECT regId, reg_name
		    ,(SELECT COUNT(*) FROM employees 
		            WHERE regId = r.regId) count_
		    FROM regions r
		    ORDER BY regId
		*/
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = MySQLConnection80.connect();

			String sql = "SELECT regId, reg_name\r\n" + 
						" ,(SELECT COUNT(*) FROM employees \r\n" + 
						" WHERE regId = r.regId) count_\r\n" + 
						" FROM regions r\r\n" + 
						" ORDER BY regId";
			System.out.println("---------------");
			System.out.println(sql);
			
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String regId = rs.getString("regId");
				String reg_name = rs.getString("reg_name");
				int count_ = rs.getInt("count_");
				list.add(new Region(regId, reg_name, count_));
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
	public int add(Region r) {
		int result = 0;
		
		/*
		INSERT INTO regions (regId, reg_name)
		    VALUES ((SELECT CONCAT('REG', LPAD(IFNULL(SUBSTR(MAX(regId), 4), 0) + 1, 2, 0)) 
			AS newId FROM regions), ?)
		*/
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = MySQLConnection80.connect();

			String sql = "INSERT INTO regions (regId, reg_name)\r\n"
					+ " VALUES ((SELECT CONCAT('REG', LPAD(IFNULL(SUBSTR(MAX(regId), 4), 0) + 1, 2, 0)) \r\n"
					+ " AS newId FROM regions R), ?)";
			System.out.println("---------------");
			System.out.println(sql);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, r.getReg_name());
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
	
	//삭제
	public int remove(Region r) {
		int result = 0;
		
		/*
		DELETE FROM regions
		    WHERE regId = ?
		*/
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = MySQLConnection80.connect();

			String sql = "DELETE FROM regions\r\n" 
						+ " WHERE regId = ?";
			System.out.println("---------------");
			System.out.println(sql);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, r.getRegId());
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
	

	//수정 메소드
	public int modify(Region r) {
		int result = 0;
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = MySQLConnection80.connect();

			String sql = "UPDATE regions SET reg_name=?\r\n"
					+ "	WHERE regId=?";
			System.out.println("---------------");
			System.out.println(sql);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, r.getReg_name());
			pstmt.setString(2, r.getRegId());
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
