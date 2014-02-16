package net.sourceforge.zmanim.util;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import net.sourceforge.zmanim.AstronomicalCalendar;

public class ZmanimFormatter
{
  public static final int DECIMAL_FORMAT = 1;
  public static final long HOUR_MILLIS = 3600000L;
  static final long MINUTE_MILLIS = 60000L;
  public static final int SEXAGESIMAL_FORMAT = 2;
  public static final int SEXAGESIMAL_MILLIS_FORMAT = 4;
  public static final int SEXAGESIMAL_SECONDS_FORMAT = 3;
  public static final int SEXAGESIMAL_XSD_FORMAT = 0;
  public static final int XSD_DURATION_FORMAT = 5;
  private static DecimalFormat milliNF;
  private static DecimalFormat minuteSecondNF = new DecimalFormat("00");
  private SimpleDateFormat dateFormat;
  private DecimalFormat hourNF;
  private boolean prependZeroHours;
  private int timeFormat = 0;
  boolean useDecimal;
  private boolean useMillis;
  private boolean useSeconds;

  static
  {
    milliNF = new DecimalFormat("000");
  }

  public ZmanimFormatter()
  {
    this(0, new SimpleDateFormat("h:mm:ss"));
  }

  public ZmanimFormatter(int paramInt, SimpleDateFormat paramSimpleDateFormat)
  {
    String str = "0";
    if (this.prependZeroHours)
      str = "00";
    this.hourNF = new DecimalFormat(str);
    setTimeFormat(paramInt);
    setDateFormat(paramSimpleDateFormat);
  }

  private static String formatDigits(int paramInt)
  {
    String str = String.valueOf(Math.abs(paramInt));
    if (str.length() == 1)
      str = '0' + str;
    return str;
  }

  private static boolean includeMethod(Method paramMethod)
  {
    int i = 1;
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    if (localArrayList1.contains(paramMethod.getName()));
    while (true)
    {
      return i;
      if (localArrayList2.contains(paramMethod.getName()))
      {
        i = 0;
        continue;
      }
      if (paramMethod.getParameterTypes().length > 0)
      {
        i = 0;
        continue;
      }
      if (!paramMethod.getName().startsWith("get"))
      {
        i = 0;
        continue;
      }
      if ((paramMethod.getReturnType().getName().endsWith("Date")) || (paramMethod.getReturnType().getName().endsWith("long")))
        continue;
      i = 0;
    }
  }

  private void setSettings(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    this.prependZeroHours = paramBoolean1;
    this.useSeconds = paramBoolean2;
    this.useMillis = paramBoolean3;
  }

