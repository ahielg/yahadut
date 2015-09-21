package ayeleyHashahar.parameters;

import java.util.Calendar;

/**
 * User: ahiel
 * Date: 06/11/12
 * Time: 22:56
 */
@SuppressWarnings("UnusedDeclaration")
public class MailGeneratorProperties {
    private final boolean musar;
    private final boolean halacha;
    private final Calendar date;
    private String koteretParasha = "טוב לי תורת פיך";
    private String koteretHalachaYomit = "כל השונה הלכות";
    private String koteretMusar = "שמע בני";
    private boolean parasha;
    private String vetenLink;
    private String parashaTextToSend = "";
    private String halachaTextToSend = "";
    private String musarTextToSend = "";
    private String holidayTextToSend = "";
    private String hodaot = "דבר תורה יומי לקירוב הגאולה";

    public MailGeneratorProperties(boolean musar, boolean halacha, boolean parasha, Calendar date) {
        this.musar = musar;
        this.halacha = halacha;
        this.parasha = parasha;
        this.date = date;
    }

    public String getHolidayTextToSend() {
        return holidayTextToSend;
    }

    public void setHolidayTextToSend(String holidayTextToSend) {
        this.holidayTextToSend = holidayTextToSend;
    }

    public String getKoteretParasha() {
        return koteretParasha;
    }

    public void setKoteretParasha(String koteretParasha) {
        this.koteretParasha = koteretParasha;
    }

    public String getKoteretHalachaYomit() {
        return koteretHalachaYomit;
    }

    public void setKoteretHalachaYomit(String koteretHalachaYomit) {
        this.koteretHalachaYomit = koteretHalachaYomit;
    }

    public String getKoteretMusar() {
        return koteretMusar;
    }

    public void setKoteretMusar(String koteretMusar) {
        this.koteretMusar = koteretMusar;
    }

    public boolean isMusar() {
        return musar;
    }

    public boolean isHalacha() {
        return halacha;
    }

    public boolean isParasha() {
        return parasha;
    }

    public void setParasha(boolean parasha) {
        this.parasha = parasha;
    }

    public String getParashaTextToSend() {
        return parashaTextToSend;
    }

    public void setParashaTextToSend(String parashaTextToSend) {
        this.parashaTextToSend = parashaTextToSend;
    }

    public String getHalachaTextToSend() {
        return halachaTextToSend;
    }

    public void setHalachaTextToSend(String halachaTextToSend) {
        this.halachaTextToSend = halachaTextToSend;
    }

    public String getMusarTextToSend() {
        return musarTextToSend;
    }

    public void setMusarTextToSend(String musarTextToSend) {
        this.musarTextToSend = musarTextToSend;
    }

    public Calendar getDate() {
        return date;
    }

    public String getHodaot() {
        return hodaot;
    }

    public void setHodaot(String hodaot) {
        this.hodaot = hodaot;
    }

    public String getVetenLink() {
        return vetenLink;
    }

    public void setVetenLink(String vetenLink) {
        this.vetenLink = vetenLink;
    }

    public void updateKotarot() {
        if (parashaTextToSend.isEmpty()) {
            koteretParasha = "";
        }
        if (halachaTextToSend.isEmpty()) {
            koteretHalachaYomit = "";
        }
        if (musarTextToSend.isEmpty()) {
            koteretMusar = "";
        }
    }
}
