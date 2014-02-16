package libsrc;

import java.util.Vector;

public class EventData
{
  public static final byte CANDLES_CONTINUOUS = 2;
  public static final byte CANDLES_FRAME = 4;
  public static final byte CANDLES_NONE = 0;
  public static final byte CANDLES_NORMAL = 1;
  public static final byte CANDLE_MODE_MASK = 3;
  public static final byte CUSTOM_FLAG = -128;
  public static byte[] CustomEventCandleLight;
  public static Vector CustomEventNames;
  public static byte[] CustomEventSettings;
  public static Vector CustomEvents;
  private static final byte[] DefaultCandleLight;
  private static final byte[] DefaultFlags;
  public static final byte EVENT_DISPLAYED = 2;
  public static final byte EVENT_EREV = 8;
  public static final byte EVENT_FLAGS_DEFAULT = 3;
  public static final byte EVENT_HIGHLIGHTED = 4;
  public static final byte EVENT_KATAN = 16;
  public static final byte EVENT_LISTED = 1;
  public static final byte EVENT_REMINDER = 64;
  public static byte[] HolidayCandleLight;
  public static byte[] HolidaySettings;
  private static final byte[][] HolidaysDiaspora;
  private static final byte[][] HolidaysIsrael;
  public static final int INDEX_CANDLELIGHT = 4;
  public static final int INDEX_COUNT_CUSTOM = 5;
  public static final int INDEX_DAY = 1;
  public static final int INDEX_DURATION = 3;
  public static final int INDEX_MONTH = 2;
  public static final int INDEX_NAMEIND = 0;
  public static final int INDEX_VAR = 5;
  public static final int LONG_NAME_SIZE = 20;
  public static final int MAX_CUSTOM_EVENTS = 128;
  private static final int PESACH_INDEX = 2;
  public static final int REGULAR_HOLIDAYS_START = 1;
  private static final int ROSHSHANA_INDEX = 12;
  public static final byte ROSH_CHODESH_INDEX = 0;
  public static final int SHORT_NAME_SIZE = 12;
  private static final int[] ShabbatimPesach;
  private static final int[] ShabbatimRoshHaShana;
  private static final int[] ShabbatimTishaBAv;
  private static final int TISHABAV_INDEX = 10;
  static final int VAR_PRE_WEND_BACK = 4;
  static final int VAR_SHAB_FORWARD = 1;
  static final int VAR_WEND_BACK = 3;
  public static DateHolder gregorianStart;
  public static final DateData gregorianStartDefault = new DateData(4, 9, 1582);
  public byte candleLightSettings;
  public final byte[] eventData;
  public final DateHolder eventDate;
  public boolean isKatan;

