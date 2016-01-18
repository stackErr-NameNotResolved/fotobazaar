package classes.domain.models;

import classes.database.DataTable;
import classes.database.DatabaseConnector;
import classes.database.StatementResult;
import classes.database.orm.DataModel;
import classes.database.orm.annotations.Column;
import classes.database.orm.annotations.Id;
import classes.database.orm.annotations.Table;
import classes.domain.AESEncryption;
import classes.domain.ELoginTypes;
import classes.domain.LoginStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * Gets all the {@link Account accounts} in the database.
     *
     * @return All the {@link Account accounts} in the database.
     */
    public static List<Account> getAll() {
        DataTable table = DatabaseConnector.getInstance().executeQuery("SELECT id, username, password, access FROM account");
        ResultSet resultSet = table.getResultSet();
        if (table.containsData()) {
            try {
                List<Account> result = new ArrayList<>(table.getRowCount());
                while (resultSet.next()) {
                    Account a = new Account();

                    a.id = resultSet.getInt(1);
                    a.username = resultSet.getString(2);
                    a.password = resultSet.getString(3);
                    a.right = resultSet.getInt(4);

                    result.add(a);
                }
                return result;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public Photographer getPhotographer() {
        DataTable rows = DatabaseConnector.getInstance().executeQuery("SELECT ID FROM PHOTOGRAPHER WHERE ACCOUNT_ID = ?", getId());
        if (!rows.containsData()) return null;
        try {
            return Photographer.fromId(Photographer.class, rows.getResultSet().getInt("ID"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

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

    public String getRightName() {
        Rights right = Rights.valueOf(getRight());
        if (right == null) return "";
        return right.toString();
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

    public static LoginStatus validateCredentials(String username, String password) {
        DataTable dt = DatabaseConnector.getInstance().executeQuery("select * from account where username=? and password=?", username, AESEncryption.encrypt(password, username));
        if (dt.containsData()) {
            if ((int) dt.getDataFromRow(0, "access") > 0) {
                return LoginStatus.SUCCESS;
            } else {
                return LoginStatus.DISABLED;
            }
        }

        return LoginStatus.FAILED;
    }

    public static boolean hasPermission(String username, ELoginTypes type) {
        DataTable dt = DatabaseConnector.getInstance().executeQuery("select access from account where username=?", username);
        if (dt.containsData()) {
            int data = (int) dt.getDataFromRow(0, "access");
            if (data < 0) {
                return false;
            }

            if (data == 1 && type == ELoginTypes.PRODUCER) {
                return true;
            } else if (data == 2 && type == ELoginTypes.PHOTOGRAPHER) {
                return true;
            } else if (data == 3 && type == ELoginTypes.CUSTOMER) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public static boolean registerNewAccount(String username, String password, Rights right) {
        StatementResult dbResult = null;
        if (username != null && password != null && !username.isEmpty() && !password.isEmpty()) {
            if(DatabaseConnector.getInstance().executeQuery("SELECT * FROM ACCOUNT WHERE USERNAME=?", username).containsData())
                return false;
            
            String encryptedPassword = AESEncryption.encrypt(password, username);
            try {
                long accountId = DatabaseConnector.getInstance().executeInsert("INSERT INTO account (USERNAME, PASSWORD, ACCESS) VALUES (?,?,?) ", username, encryptedPassword, right.right);
                if (right == Rights.Photographer)
                    dbResult = DatabaseConnector.getInstance().executeNonQuery("INSERT INTO photographer (NAME, ACCOUNT_ID) VALUES (?,?) ", username, accountId);
                else
                    dbResult = accountId > 0 ? StatementResult.ROWS_UPDATED : StatementResult.NO_ROWS_UPDATED;

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            if (dbResult != null) {
                if (dbResult.equals(StatementResult.ROWS_UPDATED)) {
                    return true;
                }
            }

            return false;
        } else {
            return false;
        }
    }

    public enum Rights {

        // Banned.
        BannedCustomer(-3),
        BannedPhotographer(-2),
        BannedProducer(-1),

        // Inactive / No acccount.
        Inactive(0),

        // Active.
        Producer(1),
        Photographer(2),
        Customer(3);

        private static Map<Integer, Rights> map = new HashMap<>();

        static {
            for (Rights rights : Rights.values()) {
                map.put(rights.getRight(), rights);
            }
        }

        public static Rights valueOf(int right) {
            return map.get(right);
        }

        public int getRight() {
            return right;
        }

        int right;

        Rights(int right) {
            this.right = right;
        }

        @Override
        public String toString() {
            Rights rights = valueOf(right);
            if (rights == null) return "";
            return rights.name();
        }
    }
}
