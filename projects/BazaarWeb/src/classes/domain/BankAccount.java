/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.domain;

/**
 *
 * @author Baya
 */
public class BankAccount {
    private final String username;
    private final String password;
    private double balance;
    
    public BankAccount(String username, String password, double balance) {
        this.username = username;
        this.password = password;
        this.balance = balance;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public double getBalance() {
        return this.balance;
    }
    
    public boolean checkPassword(String password) {
        if(this.password.equals(password)) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean checkBalance(double amount) {
        if (this.balance > amount) {
            return true;
        } else {
            return false;
        }
    }
    
    public void pay(double amount) {
        this.balance = this.balance - amount;
    }
}
