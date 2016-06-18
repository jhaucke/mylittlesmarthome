package com.github.jhaucke.mylittlesmarthome.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to access the database.
 */
public class SQLiteJDBC {

	private Connection c = null;
	private final Logger logger;

	/**
	 * Constructor for {@link SQLiteJDBC}.
	 */
	public SQLiteJDBC() {
		super();
		logger = LoggerFactory.getLogger(SQLiteJDBC.class);

		openDatabase();
	}

	private void openDatabase() {
		try {
			Class.forName("org.sqlite.JDBC");
			Properties config = new Properties();
			config.put("journal_mode", "WAL");
			c = DriverManager.getConnection("jdbc:sqlite:../smarthome.db", config);
		} catch (ClassNotFoundException e) {
			logger.error("message: " + e.getMessage() + " cause: " + e.getCause());
			e.printStackTrace();
		} catch (SQLException e) {
			logger.error(createLogMessage(e));
		}

		logger.info("opened database successfully");
	}

	public void insertPowerData(int actuatorId, int power) {
		try {
			Statement stmt = c.createStatement();
			String sql = "INSERT INTO PowerData (ID_Actuator, Power) VALUES (" + actuatorId + ", " + power + ");";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			logger.error(createLogMessage(e));
		}
		logger.info("power record created successfully");
	}

	public List<Integer> selectTheLast3Minutes(int actuatorId) {
		List<Integer> last3Minutes = new ArrayList<Integer>();
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Power FROM PowerData WHERE ID_Actuator = " + actuatorId
					+ " AND Timestamp > datetime('now', '-3 minutes');");
			while (rs.next()) {
				last3Minutes.add(rs.getInt("Power"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			logger.error(createLogMessage(e));
		}
		logger.info("last 3 minutes selected");
		return last3Minutes;
	}

	public Integer selectStateOfActuator(int actuatorId) {

		Integer actuatorStateId = null;
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ID_ActuatorState FROM Actuator WHERE ID = " + actuatorId + ";");
			while (rs.next()) {
				actuatorStateId = rs.getInt("ID_ActuatorState");
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			logger.error(createLogMessage(e));
		}
		logger.info("current state of actuator selected");
		return actuatorStateId;
	}

	public void updateStateOfActuator(int actuatorId, int actuatorStateId) {
		try {
			Statement stmt = c.createStatement();
			String sql = "UPDATE Actuator SET ID_ActuatorState = " + actuatorStateId + " WHERE ID = " + actuatorId
					+ ";";
			stmt.executeUpdate(sql);
			// AutoCommit
			stmt.close();
		} catch (SQLException e) {
			logger.error(createLogMessage(e));
		}
		logger.info("state of actuator successfully updated");
	}

	private String createLogMessage(SQLException e) {
		return "state: " + e.getSQLState() + " code: " + e.getErrorCode() + " message: " + e.getMessage() + " cause: "
				+ e.getCause();
	}
}
