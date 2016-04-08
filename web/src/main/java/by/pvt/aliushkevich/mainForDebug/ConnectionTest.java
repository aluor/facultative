package by.pvt.aliushkevich.mainForDebug;

import org.apache.log4j.Logger;

import java.sql.*;

public class ConnectionTest {
	// Get actual class name to be printed on
		static Logger logger = Logger.getLogger(ConnectionTest.class.getName());
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/facultative", "root",
				"root");
		if (conn == null) {
			System.out.println("Нет соединения с БД!");
			System.exit(0);
		}
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM facultative.students");
		while (rs.next()) {
			System.out.println(rs.getRow() + ". " + rs.getString("id") + "\t" 
		+ rs.getString("first_name") + "\t" + rs.getString("last_name") +
		"\t" + rs.getString("login") + "\t" + rs.getString("password"));
		}
		logger.debug("Hello this is a debug message");
		stmt.close();
	}
}
