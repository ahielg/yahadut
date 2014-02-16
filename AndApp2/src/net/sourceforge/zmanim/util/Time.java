package net.sourceforge.zmanim.util;

public class Time
{
  private static final int HOUR_MILLIS = 3600000;
  private static final int MINUTE_MILLIS = 60000;
  private static final int SECOND_MILLIS = 1000;
  private int hours = 0;
  private boolean isNegative = false;
  private int milliseconds = 0;
  private int minutes = 0;
  private int seconds = 0;

  public Time(double paramDouble)
  {
    this((int)paramDouble);
  }

  public Time(int paramInt)
  {
    if (paramInt < 0)
    {
      this.isNegative = true;
      paramInt = Math.abs(paramInt);
    }
    this.hours = (paramInt / 3600000);
    int i = paramInt - 3600000 * this.hours;
    this.minutes = (i / 60000);
    int j = i - 60000 * this.minutes;
    this.seconds = (j / 1000);
    this.milliseconds = (j - 1000 * this.seconds);
  }

  public Time(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.hours = paramInt1;
    this.minutes = paramInt2;
    this.seconds = paramInt3;
    this.milliseconds = paramInt4;
  }

  public int getHours()
  {
    return this.hours;
  }

  public int getMilliseconds()
  {
    return this.milliseconds;
  }

  public int getMinutes()
  {
    return this.minutes;
  }

  public int getSeconds()
  {
    return this.seconds;
  }

  public double getTime()
  {
    return 3600000 * this.hours + 60000 * this.minutes + 1000 * this.seconds + this.milliseconds;
  }

  public boolean isNegative()
  {
    return this.isNegative;
  }

  public void setHours(int paramInt)
  {
    this.hours = paramInt;
  }

  public void setIsNegative(boolean paramBoolean)
  {
    this.isNegative = paramBoolean;
  }

  public void setMilliseconds(int paramInt)
  {
    this.milliseconds = paramInt;
  }

  public void setMinutes(int paramInt)
  {
    this.minutes = paramInt;
  }

  public void setSeconds(int paramInt)
  {
    this.seconds = paramInt;
  }

  public String toString()
  {
    return new ZmanimFormatter().format(this);
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     net.sourceforge.zmanim.util.Time
 * JD-Core Version:    0.6.0
 */