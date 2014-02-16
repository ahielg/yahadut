package libsrc;

public class HebCalendar
{
  public static final int ADAR = 11;
  public static final int ADAR1 = 13;
  public static final int ADAR2 = 12;
  public static final int ANYMONTH = 14;
  public static final int AV = 4;
  private static final int BASE_DAY = 1;
  private static final int BASE_DOW = 0;
  private static final int BASE_MONTH_EXTRA = 1;
  private static final int BASE_PART = 5604;
  public static final int CHESHVAN = 7;
  private static final int DAYS_PER_CYCLE_CEIL = 6940;
  private static final int DAYS_PER_MONTH = 29;
  public static final int DAYS_PER_WEEK = 7;
  private static final int DAYS_PER_YEAR_LEAP = 384;
  private static final int DAYS_PER_YEAR_NONLEAP = 354;
  private static final int DAYS_PER_YEAR_NONLEAP_MAXIMUM = 356;
  public static final int DOW_SHABBAT = 6;
  public static final int DOW_WEEKEND = 5;
  public static final int ELUL = 5;
  private static final int HOURS_PER_DAY = 24;
  public static final int IYAR = 1;
  public static final int KISLEV = 8;
  public static final int MAX_YEAR_HEBREW = 5000000;
  public static final int MIN_YEAR_HEBREW = 1;
  private static final int MONTHS_PER_CYCLE = 235;
  public static final int MONTHS_PER_LEAP_YEAR = 13;
  private static final int MONTHS_PER_YEAR = 12;
  private static final int MONTHS_PER_YEAR_EXTRA = 7;
  private static final int NINE_32_AM = 16789;
  public static final int NISSAN = 0;
  private static final int NOON_TIME = 19440;
  private static final int PARTS_PER_DAY = 25920;
  private static final int PARTS_PER_HOUR = 1080;
  private static final int PARTS_PER_MONTH = 13753;
  public static final int SHEVAT = 10;
  public static final int SIVAN = 2;
  public static final int TAMMUZ = 3;
  public static final int TEVET = 9;
  private static final int THREE_12_AM = 9924;
  public static final int TISHRI = 6;
  private static final int YEARS_PER_CYCLE = 19;
  private static int yearStartLunarDays;
  private static int yearStartLunarParts;

  public static int calculateMoonPhase(DateHolder paramDateHolder)
  {
    int i = paramDateHolder.dateAbsolute;
    int j = (i - paramDateHolder.hebYearStartLunarDays) / 30;
    int k = paramDateHolder.hebYearStartLunarParts + j * 13753;
    int m = paramDateHolder.hebYearStartLunarDays + j * 29 + k / 25920;
    int n = k % 25920;
    while (true)
    {
      int i1 = m;
      int i2 = n + 13753;
      m += 29 + i2 / 25920;
      n = i2 % 25920;
      if (m > i)
        return 256 * (i - i1) / (m - i1);
      j++;
    }
  }

  public static String dateToTextShort(DateData paramDateData)
  {
    return String.valueOf(paramDateData.day) + getMonthNameShort(paramDateData.month) + String.valueOf(paramDateData.year);
  }

  public static boolean fromAbsolute(DateHolder paramDateHolder, int paramInt)
  {
    int i5;
    if (paramInt < 1)
    {
      i5 = 0;
      return i5;
    }
    int i = (paramInt + -1) / 6940;
    int j = i * 19;
    int k = 1 + i * 6940;
    if (k >= paramInt);
    while (true)
    {
      int n = getNewYearAbsolute(j);
      if (n >= paramInt)
      {
        if (n > paramInt)
          j += -1;
        if (getYearInfo(paramDateHolder, j))
          break label118;
        i5 = 0;
        break;
        if (isLeapYear(j));
        for (int m = 384; ; m = 354)
        {
          k += m;
          j += 1;
          break;
        }
      }
      j += 1;
    }
    label118: int[] arrayOfInt = paramDateHolder.hebMonthDurations;
    int i1 = arrayOfInt.length;
    int i2 = paramInt - paramDateHolder.hebNewYearAbsolute;
    int i3 = 6;
    while (true)
    {
      int i4 = arrayOfInt[i3];
      if (i2 < i4)
      {
        paramDateHolder.dateAbsolute = paramInt;
        paramDateHolder.dateHebrew = new DateData(i2 + 1, i3, j);
        i5 = 1;
        break;
      }
      i2 -= i4;
      i3++;
      if (i3 < i1)
        continue;
      i3 = 0;
    }
  }

  public static int getDayOfWeek(int paramInt)
  {
    return (paramInt + 0) % 7;
  }

  public static String getDayOfWeekName(int paramInt)
  {
    return MText.DowNamesHebrew[paramInt];
  }

  public static String getMonthName(int paramInt)
  {
    return MText.MonthNamesHebrew[paramInt];
  }

  public static String getMonthNameShort(int paramInt)
  {
    int i = MText.MonthNamesHebrewShortLength[paramInt];
    String str1 = MText.MonthNamesHebrew[paramInt];
    String str2;
    if (i == 0)
      str2 = str1;
    while (true)
    {
      return str2;
      str2 = str1.substring(0, i & 0xF);
      int j = 0xF & i >> 4;
      if (j == 0)
        continue;
      str2 = str2 + str1.substring(str1.length() - j);
    }
  }

