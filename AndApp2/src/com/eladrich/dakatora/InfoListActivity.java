package com.eladrich.dakatora;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.MenuItem;

public class InfoListActivity extends SherlockListActivity
{
  final String LEVEL_KEY = "level_key";
  Level level;

  public void onBackPressed()
  {
    switch ($SWITCH_TABLE$com$eladrich$dakatora$InfoListActivity$Level()[this.level.ordinal()])
    {
    default:
    case 1:
    case 2:
    case 5:
    case 3:
    case 4:
    }
    while (true)
    {
      return;
      super.onBackPressed();
      continue;
      this.level = Level.MAIN;
      updateAdapter();
      continue;
      this.level = Level.MAIN;
      updateAdapter();
      continue;
      this.level = Level.INFO;
      updateAdapter();
      continue;
      this.level = Level.INFO;
      updateAdapter();
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903069);
    ActionBar localActionBar = getSupportActionBar();
    localActionBar.setDisplayShowTitleEnabled(true);
    localActionBar.setHomeButtonEnabled(true);
    localActionBar.setDisplayHomeAsUpEnabled(true);
    localActionBar.setDisplayUseLogoEnabled(true);
    if ((paramBundle != null) && (paramBundle.containsKey("level_key")));
    for (this.level = Level.valueOf(paramBundle.getString("level_key")); ; this.level = Level.MAIN)
    {
      updateAdapter();
      return;
    }
  }

