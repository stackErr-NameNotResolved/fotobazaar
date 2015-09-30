package classes.database.orm;

import classes.database.orm.annotations.Column;
import classes.database.orm.annotations.Id;
import classes.database.orm.annotations.Table;
import junit.framework.TestCase;

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

        @Id(name = "Test")
        private int count;
    }

    public class ModelTest2 extends DataModel {
        private int key;
        @Column(name = "City_2")
        private String city;
        private String country;
    }

    ModelTest model = new ModelTest();
    ModelTest2 model2 = new ModelTest2();

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    public void testORMTableModelTest() throws Exception {
        DataModel.ORMTable table = model.getORMTable();
        assertEquals("Class in ORMTable is not the same as the model it was from.", model.getClass(), table.getClazz());
        assertEquals("Table name was not properly retrieved.", "ExampleTable", table.getName());

        // Keys.
        assertNotNull("Key should exist on model.", table.getKey("id"));
        assertNotNull("Key should exist on model.", table.getKey("Test"));
        assertNull("Key should NOT exist on model.", table.getKey("test"));
        assertNull("Key should NOT exist on model.", table.getKey("id2"));
        assertNull("Key should NOT exist on model.", table.getKey("id_"));
        assertNull("Key should NOT exist on model.", table.getKey("Id"));
        assertNull("Key should NOT exist on model.", table.getKey("iD"));

        assertEquals("There should be 2 keys.", 2, table.getKeys().size());

        List<DataModel.ORMKey> keyList = table.getKeys();
        assertEquals("First key should be id.", "id", keyList.get(0).getName());
        assertEquals("Last key should be Test.", "Test", keyList.get(1).getName());

        // Columns.
        assertEquals("There should be 3 columns.", 3, table.getColumns().size());

        List<DataModel.ORMColumn> columnList = table.getColumns();
        assertEquals("First column should be age.", "age", columnList.get(0).getName());
        assertEquals("Second column should be percentage.", "percentage", columnList.get(1).getName());
        assertEquals("Last column should be name.", "name", columnList.get(2).getName());
    }

    public void testORMTableModelTest2() throws Exception {
        DataModel.ORMTable table = model2.getORMTable();
        assertEquals("Class in ORMTable is not the same as the model it was from.", model2.getClass(), table.getClazz());
        assertEquals("Table name was not properly retrieved.", "ModelTest2", table.getName());

        // Keys.
        assertEquals("There should be 0 keys.", 0, table.getKeys().size());

        // Columns.
        assertEquals("There should be 1 column.", 1, table.getColumns().size());

        List<DataModel.ORMColumn> columnList = table.getColumns();
        assertEquals("First column should be City_2.", "City_2", columnList.get(0).getName());

    }
}