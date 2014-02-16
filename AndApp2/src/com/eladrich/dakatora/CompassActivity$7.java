package com.eladrich.dakatora;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Handler;
import java.util.List;

class CompassActivity$7
  implements Runnable
{
  Handler handler = new Handler();

  public void run()
  {
    Geocoder localGeocoder = new Geocoder(this.this$0.getApplicationContext());
    try
    {
      List localList1 = localGeocoder.getFromLocation(this.this$0.bestLocation.getLatitude(), this.this$0.bestLocation.getLongitude(), 1);
      if (localList1.size() > 0)
      {
        localAddress1 = (Address)localList1.get(0);
        List localList2 = localGeocoder.getFromLocation(31.777041000000001D, 35.224614000000003D, 1);
        if (localList2.size() > 0)
        {
          Address localAddress2 = (Address)localList2.get(0);
          if (localAddress1.getLocality() != null)
          {
            if (localAddress1.getLocality().equals(localAddress2.getLocality()))
              CompassActivity.access$12(this.this$0, localAddress1.getAddressLine(0));
            while (true)
            {
              this.handler.post(this.this$0.updatePlaceTV);
              return;
              CompassActivity.access$12(this.this$0, localAddress1.getLocality());
            }
          }
        }
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        Address localAddress1;
        CompassActivity.access$12(this.this$0, null);
        continue;
        CompassActivity.access$12(this.this$0, localAddress1.getAdminArea());
        continue;
        CompassActivity.access$12(this.this$0, null);
        continue;
        CompassActivity.access$12(this.this$0, null);
      }
    }
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.eladrich.dakatora.CompassActivity.7
 * JD-Core Version:    0.6.0
 */