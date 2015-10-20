package classes.database.orm;

import classes.database.orm.annotations.Column;
import classes.database.orm.annotations.Id;
import classes.database.orm.annotations.Table;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Container class for binding a {@link DataModel} to a database table.
 */
public class ORMTable {
    /**
     * Name of the table in the database.
     */
    protected String name;
    /**
     * Class that was used to build this {@link ORMTable}.
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
     * @return {@link ORMTable} build from the class.
     */
    public static <T extends DataModel> ORMTable fromClass(Class<T> clazz) {
        if (clazz == null) throw new IllegalArgumentException("Parameter clazz must not be null.");
        if (!DataModel.class.isAssignableFrom(clazz))
            throw new IllegalArgumentException("Parameter clazz must extend DataModel.");

        ORMTable table = new ORMTable();
        table.clazz = clazz;

        // Get table name.
        Optional<Table> tableNameAnnotation = Arrays.asList(clazz.getAnnotationsByType(Table.class)).stream().findFirst();
        if (tableNameAnnotation.isPresent() && tableNameAnnotation.get().name() != null && !tableNameAnnotation.get().name().isEmpty()) {
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
     * Gets the class that was used to build this {@link ORMTable}.
     *
     * @return Class that was used to build this {@link ORMTable}.
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
     * Gets the {@link ORMColumn columns} from the {@link DataModel} in declarative order.
     *
     * @return {@link ORMColumn columns} from the {@link DataModel}.
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