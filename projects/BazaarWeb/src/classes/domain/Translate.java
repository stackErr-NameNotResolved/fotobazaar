/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.domain;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author Bas
 */
public class Translate {

    /**
     * Translates the given text to the given language
     * @param text The text that needs to be translated
     * @param to The language to translate to. Example: 'en' or 'nl'
     * @return The text translated into the given language
     */
    public static String translate(String text, String to)
    {
        return translate(text, "nl", to);
    }
    
    /**
     * Translates the given text to the given language
     * @param text The text that needs to be translated
     * @param from The language the given text is in. Example: 'en' or 'nl'
     * @param to The language to translate to. Example: 'en' or 'nl'
     * @return The text translated into the given language
     */
    public static String translate(String text, String from, String to) {
        if(to.equals(from))
            return text;
        
        String outputLine = "";

        try {
            URL yahoo = new URL("https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20151125T150532Z.311220cf565723b5.fafee97b79c88598f9ce55d35cb132f21b72c57e&text=" + text + "&lang=" + from + "-" + to + "&format=plain");
            URLConnection yahooConnection = yahoo.openConnection();

            try (DataInputStream dis = new DataInputStream(yahooConnection.getInputStream())) {
                while ((outputLine = dis.readLine()) != null) {
                    break;
                }
            }
        } catch (MalformedURLException me) {
            System.out.println("MalformedURLException: " + me);
        } catch (IOException ioe) {
            System.out.println("IOException: " + ioe);
        }

        String[] data = parseData(outputLine);

        if (data[0].equals("200")) {
            // Success
            return data[1];
        } else if (data[0].equals("401")) {
            return "{ERROR: Invalid API key}";
        } else if (data[0].equals("402")) {
            return "{ERROR: Blocked API key}";
        } else if (data[0].equals("403")) {
            return "{ERROR: Exceeded the daily limit on the number of requests}";
        } else if (data[0].equals("404")) {
            return "{ERROR: Exceeded the daily limit on the amount of translated text}";
        } else if (data[0].equals("413")) {
            return "{ERROR: Exceeded the maximum text size}";
        } else if (data[0].equals("422")) {
            return "{ERROR: The text cannot be translated}";
        } else if (data[0].equals("501")) {
            return "{ERROR: The specified translation direction is not supported}";
        } else if (data[0].equals("000")) {
            return "{ERROR: Empty string}";
        }

        return "{ERROR: Unknown error}";
    }

    private static String[] parseData(String input) {
        if (input.equals("")) {
            return new String[]{"000"};
        }

        int index = input.indexOf("code") + 4 + 2;
        String code = input.substring(index, index + 3);

        String value = "";
        try {
            index = input.indexOf("text") + 4 + 4;
            value = input.substring(index, input.length() - 3);
        } catch (Exception ex) {
        }

        return new String[]{code, value};
    }
}
