package libsrc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.lang.reflect.Array;
import java.util.TimeZone;

public class LocationHolder
{
  public static final int DST_FIRST_AFTER = 6;
  public static final int DST_FIRST_IN_MONTH = 0;
  public static final int DST_FOURTH_IN_MONTH = 3;
  private static final int DST_INCREASE = 3600;
  public static final int DST_INDEX_CAL = 4;
  public static final int DST_INDEX_DAY = 2;
  public static final int DST_INDEX_DOW = 1;
  public static final int DST_INDEX_MODE = 0;
  public static final int DST_INDEX_MONTH = 3;
  public static final int DST_LAST_BEFORE = 7;
  public static final int DST_LAST_IN_MONTH = 5;
  public static final int DST_MODE_COUNT = 4;
  public static final int DST_MODE_CUSTOM = 3;
  public static final int DST_MODE_OFF = 0;
  public static final int DST_MODE_ON = 1;
  public static final int DST_MODE_SYSTEM = 2;
  public static final int DST_NON_MONTHLY = 6;
  public static final int DST_PERIOD_SIZE = 5;
  public static final int DST_SECOND_IN_MONTH = 1;
  public static final int DST_SECOND_LAST_IN_MONTH = 4;
  public static final int DST_SPECIFIC = 8;
  public static final int DST_STARTEND_MODE_COUNT = 9;
  public static final int DST_THIRD_IN_MONTH = 2;
  private static final byte[][] DSTperiodDefault;
  public static final int HSTYLE_DIASPORA = 1;
  public static final int HSTYLE_ISRAEL = 0;
  private static final int LONGITUDE_PER_TIMEZONE_RATIO = 900;
  public static final int MAX_LATITUDE_DEGREES = 90;
  public static final int MAX_LONGITUDE_DEGREES = 180;
  public static final int MAX_MINUTES = 59;
  public static final int MAX_SECONDS = 59;
  public static final int MILLISEC_PER_MINUTE = 60000;
  private static final int MINUTES_PER_DAY = 1440;
  public static final int MINUTES_PER_HOUR = 60;
  public static final int NAME_LENGTH_MAX = 25;
  private static final int SECONDS_PER_DAY = 86400;
  private static final int SECONDS_PER_DEGREE = 3600;
  private static final int SECONDS_PER_HOUR = 3600;
  private static final int SECONDS_PER_MINUTE = 60;
  private static final int TIME_ZONE_TOLERANCE_MAX = 120;
  private static final int TIME_ZONE_TOLERANCE_MIN = -60;
  private static final int latitudeJerusalem = coordSecs(31, 47, 0);
  public byte[][] DSTperiod;
  private boolean dstCurrent;
  private boolean dstCurrentChanged;
  public int dstMode;
  public byte holidayStyle;
  private int lastSunRiseSetDate;
  private int latitude;
  private int longitude;
  public String name;
  private int[] sunRiseSetTimes = null;
  public boolean timeScale12;
  private int tmeZone = 0;

  static
  {
    byte[][] arrayOfByte = new byte[2][];
    byte[] arrayOfByte1 = new byte[5];
    arrayOfByte1[0] = 8;
    arrayOfByte1[2] = 1;
    arrayOfByte1[3] = 3;
    arrayOfByte1[4] = 1;
    arrayOfByte[0] = arrayOfByte1;
    byte[] arrayOfByte2 = new byte[5];
    arrayOfByte2[2] = 1;
    arrayOfByte2[3] = 6;
    arrayOfByte[1] = arrayOfByte2;
    DSTperiodDefault = arrayOfByte;
  }

  public LocationHolder()
  {
    this.holidayStyle = 0;
    try
    {
      this.tmeZone = (TimeZone.getDefault().getRawOffset() / 60000);
      this.name = "Default";
      this.dstMode = 2;
      this.timeScale12 = false;
      this.latitude = latitudeJerusalem;
      this.longitude = (900 * this.tmeZone);
      int[] arrayOfInt = new int[2];
      arrayOfInt[0] = 2;
      arrayOfInt[1] = 5;
      this.DSTperiod = ((byte[][])Array.newInstance(Byte.TYPE, arrayOfInt));
      acceptDSTPeriod(DSTperiodDefault);
      this.sunRiseSetTimes = new int[3];
      this.dstCurrent = false;
      this.dstCurrentChanged = true;
      this.lastSunRiseSetDate = -1;
      return;
    }
    catch (Exception localException)
    {
      while (true)
        this.tmeZone = 0;
    }
  }

