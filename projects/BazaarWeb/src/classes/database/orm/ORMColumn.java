package classes.database.orm;

import classes.database.orm.annotations.Column;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Container class for binding a field to a database table column.
 */
public class ORMColumn extends ORMField {
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
