/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import classes.database.DataTable;
import classes.database.DatabaseConnector;

/**
 *
 * @author Bas
 */
public class TestMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DatabaseConnector.Initialize("192.168.27.10", 3306, "fotobazaar", "admin", "Server01!", false);

        if (DatabaseConnector.Instance.isOpen()) {

            DataTable dt = DatabaseConnector.Instance.executeQuery("SELECT * FROM ACCOUNT");
           
        }
    }
    
}