  public LocationHolder(LocationHolder paramLocationHolder)
  {
    int[] arrayOfInt = new int[2];
    arrayOfInt[0] = 2;
    arrayOfInt[1] = 5;
    this.DSTperiod = ((byte[][])Array.newInstance(Byte.TYPE, arrayOfInt));
    this.sunRiseSetTimes = new int[3];
    transferData(paramLocationHolder);
    this.lastSunRiseSetDate = -1;
    this.dstCurrentChanged = true;
  }

  private int adjustDSTDate(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (paramInt3 != 0)
    {
      int i = paramInt2 - HebCalendar.getDayOfWeek(paramInt1);
      int j = paramInt1 + i;
      if (i * paramInt3 < 0)
        paramInt4++;
      paramInt1 = j + paramInt4 * (paramInt3 * 7);
    }
    return paramInt1;
  }

  public static boolean checkLatitude(int[] paramArrayOfInt)
  {
    int i = 1;
    int j = paramArrayOfInt[0];
    int k = paramArrayOfInt[i];
    int m = paramArrayOfInt[2];
    if ((k >= 0) && (k <= 59) && (m >= 0) && (m <= 59) && (j >= 0) && ((j < 90) || (m + (j + k) == 90)));
    while (true)
    {
      return i;
      i = 0;
    }
  }

  public static boolean checkLongitude(int[] paramArrayOfInt)
  {
    int i = 1;
    int j = paramArrayOfInt[0];
    int k = paramArrayOfInt[i];
    int m = paramArrayOfInt[2];
    if ((k >= 0) && (k <= 59) && (m >= 0) && (m <= 59) && (j >= 0) && ((j < 180) || (m + (j + k) == 180)));
    while (true)
    {
      return i;
      i = 0;
    }
  }

  public static boolean checkTimeZoneLongitudeConsistency(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    int i = packTimeZone(paramArrayOfInt2) - getIntrinsicTimeZonePacked(paramArrayOfInt1);
    if ((i <= 120) && (i >= -60));
    for (int j = 1; ; j = 0)
      return j;
  }

  private static void coordDMS(int paramInt, int[] paramArrayOfInt)
  {
    paramArrayOfInt[0] = (paramInt / 3600);
    int i = paramInt % 3600;
    paramArrayOfInt[1] = (i / 60);
    paramArrayOfInt[2] = (i % 60);
  }

  private static int coordSecs(int paramInt1, int paramInt2, int paramInt3)
  {
    return paramInt3 + (paramInt1 * 3600 + paramInt2 * 60);
  }

  private boolean getDSTModeFromMyself(DateHolder paramDateHolder)
  {
    DateHolder localDateHolder = new DateHolder();
    DateData localDateData = new DateData();
    int i = 0;
    int j = 0;
    int k = 0;
    int i8;
    int i7;
    if (k >= 2)
    {
      i8 = paramDateHolder.dateAbsolute;
      if (i > j)
        break label321;
      if ((i8 < i) || (i8 >= j))
        break label315;
      i7 = 1;
    }
    while (true)
    {
      return i7;
      byte[] arrayOfByte = this.DSTperiod[k];
      int m = arrayOfByte[2];
      int n = arrayOfByte[1];
      int i1 = arrayOfByte[3];
      int i2 = arrayOfByte[4];
      int i3;
      label111: int i4;
      int i5;
      int i6;
      if (i2 == 0)
      {
        i3 = paramDateHolder.dateHebrew.year;
        i4 = 0;
        i5 = arrayOfByte[0];
        switch (i5)
        {
        default:
          i6 = 0;
          label171: localDateData.set(m, i1, i3);
          if (i2 != 0)
            break;
          if (localDateHolder.acceptHebrewDate(localDateData, true))
            break label284;
        case 0:
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
        case 7:
        }
      }
      do
      {
        i7 = 0;
        break;
        i3 = paramDateHolder.dateSecular.year;
        break label111;
        i6 = 1;
        m = 1;
        i4 = i5 + 0;
        break label171;
        i6 = -1;
        if (i2 == 0);
        for (m = HebCalendar.monthMaxDuration(i1); ; m = SecularCalendar.monthMaxDuration(i1))
        {
          i4 = 5 - i5;
          break;
        }
        i6 = 1;
        break label171;
        i6 = -1;
        break label171;
      }
      while (!localDateHolder.acceptSecularDate(localDateData));
      label284: j = adjustDSTDate(localDateHolder.dateAbsolute, n, i6, i4);
      if (k == 0)
        i = j;
      k++;
      break;
      label315: i7 = 0;
      continue;
      label321: if ((i8 < i) && (i8 >= j))
      {
        i7 = 0;
        continue;
      }
      i7 = 1;
    }
  }

