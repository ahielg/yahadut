package android.support.v4.app;

import android.view.View;
import com.actionbarsherlock.ActionBarSherlock.OnCreatePanelMenuListener;
import com.actionbarsherlock.ActionBarSherlock.OnMenuItemSelectedListener;
import com.actionbarsherlock.ActionBarSherlock.OnPreparePanelListener;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import java.util.ArrayList;

public abstract class _ActionBarSherlockTrojanHorse extends FragmentActivity
  implements ActionBarSherlock.OnCreatePanelMenuListener, ActionBarSherlock.OnPreparePanelListener, ActionBarSherlock.OnMenuItemSelectedListener
{
  private static final boolean DEBUG = false;
  private static final String TAG = "_ActionBarSherlockTrojanHorse";
  private ArrayList<Fragment> mCreatedMenus;

  public abstract MenuInflater getSupportMenuInflater();

  public abstract boolean onCreateOptionsMenu(Menu paramMenu);

  public boolean onCreatePanelMenu(int paramInt, Menu paramMenu)
  {
    boolean bool1;
    MenuInflater localMenuInflater;
    boolean bool2;
    ArrayList localArrayList;
    int k;
    int j;
    if (paramInt == 0)
    {
      bool1 = onCreateOptionsMenu(paramMenu);
      localMenuInflater = getSupportMenuInflater();
      bool2 = false;
      localArrayList = null;
      if (this.mFragments.mActive != null)
      {
        k = 0;
        if (k < this.mFragments.mAdded.size());
      }
      else
      {
        if (this.mCreatedMenus != null)
        {
          j = 0;
          label61: if (j < this.mCreatedMenus.size())
            break label185;
        }
        this.mCreatedMenus = localArrayList;
      }
    }
    for (int i = bool1 | bool2; ; i = 0)
    {
      return i;
      Fragment localFragment2 = (Fragment)this.mFragments.mAdded.get(k);
      if ((localFragment2 != null) && (!localFragment2.mHidden) && (localFragment2.mHasMenu) && (localFragment2.mMenuVisible) && ((localFragment2 instanceof OnCreateOptionsMenuListener)))
      {
        bool2 = true;
        ((OnCreateOptionsMenuListener)localFragment2).onCreateOptionsMenu(paramMenu, localMenuInflater);
        if (localArrayList == null)
          localArrayList = new ArrayList();
        localArrayList.add(localFragment2);
      }
      k++;
      break;
      label185: Fragment localFragment1 = (Fragment)this.mCreatedMenus.get(j);
      if ((localArrayList == null) || (!localArrayList.contains(localFragment1)))
        localFragment1.onDestroyOptionsMenu();
      j++;
      break label61;
    }
  }

  public boolean onMenuItemSelected(int paramInt, MenuItem paramMenuItem)
  {
    int i = 1;
    if (paramInt == 0)
    {
      if (onOptionsItemSelected(paramMenuItem))
        return i;
      if (this.mFragments.mActive == null);
    }
    for (int j = 0; ; j++)
    {
      if (j >= this.mFragments.mAdded.size())
      {
        i = 0;
        break;
      }
      Fragment localFragment = (Fragment)this.mFragments.mAdded.get(j);
      if ((localFragment != null) && (!localFragment.mHidden) && (localFragment.mHasMenu) && (localFragment.mMenuVisible) && ((localFragment instanceof OnOptionsItemSelectedListener)) && (((OnOptionsItemSelectedListener)localFragment).onOptionsItemSelected(paramMenuItem)))
        break;
    }
  }

  public abstract boolean onOptionsItemSelected(MenuItem paramMenuItem);

  public abstract boolean onPrepareOptionsMenu(Menu paramMenu);

  public boolean onPreparePanel(int paramInt, View paramView, Menu paramMenu)
  {
    boolean bool2;
    boolean bool3;
    int i;
    if (paramInt == 0)
    {
      bool2 = onPrepareOptionsMenu(paramMenu);
      bool3 = false;
      if (this.mFragments.mActive != null)
      {
        i = 0;
        if (i < this.mFragments.mAdded.size())
          break label59;
      }
    }
    for (boolean bool1 = (bool2 | bool3) & paramMenu.hasVisibleItems(); ; bool1 = false)
    {
      return bool1;
      label59: Fragment localFragment = (Fragment)this.mFragments.mAdded.get(i);
      if ((localFragment != null) && (!localFragment.mHidden) && (localFragment.mHasMenu) && (localFragment.mMenuVisible) && ((localFragment instanceof OnPrepareOptionsMenuListener)))
      {
        bool3 = true;
        ((OnPrepareOptionsMenuListener)localFragment).onPrepareOptionsMenu(paramMenu);
      }
      i++;
      break;
    }
  }

  public static abstract interface OnCreateOptionsMenuListener
  {
    public abstract void onCreateOptionsMenu(Menu paramMenu, MenuInflater paramMenuInflater);
  }

  public static abstract interface OnOptionsItemSelectedListener
  {
    public abstract boolean onOptionsItemSelected(MenuItem paramMenuItem);
  }

  public static abstract interface OnPrepareOptionsMenuListener
  {
    public abstract void onPrepareOptionsMenu(Menu paramMenu);
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     android.support.v4.app._ActionBarSherlockTrojanHorse
 * JD-Core Version:    0.6.0
 */