package classes.domain;

import classes.database.orm.annotations.Column;
import classes.database.orm.DataModel;
import classes.database.orm.annotations.Id;
import classes.database.orm.annotations.Table;

@Table
public class Account extends DataModel {
    @Id
    private int id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
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