  public static void getIntrinsicTimeZone(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    unpackTimeZone(getIntrinsicTimeZonePacked(paramArrayOfInt1), paramArrayOfInt2);
  }

  private static int getIntrinsicTimeZonePacked(int paramInt)
  {
    int i = 1;
    if (paramInt < 0)
    {
      paramInt = -paramInt;
      i = -1;
    }
    int j = 30 + (paramInt + 450) / 900;
    return i * (j - j % 60);
  }

  private static int getIntrinsicTimeZonePacked(int[] paramArrayOfInt)
  {
    return getIntrinsicTimeZonePacked(packLongitude(paramArrayOfInt));
  }

  public static boolean isDSTMonthly(byte[] paramArrayOfByte)
  {
    int i = 0;
    if (paramArrayOfByte[0] < 6)
      i = 1;
    return i;
  }

  private static int packLatitude(int[] paramArrayOfInt)
  {
    int i = coordSecs(paramArrayOfInt[0], paramArrayOfInt[1], paramArrayOfInt[2]);
    if (paramArrayOfInt[3] > 0)
      i = -i;
    return i;
  }

  private static int packLongitude(int[] paramArrayOfInt)
  {
    int i = coordSecs(paramArrayOfInt[0], paramArrayOfInt[1], paramArrayOfInt[2]);
    if ((paramArrayOfInt[3] > 0) && (paramArrayOfInt[0] < 180))
      i = -i;
    return i;
  }

  private static int packTimeZone(int[] paramArrayOfInt)
  {
    int i = 60 * paramArrayOfInt[0] + paramArrayOfInt[1];
    if (paramArrayOfInt[2] > 0)
      i = -i;
    return i;
  }

  private void processSunDate(DateHolder paramDateHolder)
  {
    int i = paramDateHolder.dateAbsolute;
    if (i != this.lastSunRiseSetDate)
    {
      SunRiseSet.calcSunRiseSetUTC(i, this.latitude, this.longitude, this.sunRiseSetTimes);
      this.lastSunRiseSetDate = i;
      if (this.dstMode == 3)
        this.dstCurrentChanged = true;
    }
    this.dstCurrent = getCurrentDST(paramDateHolder);
  }

  private int timeUTCtoLocal(int paramInt)
  {
    int j;
    if (paramInt == -2147483648)
      j = paramInt;
    while (true)
    {
      return j;
      int i = paramInt + 60 * this.tmeZone;
      if (this.dstCurrent)
        i += 3600;
      if (i < 0)
      {
        j = 86400 - -i % 86400;
        continue;
      }
      j = i % 86400;
    }
  }

  private void transferData(LocationHolder paramLocationHolder)
  {
    this.name = new String(paramLocationHolder.name);
    acceptDSTPeriod(paramLocationHolder.DSTperiod);
    this.holidayStyle = paramLocationHolder.holidayStyle;
    this.timeScale12 = paramLocationHolder.timeScale12;
    this.latitude = paramLocationHolder.latitude;
    this.longitude = paramLocationHolder.longitude;
    this.tmeZone = paramLocationHolder.tmeZone;
    this.dstMode = paramLocationHolder.dstMode;
    this.dstCurrent = paramLocationHolder.dstCurrent;
  }

  public static void unpackCoordinate(int paramInt, int[] paramArrayOfInt)
  {
    if (paramInt < 0)
    {
      paramArrayOfInt[3] = 1;
      paramInt = -paramInt;
    }
    while (true)
    {
      coordDMS(paramInt, paramArrayOfInt);
      return;
      paramArrayOfInt[3] = 0;
    }
  }

  private static void unpackTimeZone(int paramInt, int[] paramArrayOfInt)
  {
    if (paramInt < 0)
    {
      paramInt = -paramInt;
      paramArrayOfInt[2] = 1;
    }
    while (true)
    {
      paramArrayOfInt[0] = (paramInt / 60);
      paramArrayOfInt[1] = (paramInt % 60);
      return;
      paramArrayOfInt[2] = 0;
    }
  }

