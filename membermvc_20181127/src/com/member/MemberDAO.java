package com.member;

import java.sql.*;
import java.util.*;

import com.connection.MySQLConnection80;

public class MemberDAO {
	
	//ȸ�� ���� �Է� �޼ҵ�
	public int memberAdd(Member m) {
		int result = 0;
		
		//INSERT ���� �׼�
		
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
	
	//ȸ�� ���� ��� �� �˻� �޼ҵ�
	public List<Member> memberList(String key, String value) {
		List<Member> list = new ArrayList<Member>();
		
		Connection conn = null;	
		PreparedStatement pstmt = null;
		try {	
			conn = MySQLConnection80.connect();
			
			String sql = "SELECT mid_, name_, phone\r\n" + 
					"	FROM members\r\n";
			
			//�˻� �����
			//mid_�� ��ġ�ϴ� ���� �ۼ�
			//name_, phone�� �κ� �˻� ������ ���� �ۼ�
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
			
			//ResultSet ��ü�� �Ҹ�
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
	
	//��ü ī��Ʈ �޼ҵ�
	public int totalcount() {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			//�����ͺ��̽� ���� ����
			conn = MySQLConnection80.connect();
			
			String sql = "SELECT COUNT(*) totalcount FROM members";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getInt("totalcount");
			}
			rs.close();
			
		} catch (Exception e) {
			//������ �ܼ�â�� �޽��� ���
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
	
	
	//ȸ�� ���� ���� �޼ҵ�
	public int memberRemove(Member m) {
		int result = 0;
		
		//PK ���� �÷��� �������� ���� ����
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
	
	
	//ȸ�� ���� ���� �޼ҵ�
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
