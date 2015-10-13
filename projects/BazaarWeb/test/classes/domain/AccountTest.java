package classes.domain;

import classes.database.DatabaseConnector;
import junit.framework.TestCase;
import org.junit.Test;

public class AccountTest extends TestCase {

    private Account account;

    public void setUp() throws Exception {
        super.setUp();
        account = new Account();
        account.setUsername("Unit test account");
        account.setPassword("Secretpass of unittest");
        account.setRight(0);
    }

    public void testCRUD() throws Exception {
        Account account = Account.fromId(Account.class, 7);
        account.delete();
    }
    
    public void testValidateCredentials()
    {
        // Try to log in an user on the normal way
        assertTrue("User cannot log in", Account.validateCredentials("corpelijn", "Welkom01"));
        
        // Try to log in with a wrong password
        assertFalse("User should not be able to log in with wrong password", Account.validateCredentials("corpelijn", "foutje"));
        
        // Set the account disabled
        DatabaseConnector.getInstance().executeNonQuery("update account set access=? where username=?", -1, "corpelijn"); 
        
        // Try to log in an account that is disabled
        assertFalse("User should not be able to log in with a disabled account", Account.validateCredentials("Corpelijn", "Welkom01"));
        
        // Enable the account again
        DatabaseConnector.getInstance().executeNonQuery("update account set access=? where username=?", 1, "Corpelijn");
        
        // Just for the fun of it, test it again
        assertTrue("User cannot log in (2)", Account.validateCredentials("corpelijn", "Welkom01"));
    }
}