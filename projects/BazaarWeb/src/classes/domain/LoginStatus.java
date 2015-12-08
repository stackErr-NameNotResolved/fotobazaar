/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.domain;

/**
 *
 * @author Bas
 */
public enum LoginStatus {

    SUCCESS(0),
    FAILED(1),
    DISABLED(2);

    private int intValue;

    LoginStatus(int value) {
        intValue = value;
    }

    public int getValue() {
        return intValue;
    }
}
