package com.eladrich.dakatora;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

class CompassActivity$1
  implements SensorEventListener
{
  public void onAccuracyChanged(Sensor paramSensor, int paramInt)
  {
  }

  public void onSensorChanged(SensorEvent paramSensorEvent)
  {
    if (paramSensorEvent.sensor.getType() == 11)
      CompassActivity.access$0(this.this$0, paramSensorEvent.values);
    if (CompassActivity.access$1(this.this$0) != null)
    {
      CompassActivity.access$2(this.this$0);
      this.this$0.updateCompass();
    }
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.eladrich.dakatora.CompassActivity.1
 * JD-Core Version:    0.6.0
 */