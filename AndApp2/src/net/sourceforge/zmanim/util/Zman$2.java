package net.sourceforge.zmanim.util;

import java.util.Comparator;

class Zman$2
  implements Comparator
{
  public int compare(Object paramObject1, Object paramObject2)
  {
    Zman localZman1 = (Zman)paramObject1;
    Zman localZman2 = (Zman)paramObject2;
    return localZman1.getZmanLabel().compareTo(localZman2.getZmanLabel());
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     net.sourceforge.zmanim.util.Zman.2
 * JD-Core Version:    0.6.0
 */