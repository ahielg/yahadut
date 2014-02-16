package com.eladrich.dakatora;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import java.util.Calendar;
import java.util.List;

public class CompassActivity extends DashboardActivity
{
  static final int MAX_TIME = 3600000;
  private static final int TWO_MINUTES = 120000;
  static final double jerusalemLat = 31.777041000000001D;
  static final double jerusalemLon = 35.224614000000003D;
  private static SensorManager mySensorManager;
  static final double templeLat = 31.777999999999999D;
  static final double templeLong = 35.235399999999998D;
  private float[] aValues;
  private int apiLevel;
  Location bestLocation;
  private final SensorEventListener combineSensorEventListener = new CompassActivity.2(this);
  private float compassBearing = -361.0F;
  Location gpsLocation;
  LocationListener gpsLocationListener;
  private SensorEventListener legacySensorEventListener = new CompassActivity.3(this);
  LocationManager lm;
  private String locality;
  private LinearLayout locationLayout;
  private float[] mValues;
  private CompassView myCompassView;
  Location netLocation;
  LocationListener netLocationListener;
  LocationListener newLocationListener;
  private float prev_value;
  private float[] rValues;
  private int rotation;
  boolean runCompass;
  private SensorManager sensorManager;
  private float templeBearing = -361.0F;
  Runnable updatePlaceTV = new CompassActivity.4(this);
  private boolean updateTfila = false;
  private final SensorEventListener vectorSensorEventListener = new CompassActivity.1(this);

  private boolean isSameProvider(String paramString1, String paramString2)
  {
    boolean bool;
    if (paramString1 == null)
      if (paramString2 == null)
        bool = true;
    while (true)
    {
      return bool;
      bool = false;
      continue;
      bool = paramString1.equals(paramString2);
    }
  }

  private void updateBearingFromSensors()
  {
    float[] arrayOfFloat1 = new float[3];
    float[] arrayOfFloat2 = new float[9];
    float[] arrayOfFloat3 = new float[9];
    int i;
    int j;
    float f1;
    float f2;
    if ((!zeroArray(this.aValues)) && (!zeroArray(this.mValues)))
    {
      SensorManager.getRotationMatrix(arrayOfFloat2, null, this.aValues, this.mValues);
      i = 1;
      j = 2;
      switch (this.rotation)
      {
      default:
        SensorManager.remapCoordinateSystem(arrayOfFloat2, i, j, arrayOfFloat3);
        SensorManager.getOrientation(arrayOfFloat3, arrayOfFloat1);
        arrayOfFloat1[0] = (float)Math.toDegrees(arrayOfFloat1[0]);
        arrayOfFloat1[1] = (float)Math.toDegrees(arrayOfFloat1[1]);
        arrayOfFloat1[2] = (float)Math.toDegrees(arrayOfFloat1[2]);
        f1 = arrayOfFloat1[0];
        f2 = f1 - this.prev_value;
        if (f1 != 0.0D)
        {
          if (this.prev_value == 0.0D)
            break;
          if (Math.abs(f2) <= 180.0F)
            break label301;
          if (f2 >= 0.0F)
            break label264;
          f1 = (float)(this.prev_value + 0.1D * (360.0F + f2));
          if (f1 <= 180.0F)
            break;
          f1 -= 360.0F;
        }
      case 1:
      case 2:
      case 3:
      }
    }
    while (true)
    {
      this.compassBearing = f1;
      this.prev_value = f1;
      return;
      i = 2;
      j = 129;
      break;
      j = 130;
      break;
      i = 130;
      j = 1;
      break;
      label264: f1 = (float)(this.prev_value + 0.1D * (f2 - 360.0F));
      if (f1 >= -180.0F)
        continue;
      f1 += 360.0F;
      continue;
      label301: f1 = (float)(this.prev_value + 0.1D * f2);
    }
  }

