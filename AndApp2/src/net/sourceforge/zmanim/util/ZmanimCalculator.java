package net.sourceforge.zmanim.util;

import java.util.Calendar;
import net.sourceforge.zmanim.AstronomicalCalendar;

public class ZmanimCalculator extends AstronomicalCalculator
{
  private String calculatorName = "US Naval Almanac Algorithm";

  public String getCalculatorName()
  {
    return this.calculatorName;
  }

  public double getUTCSunrise(AstronomicalCalendar paramAstronomicalCalendar, double paramDouble, boolean paramBoolean)
  {
    double d1;
    double d5;
    label105: label112: double d6;
    label141: label148: double d10;
    if (paramBoolean)
    {
      d1 = adjustZenith(paramDouble, paramAstronomicalCalendar.getGeoLocation().getElevation());
      double d2 = paramAstronomicalCalendar.getGeoLocation().getLongitude() / 15.0D;
      double d3 = paramAstronomicalCalendar.getCalendar().get(6) + (6.0D - d2) / 24.0D;
      double d4 = 0.9856D * d3 - 3.289D;
      d5 = 282.63400000000001D + (d4 + 1.916D * Math.sin(Math.toRadians(d4)) + 0.02D * Math.sin(Math.toRadians(2.0D * d4)));
      if (d5 < 0.0D)
        break label322;
      if (d5 >= 360.0D)
        break label333;
      d6 = Math.toDegrees(Math.atan(0.91764D * Math.tan(Math.toRadians(d5))));
      if (d6 < 0.0D)
        break label344;
      if (d6 >= 360.0D)
        break label355;
      double d7 = (d6 + (90.0D * Math.floor(d5 / 90.0D) - 90.0D * Math.floor(d6 / 90.0D))) / 15.0D;
      double d8 = 0.39782D * Math.sin(Math.toRadians(d5));
      double d9 = Math.cos(Math.asin(d8));
      d10 = d7 + (360.0D - Math.toDegrees(Math.acos((Math.cos(Math.toRadians(d1)) - d8 * Math.sin(Math.toRadians(paramAstronomicalCalendar.getGeoLocation().getLatitude()))) / (d9 * Math.cos(Math.toRadians(paramAstronomicalCalendar.getGeoLocation().getLatitude())))))) / 15.0D - 0.06571000000000001D * d3 - 6.622D - d2;
      label292: if (d10 < 0.0D)
        break label366;
    }
    while (true)
    {
      if (d10 < 24.0D)
      {
        return d10;
        d1 = adjustZenith(paramDouble, 0.0D);
        break;
        label322: d5 += 360.0D;
        break label105;
        label333: d5 -= 360.0D;
        break label112;
        label344: d6 += 360.0D;
        break label141;
        label355: d6 -= 360.0D;
        break label148;
        label366: d10 += 24.0D;
        break label292;
      }
      d10 -= 24.0D;
    }
  }

  public double getUTCSunset(AstronomicalCalendar paramAstronomicalCalendar, double paramDouble, boolean paramBoolean)
  {
    double d1;
    double d5;
    label109: label116: double d6;
    label145: label152: double d10;
    if (paramBoolean)
    {
      d1 = adjustZenith(paramDouble, paramAstronomicalCalendar.getGeoLocation().getElevation());
      int i = paramAstronomicalCalendar.getCalendar().get(6);
      double d2 = paramAstronomicalCalendar.getGeoLocation().getLongitude() / 15.0D;
      double d3 = i + (18.0D - d2) / 24.0D;
      double d4 = 0.9856D * d3 - 3.289D;
      d5 = 282.63400000000001D + (d4 + 1.916D * Math.sin(Math.toRadians(d4)) + 0.02D * Math.sin(Math.toRadians(2.0D * d4)));
      if (d5 < 0.0D)
        break label322;
      if (d5 >= 360.0D)
        break label333;
      d6 = Math.toDegrees(Math.atan(0.91764D * Math.tan(Math.toRadians(d5))));
      if (d6 < 0.0D)
        break label344;
      if (d6 >= 360.0D)
        break label355;
      double d7 = (d6 + (90.0D * Math.floor(d5 / 90.0D) - 90.0D * Math.floor(d6 / 90.0D))) / 15.0D;
      double d8 = 0.39782D * Math.sin(Math.toRadians(d5));
      double d9 = Math.cos(Math.asin(d8));
      d10 = d7 + Math.toDegrees(Math.acos((Math.cos(Math.toRadians(d1)) - d8 * Math.sin(Math.toRadians(paramAstronomicalCalendar.getGeoLocation().getLatitude()))) / (d9 * Math.cos(Math.toRadians(paramAstronomicalCalendar.getGeoLocation().getLatitude()))))) / 15.0D - 0.06571000000000001D * d3 - 6.622D - d2;
      label292: if (d10 < 0.0D)
        break label366;
    }
    while (true)
    {
      if (d10 < 24.0D)
      {
        return d10;
        d1 = adjustZenith(paramDouble, 0.0D);
        break;
        label322: d5 += 360.0D;
        break label109;
        label333: d5 -= 360.0D;
        break label116;
        label344: d6 += 360.0D;
        break label145;
        label355: d6 -= 360.0D;
        break label152;
        label366: d10 += 24.0D;
        break label292;
      }
      d10 -= 24.0D;
    }
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     net.sourceforge.zmanim.util.ZmanimCalculator
 * JD-Core Version:    0.6.0
 */