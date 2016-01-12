/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author Bas
 */
public class CartTest {
    
    @Test
    public void testAddToCartTest()
    {
        // set up the test parameters
        Cart cart = new Cart();
        
        // Add one object to the cart
        // Total = 1
        cart.addOrder(Item.getItemFromId(1), new Picture(), 1);
        
        assertEquals(1, cart.getOverview().length);
        
        // Add 2 other items to the cart
        // Total = 3
        cart.addOrder(Item.getItemFromId(1), new Picture(), 2);
        cart.addOrder(Item.getItemFromId(1), new Picture(), 2);
        
        assertEquals(3, cart.getOverview().length);
    }
    
    @Test
    public void testEditItemInCart()
    {
        // Set up the test objects
        Cart cart = new Cart();
        cart.addOrder(Item.getItemFromId(1), new Picture(), 1);
        
        // Edit the item from the cart
        OrderItem o = cart.getOrder(0);
        o.setAmount(3);
        
        assertEquals(3, cart.getOrder(0).getAmount());
        
        // Change an other property
        o.setItem(Item.getItemFromId(2));
        
        assertEquals(2, cart.getOrder(0).getItem().getId());
    }
    
    @Test
    public void testDeleteItemFromCart()
    {
        // Set up the test objects
        Cart cart = new Cart();
        cart.addOrder(Item.getItemFromId(1), new Picture(), 1);
        
        // delete the item from the cart
        cart.removeOrder(0);
        
        assertEquals(0, cart.getOverview().length);
    }
    
    @Test
    public void testSaveToCookies()
    {
       // unable to test... cannot save or read cookies offline
    }
    
    @Test
    public void testLoadToCookies()
    {
       // unable to test... cannot save or read cookies offline
    }
}
