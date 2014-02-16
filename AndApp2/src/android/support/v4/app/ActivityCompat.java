package android.support.v4.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Build.VERSION;

public class ActivityCompat
{
  public static boolean invalidateOptionsMenu(Activity paramActivity)
  {
    if (Build.VERSION.SDK_INT >= 11)
      ActivityCompatHoneycomb.invalidateOptionsMenu(paramActivity);
    for (int i = 1; ; i = 0)
      return i;
  }

  public static boolean startActivities(Activity paramActivity, Intent[] paramArrayOfIntent)
  {
    if (Build.VERSION.SDK_INT >= 11)
      ActivityCompatHoneycomb.startActivities(paramActivity, paramArrayOfIntent);
    for (int i = 1; ; i = 0)
      return i;
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     android.support.v4.app.ActivityCompat
 * JD-Core Version:    0.6.0
 */