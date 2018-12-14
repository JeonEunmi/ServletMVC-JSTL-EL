package com.guestbook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.connection.MySQLConnection80;

public class GuestbookDAO {

	// 페이징 처리 출력 메소드
	public List<Guestbook> pageList(int pageStart, int pageCount) {

		/*
		 * 일반 사용자 전체출력 ->블라인드 처리 게시물 제외 SELECT gid, name_, content, regDate FROM
		 * guestbook WHERE blind = 0;
		 */
		List<Guestbook> result = new ArrayList<Guestbook>();

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = MySQLConnection80.connect();

			String sql = "SELECT gid, name_, content, DATE_FORMAT(regDate, '%Y-%m-%d') regDate FROM guestbook guestbook WHERE blind = 0 ORDER BY gid DESC LIMIT ?, ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, pageStart);
			pstmt.setInt(2, pageCount);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				String gid = rs.getString("gid");
				String name_ = rs.getString("name_");
				String content = rs.getString("content");
				String regDate = rs.getString("regDate");

				result.add(new Guestbook(gid, name_, content, regDate));
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

	// blind 안된 자료 카운트 메소드
	public int notBlindCount() {
		int result = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// 데이터베이스 연결 설정
			conn = MySQLConnection80.connect();

			// 쿼리 문자열
			// 주의) 쿼리문 끝에 ; 없어야 한다.
			String sql = "SELECT COUNT(*) totalcount FROM guestbook WHERE blind = 0";

			pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			// 쿼리 결과 분석
			while (rs.next()) {
				result = rs.getInt("totalcount");
			}

			rs.close();

		} catch (Exception e) {
			// 서버의 콘솔창에 메시지 출력
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

	// 글쓰기 입력 메소드
	public int gusetbookAdd(String name, String pw, String content, String clientIp) {
		int result = 0;

		// INSERT 쿼리 액션

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = MySQLConnection80.connect();

			String sql = "INSERT INTO guestbook (gid, name_, pw, content, regDate, clientIP, blind)\r\n"
					+ "	VALUES ((SELECT CONCAT('G', LPAD(IFNULL(SUBSTR(MAX(gid), 2), 0) + 1, 3, 0)) AS gid FROM guestbook g)\r\n"
					+ "	, ?, ?, ?, NOW(), ?, 0)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, pw);
			pstmt.setString(3, content);
			pstmt.setString(4, clientIp);

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
	public int guestbookDel(String gid, String pw) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		int rs = 0;

		try {
			conn = MySQLConnection80.connect();
			String sql = "DELETE FROM guestbook \r\n" + "    WHERE UPPER(gid) = UPPER(?) AND pw = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, gid);
			pstmt.setString(2, pw);
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

}
