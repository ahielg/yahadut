package ayeleyHashahar.generator.generators;

import ayeleyHashahar.cons.MailSenderCons;
import ayeleyHashahar.generator.TextGenerator;
import ayeleyHashahar.parameters.GeneratorParameters;
import ayeleyHashahar.parameters.MailGeneratorProperties;
import ayeleyHashahar.util.FileUtils;
import hebDate.RegularHebrewDate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * User: ahiel
 * Date: 06/11/12
 * Time: 23:37
 */
public class ParashaGenerator implements TextGenerator {
    public static boolean generalIsToCheck(MailGeneratorProperties mailGeneratorProperties) {
        RegularHebrewDate date = new RegularHebrewDate(mailGeneratorProperties.getDate());
        return mailGeneratorProperties.isParasha() && (RegularHebrewDate.getNextParashaNumOnShabat(mailGeneratorProperties.getDate()) != -1)
                &&/*not 9 Be'av*/ (!"תשעה באב".equals(date.getHoliday()));
    }

    private static boolean nextParasha(String line) {
        return (line.trim().startsWith("פרשת ") && line.length() < 100);
    }

    @Override
    public boolean isToCheck(MailGeneratorProperties mailGeneratorProperties) {
        return generalIsToCheck(mailGeneratorProperties);
    }

    @Override
    public void generateText(GeneratorParameters params, MailGeneratorProperties mailGeneratorProperties) throws IOException {

        File inputFile = new File(params.getParashaFilename());
        final int numOfTopics = Integer.parseInt(MailSenderCons.properties.getProperty(MailSenderCons.PARASHA_TOPIC_NUMBERS));
        String line = "";
        int counter = 0;
        long lineNum = 0;
        StringBuilder fullString = new StringBuilder(1000);

        int parashaNum = params.getParashaNum();
        String parasha;
        long parashaLine = params.getParashaLineNum();
        if (parashaNum > 52) {
            parasha = RegularHebrewDate.getParasha(parashaNum);
            int rand = (int) (Math.random() * 2);
            parasha = parasha.split("-")[rand];
            parashaNum = RegularHebrewDate.getNumFromParasha(parasha);
            System.out.println("Chosen today parasha pninim: " + parasha);
            parashaLine = FileUtils.getLongFromProperties(MailSenderCons.PARASHA_LINE + parashaNum);
        }
        parasha = RegularHebrewDate.getParashaAsString(parashaNum);
        BufferedReader in;

        in = new BufferedReader(new FileReader(inputFile));
        //goto the parasha start line
        while (!parasha.equals(line)) {
            line = in.readLine().trim();
            lineNum++;
        }

        long diff = parashaLine - lineNum;
        if (diff > 0 && diff < 3000) { // setting lineNum = oldLineNum
            // FileUtils.gotoLineInFile(in, diff);
            line = "";
            for (int i = 0; i < diff && !nextParasha(line); i++) {
                line = in.readLine().trim();
                lineNum++;
            }
            if (nextParasha(line)) {//if the parasha ended
                try {
                    in.close();
                } catch (IOException ignored) {
                }
                in = new BufferedReader(new FileReader(inputFile));
                line = "";
                lineNum = 0;
                //goto the parasha start line
                while (!parasha.equals(line)) {
                    line = in.readLine().trim();
                    lineNum++;
                }
            }
        }


        boolean inMiddle = false;
        boolean finishedBuild = false;
        while ((line = in.readLine()) != null && !finishedBuild) {
            if (nextParasha(line)) {
                //we continue to the next parasha
                lineNum = 1;//reset the file read for next time
                finishedBuild = true;
            } else {
                fullString.append('\n');
                lineNum++;
                if (line.trim().length() == 0) {
                    fullString.append("<br/>");
                    inMiddle = false;
                    if (counter >= numOfTopics && fullString.length() > 700) {
                        finishedBuild = true;
                    }
                } else {
                    if (!inMiddle) {
                        counter++;
                        fullString.append("<b>").append(line).append("</b>");
                        inMiddle = true;
                    } else {
                        fullString.append(line);
                    }
                    fullString.append("<br/>");
                }
            }
        }
        System.out.println("line: " + lineNum);
        MailSenderCons.dynamic_properties.setProperty(MailSenderCons.PARASHA_LINE + parashaNum, String.valueOf(lineNum));

        try {
            in.close();
        } catch (IOException ignored) {
        }
        mailGeneratorProperties.setParashaTextToSend(fullString.toString());
    }
}
