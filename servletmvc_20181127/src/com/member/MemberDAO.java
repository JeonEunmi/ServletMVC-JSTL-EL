package com.member;

import java.sql.*;
import java.util.*;

import com.connection.MySQLConnection80;

public class MemberDAO {

	// ȸ�� ���� �Է� �޼ҵ�
	public int memberAdd(Member m) {
		int result = 0;

		// INSERT ���� �׼�

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = MySQLConnection80.connect();

			String sql = "INSERT INTO members (mid_, name_, phone)\r\n"
					+ "	VALUES ((SELECT CONCAT('M', LPAD(IFNULL(SUBSTR(MAX(mid_), 2), 0) + 1, 2, 0)) AS newId FROM members m)\r\n"
					+ "		, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getName_());
			pstmt.setString(2, m.getPhone());
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

	// ȸ�� ���� ��� �޼ҵ�
	public List<Member> memberList(String key, String value) {
		List<Member> list = new ArrayList<Member>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = MySQLConnection80.connect();

			String sql = "SELECT mid_, name_, phone\r\n" + "	FROM members\r\n";

			if (key.equals("all")) {
				sql += "	ORDER BY mid_";
			}
			if (key.equals("name_")) {
				sql += "WHERE INSTR(name_, ?) > 0";
			}

			if (key.equals("phone")) {
				sql += "WHERE INSTR(phone, ?) > 0";
			}

			pstmt = conn.prepareStatement(sql);
			if (key.equals("all")) {

			}
			if (key.equals("name_") || key.equals("phone")) {
				pstmt.setString(1, value);
			}

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println("a");
				String mid_ = rs.getString("mid_");
				String name_ = rs.getString("name_");
				String phone = rs.getString("phone");
				list.add(new Member(mid_, name_, phone));
			}

			// ResultSet ��ü�� �Ҹ�
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

	// ��ü ī��Ʈ �޼ҵ�
	public int totalcount() {
		int result = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// �����ͺ��̽� ���� ����
			conn = MySQLConnection80.connect();

			String sql = "SELECT COUNT(*) totalcount FROM members";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt("totalcount");
			}
			rs.close();

		} catch (Exception e) {
			// ������ �ܼ�â�� �޽��� ���
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

	// ����
	public int memberDel(String mid_) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		int rs = 0;

		try {
			conn = MySQLConnection80.connect();
			String sql = "DELETE FROM members \r\n" + "    WHERE UPPER(mid_) = UPPER(?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid_);
			rs = pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se2) {
				se2.printStackTrace();
			}
			try {
				MySQLConnection80.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return rs;

	}

	public int memberUpdate(Member m) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		int rs = 0;

		try {
			conn = MySQLConnection80.connect();
			String sql = "UPDATE members SET name_ = ?, phone = ? WHERE mid_ = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getName_());
			pstmt.setString(2, m.getPhone());
			pstmt.setString(3, m.getMid_());
			rs = pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se2) {
				se2.printStackTrace();
			}
			try {
				MySQLConnection80.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		System.out.println(rs);
		return rs;
	}

}
