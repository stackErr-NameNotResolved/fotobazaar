package classes.domain;

import classes.database.DataTable;
import classes.database.DatabaseConnector;
import classes.database.StatementResult;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Customer {

    private int id;
    private String initials;
    private String lastname;
    private String address;
    private String number;
    private String zipcode;
    private String city;
    private String email;
    private int account_id;
    private EGender gender;
    private String country;

    private Customer() {
    }

    public Customer(String initials, String lastname, int sex, String address, String number, String zipcode, String city, String email, int account_id, String country) {
        this.initials = initials;
        this.lastname = lastname;
        this.gender = (sex == 0 ? EGender.MALE : EGender.FEMALE);
        this.address = address;
        this.number = number;
        this.zipcode = zipcode;
        this.city = city;
        this.email = email;
        this.country = country;

        this.account_id = account_id;
    }

    /**
     * Loads the Customer data with data from the database. Returns null if no
     * Customer was found.
     *
     * @param id
     * @return
     */
    public static Customer fromId(int id) {
        DataTable dt = DatabaseConnector.getInstance().executeQuery("SELECT ID, INITIALS, GENDER, ADDRESS, NUMBER, ZIPCODE, CITY, EMAIL, ACCOUNT_ID, LASTNAME, COUNTRY FROM Customer WHERE ID = ?", id);
        if (dt.containsData()) {
            Customer customer = new Customer();
            Object[] row = dt.getRow(0);
            customer.id = (int) row[0];
            customer.initials = (String) row[1];
            customer.gender = ((int) row[2]) == 0 ? EGender.MALE : EGender.FEMALE;
            customer.address = (String) row[3];
            customer.number = (String) row[4];
            customer.zipcode = (String) row[5];
            customer.city = (String) row[6];
            customer.email = (String) row[7];
            customer.account_id = (int) row[8];
            customer.lastname = (String) row[9];
            customer.country = (String) row[10];
            return customer;
        }

        return null;
    }

    public static Customer fromUsername(String username) {
        DataTable dt = DatabaseConnector.getInstance().executeQuery("SELECT CUSTOMER.ID, INITIALS, GENDER, ADDRESS, NUMBER, ZIPCODE, CITY, EMAIL, ACCOUNT_ID, LASTNAME, COUNTRY FROM Customer "
                + "JOIN ACCOUNT ON ACCOUNT.ID = CUSTOMER.ACCOUNT_ID WHERE USERNAME=?", username);
        if (dt.containsData()) {
            Customer customer = new Customer();
            Object[] row = dt.getRow(0);
            customer.id = (int) row[0];
            customer.initials = (String) row[1];
            customer.gender = ((int) row[2]) == 0 ? EGender.MALE : EGender.FEMALE;
            customer.address = (String) row[3];
            customer.number = (String) row[4];
            customer.zipcode = (String) row[5];
            customer.city = (String) row[6];
            customer.email = (String) row[7];
            customer.account_id = (int) row[8];
            customer.lastname = (String) row[9];
            customer.country = (String) row[10];
            return customer;
        }

        return new Customer();
    }

    public int getId() {
        return id;
    }

    public String getInitials() {
        return initials;
    }

    public String getLastname() {
        return lastname;
    }

    public EGender getGender() {
        return gender;
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

    public String getCountry() {
        return country;
    }

    public String getEmail() {
        return email;
    }

    public int getAccount_id() {
        return account_id;
    }

    /**
     * Inserts the data in this object to the database. Ignores the id. If
     * insert was succesful than this Customer's id is not 0.
     */
    public void insert() {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("INSERT INTO Customer");
        queryBuilder.append("(INITIALS, GENDER, LASTNAME, ADDRESS, NUMBER, ZIPCODE, CITY, COUNTRY, EMAIL, ACCOUNT_ID)");
        queryBuilder.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        id = (int) DatabaseConnector.getInstance().executeInsert(queryBuilder.toString(), initials, gender == EGender.MALE ? 0 : 1, lastname, address, number, zipcode, city, country, email, account_id);
    }

    public void update() {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("UPDATE Customer SET ");
        List<Object> params = new ArrayList<>();

        for (Field f : getClass().getDeclaredFields()) {
            try {
                Object value = f.get(this);
                String name = f.getName();
                if (name.toUpperCase().equals("GENDER")) {
                    queryBuilder.append("GENDER=?, ");
                    params.add(gender == EGender.MALE ? 0 : 1);
                } else if (!name.toUpperCase().equals("ACCOUNT_ID")) {
                    queryBuilder.append(f.getName()).append("=?, ");
                    params.add(value);
                }
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        queryBuilder.deleteCharAt(queryBuilder.length() - 2);
        queryBuilder.append(" WHERE ACCOUNT_ID=?");
        params.add(account_id);

        DatabaseConnector.getInstance().executeInsert(queryBuilder.toString(), params.toArray());
    }

    public boolean delete() {
        if (id <= 0) {
            return false;
        }
        return DatabaseConnector.getInstance().executeNonQuery("DELETE FROM Customer WHERE Id = ?", id) == StatementResult.ROWS_UPDATED;
    }
}
