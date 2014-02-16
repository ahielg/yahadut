package com.eladrich.dakatora;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

class CompassActivity$3
  implements SensorEventListener
{
  public void onAccuracyChanged(Sensor paramSensor, int paramInt)
  {
  }

  public void onSensorChanged(SensorEvent paramSensorEvent)
  {
    CompassActivity.access$8(this.this$0, paramSensorEvent.values[0]);
    float f = CompassActivity.access$9(this.this$0) - CompassActivity.access$10(this.this$0);
    if (f < 0.0F)
      (f + 360.0F);
    this.this$0.updateCompass();
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.eladrich.dakatora.CompassActivity.3
 * JD-Core Version:    0.6.0
 */