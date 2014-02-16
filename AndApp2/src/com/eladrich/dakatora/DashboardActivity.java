package com.eladrich.dakatora;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;

public abstract class DashboardActivity extends SherlockFragmentActivity
{
  static final int DIALOG_HALACHA = 3;
  static final int DIALOG_INFO = 0;
  static final int DIALOG_KOVEZ = 4;
  static final int DIALOG_TFILA = 1;
  static final int DIALOG_WAKE = 2;
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
  static final String PREF_SHOW_AGAIN = "daka_show_again";
  static final String PREF_SIZE = "daka_prefs_size";
  static final String PREF_TEXT_SIZE = "daka_prefs_text_size";

  public void goHome(Context paramContext)
  {
    Intent localIntent = new Intent(paramContext, HomeActivity.class);
    localIntent.setFlags(67108864);
    paramContext.startActivity(localIntent);
  }

  public void onClickHome(View paramView)
  {
    goHome(this);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    ActionBar localActionBar = getSupportActionBar();
    localActionBar.setDisplayShowTitleEnabled(true);
    localActionBar.setHomeButtonEnabled(true);
    localActionBar.setDisplayHomeAsUpEnabled(true);
    localActionBar.setDisplayUseLogoEnabled(true);
  }

  protected Dialog onCreateDialog(int paramInt)
  {
    return null;
  }

  protected void onDestroy()
  {
    super.onDestroy();
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    paramMenuItem.getItemId();
    return super.onOptionsItemSelected(paramMenuItem);
  }

  protected void onPause()
  {
    super.onPause();
  }

  protected void onRestart()
  {
    super.onRestart();
  }

  protected void onResume()
  {
    super.onResume();
  }

  protected void onStart()
  {
    super.onStart();
  }

  protected void onStop()
  {
    super.onStop();
  }

  public void setTitleFromActivityLabel(int paramInt)
  {
    TextView localTextView = (TextView)findViewById(paramInt);
    if (localTextView != null)
      localTextView.setText(getTitle());
  }

  public void showAlert(String paramString)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setMessage(paramString);
    localBuilder.show();
  }

  public void toast(String paramString)
  {
    Toast.makeText(getApplicationContext(), paramString, 0).show();
  }

  public void trace(String paramString)
  {
    toast(paramString);
  }

  public void transformToViews(LinearLayout paramLinearLayout, String paramString)
  {
    if (!paramString.contains("</TEXT>"))
      return;
    String str = paramString.substring(0, 7 + paramString.indexOf("</TEXT>"));
    paramString = paramString.substring(7 + paramString.indexOf("</TEXT>"), paramString.length());
    TextView localTextView = new TextView(getApplicationContext());
    localTextView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
    localTextView.setTextColor(-1);
    if (str.contains("<SMALL>"))
    {
      localTextView.setTextSize(2, 12.0F);
      label96: if (str.contains("<B>"))
        localTextView.setTextSize(2, 17.0F);
      if (!str.contains("<CENTER>"))
        break label193;
      localTextView.setGravity(17);
    }
    while (true)
    {
      localTextView.setText(str.substring(6 + str.indexOf("<TEXT>"), str.indexOf("</TEXT>")));
      paramLinearLayout.addView(localTextView);
      break;
      if (str.contains("<BIG>"))
      {
        localTextView.setTextSize(2, 20.0F);
        break label96;
      }
      localTextView.setTextSize(2, 16.0F);
      break label96;
      label193: localTextView.setGravity(5);
    }
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.eladrich.dakatora.DashboardActivity
 * JD-Core Version:    0.6.0
 */