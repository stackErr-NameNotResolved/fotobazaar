package classes.database.orm;

import classes.database.orm.annotations.Id;

import java.lang.reflect.Field;
import java.util.Arrays;

public class ORMKey extends ORMField {

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
