package ayeleyHashahar.util;

import ayeleyHashahar.cons.MailSenderCons;
import hebDate.RegularHebrewDate;

import java.io.IOException;

/**
 * @author Ahielg
 * date: 10/6/13
 */
public class UpdateLines {
    public static void main(String[] args) throws IOException {
        if (args.length != 2 || !isInteger(args[0]) || !isInteger(args[1])) {
            System.out.println("Usage: " + UpdateLines.class.getSimpleName() + " [ParashaNumber] [Lines to add]");
            int i = 0;
            for (String s : RegularHebrewDate.getParshios()) {
                System.out.printf("%s=%d%n", s, i++);
            }
            throw new IllegalArgumentException("you must have two and only two numeric argument");
        }

        int parashaNum = Integer.parseInt(args[0]);
        int linesToAdd = Integer.parseInt(args[1]);
        long parashaLine;
        FileUtils.loadPropertiesFiles();
        for (int i = parashaNum; i < 60; i++) {
            try {
                parashaLine = Long.parseLong(MailSenderCons.dynamic_properties.getProperty(MailSenderCons.PARASHA_LINE + i));
                if (parashaLine != 1) {
        //            System.out.println(MailSenderCons.PARASHA_LINE + i + "=" + String.valueOf(parashaLine + linesToAdd));
                    MailSenderCons.dynamic_properties.setProperty(MailSenderCons.PARASHA_LINE + i, String.valueOf(parashaLine + linesToAdd));
                }
            } catch (Exception ignored) {
            }
        }

        FileUtils.savePropertiesFile(MailSenderCons.dynamic_properties);


    }

    public static boolean isInteger(String str) {
        try {
            //noinspection ResultOfMethodCallIgnored
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException ignored) {
        }
        return false;
    }
}
