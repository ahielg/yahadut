package net.sourceforge.zmanim.util;

public class GeoLocationUtils
{
  private static int DISTANCE = 0;
  private static int FINAL_BEARING;
  private static int INITIAL_BEARING = 1;

  static
  {
    FINAL_BEARING = 2;
  }

  public static double getGeodesicDistance(GeoLocation paramGeoLocation1, GeoLocation paramGeoLocation2)
  {
    return vincentyFormula(paramGeoLocation1, paramGeoLocation2, DISTANCE);
  }

  public static double getGeodesicFinalBearing(GeoLocation paramGeoLocation1, GeoLocation paramGeoLocation2)
  {
    return vincentyFormula(paramGeoLocation1, paramGeoLocation2, FINAL_BEARING);
  }

  public static double getGeodesicInitialBearing(GeoLocation paramGeoLocation1, GeoLocation paramGeoLocation2)
  {
    return vincentyFormula(paramGeoLocation1, paramGeoLocation2, INITIAL_BEARING);
  }

  public static double getRhumbLineBearing(GeoLocation paramGeoLocation1, GeoLocation paramGeoLocation2)
  {
    double d1 = Math.toRadians(paramGeoLocation2.getLongitude() - paramGeoLocation1.getLongitude());
    double d2 = Math.log(Math.tan(0.7853981633974483D + Math.toRadians(paramGeoLocation2.getLatitude()) / 2.0D) / Math.tan(0.7853981633974483D + Math.toRadians(paramGeoLocation1.getLatitude()) / 2.0D));
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

  public static double getRhumbLineDistance(GeoLocation paramGeoLocation1, GeoLocation paramGeoLocation2)
  {
    double d1 = Math.toRadians(paramGeoLocation2.getLatitude() - paramGeoLocation1.getLatitude());
    double d2 = Math.toRadians(Math.abs(paramGeoLocation2.getLongitude() - paramGeoLocation1.getLongitude()));
    double d3 = Math.log(Math.tan(0.7853981633974483D + Math.toRadians(paramGeoLocation2.getLongitude()) / 2.0D) / Math.tan(0.7853981633974483D + Math.toRadians(paramGeoLocation1.getLatitude()) / 2.0D));
    double d4;
    if (Math.abs(d1) > 1.0E-010D)
      d4 = d1 / d3;
    while (true)
    {
      if (d2 > 3.141592653589793D)
        d2 = 6.283185307179586D - d2;
      return 6371.0D * Math.sqrt(d1 * d1 + d2 * (d2 * (d4 * d4)));
      d4 = Math.cos(Math.toRadians(paramGeoLocation1.getLatitude()));
    }
  }

  private static double vincentyFormula(GeoLocation paramGeoLocation1, GeoLocation paramGeoLocation2, int paramInt)
  {
    double d1 = Math.toRadians(paramGeoLocation2.getLongitude() - paramGeoLocation1.getLongitude());
    double d2 = Math.atan((1.0D - 0.003352810664747481D) * Math.tan(Math.toRadians(paramGeoLocation1.getLatitude())));
    double d3 = Math.atan((1.0D - 0.003352810664747481D) * Math.tan(Math.toRadians(paramGeoLocation2.getLatitude())));
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
      if (paramInt == DISTANCE)
        continue;
      if (paramInt == INITIAL_BEARING)
      {
        d22 = d23;
        continue;
      }
      if (paramInt == FINAL_BEARING)
      {
        d22 = d24;
        continue;
      }
      d22 = (0.0D / 0.0D);
    }
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     net.sourceforge.zmanim.util.GeoLocationUtils
 * JD-Core Version:    0.6.0
 */