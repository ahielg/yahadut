package com.eladrich.dakatora;

import java.lang.reflect.Array;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.Vector;
import libsrc.DateData;
import libsrc.DateHolder;
import libsrc.EventData;
import libsrc.HebCalendar;
import libsrc.LocationHolder;
import libsrc.Sedrot;
import net.sourceforge.zmanim.ComplexZmanimCalendar;
import net.sourceforge.zmanim.util.GeoLocation;

public class ZmanimClass
{
  static final double[] elevations;
  private static final String[] hebDay;
  static final double[] locLatitudes;
  static final double[] locLongitudes;
  static final String[] placesArray;
  private Calendar c;
  private ComplexZmanimCalendar czc;
  private DateHolder dh;
  private LocationHolder lh;
  private int loc;
  private int shabatLoc;
  private boolean wasUpdated;

  static
  {
    double[] arrayOfDouble1 = new double[30];
    arrayOfDouble1[0] = 29.557500000000001D;
    arrayOfDouble1[1] = 31.794443999999999D;
    arrayOfDouble1[2] = 31.2575D;
    arrayOfDouble1[3] = 31.941943999999999D;
    arrayOfDouble1[4] = 32.456389000000001D;
    arrayOfDouble1[5] = 33.099722D;
    arrayOfDouble1[6] = 31.516667000000002D;
    arrayOfDouble1[7] = 32.816667000000002D;
    arrayOfDouble1[8] = 33.399999999999999D;
    arrayOfDouble1[9] = 30.988889D;
    arrayOfDouble1[10] = 31.783332999999999D;
    arrayOfDouble1[11] = 31.855556D;
    arrayOfDouble1[12] = 31.373611D;
    arrayOfDouble1[13] = 31.648889D;
    arrayOfDouble1[14] = 31.905000000000001D;
    arrayOfDouble1[15] = 32.365833000000002D;
    arrayOfDouble1[16] = 33.278888999999999D;
    arrayOfDouble1[17] = 30.612777999999999D;
    arrayOfDouble1[18] = 33.003610999999999D;
    arrayOfDouble1[19] = 31.260278D;
    arrayOfDouble1[20] = 32.688333D;
    arrayOfDouble1[21] = 31.177499999999998D;
    arrayOfDouble1[22] = 32.965555999999999D;
    arrayOfDouble1[23] = 32.992221999999998D;
    arrayOfDouble1[24] = 32.523888999999997D;
    arrayOfDouble1[25] = 32.846111000000001D;
    arrayOfDouble1[26] = 31.523610999999999D;
    arrayOfDouble1[27] = 30.105556D;
    arrayOfDouble1[28] = 32.220278D;
    arrayOfDouble1[29] = 32.066667000000002D;
    locLatitudes = arrayOfDouble1;
    double[] arrayOfDouble2 = new double[30];
    arrayOfDouble2[0] = 34.950000000000003D;
    arrayOfDouble2[1] = 34.647221999999999D;
    arrayOfDouble2[2] = 34.795278000000003D;
    arrayOfDouble2[3] = 35.221944000000001D;
    arrayOfDouble2[4] = 35.295833000000002D;
    arrayOfDouble2[5] = 35.288611000000003D;
    arrayOfDouble2[6] = 35.094999999999999D;
    arrayOfDouble2[7] = 32.999721999999998D;
    arrayOfDouble2[8] = 35.850000000000001D;
    arrayOfDouble2[9] = 34.933889000000001D;
    arrayOfDouble2[10] = 35.216667000000001D;
    arrayOfDouble2[11] = 35.462221999999997D;
    arrayOfDouble2[12] = 34.397778000000002D;
    arrayOfDouble2[13] = 35.115278000000004D;
    arrayOfDouble2[14] = 35.010832999999998D;
    arrayOfDouble2[15] = 35.515833000000001D;
    arrayOfDouble2[16] = 35.576667D;
    arrayOfDouble2[17] = 34.801667000000002D;
    arrayOfDouble2[18] = 35.092500000000001D;
    arrayOfDouble2[19] = 35.213611D;
    arrayOfDouble2[20] = 34.939722000000003D;
    arrayOfDouble2[21] = 34.564166999999998D;
    arrayOfDouble2[22] = 35.499721999999998D;
    arrayOfDouble2[23] = 35.691667000000002D;
    arrayOfDouble2[24] = 35.034444000000001D;
    arrayOfDouble2[25] = 35.808610999999999D;
    arrayOfDouble2[26] = 34.595278D;
    arrayOfDouble2[27] = 34.000833D;
    arrayOfDouble2[28] = 35.278888999999999D;
    arrayOfDouble2[29] = 34.783332999999999D;
    locLongitudes = arrayOfDouble2;
    double[] arrayOfDouble3 = new double[30];
    arrayOfDouble3[0] = 10.0D;
    arrayOfDouble3[1] = 6.0D;
    arrayOfDouble3[2] = 271.0D;
    arrayOfDouble3[3] = 862.0D;
    arrayOfDouble3[4] = 133.0D;
    arrayOfDouble3[5] = 643.0D;
    arrayOfDouble3[6] = 922.0D;
    arrayOfDouble3[7] = 289.0D;
    arrayOfDouble3[8] = 2099.0D;
    arrayOfDouble3[9] = 494.0D;
    arrayOfDouble3[10] = 744.0D;
    arrayOfDouble3[11] = -256.0D;
    arrayOfDouble3[12] = 82.0D;
    arrayOfDouble3[13] = 947.0D;
    arrayOfDouble3[14] = 0.0D;
    arrayOfDouble3[15] = -254.0D;
    arrayOfDouble3[16] = 511.0D;
    arrayOfDouble3[17] = 763.0D;
    arrayOfDouble3[18] = 8.0D;
    arrayOfDouble3[19] = 521.0D;
    arrayOfDouble3[20] = 0.0D;
    arrayOfDouble3[21] = 155.0D;
    arrayOfDouble3[22] = 733.0D;
    arrayOfDouble3[23] = 309.0D;
    arrayOfDouble3[24] = 106.0D;
    arrayOfDouble3[25] = 432.0D;
    arrayOfDouble3[26] = 92.0D;
    arrayOfDouble3[27] = 402.0D;
    arrayOfDouble3[28] = 510.0D;
    arrayOfDouble3[29] = 15.0D;
    elevations = arrayOfDouble3;
    String[] arrayOfString1 = new String[30];
    arrayOfString1[0] = "אילת";
    arrayOfString1[1] = "אשדוד";
    arrayOfString1[2] = "באר שבע";
    arrayOfString1[3] = "בית אל";
    arrayOfString1[4] = "ג'נין";
    arrayOfString1[5] = "זרעית";
    arrayOfString1[6] = "חברון";
    arrayOfString1[7] = "חיפה";
    arrayOfString1[8] = "חרמון";
    arrayOfString1[9] = "ירוחם";
    arrayOfString1[10] = "ירושלים";
    arrayOfString1[11] = "יריחו";
    arrayOfString1[12] = "כיסופים";
    arrayOfString1[13] = "כפר עציון";
    arrayOfString1[14] = "מודיעין";
    arrayOfString1[15] = "מחולה";
    arrayOfString1[16] = "מטולה";
    arrayOfString1[17] = "מצפה רמון";
    arrayOfString1[18] = "נהריה";
    arrayOfString1[19] = "ערד";
    arrayOfString1[20] = "עתלית";
    arrayOfString1[21] = "צאלים";
    arrayOfString1[22] = "צפת";
    arrayOfString1[23] = "קצרין";
    arrayOfString1[24] = "רגבים";
    arrayOfString1[25] = "רמת מגשימים";
    arrayOfString1[26] = "שדרות";
    arrayOfString1[27] = "שיזפון";
    arrayOfString1[28] = "שכם";
    arrayOfString1[29] = "תל אביב";
    placesArray = arrayOfString1;
    String[] arrayOfString2 = new String[30];
    arrayOfString2[0] = "א";
    arrayOfString2[1] = "ב";
    arrayOfString2[2] = "ג";
    arrayOfString2[3] = "ד";
    arrayOfString2[4] = "ה";
    arrayOfString2[5] = "ו";
    arrayOfString2[6] = "ז";
    arrayOfString2[7] = "ח";
    arrayOfString2[8] = "ט";
    arrayOfString2[9] = "י";
    arrayOfString2[10] = "יא";
    arrayOfString2[11] = "יב";
    arrayOfString2[12] = "יג";
    arrayOfString2[13] = "יד";
    arrayOfString2[14] = "טו";
    arrayOfString2[15] = "טז";
    arrayOfString2[16] = "יז";
    arrayOfString2[17] = "יח";
    arrayOfString2[18] = "יט";
    arrayOfString2[19] = "כ";
    arrayOfString2[20] = "כא";
    arrayOfString2[21] = "כב";
    arrayOfString2[22] = "כג";
    arrayOfString2[23] = "כד";
    arrayOfString2[24] = "כה";
    arrayOfString2[25] = "כו";
    arrayOfString2[26] = "כז";
    arrayOfString2[27] = "כח";
    arrayOfString2[28] = "כט";
    arrayOfString2[29] = "ל";
    hebDay = arrayOfString2;
  }

