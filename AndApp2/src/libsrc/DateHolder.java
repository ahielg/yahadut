package libsrc;

import java.util.Calendar;
import java.util.Date;

public class DateHolder
{
  public boolean alwaysJulian = false;
  public int dateAbsolute = -1;
  public DateData dateHebrew = null;
  public DateData dateSecular = null;
  public int[] hebMonthDurations = null;
  public int hebNewYearAbsolute = -1;
  public int hebYearStartLunarDays;
  public int hebYearStartLunarParts;
  public boolean isGregorian = false;
  public int secNewYearAbsolute = -1;

  public DateHolder()
  {
  }

  public DateHolder(DateHolder paramDateHolder)
  {
    this();
    set(paramDateHolder);
  }

  public boolean acceptAbsoluteDate(int paramInt)
  {
    if ((HebCalendar.fromAbsolute(this, paramInt)) && (SecularCalendar.fromAbsolute(this, paramInt)));
    for (int i = 1; ; i = 0)
      return i;
  }

  public boolean acceptCalendarDate(Calendar paramCalendar)
  {
    return acceptSecularDate(new DateData(paramCalendar.get(5), paramCalendar.get(2), paramCalendar.get(1)));
  }

  public boolean acceptCurrentDate()
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTime(new Date());
    return acceptCalendarDate(localCalendar);
  }

  public boolean acceptHebYear(int paramInt)
  {
    DateData localDateData = new DateData(this.dateHebrew);
    localDateData.year = paramInt;
    return acceptHebrewDate(localDateData, true);
  }

  public boolean acceptHebrewDate(DateData paramDateData, boolean paramBoolean)
  {
    if ((HebCalendar.toAbsolute(this, paramDateData, paramBoolean)) && (SecularCalendar.fromAbsolute(this, this.dateAbsolute)));
    for (int i = 1; ; i = 0)
      return i;
  }

  public boolean acceptSecYear(int paramInt)
  {
    DateData localDateData = new DateData(this.dateSecular);
    localDateData.year = paramInt;
    return acceptSecularDate(localDateData);
  }

  public boolean acceptSecularDate(DateData paramDateData)
  {
    boolean bool;
    int i;
    if ((SecularCalendar.toAbsolute(this, paramDateData)) && (HebCalendar.fromAbsolute(this, this.dateAbsolute)))
    {
      bool = true;
      if ((bool) && (!this.alwaysJulian))
      {
        i = EventData.gregorianStart.dateAbsolute;
        if (this.dateAbsolute <= i)
          break label93;
        j = 1;
        label50: if (!this.isGregorian)
          if (j == 0)
            break label99;
      }
    }
    label93: label99: for (int j = 0; ; j = 1)
    {
      if (j == 0)
      {
        if (this.isGregorian)
          i++;
        bool = acceptAbsoluteDate(i);
      }
      return bool;
      bool = false;
      break;
      j = 0;
      break label50;
    }
  }

  public boolean incrementDay(int paramInt)
  {
    return acceptAbsoluteDate(paramInt + this.dateAbsolute);
  }

  public boolean incrementHebMonth(int paramInt)
  {
    DateData localDateData = new DateData(this.dateHebrew);
    int i = paramInt + localDateData.month;
    localDateData.month = i;
    int j = this.hebMonthDurations.length;
    if (paramInt < 0)
      if (i == 5)
        localDateData.year = (-1 + localDateData.year);
    while (true)
    {
      localDateData.month = i;
      return acceptHebrewDate(localDateData, true);
      if (i >= 0)
        continue;
      i = j - 1;
      continue;
      if (i == 6)
      {
        localDateData.year = (1 + localDateData.year);
        continue;
      }
      if (i < j)
        continue;
      i = 0;
    }
  }

  public boolean incrementHebYear(int paramInt)
  {
    DateData localDateData = new DateData(this.dateHebrew);
    localDateData.year = (paramInt + localDateData.year);
    return acceptHebrewDate(localDateData, true);
  }

  public boolean incrementSecMonth(int paramInt)
  {
    DateData localDateData = new DateData(this.dateSecular);
    int i = paramInt + localDateData.month;
    localDateData.month = i;
    if (i < 0)
    {
      localDateData.year = (-1 + localDateData.year);
      i = 11;
    }
    while (true)
    {
      localDateData.month = i;
      return acceptSecularDate(localDateData);
      if (i < 12)
        continue;
      localDateData.year = (1 + localDateData.year);
      i = 0;
    }
  }

  public boolean incrementSecYear(int paramInt)
  {
    DateData localDateData = new DateData(this.dateSecular);
    localDateData.year = (paramInt + localDateData.year);
    return acceptSecularDate(localDateData);
  }

  public void set(DateHolder paramDateHolder)
  {
    this.dateHebrew = paramDateHolder.dateHebrew;
    this.dateSecular = paramDateHolder.dateSecular;
    this.dateAbsolute = paramDateHolder.dateAbsolute;
    this.hebNewYearAbsolute = paramDateHolder.hebNewYearAbsolute;
    this.secNewYearAbsolute = paramDateHolder.secNewYearAbsolute;
    this.isGregorian = paramDateHolder.isGregorian;
    this.alwaysJulian = paramDateHolder.alwaysJulian;
    this.hebYearStartLunarDays = paramDateHolder.hebYearStartLunarDays;
    this.hebYearStartLunarParts = paramDateHolder.hebYearStartLunarParts;
    this.hebMonthDurations = paramDateHolder.hebMonthDurations;
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     libsrc.DateHolder
 * JD-Core Version:    0.6.0
 */