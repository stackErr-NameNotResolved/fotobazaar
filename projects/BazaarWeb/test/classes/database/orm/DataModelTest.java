package classes.database.orm;

import junit.framework.TestCase;

import java.lang.reflect.Field;
import java.util.List;

public class DataModelTest extends TestCase {

    @Table(name = "ExampleTable")
    public class ModelTest extends DataModel {
        @Id
        private int id;

        @Column
        private int age;

        @Column
        private double percentage;

        @Column
        private String name;

        @Id
        private int count;
    }

    public class ModelTest2 extends DataModel {
        private int key;
        @Column
        private String city;
        private String country;
    }

    ModelTest model = new ModelTest();
    ModelTest2 model2 = new ModelTest2();

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    public void testGetTable() throws Exception {
        assertEquals("Table name must be retrieved from the annotation Table.", "ExampleTable", model.getTable());
        assertEquals("Table name must be inferred from the name of the class.", "ModelTest2", model2.getTable());
    }

    public void testGetDataMembers() throws Exception {
        List<Field> data = model.getDataMembers();
        assertEquals("Amount of datamembers must be 5.", 5, data.size());

        if (data.size() == 4) {
            assertEquals("Order of fields is incorrect.", "id", data.get(0).getName());
            assertEquals("Order of fields is incorrect.", "age", data.get(1).getName());
            assertEquals("Order of fields is incorrect.", "percentage", data.get(2).getName());
            assertEquals("Order of fields is incorrect.", "name", data.get(3).getName());
        }

        data = model2.getDataMembers();
        assertEquals("Amount of datamembers must be 1.", 1, data.size());

        if (data.size() == 1) {
            assertEquals("Order of fields is incorrect.", "city", data.get(0).getName());
        }
    }

    public void testGetKeys() throws Exception {
        List<Field> keys = model.getKeys();
        assertTrue("Field keys must be retrieved.", keys.size() > 0);

        keys = model2.getKeys();
        assertTrue("Must not have any keys.", keys.size() <= 0);
    }

    public void testGetColumns() throws Exception {
        List<Field> columns = model.getColumns();
        assertEquals("Amount of columns must be 3.", 3, columns.size());

        if (columns.size() == 3) {
            assertEquals("Order of fields is incorrect.", "age", columns.get(0).getName());
            assertEquals("Order of fields is incorrect.", "percentage", columns.get(1).getName());
            assertEquals("Order of fields is incorrect.", "name", columns.get(2).getName());
        }
    }
}