  public ZmanimClass(double paramDouble1, double paramDouble2, String paramString, int paramInt)
  {
    this.loc = 0;
    this.shabatLoc = paramInt;
    this.c = Calendar.getInstance();
    EventData.resetDisplaySettings();
    this.dh = new DateHolder();
    this.dh.acceptCurrentDate();
    this.lh = new LocationHolder();
    int[] arrayOfInt = new int[3];
    if ((this.lh.acceptLatitude(convertLoc(paramDouble1))) && (this.lh.acceptLongitude(convertLoc(paramDouble2))))
    {
      LocationHolder.getIntrinsicTimeZone(convertLoc(paramDouble2), arrayOfInt);
      this.lh.acceptTimeZone(arrayOfInt);
    }
    this.czc = new ComplexZmanimCalendar(new GeoLocation(paramString, paramDouble1, paramDouble2, 0.0D, TimeZone.getDefault()));
  }

  public ZmanimClass(int paramInt1, int paramInt2)
  {
    this.loc = paramInt1;
    this.shabatLoc = paramInt2;
    this.c = Calendar.getInstance();
    EventData.resetDisplaySettings();
    this.dh = new DateHolder();
    this.dh.acceptCurrentDate();
    this.lh = new LocationHolder();
    int[] arrayOfInt = new int[3];
    if ((this.lh.acceptLatitude(convertLoc(locLatitudes[paramInt1]))) && (this.lh.acceptLongitude(convertLoc(locLongitudes[paramInt1]))))
    {
      LocationHolder.getIntrinsicTimeZone(convertLoc(locLongitudes[paramInt1]), arrayOfInt);
      this.lh.acceptTimeZone(arrayOfInt);
    }
    this.czc = new ComplexZmanimCalendar(new GeoLocation(placesArray[paramInt1], locLatitudes[paramInt1], locLongitudes[paramInt1], 0.0D, TimeZone.getDefault()));
  }

