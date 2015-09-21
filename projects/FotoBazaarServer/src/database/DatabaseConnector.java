/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bas
 */
public class DatabaseConnector {

    /**
     * Attributes
     */
    private String hostname;
    private int port;
    private String databasename;
    private String username;
    private String password;
    private Connection connection;

    private boolean debugMode;

    public static DatabaseConnector Instance;

    /**
     * Constructors
     */
    private DatabaseConnector() {
    }

    /**
     * Create a new instance of the database connector. Run this method only
     * once. Connecting to the database can take some time.
     *
     * @param hostname The hostname/ip of the database server
     * @param port The portnumber of the database server
     * @param databasename The name of the database running on the server
     * @param username The username needed to connect to the database
     * @param password The password needed to connect to the database
     * @param debugMode Enables or disables the debugmode of the connector
     * @return Returns true if the connection was successfull; otherwise false
     */
    public static boolean Initialize(String hostname, int port, String databasename, String username, String password, boolean debugMode) {
        DatabaseConnector.Instance = new DatabaseConnector();

        if (DatabaseConnector.Instance == null) {
            return false;
        }

        // Set the parameters for connecting to the database
        DatabaseConnector.Instance.hostname = hostname;
        DatabaseConnector.Instance.port = port;
        DatabaseConnector.Instance.databasename = databasename;
        DatabaseConnector.Instance.username = username;
        DatabaseConnector.Instance.password = password;
        DatabaseConnector.Instance.debugMode = debugMode;

        // Connect to the database and return the success result
        return DatabaseConnector.Instance.openConnection();
    }

    /**
     * Methods
     */
    private boolean openConnection() {
        if (debugMode) {
            System.out.println("-------- MySQL JDBC Connection Testing ------------");
        }

        // Check if the database connection driver is availible
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Missing MySQL JDBC Driver");
            return false;
        }

        if (debugMode) {
            System.out.println("MySQL JDBC Driver Found\nOpening connection to the database . . .");
        }

        // Set up the connection to the database
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + this.hostname + ":" + this.port + "/" + this.databasename, this.username, this.password);

        } catch (SQLException e) {
            if (debugMode) {
                e.printStackTrace();
            }
            return false;
        }

        // If the debug mode is enabled, check if the database connection is establisched
        if (debugMode) {
            if (connection != null) {
                System.out.println("Connection to the database is ready");
            } else {
                System.out.println("Failed to make the database connection");
                return false;
            }
        }

        return true;
    }

    public boolean isOpen() {
        if (connection == null) {
            return false;
        }

        try {
            return !connection.isClosed();
        } catch (SQLException ex) {
            return true;
        }
    }

    /**
     * Execute a query to the database
     * @param command The query that needs to be executed
     * @return A ResultSet with the output of the query
     */
    public DataTable executeQuery(String command) {
        if (!this.isOpen()) {
            if(debugMode)
                System.out.println("The connection to the database is not open");
            return null;
        }

        try {
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery(command);
            return new DataTable(rs);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public boolean executeNonQuery(String command) throws SQLException {
        if (!this.isOpen()) {
            throw new SQLException("The connection to the database is not open");
        }

        return false;
    }
}
