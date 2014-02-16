package com.eladrich.dakatora;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import java.util.Calendar;
import java.util.List;

public class ZmanimActivity extends DashboardActivity
{
  static final int MAX_TIME = 3600000;
  private static final int TWO_MINUTES = 120000;
  Location bestLocation;
  String dateString;
  SqlDataClass dtClass;
  String eventString;
  Runnable finishReminderThread = new Runnable()
  {
    public void run()
    {
      ZmanimActivity.this.applyReminder();
    }
  };
  Runnable finishUpdateThread = new Runnable()
  {
    public void run()
    {
      ZmanimActivity.this.applyUpdates();
    }
  };
  Location gpsLocation;
  LocationListener gpsLocationListener;
  double latDouble;
  LocationManager lm;
  double longDouble;
  Location netLocation;
  LocationListener netLocationListener;
  String parashaString;
  String reminder;
  SharedPreferences settings;
  Runnable showAlert = new Runnable()
  {
    public void run()
    {
      Toast.makeText(ZmanimActivity.this.getApplicationContext(), "לא ניתן לקבל את מיקומך, הזמנים יוצגו לפי העיר שבחרת", 1).show();
    }
  };
  String[][] timesArray;
  String timesString;
  String titleString;

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

  public void applyLoc()
  {
    if (this.bestLocation != null)
    {
      this.latDouble = this.bestLocation.getLatitude();
      this.longDouble = this.bestLocation.getLongitude();
    }
  }

  public void applyReminder()
  {
    TextView localTextView = (TextView)findViewById(2131099742);
    if ((!this.reminder.equals("not found")) && (!this.reminder.equals("")))
    {
      localTextView.setText(this.reminder);
      localTextView.setVisibility(0);
    }
    while (true)
    {
      this.reminder = "";
      return;
      localTextView.setVisibility(8);
    }
  }

  public void applyUpdates()
  {
    getSupportActionBar().setTitle(this.titleString);
    TextView localTextView = (TextView)findViewById(2131099741);
    String str = this.dateString;
    if (!this.eventString.equals(""))
    {
      str = str + " - " + this.eventString;
      if (!this.parashaString.equals("פרשת "))
        str = str + "\n" + this.parashaString;
    }
    while (true)
    {
      localTextView.setText(str);
      createTimesViews(this.timesArray);
      return;
      if (this.parashaString.equals("פרשת "))
        continue;
      str = str + " - " + this.parashaString;
    }
  }

  public void createTimesViews(String[][] paramArrayOfString)
  {
    TableLayout localTableLayout = (TableLayout)findViewById(2131099743);
    localTableLayout.removeAllViews();
    for (int i = 0; ; i++)
    {
      if (i >= paramArrayOfString[0].length)
        return;
      TextView localTextView1 = new TextView(this);
      TableRow.LayoutParams localLayoutParams1 = new TableRow.LayoutParams(-1, -2);
      localLayoutParams1.setMargins(4, 0, 0, 0);
      localTextView1.setLayoutParams(localLayoutParams1);
      localTextView1.setTextColor(-1);
      localTextView1.setTextAppearance(this, 2131492979);
      localTextView1.setTextSize(getResources().getDimension(2131296269));
      localTextView1.setGravity(5);
      localTextView1.setPadding(8, 0, 0, 0);
      TextView localTextView2 = new TextView(this);
      TableRow.LayoutParams localLayoutParams2 = new TableRow.LayoutParams(-1, -2);
      localLayoutParams2.setMargins(4, 0, 0, 0);
      localTextView2.setLayoutParams(localLayoutParams2);
      localTextView2.setTextColor(-1);
      localTextView2.setTextSize(getResources().getDimension(2131296269));
      localTextView2.setGravity(3);
      localTextView2.setPadding(0, 8, 0, 0);
      localTextView1.setText(paramArrayOfString[0][i]);
      localTextView2.setText(paramArrayOfString[1][i]);
      TableRow localTableRow = new TableRow(this);
      localTableRow.setLayoutParams(new TableLayout.LayoutParams(-2, -2));
      localTableRow.addView(localTextView2, 0);
      localTableRow.addView(localTextView1, 1);
      localTableLayout.addView(localTableRow, i);
    }
  }