  private String dateToTextHebrew(DateHolder paramDateHolder)
  {
    DateData localDateData = paramDateHolder.dateHebrew;
    return hebDay[(-1 + localDateData.day)] + " ב" + HebCalendar.getMonthName(localDateData.month);
  }

  private static String pad(int paramInt)
  {
    if (paramInt >= 10);
    for (String str = String.valueOf(paramInt); ; str = "0" + String.valueOf(paramInt))
      return str;
  }

  private static String padReversed(int paramInt)
  {
    String str2;
    if (paramInt >= 10)
      str2 = String.valueOf(paramInt);
    for (String str1 = str2.charAt(1) + str2.charAt(0); ; str1 = String.valueOf(paramInt) + "0")
      return str1;
  }

  public int[] convertLoc(double paramDouble)
  {
    int[] arrayOfInt = new int[4];
    arrayOfInt[0] = (int)paramDouble;
    for (int i = 0; ; i++)
    {
      if (i >= 2)
      {
        arrayOfInt[2] = (int)paramDouble;
        arrayOfInt[3] = 0;
        return arrayOfInt;
      }
      arrayOfInt[i] = (int)paramDouble;
      paramDouble = 60.0D * (paramDouble - arrayOfInt[i]);
    }
  }

  public String getEvents()
  {
    String str1 = "";
    Vector localVector = EventData.getEventNamesForDate(this.dh, this.lh, false);
    int i = localVector.size();
    String str2;
    if (i <= 0)
    {
      str2 = "";
      return str2;
    }
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        str2 = str1;
        break;
      }
      str1 = str1 + ((String)localVector.elementAt(j)).substring(1) + " ";
    }
  }

  public String getHebrewDate()
  {
    return dateToTextHebrew(this.dh);
  }

  public String getParasha()
  {
    String[] arrayOfString = Sedrot.getSedrotNames(this.dh, this.lh);
    String str;
    if (arrayOfString != null)
      if (arrayOfString.length == 1)
        str = arrayOfString[0];
    while (true)
    {
      return str;
      if (arrayOfString.length == 2)
      {
        str = arrayOfString[0] + " " + arrayOfString[1];
        continue;
      }
      str = "";
    }
  }

  public String getPlace()
  {
    return placesArray[this.loc];
  }

  public String[][] getTimesAsArray(boolean paramBoolean)
  {
    Calendar localCalendar = Calendar.getInstance();
    String[][] arrayOfString;
    label80: Date localDate;
    if ((this.c.get(7) == 6) || (this.c.get(7) == 7))
    {
      int[] arrayOfInt1 = new int[2];
      arrayOfInt1[0] = 2;
      arrayOfInt1[1] = 16;
      arrayOfString = (String[][])Array.newInstance(String.class, arrayOfInt1);
      localCalendar.setTime(this.czc.getAlos90Zmanis());
      if (paramBoolean)
        break label1261;
      arrayOfString[0][0] = "עלות השחר 90";
      arrayOfString[1][0] = (pad(localCalendar.get(11)) + ":" + pad(localCalendar.get(12)));
      localCalendar.setTime(this.czc.getAlos72());
      if (paramBoolean)
        break label1273;
      arrayOfString[0][1] = "עלות השחר 72";
      label150: arrayOfString[1][1] = (pad(localCalendar.get(11)) + ":" + pad(localCalendar.get(12)));
      localCalendar.setTime(this.czc.getMisheyakir11Point5Degrees());
      arrayOfString[0][2] = "ציצית ותפילין";
      arrayOfString[1][2] = (pad(localCalendar.get(11)) + ":" + pad(localCalendar.get(12)));
      localCalendar.setTime(this.czc.getSeaLevelSunrise());
      arrayOfString[0][3] = "הנץ החמה";
      arrayOfString[1][3] = (pad(localCalendar.get(11)) + ":" + pad(localCalendar.get(12)));
      localCalendar.setTime(this.czc.getSofZmanShmaMGA());
      arrayOfString[0][4] = "סזק\"ש מג\"א";
      arrayOfString[1][4] = (pad(localCalendar.get(11)) + ":" + pad(localCalendar.get(12)));
      localCalendar.setTime(this.czc.getSofZmanShmaGRA());
      arrayOfString[0][5] = "סזק\"ש גר\"א";
      arrayOfString[1][5] = (pad(localCalendar.get(11)) + ":" + pad(localCalendar.get(12)));
      localCalendar.setTime(this.czc.getSofZmanTfilaMGA());
      arrayOfString[0][6] = "סזת\"פ מג\"א";
      arrayOfString[1][6] = (pad(localCalendar.get(11)) + ":" + pad(localCalendar.get(12)));
      localCalendar.setTime(this.czc.getSofZmanTfilaGRA());
      arrayOfString[0][7] = "סזת\"פ גר\"א";
      arrayOfString[1][7] = (pad(localCalendar.get(11)) + ":" + pad(localCalendar.get(12)));
      localCalendar.setTime(this.czc.getChatzos());
      arrayOfString[0][8] = "חצות היום";
      arrayOfString[1][8] = (pad(localCalendar.get(11)) + ":" + pad(localCalendar.get(12)));
      localCalendar.setTime(this.czc.getMinchaGedolaGreaterThan30());
      arrayOfString[0][9] = "מנחה גדולה";
      arrayOfString[1][9] = (pad(localCalendar.get(11)) + ":" + pad(localCalendar.get(12)));
      localCalendar.setTime(this.czc.getMinchaKetana());
      arrayOfString[0][10] = "מנחה קטנה";
      arrayOfString[1][10] = (pad(localCalendar.get(11)) + ":" + pad(localCalendar.get(12)));
      localCalendar.setTime(this.czc.getPlagHamincha());
      arrayOfString[0][11] = "פלג המנחה";
      arrayOfString[1][11] = (pad(localCalendar.get(11)) + ":" + pad(localCalendar.get(12)));
      localCalendar.setTime(this.czc.getSeaLevelSunset());
      arrayOfString[0][12] = "שקיעת החמה";
      arrayOfString[1][12] = (pad(localCalendar.get(11)) + ":" + pad(localCalendar.get(12)));
      double d = this.czc.getTemporalHour() / 3600000.0D;
      if (d <= 1.0D)
        break label1285;
      localDate = this.czc.getTimeOffset(this.czc.getSeaLevelSunset(), 60000.0D * (18.0D * d));
      label983: localCalendar.setTime(localDate);
      arrayOfString[0][13] = "צאת הכוכבים";
      arrayOfString[1][13] = (pad(localCalendar.get(11)) + ":" + pad(localCalendar.get(12)));
      if ((this.c.get(7) == 6) || (this.c.get(7) == 7))
      {
        if (this.shabatLoc != 0)
          break label1307;
        this.czc.setCandleLightingOffset(22.0D);
      }
    }
    while (true)
    {
      localCalendar.setTime(this.czc.getCandelLighting());
      arrayOfString[0][14] = "כניסת השבת";
      arrayOfString[1][14] = (pad(localCalendar.get(11)) + ":" + pad(localCalendar.get(12)));
      localCalendar.setTime(this.czc.getTzais());
      arrayOfString[0][15] = "מוצ\"ש";
      arrayOfString[1][15] = (pad(localCalendar.get(11)) + ":" + pad(localCalendar.get(12)));
      return arrayOfString;
      int[] arrayOfInt2 = new int[2];
      arrayOfInt2[0] = 2;
      arrayOfInt2[1] = 14;
      arrayOfString = (String[][])Array.newInstance(String.class, arrayOfInt2);
      break;
      label1261: arrayOfString[0][0] = "עלות השחר 09";
      break label80;
      label1273: arrayOfString[0][1] = "עלות השחר 27";
      break label150;
      label1285: localDate = this.czc.getTimeOffset(this.czc.getSeaLevelSunset(), 1080000L);
      break label983;
      label1307: if (this.shabatLoc == 1)
      {
        this.czc.setCandleLightingOffset(40.0D);
        continue;
      }
      if (this.shabatLoc == 2)
      {
        this.czc.setCandleLightingOffset(30.0D);
        continue;
      }
      if (this.shabatLoc == 3)
      {
        this.czc.setCandleLightingOffset(20.0D);
        continue;
      }
      if (this.shabatLoc == 4)
      {
        this.czc.setCandleLightingOffset(21.0D);
        continue;
      }
      this.czc.setCandleLightingOffset(30.0D);
    }
  }

  public boolean updateLoc(int paramInt)
  {
    this.loc = paramInt;
    EventData.resetDisplaySettings();
    this.dh = new DateHolder();
    this.dh.acceptCurrentDate();
    this.lh = new LocationHolder();
    int[] arrayOfInt = new int[3];
    if ((this.lh.acceptLatitude(convertLoc(locLatitudes[paramInt]))) && (this.lh.acceptLongitude(convertLoc(locLongitudes[paramInt]))))
    {
      LocationHolder.getIntrinsicTimeZone(convertLoc(locLongitudes[paramInt]), arrayOfInt);
      this.lh.acceptTimeZone(arrayOfInt);
    }
    this.czc = new ComplexZmanimCalendar(new GeoLocation(placesArray[paramInt], locLatitudes[paramInt], locLongitudes[paramInt], 0.0D, TimeZone.getDefault()));
    return true;
  }

  public boolean updated()
  {
    int i = 0;
    boolean bool;
    if (this.wasUpdated)
    {
      bool = false;
      this.wasUpdated = bool;
      if (!this.wasUpdated)
        break label30;
    }
    while (true)
    {
      return i;
      bool = true;
      break;
      label30: i = 1;
    }
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.eladrich.dakatora.ZmanimClass
 * JD-Core Version:    0.6.0
 */