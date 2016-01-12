package classes.database.orm;

import classes.database.DataTable;
import classes.database.DatabaseConnector;
import classes.database.StatementResult;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class DataModel {
    private static Map<Class<?>, ORMTable> cacheORM = new HashMap<>();

    public DataModel() {
    }

    public static StatementResult select() {
        throw new NotImplementedException();
    }

    public StatementResult insert() {
        ORMTable table = getORMTable();

        // Build query.
        StringBuilder queryBuilder = new StringBuilder();

        queryBuilder.append("INSERT INTO ");
        queryBuilder.append('`').append(table.getName()).append('`');

        // Insert column names.
        queryBuilder.append(" (");
        List<Object> objectParams = new ArrayList<>();
        for (int i = 0; i < table.getColumns().size(); i++) {
            ORMColumn column = table.getColumns().get(i);

            queryBuilder.append('`').append(column.getName()).append('`');
            try {
                column.getField().setAccessible(true);
                objectParams.add(column.getField().get(this));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (i < table.getColumns().size() - 1) {
                queryBuilder.append(',');
            }
        }
        queryBuilder.append(") ");

        // Insert column values.
        queryBuilder.append(" VALUES(");
        for (int i = 0; i < objectParams.size(); i++) {
            Object o = objectParams.get(i);
            queryBuilder.append(objectToSQL(o));

            if (i < objectParams.size() - 1) queryBuilder.append(',');
        }

        queryBuilder.append(");");

        return DatabaseConnector.getInstance().executeNonQuery(queryBuilder.toString());
    }

    public StatementResult update() {
        throw new NotImplementedException();
    }

    public StatementResult delete() {
        ORMTable table = getORMTable();

        // Build query.
        StringBuilder queryBuilder = new StringBuilder();

        queryBuilder.append("DELETE FROM ");
        queryBuilder.append('`').append(table.getName()).append('`');

        // WHERE.
        queryBuilder.append(" WHERE ");
        List<ORMKey> keys = table.getKeys();
        for (int i = 0; i < keys.size(); i++) {
            ORMKey key = keys.get(i);
            queryBuilder.append(keys.get(i).getName());
            queryBuilder.append(" = ");
            try {
                key.getField().setAccessible(true);
                queryBuilder.append(objectToSQL(key.getField().get(this)));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            if (i < keys.size() - 1) queryBuilder.append(" AND ");
        }

        queryBuilder.append(";");

        return DatabaseConnector.getInstance().executeNonQuery(queryBuilder.toString());
    }

    public static <T extends DataModel> T fromId(Class<T> clazz, Object... ids) {
        // Create DataModel.
        T model;
        try {
            model = clazz.getConstructor().newInstance();
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException(String.format("No default constructor declared for the model %s.", clazz.getSimpleName()));
        }

        // SELECT.
        ORMTable table = getORMTable(clazz);
        StringBuilder queryBuilder = new StringBuilder("SELECT ");

        // Insert column names.
        for (int i = 0; i < table.getColumns().size(); i++) {
            ORMColumn column = table.getColumns().get(i);

            queryBuilder.append('`').append(column.getName()).append('`');
            if (i < table.getColumns().size() - 1) queryBuilder.append(',');
        }

        // FROM.
        queryBuilder.append(" FROM ");
        queryBuilder.append('`').append(table.getName()).append('`');

        // WHERE.
        queryBuilder.append(" WHERE ");
        List<ORMKey> keys = table.getKeys();
        for (int i = 0; i < ids.length; i++) {
            Object id = ids[i];
            try {
                Field field = keys.get(i).getField();
                field.setAccessible(true);
                field.set(model, id);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            queryBuilder.append(keys.get(i).getName());
            queryBuilder.append(" = ");
            queryBuilder.append(objectToSQL(id));

            if (i < ids.length - 1) queryBuilder.append(" AND ");
        }

        // Query.
        DataTable rows = DatabaseConnector.getInstance().executeQuery(queryBuilder.toString());

        // Store values in model.
        if (!rows.containsData()) return null;
        Object[] row = rows.getRow(0);

        // Store data from database into model fields.
        List<ORMColumn> columns = table.getColumns();
        for (int i = 0; i < columns.size(); i++) {
            ORMColumn column = columns.get(i);
            try {
                column.getField().setAccessible(true);
                column.getField().set(model, sqlObjectToJava(row[i]));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return model;
    }

    /**
     * Converts an Java object to a SQL friendly representation of use in queries.
     *
     * @param object Object to convert to text.
     * @return SQL friendly textual representation of the Java object.
     */
    public static String objectToSQL(Object object) {
        if (object == null) return "NULL";
        else if (object instanceof String) return String.format("'%s'", object);
        else if (object instanceof Integer) return Integer.toString((Integer) object);
        else if (object instanceof Float) return Float.toString((Float) object);
        else if (object instanceof Double) return Double.toString((Double) object);
        else
            throw new UnsupportedOperationException(String.format("Cannot convert object '%s' to SQL string.", object));
    }

    public static Object sqlObjectToJava(Object object) {
        if (object == null) return null;
        else if (object instanceof BigDecimal) return ((BigDecimal) object).toBigInteger().intValueExact();
        else return object;
    }

    /**
     * Gets the {@link ORMTable} for this {@link DataModel}.
     * If no {@link ORMTable} exists it will be created and cached.
     *
     * @return {@link ORMTable} for this {@link DataModel}.
     */
    public ORMTable getORMTable() {
        Class<? extends DataModel> clazz = getClass();
        if (!cacheORM.containsKey(clazz)) cacheORM.put(clazz, ORMTable.fromClass(clazz));
        return cacheORM.get(clazz);
    }

    /**
     * Gets the {@link ORMTable} for this {@link DataModel}.
     * If no {@link ORMTable} exists it will be created and cached.
     *
     * @param clazz Type of class to get the table from. Must be a {@link DataModel}.
     * @param <T>   Type that extends the {@link DataModel} class.
     * @return {@link ORMTable} for this {@link DataModel}.
     */
    public static <T extends DataModel> ORMTable getORMTable(Class<T> clazz) {
        if (!cacheORM.containsKey(clazz)) cacheORM.put(clazz, ORMTable.fromClass(clazz));
        return cacheORM.get(clazz);
    }
}
