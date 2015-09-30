package classes.database.orm;

import classes.database.DatabaseConnector;
import classes.database.StatementResult;
import classes.database.orm.annotations.Column;
import classes.database.orm.annotations.Id;
import classes.database.orm.annotations.Table;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.lang.reflect.Field;
import java.util.*;

public abstract class DataModel {
    private static Map<Class<?>, ORMTable> cacheORM = new HashMap<>();


    public StatementResult insert() {
        ORMTable table = getORMTable();

        // Build query.
        StringBuilder queryBuilder = new StringBuilder();

        queryBuilder.append("INSERT INTO ");
        queryBuilder.append(table.getName());

        // Insert column names.
        queryBuilder.append(" (");
        List<Object> objectParams = new ArrayList<>();
        for (int i = 0; i < table.getColumns().size(); i++) {
            ORMColumn column = table.getColumns().get(i);

            queryBuilder.append(column.getName());
            try {
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
        for (Object objectParam : objectParams) {
            queryBuilder.append(objectToSQL(objectParam));
        }
        queryBuilder.append(");");

        return DatabaseConnector.getInstance().executeNonQuery(queryBuilder.toString());
    }

    public StatementResult update() {
        throw new NotImplementedException();
    }

    public StatementResult delete() {
        throw new NotImplementedException();
    }

    /**
     * Converts an Java object to a SQL friendly representation of use in queries.
     *
     * @param object Object to convert to text.
     * @return SQL friendly textual representation of the Java object.
     */
    public String objectToSQL(Object object) {
        if (object == null) return "NULL";
        else if (object instanceof String) return String.format("'%s'", object);
        else if (object instanceof Integer) return Integer.toString((Integer) object);
        else if (object instanceof Float) return Float.toString((Float) object);
        else if (object instanceof Double) return Double.toString((Double) object);
        else throw new UnsupportedOperationException("Cannot convert object to SQL string.");
    }

    /**
     * Gets the {@link classes.database.orm.DataModel.ORMTable} for this {@link DataModel}.
     * If no {@link classes.database.orm.DataModel.ORMTable} exists it will be created and cached.
     *
     * @return {@link classes.database.orm.DataModel.ORMTable} for this {@link DataModel}.
     */
    public ORMTable getORMTable() {
        if (!cacheORM.containsKey(getClass())) cacheORM.put(getClass(), DataModel.ORMTable.fromClass(getClass()));
        return cacheORM.get(getClass());
    }

    /**
     * Container class for binding a {@link DataModel} to a database table.
     */
    public static class ORMTable {
        /**
         * Name of the table in the database.
         */
        protected String name;
        /**
         * Class that was used to build this {@link classes.database.orm.DataModel.ORMTable}.
         */
        protected Class<?> clazz;
        protected LinkedHashMap<String, ORMField> fields;
        protected LinkedHashMap<String, ORMKey> keys;
        protected LinkedHashMap<String, ORMColumn> columns;

        private ORMTable() {
            fields = new LinkedHashMap<>();
            keys = new LinkedHashMap<>();
            columns = new LinkedHashMap<>();
        }

        /**
         * Builds an ORM data structure from the class.
         *
         * @param clazz Class to get the ORM data from.
         * @return {@link classes.database.orm.DataModel.ORMTable} build from the class.
         */
        public static <T extends DataModel> ORMTable fromClass(Class<T> clazz) {
            if (clazz == null) throw new IllegalArgumentException("Parameter clazz must not be null.");
            if (!DataModel.class.isAssignableFrom(clazz))
                throw new IllegalArgumentException("Parameter clazz must extend DataModel.");

            ORMTable table = new ORMTable();
            table.clazz = clazz;

            // Get table name.
            Optional<Table> tableNameAnnotation = Arrays.asList(clazz.getAnnotationsByType(Table.class)).stream().findFirst();
            if (tableNameAnnotation.isPresent()) {
                table.name = tableNameAnnotation.get().name();
            } else {
                table.name = clazz.getSimpleName();
            }

            // Get keys and columns.
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Id.class)) {
                    ORMKey key = ORMKey.fromField(field);
                    table.keys.put(key.getName(), key);
                    table.fields.put(key.getName(), key);
                } else if (field.isAnnotationPresent(Column.class)) {
                    ORMColumn column = ORMColumn.fromField(field);
                    table.columns.put(column.getName(), column);
                    table.fields.put(column.getName(), column);
                }
            }

            return table;
        }

