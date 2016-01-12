package classes.domain.models;

import classes.database.orm.DataModel;
import classes.database.orm.annotations.Column;
import classes.database.orm.annotations.Id;
import classes.database.orm.annotations.Table;

@Table(name = "PHOTOGRAPHER")
public class Photographer extends DataModel {
    @Id(name = "ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ACCOUNT_ID")
    private int accountId;

    public Photographer() {
    }

    public Photographer(String name, int accountId) {
        this.name = name;
        this.accountId = accountId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAccountId() {
        return accountId;
    }
}
