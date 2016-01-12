package classes.domain;

import classes.database.DatabaseConnector;
import classes.domain.models.Account;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class AccountTest extends TestCase {

    private Account account;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        account = new Account();
        account.setUsername("Unit test account");
        account.setPassword("Secretpass of unittest");
        account.setRight(0);
    }
    
    @Test
    public void testValidateCredentials()
    {
        // Try to log in an user on the normal way
        assertEquals("User cannot log in", Account.validateCredentials("corpelijn", "Welkom01"), LoginStatus.SUCCESS);
        
        // Try to log in with a wrong password
        assertEquals("User should not be able to log in with wrong password", Account.validateCredentials("corpelijn", "foutje"), LoginStatus.FAILED);
        
        // Set the account disabled
        DatabaseConnector.getInstance().executeNonQuery("update account set access=? where username=?", -1, "corpelijn"); 
        
        // Try to log in an account that is disabled
        assertEquals("User should not be able to log in with a disabled account", Account.validateCredentials("Corpelijn", "Welkom01"), LoginStatus.DISABLED);
        
        // Enable the account again
        DatabaseConnector.getInstance().executeNonQuery("update account set access=? where username=?", 1, "Corpelijn");
        
        // Just for the fun of it, test it again
        assertEquals("User cannot log in (2)", Account.validateCredentials("corpelijn", "Welkom01"), LoginStatus.SUCCESS);
    }
    
    @Test
    public void testRegisterNewAccount(){
        //Arrange
        final boolean expected1 = true;
        final boolean expected2 = true;
        final boolean expected3 = false;
        final boolean expected4 = false;
        
        boolean actual1;
        final String username1 = "test1";
        final String password1 = "test1";
        final Account.Rights right1 = Account.Rights.Producer;
        
        boolean actual2;
        final String username2 = "test2";
        final String password2 = "test2";
        final Account.Rights right2 = Account.Rights.BannedCustomer;
        
        boolean actual3;
        final String username3 = "";
        final String password3 = "test3";
        final Account.Rights right3 = Account.Rights.Photographer;
        
        boolean actual4;
        final String username4 = "test4";
        final String password4 = "";
        final Account.Rights right4 = Account.Rights.Customer;
        //Act
        
        actual1 = Account.registerNewAccount(username1, password1, right1);
        
        actual2 = Account.registerNewAccount(username2, password2, right2);
        
        actual3 = Account.registerNewAccount(username3, password3, right3);
        
        actual4 = Account.registerNewAccount(username4, password4, right4);
                
        //Assert
        Assert.assertEquals("Should return true", expected1, actual1);
        Assert.assertEquals("Should return true", expected2, actual2);
        Assert.assertEquals("Should return false", expected3, actual3);
        Assert.assertEquals("Should return false", expected4, actual4);
        
        // Delete the created data
        DatabaseConnector.getInstance().executeNonQuery("DELETE FROM ACCOUNT WHERE USERNAME=?", username1);
        DatabaseConnector.getInstance().executeNonQuery("DELETE FROM ACCOUNT WHERE USERNAME=?", username2);
        DatabaseConnector.getInstance().executeNonQuery("DELETE FROM ACCOUNT WHERE USERNAME=?", username3);
        DatabaseConnector.getInstance().executeNonQuery("DELETE FROM ACCOUNT WHERE USERNAME=?", username4);
    }    
}