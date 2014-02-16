package com.eladrich.dakatora;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;

class SettingsActivity$5
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    SharedPreferences.Editor localEditor = SettingsActivity.access$0(this.this$0).edit();
    if (((CheckBox)paramView).isChecked())
    {
      localEditor.putBoolean("daka_prefs_reminder_checked", true);
      localEditor.commit();
      SettingsActivity.access$1(this.this$0);
    }
    while (true)
    {
      return;
      localEditor.putBoolean("daka_prefs_reminder_checked", false);
      localEditor.commit();
      SettingsActivity.access$1(this.this$0);
    }
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.eladrich.dakatora.SettingsActivity.5
 * JD-Core Version:    0.6.0
 */