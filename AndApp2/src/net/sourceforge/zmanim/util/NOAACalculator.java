package net.sourceforge.zmanim.util;

import java.util.Calendar;
import net.sourceforge.zmanim.AstronomicalCalendar;

public class NOAACalculator extends AstronomicalCalculator
{
  private String calculatorName = "US National Oceanic and Atmospheric Administration Algorithm";

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
    return 4.0D * Math.toDegrees(d6 * d7 - d8 * (2.0D * d3) + d9 * (d8 * (d6 * (4.0D * d3))) - d10 * (d6 * (0.5D * d6)) - d11 * (d3 * (1.25D * d3)));
  }

  private static double calcGeomMeanAnomalySun(double paramDouble)
  {
    return 357.52911D + paramDouble * (35999.050289999999D - 0.0001537D * paramDouble);
  }

  private static double calcGeomMeanLongSun(double paramDouble)
  {
    double d = 280.46645999999998D + paramDouble * (36000.769829999997D + 0.0003032D * paramDouble);
    if (d <= 360.0D);
    while (true)
    {
      if (d >= 0.0D)
      {
        return d;
        d -= 360.0D;
        break;
      }
      d += 360.0D;
    }
  }

  private static double calcHourAngleSunrise(double paramDouble1, double paramDouble2, double paramDouble3)
  {
    double d1 = Math.toRadians(paramDouble1);
    double d2 = Math.toRadians(paramDouble2);
    return Math.acos(Math.cos(Math.toRadians(paramDouble3)) / (Math.cos(d1) * Math.cos(d2)) - Math.tan(d1) * Math.tan(d2));
  }

  private static double calcHourAngleSunset(double paramDouble1, double paramDouble2, double paramDouble3)
  {
    double d1 = Math.toRadians(paramDouble1);
    double d2 = Math.toRadians(paramDouble2);
    return -Math.acos(Math.cos(Math.toRadians(paramDouble3)) / (Math.cos(d1) * Math.cos(d2)) - Math.tan(d1) * Math.tan(d2));
  }

  private static double calcJD(Calendar paramCalendar)
  {
    int i = paramCalendar.get(1);
    int j = 1 + paramCalendar.get(2);
    int k = paramCalendar.get(5);
    if (j <= 2)
    {
      i--;
      j += 12;
    }
    double d = Math.floor(i / 100);
    return 2.0D - d + Math.floor(d / 4.0D) + (Math.floor(365.25D * (i + 4716)) + Math.floor(30.600100000000001D * (j + 1)) + k) - 1524.5D;
  }

  private static double calcJDFromJulianCent(double paramDouble)
  {
    return 2451545.0D + 36525.0D * paramDouble;
  }

  private static double calcMeanObliquityOfEcliptic(double paramDouble)
  {
    return 23.0D + (26.0D + (21.448D - paramDouble * (46.814999999999998D + paramDouble * (0.00059D - 0.001813D * paramDouble))) / 60.0D) / 60.0D;
  }

  private static double calcObliquityCorrection(double paramDouble)
  {
    return calcMeanObliquityOfEcliptic(paramDouble) + 0.00256D * Math.cos(Math.toRadians(125.04000000000001D - 1934.136D * paramDouble));
  }

  private static double calcSolNoonUTC(double paramDouble1, double paramDouble2)
  {
    double d1 = calcEquationOfTime(calcTimeJulianCent(calcJDFromJulianCent(paramDouble1) + paramDouble2 / 360.0D));
    double d2 = 720.0D + 4.0D * paramDouble2 - d1;
    double d3 = calcEquationOfTime(calcTimeJulianCent(calcJDFromJulianCent(paramDouble1) - 0.5D + d2 / 1440.0D));
    return 720.0D + 4.0D * paramDouble2 - d3;
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
    return Math.toDegrees(Math.asin(Math.sin(Math.toRadians(d1)) * Math.sin(Math.toRadians(d2))));
  }

  private static double calcSunEqOfCenter(double paramDouble)
  {
    double d1 = Math.toRadians(calcGeomMeanAnomalySun(paramDouble));
    double d2 = Math.sin(d1);
    double d3 = Math.sin(d1 + d1);
    double d4 = Math.sin(d1 + (d1 + d1));
    return d2 * (1.914602D - paramDouble * (0.004817D + 1.4E-005D * paramDouble)) + d3 * (0.019993D - 0.000101D * paramDouble) + 0.000289D * d4;
  }

  private static double calcSunTrueLong(double paramDouble)
  {
    return calcGeomMeanLongSun(paramDouble) + calcSunEqOfCenter(paramDouble);
  }

  private static double calcSunriseUTC(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    double d1 = calcTimeJulianCent(paramDouble1);
    double d2 = calcTimeJulianCent(paramDouble1 + calcSolNoonUTC(d1, paramDouble3) / 1440.0D);
    double d3 = calcEquationOfTime(d2);
    double d4 = 720.0D + 4.0D * (paramDouble3 - Math.toDegrees(calcHourAngleSunrise(paramDouble2, calcSunDeclination(d2), paramDouble4))) - d3;
    double d5 = calcTimeJulianCent(calcJDFromJulianCent(d1) + d4 / 1440.0D);
    double d6 = calcEquationOfTime(d5);
    return 720.0D + 4.0D * (paramDouble3 - Math.toDegrees(calcHourAngleSunrise(paramDouble2, calcSunDeclination(d5), paramDouble4))) - d6;
  }

  private static double calcSunsetUTC(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    double d1 = calcTimeJulianCent(paramDouble1);
    double d2 = calcTimeJulianCent(paramDouble1 + calcSolNoonUTC(d1, paramDouble3) / 1440.0D);
    double d3 = calcEquationOfTime(d2);
    double d4 = 720.0D + 4.0D * (paramDouble3 - Math.toDegrees(calcHourAngleSunset(paramDouble2, calcSunDeclination(d2), paramDouble4))) - d3;
    double d5 = calcTimeJulianCent(calcJDFromJulianCent(d1) + d4 / 1440.0D);
    double d6 = calcEquationOfTime(d5);
    return 720.0D + 4.0D * (paramDouble3 - Math.toDegrees(calcHourAngleSunset(paramDouble2, calcSunDeclination(d5), paramDouble4))) - d6;
  }

  private static double calcTimeJulianCent(double paramDouble)
  {
    return (paramDouble - 2451545.0D) / 36525.0D;
  }

  public String getCalculatorName()
  {
    return this.calculatorName;
  }

  public double getUTCSunrise(AstronomicalCalendar paramAstronomicalCalendar, double paramDouble, boolean paramBoolean)
  {
    double d;
    if (paramBoolean)
      d = adjustZenith(paramDouble, paramAstronomicalCalendar.getGeoLocation().getElevation());
    while (true)
    {
      return calcSunriseUTC(calcJD(paramAstronomicalCalendar.getCalendar()), paramAstronomicalCalendar.getGeoLocation().getLatitude(), -paramAstronomicalCalendar.getGeoLocation().getLongitude(), d) / 60.0D;
      d = adjustZenith(paramDouble, 0.0D);
    }
  }

  public double getUTCSunset(AstronomicalCalendar paramAstronomicalCalendar, double paramDouble, boolean paramBoolean)
  {
    double d;
    if (paramBoolean)
      d = adjustZenith(paramDouble, paramAstronomicalCalendar.getGeoLocation().getElevation());
    while (true)
    {
      return calcSunsetUTC(calcJD(paramAstronomicalCalendar.getCalendar()), paramAstronomicalCalendar.getGeoLocation().getLatitude(), -paramAstronomicalCalendar.getGeoLocation().getLongitude(), d) / 60.0D;
      d = adjustZenith(paramDouble, 0.0D);
    }
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     net.sourceforge.zmanim.util.NOAACalculator
 * JD-Core Version:    0.6.0
 */