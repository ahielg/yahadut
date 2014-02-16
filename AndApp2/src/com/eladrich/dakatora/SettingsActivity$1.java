package com.eladrich.dakatora;

import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.TimePicker;

class SettingsActivity$1
  implements TimePickerDialog.OnTimeSetListener
{
  public void onTimeSet(TimePicker paramTimePicker, int paramInt1, int paramInt2)
  {
    SharedPreferences.Editor localEditor = SettingsActivity.access$0(this.this$0).edit();
    localEditor.putInt("daka_prefs_reminder_hour", paramInt1);
    localEditor.putInt("daka_prefs_reminder_minute", paramInt2);
    localEditor.commit();
    SettingsActivity.access$1(this.this$0);
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.eladrich.dakatora.SettingsActivity.1
 * JD-Core Version:    0.6.0
 */