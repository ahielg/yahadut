package com.eladrich.dakatora;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

class CompassActivity$2
  implements SensorEventListener
{
  public void onAccuracyChanged(Sensor paramSensor, int paramInt)
  {
  }

  public void onSensorChanged(SensorEvent paramSensorEvent)
  {
    if (paramSensorEvent.sensor.getType() == 1)
      CompassActivity.access$3(this.this$0, paramSensorEvent.values);
    if (paramSensorEvent.sensor.getType() == 2)
      CompassActivity.access$4(this.this$0, paramSensorEvent.values);
    if ((CompassActivity.access$5(this.this$0) != null) && (CompassActivity.access$6(this.this$0) != null))
    {
      CompassActivity.access$7(this.this$0);
      this.this$0.updateCompass();
    }
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.eladrich.dakatora.CompassActivity.2
 * JD-Core Version:    0.6.0
 */