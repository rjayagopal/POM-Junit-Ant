package com.raghul.util;

import java.io.*;
import java.sql.*;
import java.util.*;

public class JdbcUtilities {

	private String FILE_NAME = "Jdbc.properties";
	private String DATABASE_TYPE = PropertyHandler.getProperty(FILE_NAME,
			"DATABASE_TYPE");
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private List<String> columnNames = new ArrayList<String>();
	private List<String> columnLabels = new ArrayList<String>();
	private int rowCount;
	private int columnCount;

	public JdbcUtilities() {
		connection = null;
		preparedStatement = null;
		resultSet = null;
		this.makeConnection();
	}

	public Connection getConnection() {
		return connection;
	}

	public PreparedStatement getStatement() {
		return preparedStatement;
	}

	public ResultSet getResultset() {
		return resultSet;
	}

	private void makeConnection() {
		try {
			String driverName = getDriverName();
			String connectionString = getConnectionString() + getDatabaseName();
			String userName = getUsername();
			String password = getPassword();

			Class.forName(driverName);
			connection = DriverManager.getConnection(connectionString,
					userName, password);

		} catch (ClassNotFoundException ex) {
			Log.debug(ex);
		} catch (SQLException ex) {
			Log.debug(ex);
		} catch (Exception ex) {
			Log.fatal(ex);
		}
	}

	private String getDriverName() {
		String PROPERTY_KEY = DATABASE_TYPE + ".DRIVER_NAME";
		String DRIVER_NAME = PropertyHandler.getProperty(FILE_NAME,
				PROPERTY_KEY);
		return DRIVER_NAME;
	}

	private String getConnectionString() {
		String PROPERTY_KEY = DATABASE_TYPE + ".CONNECTION_STRING";
		String CONNECTION_STRING = PropertyHandler.getProperty(FILE_NAME,
				PROPERTY_KEY);
		return CONNECTION_STRING;
	}

	private String getDatabaseName() {
		String PROPERTY_KEY = DATABASE_TYPE + ".DATABASE_NAME";
		String DATABASE_NAME = PropertyHandler.getProperty(FILE_NAME,
				PROPERTY_KEY);
		return DATABASE_NAME;
	}

	private String getUsername() {
		String PROPERTY_KEY = DATABASE_TYPE + ".USERNAME";
		String USERNAME = PropertyHandler.getProperty(FILE_NAME, PROPERTY_KEY);
		return USERNAME;
	}

	private String getPassword() {
		String PROPERTY_KEY = DATABASE_TYPE + ".PASSWORD";
		String PASSWORD = PropertyHandler.getProperty(FILE_NAME, PROPERTY_KEY);
		return PASSWORD;
	}

	public ResultSet executeSelect(String query) {
		try {
			preparedStatement = this.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			this.setResultSetMetadata(resultSet);
		} catch (SQLException ex) {
			Log.debug(ex);
		} catch (Exception ex) {
			Log.fatal(ex);
		}
		return resultSet;
	}

	public ResultSet executeSelect(String query, Object... ids) {
		try {
			preparedStatement = this.prepareStatement(query, ids);
			resultSet = preparedStatement.executeQuery();
			this.setResultSetMetadata(resultSet);
		} catch (SQLException ex) {
			Log.debug(ex);
		} catch (Exception ex) {
			Log.fatal(ex);
		}
		return resultSet;
	}

	private PreparedStatement prepareStatement(String query) {
		try {
			preparedStatement = connection
					.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
		} catch (SQLException ex) {
			Log.debug(ex);
		} catch (Exception ex) {
			Log.fatal(ex);
		}
		return preparedStatement;
	}

	private PreparedStatement prepareStatement(String query, Object... values) {
		try {
			preparedStatement = connection
					.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
			this.setValues(preparedStatement, values);
		} catch (SQLException ex) {
			Log.debug(ex);
		} catch (Exception ex) {
			Log.fatal(ex);
		}
		return preparedStatement;
	}

