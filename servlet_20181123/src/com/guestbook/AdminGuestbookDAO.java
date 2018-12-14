package com.guestbook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.connection.MySQLConnection80;

public class AdminGuestbookDAO {


	// ����¡ ó�� ��� �޼ҵ�
	public List<Guestbook> pageList(int pageStart, int pageCount) {

		/*
		 * �Ϲ� ����� ��ü��� ->����ε� ó�� �Խù� ���� SELECT gid, name_, content, regDate FROM
		 * guestbook WHERE blind = 0;
		 */
		List<Guestbook> result = new ArrayList<Guestbook>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
 
			conn = MySQLConnection80.connect();

			String sql = "SELECT gid, name_, pw, content, regDate, clientIP, blind FROM guestbook guestbook ORDER BY gid DESC LIMIT ?, ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, pageStart);
			pstmt.setInt(2, pageCount);
			
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				
				String gid = rs.getString("gid");
				String name_ = rs.getString("name_");
				String pw = rs.getString("pw");
				String content = rs.getString("content");
				String regDate = rs.getString("regDate");
				String clientIP = rs.getString("clientIP");
				int blind = rs.getInt("blind");

				result.add(new Guestbook(gid, name_, pw, content, regDate, clientIP, blind));
			}

			rs.close();

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
		return result;
	}


	// ��ü ī��Ʈ �޼ҵ�
	public int totalcount() {
		int result = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// �����ͺ��̽� ���� ����
			conn = MySQLConnection80.connect();

			// ���� ���ڿ�
			// ����) ������ ���� ; ����� �Ѵ�.
			String sql = "SELECT COUNT(*) totalcount FROM guestbook";

			pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			// ���� ��� �м�
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


	// ����ε� ó�� �� ����
	public int blind(String gid, int blind) {

		int result = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// �����ͺ��̽� ���� ����
			conn = MySQLConnection80.connect();

			// ���� ���ڿ�
			// ����) ������ ���� ; ����� �Ѵ�.
			String sql = " ";

			if (blind == 0) {
				sql = "UPDATE guestbook SET blind=? WHERE gid =?";
			} else {
				sql = "UPDATE guestbook SET blind=? WHERE gid =?";
			}

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, blind);
			pstmt.setString(2, gid);

			result = pstmt.executeUpdate();

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

}
