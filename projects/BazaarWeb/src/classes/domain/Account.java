package classes.domain;

import classes.database.DataTable;
import classes.database.DatabaseConnector;
import classes.database.orm.annotations.Column;
import classes.database.orm.DataModel;
import classes.database.orm.annotations.Id;
import classes.database.orm.annotations.Table;

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
            if((int) dt.getDataFromRow(0, "access") >= 0)
                return ELoginStatus.SUCCESS;
            else 
                return ELoginStatus.DISABLED;
        }

        return ELoginStatus.FAILED;
    }
}
