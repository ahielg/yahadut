package net.sourceforge.zmanim.util;

import java.io.PrintStream;
import net.sourceforge.zmanim.AstronomicalCalendar;

public abstract class AstronomicalCalculator
  implements Cloneable
{
  private double refraction = 0.5666666666666667D;
  private double solarRadius = 0.2666666666666667D;

  public static AstronomicalCalculator getDefault()
  {
    return new SunTimesCalculator();
  }

  double adjustZenith(double paramDouble1, double paramDouble2)
  {
    if (paramDouble1 == 90.0D)
      paramDouble1 += getSolarRadius() + getRefraction() + getElevationAdjustment(paramDouble2);
    return paramDouble1;
  }

  public Object clone()
  {
    AstronomicalCalculator localAstronomicalCalculator = null;
    try
    {
      localAstronomicalCalculator = (AstronomicalCalculator)super.clone();
      return localAstronomicalCalculator;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      while (true)
        System.out.print("Required by the compiler. Should never be reached since we implement clone()");
    }
  }

  public abstract String getCalculatorName();

  double getElevationAdjustment(double paramDouble)
  {
    return Math.toDegrees(Math.acos(6356.8999999999996D / (6356.8999999999996D + paramDouble / 1000.0D)));
  }

  double getRefraction()
  {
    return this.refraction;
  }

  double getSolarRadius()
  {
    return this.solarRadius;
  }

  public abstract double getUTCSunrise(AstronomicalCalendar paramAstronomicalCalendar, double paramDouble, boolean paramBoolean);

  public abstract double getUTCSunset(AstronomicalCalendar paramAstronomicalCalendar, double paramDouble, boolean paramBoolean);

  public void setRefraction(double paramDouble)
  {
    this.refraction = paramDouble;
  }

  public void setSolarRadius(double paramDouble)
  {
    this.solarRadius = paramDouble;
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     net.sourceforge.zmanim.util.AstronomicalCalculator
 * JD-Core Version:    0.6.0
 */