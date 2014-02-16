package net.sourceforge.zmanim.util;

import java.util.Comparator;

class Zman$3
  implements Comparator
{
  public int compare(Object paramObject1, Object paramObject2)
  {
    Zman localZman1 = (Zman)paramObject1;
    Zman localZman2 = (Zman)paramObject2;
    int i;
    if (localZman1.getDuration() == localZman2.getDuration())
      i = 0;
    while (true)
    {
      return i;
      if (localZman1.getDuration() > localZman2.getDuration())
      {
        i = 1;
        continue;
      }
      i = -1;
    }
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     net.sourceforge.zmanim.util.Zman.3
 * JD-Core Version:    0.6.0
 */