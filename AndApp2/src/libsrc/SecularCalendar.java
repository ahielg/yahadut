package libsrc;

public class SecularCalendar
{
  public static final int APRIL = 3;
  public static final int AUGUST = 7;
  static final int BASE_ABSOLUTE = 87573;
  static final int BASE_YEAR = 4000;
  static final int DAYS_IN_CENTURY_CYCLE = 36524;
  static final int DAYS_IN_HUGE_CYCLE = 146097;
  static final int DAYS_IN_LEAP_CYCLE = 1461;
  static final int DAYS_IN_LEAP_YEAR = 366;
  static final int DAYS_IN_YEAR = 365;
  public static final int DECEMBER = 11;
  public static int[] DayInMonths;
  public static final int FEBRUARY = 1;
  static final int GREGORIAN_ADJUSTMENT = 32;
  public static final int INDEX_SATURDAY = 6;
  public static final int INDEX_SUNDAY = 0;
  public static final int JANUARY = 0;
  public static final int JULY = 6;
  public static final int JUNE = 5;
  public static final int MARCH = 2;
  private static final int MAX_DATE_ABSOLUTE = 1826234106;
  private static final int MAX_YEAR_SECULAR = 4999999;
  public static final int MAY = 4;
  public static final int MIN_YEAR_GREGORIAN = 400;
  public static final int MIN_YEAR_SECULAR = -3762;
  public static final int MONTHS_IN_YEAR = 12;
  public static final int NOVEMBER = 10;
  public static final int OCTOBER = 9;
  public static final int SEPTEMBER = 8;
  static final int YEARS_IN_CENTURY_CYCLE = 100;
  static final int YEARS_IN_HUGE_CYCLE = 400;
  static final int YEARS_IN_LEAP_CYCLE = 4;

  static
  {
    int[] arrayOfInt = new int[12];
    arrayOfInt[0] = 31;
    arrayOfInt[1] = 28;
    arrayOfInt[2] = 31;
    arrayOfInt[3] = 30;
    arrayOfInt[4] = 31;
    arrayOfInt[5] = 30;
    arrayOfInt[6] = 31;
    arrayOfInt[7] = 31;
    arrayOfInt[8] = 30;
    arrayOfInt[9] = 31;
    arrayOfInt[10] = 30;
    arrayOfInt[11] = 31;
    DayInMonths = arrayOfInt;
  }

  public static String dateToTextShort(DateData paramDateData)
  {
    return String.valueOf(paramDateData.day) + getMonthNameShort(paramDateData.month) + String.valueOf(paramDateData.year);
  }

  public static String dateToTextTruncated(DateData paramDateData)
  {
    String str = String.valueOf(Math.abs(paramDateData.year) % 100);
    if (str.length() < 2)
      str = "0" + str;
    return String.valueOf(paramDateData.day) + getMonthNameShort(paramDateData.month) + str;
  }

  private static int evaluateYear(int paramInt, boolean paramBoolean)
  {
    int i = 365;
    if (isLeapYear(paramInt, paramBoolean))
    {
      DayInMonths[1] = 29;
      i++;
    }
    while (true)
    {
      return i;
      DayInMonths[1] = 28;
    }
  }

