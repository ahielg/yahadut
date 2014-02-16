package net.sourceforge.zmanim.util;

import java.io.PrintStream;
import java.util.TimeZone;

public class GeoLocation
  implements Cloneable
{
  private static final long HOUR_MILLIS = 3600000L;
  private static final long MINUTE_MILLIS = 60000L;
  private int DISTANCE = 0;
  private int FINAL_BEARING = 2;
  private int INITIAL_BEARING = 1;
  private double elevation;
  private double latitude;
  private String locationName;
  private double longitude;
  private TimeZone timeZone;

  public GeoLocation()
  {
    setLocationName("Greenwich, England");
    setLongitude(0.0D);
    setLatitude(51.477200000000003D);
    setTimeZone(TimeZone.getTimeZone("GMT"));
  }

  public GeoLocation(String paramString, double paramDouble1, double paramDouble2, double paramDouble3, TimeZone paramTimeZone)
  {
    setLocationName(paramString);
    setLatitude(paramDouble1);
    setLongitude(paramDouble2);
    setElevation(paramDouble3);
    setTimeZone(paramTimeZone);
  }

  public GeoLocation(String paramString, double paramDouble1, double paramDouble2, TimeZone paramTimeZone)
  {
    this(paramString, paramDouble1, paramDouble2, 0.0D, paramTimeZone);
  }

  private double vincentyFormula(GeoLocation paramGeoLocation, int paramInt)
  {
    double d1 = Math.toRadians(paramGeoLocation.getLongitude() - getLongitude());
    double d2 = Math.atan((1.0D - 0.003352810664747481D) * Math.tan(Math.toRadians(getLatitude())));
    double d3 = Math.atan((1.0D - 0.003352810664747481D) * Math.tan(Math.toRadians(paramGeoLocation.getLatitude())));
    double d4 = Math.sin(d2);
    double d5 = Math.cos(d2);
    double d6 = Math.sin(d3);
    double d7 = Math.cos(d3);
    double d8 = d1;
    double d9 = 6.283185307179586D;
    double d10 = 20.0D;
    double d11 = 0.0D;
    double d12 = 0.0D;
    double d13 = 0.0D;
    double d14 = 0.0D;
    double d15 = 0.0D;
    double d16 = 0.0D;
    double d17 = 0.0D;
    double d22;
    if (Math.abs(d8 - d9) > 1.0E-012D)
    {
      d10 -= 1.0D;
      if (d10 > 0.0D);
    }
    else
    {
      if (d10 != 0.0D)
        break label393;
      d22 = (0.0D / 0.0D);
    }
    while (true)
    {
      return d22;
      d11 = Math.sin(d8);
      d12 = Math.cos(d8);
      d13 = Math.sqrt(d7 * d11 * (d7 * d11) + (d5 * d6 - d12 * (d4 * d7)) * (d5 * d6 - d12 * (d4 * d7)));
      if (d13 == 0.0D)
      {
        d22 = 0.0D;
        continue;
      }
      d14 = d4 * d6 + d12 * (d5 * d7);
      d15 = Math.atan2(d13, d14);
      double d25 = d11 * (d5 * d7) / d13;
      d16 = 1.0D - d25 * d25;
      d17 = d14 - d6 * (2.0D * d4) / d16;
      if (Double.isNaN(d17))
        d17 = 0.0D;
      double d26 = d16 * (0.003352810664747481D / 16.0D) * (4.0D + 0.003352810664747481D * (4.0D - 3.0D * d16));
      d9 = d8;
      d8 = d1 + d25 * (0.003352810664747481D * (1.0D - d26)) * (d15 + d26 * d13 * (d17 + d26 * d14 * (-1.0D + d17 * (2.0D * d17))));
      break;
      label393: double d18 = d16 * (6378137.0D * 6378137.0D - 6356752.3141999999D * 6356752.3141999999D) / (6356752.3141999999D * 6356752.3141999999D);
      double d19 = 1.0D + d18 / 16384.0D * (4096.0D + d18 * (-768.0D + d18 * (320.0D - 175.0D * d18)));
      double d20 = d18 / 1024.0D * (256.0D + d18 * (-128.0D + d18 * (74.0D - 47.0D * d18)));
      double d21 = d20 * d13 * (d17 + d20 / 4.0D * (d14 * (-1.0D + d17 * (2.0D * d17)) - d17 * (d20 / 6.0D) * (-3.0D + d13 * (4.0D * d13)) * (-3.0D + d17 * (4.0D * d17))));
      d22 = 6356752.3141999999D * d19 * (d15 - d21);
      double d23 = Math.toDegrees(Math.atan2(d7 * d11, d5 * d6 - d12 * (d4 * d7)));
      double d24 = Math.toDegrees(Math.atan2(d5 * d11, d7 * -d4 + d12 * (d5 * d6)));
      if (paramInt == this.DISTANCE)
        continue;
      if (paramInt == this.INITIAL_BEARING)
      {
        d22 = d23;
        continue;
      }
      if (paramInt == this.FINAL_BEARING)
      {
        d22 = d24;
        continue;
      }
      d22 = (0.0D / 0.0D);
    }
  }

  public Object clone()
  {
    GeoLocation localGeoLocation = null;
    try
    {
      localGeoLocation = (GeoLocation)super.clone();
      localGeoLocation.timeZone = ((TimeZone)getTimeZone().clone());
      localGeoLocation.locationName = getLocationName();
      return localGeoLocation;
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
      if (!(paramObject instanceof GeoLocation))
      {
        i = 0;
        continue;
      }
      GeoLocation localGeoLocation = (GeoLocation)paramObject;
      if ((Double.doubleToLongBits(this.latitude) == Double.doubleToLongBits(localGeoLocation.latitude)) && (Double.doubleToLongBits(this.longitude) == Double.doubleToLongBits(localGeoLocation.longitude)) && (this.elevation == localGeoLocation.elevation))
      {
        if (this.locationName != null)
          break label107;
        if (localGeoLocation.locationName == null)
        {
          label88: if (this.timeZone != null)
            break label124;
          if (localGeoLocation.timeZone == null)
            continue;
        }
      }
      label107: label124: 
      do
      {
        do
        {
          i = 0;
          break;
        }
        while (!this.locationName.equals(localGeoLocation.locationName));
        break label88;
      }
      while (!this.timeZone.equals(localGeoLocation.timeZone));
    }
  }

  public double getElevation()
  {
    return this.elevation;
  }

  public double getGeodesicDistance(GeoLocation paramGeoLocation)
  {
    return vincentyFormula(paramGeoLocation, this.DISTANCE);
  }

  public double getGeodesicFinalBearing(GeoLocation paramGeoLocation)
  {
    return vincentyFormula(paramGeoLocation, this.FINAL_BEARING);
  }

  public double getGeodesicInitialBearing(GeoLocation paramGeoLocation)
  {
    return vincentyFormula(paramGeoLocation, this.INITIAL_BEARING);
  }

  public double getLatitude()
  {
    return this.latitude;
  }

  public long getLocalMeanTimeOffset()
  {
    return ()(60000.0D * (4.0D * getLongitude()) - getTimeZone().getRawOffset());
  }

  public String getLocationName()
  {
    return this.locationName;
  }

  public double getLongitude()
  {
    return this.longitude;
  }

  public double getRhumbLineBearing(GeoLocation paramGeoLocation)
  {
    double d1 = Math.toRadians(paramGeoLocation.getLongitude() - getLongitude());
    double d2 = Math.log(Math.tan(0.7853981633974483D + Math.toRadians(paramGeoLocation.getLatitude()) / 2.0D) / Math.tan(0.7853981633974483D + Math.toRadians(getLatitude()) / 2.0D));
    if (Math.abs(d1) > 3.141592653589793D)
    {
      if (d1 <= 0.0D)
        break label89;
      d1 = -(6.283185307179586D - d1);
    }
    while (true)
    {
      return Math.toDegrees(Math.atan2(d1, d2));
      label89: d1 += 6.283185307179586D;
    }
  }

  public double getRhumbLineDistance(GeoLocation paramGeoLocation)
  {
    double d1 = Math.toRadians(paramGeoLocation.getLatitude() - getLatitude());
    double d2 = Math.toRadians(Math.abs(paramGeoLocation.getLongitude() - getLongitude()));
    double d3 = Math.log(Math.tan(0.7853981633974483D + Math.toRadians(paramGeoLocation.getLongitude()) / 2.0D) / Math.tan(0.7853981633974483D + Math.toRadians(getLatitude()) / 2.0D));
    double d4;
    if (Math.abs(d1) > 1.0E-010D)
      d4 = d1 / d3;
    while (true)
    {
      if (d2 > 3.141592653589793D)
        d2 = 6.283185307179586D - d2;
      return 6371.0D * Math.sqrt(d1 * d1 + d2 * (d2 * (d4 * d4)));
      d4 = Math.cos(Math.toRadians(getLatitude()));
    }
  }

  public TimeZone getTimeZone()
  {
    return this.timeZone;
  }

  public int hashCode()
  {
    int i = 0;
    long l1 = Double.doubleToLongBits(this.latitude);
    long l2 = Double.doubleToLongBits(this.longitude);
    long l3 = Double.doubleToLongBits(this.elevation);
    int j = (int)(l1 ^ l1 >>> 32);
    int k = (int)(l2 ^ l2 >>> 32);
    int m = (int)(l3 ^ l3 >>> 32);
    int n = 629 + getClass().hashCode();
    int i1 = n + (j + n * 37);
    int i2 = i1 + (k + i1 * 37);
    int i3 = i2 + (m + i2 * 37);
    int i4 = i3 * 37;
    int i5;
    int i6;
    int i7;
    if (this.locationName == null)
    {
      i5 = 0;
      i6 = i3 + (i5 + i4);
      i7 = i6 * 37;
      if (this.timeZone != null)
        break label172;
    }
    while (true)
    {
      return i6 + (i7 + i);
      i5 = this.locationName.hashCode();
      break;
      label172: i = this.timeZone.hashCode();
    }
  }

  public void setElevation(double paramDouble)
  {
    if (paramDouble < 0.0D)
      throw new IllegalArgumentException("Elevation cannot be negative");
    this.elevation = paramDouble;
  }

  public void setLatitude(double paramDouble)
  {
    if ((paramDouble > 90.0D) || (paramDouble < -90.0D))
      throw new IllegalArgumentException("Latitude must be between -90 and  90");
    this.latitude = paramDouble;
  }

  public void setLatitude(int paramInt1, int paramInt2, double paramDouble, String paramString)
  {
    double d = paramInt1 + (paramInt2 + paramDouble / 60.0D) / 60.0D;
    if ((d > 90.0D) || (d < 0.0D))
      throw new IllegalArgumentException("Latitude must be between 0 and  90. Use direction of S instead of negative.");
    if (paramString.equals("S"))
      d *= -1.0D;
    do
    {
      this.latitude = d;
      return;
    }
    while (paramString.equals("N"));
    throw new IllegalArgumentException("Latitude direction must be N or S");
  }

  public void setLocationName(String paramString)
  {
    this.locationName = paramString;
  }

  public void setLongitude(double paramDouble)
  {
    if ((paramDouble > 180.0D) || (paramDouble < -180.0D))
      throw new IllegalArgumentException("Longitude must be between -180 and  180");
    this.longitude = paramDouble;
  }

  public void setLongitude(int paramInt1, int paramInt2, double paramDouble, String paramString)
  {
    double d = paramInt1 + (paramInt2 + paramDouble / 60.0D) / 60.0D;
    if ((d > 180.0D) || (this.longitude < 0.0D))
      throw new IllegalArgumentException("Longitude must be between 0 and  180. Use the ");
    if (paramString.equals("W"))
      d *= -1.0D;
    do
    {
      this.longitude = d;
      return;
    }
    while (paramString.equals("E"));
    throw new IllegalArgumentException("Longitude direction must be E or W");
  }

  public void setTimeZone(TimeZone paramTimeZone)
  {
    this.timeZone = paramTimeZone;
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("\nLocation Name:\t\t\t").append(getLocationName());
    localStringBuffer.append("\nLatitude:\t\t\t").append(getLatitude()).append("&deg;");
    localStringBuffer.append("\nLongitude:\t\t\t").append(getLongitude()).append("&deg;");
    localStringBuffer.append("\nElevation:\t\t\t").append(getElevation()).append(" Meters");
    localStringBuffer.append("\nTimezone Name:\t\t\t").append(getTimeZone().getID());
    localStringBuffer.append("\nTimezone GMT Offset:\t\t").append(getTimeZone().getRawOffset() / 3600000L);
    localStringBuffer.append("\nTimezone DST Offset:\t\t").append(getTimeZone().getDSTSavings() / 3600000L);
    return localStringBuffer.toString();
  }

  public String toXML()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("<GeoLocation>\n");
    localStringBuffer.append("\t<LocationName>").append(getLocationName()).append("</LocationName>\n");
    localStringBuffer.append("\t<Latitude>").append(getLatitude()).append("&deg;").append("</Latitude>\n");
    localStringBuffer.append("\t<Longitude>").append(getLongitude()).append("&deg;").append("</Longitude>\n");
    localStringBuffer.append("\t<Elevation>").append(getElevation()).append(" Meters").append("</Elevation>\n");
    localStringBuffer.append("\t<TimezoneName>").append(getTimeZone().getID()).append("</TimezoneName>\n");
    localStringBuffer.append("\t<TimeZoneDisplayName>").append(getTimeZone().getDisplayName()).append("</TimeZoneDisplayName>\n");
    localStringBuffer.append("\t<TimezoneGMTOffset>").append(getTimeZone().getRawOffset() / 3600000L).append("</TimezoneGMTOffset>\n");
    localStringBuffer.append("\t<TimezoneDSTOffset>").append(getTimeZone().getDSTSavings() / 3600000L).append("</TimezoneDSTOffset>\n");
    localStringBuffer.append("</GeoLocation>");
    return localStringBuffer.toString();
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     net.sourceforge.zmanim.util.GeoLocation
 * JD-Core Version:    0.6.0
 */