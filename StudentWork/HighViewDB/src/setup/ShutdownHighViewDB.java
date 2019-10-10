package src.setup; 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ShutdownHighViewDB {
	public static void main(String[] args) {
		System.out.println("Shutting down the Database");
		Connection c = null;
		Statement s = null;
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			c = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9002/","sa","");
			System.out.println ("connecting....");
			s = c.createStatement();

			s.execute("SHUTDOWN");
			System.out.println("hsql shut down successfully");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (s != null) s.close();
				if (c != null) c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
