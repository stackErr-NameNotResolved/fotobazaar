/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Baya
 */
public class BankTest {

    BankAccount bank1;
    BankAccount bank2;
    BankAccount bank3;

    public BankTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        bank1 = new BankAccount("veel", "geld", 100.0);
        bank2 = new BankAccount("veel", "geld", 200.0);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAccount() {
        assertFalse(bank1.equals(bank2));
        bank3 = bank1;
        assertSame(bank1, bank3);
        assertEquals(bank1.getUsername(), bank3.getUsername());
    }
    
    @Test
    public void testBalance() {
        Double amount = 150.0;
        assertEquals(bank1.getBalance(), 100,0);
        assertEquals(bank2.getBalance(), 200,0);
        assertFalse(bank1.checkBalance(amount));
        assertTrue(bank2.checkBalance(amount));
        bank2.pay(amount);
        assertEquals(bank2.getBalance(), 50,0);
    }
}
