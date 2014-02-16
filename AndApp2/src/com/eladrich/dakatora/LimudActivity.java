package com.eladrich.dakatora;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.ZoomButton;
import android.widget.ZoomControls;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.widget.ShareActionProvider;
import java.util.Calendar;

public class LimudActivity extends DashboardActivity
{
  public final String CLEAN_ONLY = "cleanData";
  public final String DATA_NEXT = "getNextData";
  public final String DATA_ONLY = "getOnlyData";
  public final String DATA_PREV = "getPrevData";
  public final String DATA_TODAY = "getTodayData";
  public final String SAVE_ONLY = "saveData";
  ShareActionProvider actionProvider;
  AssetManager asManager;
  public boolean cleanFinished;
  String codeString1;
  String codeString2;
  String codeString3;
  String contentString1;
  String contentString2;
  String contentString3;
  Calendar currentCalendar;
  ProgressDialog dialog;
  SqlDataClass dtClass;
  Runnable finishThread = new Runnable()
  {
    public void run()
    {
      LimudActivity.this.applyLoaded();
    }
  };
  float fontSize;
  WebView limudWebView;
  boolean mBound;
  DataService mService;
  String mainTitleString;
  public boolean saveFinished;
  boolean stringsAvailable;
  Thread t;
  String titleString1;
  String titleString2;
  String titleString3;
  ToraDBOpenHelper toraDBOpenHelper;
  ZoomControls zoomControls;

