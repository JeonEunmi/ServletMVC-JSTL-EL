package com.member;

import java.sql.*;
import java.util.*;

import com.connection.MySQLConnection80;

public class MemberDAO {
	
	//회원 정보 입력 메소드
	public int memberAdd(Member m) {
		int result = 0;
		
		//INSERT 쿼리 액션
		
		Connection conn = null;	
		PreparedStatement pstmt = null;
		try {	
			conn = MySQLConnection80.connect();
			
			String sql = "INSERT INTO members (mid_, name_, phone)\r\n" + 
					"	VALUES ((SELECT CONCAT('M', LPAD(IFNULL(SUBSTR(MAX(mid_), 2), 0) + 1, 2, 0)) AS newId FROM members m)\r\n" + 
					"		, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getName_());
			pstmt.setString(2, m.getPhone());
			result = pstmt.executeUpdate();
			
		} catch(Exception e) {
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
	
	//회원 정보 출력 메소드
	public List<Member> memberList() {
		List<Member> list = new ArrayList<Member>();
		
		Connection conn = null;	
		PreparedStatement pstmt = null;
		try {	
			conn = MySQLConnection80.connect();
			
			String sql = "SELECT mid_, name_, phone\r\n" + 
					"	FROM members\r\n" + 
					"	ORDER BY mid_";
			
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String mid_ = rs.getString("mid_");
				String name_ = rs.getString("name_");
				String phone = rs.getString("phone");
				list.add(new Member(mid_, name_, phone));
			}
			
			//ResultSet 객체의 소멸
			rs.close();
			
		} catch(Exception e) {
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
	
	//전체 카운트 메소드
	public int totalcount() {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			//데이터베이스 연결 설정
			conn = MySQLConnection80.connect();
			
			String sql = "SELECT COUNT(*) totalcount FROM members";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getInt("totalcount");
			}
			rs.close();
			
		} catch (Exception e) {
			//서버의 콘솔창에 메시지 출력
			e.printStackTrace();
		} finally {
			try {
				MySQLConnection80.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
		
		return result;
	}

}