  @TargetApi(9)
  private void updateBearingFromVector()
  {
    float[] arrayOfFloat1 = new float[3];
    float[] arrayOfFloat2 = new float[9];
    float[] arrayOfFloat3 = new float[9];
    SensorManager.getRotationMatrixFromVector(arrayOfFloat2, this.rValues);
    int i = 1;
    int j = 2;
    switch (this.rotation)
    {
    default:
    case 1:
    case 2:
    case 3:
    }
    while (true)
    {
      SensorManager.remapCoordinateSystem(arrayOfFloat2, i, j, arrayOfFloat3);
      SensorManager.getOrientation(arrayOfFloat3, arrayOfFloat1);
      arrayOfFloat1[0] = (float)Math.toDegrees(arrayOfFloat1[0]);
      arrayOfFloat1[1] = (float)Math.toDegrees(arrayOfFloat1[1]);
      arrayOfFloat1[2] = (float)Math.toDegrees(arrayOfFloat1[2]);
      this.compassBearing = arrayOfFloat1[0];
      return;
      i = 2;
      j = 129;
      continue;
      j = 130;
      continue;
      i = 130;
      j = 1;
    }
  }

  public void bestChanged()
  {
    int i = 0;
    if (this.bestLocation != null)
      if (this.bestLocation.getAccuracy() < getMinAccuracy())
      {
        this.templeBearing = (float)getBearing();
        ((TextView)findViewById(2131099704)).setText(getString(2131427435));
        updatePlace();
        TextView localTextView2 = (TextView)findViewById(2131099701);
        this.updateTfila = true;
        updateCompass();
        localTextView2.setText(getString(2131427437));
      }
    while (true)
    {
      if (i != 0)
      {
        this.templeBearing = 0.0F;
        ((TextView)findViewById(2131099704)).setText(getString(2131427436));
        TextView localTextView1 = (TextView)findViewById(2131099701);
        this.updateTfila = false;
        updateCompass();
        localTextView1.setText(getString(2131427438));
      }
      return;
      i = 1;
      continue;
      i = 1;
    }
  }

  public double getBearing()
  {
    double d1 = Math.toRadians(this.bestLocation.getLatitude());
    double d2 = Math.toRadians(31.777999999999999D);
    double d3 = Math.toRadians(35.235399999999998D - this.bestLocation.getLongitude());
    double d4 = Math.atan2(Math.sin(d3) * Math.cos(d2), Math.cos(d1) * Math.sin(d2) - Math.sin(d1) * Math.cos(d2) * Math.cos(d3));
    Log.e("compassOrigin", (360.0D + Math.toDegrees(d4)) % 360.0D);
    return (360.0D + Math.toDegrees(d4)) % 360.0D;
  }

  public void getDirection()
  {
    if (this.apiLevel >= 9)
    {
      Sensor localSensor3 = this.sensorManager.getDefaultSensor(11);
      if (localSensor3 != null)
      {
        this.sensorManager.registerListener(this.vectorSensorEventListener, localSensor3, 2);
        this.runCompass = true;
      }
    }
    while (true)
    {
      return;
      Sensor localSensor4 = this.sensorManager.getDefaultSensor(1);
      Sensor localSensor5 = this.sensorManager.getDefaultSensor(2);
      if ((localSensor4 != null) && (localSensor5 != null))
      {
        this.sensorManager.registerListener(this.combineSensorEventListener, localSensor4, 2);
        this.sensorManager.registerListener(this.combineSensorEventListener, localSensor5, 2);
        this.runCompass = true;
        continue;
      }
      ((TextView)findViewById(2131099701)).setText("לא ניתן להשתמש במצפן המכשיר");
      this.myCompassView.setVisibility(4);
      this.runCompass = false;
      continue;
      if (this.apiLevel >= 8)
      {
        Sensor localSensor1 = this.sensorManager.getDefaultSensor(1);
        Sensor localSensor2 = this.sensorManager.getDefaultSensor(2);
        if ((localSensor1 != null) && (localSensor2 != null))
        {
          this.sensorManager.registerListener(this.combineSensorEventListener, localSensor1, 2);
          this.sensorManager.registerListener(this.combineSensorEventListener, localSensor2, 2);
          this.runCompass = true;
          continue;
        }
        ((TextView)findViewById(2131099701)).setText("לא ניתן להשתמש במצפן המכשיר");
        this.myCompassView.setVisibility(4);
        this.runCompass = false;
        continue;
      }
      mySensorManager = (SensorManager)getSystemService("sensor");
      List localList = mySensorManager.getSensorList(3);
      if (localList.size() > 0)
      {
        mySensorManager.registerListener(this.legacySensorEventListener, (Sensor)localList.get(0), 2);
        continue;
      }
      ((TextView)findViewById(2131099701)).setText("לא ניתן להשתמש במצפן המכשיר");
      this.myCompassView.setVisibility(4);
      this.runCompass = false;
    }
  }