  static
  {
    byte[] arrayOfByte1 = new byte[25];
    arrayOfByte1[0] = 2;
    arrayOfByte1[1] = 11;
    arrayOfByte1[2] = 79;
    arrayOfByte1[3] = 3;
    arrayOfByte1[4] = 3;
    arrayOfByte1[5] = 79;
    arrayOfByte1[6] = 11;
    arrayOfByte1[7] = 3;
    arrayOfByte1[8] = 79;
    arrayOfByte1[9] = 3;
    arrayOfByte1[10] = 11;
    arrayOfByte1[11] = 2;
    arrayOfByte1[12] = 79;
    arrayOfByte1[13] = 11;
    arrayOfByte1[14] = 79;
    arrayOfByte1[15] = 79;
    arrayOfByte1[16] = 7;
    arrayOfByte1[17] = 7;
    arrayOfByte1[18] = 7;
    arrayOfByte1[19] = 75;
    arrayOfByte1[20] = 3;
    arrayOfByte1[21] = 7;
    arrayOfByte1[22] = 11;
    arrayOfByte1[23] = 95;
    arrayOfByte1[24] = 2;
    DefaultFlags = arrayOfByte1;
    byte[] arrayOfByte2 = new byte[25];
    arrayOfByte2[2] = 5;
    arrayOfByte2[8] = 1;
    arrayOfByte2[12] = 1;
    arrayOfByte2[14] = 1;
    arrayOfByte2[15] = 1;
    arrayOfByte2[17] = 1;
    arrayOfByte2[19] = 2;
    DefaultCandleLight = arrayOfByte2;
    byte[][] arrayOfByte3 = new byte[25][];
    byte[] arrayOfByte4 = new byte[3];
    arrayOfByte4[1] = 1;
    arrayOfByte4[2] = 14;
    arrayOfByte3[0] = arrayOfByte4;
    byte[] arrayOfByte5 = new byte[6];
    arrayOfByte5[0] = 1;
    arrayOfByte5[1] = 14;
    arrayOfByte5[3] = 1;
    arrayOfByte5[5] = 3;
    arrayOfByte3[1] = arrayOfByte5;
    byte[] arrayOfByte6 = new byte[5];
    arrayOfByte6[0] = 2;
    arrayOfByte6[1] = 15;
    arrayOfByte6[3] = 8;
    arrayOfByte6[4] = 2;
    arrayOfByte3[2] = arrayOfByte6;
    byte[] arrayOfByte7 = new byte[3];
    arrayOfByte7[0] = 3;
    arrayOfByte7[1] = 27;
    arrayOfByte3[3] = arrayOfByte7;
    byte[] arrayOfByte8 = new byte[6];
    arrayOfByte8[0] = 4;
    arrayOfByte8[1] = 4;
    arrayOfByte8[2] = 1;
    arrayOfByte8[3] = 1;
    arrayOfByte8[5] = 4;
    arrayOfByte3[4] = arrayOfByte8;
    byte[] arrayOfByte9 = new byte[6];
    arrayOfByte9[0] = 5;
    arrayOfByte9[1] = 5;
    arrayOfByte9[2] = 1;
    arrayOfByte9[3] = 1;
    arrayOfByte9[5] = 3;
    arrayOfByte3[5] = arrayOfByte9;
    byte[] arrayOfByte10 = new byte[3];
    arrayOfByte10[0] = 6;
    arrayOfByte10[1] = 18;
    arrayOfByte10[2] = 1;
    arrayOfByte3[6] = arrayOfByte10;
    byte[] arrayOfByte11 = new byte[3];
    arrayOfByte11[0] = 7;
    arrayOfByte11[1] = 28;
    arrayOfByte11[2] = 1;
    arrayOfByte3[7] = arrayOfByte11;
    byte[] arrayOfByte12 = new byte[4];
    arrayOfByte12[0] = 8;
    arrayOfByte12[1] = 6;
    arrayOfByte12[2] = 2;
    arrayOfByte12[3] = 2;
    arrayOfByte3[8] = arrayOfByte12;
    byte[] arrayOfByte13 = new byte[6];
    arrayOfByte13[0] = 9;
    arrayOfByte13[1] = 17;
    arrayOfByte13[2] = 3;
    arrayOfByte13[3] = 1;
    arrayOfByte13[5] = 1;
    arrayOfByte3[9] = arrayOfByte13;
    byte[] arrayOfByte14 = new byte[6];
    arrayOfByte14[0] = 10;
    arrayOfByte14[1] = 9;
    arrayOfByte14[2] = 4;
    arrayOfByte14[3] = 1;
    arrayOfByte14[5] = 1;
    arrayOfByte3[10] = arrayOfByte14;
    byte[] arrayOfByte15 = new byte[3];
    arrayOfByte15[0] = 11;
    arrayOfByte15[1] = 15;
    arrayOfByte15[2] = 4;
    arrayOfByte3[11] = arrayOfByte15;
    byte[] arrayOfByte16 = new byte[4];
    arrayOfByte16[0] = 12;
    arrayOfByte16[1] = 1;
    arrayOfByte16[2] = 6;
    arrayOfByte16[3] = 2;
    arrayOfByte3[12] = arrayOfByte16;
    byte[] arrayOfByte17 = new byte[6];
    arrayOfByte17[0] = 13;
    arrayOfByte17[1] = 3;
    arrayOfByte17[2] = 6;
    arrayOfByte17[3] = 1;
    arrayOfByte17[5] = 1;
    arrayOfByte3[13] = arrayOfByte17;
    byte[] arrayOfByte18 = new byte[3];
    arrayOfByte18[0] = 14;
    arrayOfByte18[1] = 10;
    arrayOfByte18[2] = 6;
    arrayOfByte3[14] = arrayOfByte18;
    byte[] arrayOfByte19 = new byte[5];
    arrayOfByte19[0] = 15;
    arrayOfByte19[1] = 15;
    arrayOfByte19[2] = 6;
    arrayOfByte19[3] = 6;
    arrayOfByte19[4] = 2;
    arrayOfByte3[15] = arrayOfByte19;
    byte[] arrayOfByte20 = new byte[3];
    arrayOfByte20[0] = 16;
    arrayOfByte20[1] = 21;
    arrayOfByte20[2] = 6;
    arrayOfByte3[16] = arrayOfByte20;
    byte[] arrayOfByte21 = new byte[5];
    arrayOfByte21[0] = 17;
    arrayOfByte21[1] = 22;
    arrayOfByte21[2] = 6;
    arrayOfByte21[3] = 1;
    arrayOfByte21[4] = 2;
    arrayOfByte3[17] = arrayOfByte21;
    byte[] arrayOfByte22 = new byte[3];
    arrayOfByte22[0] = 18;
    arrayOfByte22[1] = 23;
    arrayOfByte22[2] = 6;
    arrayOfByte3[18] = arrayOfByte22;
    byte[] arrayOfByte23 = new byte[4];
    arrayOfByte23[0] = 19;
    arrayOfByte23[1] = 25;
    arrayOfByte23[2] = 8;
    arrayOfByte23[3] = 8;
    arrayOfByte3[19] = arrayOfByte23;
    byte[] arrayOfByte24 = new byte[6];
    arrayOfByte24[0] = 20;
    arrayOfByte24[1] = 10;
    arrayOfByte24[2] = 9;
    arrayOfByte24[3] = 1;
    arrayOfByte24[5] = 3;
    arrayOfByte3[20] = arrayOfByte24;
    byte[] arrayOfByte25 = new byte[3];
    arrayOfByte25[0] = 21;
    arrayOfByte25[1] = 15;
    arrayOfByte25[2] = 10;
    arrayOfByte3[21] = arrayOfByte25;
    byte[] arrayOfByte26 = new byte[6];
    arrayOfByte26[0] = 22;
    arrayOfByte26[1] = 13;
    arrayOfByte26[2] = 12;
    arrayOfByte26[3] = 1;
    arrayOfByte26[5] = 3;
    arrayOfByte3[22] = arrayOfByte26;
    byte[] arrayOfByte27 = new byte[3];
    arrayOfByte27[0] = 23;
    arrayOfByte27[1] = 14;
    arrayOfByte27[2] = 12;
    arrayOfByte3[23] = arrayOfByte27;
    byte[] arrayOfByte28 = new byte[6];
    arrayOfByte28[0] = 24;
    arrayOfByte28[1] = 15;
    arrayOfByte28[2] = 12;
    arrayOfByte28[3] = 1;
    arrayOfByte28[5] = 1;
    arrayOfByte3[24] = arrayOfByte28;
    HolidaysDiaspora = arrayOfByte3;
    byte[][] arrayOfByte29 = new byte[25][];
    byte[] arrayOfByte30 = new byte[3];
    arrayOfByte30[1] = 1;
    arrayOfByte30[2] = 14;
    arrayOfByte29[0] = arrayOfByte30;
    byte[] arrayOfByte31 = new byte[6];
    arrayOfByte31[0] = 1;
    arrayOfByte31[1] = 14;
    arrayOfByte31[3] = 1;
    arrayOfByte31[5] = 3;
    arrayOfByte29[1] = arrayOfByte31;
    byte[] arrayOfByte32 = new byte[5];
    arrayOfByte32[0] = 2;
    arrayOfByte32[1] = 15;
    arrayOfByte32[3] = 7;
    arrayOfByte32[4] = 1;
    arrayOfByte29[2] = arrayOfByte32;
    byte[] arrayOfByte33 = new byte[3];
    arrayOfByte33[0] = 3;
    arrayOfByte33[1] = 27;
    arrayOfByte29[3] = arrayOfByte33;
    byte[] arrayOfByte34 = new byte[6];
    arrayOfByte34[0] = 4;
    arrayOfByte34[1] = 4;
    arrayOfByte34[2] = 1;
    arrayOfByte34[3] = 1;
    arrayOfByte34[5] = 4;
    arrayOfByte29[4] = arrayOfByte34;
    byte[] arrayOfByte35 = new byte[6];
    arrayOfByte35[0] = 5;
    arrayOfByte35[1] = 5;
    arrayOfByte35[2] = 1;
    arrayOfByte35[3] = 1;
    arrayOfByte35[5] = 3;
    arrayOfByte29[5] = arrayOfByte35;
    byte[] arrayOfByte36 = new byte[3];
    arrayOfByte36[0] = 6;
    arrayOfByte36[1] = 18;
    arrayOfByte36[2] = 1;
    arrayOfByte29[6] = arrayOfByte36;
    byte[] arrayOfByte37 = new byte[3];
    arrayOfByte37[0] = 7;
    arrayOfByte37[1] = 28;
    arrayOfByte37[2] = 1;
    arrayOfByte29[7] = arrayOfByte37;
    byte[] arrayOfByte38 = new byte[3];
    arrayOfByte38[0] = 8;
    arrayOfByte38[1] = 6;
    arrayOfByte38[2] = 2;
    arrayOfByte29[8] = arrayOfByte38;
    byte[] arrayOfByte39 = new byte[6];
    arrayOfByte39[0] = 9;
    arrayOfByte39[1] = 17;
    arrayOfByte39[2] = 3;
    arrayOfByte39[3] = 1;
    arrayOfByte39[5] = 1;
    arrayOfByte29[9] = arrayOfByte39;
    byte[] arrayOfByte40 = new byte[6];
    arrayOfByte40[0] = 10;
    arrayOfByte40[1] = 9;
    arrayOfByte40[2] = 4;
    arrayOfByte40[3] = 1;
    arrayOfByte40[5] = 1;
    arrayOfByte29[10] = arrayOfByte40;
    byte[] arrayOfByte41 = new byte[3];
    arrayOfByte41[0] = 11;
    arrayOfByte41[1] = 15;
    arrayOfByte41[2] = 4;
    arrayOfByte29[11] = arrayOfByte41;
    byte[] arrayOfByte42 = new byte[4];
    arrayOfByte42[0] = 12;
    arrayOfByte42[1] = 1;
    arrayOfByte42[2] = 6;
    arrayOfByte42[3] = 2;
    arrayOfByte29[12] = arrayOfByte42;
    byte[] arrayOfByte43 = new byte[6];
    arrayOfByte43[0] = 13;
    arrayOfByte43[1] = 3;
    arrayOfByte43[2] = 6;
    arrayOfByte43[3] = 1;
    arrayOfByte43[5] = 1;
    arrayOfByte29[13] = arrayOfByte43;
    byte[] arrayOfByte44 = new byte[3];
    arrayOfByte44[0] = 14;
    arrayOfByte44[1] = 10;
    arrayOfByte44[2] = 6;
    arrayOfByte29[14] = arrayOfByte44;
    byte[] arrayOfByte45 = new byte[5];
    arrayOfByte45[0] = 15;
    arrayOfByte45[1] = 15;
    arrayOfByte45[2] = 6;
    arrayOfByte45[3] = 6;
    arrayOfByte45[4] = 1;
    arrayOfByte29[15] = arrayOfByte45;
    byte[] arrayOfByte46 = new byte[3];
    arrayOfByte46[0] = 16;
    arrayOfByte46[1] = 21;
    arrayOfByte46[2] = 6;
    arrayOfByte29[16] = arrayOfByte46;
    byte[] arrayOfByte47 = new byte[3];
    arrayOfByte47[0] = 17;
    arrayOfByte47[1] = 22;
    arrayOfByte47[2] = 6;
    arrayOfByte29[17] = arrayOfByte47;
    byte[] arrayOfByte48 = new byte[3];
    arrayOfByte48[0] = 18;
    arrayOfByte48[1] = 22;
    arrayOfByte48[2] = 6;
    arrayOfByte29[18] = arrayOfByte48;
    byte[] arrayOfByte49 = new byte[4];
    arrayOfByte49[0] = 19;
    arrayOfByte49[1] = 25;
    arrayOfByte49[2] = 8;
    arrayOfByte49[3] = 8;
    arrayOfByte29[19] = arrayOfByte49;
    byte[] arrayOfByte50 = new byte[6];
    arrayOfByte50[0] = 20;
    arrayOfByte50[1] = 10;
    arrayOfByte50[2] = 9;
    arrayOfByte50[3] = 1;
    arrayOfByte50[5] = 3;
    arrayOfByte29[20] = arrayOfByte50;
    byte[] arrayOfByte51 = new byte[3];
    arrayOfByte51[0] = 21;
    arrayOfByte51[1] = 15;
    arrayOfByte51[2] = 10;
    arrayOfByte29[21] = arrayOfByte51;
    byte[] arrayOfByte52 = new byte[6];
    arrayOfByte52[0] = 22;
    arrayOfByte52[1] = 13;
    arrayOfByte52[2] = 12;
    arrayOfByte52[3] = 1;
    arrayOfByte52[5] = 3;
    arrayOfByte29[22] = arrayOfByte52;
    byte[] arrayOfByte53 = new byte[3];
    arrayOfByte53[0] = 23;
    arrayOfByte53[1] = 14;
    arrayOfByte53[2] = 12;
    arrayOfByte29[23] = arrayOfByte53;
    byte[] arrayOfByte54 = new byte[5];
    arrayOfByte54[0] = 24;
    arrayOfByte54[1] = 15;
    arrayOfByte54[2] = 12;
    arrayOfByte54[3] = 1;
    arrayOfByte54[4] = 1;
    arrayOfByte29[24] = arrayOfByte54;
    HolidaysIsrael = arrayOfByte29;
    int[] arrayOfInt1 = new int[5];
    arrayOfInt1[0] = -43;
    arrayOfInt1[1] = -30;
    arrayOfInt1[2] = -21;
    arrayOfInt1[3] = -14;
    arrayOfInt1[4] = -1;
    ShabbatimPesach = arrayOfInt1;
    int[] arrayOfInt2 = new int[2];
    arrayOfInt2[1] = 7;
    ShabbatimTishaBAv = arrayOfInt2;
    int[] arrayOfInt3 = new int[1];
    arrayOfInt3[0] = 7;
    ShabbatimRoshHaShana = arrayOfInt3;
  }

