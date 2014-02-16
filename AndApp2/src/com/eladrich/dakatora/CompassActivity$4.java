package com.eladrich.dakatora;

import android.location.Location;
import android.widget.TextView;

class CompassActivity$4
  implements Runnable
{
  public void run()
  {
    TextView localTextView = (TextView)this.this$0.findViewById(2131099704);
    if (CompassActivity.access$11(this.this$0) != null)
      localTextView.setText(CompassActivity.access$11(this.this$0));
    while (true)
    {
      return;
      localTextView.setText(this.this$0.bestLocation.getLatitude() + "°," + this.this$0.bestLocation.getLongitude() + "°");
    }
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.eladrich.dakatora.CompassActivity.4
 * JD-Core Version:    0.6.0
 */