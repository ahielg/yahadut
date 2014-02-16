package libsrc;

public class DateData
{
  public int day;
  public int month;
  public int year;

  public DateData()
  {
  }

  public DateData(int paramInt1, int paramInt2, int paramInt3)
  {
    this();
    set(paramInt1, paramInt2, paramInt3);
  }

  public DateData(DateData paramDateData)
  {
    this();
    set(paramDateData);
  }

  public int compare(DateData paramDateData)
  {
    int i = this.year - paramDateData.year;
    int k;
    if (i != 0)
      k = i;
    while (true)
    {
      return k;
      int j = this.month - paramDateData.month;
      if (j != 0)
      {
        k = j;
        continue;
      }
      k = this.day - paramDateData.day;
    }
  }

  public void set(int paramInt1, int paramInt2, int paramInt3)
  {
    this.day = paramInt1;
    this.month = paramInt2;
    this.year = paramInt3;
  }

  public void set(DateData paramDateData)
  {
    this.day = paramDateData.day;
    this.month = paramDateData.month;
    this.year = paramDateData.year;
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     libsrc.DateData
 * JD-Core Version:    0.6.0
 */