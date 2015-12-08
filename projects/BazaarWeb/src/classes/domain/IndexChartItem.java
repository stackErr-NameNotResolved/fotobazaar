/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.domain;

/**
 *
 * @author Jip
 */
public class IndexChartItem {

    private final String pictureCode;
    private final int itemId;
    private final int amount;
    private String name;
    private String address;
    private String zipcode;
    private String city;
    private String email;

    public IndexChartItem(String pictureCode, int itemId, int amount, String name, String address, String zipcode, String city, String email) {
        this.pictureCode = pictureCode;
        this.itemId = itemId;
        this.amount = amount;
        this.name = name;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.email = email;
    }

    public int getAmount() {
        return amount;
    }

    public int getItemId() {
        return itemId;
    }

    public String getPictureCode() {
        return pictureCode;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getZipcode() {
        return zipcode;
    }

}
