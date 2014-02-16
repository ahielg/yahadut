package com.eladrich.dakatora;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import java.util.List;

public class HomeActivity extends DashboardActivity
{
  public final String ADVERT_COUNT = "advert";
  final int DATA_LENGTH = 4;
  public final String DOWNLOAD_COUNT = "download";
  public final String IGNORE_COUNT = "reopen";
  public final String LISTEN_COUNT = "listen";
  final int NOTIF_ID = 2;
  public final String OPEN_COUNT = "open";
  public final String UNIQUE_COUNT = "unique";
  boolean activityIsVisible;
  ViewAnimator animator;
  String compassTag;
  String currentDate = "";
  String[] dataString;
  ProgressDialog dialog;
  boolean fileFromIntent;
  Runnable finishReminderThread = new HomeActivity.1(this);
  String homeMenuTag;
  ActionBar.Tab homeTab;
  ImageView iconIV;
  String infoTag;
  String limudTag;
  TextView playerSeperator;
  String playerTag;
  String reminder;
  MenuItem searchItem;
  TextView seperator;
  ImageView shareIV;
  String shiurListTag;
  boolean showAd;
  ImageView sponsorIV;
  LinearLayout subContainer;
  String zmanimTag;

  private Intent createShareIntent()
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType("text/plain");
    localIntent.putExtra("android.intent.extra.SUBJECT", "דקה תורה - לימוד תורה יומי לחייל");
    localIntent.putExtra("android.intent.extra.TEXT", "השתמשתי באפליקציית דקה תורה וחשבתי שאולי תרצה לנסות אותה \n http://dakatora.co.il");
    return localIntent;
  }

  public void applyReminder()
  {
    this.reminder.equals("not found");
    this.reminder = "";
  }

  public void onClickFeature(View paramView)
  {
    switch (paramView.getId())
    {
    default:
    case 2131099725:
    case 2131099726:
    case 2131099727:
    case 2131099728:
    }
    while (true)
    {
      return;
      startActivity(new Intent(getApplicationContext(), LimudActivity.class));
      continue;
      startActivity(new Intent(getApplicationContext(), CompassActivity.class));
      continue;
      startActivity(new Intent(getApplicationContext(), ZmanimActivity.class));
      continue;
      CharSequence[] arrayOfCharSequence = new CharSequence[4];
      arrayOfCharSequence[0] = getString(2131427371);
      arrayOfCharSequence[1] = getString(2131427357);
      arrayOfCharSequence[2] = getString(2131427358);
      arrayOfCharSequence[3] = getString(2131427369);
      startActivity(new Intent(getApplicationContext(), InfoListActivity.class));
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903067);
    ActionBar localActionBar = getSupportActionBar();
    localActionBar.setDisplayShowTitleEnabled(true);
    localActionBar.setHomeButtonEnabled(false);
    localActionBar.setDisplayHomeAsUpEnabled(false);
    this.showAd = false;
    this.shiurListTag = "shiur_list_tag";
    this.homeMenuTag = "home_menu_tag";
    FragmentManager localFragmentManager = getSupportFragmentManager();
    FragmentTransaction localFragmentTransaction = localFragmentManager.beginTransaction();
    localFragmentTransaction.replace(2131099723, new HomeMenuFragment(), this.homeMenuTag);
    localFragmentTransaction.commit();
    PlayerFragment localPlayerFragment = (PlayerFragment)localFragmentManager.findFragmentById(2131099722);
    Uri localUri = getIntent().getData();
    if (localUri != null)
    {
      this.fileFromIntent = true;
      List localList = localUri.getPathSegments();
      if (localList.size() >= 2)
        localPlayerFragment.setLink((String)localList.get(2));
    }
    while (true)
    {
      localPlayerFragment.setFileFromIntent(this.fileFromIntent);
      return;
      this.fileFromIntent = false;
      continue;
      this.fileFromIntent = false;
    }
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getSupportMenuInflater().inflate(2131623937, paramMenu);
    return true;
  }

  protected void onDestroy()
  {
    super.onDestroy();
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    boolean bool = true;
    switch (paramMenuItem.getItemId())
    {
    default:
      bool = super.onOptionsItemSelected(paramMenuItem);
    case 2131099782:
    case 2131099781:
    }
    while (true)
    {
      return bool;
      startActivity(new Intent(this, PrefsActivity.class));
      continue;
      startActivity(new Intent(this, AboutActivity.class));
    }
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

  public void performSearch(String paramString)
  {
    Toast.makeText(getApplicationContext(), paramString, 0).show();
  }

  public void rowClick(View paramView)
  {
    switch (paramView.getId())
    {
    case 2131099761:
    default:
    case 2131099760:
    case 2131099762:
    case 2131099763:
    case 2131099764:
    case 2131099765:
    }
    while (true)
    {
      return;
      startActivity(new Intent(getApplicationContext(), LimudActivity.class));
      continue;
      startActivity(new Intent(getApplicationContext(), CompassActivity.class));
      continue;
      startActivity(new Intent(getApplicationContext(), ZmanimActivity.class));
      continue;
      startActivity(new Intent(getApplicationContext(), InfoListActivity.class));
      continue;
      FragmentTransaction localFragmentTransaction = getSupportFragmentManager().beginTransaction();
      localFragmentTransaction.addToBackStack(null);
      localFragmentTransaction.replace(2131099723, new ShiurListFragment(), this.shiurListTag);
      localFragmentTransaction.commit();
    }
  }

  public void startSpecificFragmentFile(String[] paramArrayOfString)
  {
    ((PlayerFragment)getSupportFragmentManager().findFragmentById(2131099722)).startSpecificFile(paramArrayOfString);
  }

  public void stopSpecificFragmentFile()
  {
    ((PlayerFragment)getSupportFragmentManager().findFragmentById(2131099722)).stopSpecificFile();
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.eladrich.dakatora.HomeActivity
 * JD-Core Version:    0.6.0
 */