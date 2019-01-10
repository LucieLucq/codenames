package fr.codenames.dao.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DAOSQL {
	protected Connection connection;

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public void connect() throws SQLException {
		if (this.connection == null) {
			this.connection = DriverManager.getConnection("jdbc:mysql://192.168.1.128:3306/codenames?serverTimezone=UTC",
					"root", "");
		}
	}
}
