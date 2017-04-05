package ayeleyHashahar.generator.generators;

import ayeleyHashahar.generator.TextGenerator;
import ayeleyHashahar.parameters.GeneratorParameters;
import ayeleyHashahar.parameters.MailGeneratorProperties;
import hebDate.RegularHebrewDate;

import java.io.IOException;

/**
 * @author Ahielg
 * @date 28/11/12
 */
public class VetenChelkenu implements TextGenerator {
    private static final String VETEN_SITE = "http://www.veten.co.il/%s%d.pdf";
    private static final String[] HEBREW_MONTHS = {"nisan", "iyar", "sivan", "tamuz",//first year was eyar.. now its eiar, and now eyar again, and now iyar
            "av", "elul", "tishrei", "cheshvan",
            "kislev", "tevet", "shvat", "adar", "adar-b"};

    public static String getMonthAsString(RegularHebrewDate regularHebrewDate) {
        // if it is a leap year and 12th month //
        if (regularHebrewDate.isHebrewLeapYear() && regularHebrewDate.getHebrewMonth() == 12)
            return "adara";
            //return "adar-a";
        else
            return HEBREW_MONTHS[regularHebrewDate.getHebrewMonth() - 1];
    }

    @Override
    public boolean isToCheck(MailGeneratorProperties mailGeneratorProperties) {
        return true;
    }

    @Override
    public void generateText(GeneratorParameters params, MailGeneratorProperties mailGeneratorProperties) throws IOException {
        RegularHebrewDate regularHebrewDate = new RegularHebrewDate(mailGeneratorProperties.getDate());
        String month = getMonthAsString(regularHebrewDate);
        mailGeneratorProperties.setVetenLink(String.format(VETEN_SITE, month, regularHebrewDate.getHebrewDate()));
    }
}
