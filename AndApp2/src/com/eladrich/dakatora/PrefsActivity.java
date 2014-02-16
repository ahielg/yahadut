package com.eladrich.dakatora;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.preference.PreferenceActivity.Header;
import android.preference.PreferenceManager;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockPreferenceActivity;
import com.actionbarsherlock.view.MenuItem;
import java.util.Calendar;
import java.util.List;

public class PrefsActivity extends SherlockPreferenceActivity
{
  static final String PREF_GPS_CHECKED = "daka_prefs_gps_checked";
  static final String PREF_IMPORT_SETTINGS = "daka_import_settings";
  static final String PREF_LAST_LISTEN = "daka_prefs_listen";
  static final String PREF_NAME = "daka_prefs";
  static final String PREF_PLACE = "daka_prefs_place";
  static final String PREF_REMINDER_CHECKED = "daka_prefs_reminder_checked";
  static final String PREF_REMINDER_HOUR = "daka_prefs_reminder_hour";
  static final String PREF_REMINDER_MINUTE = "daka_prefs_reminder_minute";
  static final String PREF_REMINDER_TIME = "daka_prefs_reminder_time";
  static final String PREF_REVERSE = "daka_prefs_reverse";
  static final String PREF_SHABAT = "daka_prefs_shabat";
  static final String PREF_SIZE = "daka_prefs_size";
  static final String PREF_TEXT_SIZE = "daka_prefs_text_size";

  private static String pad(int paramInt)
  {
    if (paramInt >= 10);
    for (String str = String.valueOf(paramInt); ; str = "0" + String.valueOf(paramInt))
      return str;
  }

  private void startAlarm()
  {
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    AlarmManager localAlarmManager = (AlarmManager)getSystemService("alarm");
    Calendar localCalendar = Calendar.getInstance();
    String str = localSharedPreferences.getString("daka_prefs_reminder_time", "12:00");
    int i = Integer.valueOf(str.substring(1 + str.indexOf(":"))).intValue();
    int j = Integer.valueOf(str.substring(0, str.indexOf(":"))).intValue();
    int k = localCalendar.get(11);
    int m = localCalendar.get(12);
    localCalendar.set(14, 0);
    localCalendar.set(13, 0);
    localCalendar.set(11, j);
    localCalendar.set(12, i);
    long l = localCalendar.getTimeInMillis();
    if ((j < k) || ((j == k) && (i <= m)))
      l += 86400000L;
    PendingIntent localPendingIntent = PendingIntent.getService(this, 0, new Intent(this, ReminderService.class), 0);
    if (localSharedPreferences.getBoolean("daka_prefs_reminder_checked", false))
    {
      localAlarmManager.cancel(localPendingIntent);
      localAlarmManager.setRepeating(1, l, 86400000L, localPendingIntent);
    }
    while (true)
    {
      return;
      localAlarmManager.cancel(localPendingIntent);
    }
  }

  public void onBuildHeaders(List<PreferenceActivity.Header> paramList)
  {
    loadHeadersFromResource(2131034115, paramList);
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    ActionBar localActionBar = getSupportActionBar();
    localActionBar.setHomeButtonEnabled(true);
    localActionBar.setDisplayHomeAsUpEnabled(true);
    localActionBar.setDisplayUseLogoEnabled(true);
    SharedPreferences localSharedPreferences1 = getSharedPreferences("daka_prefs", 0);
    SharedPreferences localSharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(this);
    if (localSharedPreferences2.getBoolean("daka_import_settings", true))
    {
      SharedPreferences.Editor localEditor = localSharedPreferences2.edit();
      localEditor.putBoolean("daka_prefs_reverse", localSharedPreferences1.getBoolean("daka_prefs_reverse", false));
      localEditor.putBoolean("daka_prefs_gps_checked", localSharedPreferences1.getBoolean("daka_prefs_gps_checked", true));
      localEditor.putBoolean("daka_prefs_reminder_checked", localSharedPreferences1.getBoolean("daka_prefs_reminder_checked", false));
      localEditor.putString("daka_prefs_reminder_time", pad(localSharedPreferences1.getInt("daka_prefs_reminder_hour", 12)) + ":" + pad(localSharedPreferences1.getInt("daka_prefs_reminder_minute", 0)));
      localEditor.putString("daka_prefs_place", String.valueOf(localSharedPreferences1.getInt("daka_prefs_place", 0)));
      localEditor.putString("daka_prefs_shabat", String.valueOf(localSharedPreferences1.getInt("daka_prefs_shabat", 0)));
      localEditor.putBoolean("daka_import_settings", false);
      localEditor.commit();
    }
    if (Build.VERSION.SDK_INT < 11)
    {
      addPreferencesFromResource(2131034112);
      addPreferencesFromResource(2131034114);
    }
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default:
    case 16908332:
    }
    for (boolean bool = super.onOptionsItemSelected(paramMenuItem); ; bool = true)
    {
      return bool;
      Intent localIntent = new Intent(this, HomeActivity.class);
      localIntent.setFlags(67108864);
      startActivity(localIntent);
    }
  }

  protected void onStop()
  {
    startAlarm();
    super.onStop();
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.eladrich.dakatora.PrefsActivity
 * JD-Core Version:    0.6.0
 */