package com.persistance;

import java.sql.*;

import com.connection.MySQLConnection80;
import com.domain.Picture;

public class PictureDAO {
	
	public int pictureAdd(Picture p) {
		int result = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = MySQLConnection80.connect();

			String sql = "INSERT INTO employeePictureList \r\n" + 
					"	(pid, filename, content, empId)\r\n" + 
					"	VALUES ((SELECT CONCAT('P', LPAD(IFNULL(SUBSTR(MAX(pid), 2), 0) + 1, 2, 0)) AS newId FROM employeePictureList p)\r\n" + 
					"    , ?, ?, ?)";
			System.out.println("---------------");
			System.out.println(sql);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p.getFilename());
			pstmt.setString(2, p.getContent());
			pstmt.setString(3, p.getEmpId());
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
	
	public int pictureModify(Picture p) {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = MySQLConnection80.connect();

			String sql = "UPDATE employeePictureList \r\n" + 
					"	SET filename=?, content=?\r\n" + 
					"    WHERE empId=?";
			System.out.println("---------------");
			System.out.println(sql);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p.getFilename());
			pstmt.setString(2, p.getContent());
			pstmt.setString(3, p.getEmpId());
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
