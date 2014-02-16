package ayeleyHashahar.parameters;

/**
 * User: ahiel
 * Date: 06/11/12
 * Time: 23:35
 */
public class GeneratorParameters {
    private String halachaFilename;
    private String musarFilename;
    private String parashaFilename;
    private String holidaysFilename;

    private int parashaNum;
    private int holidayNum;
    private long musarLineNum;
    private long parashaLineNum;

    private long holidayLineNum;
    private boolean isThursdayLastDay;

    public GeneratorParameters(String halachaFilename, String musarFilename, String parashaFilename, String holidaysFilename,
                               int parashaNum, int holidayNum, long musarLineNum, long parashaLineNum, long holidayLineNum,
                               boolean isThursdayLastDay) {
        this.halachaFilename = halachaFilename;
        this.musarFilename = musarFilename;
        this.parashaFilename = parashaFilename;
        this.holidaysFilename = holidaysFilename;
        this.parashaNum = parashaNum;
        this.holidayNum = holidayNum;
        this.musarLineNum = musarLineNum;
        this.parashaLineNum = parashaLineNum;
        this.holidayLineNum = holidayLineNum;
        this.isThursdayLastDay = isThursdayLastDay;
    }

    public String getHalachaFilename() {
        return halachaFilename;
    }

    public String getMusarFilename() {
        return musarFilename;
    }

    public String getParashaFilename() {
        return parashaFilename;
    }

    public String getHolidaysFilename() {
        return holidaysFilename;
    }

    public int getParashaNum() {
        return parashaNum;
    }
    public long getMusarLineNum() {
        return musarLineNum;
    }

    public long getParashaLineNum() {
        return parashaLineNum;
    }

    public boolean isThursdayLastDay() {
        return isThursdayLastDay;
    }

    public long getHolidayLineNum() {
        return holidayLineNum;
    }

    public int getHolidayNum() {
        return holidayNum;
    }
}
