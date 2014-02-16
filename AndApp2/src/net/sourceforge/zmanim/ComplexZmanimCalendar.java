package net.sourceforge.zmanim;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import net.sourceforge.zmanim.util.GeoLocation;

public class ComplexZmanimCalendar extends ZmanimCalendar
{
  protected static final double ZENITH_10_POINT_2 = 100.2D;
  protected static final double ZENITH_11_DEGREES = 101.0D;
  protected static final double ZENITH_11_POINT_5 = 101.5D;
  protected static final double ZENITH_13_DEGREES = 103.0D;
  protected static final double ZENITH_19_POINT_8 = 109.8D;
  protected static final double ZENITH_26_DEGREES = 116.0D;
  protected static final double ZENITH_3_POINT_65 = 93.650000000000006D;
  protected static final double ZENITH_3_POINT_7 = 93.700000000000003D;
  protected static final double ZENITH_4_POINT_37 = 94.370000000000005D;
  protected static final double ZENITH_4_POINT_61 = 94.609999999999999D;
  protected static final double ZENITH_4_POINT_8 = 94.799999999999997D;
  protected static final double ZENITH_5_POINT_88 = 95.879999999999995D;
  protected static final double ZENITH_5_POINT_95 = 95.950000000000003D;
  protected static final double ZENITH_7_POINT_083 = 97.0D;
  private static final long serialVersionUID = 1L;
  private double ateretTorahSunsetOffset = 40.0D;

  public ComplexZmanimCalendar()
  {
  }

  public ComplexZmanimCalendar(GeoLocation paramGeoLocation)
  {
    super(paramGeoLocation);
  }

  public boolean equals(Object paramObject)
  {
    int i = 1;
    if (this == paramObject);
    while (true)
    {
      return i;
      if (!(paramObject instanceof ComplexZmanimCalendar))
      {
        i = 0;
        continue;
      }
      ComplexZmanimCalendar localComplexZmanimCalendar = (ComplexZmanimCalendar)paramObject;
      if ((getCalendar().equals(localComplexZmanimCalendar.getCalendar())) && (getGeoLocation().equals(localComplexZmanimCalendar.getGeoLocation())) && (getAstronomicalCalculator().equals(localComplexZmanimCalendar.getAstronomicalCalculator())))
        continue;
      i = 0;
    }
  }

  public Date getAlos120()
  {
    return getTimeOffset(getSeaLevelSunrise(), -7200000L);
  }

  public Date getAlos120Zmanis()
  {
    long l = getShaahZmanisGra();
    if (l == -9223372036854775808L);
    for (Date localDate = null; ; localDate = getTimeOffset(getSeaLevelSunrise(), -2L * l))
      return localDate;
  }

  public Date getAlos16Point1Degrees()
  {
    return getSunriseOffsetByDegrees(106.09999999999999D);
  }

  public Date getAlos18Degrees()
  {
    return getSunriseOffsetByDegrees(108.0D);
  }

  public Date getAlos19Point8Degrees()
  {
    return getSunriseOffsetByDegrees(109.8D);
  }

  public Date getAlos26Degrees()
  {
    return getSunriseOffsetByDegrees(116.0D);
  }

  public Date getAlos60()
  {
    return getTimeOffset(getSeaLevelSunrise(), -3600000L);
  }

  public Date getAlos72Zmanis()
  {
    long l = getShaahZmanisGra();
    if (l == -9223372036854775808L);
    for (Date localDate = null; ; localDate = getTimeOffset(getSeaLevelSunrise(), ()(-1.2D * l)))
      return localDate;
  }

  public Date getAlos90()
  {
    return getTimeOffset(getSeaLevelSunrise(), -5400000L);
  }

  public Date getAlos90Zmanis()
  {
    long l = getShaahZmanisGra();
    if (l == -9223372036854775808L);
    for (Date localDate = null; ; localDate = getTimeOffset(getSeaLevelSunrise(), ()(-1.5D * l)))
      return localDate;
  }

  public Date getAlos96()
  {
    return getTimeOffset(getSeaLevelSunrise(), -5760000L);
  }

  public Date getAlos96Zmanis()
  {
    long l = getShaahZmanisGra();
    if (l == -9223372036854775808L);
    for (Date localDate = null; ; localDate = getTimeOffset(getSeaLevelSunrise(), ()(-1.6D * l)))
      return localDate;
  }

