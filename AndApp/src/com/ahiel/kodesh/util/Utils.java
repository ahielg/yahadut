package com.ahiel.kodesh.util;

/**
 * User: ahiel
 * Date: 09/12/12
 * Time: 23:44
 */
public class Utils {
    private Utils() {
    }

    public static String reverse(String paramString) {
        StringBuilder str = new StringBuilder();
        char[] arrayOfChar = paramString.toCharArray();
        for (int i = -1 + arrayOfChar.length; ; i--) {
            if (i < 0)
                return str.toString();
            str.append(arrayOfChar[i]);
        }
    }
}
