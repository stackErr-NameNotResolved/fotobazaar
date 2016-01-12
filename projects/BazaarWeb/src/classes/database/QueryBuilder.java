package classes.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryBuilder {
    private Connection connection;
    private boolean wasAutoCommit;

    private List<PreparedStatement> preparedStatements;

    private QueryBuilder() {
        preparedStatements = new ArrayList<>();
    }

    public static QueryBuilder create() throws SQLException {
        QueryBuilder builder = new QueryBuilder();
        builder.connection = DatabaseConnector.getInstance().getConnection();
        builder.wasAutoCommit = builder.connection.getAutoCommit();

        return builder;
    }

    public void addStatement(String statement, Object... args) throws SQLException {
        PreparedStatement prepare = connection.prepareStatement(statement);
        for (int i = 0; i < args.length; i++) {
            prepare.setObject(i + 1, args[i]);
        }
        preparedStatements.add(prepare);
    }

    public void commit() {

    }

    public void rollback() {

    }
}
