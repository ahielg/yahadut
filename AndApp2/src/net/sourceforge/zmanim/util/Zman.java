package net.sourceforge.zmanim.util;

import java.util.Comparator;
import java.util.Date;

public class Zman
{
  public static final Comparator DATE_ORDER = new Zman.1();
  public static final Comparator DURATION_ORDER;
  public static final Comparator NAME_ORDER = new Zman.2();
  private long duration;
  private Date zman;
  private Date zmanDescription;
  private String zmanLabel;

  static
  {
    DURATION_ORDER = new Zman.3();
  }

  public Zman(long paramLong, String paramString)
  {
    this.zmanLabel = paramString;
    this.duration = paramLong;
  }

  public Zman(Date paramDate, String paramString)
  {
    this.zmanLabel = paramString;
    this.zman = paramDate;
  }

  public long getDuration()
  {
    return this.duration;
  }

  public Date getZman()
  {
    return this.zman;
  }

  public Date getZmanDescription()
  {
    return this.zmanDescription;
  }

  public String getZmanLabel()
  {
    return this.zmanLabel;
  }

  public void setDuration(long paramLong)
  {
    this.duration = paramLong;
  }

  public void setZman(Date paramDate)
  {
    this.zman = paramDate;
  }

  public void setZmanDescription(Date paramDate)
  {
    this.zmanDescription = paramDate;
  }

  public void setZmanLabel(String paramString)
  {
    this.zmanLabel = paramString;
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     net.sourceforge.zmanim.util.Zman
 * JD-Core Version:    0.6.0
 */