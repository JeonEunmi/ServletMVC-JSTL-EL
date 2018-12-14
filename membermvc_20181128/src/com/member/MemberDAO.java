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
	
	//회원 정보 출력 및 검색 메소드
	public List<Member> memberList(String key, String value) {
		List<Member> list = new ArrayList<Member>();
		
		Connection conn = null;	
		PreparedStatement pstmt = null;
		try {	
			conn = MySQLConnection80.connect();
			
			String sql = "SELECT mid_, name_, phone\r\n" + 
					"	FROM members\r\n";
			
			//검색 진행시
			//mid_는 일치하는 경우로 작성
			//name_, phone는 부분 검색 가능한 경우로 작성
			if (key.equals("all")) {
			} else if (key.equals("mid_")) {
				sql += " WHERE mid_ = ?";
			} else if (key.equals("name_")) {
				sql += " WHERE INSTR(name_, ?)>0";
			} else if (key.equals("phone")) {
				sql += " WHERE INSTR(phone, ?)>0";
			}
			sql += " ORDER BY mid_";
				   
			
			pstmt = conn.prepareStatement(sql);
			if(!key.equals("all")) {
			    pstmt.setString(1, value); 
			}
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
		System.out.println(list);
		
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
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}			
			try {
				MySQLConnection80.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
		
		return result;
	}
	
	
	//회원 정보 삭제 메소드
	public int memberRemove(Member m) {
		int result = 0;
		
		//PK 제약 컬럼을 기준으로 삭제 진행
		//DELETE FROM members WHERE mid_=?

		Connection conn = null;	
		PreparedStatement pstmt = null;
		try {	
			conn = MySQLConnection80.connect();
			
			String sql = "DELETE FROM members WHERE mid_=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getMid_());
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
	
	
	//회원 정보 수정 메소드
	public int memberModify(Member m) {
		int result = 0;
		
		//UPDATE members SET name_=?, phone=? WHERE mid_=?
		
		Connection conn = null;	
		PreparedStatement pstmt = null;
		try {	
			conn = MySQLConnection80.connect();
			
			String sql = "UPDATE members SET name_=?, phone=? WHERE mid_=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getName_());
			pstmt.setString(2, m.getPhone());
			pstmt.setString(3, m.getMid_());
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

}
