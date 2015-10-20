/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author Bas
 */
public class AESEncryptionTest {

    @Test
    public void testEncryption() {
        
        // Set up
        String message = "Hello World!";
        String key = "SecurityKey12345".substring(0, 16);
        
        // Encode a message
        String encoded = AESEncryption.encrypt(message, key);

        // Decode the message again
        String output = AESEncryption.decrypt(key, encoded);

        // Check if the messages are the same
        assertEquals("The data was not encoded or decoded properly", output, message);
    }

    @Test
    public void testBreakEncryption() {
        // Set up
        String message = "Hello World!";
        String key = "SecurityKey12345".substring(0, 16);

        // encode an empty string
        try {
            AESEncryption.encrypt("", key);
            fail("You cannot encrypt an empty message");
        } catch (Exception ex) {
        }
        
        
        // encode with an empty key
        try {
            AESEncryption.encrypt(message, "");
            fail("You cannot encrypt with an empty key");
        } catch (Exception ex) {
        }
        
        // Set up
        String encoded = AESEncryption.encrypt(message, key);
        
        // decode with an empty key
        try {
            AESEncryption.decrypt("", encoded);
            fail("You cannot decrypt with an empty key");
        } catch (Exception ex) {
        }
        
        
        // decode with an message
        try {
            AESEncryption.decrypt(key, "");
            fail("You cannot decrypt an empty message");
        } catch (Exception ex) {
        }
    }
}