  static boolean fromAbsolute(DateHolder paramDateHolder, int paramInt)
  {
    boolean bool1 = true;
    boolean bool2;
    int j;
    int m;
    int n;
    if ((!paramDateHolder.alwaysJulian) && (paramInt > EventData.gregorianStart.dateAbsolute))
    {
      bool2 = bool1;
      int i = 0;
      j = paramInt + 87573;
      int k = j;
      if (bool2)
      {
        int i6 = k - 32;
        int i7 = 0 + 400 * (i6 / 146097);
        int i8 = i6 % 146097;
        i = i7 + 100 * (i8 / 36524);
        k = i8 % 36524;
      }
      m = i + 4 * (k / 1461) + 365 * (k % 1461 / 365);
      n = getNewYearAbsolute(m, bool2);
      label121: if (n > j)
        break label182;
    }
    int i2;
    int i3;
    while (true)
    {
      int i1 = evaluateYear(m, bool2);
      i2 = j - n;
      if (i2 < i1)
      {
        i3 = m - 4000;
        if ((i3 <= 4999999) && (i3 >= -3762))
          break label212;
        bool1 = false;
        return bool1;
        bool2 = false;
        break;
        label182: m--;
        n -= evaluateYear(m, bool2);
        break label121;
      }
      m++;
      n += i1;
    }
    label212: for (int i4 = 0; ; i4++)
    {
      int i5 = DayInMonths[i4];
      if (i2 < i5)
      {
        paramDateHolder.dateAbsolute = (j - 87573);
        paramDateHolder.secNewYearAbsolute = (n - 87573);
        paramDateHolder.dateSecular = new DateData(i2 + 1, i4, i3);
        paramDateHolder.isGregorian = bool2;
        break;
      }
      i2 -= i5;
    }
  }

  public static String getDayOfWeekName(int paramInt)
  {
    return MText.DowNamesCommon[paramInt];
  }

  public static String getMonthName(int paramInt)
  {
    return MText.MonthNamesSecular[paramInt];
  }

  public static String getMonthNameShort(int paramInt)
  {
    int i = MText.MonthNamesSecularShortLength[paramInt];
    String str = MText.MonthNamesSecular[paramInt];
    if (i > 0)
      str = str.substring(0, i);
    return str;
  }

  private static int getNewYearAbsolute(int paramInt, boolean paramBoolean)
  {
    int i = paramInt - 1;
    int j = i * 365 + i / 4;
    if (paramBoolean)
      j += 32 + (i / 400 - i / 100);
    return j;
  }

  public static boolean isLeapYear(int paramInt, boolean paramBoolean)
  {
    if (paramInt % 4 == 0);
    for (int i = 1; ; i = 0)
    {
      if ((paramBoolean) && (paramInt % 100 == 0) && (paramInt % 400 != 0))
        i = 0;
      return i;
    }
  }

  public static int monthMaxDuration(int paramInt)
  {
    if (paramInt == 1);
    for (int i = 29; ; i = DayInMonths[paramInt])
      return i;
  }

  static boolean toAbsolute(DateHolder paramDateHolder, DateData paramDateData)
  {
    if ((!paramDateHolder.alwaysJulian) && (paramDateData.compare(EventData.gregorianStart.dateSecular) > 0));
    for (boolean bool = true; ; bool = false)
      return toAbsolute(paramDateHolder, paramDateData, bool);
  }

  static boolean toAbsolute(DateHolder paramDateHolder, DateData paramDateData, boolean paramBoolean)
  {
    int i = 0;
    int j = paramDateData.year;
    if ((j > 4999999) || (j < -3762));
    int k;
    int m;
    int n;
    do
    {
      return i;
      k = j + 4000;
      m = paramDateData.day;
      n = paramDateData.month;
    }
    while ((n < 0) || (m <= 0));
    evaluateYear(k, paramBoolean);
    int i1 = Math.min(m, DayInMonths[n]);
    paramDateData.day = i1;
    int i2 = getNewYearAbsolute(k, paramBoolean);
    int i3 = i2 + (i1 - 1);
    while (true)
    {
      n--;
      if (n < 0)
      {
        int i4 = i3 - 87573;
        if ((i4 < 0) || (i4 >= 1826234106))
          break;
        paramDateHolder.dateSecular = paramDateData;
        paramDateHolder.secNewYearAbsolute = (i2 - 87573);
        paramDateHolder.dateAbsolute = i4;
        paramDateHolder.isGregorian = paramBoolean;
        i = 1;
        break;
      }
      i3 += DayInMonths[n];
    }
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     libsrc.SecularCalendar
 * JD-Core Version:    0.6.0
 */