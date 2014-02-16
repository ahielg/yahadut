package com.eladrich.dakatora;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

class SettingsActivity$2
  implements AdapterView.OnItemSelectedListener
{
  public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    SharedPreferences.Editor localEditor = SettingsActivity.access$0(this.this$0).edit();
    localEditor.putInt("daka_prefs_place", paramInt);
    localEditor.commit();
  }

  public void onNothingSelected(AdapterView<?> paramAdapterView)
  {
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.eladrich.dakatora.SettingsActivity.2
 * JD-Core Version:    0.6.0
 */