package classes.domain.models;

import classes.database.DataTable;
import classes.database.DatabaseConnector;
import classes.database.StatementResult;
import classes.database.orm.DataModel;
import classes.database.orm.annotations.Column;
import classes.database.orm.annotations.Id;
import classes.database.orm.annotations.Table;
import classes.domain.AESEncryption;
import classes.domain.ELoginStatus;
import classes.domain.ELoginTypes;

@Table(name = "ACCOUNT")
public class Account extends DataModel {

    @Id(name = "ID")
    private int id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ACCESS")
    private int right;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getRight() {
        return right;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public static ELoginStatus validateCredentials(String username, String password) {
        DataTable dt = DatabaseConnector.getInstance().executeQuery("select * from account where username=? and password=?", username, AESEncryption.encrypt(password, username));
        if (dt.containsData()) {
            if ((int) dt.getDataFromRow(0, "access") >= 0) {
                return ELoginStatus.SUCCESS;
            } else {
                return ELoginStatus.DISABLED;
            }
        }

        return ELoginStatus.FAILED;
    }

    public static boolean hasPermission(String username, ELoginTypes type) {
        DataTable dt = DatabaseConnector.getInstance().executeQuery("select access from account where username=?", username);
        if (dt.containsData()) {
            int data = (int) dt.getDataFromRow(0, "access");
            if (data < 0) {
                return false;
            }
            if (data == 1 && type == ELoginTypes.ADMINISTRATOR) {
                return true;
            } else if (data == 2 && type == ELoginTypes.PRODUCER) {
                return true;
            } else if (data == 3 && type == ELoginTypes.PHOTOGRAPHER) {
                return true;
            } else if (data == 4 && type == ELoginTypes.CUSTOMER) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public static boolean registerNewAccount(String username, String password, Rights right) {

        boolean result = false;
        StatementResult dbResult = null;
        if (username != null && password != null && !username.isEmpty() && !password.isEmpty()) {

            String encryptedPassword = AESEncryption.encrypt(password, username);
            try {
                dbResult = DatabaseConnector.getInstance().executeNonQuery("INSERT INTO account (USERNAME, PASSWORD, ACCESS) VALUES (?,?,?) ", username, encryptedPassword, right.toString());
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            if (dbResult.equals(StatementResult.ERROR) || dbResult.equals(StatementResult.NO_ROWS_UPDATED)) {
                return false;
            }

            result = true;
        } else {
            result = false;
        }

        return result;
    }

    public static enum Rights {
        // Banned.
        BannedCustomer(-3),
        BannedPhotographer(-2),
        BannedProducer(-1),

        // Active.
        Producer(1),
        Photographer(2),
        Customer(3);

        public int getRight() {
            return right;
        }

        int right;

        Rights(int right) {
            this.right = right;
        }

        @Override
        public String toString() {
            return String.valueOf(right);
        }
    }
}