  public double getDistance()
  {
    double d1 = Math.toRadians(31.777999999999999D - this.bestLocation.getLatitude());
    double d2 = Math.toRadians(35.235399999999998D - this.bestLocation.getLongitude());
    double d3 = Math.toRadians(this.bestLocation.getLatitude());
    double d4 = Math.toRadians(31.777999999999999D);
    double d5 = Math.sin(d1 / 2.0D) * Math.sin(d1 / 2.0D) + Math.sin(d2 / 2.0D) * Math.sin(d2 / 2.0D) * Math.cos(d3) * Math.cos(d4);
    return 6371.0D * (2.0D * Math.atan2(Math.sqrt(d5), Math.sqrt(1.0D - d5)));
  }

  public void getLocation()
  {
    this.lm = ((LocationManager)getSystemService("location"));
    this.gpsLocationListener = new CompassActivity.5(this);
    this.netLocationListener = new CompassActivity.6(this);
    Calendar localCalendar = Calendar.getInstance();
    this.gpsLocation = this.lm.getLastKnownLocation("gps");
    this.netLocation = this.lm.getLastKnownLocation("network");
    if ((this.netLocation != null) && (localCalendar.getTimeInMillis() - this.netLocation.getTime() > 3600000L))
      this.netLocation = null;
    if ((this.gpsLocation != null) && (localCalendar.getTimeInMillis() - this.gpsLocation.getTime() > 3600000L))
      this.gpsLocation = null;
    if (this.netLocation != null)
      this.bestLocation = this.netLocation;
    while (true)
    {
      bestChanged();
      this.lm.requestLocationUpdates("gps", 1L, 0.0F, this.gpsLocationListener);
      this.lm.requestLocationUpdates("network", 1L, 0.0F, this.netLocationListener);
      return;
      if (this.gpsLocation != null)
      {
        this.bestLocation = this.gpsLocation;
        continue;
      }
      this.bestLocation = null;
    }
  }

  public double getMinAccuracy()
  {
    return 1000.0D * (getDistance() * Math.sin(Math.toRadians(10.0D)));
  }

  protected boolean isBetterLocation(Location paramLocation1, Location paramLocation2)
  {
    int i3;
    if (paramLocation2 == null)
      i3 = 1;
    while (true)
    {
      return i3;
      if (paramLocation1 == null)
      {
        i3 = 0;
        continue;
      }
      long l = paramLocation1.getTime() - paramLocation2.getTime();
      int i;
      label41: int j;
      if (l > 120000L)
      {
        i = 1;
        if (l >= -120000L)
          break label78;
        j = 1;
        label52: if (l <= 0L)
          break label84;
      }
      label78: label84: for (int k = 1; ; k = 0)
      {
        if (i == 0)
          break label90;
        i3 = 1;
        break;
        i = 0;
        break label41;
        j = 0;
        break label52;
      }
      label90: if (j != 0)
      {
        i3 = 0;
        continue;
      }
      int m = (int)(paramLocation1.getAccuracy() - paramLocation2.getAccuracy());
      int n;
      label121: int i1;
      if (m > 0)
      {
        n = 1;
        if (m >= 0)
          break label171;
        i1 = 1;
        label129: if (m <= 200)
          break label177;
      }
      boolean bool;
      label171: label177: for (int i2 = 1; ; i2 = 0)
      {
        bool = isSameProvider(paramLocation1.getProvider(), paramLocation2.getProvider());
        if (i1 == 0)
          break label183;
        i3 = 1;
        break;
        n = 0;
        break label121;
        i1 = 0;
        break label129;
      }
      label183: if ((k != 0) && (n == 0))
      {
        i3 = 1;
        continue;
      }
      if ((k != 0) && (i2 == 0) && (bool))
      {
        i3 = 1;
        continue;
      }
      i3 = 0;
    }
  }