  public double getAteretTorahSunsetOffset()
  {
    return this.ateretTorahSunsetOffset;
  }

  public Date getBainHasmashosRT13Degrees()
  {
    return getSunsetOffsetByDegrees(103.0D);
  }

  public Date getBainHasmashosRT13Point5MinutesBefore7Point083Degrees()
  {
    return getTimeOffset(getSunsetOffsetByDegrees(97.0D), -810000.0D);
  }

  public Date getBainHasmashosRT2Stars()
  {
    Date localDate1 = getAlos19Point8Degrees();
    Date localDate2 = getSeaLevelSunrise();
    if ((localDate1 == null) || (localDate2 == null));
    for (Date localDate3 = null; ; localDate3 = getTimeOffset(getSeaLevelSunset(), 0.2777777777777778D * (localDate2.getTime() - localDate1.getTime())))
      return localDate3;
  }

  public Date getBainHasmashosRT58Point5Minutes()
  {
    return getTimeOffset(getSeaLevelSunset(), 3510000.0D);
  }

  public Date getFixedLocalChatzos()
  {
    return getTimeOffset(getDateFromTime(12.0D - getGeoLocation().getTimeZone().getRawOffset() / 3600000L), -getGeoLocation().getLocalMeanTimeOffset());
  }

  public Date getMinchaGedola16Point1Degrees()
  {
    return getTimeOffset(getAlos16Point1Degrees(), 6.5D * getShaahZmanis16Point1Degrees());
  }

  public Date getMinchaGedola30Minutes()
  {
    return getTimeOffset(getChatzos(), 1800000L);
  }

  public Date getMinchaGedola72Minutes()
  {
    return getTimeOffset(getAlos72(), 6.5D * getShaahZmanis72Minutes());
  }

  public Date getMinchaGedolaAteretTorah()
  {
    return getTimeOffset(getAlos72Zmanis(), 6.5D * getShaahZmanisAteretTorah());
  }

  public Date getMinchaGedolaGreaterThan30()
  {
    Date localDate;
    if ((getMinchaGedola30Minutes() == null) || (getMinchaGedola() == null))
      localDate = null;
    while (true)
    {
      return localDate;
      if (getMinchaGedola30Minutes().compareTo(getMinchaGedola()) > 0)
      {
        localDate = getMinchaGedola30Minutes();
        continue;
      }
      localDate = getMinchaGedola();
    }
  }

  public Date getMinchaKetana16Point1Degrees()
  {
    return getTimeOffset(getAlos16Point1Degrees(), 9.5D * getShaahZmanis16Point1Degrees());
  }

  public Date getMinchaKetana72Minutes()
  {
    return getTimeOffset(getAlos72(), 9.5D * getShaahZmanis72Minutes());
  }

  public Date getMinchaKetanaAteretTorah()
  {
    return getTimeOffset(getAlos72Zmanis(), 9.5D * getShaahZmanisAteretTorah());
  }

  public Date getMisheyakir10Point2Degrees()
  {
    return getSunriseOffsetByDegrees(100.2D);
  }

  public Date getMisheyakir11Degrees()
  {
    return getSunriseOffsetByDegrees(101.0D);
  }

  public Date getMisheyakir11Point5Degrees()
  {
    return getSunriseOffsetByDegrees(101.5D);
  }

  public Date getPlagAlos16Point1ToTzaisGeonim7Point083Degrees()
  {
    long l = getTemporalHour(getAlos16Point1Degrees(), getTzaisGeonim7Point083Degrees());
    return getTimeOffset(getAlos16Point1Degrees(), 10.75D * l);
  }

  public Date getPlagAlosToSunset()
  {
    long l = getTemporalHour(getAlos16Point1Degrees(), getSeaLevelSunset());
    return getTimeOffset(getAlos16Point1Degrees(), 10.75D * l);
  }

  public Date getPlagHamincha120Minutes()
  {
    return getTimeOffset(getAlos120(), 10.75D * getShaahZmanis120Minutes());
  }

  public Date getPlagHamincha120MinutesZmanis()
  {
    return getTimeOffset(getAlos120Zmanis(), 10.75D * getShaahZmanis120MinutesZmanis());
  }

