package classes.domain;

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

    public void testCRUD() throws Exception {

    }
}