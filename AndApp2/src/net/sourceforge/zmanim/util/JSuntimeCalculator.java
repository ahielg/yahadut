package net.sourceforge.zmanim.util;

import java.util.Calendar;
import net.sourceforge.zmanim.AstronomicalCalendar;

public class JSuntimeCalculator extends AstronomicalCalculator
{
  private String calculatorName = "US National Oceanic and Atmospheric Administration Algorithm";

  private static double dateToJulian(Calendar paramCalendar)
  {
    int i = paramCalendar.get(1);
    int j = 1 + paramCalendar.get(2);
    int k = paramCalendar.get(5);
    int m = paramCalendar.get(11);
    int n = paramCalendar.get(12);
    int i1 = paramCalendar.get(13);
    double d = 100.0D * i + j - 190002.5D;
    return 0.5D + (1721013.5D + (367.0D * i - Math.floor(7.0D * (i + Math.floor((9.0D + j) / 12.0D)) / 4.0D) + Math.floor(275.0D * j / 9.0D) + k + (m + (n + i1 / 60.0D) / 60.0D) / 24.0D) - 0.5D * d / Math.abs(d));
  }

  private static double eccentricityOfEarthsOrbit(double paramDouble)
  {
    return 0.016708634D - paramDouble * (4.2037E-005D + 1.267E-007D * paramDouble);
  }

  private static double equationOfCentreForSun(double paramDouble)
  {
    double d = geometricMeanAnomalyOfSun(paramDouble);
    return Math.sin(Math.toRadians(d)) * (1.914602D - paramDouble * (0.004817D + 1.4E-005D * paramDouble)) + Math.sin(2.0D * Math.toRadians(d)) * (0.019993D - 0.000101D * paramDouble) + 0.000289D * Math.sin(3.0D * Math.toRadians(d));
  }

  private static double equationOfTime(double paramDouble)
  {
    double d1 = obliquityCorrection(paramDouble);
    double d2 = geomMeanLongSun(paramDouble);
    double d3 = eccentricityOfEarthsOrbit(paramDouble);
    double d4 = geometricMeanAnomalyOfSun(paramDouble);
    double d5 = Math.pow(Math.tan(Math.toRadians(d1) / 2.0D), 2.0D);
    return 4.0D * Math.toDegrees(d5 * Math.sin(2.0D * Math.toRadians(d2)) - 2.0D * d3 * Math.sin(Math.toRadians(d4)) + d5 * (4.0D * d3) * Math.sin(Math.toRadians(d4)) * Math.cos(2.0D * Math.toRadians(d2)) - d5 * (0.5D * d5) * Math.sin(4.0D * Math.toRadians(d2)) - d3 * (1.25D * d3) * Math.sin(2.0D * Math.toRadians(d4)));
  }

  private static double eveningPhenomenon(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    double d1 = julianDayToJulianCenturies(paramDouble1);
    double d2 = equationOfTime(d1);
    double d3 = 720.0D + 4.0D * (paramDouble3 - Math.toDegrees(hourAngleEvening(paramDouble2, sunDeclination(d1), paramDouble4))) - d2;
    double d4 = julianDayToJulianCenturies(julianCenturiesToJulianDay(d1) + d3 / 1440.0D);
    double d5 = equationOfTime(d4);
    return 720.0D + 4.0D * (paramDouble3 - Math.toDegrees(hourAngleEvening(paramDouble2, sunDeclination(d4), paramDouble4))) - d5;
  }

  private static double geomMeanLongSun(double paramDouble)
  {
    double d = 280.46645999999998D + paramDouble * (36000.769829999997D + 0.0003032D * paramDouble);
    while (true)
    {
      if ((d < 0.0D) || (d > 360.0D))
        return d;
      if (d > 360.0D)
        d -= 360.0D;
      if (d >= 0.0D)
        continue;
      d += 360.0D;
    }
  }

  private static double geometricMeanAnomalyOfSun(double paramDouble)
  {
    return 357.52911D + paramDouble * (35999.050289999999D - 0.0001537D * paramDouble);
  }

  private static double hourAngleEvening(double paramDouble1, double paramDouble2, double paramDouble3)
  {
    return -hourAngleMorning(paramDouble1, paramDouble2, paramDouble3);
  }

  private static double hourAngleMorning(double paramDouble1, double paramDouble2, double paramDouble3)
  {
    return Math.acos(Math.cos(Math.toRadians(paramDouble3)) / (Math.cos(Math.toRadians(paramDouble1)) * Math.cos(Math.toRadians(paramDouble2))) - Math.tan(Math.toRadians(paramDouble1)) * Math.tan(Math.toRadians(paramDouble2)));
  }

  private static double julianCenturiesToJulianDay(double paramDouble)
  {
    return 2451545.0D + 36525.0D * paramDouble;
  }

  private static double julianDayToJulianCenturies(double paramDouble)
  {
    return (paramDouble - 2451545.0D) / 36525.0D;
  }

  private static double meanObliquityOfEcliptic(double paramDouble)
  {
    return 23.0D + (26.0D + (21.448D - paramDouble * (46.814999999999998D + paramDouble * (0.00059D - 0.001813D * paramDouble)) / 60.0D)) / 60.0D;
  }

  private static double morningPhenomenon(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    double d1 = julianDayToJulianCenturies(paramDouble1);
    double d2 = equationOfTime(d1);
    double d3 = 720.0D + 4.0D * (paramDouble3 - Math.toDegrees(hourAngleMorning(paramDouble2, sunDeclination(d1), paramDouble4))) - d2;
    double d4 = julianDayToJulianCenturies(julianCenturiesToJulianDay(d1) + d3 / 1440.0D);
    double d5 = equationOfTime(d4);
    return 720.0D + 4.0D * (paramDouble3 - Math.toDegrees(hourAngleMorning(paramDouble2, sunDeclination(d4), paramDouble4))) - d5;
  }

  private static double obliquityCorrection(double paramDouble)
  {
    return meanObliquityOfEcliptic(paramDouble) + 0.00256D * Math.cos(Math.toRadians(125.04000000000001D - 1934.136D * paramDouble));
  }

  private static double sunDeclination(double paramDouble)
  {
    double d1 = obliquityCorrection(paramDouble);
    double d2 = sunsApparentLongitude(paramDouble);
    return Math.toDegrees(Math.asin(Math.sin(Math.toRadians(d1)) * Math.sin(Math.toRadians(d2))));
  }

  private static double sunsApparentLongitude(double paramDouble)
  {
    return sunsTrueLongitude(paramDouble) - 0.00569D - 0.00478D * Math.sin(Math.toRadians(125.04000000000001D - 1934.136D * paramDouble));
  }

  private static double sunsTrueLongitude(double paramDouble)
  {
    return geomMeanLongSun(paramDouble) + equationOfCentreForSun(paramDouble);
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
      return morningPhenomenon(dateToJulian(paramAstronomicalCalendar.getCalendar()), paramAstronomicalCalendar.getGeoLocation().getLatitude(), -paramAstronomicalCalendar.getGeoLocation().getLongitude(), d) / 60.0D;
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
      return eveningPhenomenon(dateToJulian(paramAstronomicalCalendar.getCalendar()), paramAstronomicalCalendar.getGeoLocation().getLatitude(), -paramAstronomicalCalendar.getGeoLocation().getLongitude(), d) / 60.0D;
      d = adjustZenith(paramDouble, 0.0D);
    }
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     net.sourceforge.zmanim.util.JSuntimeCalculator
 * JD-Core Version:    0.6.0
 */