  public void acceptDSTPeriod(byte[][] paramArrayOfByte)
  {
    for (int i = 0; ; i++)
    {
      if (i >= 2)
      {
        if (this.dstMode == 3)
          this.dstCurrentChanged = true;
        return;
      }
      System.arraycopy(paramArrayOfByte[i], 0, this.DSTperiod[i], 0, 5);
    }
  }

  public void acceptInternalCoordinates(int paramInt1, int paramInt2)
  {
    this.latitude = paramInt1;
    this.longitude = paramInt2;
    invalidateSunTimes();
  }

  public boolean acceptLatitude(int[] paramArrayOfInt)
  {
    if (checkLatitude(paramArrayOfInt))
    {
      this.latitude = packLatitude(paramArrayOfInt);
      invalidateSunTimes();
    }
    for (int i = 1; ; i = 0)
      return i;
  }

  public boolean acceptLongitude(int[] paramArrayOfInt)
  {
    if (checkLongitude(paramArrayOfInt))
    {
      this.longitude = packLongitude(paramArrayOfInt);
      invalidateSunTimes();
    }
    for (int i = 1; ; i = 0)
      return i;
  }

  public void acceptTimeZone(int paramInt)
  {
    this.tmeZone = paramInt;
  }

  public void acceptTimeZone(int[] paramArrayOfInt)
  {
    this.tmeZone = packTimeZone(paramArrayOfInt);
  }

  public boolean checkTimeZoneLongitudeConsistency()
  {
    int i = this.tmeZone - getIntrinsicTimeZonePacked(this.longitude);
    if ((i <= 120) && (i >= -60));
    for (int j = 1; ; j = 0)
      return j;
  }

  public boolean getCurrentDST(DateHolder paramDateHolder)
  {
    if (this.dstCurrentChanged)
    {
      this.dstCurrent = false;
      switch (this.dstMode)
      {
      default:
      case 1:
      case 3:
      case 2:
      }
    }
    while (true)
    {
      this.dstCurrentChanged = false;
      return this.dstCurrent;
      this.dstCurrent = true;
      continue;
      this.dstCurrent = getDSTModeFromMyself(paramDateHolder);
      continue;
      this.dstCurrent = getDSTModeFromDevice(paramDateHolder);
    }
  }

  boolean getDSTModeFromDevice(DateHolder paramDateHolder)
  {
    int i = paramDateHolder.dateSecular.day;
    int j = paramDateHolder.dateSecular.month;
    int k = paramDateHolder.dateSecular.year;
    int m = HebCalendar.getDayOfWeek(paramDateHolder.dateAbsolute);
    int n = 1;
    if (k < 0)
    {
      k = -k;
      n = 0;
    }
    try
    {
      TimeZone localTimeZone = TimeZone.getDefault();
      int i2 = localTimeZone.getOffset(n, k, j, i, m, 43200000);
      int i3 = localTimeZone.getRawOffset();
      if (i2 - i3 >= 3600000);
      for (i1 = 1; ; i1 = 0)
        return i1;
    }
    catch (Exception localException)
    {
      while (true)
        int i1 = 0;
    }
  }

  public byte[][] getDSTPeriod()
  {
    int[] arrayOfInt = new int[2];
    arrayOfInt[0] = 2;
    arrayOfInt[1] = 5;
    byte[][] arrayOfByte = (byte[][])Array.newInstance(Byte.TYPE, arrayOfInt);
    for (int i = 0; ; i++)
    {
      if (i >= 2)
        return arrayOfByte;
      System.arraycopy(this.DSTperiod[i], 0, arrayOfByte[i], 0, 5);
    }
  }

  public int[] getLatitude()
  {
    int[] arrayOfInt = new int[4];
    unpackCoordinate(this.latitude, arrayOfInt);
    return arrayOfInt;
  }

  public int[] getLongitude()
  {
    int[] arrayOfInt = new int[4];
    unpackCoordinate(this.longitude, arrayOfInt);
    return arrayOfInt;
  }

  public int getSunRiseTime(DateHolder paramDateHolder)
  {
    processSunDate(paramDateHolder);
    return timeUTCtoLocal(this.sunRiseSetTimes[0]);
  }

  public int getSunSetTime(DateHolder paramDateHolder)
  {
    processSunDate(paramDateHolder);
    return timeUTCtoLocal(this.sunRiseSetTimes[1]);
  }

