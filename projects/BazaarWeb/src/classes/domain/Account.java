package classes.domain;

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
}
