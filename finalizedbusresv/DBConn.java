package finalizedbusresv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
	public static Connection getConnection() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/bus_reservation_system";
		String username = "root";
		String password = "root";
		
		return DriverManager.getConnection(url, username, password);
	}
	
	public static void main(String[] args) {
		try {
			Connection conn = getConnection();
			System.out.println("Database Connected Successfully!");
			conn.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
