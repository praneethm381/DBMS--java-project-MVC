package studentController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static String DB_URL = "jdbc:mysql://localhost/university";
	private static String DB_USER = "root";
	private static String DB_PASSWORD = "praneeth";
	public static String DRIVER_NAME = "com.mysql.jdbc.Driver";

	public static Connection getConnection() throws SQLException {
		
		Connection connection = null;
		
		try {
			Class.forName(DRIVER_NAME);
			connection = DriverManager.getConnection(DB_URL, DB_USER,
					DB_PASSWORD);
			System.out.println("The connection is successfully obtained");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
}
