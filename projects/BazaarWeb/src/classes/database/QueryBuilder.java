package classes.database;

import java.sql.Connection;
import java.sql.SQLException;

public class QueryBuilder {
    private Connection connection;
    private boolean wasAutoCommit;

    public static QueryBuilder create() throws SQLException {
        QueryBuilder builder = new QueryBuilder();
        builder.connection = DatabaseConnector.getInstance().getConnection();
        builder.wasAutoCommit = builder.connection.getAutoCommit();

        return builder;
    }

    public void addStatement(String statement, Object... args) throws SQLException {

    }

    public void commit() {

    }

    public void rollback() {

    }
}