  public void getLocation()
  {
    this.lm = ((LocationManager)getSystemService("location"));
    this.gpsLocationListener = new LocationListener()
    {
      public void onLocationChanged(Location paramLocation)
      {
        if (paramLocation != null)
        {
          ZmanimActivity.this.gpsLocation = paramLocation;
          if (ZmanimActivity.this.isBetterLocation(ZmanimActivity.this.gpsLocation, ZmanimActivity.this.bestLocation))
          {
            ZmanimActivity.this.bestLocation = ZmanimActivity.this.gpsLocation;
            ZmanimActivity.this.applyLoc();
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
    };
    this.netLocationListener = new LocationListener()
    {
      public void onLocationChanged(Location paramLocation)
      {
        if (paramLocation != null)
        {
          ZmanimActivity.this.netLocation = paramLocation;
          if (ZmanimActivity.this.isBetterLocation(ZmanimActivity.this.netLocation, ZmanimActivity.this.bestLocation))
          {
            ZmanimActivity.this.bestLocation = ZmanimActivity.this.netLocation;
            ZmanimActivity.this.applyLoc();
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
    };
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
      applyLoc();
      updateViewsUsingThread();
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

  public void getReminder()
  {
    new getReminderAsyncTask(null).execute(new String[0]);
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

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903071);
    getReminder();
    this.settings = PreferenceManager.getDefaultSharedPreferences(this);
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getSupportMenuInflater().inflate(2131623940, paramMenu);
    return true;
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default:
    case 2131099784:
    case 16908332:
    }
    while (true)
    {
      for (boolean bool = super.onOptionsItemSelected(paramMenuItem); ; bool = true)
      {
        return bool;
        startActivity(new Intent(this, ZmanimInfoActivity.class));
      }
      Intent localIntent = new Intent(this, HomeActivity.class);
      localIntent.setFlags(67108864);
      startActivity(localIntent);
    }
  }

  protected void onPause()
  {
    if (this.lm != null)
    {
      this.lm.removeUpdates(this.gpsLocationListener);
      this.lm.removeUpdates(this.netLocationListener);
    }
    super.onPause();
  }

  protected void onResume()
  {
    if (this.settings.getBoolean("daka_prefs_gps_checked", true))
      getLocation();
    while (true)
    {
      super.onResume();
      return;
      updateViewsUsingThread();
    }
  }

  public void updateViewsUsingThread()
  {
    new Thread(new Runnable()
    {
      Handler handler = new Handler();

      public void run()
      {
        int i = 0;
        if ((ZmanimActivity.this.settings.getBoolean("daka_prefs_gps_checked", true)) && (ZmanimActivity.this.latDouble != 0.0D) && (ZmanimActivity.this.longDouble != 0.0D));
        while (true)
        {
          try
          {
            Geocoder localGeocoder = new Geocoder(ZmanimActivity.this.getApplicationContext());
            try
            {
              List localList2 = localGeocoder.getFromLocation(ZmanimActivity.this.latDouble, ZmanimActivity.this.longDouble, 1);
              localList1 = localList2;
              if ((localList1 == null) || (localList1.size() <= 0))
                continue;
              str = ((Address)localList1.get(0)).getLocality();
              if (str == null)
                continue;
              ZmanimActivity.this.titleString = ("זמנים עבור " + str);
              localZmanimClass2 = new ZmanimClass(ZmanimActivity.this.latDouble, ZmanimActivity.this.longDouble, str, Integer.valueOf(ZmanimActivity.this.settings.getString("daka_prefs_shabat", "0")).intValue());
              ZmanimActivity.this.dateString = localZmanimClass2.getHebrewDate();
              ZmanimActivity.this.parashaString = ("פרשת " + localZmanimClass2.getParasha());
              ZmanimActivity.this.eventString = localZmanimClass2.getEvents();
              boolean bool2 = ZmanimActivity.this.settings.getBoolean("daka_prefs_reverse", false);
              ZmanimActivity.this.timesArray = localZmanimClass2.getTimesAsArray(bool2);
              if ((i == 0) || (ZmanimActivity.this.timesString != null))
                continue;
              ZmanimClass localZmanimClass1 = new ZmanimClass(Integer.valueOf(ZmanimActivity.this.settings.getString("daka_prefs_place", "0")).intValue(), Integer.valueOf(ZmanimActivity.this.settings.getString("daka_prefs_shabat", "0")).intValue());
              ZmanimActivity.this.titleString = ("זמנים עבור " + localZmanimClass1.getPlace());
              ZmanimActivity.this.dateString = localZmanimClass1.getHebrewDate();
              ZmanimActivity.this.parashaString = ("פרשת " + localZmanimClass1.getParasha());
              ZmanimActivity.this.eventString = localZmanimClass1.getEvents();
              boolean bool1 = ZmanimActivity.this.settings.getBoolean("daka_prefs_reverse", false);
              ZmanimActivity.this.timesArray = localZmanimClass1.getTimesAsArray(bool1);
              this.handler.post(ZmanimActivity.this.finishUpdateThread);
              return;
            }
            catch (Exception localException1)
            {
              List localList1 = null;
              continue;
              String str = null;
              continue;
              ZmanimActivity.this.titleString = "זמנים עבור מיקומך";
              double d1 = ZmanimActivity.this.latDouble;
              double d2 = ZmanimActivity.this.longDouble;
              int j = Integer.valueOf(ZmanimActivity.this.settings.getString("daka_prefs_shabat", "0")).intValue();
              ZmanimClass localZmanimClass2 = new ZmanimClass(d1, d2, "", j);
              continue;
            }
          }
          catch (Exception localException2)
          {
            if (ZmanimActivity.this.timesString != null)
              continue;
            this.handler.post(ZmanimActivity.this.showAlert);
            i = 1;
            continue;
          }
          i = 1;
        }
      }
    }).start();
  }

  private class getReminderAsyncTask extends AsyncTask<String, Boolean, Boolean>
  {
    private getReminderAsyncTask()
    {
    }

    protected Boolean doInBackground(String[] paramArrayOfString)
    {
      if (ZmanimActivity.this.dtClass == null)
        ZmanimActivity.this.dtClass = new SqlDataClass(ZmanimActivity.this.getApplicationContext());
      ZmanimActivity.this.reminder = ZmanimActivity.this.dtClass.getField("KEY_REMINDER");
      ZmanimActivity.this.dtClass.closeDatabase();
      return Boolean.valueOf(true);
    }

    protected void onPostExecute(Boolean paramBoolean)
    {
      ZmanimActivity.this.applyReminder();
    }
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.eladrich.dakatora.ZmanimActivity
 * JD-Core Version:    0.6.0
 */