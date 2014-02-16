package com.eladrich.dakatora;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

class CompassActivity$6
  implements LocationListener
{
  public void onLocationChanged(Location paramLocation)
  {
    if (paramLocation != null)
    {
      this.this$0.netLocation = paramLocation;
      if (this.this$0.isBetterLocation(this.this$0.netLocation, this.this$0.bestLocation))
      {
        this.this$0.bestLocation = this.this$0.netLocation;
        this.this$0.bestChanged();
      }
    }
  }

  public void onProviderDisabled(String paramString)
  {
  }

  public void onProviderEnabled(String paramString)
  {
  }

  public void onStatusChanged(String paramString, int paramInt, Bundle paramBundle)
  {
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.eladrich.dakatora.CompassActivity.6
 * JD-Core Version:    0.6.0
 */