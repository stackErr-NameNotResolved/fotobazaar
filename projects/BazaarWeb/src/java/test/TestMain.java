/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import classes.database.DatabaseConnector;
import classes.picture.Picture;

/**
 *
 * @author Bas
 */
public class TestMain {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        DatabaseConnector.Initialize("192.168.27.10", 3306, "fotobazaar", "admin", "Server01!", false);

        for(int i = 1; i<11; i++)
        {
            Picture.generateNewID();
            Thread.sleep(100);
        }
    }
    
}
