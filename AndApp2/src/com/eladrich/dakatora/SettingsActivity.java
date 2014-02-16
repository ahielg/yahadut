package com.eladrich.dakatora;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.ToggleButton;
import java.util.Calendar;

public class SettingsActivity extends DashboardActivity
{
  static final int TIME_DIALOG_ID;
  private CheckBox alarmCheckBox;
  private int desiredHour;
  private int desiredMinute;
  private Spinner locSpinner;
  private CheckBox locationCheckBox;
  private TimePickerDialog.OnTimeSetListener mTimeSetListener = new SettingsActivity.1(this);
  private Button pickTimeButton;
  private SharedPreferences settings;
  private Spinner shabatSpinner;

  private static String pad(int paramInt)
  {
    if (paramInt >= 10);
    for (String str = String.valueOf(paramInt); ; str = "0" + String.valueOf(paramInt))
      return str;
  }

  private static String padReversed(int paramInt)
  {
    String str2;
    if (paramInt >= 10)
      str2 = String.valueOf(paramInt);
    for (String str1 = str2.charAt(1) + str2.charAt(0); ; str1 = String.valueOf(paramInt) + "0")
      return str1;
  }

  private void startAlarm()
  {
    AlarmManager localAlarmManager = (AlarmManager)getSystemService("alarm");
    Calendar localCalendar = Calendar.getInstance();
    int i = localCalendar.get(11);
    int j = localCalendar.get(12);
    long l1 = System.currentTimeMillis();
    if ((this.desiredHour < i) || ((this.desiredHour == i) && (this.desiredMinute <= j)))
      l1 += 86400000L;
    long l2 = l1 + (3600000 * (this.desiredHour - i) + 60000 * (this.desiredMinute - j));
    PendingIntent localPendingIntent = PendingIntent.getService(this, 0, new Intent(this, ReminderService.class), 0);
    if (this.settings.getBoolean("daka_prefs_reminder_checked", false))
      localAlarmManager.setRepeating(1, l2, 86400000L, localPendingIntent);
    while (true)
    {
      return;
      localAlarmManager.cancel(localPendingIntent);
    }
  }

  private void updateDisplay()
  {
    if (this.settings.getBoolean("daka_prefs_reminder_checked", false))
    {
      this.alarmCheckBox.setChecked(true);
      this.pickTimeButton.setEnabled(true);
      this.desiredHour = this.settings.getInt("daka_prefs_reminder_hour", 12);
      this.desiredMinute = this.settings.getInt("daka_prefs_reminder_minute", 0);
      if (this.settings.getBoolean("daka_prefs_reverse", false))
        this.alarmCheckBox.setText("תזכורת בשעה " + padReversed(this.desiredMinute) + ":" + padReversed(this.desiredHour));
    }
    while (true)
    {
      startAlarm();
      return;
      this.alarmCheckBox.setText("תזכורת בשעה " + pad(this.desiredHour) + ":" + pad(this.desiredMinute));
      continue;
      this.alarmCheckBox.setChecked(false);
      this.pickTimeButton.setEnabled(false);
      this.alarmCheckBox.setText("תזכורת");
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903070);
    setTitleFromActivityLabel(2131099666);
    this.settings = getSharedPreferences("daka_prefs", 0);
    this.locSpinner = ((Spinner)findViewById(2131099732));
    this.shabatSpinner = ((Spinner)findViewById(2131099735));
    this.locationCheckBox = ((CheckBox)findViewById(2131099733));
    this.pickTimeButton = ((Button)findViewById(2131099738));
    this.alarmCheckBox = ((CheckBox)findViewById(2131099737));
    ToggleButton localToggleButton = (ToggleButton)findViewById(2131099739);
    ArrayAdapter localArrayAdapter1 = ArrayAdapter.createFromResource(this, 2131558402, 17367048);
    localArrayAdapter1.setDropDownViewResource(17367049);
    this.locSpinner.setAdapter(localArrayAdapter1);
    this.locSpinner.setOnItemSelectedListener(new SettingsActivity.2(this));
    this.locSpinner.setSelection(this.settings.getInt("daka_prefs_place", 0));
    ArrayAdapter localArrayAdapter2 = ArrayAdapter.createFromResource(this, 2131558400, 17367048);
    localArrayAdapter2.setDropDownViewResource(17367049);
    this.shabatSpinner.setAdapter(localArrayAdapter2);
    this.shabatSpinner.setOnItemSelectedListener(new SettingsActivity.3(this));
    this.shabatSpinner.setSelection(this.settings.getInt("daka_prefs_shabat", 0));
    this.locationCheckBox.setOnClickListener(new SettingsActivity.4(this));
    if (this.settings.getBoolean("daka_prefs_gps_checked", true))
    {
      this.locationCheckBox.setChecked(true);
      this.locSpinner.setEnabled(false);
    }
    while (true)
    {
      this.alarmCheckBox.setOnClickListener(new SettingsActivity.5(this));
      this.pickTimeButton.setOnClickListener(new SettingsActivity.6(this));
      updateDisplay();
      localToggleButton.setOnClickListener(new SettingsActivity.7(this));
      localToggleButton.setChecked(this.settings.getBoolean("daka_prefs_reverse", false));
      return;
      this.locationCheckBox.setChecked(false);
      this.locSpinner.setEnabled(true);
    }
  }

  protected Dialog onCreateDialog(int paramInt)
  {
    switch (paramInt)
    {
    default:
    case 0:
    }
    for (TimePickerDialog localTimePickerDialog = null; ; localTimePickerDialog = new TimePickerDialog(this, this.mTimeSetListener, this.desiredHour, this.desiredMinute, false))
      return localTimePickerDialog;
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.eladrich.dakatora.SettingsActivity
 * JD-Core Version:    0.6.0
 */