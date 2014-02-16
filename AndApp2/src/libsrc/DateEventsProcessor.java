package libsrc;

import java.util.Vector;

class DateEventsProcessor extends EventProcessor
{
  public boolean lightCandle;
  public boolean needLightCandleEvaluation;

  DateEventsProcessor(DateHolder paramDateHolder, LocationHolder paramLocationHolder, boolean paramBoolean)
  {
    super(paramDateHolder, paramLocationHolder);
    this.needLightCandleEvaluation = paramBoolean;
    if ((this.needLightCandleEvaluation) && (HebCalendar.getDayOfWeek(this.targetDateHolder.dateAbsolute) == 5))
      bool = true;
    this.lightCandle = bool;
  }

  boolean processEvent(byte[] paramArrayOfByte, byte paramByte1, byte paramByte2, boolean paramBoolean1, boolean paramBoolean2)
  {
    int i = paramArrayOfByte[2];
    int i7;
    if (paramBoolean2)
      if (this.targetDateHolder.hebMonthDurations.length < 13)
        i7 = 0;
    int k;
    int i1;
    int i2;
    label162: label191: DateHolder localDateHolder;
    label229: int i6;
    while (true)
    {
      return i7;
      i--;
      int j = paramArrayOfByte[1];
      k = paramArrayOfByte[0];
      DateData localDateData = this.targetDateHolder.dateHebrew;
      int m = localDateData.year;
      int n = localDateData.month;
      i1 = 1;
      i2 = 0;
      if (paramArrayOfByte.length > 3)
        i1 = paramArrayOfByte[3];
      int i3 = this.targetDateHolder.hebMonthDurations.length;
      if (paramBoolean1)
        i = Math.min(i, i3 - 1);
      int i4 = i - n;
      if (i4 > 1)
        i4 -= i3;
      while (true)
      {
        if (i4 <= 0)
          break label229;
        if (i4 <= 1)
          break label162;
        i7 = 0;
        break;
        if (i4 >= -1)
          continue;
        i4 += i3;
      }
      if ((j > 1) || ((paramByte1 & 0x8) == 0))
      {
        i7 = 0;
        continue;
      }
      if (i == 6)
        m++;
      if (paramArrayOfByte.length > 5);
      for (int i5 = paramArrayOfByte[5]; ; i5 = 0)
      {
        localDateHolder = acceptEventDate(j, i, m, i5, paramBoolean1);
        if (localDateHolder != null)
          break label273;
        i7 = 0;
        break;
        if (i4 >= 0)
          break label191;
        if (i4 < -1)
        {
          i7 = 0;
          break;
        }
        if ((n < 6) || (i >= 6))
          break label191;
        m--;
        break label191;
      }
      label273: i6 = this.targetDateHolder.dateAbsolute - localDateHolder.dateAbsolute;
      if ((i6 >= -1) || (m <= 0))
        break;
      int i13 = m - 1;
      localDateHolder = acceptEventDate(j, i, i13, i5, paramBoolean1);
      if (localDateHolder == null)
      {
        i7 = 0;
        continue;
      }
      i6 = this.targetDateHolder.dateAbsolute - localDateHolder.dateAbsolute;
    }
    String str2;
    int i12;
    label400: label447: int i8;
    int i9;
    if ((i6 >= 0) && (i6 < i1))
      if (paramBoolean2)
      {
        str2 = EventData.getEventName(k, localDateHolder, 1) + MText.TitleKatan[0];
        i12 = 71;
        if (i1 > 1)
          str2 = str2 + ",  יום " + (i6 + 1);
        processEventName(paramByte1, str2, i12);
        i2 = 1;
        if ((i2 != 0) && (this.needLightCandleEvaluation) && (!this.lightCandle) && ((paramByte2 & 0x3) != 0))
        {
          i8 = i6 + 1;
          if (i8 >= 0)
          {
            i9 = i1;
            if (paramArrayOfByte.length > 4)
            {
              int i10 = paramArrayOfByte[4];
              if (i10 != 0)
              {
                i9 = i10;
                if ((i9 << 1 < i1) && ((paramByte2 & 0x4) != 0))
                  i8 %= (i1 - i9);
              }
            }
            if ((paramByte2 & 0x2) != 0)
              break label673;
            if (i8 != 0)
              break label667;
          }
        }
      }
    label667: for (boolean bool2 = true; ; bool2 = false)
    {
      this.lightCandle = bool2;
      i7 = 1;
      break;
      str2 = EventData.getEventName(k, localDateHolder, 0);
      i12 = 65;
      break label400;
      if (i6 != -1)
        break label447;
      if ((paramByte1 & 0x8) != 0)
      {
        String str1 = "ערב " + EventData.getEventName(k, localDateHolder, 1);
        int i11 = 69;
        if (paramBoolean2)
        {
          str1 = str1 + MText.TitleKatan[1];
          i11 = 71;
        }
        processEventName(paramByte1, str1, i11);
      }
      i2 = 1;
      break label447;
    }
    label673: if (i8 < i9);
    for (boolean bool1 = true; ; bool1 = false)
    {
      this.lightCandle = bool1;
      break;
    }
  }

  void processEventName(byte paramByte, String paramString, int paramInt)
  {
    if ((paramByte & 0x4) == 0)
      paramInt++;
    char c = (char)paramInt;
    int i = this.eventList.size();
    i--;
    if (i < 0);
    while (true)
    {
      this.eventList.insertElementAt(c + paramString, i + 1);
      return;
      if (((String)this.eventList.elementAt(i)).charAt(0) > c)
        break;
    }
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     libsrc.DateEventsProcessor
 * JD-Core Version:    0.6.0
 */