        /**
         * Gets the {@link ORMField fields} from the {@link DataModel} in declarative order.
         *
         * @return {@link ORMField fields} from the {@link DataModel}.
         */
        public List<ORMField> getFields() {
            return new ArrayList<>(fields.values());
        }

        /**
         * Gets the class that was used to build this {@link classes.database.orm.DataModel.ORMTable}.
         *
         * @return Class that was used to build this {@link classes.database.orm.DataModel.ORMTable}.
         */
        public Class<?> getClazz() {

            return clazz;
        }

        /**
         * Gets the name of the table in the database.
         *
         * @return Name of the table in the database.
         */
        public String getName() {
            return name;
        }

        /**
         * Gets the {@link ORMKey keys} from the {@link DataModel} in declarative order.
         *
         * @return {@link ORMKey keys} from the {@link DataModel}.
         */
        public List<ORMKey> getKeys() {
            return new ArrayList<>(keys.values());
        }

        /**
         * Gets the {@link classes.database.orm.DataModel.ORMColumn columns} from the {@link DataModel} in declarative order.
         *
         * @return {@link classes.database.orm.DataModel.ORMColumn columns} from the {@link DataModel}.
         */
        public List<ORMColumn> getColumns() {
            return new ArrayList<>(columns.values());
        }

        /**
         * Gets the key of the table by name or null if the key was not found.
         *
         * @param name Name of the key to get.
         * @return Key of the table by name or null if the key was not found.
         */
        public ORMKey getKey(String name) {
            return keys.get(name);
        }

        /**
         * Gets the column of the table by name or null if the column was not found.
         *
         * @param name Name of the column to get.
         * @return Column of the table by name or null if the column was not found.
         */
        public ORMColumn getColumn(String name) {
            return columns.get(name);
        }
    }

    public static class ORMKey extends ORMField {

        private ORMKey() {

        }

        public ORMKey(Field field, String name) {
            this.field = field;
            this.name = name;
        }

        /**
         * Gets the name of the column in the database.
         *
         * @return Name of the column in the database.
         */
        public String getName() {
            return this.name;
        }

        public static ORMKey fromField(Field field) {
            if (field == null) throw new IllegalArgumentException("Parameter field must not be null.");
            if (!field.isAnnotationPresent(Id.class))
                throw new IllegalArgumentException("Field does not have an id annotation.");

            ORMKey key = new ORMKey();
            key.field = field;
            Arrays.asList(field.getAnnotationsByType(Id.class)).stream().findAny().ifPresent(c -> key.name = c.name());

            if (key.name == null || key.name.isEmpty()) {
                key.name = field.getName();
            }

            return key;
        }
    }

    /**
     * Container class for binding a field to a database table column.
     */
    public static class ORMColumn extends ORMField {
        private ORMColumn() {
        }

        /**
         * Relates a field to a column in the database.
         *
         * @param field Field of the class that represents the column.
         * @param name
         */
        public ORMColumn(Field field, String name) {
            this.field = field;
            this.name = name;
        }

        public static ORMColumn fromField(Field field) {
            if (field == null) throw new IllegalArgumentException("Parameter field must not be null.");
            if (!field.isAnnotationPresent(Column.class))
                throw new IllegalArgumentException("Field does not have a column annotation.");

            ORMColumn column = new ORMColumn();
            column.field = field;
            Arrays.asList(field.getAnnotationsByType(Column.class)).stream().findAny().ifPresent(a -> column.name = a.name());

            if (column.name == null || column.name.isEmpty()) {
                column.name = field.getName();
            }

            return column;
        }

        /**
         * Gets the field that represents a column in the database.
         *
         * @return Field that represents a column in the database.
         */
        public Field getField() {
            return field;
        }

        /**
         * Gets the name of the column in the database.
         *
         * @return Name of the column in the database.
         */
        public String getName() {
            return name;
        }
    }
}
