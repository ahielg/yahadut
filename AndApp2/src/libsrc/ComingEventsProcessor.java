package libsrc;

import java.util.Vector;

class ComingEventsProcessor extends EventProcessor
{
  ComingEventsProcessor(DateHolder paramDateHolder, LocationHolder paramLocationHolder)
  {
    super(paramDateHolder, paramLocationHolder);
  }

  private void insertEvent(byte[] paramArrayOfByte, DateHolder paramDateHolder, byte paramByte, boolean paramBoolean)
  {
    int i = paramDateHolder.dateAbsolute;
    int j = this.eventList.size();
    j--;
    if (j < 0);
    while (true)
    {
      EventData localEventData = new EventData(paramDateHolder, paramArrayOfByte, paramByte, paramBoolean);
      this.eventList.insertElementAt(localEventData, j + 1);
      return;
      if (((EventData)this.eventList.elementAt(j)).eventDate.dateAbsolute > i)
        break;
    }
  }

  boolean processEvent(byte[] paramArrayOfByte, byte paramByte1, byte paramByte2, boolean paramBoolean1, boolean paramBoolean2)
  {
    int i = paramArrayOfByte[2];
    int i2;
    if (paramBoolean2)
      if (this.targetDateHolder.hebMonthDurations.length < 13)
        i2 = 0;
    while (true)
    {
      return i2;
      i--;
      DateData localDateData = this.targetDateHolder.dateHebrew;
      int j = localDateData.year;
      int k = paramArrayOfByte[1];
      int m = localDateData.month;
      int n = i - m;
      if (n == 0)
        n = k - localDateData.day;
      if (n > 0)
      {
        if ((m < 6) && (i >= 6))
          j++;
        label104: if (paramArrayOfByte.length <= 5)
          break label162;
      }
      DateHolder localDateHolder;
      label162: for (int i1 = paramArrayOfByte[5]; ; i1 = 0)
      {
        localDateHolder = acceptEventDate(k, i, j, i1, paramBoolean1);
        if (localDateHolder != null)
          break label168;
        i2 = 0;
        break;
        if ((m >= 6) && (i < 6))
          break label104;
        j++;
        break label104;
      }
      label168: insertEvent(paramArrayOfByte, localDateHolder, paramByte2, paramBoolean2);
      i2 = 1;
    }
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     libsrc.ComingEventsProcessor
 * JD-Core Version:    0.6.0
 */