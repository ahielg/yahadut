package libsrc;

public final class MathE
{
  private static final double HalfPI = 1.570796326794897D;
  private static final int ITNUM = 100;
  private static final double PRECISION = 1.0E-020D;
  private static final double QuarterPI = 0.7853981633974483D;
  private static final double atanTinyArgThreshold = 0.4142135623730952D;

  public static final double acos(double paramDouble)
  {
    double d;
    if ((paramDouble > 1.0D) || (paramDouble < -1.0D))
      d = (0.0D / 0.0D);
    while (true)
    {
      return d;
      d = 1.570796326794897D - asin(paramDouble);
    }
  }

  public static final double asin(double paramDouble)
  {
    double d;
    if ((paramDouble > 1.0D) || (paramDouble < -1.0D))
      d = (0.0D / 0.0D);
    while (true)
    {
      return d;
      d = atan2(paramDouble, Math.sqrt(1.0D - paramDouble * paramDouble));
    }
  }

  public static final double atan(double paramDouble)
  {
    int i = 0;
    if (Double.isNaN(paramDouble))
      return paramDouble;
    label43: double d;
    if ((paramDouble > 1.0D) || (paramDouble < -1.0D))
    {
      if ((paramDouble == (1.0D / 0.0D)) || (paramDouble == (-1.0D / 0.0D)))
      {
        paramDouble = 0.0D;
        i = 1;
      }
    }
    else
    {
      d = atanSmallArg(paramDouble);
      if (i != 0)
      {
        if (paramDouble < 0.0D)
          break label78;
        d = 1.570796326794897D - d;
      }
    }
    while (true)
    {
      paramDouble = d;
      break;
      paramDouble = 1.0D / paramDouble;
      break label43;
      label78: d = -(1.570796326794897D + d);
    }
  }

  public static final double atan2(double paramDouble1, double paramDouble2)
  {
    int i = 0;
    if (Math.abs(paramDouble1) > Math.abs(paramDouble2))
    {
      double d2 = paramDouble1;
      paramDouble1 = paramDouble2;
      paramDouble2 = d2;
      i = 1;
    }
    double d1;
    if (paramDouble2 == 0.0D)
      d1 = (0.0D / 0.0D);
    while (true)
    {
      return d1;
      d1 = atanSmallArg(paramDouble1 / paramDouble2);
      if (i == 0)
        continue;
      if (d1 >= 0.0D)
      {
        d1 = 1.570796326794897D - d1;
        continue;
      }
      d1 = -(1.570796326794897D + d1);
    }
  }

  private static final double atanSmallArg(double paramDouble)
  {
    double d;
    if ((paramDouble <= 0.4142135623730952D) && (paramDouble >= -0.4142135623730952D))
      d = atanTinyArg(paramDouble);
    while (true)
    {
      return d;
      if (paramDouble > 0.0D)
      {
        d = 0.7853981633974483D - atanTinyArg((1.0D - paramDouble) / (1.0D + paramDouble));
        continue;
      }
      d = atanTinyArg((1.0D + paramDouble) / (1.0D - paramDouble)) - 0.7853981633974483D;
    }
  }

  private static final double atanTinyArg(double paramDouble)
  {
    double d1 = paramDouble;
    double d2 = paramDouble * paramDouble;
    double d3 = d1;
    int i = 0;
    label68: label109: label119: for (int j = 3; ; j += 2)
    {
      if (j > 100);
      double d5;
      double d4;
      do
      {
        d5 = d3;
        return d5;
        d1 *= d2;
        d4 = d1 / j;
      }
      while ((d4 > -1.0E-020D) && (d4 < 1.0E-020D));
      if (i != 0)
      {
        i = 0;
        if (i == 0)
          break label109;
        d3 -= d4;
      }
      while (true)
      {
        if ((!Double.isInfinite(d3)) && (!Double.isNaN(d3)))
          break label119;
        d5 = d3;
        break;
        i = 1;
        break label68;
        d3 += d4;
      }
    }
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     libsrc.MathE
 * JD-Core Version:    0.6.0
 */