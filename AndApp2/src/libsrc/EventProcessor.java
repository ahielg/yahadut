package libsrc;

import java.util.Vector;

abstract class EventProcessor
{
  protected Vector eventList;
  protected final DateHolder targetDateHolder;
  protected final LocationHolder targetLocation;

  EventProcessor(DateHolder paramDateHolder, LocationHolder paramLocationHolder)
  {
    this.targetDateHolder = paramDateHolder;
    this.targetLocation = paramLocationHolder;
    this.eventList = new Vector();
  }

  protected DateHolder acceptEventDate(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean)
  {
    DateHolder localDateHolder = new DateHolder();
    if (!localDateHolder.acceptHebrewDate(new DateData(paramInt1, paramInt2, paramInt3), paramBoolean))
      localDateHolder = null;
    label163: 
    while (true)
    {
      return localDateHolder;
      int i = localDateHolder.dateAbsolute;
      int j = HebCalendar.getDayOfWeek(i);
      int k = 0;
      switch (paramInt4)
      {
      case 2:
      default:
      case 1:
      case 3:
      case 4:
      }
      while (true)
      {
        if (k == 0)
          break label163;
        localDateHolder.acceptAbsoluteDate(i + k);
        break;
        if (j != 6)
          continue;
        k = 1;
        continue;
        if (j < 5)
          continue;
        k = -(1 + (j - 5));
        continue;
        int m = j + 1;
        if ((m < 5) || (m > 6))
          continue;
        k = -(1 + (m - 5));
      }
    }
  }

  Vector getEventList()
  {
    return this.eventList;
  }

  abstract boolean processEvent(byte[] paramArrayOfByte, byte paramByte1, byte paramByte2, boolean paramBoolean1, boolean paramBoolean2);
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     libsrc.EventProcessor
 * JD-Core Version:    0.6.0
 */