  public Date getPlagHamincha16Point1Degrees()
  {
    return getTimeOffset(getAlos16Point1Degrees(), 10.75D * getShaahZmanis16Point1Degrees());
  }

  public Date getPlagHamincha18Degrees()
  {
    return getTimeOffset(getAlos18Degrees(), 10.75D * getShaahZmanis18Degrees());
  }

  public Date getPlagHamincha19Point8Degrees()
  {
    return getTimeOffset(getAlos19Point8Degrees(), 10.75D * getShaahZmanis19Point8Degrees());
  }

  public Date getPlagHamincha26Degrees()
  {
    return getTimeOffset(getAlos26Degrees(), 10.75D * getShaahZmanis26Degrees());
  }

  public Date getPlagHamincha60Minutes()
  {
    return getTimeOffset(getAlos60(), 10.75D * getShaahZmanis60Minutes());
  }

  public Date getPlagHamincha72Minutes()
  {
    return getTimeOffset(getAlos72(), 10.75D * getShaahZmanis72Minutes());
  }

  public Date getPlagHamincha72MinutesZmanis()
  {
    return getTimeOffset(getAlos72Zmanis(), 10.75D * getShaahZmanis72MinutesZmanis());
  }

  public Date getPlagHamincha90Minutes()
  {
    return getTimeOffset(getAlos90(), 10.75D * getShaahZmanis90Minutes());
  }

  public Date getPlagHamincha90MinutesZmanis()
  {
    return getTimeOffset(getAlos90Zmanis(), 10.75D * getShaahZmanis90MinutesZmanis());
  }

  public Date getPlagHamincha96Minutes()
  {
    return getTimeOffset(getAlos96(), 10.75D * getShaahZmanis96Minutes());
  }

  public Date getPlagHamincha96MinutesZmanis()
  {
    return getTimeOffset(getAlos96Zmanis(), 10.75D * getShaahZmanis96MinutesZmanis());
  }

  public Date getPlagHaminchaAteretTorah()
  {
    return getTimeOffset(getAlos72Zmanis(), 10.75D * getShaahZmanisAteretTorah());
  }

  public long getShaahZmanis120Minutes()
  {
    return getTemporalHour(getAlos120(), getTzais120());
  }

  public long getShaahZmanis120MinutesZmanis()
  {
    return getTemporalHour(getAlos120Zmanis(), getTzais120Zmanis());
  }

  public long getShaahZmanis16Point1Degrees()
  {
    return getTemporalHour(getAlos16Point1Degrees(), getTzais16Point1Degrees());
  }

  public long getShaahZmanis18Degrees()
  {
    return getTemporalHour(getAlos18Degrees(), getTzais18Degrees());
  }

  public long getShaahZmanis19Point8Degrees()
  {
    return getTemporalHour(getAlos19Point8Degrees(), getTzais19Point8Degrees());
  }

  public long getShaahZmanis26Degrees()
  {
    return getTemporalHour(getAlos26Degrees(), getTzais26Degrees());
  }

  public long getShaahZmanis60Minutes()
  {
    return getTemporalHour(getAlos60(), getTzais60());
  }

  public long getShaahZmanis72Minutes()
  {
    return getShaahZmanisMGA();
  }

  public long getShaahZmanis72MinutesZmanis()
  {
    return getTemporalHour(getAlos72Zmanis(), getTzais72Zmanis());
  }

  public long getShaahZmanis90Minutes()
  {
    return getTemporalHour(getAlos90(), getTzais90());
  }

  public long getShaahZmanis90MinutesZmanis()
  {
    return getTemporalHour(getAlos90Zmanis(), getTzais90Zmanis());
  }

  public long getShaahZmanis96Minutes()
  {
    return getTemporalHour(getAlos96(), getTzais96());
  }

  public long getShaahZmanis96MinutesZmanis()
  {
    return getTemporalHour(getAlos96Zmanis(), getTzais96Zmanis());
  }

  public long getShaahZmanisAteretTorah()
  {
    return getTemporalHour(getAlos72Zmanis(), getTzaisAteretTorah());
  }

  public Date getSofZmanShma3HoursBeforeChatzos()
  {
    return getTimeOffset(getChatzos(), -10800000L);
  }

  public Date getSofZmanShmaAlos16Point1ToSunset()
  {
    long l = getTemporalHour(getAlos16Point1Degrees(), getSeaLevelSunset());
    return getTimeOffset(getAlos16Point1Degrees(), 3L * l);
  }

