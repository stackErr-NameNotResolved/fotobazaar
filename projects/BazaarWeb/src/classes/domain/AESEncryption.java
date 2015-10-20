/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.domain;


import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bas
 */
public class AESEncryption {

    // AES encryption and decryption key (standard): MvhfwqMBU3kCl0M4
    // Add to that the a enrypted version of the username
    private static String getKey(String username) {
        String key = "MvhfwqMBU3kCl0M4" + username.toLowerCase();

        char[] chars = key.toCharArray();
        for (int i = 0; i < key.length() / 2; i++) {
            int a = (i * 3 + key.length() / 2) % key.length();
            int b = (i * 8 + key.length() / 3) % key.length();

            char temp = chars[a];
            chars[a] = chars[b];
            chars[b] = temp;
        }

        key = new String(chars);

        return key.substring(0, 16);
    }

    public static String encrypt(String password, String username) {
        if(password == null || password.equals(""))
            throw new IllegalArgumentException("Cannot encrypt an empty message");
        
        if(username == null || username.equals(""))
            throw new IllegalArgumentException("Cannot use an empty key as encryption");
        
        try {
            Key key = new SecretKeySpec(getKey(username).getBytes(), "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encrypted = cipher.doFinal(password.getBytes());
            System.out.println("encrypted string:" + Base64.getEncoder().encodeToString(encrypted));
            return new String(Base64.getEncoder().encodeToString(encrypted).getBytes("ISO-8859-1"), "ISO-8859-1");
        } catch (NoSuchAlgorithmException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException | NoSuchPaddingException | UnsupportedEncodingException ex) {
            Logger.getLogger(AESEncryption.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String decrypt(String username, String encrypted) {
        if(encrypted == null || encrypted.equals(""))
            throw new IllegalArgumentException("Cannot decrypt an empty message");
        
        if(username == null || username.equals(""))
            throw new IllegalArgumentException("Cannot use an empty key for decryption");
        
        try {
            Key k = new SecretKeySpec(getKey(username).getBytes(), "AES");
            Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
            c.init(Cipher.DECRYPT_MODE, k);
            byte[] decodedValue = Base64.getDecoder().decode(encrypted.getBytes("ISO-8859-1"));
            byte[] decValue = c.doFinal(decodedValue);
            String decryptedValue = new String(decValue, "ISO-8859-1");
            
            System.out.println("decoded string: " + decryptedValue);
            return decryptedValue;
        } catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | UnsupportedEncodingException ex) {
            Logger.getLogger(AESEncryption.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
