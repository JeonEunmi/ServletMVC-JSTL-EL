package com.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.connection.MySQLConnection80;
import com.domain.Login;

public class LoginDAO {
	
	//로그인 메소드
	public Login login(Login l) {
		Login result = null;
		
		/*
		SELECT id_
		    FROM login
		    WHERE id_=? AND pw_=?
		*/
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = MySQLConnection80.connect();

			String sql = "SELECT id_ \r\n" + 
					"FROM login\r\n" + 
					"WHERE id_ = ? AND pw_ = ?";
			System.out.println("---------------");
			System.out.println(sql);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, l.getId_());
			pstmt.setString(2, l.getPw_());
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String id_ = rs.getString("id_");
				result = new Login(id_);
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
	
	//관리자 추가 메소드
	public int add(Login l) {
		//주의) id_ 컬럼은 PK 제약이 지정된 상태
		/*
		INSERT INTO login (id_, pw_)
	    	VALUES (?, ?)
		*/
		Connection conn = null;
		PreparedStatement pstmt = null;

		int result = 0;

		try {
			conn = MySQLConnection80.connect();

			String sql = "INSERT INTO login (id_, pw_) \r\n" + 
						"VALUES (?, ?)";
			System.out.println("---------------");
			System.out.println(sql);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, l.getId_());
			pstmt.setString(2, l.getPw_());
			result = pstmt.executeUpdate();
			
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
	
	
	//패스워드 변경 메소드
	public int modify(Login l) {
		/*
		UPDATE login
		    SET pw_ = ?
		    WHERE id_ = ? 
		        AND pw_ = ?
		*/
		Connection conn = null;
		PreparedStatement pstmt = null;

		int result = 0;

		try {
			conn = MySQLConnection80.connect();

			String sql = "UPDATE login SET pw_ = ? \r\n" + 
						"WHERE id_ = ? AND pw_ = ?";
			System.out.println("---------------");
			System.out.println(sql);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, l.getNewPw_());
			pstmt.setString(2, l.getId_());
			pstmt.setString(3, l.getPw_());
			result = pstmt.executeUpdate();

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

}
