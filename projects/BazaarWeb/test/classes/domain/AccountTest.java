package classes.domain;

import classes.database.DatabaseConnector;
import classes.domain.models.Account;
import junit.framework.TestCase;

public class AccountTest extends TestCase {

    private Account account;

    public void setUp() throws Exception {
        super.setUp();
        account = new Account();
        account.setUsername("Unit test account");
        account.setPassword("Secretpass of unittest");
        account.setRight(0);
    }
    
    public void testValidateCredentials()
    {
        // Try to log in an user on the normal way
        assertEquals("User cannot log in", Account.validateCredentials("corpelijn", "Welkom01"), ELoginStatus.SUCCESS);
        
        // Try to log in with a wrong password
        assertEquals("User should not be able to log in with wrong password", Account.validateCredentials("corpelijn", "foutje"), ELoginStatus.FAILED);
        
        // Set the account disabled
        DatabaseConnector.getInstance().executeNonQuery("update account set access=? where username=?", -1, "corpelijn"); 
        
        // Try to log in an account that is disabled
        assertEquals("User should not be able to log in with a disabled account", Account.validateCredentials("Corpelijn", "Welkom01"), ELoginStatus.DISABLED);
        
        // Enable the account again
        DatabaseConnector.getInstance().executeNonQuery("update account set access=? where username=?", 1, "Corpelijn");
        
        // Just for the fun of it, test it again
        assertEquals("User cannot log in (2)", Account.validateCredentials("corpelijn", "Welkom01"), ELoginStatus.SUCCESS);
    }
}