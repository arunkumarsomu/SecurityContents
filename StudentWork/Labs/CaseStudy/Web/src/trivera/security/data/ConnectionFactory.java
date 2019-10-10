package trivera.security.data;





import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <p>
 * This component and its source code representation are copyright protected
 * and proprietary to Kim Morello, Worldwide
 *
 * This component and source code may be used for instructional and
 * evaluation purposes only. No part of this component or its source code
 * may be sold, transferred, or publicly posted, nor may it be used in a
 * commercial or production environment, without the express written consent
 * of Trivera Technologies, LLC
 *
 * Copyright (c) 2018 Kim Morello
 * </p>
 * @author Kim Morello.
 */

public class ConnectionFactory {



public static Connection getConnection() {

		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (Exception e) {
			System.out.println("Could not load the HSQLDB driver");
			e.printStackTrace();
			return null;
		}
		Connection c = null;
		try {
			c = DriverManager.getConnection(
					"jdbc:hsqldb:hsql://localhost:9002", "sa", "");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		};
		return c;
}

public static void closeConnection(Connection connection, Statement[] statements) {
    try {
        if (statements != null)
            for (int i = 0; i < statements.length; i++) {
                Statement statement = statements[i];
                if (statement != null) {
                    statement.close();
                }
            }
        connection.close();
    } catch (SQLException e) {
		e.printStackTrace();
		return;
    }
}
}
