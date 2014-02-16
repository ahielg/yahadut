package com.actionbarsherlock.internal.view.menu;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.DisplayMetrics;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import com.actionbarsherlock.R.attr;
import com.actionbarsherlock.R.integer;
import com.actionbarsherlock.R.layout;
import com.actionbarsherlock.R.styleable;
import com.actionbarsherlock.internal.ResourcesCompat;
import com.actionbarsherlock.internal.view.View_HasStateListenerSupport;
import com.actionbarsherlock.internal.view.View_OnAttachStateChangeListener;
import com.actionbarsherlock.view.ActionProvider;
import com.actionbarsherlock.view.ActionProvider.SubUiVisibilityListener;
import com.actionbarsherlock.view.MenuItem;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ActionMenuPresenter extends BaseMenuPresenter
  implements ActionProvider.SubUiVisibilityListener
{
  private final SparseBooleanArray mActionButtonGroups = new SparseBooleanArray();
  private ActionButtonSubmenu mActionButtonPopup;
  private int mActionItemWidthLimit;
  private boolean mExpandedActionViewsExclusive;
  private int mMaxItems;
  private boolean mMaxItemsSet;
  private int mMinCellSize;
  int mOpenSubMenuId;
  private View mOverflowButton;
  private OverflowPopup mOverflowPopup;
  final PopupPresenterCallback mPopupPresenterCallback = new PopupPresenterCallback(null);
  private OpenOverflowRunnable mPostedOpenRunnable;
  private boolean mReserveOverflow;
  private boolean mReserveOverflowSet;
  private View mScrapActionButtonView;
  private boolean mStrictWidthLimit;
  private int mWidthLimit;
  private boolean mWidthLimitSet;

  public ActionMenuPresenter(Context paramContext)
  {
    super(paramContext, R.layout.abs__action_menu_layout, R.layout.abs__action_menu_item_layout);
  }

  private View findViewForItem(MenuItem paramMenuItem)
  {
    ViewGroup localViewGroup = (ViewGroup)this.mMenuView;
    View localView;
    if (localViewGroup == null)
    {
      localView = null;
      return localView;
    }
    int i = localViewGroup.getChildCount();
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        localView = null;
        break;
      }
      localView = localViewGroup.getChildAt(j);
      if (((localView instanceof MenuView.ItemView)) && (((MenuView.ItemView)localView).getItemData() == paramMenuItem))
        break;
    }
  }

  public static boolean reserveOverflow(Context paramContext)
  {
    int i = 1;
    int j = 0;
    TypedArray localTypedArray = paramContext.getTheme().obtainStyledAttributes(R.styleable.SherlockTheme);
    boolean bool = localTypedArray.getBoolean(52, false);
    localTypedArray.recycle();
    if (bool);
    while (true)
    {
      return i;
      if (Build.VERSION.SDK_INT >= 14)
        break;
      if (Build.VERSION.SDK_INT >= 11)
        continue;
      i = 0;
    }
    if (HasPermanentMenuKey.get(paramContext));
    while (true)
    {
      i = j;
      break;
      j = i;
    }
  }

  public void bindItemView(MenuItemImpl paramMenuItemImpl, MenuView.ItemView paramItemView)
  {
    paramItemView.initialize(paramMenuItemImpl, 0);
    ActionMenuView localActionMenuView = (ActionMenuView)this.mMenuView;
    ((ActionMenuItemView)paramItemView).setItemInvoker(localActionMenuView);
  }

  public boolean dismissPopupMenus()
  {
    return hideOverflowMenu() | hideSubMenus();
  }

  public boolean filterLeftoverView(ViewGroup paramViewGroup, int paramInt)
  {
    if (paramViewGroup.getChildAt(paramInt) == this.mOverflowButton);
    for (boolean bool = false; ; bool = super.filterLeftoverView(paramViewGroup, paramInt))
      return bool;
  }

  public boolean flagActionItems()
  {
    ArrayList localArrayList = this.mMenu.getVisibleItems();
    int i = localArrayList.size();
    int j = this.mMaxItems;
    int k = this.mActionItemWidthLimit;
    int m = View.MeasureSpec.makeMeasureSpec(0, 0);
    ViewGroup localViewGroup = (ViewGroup)this.mMenuView;
    int n = 0;
    int i1 = 0;
    int i2 = 0;
    int i3 = 0;
    int i4 = 0;
    int i5;
    SparseBooleanArray localSparseBooleanArray;
    int i6;
    int i7;
    int i8;
    if (i4 >= i)
    {
      if ((this.mReserveOverflow) && ((i3 != 0) || (n + i1 > j)))
        j--;
      i5 = j - n;
      localSparseBooleanArray = this.mActionButtonGroups;
      localSparseBooleanArray.clear();
      i6 = 0;
      i7 = 0;
      if (this.mStrictWidthLimit)
      {
        i7 = k / this.mMinCellSize;
        int i15 = k % this.mMinCellSize;
        i6 = this.mMinCellSize + i15 / i7;
      }
      i8 = 0;
      if (i8 >= i)
        return true;
    }
    else
    {
      MenuItemImpl localMenuItemImpl1 = (MenuItemImpl)localArrayList.get(i4);
      if (localMenuItemImpl1.requiresActionButton())
        n++;
      while (true)
      {
        if ((this.mExpandedActionViewsExclusive) && (localMenuItemImpl1.isActionViewExpanded()))
          j = 0;
        i4++;
        break;
        if (localMenuItemImpl1.requestsActionButton())
        {
          i1++;
          continue;
        }
        i3 = 1;
      }
    }
    MenuItemImpl localMenuItemImpl2 = (MenuItemImpl)localArrayList.get(i8);
    View localView2;
    if (localMenuItemImpl2.requiresActionButton())
    {
      localView2 = getItemView(localMenuItemImpl2, this.mScrapActionButtonView, localViewGroup);
      if (this.mScrapActionButtonView == null)
        this.mScrapActionButtonView = localView2;
      if (this.mStrictWidthLimit)
      {
        i7 -= ActionMenuView.measureChildForCells(localView2, i6, i7, m, 0);
        label291: int i13 = localView2.getMeasuredWidth();
        k -= i13;
        if (i2 == 0)
          i2 = i13;
        int i14 = localMenuItemImpl2.getGroupId();
        if (i14 != 0)
          localSparseBooleanArray.put(i14, true);
        localMenuItemImpl2.setIsActionButton(true);
      }
    }
    do
    {
      i8++;
      break;
      localView2.measure(m, m);
      break label291;
    }
    while (!localMenuItemImpl2.requestsActionButton());
    int i9 = localMenuItemImpl2.getGroupId();
    boolean bool1 = localSparseBooleanArray.get(i9);
    boolean bool2;
    label412: View localView1;
    label480: boolean bool4;
    if (((i5 > 0) || (bool1)) && (k > 0) && ((!this.mStrictWidthLimit) || (i7 > 0)))
    {
      bool2 = true;
      if (bool2)
      {
        localView1 = getItemView(localMenuItemImpl2, this.mScrapActionButtonView, localViewGroup);
        if (this.mScrapActionButtonView == null)
          this.mScrapActionButtonView = localView1;
        if (!this.mStrictWidthLimit)
          break label567;
        int i12 = ActionMenuView.measureChildForCells(localView1, i6, i7, m, 0);
        i7 -= i12;
        if (i12 == 0)
          bool2 = false;
        int i11 = localView1.getMeasuredWidth();
        k -= i11;
        if (i2 == 0)
          i2 = i11;
        if (!this.mStrictWidthLimit)
          break label585;
        if (k < 0)
          break label579;
        bool4 = true;
        label518: bool2 &= bool4;
      }
      if ((!bool2) || (i9 == 0))
        break label612;
      localSparseBooleanArray.put(i9, true);
    }
    while (true)
    {
      if (bool2)
        i5--;
      localMenuItemImpl2.setIsActionButton(bool2);
      break;
      bool2 = false;
      break label412;
      label567: localView1.measure(m, m);
      break label480;
      label579: bool4 = false;
      break label518;
      label585: if (k + i2 > 0);
      for (boolean bool3 = true; ; bool3 = false)
      {
        bool2 &= bool3;
        break;
      }
      label612: if (!bool1)
        continue;
      localSparseBooleanArray.put(i9, false);
      for (int i10 = 0; i10 < i8; i10++)
      {
        MenuItemImpl localMenuItemImpl3 = (MenuItemImpl)localArrayList.get(i10);
        if (localMenuItemImpl3.getGroupId() != i9)
          continue;
        if (localMenuItemImpl3.isActionButton())
          i5++;
        localMenuItemImpl3.setIsActionButton(false);
      }
    }
  }

  public View getItemView(MenuItemImpl paramMenuItemImpl, View paramView, ViewGroup paramViewGroup)
  {
    View localView = paramMenuItemImpl.getActionView();
    if ((localView == null) || (paramMenuItemImpl.hasCollapsibleActionView()))
    {
      if (!(paramView instanceof ActionMenuItemView))
        paramView = null;
      localView = super.getItemView(paramMenuItemImpl, paramView, paramViewGroup);
    }
    if (paramMenuItemImpl.isActionViewExpanded());
    for (int i = 8; ; i = 0)
    {
      localView.setVisibility(i);
      ActionMenuView localActionMenuView = (ActionMenuView)paramViewGroup;
      ViewGroup.LayoutParams localLayoutParams = localView.getLayoutParams();
      if (!localActionMenuView.checkLayoutParams(localLayoutParams))
        localView.setLayoutParams(localActionMenuView.generateLayoutParams(localLayoutParams));
      return localView;
    }
  }

  public MenuView getMenuView(ViewGroup paramViewGroup)
  {
    MenuView localMenuView = super.getMenuView(paramViewGroup);
    ((ActionMenuView)localMenuView).setPresenter(this);
    return localMenuView;
  }

  public boolean hideOverflowMenu()
  {
    int i;
    if ((this.mPostedOpenRunnable != null) && (this.mMenuView != null))
    {
      ((View)this.mMenuView).removeCallbacks(this.mPostedOpenRunnable);
      this.mPostedOpenRunnable = null;
      i = 1;
    }
    while (true)
    {
      return i;
      OverflowPopup localOverflowPopup = this.mOverflowPopup;
      if (localOverflowPopup != null)
      {
        localOverflowPopup.dismiss();
        i = 1;
        continue;
      }
      i = 0;
    }
  }

  public boolean hideSubMenus()
  {
    if (this.mActionButtonPopup != null)
      this.mActionButtonPopup.dismiss();
    for (int i = 1; ; i = 0)
      return i;
  }

  public void initForMenu(Context paramContext, MenuBuilder paramMenuBuilder)
  {
    super.initForMenu(paramContext, paramMenuBuilder);
    Resources localResources = paramContext.getResources();
    if (!this.mReserveOverflowSet)
      this.mReserveOverflow = reserveOverflow(this.mContext);
    if (!this.mWidthLimitSet)
      this.mWidthLimit = (localResources.getDisplayMetrics().widthPixels / 2);
    if (!this.mMaxItemsSet)
      this.mMaxItems = ResourcesCompat.getResources_getInteger(paramContext, R.integer.abs__max_action_buttons);
    int i = this.mWidthLimit;
    if (this.mReserveOverflow)
    {
      if (this.mOverflowButton == null)
      {
        this.mOverflowButton = new OverflowMenuButton(this.mSystemContext);
        int j = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.mOverflowButton.measure(j, j);
      }
      i -= this.mOverflowButton.getMeasuredWidth();
    }
    while (true)
    {
      this.mActionItemWidthLimit = i;
      this.mMinCellSize = (int)(56.0F * localResources.getDisplayMetrics().density);
      this.mScrapActionButtonView = null;
      return;
      this.mOverflowButton = null;
    }
  }

  public boolean isOverflowMenuShowing()
  {
    if ((this.mOverflowPopup != null) && (this.mOverflowPopup.isShowing()));
    for (int i = 1; ; i = 0)
      return i;
  }

  public boolean isOverflowReserved()
  {
    return this.mReserveOverflow;
  }

  public void onCloseMenu(MenuBuilder paramMenuBuilder, boolean paramBoolean)
  {
    dismissPopupMenus();
    super.onCloseMenu(paramMenuBuilder, paramBoolean);
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    if (!this.mMaxItemsSet)
    {
      this.mMaxItems = ResourcesCompat.getResources_getInteger(this.mContext, R.integer.abs__max_action_buttons);
      if (this.mMenu != null)
        this.mMenu.onItemsChanged(true);
    }
  }

  public void onRestoreInstanceState(Parcelable paramParcelable)
  {
    SavedState localSavedState = (SavedState)paramParcelable;
    if (localSavedState.openSubMenuId > 0)
    {
      MenuItem localMenuItem = this.mMenu.findItem(localSavedState.openSubMenuId);
      if (localMenuItem != null)
        onSubMenuSelected((SubMenuBuilder)localMenuItem.getSubMenu());
    }
  }

  public Parcelable onSaveInstanceState()
  {
    SavedState localSavedState = new SavedState();
    localSavedState.openSubMenuId = this.mOpenSubMenuId;
    return localSavedState;
  }

  public boolean onSubMenuSelected(SubMenuBuilder paramSubMenuBuilder)
  {
    int i = 0;
    if (!paramSubMenuBuilder.hasVisibleItems())
      return i;
    for (SubMenuBuilder localSubMenuBuilder = paramSubMenuBuilder; ; localSubMenuBuilder = (SubMenuBuilder)localSubMenuBuilder.getParentMenu())
    {
      if (localSubMenuBuilder.getParentMenu() != this.mMenu)
        continue;
      View localView = findViewForItem(localSubMenuBuilder.getItem());
      if (localView == null)
      {
        if (this.mOverflowButton == null)
          break;
        localView = this.mOverflowButton;
      }
      this.mOpenSubMenuId = paramSubMenuBuilder.getItem().getItemId();
      this.mActionButtonPopup = new ActionButtonSubmenu(this.mContext, paramSubMenuBuilder);
      this.mActionButtonPopup.setAnchorView(localView);
      this.mActionButtonPopup.show();
      super.onSubMenuSelected(paramSubMenuBuilder);
      i = 1;
      break;
    }
  }

  public void onSubUiVisibilityChanged(boolean paramBoolean)
  {
    if (paramBoolean)
      super.onSubMenuSelected(null);
    while (true)
    {
      return;
      this.mMenu.close(false);
    }
  }

  public void setExpandedActionViewsExclusive(boolean paramBoolean)
  {
    this.mExpandedActionViewsExclusive = paramBoolean;
  }

  public void setItemLimit(int paramInt)
  {
    this.mMaxItems = paramInt;
    this.mMaxItemsSet = true;
  }

  public void setReserveOverflow(boolean paramBoolean)
  {
    this.mReserveOverflow = paramBoolean;
    this.mReserveOverflowSet = true;
  }

  public void setWidthLimit(int paramInt, boolean paramBoolean)
  {
    this.mWidthLimit = paramInt;
    this.mStrictWidthLimit = paramBoolean;
    this.mWidthLimitSet = true;
  }

  public boolean shouldIncludeItem(int paramInt, MenuItemImpl paramMenuItemImpl)
  {
    return paramMenuItemImpl.isActionButton();
  }

  public boolean showOverflowMenu()
  {
    boolean bool = true;
    if ((this.mReserveOverflow) && (!isOverflowMenuShowing()) && (this.mMenu != null) && (this.mMenuView != null) && (this.mPostedOpenRunnable == null) && (!this.mMenu.getNonActionItems().isEmpty()))
    {
      this.mPostedOpenRunnable = new OpenOverflowRunnable(new OverflowPopup(this.mContext, this.mMenu, this.mOverflowButton, bool));
      ((View)this.mMenuView).post(this.mPostedOpenRunnable);
      super.onSubMenuSelected(null);
    }
    while (true)
    {
      return bool;
      bool = false;
    }
  }

  public void updateMenuView(boolean paramBoolean)
  {
    super.updateMenuView(paramBoolean);
    ArrayList localArrayList2;
    int m;
    ArrayList localArrayList1;
    label53: int i;
    int j;
    if (this.mMenu != null)
    {
      localArrayList2 = this.mMenu.getActionItems();
      int k = localArrayList2.size();
      m = 0;
      if (m < k);
    }
    else
    {
      if (this.mMenu == null)
        break label226;
      localArrayList1 = this.mMenu.getNonActionItems();
      i = 0;
      if ((this.mReserveOverflow) && (localArrayList1 != null))
      {
        j = localArrayList1.size();
        if (j != 1)
          break label236;
        if (!((MenuItemImpl)localArrayList1.get(0)).isActionViewExpanded())
          break label231;
        i = 0;
      }
      label94: if (i == 0)
        break label251;
      if (this.mOverflowButton == null)
        this.mOverflowButton = new OverflowMenuButton(this.mSystemContext);
      ViewGroup localViewGroup = (ViewGroup)this.mOverflowButton.getParent();
      if (localViewGroup != this.mMenuView)
      {
        if (localViewGroup != null)
          localViewGroup.removeView(this.mOverflowButton);
        ActionMenuView localActionMenuView = (ActionMenuView)this.mMenuView;
        localActionMenuView.addView(this.mOverflowButton, localActionMenuView.generateOverflowButtonLayoutParams());
      }
    }
    while (true)
    {
      ((ActionMenuView)this.mMenuView).setOverflowReserved(this.mReserveOverflow);
      return;
      ActionProvider localActionProvider = ((MenuItemImpl)localArrayList2.get(m)).getActionProvider();
      if (localActionProvider != null)
        localActionProvider.setSubUiVisibilityListener(this);
      m++;
      break;
      label226: localArrayList1 = null;
      break label53;
      label231: i = 1;
      break label94;
      label236: if (j > 0);
      for (i = 1; ; i = 0)
        break;
      label251: if ((this.mOverflowButton == null) || (this.mOverflowButton.getParent() != this.mMenuView))
        continue;
      ((ViewGroup)this.mMenuView).removeView(this.mOverflowButton);
    }
  }

  private class ActionButtonSubmenu extends MenuPopupHelper
  {
    public ActionButtonSubmenu(Context paramSubMenuBuilder, SubMenuBuilder arg3)
    {
      super(localMenuBuilder);
      View localView;
      boolean bool;
      int i;
      if (!((MenuItemImpl)localMenuBuilder.getItem()).isActionButton())
      {
        if (ActionMenuPresenter.this.mOverflowButton == null)
        {
          localView = (View)ActionMenuPresenter.this.mMenuView;
          setAnchorView(localView);
        }
      }
      else
      {
        setCallback(ActionMenuPresenter.this.mPopupPresenterCallback);
        bool = false;
        i = localMenuBuilder.size();
      }
      label123: for (int j = 0; ; j++)
      {
        if (j >= i);
        while (true)
        {
          setForceShowIcon(bool);
          return;
          localView = ActionMenuPresenter.this.mOverflowButton;
          break;
          MenuItem localMenuItem = localMenuBuilder.getItem(j);
          if ((!localMenuItem.isVisible()) || (localMenuItem.getIcon() == null))
            break label123;
          bool = true;
        }
      }
    }

    public void onDismiss()
    {
      super.onDismiss();
      ActionMenuPresenter.this.mActionButtonPopup = null;
      ActionMenuPresenter.this.mOpenSubMenuId = 0;
    }
  }

  private static class HasPermanentMenuKey
  {
    public static boolean get(Context paramContext)
    {
      return ViewConfiguration.get(paramContext).hasPermanentMenuKey();
    }
  }

  private class OpenOverflowRunnable
    implements Runnable
  {
    private ActionMenuPresenter.OverflowPopup mPopup;

    public OpenOverflowRunnable(ActionMenuPresenter.OverflowPopup arg2)
    {
      Object localObject;
      this.mPopup = localObject;
    }

    public void run()
    {
      ActionMenuPresenter.this.mMenu.changeMenuMode();
      View localView = (View)ActionMenuPresenter.this.mMenuView;
      if ((localView != null) && (localView.getWindowToken() != null) && (this.mPopup.tryShow()))
        ActionMenuPresenter.this.mOverflowPopup = this.mPopup;
      ActionMenuPresenter.this.mPostedOpenRunnable = null;
    }
  }

  private class OverflowMenuButton extends ImageButton
    implements ActionMenuView.ActionMenuChildView, View_HasStateListenerSupport
  {
    private final Set<View_OnAttachStateChangeListener> mListeners = new HashSet();

    public OverflowMenuButton(Context arg2)
    {
      super(null, R.attr.actionOverflowButtonStyle);
      setClickable(true);
      setFocusable(true);
      setVisibility(0);
      setEnabled(true);
    }

    public void addOnAttachStateChangeListener(View_OnAttachStateChangeListener paramView_OnAttachStateChangeListener)
    {
      this.mListeners.add(paramView_OnAttachStateChangeListener);
    }

    public boolean needsDividerAfter()
    {
      return false;
    }

    public boolean needsDividerBefore()
    {
      return false;
    }

    protected void onAttachedToWindow()
    {
      super.onAttachedToWindow();
      Iterator localIterator = this.mListeners.iterator();
      while (true)
      {
        if (!localIterator.hasNext())
          return;
        ((View_OnAttachStateChangeListener)localIterator.next()).onViewAttachedToWindow(this);
      }
    }

    protected void onDetachedFromWindow()
    {
      super.onDetachedFromWindow();
      Iterator localIterator = this.mListeners.iterator();
      while (true)
      {
        if (!localIterator.hasNext())
          return;
        ((View_OnAttachStateChangeListener)localIterator.next()).onViewDetachedFromWindow(this);
      }
    }

    public boolean performClick()
    {
      if (super.performClick());
      while (true)
      {
        return true;
        playSoundEffect(0);
        ActionMenuPresenter.this.showOverflowMenu();
      }
    }

    public void removeOnAttachStateChangeListener(View_OnAttachStateChangeListener paramView_OnAttachStateChangeListener)
    {
      this.mListeners.remove(paramView_OnAttachStateChangeListener);
    }
  }

  private class OverflowPopup extends MenuPopupHelper
  {
    public OverflowPopup(Context paramMenuBuilder, MenuBuilder paramView, View paramBoolean, boolean arg5)
    {
      super(paramView, paramBoolean, bool);
      setCallback(ActionMenuPresenter.this.mPopupPresenterCallback);
    }

    public void onDismiss()
    {
      super.onDismiss();
      ActionMenuPresenter.this.mMenu.close();
      ActionMenuPresenter.this.mOverflowPopup = null;
    }
  }

  private class PopupPresenterCallback
    implements MenuPresenter.Callback
  {
    private PopupPresenterCallback()
    {
    }

    public void onCloseMenu(MenuBuilder paramMenuBuilder, boolean paramBoolean)
    {
      if ((paramMenuBuilder instanceof SubMenuBuilder))
        ((SubMenuBuilder)paramMenuBuilder).getRootMenu().close(false);
    }

    public boolean onOpenSubMenu(MenuBuilder paramMenuBuilder)
    {
      if (paramMenuBuilder == null);
      while (true)
      {
        return false;
        ActionMenuPresenter.this.mOpenSubMenuId = ((SubMenuBuilder)paramMenuBuilder).getItem().getItemId();
      }
    }
  }

  private static class SavedState
    implements Parcelable
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new ActionMenuPresenter.SavedState.1();
    public int openSubMenuId;

    SavedState()
    {
    }

    SavedState(Parcel paramParcel)
    {
      this.openSubMenuId = paramParcel.readInt();
    }

    public int describeContents()
    {
      return 0;
    }

    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      paramParcel.writeInt(this.openSubMenuId);
    }
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.actionbarsherlock.internal.view.menu.ActionMenuPresenter
 * JD-Core Version:    0.6.0
 */