	private void setValues(PreparedStatement preparedStatement,
			Object... values) {
		try {
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					preparedStatement.setObject(i + 1, values[i]);
				}
			}
		} catch (SQLException ex) {
			Log.debug(ex);
		} catch (Exception ex) {
			Log.fatal(ex);
		}
	}

	public int executeInsert(String query) throws SQLException {
		try {
			preparedStatement = this.prepareInsertStatement(query);
			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
		} catch (SQLException ex) {
			Log.debug(ex);
		} catch (Exception ex) {
			Log.fatal(ex);
		}
		return resultSet.getInt(1);

	}

	public int executeInsert(String query, Object... values)
			throws SQLException {
		try {
			preparedStatement = this.prepareInsertStatement(query, values);
			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
		} catch (SQLException ex) {
			Log.debug(ex);
		} catch (Exception ex) {
			Log.fatal(ex);
		}
		return resultSet.getInt(1);
	}

	private PreparedStatement prepareInsertStatement(String query) {
		try {
			preparedStatement = connection.prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
		} catch (SQLException ex) {
			Log.debug(ex);
		} catch (Exception ex) {
			Log.fatal(ex);
		}
		return preparedStatement;
	}

	private PreparedStatement prepareInsertStatement(String query,
			Object... values) {
		try {
			preparedStatement = connection.prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			this.setValues(preparedStatement, values);
		} catch (SQLException ex) {
			Log.debug(ex);
		} catch (Exception ex) {
			Log.fatal(ex);
		}
		return preparedStatement;
	}

	public void executeUpdate(String query, Object... values) {
		try {
			preparedStatement = this.prepareStatement(query, values);
			preparedStatement.executeUpdate();
		} catch (SQLException ex) {
			Log.debug(ex);
		} catch (Exception ex) {
			Log.fatal(ex);
		}
	}

	public void executeUpdate(String query) {
		try {
			preparedStatement = this.prepareStatement(query);
			preparedStatement.executeUpdate();
		} catch (SQLException ex) {
			Log.debug(ex);
		} catch (Exception ex) {
			Log.fatal(ex);
		}
	}

	public final void disconnect(ResultSet resultSet, Statement statement,
			Connection connection) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException ex) {
				Log.debug(ex);
			} catch (Exception ex) {
				Log.fatal(ex);
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException ex) {
				Log.debug(ex);
			} catch (Exception ex) {
				Log.fatal(ex);
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException ex) {
				Log.debug(ex);
			} catch (Exception ex) {
				Log.fatal(ex);
			}
		}

	}

	private void setResultSetMetadata(ResultSet resultSet) {
		try {
			if (resultSet == null || resultSet.isClosed()) {
				throw new SQLException();
			}
			columnNames.clear();
			columnLabels.clear();

			resultSet.beforeFirst();
			ResultSetMetaData md = resultSet.getMetaData();
			columnCount = md.getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
				columnNames.add(md.getColumnName(i));
				columnLabels.add(md.getColumnLabel(i));
			}
			resultSet.last();
			this.rowCount = resultSet.getRow();
			resultSet.beforeFirst();
		} catch (SQLException ex) {
			Log.debug(ex);
		} catch (Exception ex) {
			Log.fatal(ex);
		}

	}

	public int getRowCount() {
		return rowCount;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public List<String> getColumnNames() {
		return columnNames;
	}

	public List<String> getColumnLabels() {
		return columnLabels;
	}

	public String toCsvString(ResultSet resultSet, char columnSeparator,
			boolean includeColumnNames) throws SQLException {

		if (resultSet == null || resultSet.isClosed()) {
			throw new SQLException();
		}
		resultSet.beforeFirst();
		this.setResultSetMetadata(resultSet);
		int _columnCount = this.getColumnCount();
		String _columnNames;
		String lineSeperator = System.getProperty("line.separator");
		StringBuilder sb = new StringBuilder();
		if (includeColumnNames) {
			List<String> cols = this.getColumnNames();
			_columnNames = cols.toString().replaceAll(" ", "")
					.replaceAll("\\[", "").replaceAll("\\]", "")
					.replaceAll(",", String.valueOf(columnSeparator));
			sb = new StringBuilder(_columnNames);
			sb.append(lineSeperator);
		}

		while (resultSet.next()) {
			for (int i = 1; i <= _columnCount; i++) {
				sb.append(resultSet.getString(i));
				if (i != _columnCount) {
					sb.append(columnSeparator);
				}
			}
			sb.append(lineSeperator);
		}
		return sb.toString();
	}

	public File toCsvFile(ResultSet resultSet, char columnSeparator,
			boolean includeColumnNames, String filePath) throws SQLException,
			IOException {
		String csvString = this.toCsvString(resultSet, columnSeparator,
				includeColumnNames);
		File file = new File(filePath);
		Writer writer = new FileWriter(file);
		try {
			writer.write(csvString);
		} finally {
			writer.flush();
			writer.close();
		}
		return file;
	}

	public File toCsvFile(ResultSet resultSet, char columnSeparator,
			boolean includeColumnNames, File file) throws SQLException,
			IOException {
		String csvString = this.toCsvString(resultSet, columnSeparator,
				includeColumnNames);
		Writer writer = new FileWriter(file);
		try {
			writer.write(csvString);
		} finally {
			writer.flush();
			writer.close();
		}
		return file;
	}

	public static List<HashMap<String, Object>> toListString(ResultSet resultSet)
			throws SQLException {
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		ResultSetMetaData md = resultSet.getMetaData();
		int columns = md.getColumnCount();
		while (resultSet.next()) {
			HashMap<String, Object> row = new HashMap<String, Object>(columns);
			for (int i = 1; i <= columns; ++i) {
				row.put(md.getColumnName(i), resultSet.getObject(i));
			}
			list.add(row);
		}
		return list;
	}

}