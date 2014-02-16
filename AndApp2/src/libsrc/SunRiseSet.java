package libsrc;

public final class SunRiseSet
{
  public static boolean calcSolarNoon = false;
  public static boolean doublePath = false;
  private static double lastEqOfTimeNoon = 0.0D;
  private static double lastJD = 0.0D;
  private static double lastLatitude = 0.0D;
  private static double lastLongitude = 0.0D;
  private static double lastSolarDecNoon = 0.0D;
  private static final double zenithAngleSunRiseSetOfficial = 90.832999999999998D;

  static
  {
    calcSolarNoon = false;
  }

  private static double calcEccentricityEarthOrbit(double paramDouble)
  {
    return 0.016708634D - paramDouble * (4.2037E-005D + 1.267E-007D * paramDouble);
  }

  private static double calcEquationOfTime(double paramDouble)
  {
    double d1 = calcObliquityCorrection(paramDouble);
    double d2 = calcGeomMeanLongSun(paramDouble);
    double d3 = calcEccentricityEarthOrbit(paramDouble);
    double d4 = calcGeomMeanAnomalySun(paramDouble);
    double d5 = Math.tan(Math.toRadians(d1) / 2.0D);
    double d6 = d5 * d5;
    double d7 = Math.sin(2.0D * Math.toRadians(d2));
    double d8 = Math.sin(Math.toRadians(d4));
    double d9 = Math.cos(2.0D * Math.toRadians(d2));
    double d10 = Math.sin(4.0D * Math.toRadians(d2));
    double d11 = Math.sin(2.0D * Math.toRadians(d4));
    return 240.0D * Math.toDegrees(d6 * d7 - d8 * (2.0D * d3) + d9 * (d8 * (d6 * (4.0D * d3))) - d10 * (d6 * (0.5D * d6)) - d11 * (d3 * (1.25D * d3)));
  }

  private static double calcGeomMeanAnomalySun(double paramDouble)
  {
    return 357.52911D + paramDouble * (35999.050289999999D - 0.0001537D * paramDouble);
  }

  private static double calcGeomMeanLongSun(double paramDouble)
  {
    return 280.46645999999998D + paramDouble * (36000.769829999997D + 0.0003032D * paramDouble);
  }

  private static double calcHourAngle(double paramDouble1, double paramDouble2, double paramDouble3)
  {
    double d1 = (0.0D / 0.0D);
    if (!Double.isNaN(paramDouble2))
    {
      double d2 = Math.toRadians(paramDouble1);
      d1 = MathE.acos(Math.cos(Math.toRadians(paramDouble3)) / (Math.cos(d2) * Math.cos(paramDouble2)) - Math.tan(d2) * Math.tan(paramDouble2));
      if (!Double.isNaN(d1))
      {
        d1 = Math.toDegrees(d1);
        if (paramDouble3 < 0.0D)
          d1 = -d1;
      }
    }
    return d1;
  }

  private static double calcObliquityCorrection(double paramDouble)
  {
    return 23.0D + (26.0D + (21.448D - paramDouble * (46.814999999999998D + paramDouble * (0.00059D - 0.001813D * paramDouble))) / 60.0D) / 60.0D + 0.00256D * Math.cos(Math.toRadians(125.04000000000001D - 1934.136D * paramDouble));
  }

  private static double calcSolNoonUTC(double paramDouble1, double paramDouble2)
  {
    double d1 = calcEquationOfTime(calcTimeJulianCent(0.5D + paramDouble1 + paramDouble2 / 360.0D));
    double d2 = 240.0D * (180.0D + paramDouble2) - d1;
    if (doublePath)
    {
      double d3 = calcEquationOfTime(calcTimeJulianCent(paramDouble1 + d2 / 86400.0D));
      d2 = 240.0D * (180.0D + paramDouble2) - d3;
    }
    return d2;
  }

  private static double calcSunApparentLong(double paramDouble)
  {
    double d1 = calcSunTrueLong(paramDouble);
    double d2 = 125.04000000000001D - 1934.136D * paramDouble;
    return d1 - 0.00569D - 0.00478D * Math.sin(Math.toRadians(d2));
  }

  private static double calcSunDeclination(double paramDouble)
  {
    double d1 = calcObliquityCorrection(paramDouble);
    double d2 = calcSunApparentLong(paramDouble);
    return MathE.asin(Math.sin(Math.toRadians(d1)) * Math.sin(Math.toRadians(d2)));
  }

