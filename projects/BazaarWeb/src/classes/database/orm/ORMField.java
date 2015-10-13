package classes.database.orm;

import java.lang.reflect.Field;

public abstract class ORMField {
    protected String name;
    protected Field field;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }
}
