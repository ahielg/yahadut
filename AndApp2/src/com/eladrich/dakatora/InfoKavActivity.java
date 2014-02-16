package com.eladrich.dakatora;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import com.actionbarsherlock.view.MenuItem;

public class InfoKavActivity extends DashboardActivity
{
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903068);
    ((Button)findViewById(2131099730)).setOnClickListener(new InfoKavActivity.1(this));
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default:
    case 16908332:
    }
    while (true)
    {
      return super.onOptionsItemSelected(paramMenuItem);
      Intent localIntent = new Intent(this, HomeActivity.class);
      localIntent.setFlags(67108864);
      startActivity(localIntent);
    }
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.eladrich.dakatora.InfoKavActivity
 * JD-Core Version:    0.6.0
 */