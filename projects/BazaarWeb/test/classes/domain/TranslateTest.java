/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.domain;

import org.junit.Test;

/**
 *
 * @author Bas
 */
public class TranslateTest {

    @Test
    public void testTranslator() {
        System.out.println(Translate.translate("mok, wit, met handvat", "nl", "en"));
        
        System.out.println(Translate.translate("", "nl", "en"));
    }
}
