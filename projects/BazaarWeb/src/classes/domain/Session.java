/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.domain;

import classes.database.DatabaseConnector;
import classes.domain.models.Account;

/**
 * @author Bas
 */
public class Session {

    public static String generateSessionData(String username, String ipaddr) {
        String id = DatabaseConnector.getInstance().executeQuery("select id from Account where username=?", username).getDataFromRow(0, "id").toString();
        String data = "";
        if (id != null) {
            data = id + "~";
        }

        data += username + "~" + ipaddr;
        String enc = AESEncryption.encrypt(data, username);

        System.out.println("session: " + enc);
        System.out.println("data: " + data);

        return enc;
    }

    public static boolean checkSessionData(String username, String encrypted, String ipaddr) {
        if (username == null || username.isEmpty() || encrypted == null || encrypted.isEmpty() || ipaddr == null || ipaddr.isEmpty())
            return false;

        String data = AESEncryption.decrypt(username, encrypted);
        if (data == null)
            return false;

        String[] values = data.split("~");

        if (values.length < 3) {
            return false;
        }

        try {
            int id = Integer.parseInt(values[0]);
            String user = (String) DatabaseConnector.getInstance().executeQuery("select username from account where id=?", id).getDataFromRow(0, "username");
            if (!user.equals(username)) {
                return false;
            }
        } catch (Exception ignored) {
        }

        if (!values[1].equals(username))
            return false;

        return values[2].equals(ipaddr);
    }

    public static Account getAccountFromSession(String username, String encrypted, String ipaddr) {
        if (username == null || username.isEmpty() || encrypted == null || encrypted.isEmpty() || ipaddr == null || ipaddr.isEmpty())
            return null;

        String data = AESEncryption.decrypt(username, encrypted);
        if (data == null)
            return null;

        String[] values = data.split("~");

        if (values.length < 3) {
            return null;
        }

        try {
            int id = Integer.parseInt(values[0]);
            String user = (String) DatabaseConnector.getInstance().executeQuery("select username from account where id=?", id).getDataFromRow(0, "username");
            if (user.equalsIgnoreCase(username)) {
                return Account.fromId(Account.class, id);
            }
        } catch (Exception ignored) {
        }

        return null;
    }
}