  private static double calcSunEqOfCenter(double paramDouble)
  {
    double d1 = Math.toRadians(calcGeomMeanAnomalySun(paramDouble));
    double d2 = Math.sin(d1);
    double d3 = Math.sin(d1 + d1);
    double d4 = Math.sin(d1 + (d1 + d1));
    return d2 * (1.914602D - paramDouble * (0.004817D + 1.4E-005D * paramDouble)) + d3 * (0.019993D - 0.000101D * paramDouble) + 0.000289D * d4;
  }

  private static double calcSunRadVector(double paramDouble)
  {
    double d1 = calcSunTrueAnomaly(paramDouble);
    double d2 = calcEccentricityEarthOrbit(paramDouble);
    return 1.000001018D * (1.0D - d2 * d2) / (1.0D + d2 * Math.cos(Math.toRadians(d1)));
  }

  public static final void calcSunRiseSetUTC(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt)
  {
    lastJD = 347997.0D + paramInt1;
    lastLatitude = paramInt2 / 3600.0D;
    lastLongitude = paramInt3 / -3600.0D;
    double d1;
    double d2;
    if (calcSolarNoon)
    {
      double d11 = calcSolNoonUTC(lastJD, lastLongitude);
      d1 = calcTimeJulianCent(lastJD + d11 / 86400.0D);
      lastEqOfTimeNoon = calcEquationOfTime(d1);
      lastSolarDecNoon = calcSunDeclination(d1);
      d2 = calcHourAngle(lastLatitude, lastSolarDecNoon, 90.832999999999998D);
      if (!Double.isNaN(d2))
        break label131;
      paramArrayOfInt[1] = -2147483648;
      paramArrayOfInt[0] = -2147483648;
    }
    while (true)
    {
      return;
      d1 = calcTimeJulianCent(0.5D + lastJD + lastLongitude / 360.0D);
      break;
      label131: if (doublePath)
      {
        int i = 0;
        label140: double d4;
        if (i < 2)
        {
          if (i != 0)
            break label237;
          d4 = lastLongitude - d2;
        }
        double d7;
        double d9;
        while (true)
        {
          double d5 = 240.0D * (180.0D + d4) - lastEqOfTimeNoon;
          double d6 = calcTimeJulianCent(lastJD + d5 / 86400.0D);
          d7 = calcEquationOfTime(d6);
          double d8 = calcSunDeclination(d6);
          d9 = calcHourAngle(lastLatitude, d8, 90.832999999999998D);
          if (!Double.isNaN(d9))
            break label248;
          paramArrayOfInt[i] = -2147483648;
          i++;
          break label140;
          break;
          label237: d4 = d2 + lastLongitude;
        }
        label248: double d10;
        if (i == 0)
          d10 = lastLongitude - d9;
        while (true)
        {
          paramArrayOfInt[i] = (int)(240.0D * (180.0D + d10) - d7);
          break;
          d10 = d9 + lastLongitude;
        }
      }
      double d3 = 240.0D * (180.0D + lastLongitude - d2) - lastEqOfTimeNoon;
      paramArrayOfInt[0] = (int)d3;
      paramArrayOfInt[1] = (int)(d3 + 480.0D * d2);
    }
  }

  private static double calcSunTrueAnomaly(double paramDouble)
  {
    return calcGeomMeanAnomalySun(paramDouble) + calcSunEqOfCenter(paramDouble);
  }

  private static double calcSunTrueLong(double paramDouble)
  {
    return calcGeomMeanLongSun(paramDouble) + calcSunEqOfCenter(paramDouble);
  }

  private static double calcTimeJulianCent(double paramDouble)
  {
    return (paramDouble - 2451545.0D) / 36525.0D;
  }

  public static final int recalcZenithAngleUTC(int paramInt)
  {
    double d1 = paramInt / 3600.0D;
    double d2 = calcHourAngle(lastLatitude, lastSolarDecNoon, d1);
    int i;
    if (Double.isNaN(d2))
      i = -2147483648;
    while (true)
    {
      return i;
      double d3 = 240.0D * (180.0D + (d2 + lastLongitude)) - lastEqOfTimeNoon;
      if (doublePath)
      {
        double d4 = calcTimeJulianCent(lastJD + d3 / 86400.0D);
        double d5 = calcEquationOfTime(d4);
        double d6 = calcSunDeclination(d4);
        double d7 = calcHourAngle(lastLatitude, d6, d1);
        if (Double.isNaN(d7))
        {
          i = -2147483648;
          continue;
        }
        d3 = 240.0D * (180.0D + (d7 + lastLongitude)) - d5;
      }
      i = (int)d3;
    }
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     libsrc.SunRiseSet
 * JD-Core Version:    0.6.0
 */