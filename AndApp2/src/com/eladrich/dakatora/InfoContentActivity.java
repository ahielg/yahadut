package com.eladrich.dakatora;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ZoomButton;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.MenuItem;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class InfoContentActivity extends DashboardActivity
{
  WebView infoWebView;

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903079);
    this.infoWebView = ((WebView)findViewById(2131099767));
    ZoomButton localZoomButton1 = (ZoomButton)findViewById(2131099768);
    ZoomButton localZoomButton2 = (ZoomButton)findViewById(2131099769);
    localZoomButton1.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        InfoContentActivity.this.infoWebView.zoomIn();
      }
    });
    localZoomButton2.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        InfoContentActivity.this.infoWebView.zoomOut();
      }
    });
    Bundle localBundle = getIntent().getExtras();
    ActionBar localActionBar = getSupportActionBar();
    String str;
    if (localBundle != null)
    {
      InfoListActivity.InfoType localInfoType = InfoListActivity.InfoType.valueOf(localBundle.getString("info_type"));
      switch ($SWITCH_TABLE$com$eladrich$dakatora$InfoListActivity$InfoType()[localInfoType.ordinal()])
      {
      default:
        str = "";
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      case 10:
      case 11:
      case 12:
      case 13:
      case 14:
      case 15:
      case 16:
      case 17:
      case 18:
      }
    }
    while (true)
    {
      readUnicodeFileAsyncTask localreadUnicodeFileAsyncTask = new readUnicodeFileAsyncTask(null);
      String[] arrayOfString = new String[1];
      arrayOfString[0] = str;
      localreadUnicodeFileAsyncTask.execute(arrayOfString);
      this.infoWebView.getSettings().setBuiltInZoomControls(false);
      return;
      str = "info_halacha_kovez_about.html";
      localActionBar.setTitle(getString(2131427452));
      continue;
      str = "info_halacha_kovez_eruv.html";
      localActionBar.setTitle(getString(2131427453));
      continue;
      str = "info_halacha_kovez_hands.html";
      localActionBar.setTitle(getString(2131427454));
      continue;
      str = "info_halacha_kovez_shabat.html";
      localActionBar.setTitle(getString(2131427455));
      continue;
      str = "info_halacha_kovez_tfila.html";
      localActionBar.setTitle(getString(2131427456));
      continue;
      str = "info_halacha_wake_all.html";
      localActionBar.setTitle(getString(2131427458));
      continue;
      str = "info_halacha_wake_before.html";
      localActionBar.setTitle(getString(2131427459));
      continue;
      str = "info_halacha_wake_tmp.html";
      localActionBar.setTitle(getString(2131427460));
      continue;
      str = "info_halacha_wake_twice.html";
      localActionBar.setTitle(getString(2131427461));
      continue;
      str = "info_halacha_zmanim.html";
      localActionBar.setTitle(getString(2131427462));
      continue;
      str = "info_rambam.html";
      localActionBar.setTitle(getString(2131427450));
      continue;
      str = "info_tfila_battle.html";
      localActionBar.setTitle(getString(2131427464));
      continue;
      str = "info_tfila_derech.html";
      localActionBar.setTitle(getString(2131427465));
      continue;
      str = "info_tfila_emergency.html";
      localActionBar.setTitle(getString(2131427466));
      continue;
      str = "info_tfila_paratrooper.html";
      localActionBar.setTitle(getString(2131427467));
      continue;
      str = "info_tfila_pilot.html";
      localActionBar.setTitle(getString(2131427468));
      continue;
      str = "info_tfila_sea.html";
      localActionBar.setTitle(getString(2131427469));
      continue;
      str = "info_tfila_submarine.html";
      localActionBar.setTitle(getString(2131427470));
      continue;
      str = "";
    }
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

  public String readUnicodeFile(String paramString)
  {
    Object localObject = null;
    AssetManager localAssetManager = getAssets();
    InputStream localInputStream;
    try
    {
      localInputStream = localAssetManager.open(paramString);
      if (localInputStream != null)
        break label45;
      throw new Exception("File Does Not Exist");
    }
    catch (Exception localException1)
    {
    }
    System.out.println(localException1);
    while (true)
    {
      label45: StringBuffer localStringBuffer;
      while (true)
      {
        return localObject.toString();
        InputStreamReader localInputStreamReader = new InputStreamReader(localInputStream, "UTF-8");
        try
        {
          localStringBuffer = new StringBuffer();
          try
          {
            while (true)
            {
              int i = localInputStreamReader.read();
              if (i <= -1)
              {
                if (localInputStreamReader == null)
                  break label123;
                localInputStreamReader.close();
                localObject = localStringBuffer;
                break;
              }
              localStringBuffer.append((char)i);
            }
          }
          catch (Exception localException2)
          {
            localObject = localStringBuffer;
          }
        }
        catch (Exception localException3)
        {
        }
      }
      break;
      label123: localObject = localStringBuffer;
    }
  }

  private class readUnicodeFileAsyncTask extends AsyncTask<String, Void, String>
  {
    private readUnicodeFileAsyncTask()
    {
    }

    protected String doInBackground(String[] paramArrayOfString)
    {
      return InfoContentActivity.this.readUnicodeFile(paramArrayOfString[0]);
    }

    protected void onPostExecute(String paramString)
    {
      if ((paramString != null) && (InfoContentActivity.this.infoWebView != null))
        InfoContentActivity.this.infoWebView.loadDataWithBaseURL(null, paramString, "text/html", "utf-8", null);
    }
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.eladrich.dakatora.InfoContentActivity
 * JD-Core Version:    0.6.0
 */