  protected void onListItemClick(ListView paramListView, View paramView, int paramInt, long paramLong)
  {
    switch ($SWITCH_TABLE$com$eladrich$dakatora$InfoListActivity$Level()[this.level.ordinal()])
    {
    default:
    case 1:
    case 2:
    case 5:
    case 3:
    case 4:
    }
    while (true)
    {
      return;
      switch (paramInt)
      {
      default:
        break;
      case 0:
        this.level = Level.INFO;
        updateAdapter();
        break;
      case 1:
        startActivity(new Intent(this, InfoKavActivity.class));
        break;
      case 2:
        Intent localIntent18 = new Intent(this, InfoContentActivity.class);
        localIntent18.putExtra("info_type", InfoType.RAMBAM.toString());
        startActivity(localIntent18);
        break;
      case 3:
        this.level = Level.TFILA;
        updateAdapter();
        continue;
        switch (paramInt)
        {
        default:
          break;
        case 0:
          this.level = Level.KOVEZ;
          updateAdapter();
          break;
        case 1:
          Intent localIntent17 = new Intent(this, InfoContentActivity.class);
          localIntent17.putExtra("info_type", InfoType.HALACHA_ZMANIM.toString());
          startActivity(localIntent17);
          break;
        case 2:
          this.level = Level.WAKE;
          updateAdapter();
          continue;
          switch (paramInt)
          {
          default:
            break;
          case 0:
            Intent localIntent16 = new Intent(this, InfoContentActivity.class);
            localIntent16.putExtra("info_type", InfoType.TFILA_DERECH.toString());
            startActivity(localIntent16);
            break;
          case 1:
            Intent localIntent15 = new Intent(this, InfoContentActivity.class);
            localIntent15.putExtra("info_type", InfoType.TFILA_BATTLE.toString());
            startActivity(localIntent15);
            break;
          case 2:
            Intent localIntent14 = new Intent(this, InfoContentActivity.class);
            localIntent14.putExtra("info_type", InfoType.TFILA_PARATROOPER.toString());
            startActivity(localIntent14);
            break;
          case 3:
            Intent localIntent13 = new Intent(this, InfoContentActivity.class);
            localIntent13.putExtra("info_type", InfoType.TFILA_PILOT.toString());
            startActivity(localIntent13);
            break;
          case 4:
            Intent localIntent12 = new Intent(this, InfoContentActivity.class);
            localIntent12.putExtra("info_type", InfoType.TFILA_SUBMARINE.toString());
            startActivity(localIntent12);
            break;
          case 5:
            Intent localIntent11 = new Intent(this, InfoContentActivity.class);
            localIntent11.putExtra("info_type", InfoType.TFILA_SEA.toString());
            startActivity(localIntent11);
            break;
          case 6:
            Intent localIntent10 = new Intent(this, InfoContentActivity.class);
            localIntent10.putExtra("info_type", InfoType.TFILA_EMERGENCY.toString());
            startActivity(localIntent10);
            continue;
            switch (paramInt)
            {
            default:
              break;
            case 0:
              Intent localIntent9 = new Intent(this, InfoContentActivity.class);
              localIntent9.putExtra("info_type", InfoType.HALACHA_KOVEZ_TFILA.toString());
              startActivity(localIntent9);
              break;
            case 1:
              Intent localIntent8 = new Intent(this, InfoContentActivity.class);
              localIntent8.putExtra("info_type", InfoType.HALACHA_KOVEZ_HANDS.toString());
              startActivity(localIntent8);
              break;
            case 2:
              Intent localIntent7 = new Intent(this, InfoContentActivity.class);
              localIntent7.putExtra("info_type", InfoType.HALACHA_KOVEZ_ERUV.toString());
              startActivity(localIntent7);
              break;
            case 3:
              Intent localIntent6 = new Intent(this, InfoContentActivity.class);
              localIntent6.putExtra("info_type", InfoType.HALACHA_KOVEZ_SHABAT.toString());
              startActivity(localIntent6);
              break;
            case 4:
              Intent localIntent5 = new Intent(this, InfoContentActivity.class);
              localIntent5.putExtra("info_type", InfoType.HALACHA_KOVEZ_ABOUT.toString());
              startActivity(localIntent5);
              continue;
              switch (paramInt)
              {
              default:
                break;
              case 0:
                Intent localIntent4 = new Intent(this, InfoContentActivity.class);
                localIntent4.putExtra("info_type", InfoType.HALACHA_WAKE_ALL.toString());
                startActivity(localIntent4);
                break;
              case 1:
                Intent localIntent3 = new Intent(this, InfoContentActivity.class);
                localIntent3.putExtra("info_type", InfoType.HALACHA_WAKE_BEFORE.toString());
                startActivity(localIntent3);
                break;
              case 2:
                Intent localIntent2 = new Intent(this, InfoContentActivity.class);
                localIntent2.putExtra("info_type", InfoType.HALACHA_WAKE_TWICE.toString());
                startActivity(localIntent2);
                break;
              case 3:
                Intent localIntent1 = new Intent(this, InfoContentActivity.class);
                localIntent1.putExtra("info_type", InfoType.HALACHA_WAKE_TMP.toString());
                startActivity(localIntent1);
              }
            }
          }
        }
      }
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

  public void onSaveInstanceState(Bundle paramBundle)
  {
    switch ($SWITCH_TABLE$com$eladrich$dakatora$InfoListActivity$Level()[this.level.ordinal()])
    {
    default:
    case 1:
    case 2:
    case 5:
    case 3:
    case 4:
    }
    while (true)
    {
      super.onSaveInstanceState(paramBundle);
      return;
      paramBundle.putString("level_key", "MAIN");
      continue;
      paramBundle.putString("level_key", "INFO");
      continue;
      paramBundle.putString("level_key", "TFILA");
      continue;
      paramBundle.putString("level_key", "KOVEZ");
      continue;
      paramBundle.putString("level_key", "WAKE");
    }
  }

  public void updateAdapter()
  {
    Object localObject = null;
    ActionBar localActionBar = getSupportActionBar();
    String str = getString(2131427447);
    switch ($SWITCH_TABLE$com$eladrich$dakatora$InfoListActivity$Level()[this.level.ordinal()])
    {
    default:
    case 1:
    case 2:
    case 5:
    case 3:
    case 4:
    }
    while (true)
    {
      localActionBar.setTitle(str);
      if (localObject != null)
        setListAdapter((ListAdapter)localObject);
      return;
      String[] arrayOfString5 = new String[4];
      arrayOfString5[0] = getString(2131427448);
      arrayOfString5[1] = getString(2131427449);
      arrayOfString5[2] = getString(2131427450);
      arrayOfString5[3] = getString(2131427463);
      localObject = new ArrayAdapter(this, 17367043, arrayOfString5);
      str = getString(2131427447);
      continue;
      String[] arrayOfString4 = new String[3];
      arrayOfString4[0] = getString(2131427451);
      arrayOfString4[1] = getString(2131427462);
      arrayOfString4[2] = getString(2131427457);
      localObject = new ArrayAdapter(this, 17367043, arrayOfString4);
      str = getString(2131427448);
      continue;
      String[] arrayOfString3 = new String[7];
      arrayOfString3[0] = getString(2131427465);
      arrayOfString3[1] = getString(2131427464);
      arrayOfString3[2] = getString(2131427467);
      arrayOfString3[3] = getString(2131427468);
      arrayOfString3[4] = getString(2131427470);
      arrayOfString3[5] = getString(2131427469);
      arrayOfString3[6] = getString(2131427466);
      localObject = new ArrayAdapter(this, 17367043, arrayOfString3);
      str = getString(2131427463);
      continue;
      String[] arrayOfString2 = new String[5];
      arrayOfString2[0] = getString(2131427456);
      arrayOfString2[1] = getString(2131427454);
      arrayOfString2[2] = getString(2131427453);
      arrayOfString2[3] = getString(2131427455);
      arrayOfString2[4] = getString(2131427452);
      localObject = new ArrayAdapter(this, 17367043, arrayOfString2);
      str = getString(2131427451);
      setListAdapter((ListAdapter)localObject);
      continue;
      String[] arrayOfString1 = new String[4];
      arrayOfString1[0] = getString(2131427458);
      arrayOfString1[1] = getString(2131427459);
      arrayOfString1[2] = getString(2131427461);
      arrayOfString1[3] = getString(2131427460);
      localObject = new ArrayAdapter(this, 17367043, arrayOfString1);
      str = getString(2131427457);
    }
  }

  public static enum InfoType
  {
    static
    {
      InfoType[] arrayOfInfoType = new InfoType[18];
      arrayOfInfoType[0] = HALACHA_KOVEZ_ABOUT;
      arrayOfInfoType[1] = HALACHA_KOVEZ_ERUV;
      arrayOfInfoType[2] = HALACHA_KOVEZ_HANDS;
      arrayOfInfoType[3] = HALACHA_KOVEZ_SHABAT;
      arrayOfInfoType[4] = HALACHA_KOVEZ_TFILA;
      arrayOfInfoType[5] = HALACHA_WAKE_ALL;
      arrayOfInfoType[6] = HALACHA_WAKE_BEFORE;
      arrayOfInfoType[7] = HALACHA_WAKE_TMP;
      arrayOfInfoType[8] = HALACHA_WAKE_TWICE;
      arrayOfInfoType[9] = HALACHA_ZMANIM;
      arrayOfInfoType[10] = RAMBAM;
      arrayOfInfoType[11] = TFILA_BATTLE;
      arrayOfInfoType[12] = TFILA_DERECH;
      arrayOfInfoType[13] = TFILA_EMERGENCY;
      arrayOfInfoType[14] = TFILA_PARATROOPER;
      arrayOfInfoType[15] = TFILA_PILOT;
      arrayOfInfoType[16] = TFILA_SEA;
      arrayOfInfoType[17] = TFILA_SUBMARINE;
      ENUM$VALUES = arrayOfInfoType;
    }
  }

  static enum Level
  {
    static
    {
      INFO = new Level("INFO", 1);
      KOVEZ = new Level("KOVEZ", 2);
      WAKE = new Level("WAKE", 3);
      TFILA = new Level("TFILA", 4);
      Level[] arrayOfLevel = new Level[5];
      arrayOfLevel[0] = MAIN;
      arrayOfLevel[1] = INFO;
      arrayOfLevel[2] = KOVEZ;
      arrayOfLevel[3] = WAKE;
      arrayOfLevel[4] = TFILA;
      ENUM$VALUES = arrayOfLevel;
    }
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.eladrich.dakatora.InfoListActivity
 * JD-Core Version:    0.6.0
 */