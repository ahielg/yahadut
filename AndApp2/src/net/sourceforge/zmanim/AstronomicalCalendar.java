package net.sourceforge.zmanim;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import net.sourceforge.zmanim.util.AstronomicalCalculator;
import net.sourceforge.zmanim.util.GeoLocation;
import net.sourceforge.zmanim.util.ZmanimFormatter;

public class AstronomicalCalendar
  implements Cloneable
{
  public static final double ASTRONOMICAL_ZENITH = 108.0D;
  public static final double CIVIL_ZENITH = 96.0D;
  public static final double GEOMETRIC_ZENITH = 90.0D;
  static final long HOUR_MILLIS = 3600000L;
  static final long MINUTE_MILLIS = 60000L;
  public static final double NAUTICAL_ZENITH = 102.0D;
  private static final long serialVersionUID = 1L;
  private AstronomicalCalculator astronomicalCalculator;
  private Calendar calendar;
  private GeoLocation geoLocation;

  public AstronomicalCalendar()
  {
    this(new GeoLocation());
  }

  public AstronomicalCalendar(GeoLocation paramGeoLocation)
  {
    setCalendar(Calendar.getInstance(paramGeoLocation.getTimeZone()));
    setGeoLocation(paramGeoLocation);
    setAstronomicalCalculator(AstronomicalCalculator.getDefault());
  }

  private Date getAdjustedSunsetDate(Date paramDate1, Date paramDate2)
  {
    if ((paramDate1 != null) && (paramDate2 != null) && (paramDate2.compareTo(paramDate1) >= 0))
    {
      Calendar localCalendar = (Calendar)getCalendar().clone();
      localCalendar.setTime(paramDate1);
      localCalendar.add(5, 1);
      paramDate1 = localCalendar.getTime();
    }
    return paramDate1;
  }

  private double getOffsetTime(double paramDouble)
  {
    boolean bool = getCalendar().getTimeZone().inDaylightTime(getCalendar().getTime());
    double d1 = 0.0D;
    double d2 = getCalendar().getTimeZone().getRawOffset() / 3600000L;
    if (bool)
      d1 = getCalendar().getTimeZone().getDSTSavings() / 3600000L;
    return d1 + (paramDouble + d2);
  }

  public Object clone()
  {
    AstronomicalCalendar localAstronomicalCalendar = null;
    try
    {
      localAstronomicalCalendar = (AstronomicalCalendar)super.clone();
      localAstronomicalCalendar.setGeoLocation((GeoLocation)getGeoLocation().clone());
      localAstronomicalCalendar.setCalendar((Calendar)getCalendar().clone());
      localAstronomicalCalendar.setAstronomicalCalculator((AstronomicalCalculator)getAstronomicalCalculator().clone());
      return localAstronomicalCalendar;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      while (true)
        System.out.print("Required by the compiler. Should never be reached since we implement clone()");
    }
  }

  public boolean equals(Object paramObject)
  {
    int i = 1;
    if (this == paramObject);
    while (true)
    {
      return i;
      if (!(paramObject instanceof AstronomicalCalendar))
      {
        i = 0;
        continue;
      }
      AstronomicalCalendar localAstronomicalCalendar = (AstronomicalCalendar)paramObject;
      if ((getCalendar().equals(localAstronomicalCalendar.getCalendar())) && (getGeoLocation().equals(localAstronomicalCalendar.getGeoLocation())) && (getAstronomicalCalculator().equals(localAstronomicalCalendar.getAstronomicalCalculator())))
        continue;
      i = 0;
    }
  }

  public AstronomicalCalculator getAstronomicalCalculator()
  {
    return this.astronomicalCalculator;
  }

  public Date getBeginAstronomicalTwilight()
  {
    return getSunriseOffsetByDegrees(108.0D);
  }

  public Date getBeginCivilTwilight()
  {
    return getSunriseOffsetByDegrees(96.0D);
  }

  public Date getBeginNauticalTwilight()
  {
    return getSunriseOffsetByDegrees(102.0D);
  }

  public Calendar getCalendar()
  {
    return this.calendar;
  }

  protected Date getDateFromTime(double paramDouble)
  {
    if (Double.isNaN(paramDouble));
    Calendar localCalendar;
    for (Date localDate = null; ; localDate = localCalendar.getTime())
    {
      return localDate;
      double d1 = (240.0D + getOffsetTime(paramDouble)) % 24.0D;
      localCalendar = Calendar.getInstance();
      localCalendar.clear();
      localCalendar.set(1, getCalendar().get(1));
      localCalendar.set(2, getCalendar().get(2));
      localCalendar.set(5, getCalendar().get(5));
      int i = (int)d1;
      double d2 = 60.0D * (d1 - i);
      int j = (int)d2;
      double d3 = 60.0D * (d2 - j);
      int k = (int)d3;
      double d4 = d3 - k;
      localCalendar.set(11, i);
      localCalendar.set(12, j);
      localCalendar.set(13, k);
      localCalendar.set(14, (int)(1000.0D * d4));
    }
  }

  public Date getEndAstronomicalTwilight()
  {
    return getSunsetOffsetByDegrees(108.0D);
  }

  public Date getEndCivilTwilight()
  {
    return getSunsetOffsetByDegrees(96.0D);
  }

  public Date getEndNauticalTwilight()
  {
    return getSunsetOffsetByDegrees(102.0D);
  }

  public GeoLocation getGeoLocation()
  {
    return this.geoLocation;
  }

  public Date getSeaLevelSunrise()
  {
    double d = getUTCSeaLevelSunrise(90.0D);
    if (Double.isNaN(d));
    for (Date localDate = null; ; localDate = getDateFromTime(d))
      return localDate;
  }

  public Date getSeaLevelSunset()
  {
    double d = getUTCSeaLevelSunset(90.0D);
    if (Double.isNaN(d));
    for (Date localDate = null; ; localDate = getAdjustedSunsetDate(getDateFromTime(d), getSeaLevelSunrise()))
      return localDate;
  }

  public Date getSunTransit()
  {
    return getTimeOffset(getSunrise(), 6L * getTemporalHour());
  }

  public Date getSunrise()
  {
    double d = getUTCSunrise(90.0D);
    if (Double.isNaN(d));
    for (Date localDate = null; ; localDate = getDateFromTime(d))
      return localDate;
  }

  public Date getSunriseOffsetByDegrees(double paramDouble)
  {
    double d = getUTCSunrise(paramDouble);
    if (Double.isNaN(d));
    for (Date localDate = null; ; localDate = getDateFromTime(d))
      return localDate;
  }

  public double getSunriseSolarDipFromOffset(double paramDouble)
  {
    Date localDate1 = getSeaLevelSunrise();
    Date localDate2 = getTimeOffset(getSeaLevelSunrise(), -(60000.0D * paramDouble));
    BigDecimal localBigDecimal1 = new BigDecimal(0);
    BigDecimal localBigDecimal2 = new BigDecimal("0.0001");
    while (true)
    {
      if ((localDate1 != null) && (localDate1.getTime() <= localDate2.getTime()))
        return localBigDecimal1.doubleValue();
      localBigDecimal1 = localBigDecimal1.add(localBigDecimal2);
      localDate1 = getSunriseOffsetByDegrees(90.0D + localBigDecimal1.doubleValue());
    }
  }

  public Date getSunset()
  {
    double d = getUTCSunset(90.0D);
    if (Double.isNaN(d));
    for (Date localDate = null; ; localDate = getAdjustedSunsetDate(getDateFromTime(d), getSunrise()))
      return localDate;
  }

  public Date getSunsetOffsetByDegrees(double paramDouble)
  {
    double d = getUTCSunset(paramDouble);
    if (Double.isNaN(d));
    for (Date localDate = null; ; localDate = getAdjustedSunsetDate(getDateFromTime(d), getSunriseOffsetByDegrees(paramDouble)))
      return localDate;
  }

  public double getSunsetSolarDipFromOffset(double paramDouble)
  {
    Date localDate1 = getSeaLevelSunset();
    Date localDate2 = getTimeOffset(getSeaLevelSunset(), 60000.0D * paramDouble);
    BigDecimal localBigDecimal1 = new BigDecimal(0);
    BigDecimal localBigDecimal2 = new BigDecimal("0.0001");
    while (true)
    {
      if ((localDate1 != null) && (localDate1.getTime() >= localDate2.getTime()))
        return localBigDecimal1.doubleValue();
      localBigDecimal1 = localBigDecimal1.add(localBigDecimal2);
      localDate1 = getSunsetOffsetByDegrees(90.0D + localBigDecimal1.doubleValue());
    }
  }

  public long getTemporalHour()
  {
    return getTemporalHour(getSunrise(), getSunset());
  }

  public long getTemporalHour(Date paramDate1, Date paramDate2)
  {
    long l;
    if ((paramDate1 == null) || (paramDate2 == null))
      l = -9223372036854775808L;
    while (true)
    {
      return l;
      l = (paramDate2.getTime() - paramDate1.getTime()) / 12L;
    }
  }

  public Date getTimeOffset(Date paramDate, double paramDouble)
  {
    return getTimeOffset(paramDate, ()paramDouble);
  }

  public Date getTimeOffset(Date paramDate, long paramLong)
  {
    if ((paramDate == null) || (paramLong == -9223372036854775808L));
    for (Date localDate = null; ; localDate = new Date(paramLong + paramDate.getTime()))
      return localDate;
  }

  public double getUTCSeaLevelSunrise(double paramDouble)
  {
    return getAstronomicalCalculator().getUTCSunrise(this, paramDouble, false);
  }

  public double getUTCSeaLevelSunset(double paramDouble)
  {
    return getAstronomicalCalculator().getUTCSunset(this, paramDouble, false);
  }

  public double getUTCSunrise(double paramDouble)
  {
    return getAstronomicalCalculator().getUTCSunrise(this, paramDouble, true);
  }

  public double getUTCSunset(double paramDouble)
  {
    return getAstronomicalCalculator().getUTCSunset(this, paramDouble, true);
  }

  public int hashCode()
  {
    int i = 629 + getClass().hashCode();
    int j = i + (i * 37 + getCalendar().hashCode());
    int k = j + (j * 37 + getGeoLocation().hashCode());
    return k + (k * 37 + getAstronomicalCalculator().hashCode());
  }

  public void setAstronomicalCalculator(AstronomicalCalculator paramAstronomicalCalculator)
  {
    this.astronomicalCalculator = paramAstronomicalCalculator;
  }

  public void setCalendar(Calendar paramCalendar)
  {
    this.calendar = paramCalendar;
    if (getGeoLocation() != null)
      getCalendar().setTimeZone(getGeoLocation().getTimeZone());
  }

  public void setGeoLocation(GeoLocation paramGeoLocation)
  {
    this.geoLocation = paramGeoLocation;
    getCalendar().setTimeZone(paramGeoLocation.getTimeZone());
  }

  public String toString()
  {
    return ZmanimFormatter.toXML(this);
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     net.sourceforge.zmanim.AstronomicalCalendar
 * JD-Core Version:    0.6.0
 */