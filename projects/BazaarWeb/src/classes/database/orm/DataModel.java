package classes.database.orm;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public abstract class DataModel {
    public void insert() {

    }

    public void update() {

    }

    public void delete() {

    }

    private static Map<Class<?>, Map<String, Object>> cacheORM = new HashMap<>();

    private Object getDataMap(String name) {
        if (cacheORM.containsKey(getClass())) {
            Map<String, Object> map = cacheORM.get(getClass());
            if (map.containsKey(name)) {
                return map.get(name);
            }
        }
        return null;
    }

    private void setDataMap(String name, Object value) {
        if (!cacheORM.containsKey(getClass())) cacheORM.put(getClass(), new HashMap<>());

        Map<String, Object> map = cacheORM.get(getClass());
        map.put(name, value);
    }

    /**
     * Gets the name of the table set for this model. Returns the name of the class if no name was set.
     *
     * @return Name of the table set for this model. Returns the name of the class if no name was set.
     */
    protected String getTable() {
        String data = (String) getDataMap("table");
        if (data != null) return data;

        Table[] annotations = getClass().getAnnotationsByType(Table.class);
        if (annotations.length > 0) {
            data = annotations[0].name();
        } else {
            data = getClass().getSimpleName();
        }
        setDataMap("table", data);
        return data;
    }

    /**
     * Gets the fields prepared for ORM in declarative order. Uses caching for performance.
     *
     * @return Fields prepared for ORM in declarative order. Uses caching for performance.
     */
    @SuppressWarnings("unchecked")
    protected List<Field> getDataMembers() {
        List<Field> data = (List<Field>) getDataMap("members");
        if (data != null) return data;

        data = Arrays.asList(getClass().getDeclaredFields()).stream().filter(f -> f.isAnnotationPresent(Id.class) || f.isAnnotationPresent(Column.class)).collect(Collectors.toList());
        setDataMap("members", data);
        return data;
    }

    /**
     * Gets the fields that are defined as the primary keys of this model.
     * Only includes fields defined with the Id annotation.
     *
     * @return Fields that are defined as the primary keys of this model.
     * @see Id
     */
    protected List<Field> getKeys() {
        List<Field> data = (List<Field>) getDataMap("keys");
        if (data != null) return data;

        data = getDataMembers().stream().filter(f -> f.isAnnotationPresent(Id.class)).collect(Collectors.toList());
        setDataMap("keys", data);
        return data;
    }

    @SuppressWarnings("unchecked")
    protected List<Field> getColumns() {
        List<Field> data = (List<Field>) getDataMap("columns");
        if (data != null) return data;

        data = getDataMembers().stream().filter(f -> f.isAnnotationPresent(Column.class)).collect(Collectors.toList());
        setDataMap("columns", data);
        return data;
    }
}
