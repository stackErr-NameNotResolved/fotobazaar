/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
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

    private Exception error;

    private static DatabaseConnector instance;
    public static DatabaseConnector getInstance() {
        if(instance == null)
        {
            Initialize("192.168.27.10", 3306, "fotobazaar", "admin", "Server01!");
        }
        
        return instance;
    }

    /**
     * Constructors
     */
    private DatabaseConnector() {
    }

    /**
     * Create a new instance of the database connector. Run this method only
     * once. Connecting to the database can take some time.
     *
     * @param hostname     The hostname/ip of the database server
     * @param port         The portnumber of the database server
     * @param databasename The name of the database running on the server
     * @param username     The username needed to connect to the database
     * @param password     The password needed to connect to the database
     * @return Returns true if the connection was successfull; otherwise false
     */
    private static boolean Initialize(String hostname, int port, String databasename, String username, String password) {
        instance = new DatabaseConnector();

        // Set the parameters for connecting to the database
        DatabaseConnector.instance.hostname = hostname;
        DatabaseConnector.instance.port = port;
        DatabaseConnector.instance.databasename = databasename;
        DatabaseConnector.instance.username = username;
        DatabaseConnector.instance.password = password;

        // Connect to the database and return the success result
        return DatabaseConnector.instance.openConnection();
    }

    /**
     * Methods
     */
    private boolean openConnection() {
        // Check if the database connection driver is availible
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            error = new Exception("Missing MySQL JDBC Driver");
            return false;
        }

        // Set up the connection to the database
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + this.hostname + ":" + this.port + "/" + this.databasename, this.username, this.password);
        } catch (SQLException e) {
            error = e;
            return false;
        }

        // Check if the database connection is establisched
        if (connection == null) {
            error = new Exception("Failed to make the database connection");
            return false;
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
     *
     * @param command The query that needs to be executed
     * @param params
     * @return A ResultSet with the output of the query
     */
    public DataTable executeQuery(String command, Object... params) {
        if (!this.isOpen()) {
            error = new Exception("The connection to the database is not open");
            return null;
        }

        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(command);
            for (int i = 0; i < params.length; i++) {
                Object parm = params[i];
                statement.setObject(i + 1, parm);
            }
            return new DataTable(statement.executeQuery());
        } catch (Exception ex) {
            error = ex;
        }

        return null;
    }

    public StatementResult executeNonQuery(String command, Object... params) throws SQLException {
        if (!this.isOpen()) {
            error = new Exception("The connection to the database is not open");
            return StatementResult.ERROR;
        }

        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(command);
            for (int i = 0; i < params.length; i++) {
                Object parm = params[i];
                statement.setObject(i + 1, parm);
            }
            return (statement.executeUpdate() == 0 ? StatementResult.NO_ROWS_UPDATED : StatementResult.ROWS_UPDATED);
        } catch (SQLException ex) {
            error = ex;
        }

        return StatementResult.ERROR;
    }

    public Exception getLastError() {
        return error;
    }

    public String getLastErrorMessage() {
        return error.getMessage();
    }
}
