/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.domain;

import org.junit.Test;

/**
 *
 * @author Bas
 */
public class AESEncryptionTest {
    
    @Test
    public void testEncryption()
    {
        String encoded = AESEncryption.encrypt("1~Corpelijn~0:0:0:0:0:0:0:1", "Corpelijn");
        
        AESEncryption.decrypt("Corpelijn", "LO+RjPNXnnXauOZN9qn8tMWvqF2QzwC7u6qo9kgaMNI=");
    }
}
