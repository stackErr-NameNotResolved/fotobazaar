package classes.domain;

import classes.database.DataTable;
import classes.database.DatabaseConnector;
import classes.database.StatementResult;

public class Customer {
    private int id;
    private String name;
    private String address;
    private String number;
    private String zipcode;
    private String city;
    private String email;
    private int account_id;

    private Customer() {
    }

    public Customer(String name, String address, String number, String zipcode, String city, String email, int account_id) {
        this.name = name;
        this.address = address;
        this.number = number;
        this.zipcode = zipcode;
        this.city = city;
        this.email = email;
        this.account_id = account_id;
    }

    /**
     * Loads the Customer data with data from the database.
     * Returns null if no Customer was found.
     */
    public static Customer fromId(int id) {
        DataTable dt = DatabaseConnector.getInstance().executeQuery("SELECT ID, NAME, ADDRESS, NUMBER, ZIPCODE, CITY, EMAIL, ACCOUNT_ID FROM Customer WHERE ID = ?", id);
        if (dt.containsData()) {
            Customer customer = new Customer();
            Object[] row = dt.getRow(0);
            customer.id = (int) row[0];
            customer.name = (String)row[1];
            customer.address = (String)row[2];
            customer.number = (String)row[3];
            customer.zipcode = (String)row[4];
            customer.city = (String)row[5];
            customer.email = (String)row[6];
            customer.account_id = (int) row[7];
            return customer;
        }

        return null;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getNumber() {
        return number;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getCity() {
        return city;
    }

    public String getEmail() {
        return email;
    }

    public int getAccount_id() {
        return account_id;
    }

    /**
     * Inserts the data in this object to the database. Ignores the id.
     * If insert was succesful than this Customer's id is not 0.
     */
    public void insert() {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("INSERT INTO Customer");
        queryBuilder.append("(NAME, ADDRESS, NUMBER, ZIPCODE, CITY, EMAIL, ACCOUNT_ID)");
        queryBuilder.append(" VALUES(?, ?, ?, ?, ?, ?, ?)");
        id = (int) DatabaseConnector.getInstance().executeInsert(queryBuilder.toString(), name, address, number, zipcode, city, email, account_id);
    }

    public boolean delete() {
        if (id <= 0) return false;
        return DatabaseConnector.getInstance().executeNonQuery("DELETE FROM Customer WHERE Id = ?", id) == StatementResult.ROWS_UPDATED;
    }
}