  public Date getSofZmanShmaAlos16Point1ToTzaisGeonim7Point083Degrees()
  {
    long l = getTemporalHour(getAlos16Point1Degrees(), getTzaisGeonim7Point083Degrees());
    return getTimeOffset(getAlos16Point1Degrees(), 3L * l);
  }

  public Date getSofZmanShmaAteretTorah()
  {
    return getTimeOffset(getAlos72Zmanis(), 3L * getShaahZmanisAteretTorah());
  }

  public Date getSofZmanShmaFixedLocal()
  {
    return getTimeOffset(getFixedLocalChatzos(), -10800000L);
  }

  public Date getSofZmanShmaKolEliyahu()
  {
    Date localDate1 = getFixedLocalChatzos();
    if ((localDate1 == null) || (getSunrise() == null));
    for (Date localDate2 = null; ; localDate2 = getTimeOffset(localDate1, -((localDate1.getTime() - getSeaLevelSunrise().getTime()) / 2L)))
      return localDate2;
  }

  public Date getSofZmanShmaMGA120Minutes()
  {
    return getTimeOffset(getAlos120(), 3L * getShaahZmanis120Minutes());
  }

  public Date getSofZmanShmaMGA16Point1Degrees()
  {
    return getTimeOffset(getAlos16Point1Degrees(), 3L * getShaahZmanis16Point1Degrees());
  }

  public Date getSofZmanShmaMGA19Point8Degrees()
  {
    return getTimeOffset(getAlos19Point8Degrees(), 3L * getShaahZmanis19Point8Degrees());
  }

  public Date getSofZmanShmaMGA72Minutes()
  {
    return getSofZmanShmaMGA();
  }

  public Date getSofZmanShmaMGA72MinutesZmanis()
  {
    return getTimeOffset(getAlos72Zmanis(), 3L * getShaahZmanis72MinutesZmanis());
  }

  public Date getSofZmanShmaMGA90Minutes()
  {
    return getTimeOffset(getAlos90(), 3L * getShaahZmanis90Minutes());
  }

  public Date getSofZmanShmaMGA90MinutesZmanis()
  {
    return getTimeOffset(getAlos90Zmanis(), 3L * getShaahZmanis90MinutesZmanis());
  }

  public Date getSofZmanShmaMGA96Minutes()
  {
    return getTimeOffset(getAlos96(), 3L * getShaahZmanis96Minutes());
  }

  public Date getSofZmanShmaMGA96MinutesZmanis()
  {
    return getTimeOffset(getAlos96Zmanis(), 3L * getShaahZmanis96MinutesZmanis());
  }

  public Date getSofZmanTfila2HoursBeforeChatzos()
  {
    return getTimeOffset(getChatzos(), -7200000L);
  }

  public Date getSofZmanTfilaFixedLocal()
  {
    return getTimeOffset(getFixedLocalChatzos(), -7200000L);
  }

  public Date getSofZmanTfilaMGA120Minutes()
  {
    return getTimeOffset(getAlos120(), 4L * getShaahZmanis120Minutes());
  }

  public Date getSofZmanTfilaMGA16Point1Degrees()
  {
    return getTimeOffset(getAlos16Point1Degrees(), 4L * getShaahZmanis16Point1Degrees());
  }

  public Date getSofZmanTfilaMGA19Point8Degrees()
  {
    return getTimeOffset(getAlos19Point8Degrees(), 4L * getShaahZmanis19Point8Degrees());
  }

  public Date getSofZmanTfilaMGA72Minutes()
  {
    return getSofZmanTfilaMGA();
  }

  public Date getSofZmanTfilaMGA72MinutesZmanis()
  {
    return getTimeOffset(getAlos72Zmanis(), 4L * getShaahZmanis72MinutesZmanis());
  }

  public Date getSofZmanTfilaMGA90Minutes()
  {
    return getTimeOffset(getAlos90(), 4L * getShaahZmanis90Minutes());
  }

  public Date getSofZmanTfilaMGA90MinutesZmanis()
  {
    return getTimeOffset(getAlos90Zmanis(), 4L * getShaahZmanis90MinutesZmanis());
  }

