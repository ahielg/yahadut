package ayeleyHashahar.generator.generators;

import ayeleyHashahar.generator.TextGenerator;
import ayeleyHashahar.parameters.GeneratorParameters;
import ayeleyHashahar.parameters.MailGeneratorProperties;
import hebDate.RegularHebrewDate;

import java.io.IOException;

/**
 * @author Ahielg
 *         Date: 11/28/12
 */
public class VetenChelkenu  implements TextGenerator {
    private static final String veten_site = "http://www.veten.co.il/%s.pdf";
    private static final String[] hebrewMonths = {"nisan", "eiar", "sivan", "tamuz",//first year was eyar.. now its eiar
            "av", "elul", "tishrei", "cheshvan",
            "kislev", "tevet", "shvat", "adar", "adar-b"};

    @Override
    public boolean isToCheck(MailGeneratorProperties mailGeneratorProperties) {
        return true;
    }

    @Override
    public void generateText(GeneratorParameters params, MailGeneratorProperties mailGeneratorProperties) throws IOException {
        RegularHebrewDate regularHebrewDate = new RegularHebrewDate(mailGeneratorProperties.getDate());
        String month = getMonthAsString(regularHebrewDate);
        mailGeneratorProperties.setVetenLink(String.format("http://www.veten.co.il/%s%d.pdf", month, regularHebrewDate.getHebrewDate()));
    }

    public String getMonthAsString(RegularHebrewDate regularHebrewDate) {
        // if it is a leap year and 12th month //
        if (regularHebrewDate.isHebrewLeapYear() && regularHebrewDate.getHebrewMonth() == 12)
            return "adara";
            //return "adar-a";
        else
            return hebrewMonths[regularHebrewDate.getHebrewMonth() - 1];
    }
}