  EventData(DateHolder paramDateHolder, byte[] paramArrayOfByte, byte paramByte, boolean paramBoolean)
  {
    this.eventDate = paramDateHolder;
    this.eventData = paramArrayOfByte;
    this.candleLightSettings = paramByte;
    this.isKatan = paramBoolean;
  }

  private static boolean checkHavdalahForDate(DateHolder paramDateHolder, LocationHolder paramLocationHolder, int paramInt)
  {
    DateHolder localDateHolder = new DateHolder(paramDateHolder);
    localDateHolder.incrementDay(1);
    Vector localVector = getEvents(new PastEventsProcessor(localDateHolder, paramLocationHolder), paramInt, true);
    int i = localVector.size();
    int j = 0;
    if (j >= i);
    for (int i2 = 0; ; i2 = 1)
    {
      return i2;
      EventData localEventData = (EventData)localVector.elementAt(j);
      int k = localEventData.candleLightSettings;
      if ((k & 0x3) == 0);
      int m;
      int n;
      int i1;
      do
      {
        j++;
        break;
        m = 1;
        byte[] arrayOfByte = localEventData.eventData;
        if (arrayOfByte.length > 3)
          m = arrayOfByte[3];
        n = 0;
        if (arrayOfByte.length > 4)
          n = arrayOfByte[4];
        if (n == 0)
          n = m;
        i1 = 1 + (paramDateHolder.dateAbsolute - localEventData.eventDate.dateAbsolute);
      }
      while ((i1 <= 0) || ((i1 != n) && (((k & 0x4) == 0) || (i1 != m))));
    }
  }