  public Date getSofZmanTfilaMGA96Minutes()
  {
    return getTimeOffset(getAlos96(), 4L * getShaahZmanis96Minutes());
  }

  public Date getSofZmanTfilaMGA96MinutesZmanis()
  {
    return getTimeOffset(getAlos96Zmanis(), 4L * getShaahZmanis96MinutesZmanis());
  }

  public Date getSofZmanTfilahAteretTorah()
  {
    return getTimeOffset(getAlos72Zmanis(), 4L * getShaahZmanisAteretTorah());
  }

  public Date getTzais120()
  {
    return getTimeOffset(getSeaLevelSunset(), 7200000L);
  }

  public Date getTzais120Zmanis()
  {
    long l = getShaahZmanisGra();
    if (l == -9223372036854775808L);
    for (Date localDate = null; ; localDate = getTimeOffset(getSeaLevelSunset(), 2.0D * l))
      return localDate;
  }

  public Date getTzais16Point1Degrees()
  {
    return getSunsetOffsetByDegrees(106.09999999999999D);
  }

  public Date getTzais18Degrees()
  {
    return getSunsetOffsetByDegrees(108.0D);
  }

  public Date getTzais19Point8Degrees()
  {
    return getSunsetOffsetByDegrees(109.8D);
  }

  public Date getTzais26Degrees()
  {
    return getSunsetOffsetByDegrees(116.0D);
  }

  public Date getTzais60()
  {
    return getTimeOffset(getSeaLevelSunset(), 3600000L);
  }

  public Date getTzais72Zmanis()
  {
    long l = getShaahZmanisGra();
    if (l == -9223372036854775808L);
    for (Date localDate = null; ; localDate = getTimeOffset(getSeaLevelSunset(), 1.2D * l))
      return localDate;
  }

  public Date getTzais90()
  {
    return getTimeOffset(getSeaLevelSunset(), 5400000L);
  }

  public Date getTzais90Zmanis()
  {
    long l = getShaahZmanisGra();
    if (l == -9223372036854775808L);
    for (Date localDate = null; ; localDate = getTimeOffset(getSeaLevelSunset(), 1.5D * l))
      return localDate;
  }

  public Date getTzais96()
  {
    return getTimeOffset(getSeaLevelSunset(), 5760000L);
  }

  public Date getTzais96Zmanis()
  {
    long l = getShaahZmanisGra();
    if (l == -9223372036854775808L);
    for (Date localDate = null; ; localDate = getTimeOffset(getSeaLevelSunset(), 1.6D * l))
      return localDate;
  }

  public Date getTzaisAteretTorah()
  {
    return getTimeOffset(getSeaLevelSunset(), 60000.0D * getAteretTorahSunsetOffset());
  }

  public Date getTzaisGeonim3Point65Degrees()
  {
    return getSunsetOffsetByDegrees(93.650000000000006D);
  }

  public Date getTzaisGeonim4Point37Degrees()
  {
    return getSunsetOffsetByDegrees(94.370000000000005D);
  }

  public Date getTzaisGeonim4Point61Degrees()
  {
    return getSunsetOffsetByDegrees(94.609999999999999D);
  }

  public Date getTzaisGeonim4Point8Degrees()
  {
    return getSunsetOffsetByDegrees(94.799999999999997D);
  }

  public Date getTzaisGeonim5Point88Degrees()
  {
    return getSunsetOffsetByDegrees(95.879999999999995D);
  }

  public Date getTzaisGeonim5Point95Degrees()
  {
    return getSunsetOffsetByDegrees(95.950000000000003D);
  }

  public Date getTzaisGeonim7Point083Degrees()
  {
    return getSunsetOffsetByDegrees(97.0D);
  }

  public Date getTzaisGeonim8Point5Degrees()
  {
    return getSunsetOffsetByDegrees(98.5D);
  }

  public int hashCode()
  {
    int i = 629 + getClass().hashCode();
    int j = i + (i * 37 + getCalendar().hashCode());
    int k = j + (j * 37 + getGeoLocation().hashCode());
    return k + (k * 37 + getAstronomicalCalculator().hashCode());
  }

  public void setAteretTorahSunsetOffset(double paramDouble)
  {
    this.ateretTorahSunsetOffset = paramDouble;
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     net.sourceforge.zmanim.ComplexZmanimCalendar
 * JD-Core Version:    0.6.0
 */