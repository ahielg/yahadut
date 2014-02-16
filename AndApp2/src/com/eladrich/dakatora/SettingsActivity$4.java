package com.eladrich.dakatora;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.Spinner;

class SettingsActivity$4
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    SharedPreferences.Editor localEditor = SettingsActivity.access$0(this.this$0).edit();
    if (((CheckBox)paramView).isChecked())
    {
      localEditor.putBoolean("daka_prefs_gps_checked", true);
      localEditor.commit();
      SettingsActivity.access$2(this.this$0).setEnabled(false);
    }
    while (true)
    {
      return;
      localEditor.putBoolean("daka_prefs_gps_checked", false);
      localEditor.commit();
      SettingsActivity.access$2(this.this$0).setEnabled(true);
    }
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.eladrich.dakatora.SettingsActivity.4
 * JD-Core Version:    0.6.0
 */