  public int getTimeForDayPart(DateHolder paramDateHolder, int paramInt1, int paramInt2)
  {
    processSunDate(paramDateHolder);
    int i = this.sunRiseSetTimes[0];
    if ((i != -2147483648) && (paramInt2 != 0))
      i += paramInt1 * (this.sunRiseSetTimes[1] - i) / paramInt2;
    return timeUTCtoLocal(i);
  }

  public int getTimeForElevation(DateHolder paramDateHolder, int paramInt)
  {
    processSunDate(paramDateHolder);
    return timeUTCtoLocal(SunRiseSet.recalcZenithAngleUTC(paramInt));
  }

  public int[] getTimeZone()
  {
    int[] arrayOfInt = new int[3];
    unpackTimeZone(this.tmeZone, arrayOfInt);
    return arrayOfInt;
  }

  public int getTimeZonePacked()
  {
    return this.tmeZone;
  }

  public void invalidateSunTimes()
  {
    this.lastSunRiseSetDate = -1;
  }

  public boolean isChanged(LocationHolder paramLocationHolder)
  {
    if ((this.holidayStyle == paramLocationHolder.holidayStyle) && (this.timeScale12 == paramLocationHolder.timeScale12) && (this.dstMode == paramLocationHolder.dstMode) && (this.latitude == paramLocationHolder.latitude) && (this.longitude == paramLocationHolder.longitude) && (this.tmeZone == paramLocationHolder.tmeZone) && (this.name.compareTo(paramLocationHolder.name) == 0) && ((this.dstMode != 3) || (!isChangedDSTPeriod(paramLocationHolder.DSTperiod))));
    for (int i = 0; ; i = 1)
      return i;
  }

  public boolean isChangedDSTPeriod(byte[][] paramArrayOfByte)
  {
    int i = 0;
    int k;
    if (i >= 2)
    {
      k = 0;
      label10: return k;
    }
    for (int j = 0; ; j++)
    {
      if (j >= 5)
      {
        i++;
        break;
      }
      if (this.DSTperiod[i][j] == paramArrayOfByte[i][j])
        continue;
      k = 1;
      break label10;
    }
  }

  public boolean isSouth()
  {
    if (this.latitude < 0);
    for (int i = 1; ; i = 0)
      return i;
  }

  public boolean isWest()
  {
    if (this.longitude < 0);
    for (int i = 1; ; i = 0)
      return i;
  }

  public void restore(DataInputStream paramDataInputStream)
    throws Exception
  {
    this.name = paramDataInputStream.readUTF();
    this.holidayStyle = (0x1 & paramDataInputStream.readByte());
    this.timeScale12 = paramDataInputStream.readBoolean();
    this.latitude = paramDataInputStream.readInt();
    this.longitude = paramDataInputStream.readInt();
    this.tmeZone = paramDataInputStream.readShort();
    this.dstMode = (paramDataInputStream.readByte() % 4);
    int i = 0;
    if (i >= 2)
    {
      this.dstCurrentChanged = true;
      this.lastSunRiseSetDate = -1;
      return;
    }
    for (int j = 0; ; j++)
    {
      if (j >= 5)
      {
        i++;
        break;
      }
      this.DSTperiod[i][j] = paramDataInputStream.readByte();
    }
  }

  public void save(DataOutputStream paramDataOutputStream)
    throws Exception
  {
    paramDataOutputStream.writeUTF(this.name);
    paramDataOutputStream.writeByte(this.holidayStyle);
    paramDataOutputStream.writeBoolean(this.timeScale12);
    paramDataOutputStream.writeInt(this.latitude);
    paramDataOutputStream.writeInt(this.longitude);
    paramDataOutputStream.writeShort((short)this.tmeZone);
    paramDataOutputStream.writeByte((byte)this.dstMode);
    int i = 0;
    if (i >= 2);
    for (int k = 0; ; k++)
    {
      if (k >= 6)
      {
        return;
        for (int j = 0; ; j++)
        {
          if (j >= 5)
          {
            i++;
            break;
          }
          paramDataOutputStream.writeByte(this.DSTperiod[i][j]);
        }
      }
      paramDataOutputStream.writeByte(0);
    }
  }

  public void set(LocationHolder paramLocationHolder)
  {
    if ((this.latitude != paramLocationHolder.latitude) || (this.longitude != paramLocationHolder.longitude))
      invalidateSunTimes();
    if (paramLocationHolder.dstMode != this.dstMode)
      this.dstCurrentChanged = true;
    transferData(paramLocationHolder);
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     libsrc.LocationHolder
 * JD-Core Version:    0.6.0
 */