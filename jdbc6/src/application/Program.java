package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Program {

	public static void main(String[] args) {
		
		Connection conn = null;
		Statement st = null;
		
		try {
			conn = DB.getConnection();
			conn.setAutoCommit(false);
			st = conn.createStatement();
			
			int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");
			int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");
			
			System.out.println("Rows1 " + rows1);
			System.out.println("Rows2 " + rows2);
		
			conn.commit();
		
		}
		catch (SQLException e) {
			try {
				conn.rollback();
				throw new db.DbException("Transaction rolled back! Causad by: " + e.getMessage());
			} catch (SQLException e1) {
				throw new db.DbException("Error trying to rollback! Causad by: " + e1.getMessage());
			}
		}
		finally{
			DB.closeStatemente(st);
			DB.closeConnection();
		}

	}
}