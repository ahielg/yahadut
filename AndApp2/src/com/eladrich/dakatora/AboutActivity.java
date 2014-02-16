package com.eladrich.dakatora;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import com.actionbarsherlock.view.MenuItem;

public class AboutActivity extends DashboardActivity
{
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903061);
    TextView localTextView = (TextView)findViewById(2131099695);
    String str1 = getString(2131427392);
    try
    {
      String str3 = str1.replace("x", getPackageManager().getPackageInfo(getPackageName(), 0).versionName);
      str2 = str3;
      localTextView.setText(str2);
      ((Button)findViewById(2131099697)).setOnClickListener(new AboutActivity.1(this));
      ((Button)findViewById(2131099696)).setOnClickListener(new AboutActivity.2(this));
      ((Button)findViewById(2131099698)).setOnClickListener(new AboutActivity.3(this));
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      while (true)
        String str2 = str1.replace("x", "3.0.1");
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
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.eladrich.dakatora.AboutActivity
 * JD-Core Version:    0.6.0
 */