  @SuppressLint({"NewApi"})
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903062);
    this.myCompassView = ((CompassView)findViewById(2131099702));
    this.locationLayout = ((LinearLayout)findViewById(2131099703));
    this.apiLevel = Integer.valueOf(Build.VERSION.SDK_INT).intValue();
    if (this.apiLevel >= 8)
    {
      this.sensorManager = ((SensorManager)getSystemService("sensor"));
      this.rotation = ((WindowManager)getSystemService("window")).getDefaultDisplay().getRotation();
      this.prev_value = 0.0F;
    }
    for (this.runCompass = true; ; this.runCompass = true)
    {
      SharedPreferences localSharedPreferences = getSharedPreferences("daka_prefs", 0);
      if (localSharedPreferences.getBoolean("daka_show_again", true))
      {
        startActivity(new Intent(this, CompassInfoActivity.class));
        SharedPreferences.Editor localEditor = localSharedPreferences.edit();
        localEditor.putBoolean("daka_show_again", false);
        localEditor.commit();
      }
      return;
    }
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getSupportMenuInflater().inflate(2131623936, paramMenu);
    return true;
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    new String[2][0] = "";
    switch (paramMenuItem.getItemId())
    {
    default:
    case 2131099780:
    case 16908332:
    }
    while (true)
    {
      for (boolean bool = super.onOptionsItemSelected(paramMenuItem); ; bool = true)
      {
        return bool;
        startActivity(new Intent(this, CompassInfoActivity.class));
      }
      Intent localIntent = new Intent(this, HomeActivity.class);
      localIntent.setFlags(67108864);
      startActivity(localIntent);
    }
  }

  public void onPause()
  {
    if (this.runCompass)
    {
      if (this.lm != null)
      {
        this.lm.removeUpdates(this.gpsLocationListener);
        this.lm.removeUpdates(this.netLocationListener);
      }
      if (this.apiLevel < 8)
        break label72;
      this.sensorManager.unregisterListener(this.combineSensorEventListener);
      this.sensorManager.unregisterListener(this.vectorSensorEventListener);
    }
    while (true)
    {
      super.onStop();
      return;
      label72: mySensorManager.unregisterListener(this.legacySensorEventListener);
    }
  }

  protected void onResume()
  {
    if (this.runCompass)
    {
      getLocation();
      getDirection();
    }
    super.onResume();
  }

  public void updateCompass()
  {
    float f;
    if (this.compassBearing != -361.0F)
    {
      f = this.compassBearing - this.templeBearing;
      if ((f <= -10.0F) || (f >= 10.0F))
        break label89;
      this.myCompassView.highlightBase(true);
      if (this.templeBearing == -361.0F)
        break label100;
      this.myCompassView.updateTfila(this.updateTfila);
    }
    while (true)
    {
      if (f < 0.0F)
        f += 360.0F;
      this.myCompassView.updateDirection(f, this.compassBearing);
      return;
      label89: this.myCompassView.highlightBase(false);
      break;
      label100: this.myCompassView.updateTfila(false);
    }
  }

  public void updatePlace()
  {
    new Thread(new CompassActivity.7(this)).start();
  }

  public boolean zeroArray(float[] paramArrayOfFloat)
  {
    for (int i = 0; ; i++)
    {
      if (i >= paramArrayOfFloat.length);
      for (int j = 0; ; j = 1)
      {
        return j;
        if (paramArrayOfFloat[i] != 0.0D)
          break;
      }
    }
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.eladrich.dakatora.CompassActivity
 * JD-Core Version:    0.6.0
 */