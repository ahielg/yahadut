package net.sourceforge.zmanim;

import java.util.Calendar;
import java.util.Date;
import net.sourceforge.zmanim.util.GeoLocation;

public class ZmanimCalendar extends AstronomicalCalendar
{
  protected static final double ZENITH_16_POINT_1 = 106.09999999999999D;
  protected static final double ZENITH_8_POINT_5 = 98.5D;
  private static final long serialVersionUID = 1L;
  private double candleLightingOffset = 18.0D;

  public ZmanimCalendar()
  {
  }

  public ZmanimCalendar(GeoLocation paramGeoLocation)
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
      if (!(paramObject instanceof ZmanimCalendar))
      {
        i = 0;
        continue;
      }
      ZmanimCalendar localZmanimCalendar = (ZmanimCalendar)paramObject;
      if ((getCalendar().equals(localZmanimCalendar.getCalendar())) && (getGeoLocation().equals(localZmanimCalendar.getGeoLocation())) && (getAstronomicalCalculator().equals(localZmanimCalendar.getAstronomicalCalculator())))
        continue;
      i = 0;
    }
  }

  public Date getAlos72()
  {
    return getTimeOffset(getSeaLevelSunrise(), -4320000L);
  }

  public Date getAlosHashachar()
  {
    return getSunriseOffsetByDegrees(106.09999999999999D);
  }

  public Date getCandelLighting()
  {
    return getTimeOffset(getSunset(), 60000.0D * -getCandleLightingOffset());
  }

  public double getCandleLightingOffset()
  {
    return this.candleLightingOffset;
  }

  public Date getChatzos()
  {
    return getSunTransit();
  }

  public Date getMinchaGedola()
  {
    return getTimeOffset(getSeaLevelSunrise(), 6.5D * getShaahZmanisGra());
  }

  public Date getMinchaKetana()
  {
    return getTimeOffset(getSeaLevelSunrise(), 9.5D * getShaahZmanisGra());
  }

  public Date getPlagHamincha()
  {
    return getTimeOffset(getSeaLevelSunrise(), 10.75D * getShaahZmanisGra());
  }

  public long getShaahZmanisGra()
  {
    return getTemporalHour(getSeaLevelSunrise(), getSeaLevelSunset());
  }

  public long getShaahZmanisMGA()
  {
    return getTemporalHour(getAlos72(), getTzais72());
  }

  public Date getSofZmanShmaGRA()
  {
    return getTimeOffset(getSeaLevelSunrise(), 3L * getShaahZmanisGra());
  }

  public Date getSofZmanShmaMGA()
  {
    return getTimeOffset(getAlos72(), 3L * getShaahZmanisMGA());
  }

  public Date getSofZmanTfilaGRA()
  {
    return getTimeOffset(getSeaLevelSunrise(), 4L * getShaahZmanisGra());
  }

  public Date getSofZmanTfilaMGA()
  {
    return getTimeOffset(getAlos72(), 4L * getShaahZmanisMGA());
  }

  public Date getSolarMidnight()
  {
    ZmanimCalendar localZmanimCalendar = (ZmanimCalendar)clone();
    localZmanimCalendar.getCalendar().add(5, 1);
    Date localDate = getSunset();
    return getTimeOffset(localDate, 6L * getTemporalHour(localDate, localZmanimCalendar.getSunrise()));
  }

  public Date getTzais()
  {
    return getSunsetOffsetByDegrees(98.5D);
  }

  public Date getTzais72()
  {
    return getTimeOffset(getSeaLevelSunset(), 4320000L);
  }

  public int hashCode()
  {
    int i = 629 + getClass().hashCode();
    int j = i + (i * 37 + getCalendar().hashCode());
    int k = j + (j * 37 + getGeoLocation().hashCode());
    return k + (k * 37 + getAstronomicalCalculator().hashCode());
  }

  public void setCandleLightingOffset(double paramDouble)
  {
    this.candleLightingOffset = paramDouble;
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     net.sourceforge.zmanim.ZmanimCalendar
 * JD-Core Version:    0.6.0
 */