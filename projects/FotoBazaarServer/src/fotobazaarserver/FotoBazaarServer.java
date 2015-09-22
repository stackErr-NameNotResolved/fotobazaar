/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fotobazaarserver;

import database.DataTable;
import database.DatabaseConnector;
import java.sql.SQLException;

/**
 *
 * @author Bas
 */
public class FotoBazaarServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DatabaseConnector.Initialize("192.168.94.5", 3306, "test", "user", "test", false);

        if (DatabaseConnector.Instance.isOpen()) {

            DataTable dt = DatabaseConnector.Instance.executeQuery("SELECT ID, ACCESS, USERNAME, PASSWORD FROM ACCOUNT;");
            Object[] o = dt.getRow(0, "ID,PASSWORD,USERNAME");
            System.out.println(dt.getRowCount());
        }
    }

}