  public static void fixCustomEventReferences()
  {
    int i = CustomEvents.size();
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return;
      ((byte[])CustomEvents.elementAt(j))[0] = (byte)(j | 0xFFFFFF80);
    }
  }

  public static String getCustomEventName(int paramInt1, int paramInt2)
  {
    return ((String[])CustomEventNames.elementAt(paramInt1))[paramInt2];
  }

  public static String getEventName(int paramInt1, DateHolder paramDateHolder, int paramInt2)
  {
    String str;
    if ((paramInt1 & 0xFFFFFF80) != 0)
      str = getCustomEventName(paramInt1 & 0x7F, paramInt2);
    while (true)
    {
      return str;
      str = getHolidayName(paramInt1, paramInt2);
      if (paramInt1 != 0)
        continue;
      str = str + " " + HebCalendar.getMonthName(paramDateHolder.dateHebrew.month);
    }
  }

  public static Vector getEventNamesForDate(DateHolder paramDateHolder, LocationHolder paramLocationHolder, boolean paramBoolean)
  {
    DateEventsProcessor localDateEventsProcessor = new DateEventsProcessor(paramDateHolder, paramLocationHolder, paramBoolean);
    Vector localVector = getEvents(localDateEventsProcessor, 14, false);
    if (paramBoolean)
    {
      if (!localDateEventsProcessor.lightCandle)
        break label41;
      localVector.addElement("ZL");
    }
    while (true)
    {
      return localVector;
      label41: if ((HebCalendar.getDayOfWeek(paramDateHolder.dateAbsolute) != 6) && (!checkHavdalahForDate(paramDateHolder, paramLocationHolder, 14)))
        continue;
      localVector.addElement("ZH");
    }
  }

  private static Vector getEvents(EventProcessor paramEventProcessor, int paramInt, boolean paramBoolean)
  {
    byte[][] arrayOfByte = getHolidayData(paramEventProcessor.targetLocation);
    int i = arrayOfByte.length;
    int j = 1;
    int m;
    byte b2;
    byte[] arrayOfByte3;
    int i3;
    label72: int n;
    if (j >= i)
    {
      m = HolidaySettings[0];
      b2 = HolidayCandleLight[0];
      if (((m & paramInt) != 0) && ((!paramBoolean) || ((b2 & 0x3) != 0)))
      {
        arrayOfByte3 = new byte[3];
        arrayOfByte3[0] = 0;
        arrayOfByte3[1] = 1;
        i3 = 0;
        if (i3 < 13)
          break label191;
      }
      n = CustomEvents.size();
    }
    for (int i1 = 0; ; i1++)
    {
      if (i1 >= n)
      {
        return paramEventProcessor.getEventList();
        int k = HolidaySettings[j];
        byte b1 = HolidayCandleLight[j];
        byte[] arrayOfByte1 = arrayOfByte[j];
        if (((k & paramInt) != 0) && ((!paramBoolean) || ((b1 & 0x3) != 0)))
        {
          paramEventProcessor.processEvent(arrayOfByte1, k, b1, true, false);
          if ((arrayOfByte1[2] == 12) && ((k & 0x10) != 0))
            paramEventProcessor.processEvent(arrayOfByte1, k, b1, false, true);
        }
        j++;
        break;
        label191: if (i3 != 6)
        {
          arrayOfByte3[2] = (byte)i3;
          paramEventProcessor.processEvent(arrayOfByte3, m, b2, false, false);
        }
        i3++;
        break label72;
      }
      int i2 = CustomEventSettings[i1];
      byte b3 = CustomEventCandleLight[i1];
      if (((i2 & paramInt) == 0) || ((paramBoolean) && ((b3 & 0x3) == 0)))
        continue;
      byte[] arrayOfByte2 = (byte[])CustomEvents.elementAt(i1);
      paramEventProcessor.processEvent(arrayOfByte2, i2, b3, true, false);
      if ((arrayOfByte2[2] != 12) || ((i2 & 0x10) == 0))
        continue;
      paramEventProcessor.processEvent(arrayOfByte2, i2, b3, false, true);
    }
  }

  public static int getHolidayCount()
  {
    return MText.HolidayNames.length;
  }

  public static byte[][] getHolidayData(LocationHolder paramLocationHolder)
  {
    if (paramLocationHolder.holidayStyle == 0);
    for (byte[][] arrayOfByte = HolidaysIsrael; ; arrayOfByte = HolidaysDiaspora)
      return arrayOfByte;
  }

  public static String getHolidayName(int paramInt1, int paramInt2)
  {
    return MText.HolidayNames[paramInt1][paramInt2];
  }

  public static Vector getListOfEvents(DateHolder paramDateHolder, LocationHolder paramLocationHolder, boolean paramBoolean)
  {
    if (paramBoolean);
    for (Object localObject = new PastEventsProcessor(paramDateHolder, paramLocationHolder); ; localObject = new ComingEventsProcessor(paramDateHolder, paramLocationHolder))
      return getEvents((EventProcessor)localObject, 1, false);
  }

  public static String getReminderForDate(DateHolder paramDateHolder, LocationHolder paramLocationHolder, int paramInt)
  {
    String str1 = null;
    if (paramInt <= 0);
    while (true)
    {
      return str1;
      Vector localVector = getEvents(new ComingEventsProcessor(paramDateHolder, paramLocationHolder), 64, false);
      if ((localVector == null) || (localVector.size() == 0))
        continue;
      int i = 0;
      EventData localEventData;
      DateHolder localDateHolder;
      int j;
      do
      {
        localEventData = (EventData)localVector.elementAt(i);
        localDateHolder = localEventData.eventDate;
        j = -1 + (localDateHolder.dateAbsolute - paramDateHolder.dateAbsolute);
        if (j > paramInt)
          break;
        i++;
      }
      while (j <= 0);
      String str2 = getEventName(localEventData.eventData[0], localDateHolder, 1);
      if (localEventData.isKatan)
        str2 = str2 + MText.TitleKatan[1];
      str1 = str2 + ' ' + MText.getInNightsText(j);
    }
  }

  public static final String getShabbatName(DateHolder paramDateHolder, LocationHolder paramLocationHolder)
  {
    DateData localDateData = paramDateHolder.dateHebrew;
    int[] arrayOfInt = (int[])null;
    String[] arrayOfString = (String[])null;
    int i = localDateData.month;
    int j = -1;
    if ((i <= 0) || (i >= -1 + paramDateHolder.hebMonthDurations.length))
    {
      arrayOfInt = ShabbatimPesach;
      arrayOfString = MText.ShabbathPesachNames;
      j = 2;
    }
    String str;
    while (arrayOfInt == null)
    {
      str = null;
      return str;
      if (i == 4)
      {
        arrayOfInt = ShabbatimTishaBAv;
        arrayOfString = MText.ShabbathTishaBAvNames;
        j = 10;
        continue;
      }
      if (i != 6)
        continue;
      arrayOfInt = ShabbatimRoshHaShana;
      arrayOfString = MText.ShabbathRoshHaShanaNames;
      j = 12;
    }
    int k = arrayOfInt.length;
    byte[] arrayOfByte = getHolidayData(paramLocationHolder)[j];
    DateHolder localDateHolder = new DateHolder();
    localDateHolder.acceptHebrewDate(new DateData(arrayOfByte[1], arrayOfByte[2], localDateData.year), true);
    int m = paramDateHolder.dateAbsolute - localDateHolder.dateAbsolute;
    for (int n = 0; ; n++)
    {
      if (n >= k)
      {
        str = null;
        break;
      }
      int i1 = arrayOfInt[n];
      if ((m > i1) || (m <= i1 - 7))
        continue;
      str = "שבת " + arrayOfString[n];
      break;
    }
  }

  public static void resetDisplaySettings()
  {
    int i = getHolidayCount();
    HolidaySettings = new byte[i];
    HolidayCandleLight = new byte[i];
    resetHolidaySettings();
    CustomEventNames = new Vector();
    CustomEvents = new Vector();
    CustomEventSettings = new byte[''];
    CustomEventCandleLight = new byte[''];
    gregorianStart = new DateHolder();
    gregorianStart.alwaysJulian = true;
    gregorianStart.acceptSecularDate(gregorianStartDefault);
  }

  public static void resetHolidaySettings()
  {
    int i = getHolidayCount();
    System.arraycopy(DefaultFlags, 0, HolidaySettings, 0, i);
    System.arraycopy(DefaultCandleLight, 0, HolidayCandleLight, 0, i);
  }

  public static void setCustomEventReminderToDefault()
  {
    int i = CustomEventSettings.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return;
      if ((0x4 & CustomEventSettings[j]) == 0)
        continue;
      byte[] arrayOfByte = CustomEventSettings;
      arrayOfByte[j] = (byte)(0x40 | arrayOfByte[j]);
    }
  }

  public static void setHolidayReminderToDefault()
  {
    int i = Math.min(HolidaySettings.length, DefaultFlags.length);
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return;
      if ((0x40 & DefaultFlags[j]) == 0)
        continue;
      byte[] arrayOfByte = HolidaySettings;
      arrayOfByte[j] = (byte)(0x40 | arrayOfByte[j]);
    }
  }

  public String getEventName(int paramInt)
  {
    return getEventName(this.eventData[0], this.eventDate, paramInt);
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     libsrc.EventData
 * JD-Core Version:    0.6.0
 */