  private Intent createShareIntent()
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType("text/plain");
    localIntent.putExtra("android.intent.extra.SUBJECT", "דקה תורה - לימוד יומי");
    if (this.stringsAvailable)
    {
      String str = new StringBuilder(String.valueOf("")).append(this.titleString1).toString() + "\n" + this.contentString1;
      if (!this.codeString1.equals(""))
        str = str + "\n" + this.codeString1;
      if (!this.titleString2.equals(""))
        str = str + "\n" + this.titleString2;
      if (!this.contentString2.equals(""))
        str = str + "\n" + this.contentString2;
      if (!this.codeString2.equals(""))
        str = str + "\n" + this.codeString2;
      if (!this.titleString3.equals(""))
        str = str + "\n" + this.titleString3;
      if (!this.contentString3.equals(""))
        str = str + "\n" + this.contentString3.replace("\n", "\n");
      if (!this.codeString3.equals(""))
        str = str + "\n" + this.codeString3;
      localIntent.putExtra("android.intent.extra.TEXT", str + "\ndakatora.co.il");
    }
    return localIntent;
  }

  private static String pad(int paramInt)
  {
    if (paramInt >= 10);
    for (String str = String.valueOf(paramInt); ; str = "0" + String.valueOf(paramInt))
      return str;
  }

  public void ButtonClick(View paramView)
  {
    int i = paramView.getId();
    String[] arrayOfString = new String[2];
    arrayOfString[0] = "";
    switch (i)
    {
    default:
    case 2131099772:
    case 2131099773:
    }
    while (true)
    {
      return;
      arrayOfString[1] = "getNextData";
      new getDataAsyncTask(null).execute(arrayOfString);
      setSupportProgressBarIndeterminateVisibility(true);
      continue;
      arrayOfString[1] = "getPrevData";
      new getDataAsyncTask(null).execute(arrayOfString);
      setSupportProgressBarIndeterminateVisibility(true);
    }
  }

  public void applyLoaded()
  {
    if (this.actionProvider != null)
      this.actionProvider.setShareIntent(createShareIntent());
    ActionBar localActionBar = getSupportActionBar();
    if (!this.mainTitleString.equals("not found"))
    {
      localActionBar.setTitle(this.mainTitleString);
      str1 = new StringBuilder(String.valueOf("<html dir=\"RTL\"><head><meta charset=\"utf-8\"/></head><body>")).append("<big>").append(this.titleString1).append("</big>").toString() + "<br />" + this.contentString1.replace("\n", "<br />") + "<br />";
      if (!this.codeString1.equals(""))
        str1 = str1 + "<br /><small>" + this.codeString1 + "</small>";
      if (!this.titleString2.equals(""))
        str1 = str1 + "<br /><big>" + this.titleString2 + "</big>";
      if (!this.contentString2.equals(""))
        str1 = str1 + "<br />" + this.contentString2.replace("\n", "<br />") + "<br />";
      if (!this.codeString2.equals(""))
        str1 = str1 + "<br /><small>" + this.codeString2 + "</small>";
      if (!this.titleString3.equals(""))
        str1 = str1 + "<br /><big>" + this.titleString3 + "</big>";
      if (!this.contentString3.equals(""))
        str1 = str1 + "<br />" + this.contentString3.replace("\n", "<br />") + "<br />";
      if (this.codeString3.equals(""));
    }
    for (String str1 = str1 + "<br /><small>" + this.codeString3 + "</small>"; ; str1 = "<html dir=\"RTL\"><head><meta charset=\"utf-8\"/></head><body>" + "<big>" + getString(2131427397) + "</big>")
    {
      String str2 = str1 + "</body></html>";
      this.limudWebView.getSettings().setBuiltInZoomControls(false);
      this.limudWebView.loadDataWithBaseURL(null, str2, "text/html", "utf-8", null);
      return;
      localActionBar.setTitle(getString(2131427361));
    }
  }

  public void load()
  {
    String str = Integer.toString(this.currentCalendar.get(1)) + '-' + pad(1 + this.currentCalendar.get(2)) + '-' + pad(this.currentCalendar.get(5));
    String[] arrayOfString = new String[2];
    arrayOfString[0] = str;
    arrayOfString[1] = "getOnlyData";
    new getDataAsyncTask(null).execute(arrayOfString);
  }

  protected void onCreate(Bundle paramBundle)
  {
    requestWindowFeature(5L);
    super.onCreate(paramBundle);
    setContentView(2130903080);
    this.saveFinished = false;
    this.cleanFinished = false;
    this.stringsAvailable = false;
    this.limudWebView = ((WebView)findViewById(2131099771));
    ZoomButton localZoomButton1 = (ZoomButton)findViewById(2131099768);
    ZoomButton localZoomButton2 = (ZoomButton)findViewById(2131099769);
    localZoomButton1.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        LimudActivity.this.limudWebView.zoomIn();
      }
    });
    localZoomButton2.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        LimudActivity.this.limudWebView.zoomOut();
      }
    });
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getSupportMenuInflater().inflate(2131623938, paramMenu);
    this.actionProvider = ((ShareActionProvider)paramMenu.findItem(2131099783).getActionProvider());
    this.actionProvider.setShareHistoryFileName("share_history.xml");
    this.actionProvider.setShareIntent(createShareIntent());
    return true;
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

  protected void onStart()
  {
    super.onStart();
    this.currentCalendar = Calendar.getInstance();
    setSupportProgressBarIndeterminateVisibility(true);
    load();
  }

  protected void onStop()
  {
    super.onStop();
    String[] arrayOfString = new String[2];
    arrayOfString[0] = "";
    arrayOfString[1] = "cleanData";
    new getDataAsyncTask(null).execute(arrayOfString);
  }

  public void updateSize()
  {
    TextView localTextView1 = (TextView)findViewById(2131099707);
    TextView localTextView2 = (TextView)findViewById(2131099708);
    TextView localTextView3 = (TextView)findViewById(2131099709);
    TextView localTextView4 = (TextView)findViewById(2131099710);
    TextView localTextView5 = (TextView)findViewById(2131099711);
    TextView localTextView6 = (TextView)findViewById(2131099712);
    TextView localTextView7 = (TextView)findViewById(2131099713);
    TextView localTextView8 = (TextView)findViewById(2131099714);
    TextView localTextView9 = (TextView)findViewById(2131099715);
    localTextView1.setTextSize(2.0F + this.fontSize);
    localTextView4.setTextSize(2.0F + this.fontSize);
    localTextView7.setTextSize(2.0F + this.fontSize);
    localTextView2.setTextSize(this.fontSize);
    localTextView5.setTextSize(this.fontSize);
    localTextView8.setTextSize(this.fontSize);
    localTextView3.setTextSize(this.fontSize - 2.0F);
    localTextView6.setTextSize(this.fontSize - 2.0F);
    localTextView9.setTextSize(this.fontSize - 2.0F);
    if (this.fontSize == 30.0F)
      this.zoomControls.setIsZoomInEnabled(false);
    while (true)
    {
      return;
      if (this.fontSize == 12.0F)
      {
        this.zoomControls.setIsZoomOutEnabled(false);
        continue;
      }
      this.zoomControls.setIsZoomInEnabled(true);
      this.zoomControls.setIsZoomOutEnabled(true);
    }
  }

  private class getDataAsyncTask extends AsyncTask<String, Boolean, String>
  {
    private getDataAsyncTask()
    {
    }

    protected String doInBackground(String[] paramArrayOfString)
    {
      String str1 = paramArrayOfString[1];
      if (LimudActivity.this.dtClass == null)
        LimudActivity.this.dtClass = new SqlDataClass(LimudActivity.this.getApplicationContext());
      if (paramArrayOfString[1].equals("getOnlyData"))
      {
        LimudActivity.this.stringsAvailable = false;
        LimudActivity.this.mainTitleString = LimudActivity.this.dtClass.getField("KEY_TITLE_DAY");
        LimudActivity.this.titleString1 = LimudActivity.this.dtClass.getField("KEY_TITLE_1");
        String str2 = LimudActivity.this.dtClass.getField("KEY_CONTENT_1");
        LimudActivity.this.contentString1 = str2;
        LimudActivity.this.codeString1 = LimudActivity.this.dtClass.getField("KEY_SOURCE_1");
        LimudActivity.this.titleString2 = LimudActivity.this.dtClass.getField("KEY_TITLE_2");
        LimudActivity.this.contentString2 = LimudActivity.this.dtClass.getField("KEY_CONTENT_2");
        LimudActivity.this.codeString2 = LimudActivity.this.dtClass.getField("KEY_SOURCE_2");
        LimudActivity.this.titleString3 = LimudActivity.this.dtClass.getField("KEY_TITLE_3");
        LimudActivity.this.contentString3 = LimudActivity.this.dtClass.getField("KEY_CONTENT_3");
        LimudActivity.this.codeString3 = LimudActivity.this.dtClass.getField("KEY_SOURCE_3");
        LimudActivity.this.stringsAvailable = true;
      }
      while (true)
      {
        return str1;
        if (paramArrayOfString[1].equals("getNextData"))
        {
          LimudActivity.this.stringsAvailable = false;
          LimudActivity.this.dtClass.nextQueryDate();
          LimudActivity.this.mainTitleString = LimudActivity.this.dtClass.getField("KEY_TITLE_DAY");
          LimudActivity.this.titleString1 = LimudActivity.this.dtClass.getField("KEY_TITLE_1");
          LimudActivity.this.contentString1 = LimudActivity.this.dtClass.getField("KEY_CONTENT_1");
          LimudActivity.this.codeString1 = LimudActivity.this.dtClass.getField("KEY_SOURCE_1");
          LimudActivity.this.titleString2 = LimudActivity.this.dtClass.getField("KEY_TITLE_2");
          LimudActivity.this.contentString2 = LimudActivity.this.dtClass.getField("KEY_CONTENT_2");
          LimudActivity.this.codeString2 = LimudActivity.this.dtClass.getField("KEY_SOURCE_2");
          LimudActivity.this.titleString3 = LimudActivity.this.dtClass.getField("KEY_TITLE_3");
          LimudActivity.this.contentString3 = LimudActivity.this.dtClass.getField("KEY_CONTENT_3");
          LimudActivity.this.codeString3 = LimudActivity.this.dtClass.getField("KEY_SOURCE_3");
          LimudActivity.this.stringsAvailable = true;
          continue;
        }
        if (paramArrayOfString[1].equals("getPrevData"))
        {
          LimudActivity.this.stringsAvailable = false;
          LimudActivity.this.dtClass.prevQueryDate();
          LimudActivity.this.mainTitleString = LimudActivity.this.dtClass.getField("KEY_TITLE_DAY");
          LimudActivity.this.titleString1 = LimudActivity.this.dtClass.getField("KEY_TITLE_1");
          LimudActivity.this.contentString1 = LimudActivity.this.dtClass.getField("KEY_CONTENT_1");
          LimudActivity.this.codeString1 = LimudActivity.this.dtClass.getField("KEY_SOURCE_1");
          LimudActivity.this.titleString2 = LimudActivity.this.dtClass.getField("KEY_TITLE_2");
          LimudActivity.this.contentString2 = LimudActivity.this.dtClass.getField("KEY_CONTENT_2");
          LimudActivity.this.codeString2 = LimudActivity.this.dtClass.getField("KEY_SOURCE_2");
          LimudActivity.this.titleString3 = LimudActivity.this.dtClass.getField("KEY_TITLE_3");
          LimudActivity.this.contentString3 = LimudActivity.this.dtClass.getField("KEY_CONTENT_3");
          LimudActivity.this.codeString3 = LimudActivity.this.dtClass.getField("KEY_SOURCE_3");
          LimudActivity.this.stringsAvailable = true;
          continue;
        }
        if (paramArrayOfString[1].equals("getTodayData"))
        {
          LimudActivity.this.stringsAvailable = false;
          LimudActivity.this.dtClass.resetQueryDate();
          LimudActivity.this.mainTitleString = LimudActivity.this.dtClass.getField("KEY_TITLE_DAY");
          LimudActivity.this.titleString1 = LimudActivity.this.dtClass.getField("KEY_TITLE_1");
          LimudActivity.this.contentString1 = LimudActivity.this.dtClass.getField("KEY_CONTENT_1");
          LimudActivity.this.codeString1 = LimudActivity.this.dtClass.getField("KEY_SOURCE_1");
          LimudActivity.this.titleString2 = LimudActivity.this.dtClass.getField("KEY_TITLE_2");
          LimudActivity.this.contentString2 = LimudActivity.this.dtClass.getField("KEY_CONTENT_2");
          LimudActivity.this.codeString2 = LimudActivity.this.dtClass.getField("KEY_SOURCE_2");
          LimudActivity.this.titleString3 = LimudActivity.this.dtClass.getField("KEY_TITLE_3");
          LimudActivity.this.contentString3 = LimudActivity.this.dtClass.getField("KEY_CONTENT_3");
          LimudActivity.this.codeString3 = LimudActivity.this.dtClass.getField("KEY_SOURCE_3");
          LimudActivity.this.stringsAvailable = true;
          continue;
        }
        if (paramArrayOfString[1].equals("saveData"))
        {
          LimudActivity.this.saveFinished = false;
          LimudActivity.this.dtClass.saveDatabase();
          continue;
        }
        if (paramArrayOfString[1].equals("cleanData"))
        {
          LimudActivity.this.cleanFinished = false;
          LimudActivity.this.dtClass.cleanDatabase();
          continue;
        }
        if (!paramArrayOfString[1].equals("closeData"))
          continue;
        LimudActivity.this.dtClass.closeDatabase();
      }
    }

    protected void onPostExecute(String paramString)
    {
      if ((paramString.equals("getNextData")) || (paramString.equals("getPrevData")) || (paramString.equals("getTodayData")) || (paramString.equals("getOnlyData")))
      {
        LimudActivity.this.setSupportProgressBarIndeterminateVisibility(false);
        LimudActivity.this.applyLoaded();
        String[] arrayOfString1 = new String[2];
        arrayOfString1[0] = "";
        arrayOfString1[1] = "saveData";
        new getDataAsyncTask(LimudActivity.this).execute(arrayOfString1);
      }
      while (true)
      {
        if ((LimudActivity.this.saveFinished) && (LimudActivity.this.cleanFinished))
        {
          String[] arrayOfString2 = new String[2];
          arrayOfString2[0] = "";
          arrayOfString2[1] = "closeData";
          new getDataAsyncTask(LimudActivity.this).execute(arrayOfString2);
          LimudActivity.this.saveFinished = false;
          LimudActivity.this.cleanFinished = false;
        }
        return;
        if (paramString.equals("saveData"))
        {
          LimudActivity.this.saveFinished = true;
          continue;
        }
        if (paramString.equals("cleanData"))
        {
          LimudActivity.this.cleanFinished = true;
          continue;
        }
        if (!paramString.equals("closeData"))
          continue;
        LimudActivity.this.dtClass = null;
      }
    }

    protected void onProgressUpdate(Boolean[] paramArrayOfBoolean)
    {
    }
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.eladrich.dakatora.LimudActivity
 * JD-Core Version:    0.6.0
 */