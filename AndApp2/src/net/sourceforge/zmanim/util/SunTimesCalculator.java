package net.sourceforge.zmanim.util;

import java.util.Calendar;
import net.sourceforge.zmanim.AstronomicalCalendar;

public class SunTimesCalculator extends AstronomicalCalculator
{
  private static final double DEG_PER_HOUR = 15.0D;
  private static final int TYPE_SUNRISE = 0;
  private static final int TYPE_SUNSET = 1;
  public static final double ZENITH = 90.833333333333329D;
  private String calculatorName = "US Naval Almanac Algorithm";

  private static double acosDeg(double paramDouble)
  {
    return 360.0D * Math.acos(paramDouble) / 6.283185307179586D;
  }

  private static double asinDeg(double paramDouble)
  {
    return 360.0D * Math.asin(paramDouble) / 6.283185307179586D;
  }

  private static double cosDeg(double paramDouble)
  {
    return Math.cos(3.141592653589793D * (2.0D * paramDouble) / 360.0D);
  }

  private static double getApproxTimeDays(int paramInt1, double paramDouble, int paramInt2)
  {
    double d;
    if (paramInt2 == 0)
      d = paramInt1 + (6.0D - paramDouble) / 24.0D;
    while (true)
    {
      return d;
      d = paramInt1 + (18.0D - paramDouble) / 24.0D;
    }
  }

  private static double getCosLocalHourAngle(double paramDouble1, double paramDouble2, double paramDouble3)
  {
    double d1 = 0.39782D * sinDeg(paramDouble1);
    double d2 = cosDeg(asinDeg(d1));
    return (cosDeg(paramDouble3) - d1 * sinDeg(paramDouble2)) / (d2 * cosDeg(paramDouble2));
  }

  private static int getDayOfYear(int paramInt1, int paramInt2, int paramInt3)
  {
    return -30 + (paramInt3 + (paramInt2 * 275 / 9 - (paramInt2 + 9) / 12 * (1 + (2 + (paramInt1 - 4 * (paramInt1 / 4))) / 3)));
  }

  private static double getHoursFromMeridian(double paramDouble)
  {
    return paramDouble / 15.0D;
  }

  private static double getLocalMeanTime(double paramDouble1, double paramDouble2, double paramDouble3)
  {
    return paramDouble1 + paramDouble2 - 0.06571000000000001D * paramDouble3 - 6.622D;
  }

  private static double getMeanAnomaly(int paramInt1, double paramDouble, int paramInt2)
  {
    return 0.9856D * getApproxTimeDays(paramInt1, getHoursFromMeridian(paramDouble), paramInt2) - 3.289D;
  }

  private static double getSunRightAscensionHours(double paramDouble)
  {
    double d = 57.295779513082323D * Math.atan(0.91764D * tanDeg(paramDouble));
    return (d + (90.0D * Math.floor(paramDouble / 90.0D) - 90.0D * Math.floor(d / 90.0D))) / 15.0D;
  }

  private static double getSunTrueLongitude(double paramDouble)
  {
    double d = 282.63400000000001D + (paramDouble + 1.916D * sinDeg(paramDouble) + 0.02D * sinDeg(2.0D * paramDouble));
    if (d >= 360.0D)
      d -= 360.0D;
    if (d < 0.0D)
      d += 360.0D;
    return d;
  }

  private static double getTimeUTC(int paramInt1, int paramInt2, int paramInt3, double paramDouble1, double paramDouble2, double paramDouble3, int paramInt4)
  {
    int i = getDayOfYear(paramInt1, paramInt2, paramInt3);
    double d1 = getSunTrueLongitude(getMeanAnomaly(i, paramDouble1, paramInt4));
    double d2 = getSunRightAscensionHours(d1);
    double d3 = getCosLocalHourAngle(d1, paramDouble2, paramDouble3);
    double d4;
    double d5;
    if (paramInt4 == 0)
    {
      d4 = 360.0D - acosDeg(d3);
      d5 = getLocalMeanTime(d4 / 15.0D, d2, getApproxTimeDays(i, getHoursFromMeridian(paramDouble1), paramInt4)) - getHoursFromMeridian(paramDouble1);
      label84: if (d5 < 0.0D)
        break label113;
    }
    while (true)
    {
      if (d5 < 24.0D)
      {
        return d5;
        d4 = acosDeg(d3);
        break;
        label113: d5 += 24.0D;
        break label84;
      }
      d5 -= 24.0D;
    }
  }

  private static double sinDeg(double paramDouble)
  {
    return Math.sin(3.141592653589793D * (2.0D * paramDouble) / 360.0D);
  }

  private static double tanDeg(double paramDouble)
  {
    return Math.tan(3.141592653589793D * (2.0D * paramDouble) / 360.0D);
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
      return getTimeUTC(paramAstronomicalCalendar.getCalendar().get(1), 1 + paramAstronomicalCalendar.getCalendar().get(2), paramAstronomicalCalendar.getCalendar().get(5), paramAstronomicalCalendar.getGeoLocation().getLongitude(), paramAstronomicalCalendar.getGeoLocation().getLatitude(), d, 0);
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
      return getTimeUTC(paramAstronomicalCalendar.getCalendar().get(1), 1 + paramAstronomicalCalendar.getCalendar().get(2), paramAstronomicalCalendar.getCalendar().get(5), paramAstronomicalCalendar.getGeoLocation().getLongitude(), paramAstronomicalCalendar.getGeoLocation().getLatitude(), d, 1);
      d = adjustZenith(paramDouble, 0.0D);
    }
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     net.sourceforge.zmanim.util.SunTimesCalculator
 * JD-Core Version:    0.6.0
 */