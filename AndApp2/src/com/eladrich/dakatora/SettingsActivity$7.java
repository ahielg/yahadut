package com.eladrich.dakatora;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ToggleButton;

class SettingsActivity$7
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    ToggleButton localToggleButton = (ToggleButton)paramView;
    SharedPreferences.Editor localEditor = SettingsActivity.access$0(this.this$0).edit();
    if (localToggleButton.isChecked())
    {
      localEditor.putBoolean("daka_prefs_reverse", true);
      localEditor.commit();
      SettingsActivity.access$1(this.this$0);
    }
    while (true)
    {
      return;
      localEditor.putBoolean("daka_prefs_reverse", false);
      localEditor.commit();
      SettingsActivity.access$1(this.this$0);
    }
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.eladrich.dakatora.SettingsActivity.7
 * JD-Core Version:    0.6.0
 */