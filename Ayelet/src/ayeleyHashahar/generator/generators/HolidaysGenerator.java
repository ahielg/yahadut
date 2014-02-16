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
 * @author Ahielg
 * Date: 12/4/12
 */
public class HolidaysGenerator implements TextGenerator {
    @Override
    public boolean isToCheck(MailGeneratorProperties mailGeneratorProperties) {
        int nextHolidayNum = RegularHebrewDate.getNextHolidayNum(mailGeneratorProperties.getDate(), 7);
        return mailGeneratorProperties.isParasha() && (nextHolidayNum != -1);
    }


    public void generateText(GeneratorParameters params, MailGeneratorProperties mailGeneratorProperties) throws IOException {

        File inputFile = new File(params.getHolidaysFilename());
        BufferedReader in = new BufferedReader(new FileReader(inputFile));
        int numOfTopics = Integer.parseInt(MailSenderCons.properties.getProperty(MailSenderCons.PARASHA_TOPIC_NUMBERS));
        String line = "";
        int counter = 0;
        long lineNum = 0;
        StringBuilder fullString = new StringBuilder("<span style=\"color:#006699;font-family:Arial,serif;font-size:24px;text-decoration:none\">פנינים ל");

        int nextHolidayNum = params.getHolidayNum();

        String holiday = RegularHebrewDate.getImportantHolidayByNum(nextHolidayNum);
        fullString.append(holiday).append("</SPAN><BR/>");
        System.out.println(holiday);
        while (!holiday.equals(line)) {
            line = in.readLine().trim();
            lineNum++;
        }

        long diff = params.getHolidayLineNum() - lineNum;
        if (diff > 0 && diff < 1000) { // setting lineNum = oldLineNum
            FileUtils.gotoLineInFile(in, diff);
            lineNum = params.getHolidayLineNum();
        }

        boolean inMiddle = false;
        boolean finishedBuild = false;
        while ((line = in.readLine()) != null && !finishedBuild) {
            if (line.trim().startsWith("-----") && line.length() < 100) {
                //we continue to the next holiday
                lineNum = 1;//reset the file read for next time
                finishedBuild = true;
            } else {
                fullString.append('\n');
                lineNum++;
                if (line.trim().length() == 0) {
                    fullString.append("<br/>");
                    inMiddle = false;
                    if (counter >= numOfTopics && fullString.length() > 100) {
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

        System.out.println("holiday line: " + lineNum);
        MailSenderCons.dynamic_properties.setProperty(MailSenderCons.HOLIDAY_LINE + nextHolidayNum, String.valueOf(lineNum));


        if (ParashaGenerator.generalIsToCheck(mailGeneratorProperties)) {
            fullString.insert(0,"<BR/><BR/>");
        }

        mailGeneratorProperties.setHolidayTextToSend(fullString.toString());
    }

}