  public static String toXML(AstronomicalCalendar paramAstronomicalCalendar)
  {
    ZmanimFormatter localZmanimFormatter = new ZmanimFormatter(5, new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String str1 = "<";
    String str2;
    Method[] arrayOfMethod;
    ArrayList localArrayList1;
    ArrayList localArrayList2;
    ArrayList localArrayList3;
    if (paramAstronomicalCalendar.getClass().getName().endsWith("AstronomicalCalendar"))
    {
      str1 = str1 + "AstronomicalTimes";
      str2 = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(str1)).append(" date=\"").append(localSimpleDateFormat.format(paramAstronomicalCalendar.getCalendar().getTime())).append("\"").toString())).append(" type=\"").append(paramAstronomicalCalendar.getClass().getName()).append("\"").toString())).append(" algorithm=\"").append(paramAstronomicalCalendar.getAstronomicalCalculator().getCalculatorName()).append("\"").toString())).append(" location=\"").append(paramAstronomicalCalendar.getGeoLocation().getLocationName()).append("\"").toString())).append(" latitude=\"").append(paramAstronomicalCalendar.getGeoLocation().getLatitude()).append("\"").toString())).append(" longitude=\"").append(paramAstronomicalCalendar.getGeoLocation().getLongitude()).append("\"").toString())).append(" elevation=\"").append(paramAstronomicalCalendar.getGeoLocation().getElevation()).append("\"").toString())).append(" timeZoneName=\"").append(paramAstronomicalCalendar.getGeoLocation().getTimeZone().getDisplayName()).append("\"").toString())).append(" timeZoneID=\"").append(paramAstronomicalCalendar.getGeoLocation().getTimeZone().getID()).append("\"").toString())).append(" timeZoneOffset=\"").append(paramAstronomicalCalendar.getGeoLocation().getTimeZone().getOffset(paramAstronomicalCalendar.getCalendar().getTimeInMillis()) / 3600000.0D).append("\"").toString() + ">\n";
      arrayOfMethod = paramAstronomicalCalendar.getClass().getMethods();
      localArrayList1 = new ArrayList();
      localArrayList2 = new ArrayList();
      localArrayList3 = new ArrayList();
    }
    label525: label540: label1181: for (int i = 0; ; i++)
    {
      int j;
      label502: int k;
      int m;
      if (i >= arrayOfMethod.length)
      {
        Collections.sort(localArrayList1, Zman.DATE_ORDER);
        j = 0;
        if (j < localArrayList1.size())
          break label857;
        Collections.sort(localArrayList2, Zman.DURATION_ORDER);
        k = 0;
        if (k < localArrayList2.size())
          break label976;
        m = 0;
        if (m < localArrayList3.size())
          break label1092;
        if (!paramAstronomicalCalendar.getClass().getName().endsWith("AstronomicalCalendar"))
          break label1139;
        str2 = str2 + "</AstronomicalTimes>";
      }
      while (true)
      {
        return str2;
        if (!paramAstronomicalCalendar.getClass().getName().endsWith("ZmanimCalendar"))
          break;
        str1 = str1 + "Zmanim";
        break;
        if (!includeMethod(arrayOfMethod[i]))
          break label1181;
        String str3 = arrayOfMethod[i].getName().substring(3);
        Object localObject;
        try
        {
          localObject = arrayOfMethod[i].invoke(paramAstronomicalCalendar, null);
          if (localObject == null)
          {
            localArrayList3.add("<" + str3 + ">N/A</" + str3 + ">");
            break label1181;
          }
          if ((localObject instanceof Date))
            localArrayList1.add(new Zman((Date)localObject, str3));
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
        if ((localObject instanceof Long))
        {
          localArrayList2.add(new Zman((int)((Long)localObject).longValue(), str3));
          break label1181;
        }
        localArrayList3.add("<" + str3 + ">" + localObject + "</" + str3 + ">");
        break label1181;
        label857: Zman localZman1 = (Zman)localArrayList1.get(j);
        str2 = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(str2)).append("\t<").append(localZman1.getZmanLabel()).toString())).append(">").toString() + localZmanimFormatter.formatDateTime(localZman1.getZman(), paramAstronomicalCalendar.getCalendar()) + "</" + localZman1.getZmanLabel() + ">\n";
        j++;
        break label502;
        label976: Zman localZman2 = (Zman)localArrayList2.get(k);
        str2 = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(str2)).append("\t<").append(localZman2.getZmanLabel()).toString())).append(">").toString() + localZmanimFormatter.format((int)localZman2.getDuration()) + "</" + localZman2.getZmanLabel() + ">\n";
        k++;
        break label525;
        label1092: str2 = str2 + "\t" + localArrayList3.get(m) + "\n";
        m++;
        break label540;
        label1139: if (!paramAstronomicalCalendar.getClass().getName().endsWith("ZmanimCalendar"))
          continue;
        str2 = str2 + "</Zmanim>";
      }
    }
  }

  public String format(double paramDouble)
  {
    return format((int)paramDouble);
  }

  public String format(int paramInt)
  {
    return format(new Time(paramInt));
  }

  public String format(Time paramTime)
  {
    if (this.timeFormat == 5);
    StringBuffer localStringBuffer;
    for (String str = formatXSDDurationTime(paramTime); ; str = localStringBuffer.toString())
    {
      return str;
      localStringBuffer = new StringBuffer();
      localStringBuffer.append(this.hourNF.format(paramTime.getHours()));
      localStringBuffer.append(":");
      localStringBuffer.append(minuteSecondNF.format(paramTime.getMinutes()));
      if (this.useSeconds)
      {
        localStringBuffer.append(":");
        localStringBuffer.append(minuteSecondNF.format(paramTime.getSeconds()));
      }
      if (!this.useMillis)
        continue;
      localStringBuffer.append(".");
      localStringBuffer.append(milliNF.format(paramTime.getMilliseconds()));
    }
  }

  public String formatDateTime(Date paramDate, Calendar paramCalendar)
  {
    this.dateFormat.setCalendar(paramCalendar);
    if (this.dateFormat.toPattern().equals("yyyy-MM-dd'T'HH:mm:ss"));
    for (String str = getXSDateTime(paramDate, paramCalendar); ; str = this.dateFormat.format(paramDate))
      return str;
  }

  public String formatXSDDurationTime(long paramLong)
  {
    return formatXSDDurationTime(new Time(paramLong));
  }

  public String formatXSDDurationTime(Time paramTime)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("P");
    if ((paramTime.getHours() != 0) || (paramTime.getMinutes() != 0) || (paramTime.getSeconds() != 0) || (paramTime.getMilliseconds() != 0))
    {
      localStringBuffer.append("T");
      if (paramTime.getHours() != 0)
        localStringBuffer.append(paramTime.getHours() + "H");
      if (paramTime.getMinutes() != 0)
        localStringBuffer.append(paramTime.getMinutes() + "M");
      if ((paramTime.getSeconds() != 0) || (paramTime.getMilliseconds() != 0))
      {
        localStringBuffer.append(paramTime.getSeconds() + "." + milliNF.format(paramTime.getMilliseconds()));
        localStringBuffer.append("S");
      }
      if (localStringBuffer.length() == 1)
        localStringBuffer.append("T0S");
      if (paramTime.isNegative())
        localStringBuffer.insert(0, "-");
    }
    return localStringBuffer.toString();
  }

  public SimpleDateFormat getDateFormat()
  {
    return this.dateFormat;
  }

  public String getXSDateTime(Date paramDate, Calendar paramCalendar)
  {
    StringBuffer localStringBuffer = new StringBuffer(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(paramDate));
    int i = paramCalendar.get(15) + paramCalendar.get(16);
    if (i == 0)
    {
      localStringBuffer.append("Z");
      return localStringBuffer.toString();
    }
    int j = i / 3600000;
    int k = i % 3600000;
    if (j < 0);
    for (char c = '-'; ; c = '+')
    {
      localStringBuffer.append(c + formatDigits(j) + ':' + formatDigits(k));
      break;
    }
  }

  public void setDateFormat(SimpleDateFormat paramSimpleDateFormat)
  {
    this.dateFormat = paramSimpleDateFormat;
  }

  public void setTimeFormat(int paramInt)
  {
    this.timeFormat = paramInt;
    switch (paramInt)
    {
    case 1:
    default:
      this.useDecimal = true;
    case 0:
    case 2:
    case 3:
    case 4:
    }
    while (true)
    {
      return;
      setSettings(true, true, true);
      continue;
      setSettings(false, false, false);
      continue;
      setSettings(false, true, false);
      continue;
      setSettings(false, true, true);
    }
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     net.sourceforge.zmanim.util.ZmanimFormatter
 * JD-Core Version:    0.6.0
 */