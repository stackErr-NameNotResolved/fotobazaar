/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.database;

import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Bas
 */
public class DatabaseConnectorTest {

    @Before
    public void setupTest() {
        DatabaseConnector.getInstance().executeNonQuery("insert into account (USERNAME, PASSWORD, ACCESS) values (?,?,?)", "testKlant", "testWachtwoord", 4);
    }

    @After
    public void endTest() {
        DatabaseConnector.getInstance().executeNonQuery("delete from account where username=?", "testKlant");
    }

    @Test
    public void testInsert() {
        // Test inserting a user account
        StatementResult sr = DatabaseConnector.getInstance().executeNonQuery("insert into account (USERNAME, PASSWORD, ACCESS) values (?,?,?)", "testKlant1", "testWachtwoord", 4);
        assertTrue("No rows were inserted", sr.toString().equals(StatementResult.ROWS_UPDATED.toString()));

        // Try to break the system by inserting the same username
        try {
            sr = DatabaseConnector.getInstance().executeNonQuery("insert into account (USERNAME, PASSWORD, ACCESS) values (?,?,?)", "testKlant1", "testWachtwoord", 4);
            fail("Usernames are supposed to be unique from others");
        } catch (IllegalStateException ex) {
        }

        // Try to break the system by inserting a row with a missing value
        try {
            sr = DatabaseConnector.getInstance().executeNonQuery("insert into account (USERNAME, PASSWORD, ACCESS) values (?,?)", "testKlant2", "testWachtwoord");
            fail("Missing value is not detected");
        } catch (IllegalStateException ex) {
        }

        // Delete all
        DatabaseConnector.getInstance().executeNonQuery("delete from account where username=?", "testKlant1");
        DatabaseConnector.getInstance().executeNonQuery("delete from account where username=?", "testKlant2");
    }

    @Test
    public void testSelect() {
        // Test selecting all accounts
        DataTable dt = DatabaseConnector.getInstance().executeQuery("select * from account where access=? or access=?", 1, -1);
        assertTrue("There should be 6 admin accounts", dt.getRowCount() == 6);

        // Read selective data from the database
        dt = DatabaseConnector.getInstance().executeQuery("select * from account where username=?", "testKlant");
        try {

            assertTrue("The test account is not readable", dt.getDataFromRow(0, "username").equals("testKlant"));
            assertTrue("The test account is not a customer", (int) dt.getDataFromRow(0, "access") == 4);
        } catch (Exception ex) {
            fail("No data found in the database");
        }
    }

    @Test
    public void testUpdate() {
        // Test updateing the password of the test account
        StatementResult sr = DatabaseConnector.getInstance().executeNonQuery("update account set password=? where username=?", "Hello01!", "testKlant");
        assertTrue("The information in the database was not updated", sr == StatementResult.ROWS_UPDATED);

        // Use a select query to check the result
        DataTable dt = DatabaseConnector.getInstance().executeQuery("select * from account where username=?", "testKlant");
        assertTrue("The information in the database was not updated", dt.getDataFromRow(0, "password").equals("Hello01!"));
    }

    @Test
    public void testDelete() {
        // Insert some data
        DatabaseConnector.getInstance().executeNonQuery("insert into account (USERNAME, PASSWORD, ACCESS) values (?,?,?)", "testKlant1", "testWachtwoord1", 4);
        DatabaseConnector.getInstance().executeNonQuery("insert into account (USERNAME, PASSWORD, ACCESS) values (?,?,?)", "testKlant2", "testWachtwoord2", 4);

        // Delete the test account 
        StatementResult sr = DatabaseConnector.getInstance().executeNonQuery("delete from account where username=?", "testKlant1");
        assertTrue("The specified row was not deleted", sr == StatementResult.ROWS_UPDATED);

        // Delete the test account 
        sr = DatabaseConnector.getInstance().executeNonQuery("delete from account where username=?", "testKlant2");
        assertTrue("The second row was not deleted", sr == StatementResult.ROWS_UPDATED);
    }
}