  private static int getNewYearAbsolute(int paramInt)
  {
    int i = paramInt - 1;
    int j = i / 19;
    int k = i % 19;
    int m = j * 235 + k * 12 + (1 + k * 7) / 19;
    int n = m / 25920;
    int i1 = 5604 + 13753 * (m % 25920);
    int i2 = 1 + (m * 29 + n * 13753 + i1 / 25920);
    int i3 = i1 % 25920;
    yearStartLunarDays = i2;
    yearStartLunarParts = i3;
    int i4 = getDayOfWeek(i2);
    if ((i3 >= 19440) || ((i4 == 2) && (i3 >= 9924) && (!isLeapYear(paramInt))) || ((i4 == 1) && (i3 >= 16789) && (isLeapYear(paramInt - 1))))
    {
      i2++;
      i4 = getDayOfWeek(i2);
    }
    if ((i4 == 0) || (i4 == 3) || (i4 == 5))
      i2++;
    return i2;
  }

  public static int getOmerDay(DateHolder paramDateHolder)
  {
    DateData localDateData = paramDateHolder.dateHebrew;
    int i = localDateData.month;
    int k;
    int m;
    int n;
    int i1;
    int[] arrayOfInt;
    int i2;
    label41: int i3;
    int j;
    if (i <= 2)
    {
      k = 15;
      m = 49;
      n = 0;
      i1 = localDateData.day;
      arrayOfInt = paramDateHolder.hebMonthDurations;
      i2 = 0;
      if (i2 < i)
        break label104;
      i3 = i1 - k;
      if ((i3 >= 1) && (i3 <= m))
        break label120;
      j = -1;
    }
    while (true)
    {
      return j;
      if ((i >= 3) && (i <= 4))
      {
        k = 105;
        m = 21;
        n = 256;
        break;
      }
      j = -1;
      continue;
      label104: i1 += arrayOfInt[i2];
      i2++;
      break label41;
      label120: j = i3 | n;
    }
  }

  private static boolean getYearInfo(DateHolder paramDateHolder, int paramInt)
  {
    int i = 0;
    if ((paramInt < 1) || (paramInt > 5000000));
    while (true)
    {
      return i;
      DateData localDateData = paramDateHolder.dateHebrew;
      if ((localDateData == null) || (paramInt != localDateData.year))
        break;
      i = 1;
    }
    int j = getNewYearAbsolute(paramInt);
    paramDateHolder.hebYearStartLunarDays = yearStartLunarDays;
    paramDateHolder.hebYearStartLunarParts = yearStartLunarParts;
    int k = getNewYearAbsolute(paramInt + 1) - j;
    int m;
    label79: int n;
    label88: int[] arrayOfInt;
    int i1;
    label109: int i3;
    if (k > 356)
    {
      m = 1;
      if (m == 0)
        break label172;
      n = 13;
      arrayOfInt = new int[n];
      paramDateHolder.hebNewYearAbsolute = j;
      paramDateHolder.hebMonthDurations = arrayOfInt;
      i1 = 0;
      if (i1 < 12)
        break label179;
      if (m == 0)
        break label208;
      arrayOfInt[11] = 30;
      arrayOfInt[12] = 29;
      i3 = k - 384;
      label143: if (i3 >= 0)
        break label219;
      arrayOfInt[8] = (-1 + arrayOfInt[8]);
    }
    while (true)
    {
      i = 1;
      break;
      m = 0;
      break label79;
      label172: n = 12;
      break label88;
      label179: int i2 = i1 + 1;
      arrayOfInt[i1] = 30;
      i1 = i2 + 1;
      arrayOfInt[i2] = 29;
      break label109;
      label208: i3 = k - 354;
      break label143;
      label219: if (i3 <= 0)
        continue;
      arrayOfInt[7] = (1 + arrayOfInt[7]);
    }
  }

  public static boolean isLeapYear(int paramInt)
  {
    if ((1 + paramInt * 7) % 19 < 7);
    for (int i = 1; ; i = 0)
      return i;
  }

  public static int monthMaxDuration(int paramInt)
  {
    int i;
    switch (paramInt)
    {
    case 8:
    case 9:
    case 10:
    default:
      i = 30 - (paramInt & 0x1);
    case 12:
    case 7:
    case 11:
    }
    while (true)
    {
      return i;
      i = 29;
      continue;
      i = 30;
    }
  }

  public static boolean toAbsolute(DateHolder paramDateHolder, DateData paramDateData, boolean paramBoolean)
  {
    int i = 0;
    int j = paramDateData.day;
    int k = paramDateData.month;
    if ((k < 0) || (j <= 0) || (!getYearInfo(paramDateHolder, paramDateData.year)))
      return i;
    int[] arrayOfInt = paramDateHolder.hebMonthDurations;
    int m = arrayOfInt.length;
    label83: int n;
    int i3;
    label111: int i1;
    if (paramBoolean)
    {
      if (k >= m)
      {
        k = m - 1;
        paramDateData.month = k;
      }
      j = Math.min(j, arrayOfInt[k]);
      paramDateData.day = j;
      n = j - 1 + paramDateHolder.hebNewYearAbsolute;
      if (k >= 6)
        break label210;
      i3 = 6;
      if (i3 < m)
        break label164;
      i1 = 0;
    }
    while (true)
    {
      if (i1 >= k)
      {
        paramDateHolder.dateHebrew = paramDateData;
        paramDateHolder.dateAbsolute = n;
        i = 1;
        break;
        if (k >= m)
          break;
        if (j <= arrayOfInt[k])
          break label83;
        break;
        label164: int i4 = i3 + 1;
        n += arrayOfInt[i3];
        i3 = i4;
        break label111;
      }
      int i2 = i1 + 1;
      n += arrayOfInt[i1];
      i1 = i2;
      continue;
      label210: i1 = 6;
    }
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     libsrc.HebCalendar
 * JD-Core Version:    0.6.0
 */