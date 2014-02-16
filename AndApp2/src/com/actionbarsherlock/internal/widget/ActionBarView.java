package com.actionbarsherlock.internal.widget;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.actionbarsherlock.R.attr;
import com.actionbarsherlock.R.bool;
import com.actionbarsherlock.R.id;
import com.actionbarsherlock.R.layout;
import com.actionbarsherlock.R.string;
import com.actionbarsherlock.R.styleable;
import com.actionbarsherlock.app.ActionBar.LayoutParams;
import com.actionbarsherlock.app.ActionBar.OnNavigationListener;
import com.actionbarsherlock.internal.ActionBarSherlockCompat;
import com.actionbarsherlock.internal.ResourcesCompat;
import com.actionbarsherlock.internal.view.menu.ActionMenuItem;
import com.actionbarsherlock.internal.view.menu.ActionMenuPresenter;
import com.actionbarsherlock.internal.view.menu.ActionMenuView;
import com.actionbarsherlock.internal.view.menu.MenuBuilder;
import com.actionbarsherlock.internal.view.menu.MenuItemImpl;
import com.actionbarsherlock.internal.view.menu.MenuPresenter;
import com.actionbarsherlock.internal.view.menu.MenuPresenter.Callback;
import com.actionbarsherlock.internal.view.menu.MenuView;
import com.actionbarsherlock.internal.view.menu.SubMenuBuilder;
import com.actionbarsherlock.view.CollapsibleActionView;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.Window.Callback;
import java.util.List;

public class ActionBarView extends AbsActionBarView
{
  private static final boolean DEBUG = false;
  private static final int DEFAULT_CUSTOM_GRAVITY = 19;
  public static final int DISPLAY_DEFAULT = 0;
  private static final int DISPLAY_RELAYOUT_MASK = 31;
  private static final String TAG = "ActionBarView";
  private ActionBar.OnNavigationListener mCallback;
  private ActionBarContextView mContextView;
  private View mCustomNavView;
  private int mDisplayOptions = -1;
  View mExpandedActionView;
  private final View.OnClickListener mExpandedActionViewUpListener = new View.OnClickListener()
  {
    public void onClick(View paramView)
    {
      MenuItemImpl localMenuItemImpl = ActionBarView.this.mExpandedMenuPresenter.mCurrentExpandedItem;
      if (localMenuItemImpl != null)
        localMenuItemImpl.collapseActionView();
    }
  };
  private HomeView mExpandedHomeLayout;
  private ExpandedActionViewMenuPresenter mExpandedMenuPresenter;
  private HomeView mHomeLayout;
  private Drawable mIcon;
  private boolean mIncludeTabs;
  private int mIndeterminateProgressStyle;
  private IcsProgressBar mIndeterminateProgressView;
  private boolean mIsCollapsable;
  private boolean mIsCollapsed;
  private int mItemPadding;
  private IcsLinearLayout mListNavLayout;
  private Drawable mLogo;
  private ActionMenuItem mLogoNavItem;
  private final IcsAdapterView.OnItemSelectedListener mNavItemSelectedListener = new IcsAdapterView.OnItemSelectedListener()
  {
    public void onItemSelected(IcsAdapterView paramIcsAdapterView, View paramView, int paramInt, long paramLong)
    {
      if (ActionBarView.this.mCallback != null)
        ActionBarView.this.mCallback.onNavigationItemSelected(paramInt, paramLong);
    }

    public void onNothingSelected(IcsAdapterView paramIcsAdapterView)
    {
    }
  };
  private int mNavigationMode;
  private MenuBuilder mOptionsMenu;
  private int mProgressBarPadding;
  private int mProgressStyle;
  private IcsProgressBar mProgressView;
  private IcsSpinner mSpinner;
  private SpinnerAdapter mSpinnerAdapter;
  private CharSequence mSubtitle;
  private int mSubtitleStyleRes;
  private TextView mSubtitleView;
  private ScrollingTabContainerView mTabScrollView;
  private CharSequence mTitle;
  private LinearLayout mTitleLayout;
  private int mTitleStyleRes;
  private View mTitleUpView;
  private TextView mTitleView;
  private final View.OnClickListener mUpClickListener = new View.OnClickListener()
  {
    public void onClick(View paramView)
    {
      ActionBarView.this.mWindowCallback.onMenuItemSelected(0, ActionBarView.this.mLogoNavItem);
    }
  };
  private boolean mUserTitle;
  Window.Callback mWindowCallback;

  public ActionBarView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setBackgroundResource(0);
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.SherlockActionBar, R.attr.actionBarStyle, 0);
    ApplicationInfo localApplicationInfo = paramContext.getApplicationInfo();
    PackageManager localPackageManager = paramContext.getPackageManager();
    this.mNavigationMode = localTypedArray.getInt(6, 0);
    this.mTitle = localTypedArray.getText(8);
    this.mSubtitle = localTypedArray.getText(9);
    this.mLogo = localTypedArray.getDrawable(11);
    if (this.mLogo == null)
    {
      if (Build.VERSION.SDK_INT >= 11)
        break label493;
      if ((paramContext instanceof Activity))
      {
        int k = loadLogoFromManifest((Activity)paramContext);
        if (k != 0)
          this.mLogo = paramContext.getResources().getDrawable(k);
      }
    }
    while (true)
    {
      this.mIcon = localTypedArray.getDrawable(10);
      if ((this.mIcon != null) || ((paramContext instanceof Activity)));
      try
      {
        this.mIcon = localPackageManager.getActivityIcon(((Activity)paramContext).getComponentName());
        if (this.mIcon == null)
          this.mIcon = localApplicationInfo.loadIcon(localPackageManager);
        LayoutInflater localLayoutInflater = LayoutInflater.from(paramContext);
        int i = localTypedArray.getResourceId(14, R.layout.abs__action_bar_home);
        this.mHomeLayout = ((HomeView)localLayoutInflater.inflate(i, this, false));
        this.mExpandedHomeLayout = ((HomeView)localLayoutInflater.inflate(i, this, false));
        this.mExpandedHomeLayout.setUp(true);
        this.mExpandedHomeLayout.setOnClickListener(this.mExpandedActionViewUpListener);
        this.mExpandedHomeLayout.setContentDescription(getResources().getText(R.string.abs__action_bar_up_description));
        this.mTitleStyleRes = localTypedArray.getResourceId(0, 0);
        this.mSubtitleStyleRes = localTypedArray.getResourceId(1, 0);
        this.mProgressStyle = localTypedArray.getResourceId(15, 0);
        this.mIndeterminateProgressStyle = localTypedArray.getResourceId(16, 0);
        this.mProgressBarPadding = localTypedArray.getDimensionPixelOffset(17, 0);
        this.mItemPadding = localTypedArray.getDimensionPixelOffset(18, 0);
        setDisplayOptions(localTypedArray.getInt(7, 0));
        int j = localTypedArray.getResourceId(13, 0);
        if (j != 0)
        {
          this.mCustomNavView = localLayoutInflater.inflate(j, this, false);
          this.mNavigationMode = 0;
          setDisplayOptions(0x10 | this.mDisplayOptions);
        }
        this.mContentHeight = localTypedArray.getLayoutDimension(4, 0);
        localTypedArray.recycle();
        this.mLogoNavItem = new ActionMenuItem(paramContext, 0, 16908332, 0, 0, this.mTitle);
        this.mHomeLayout.setOnClickListener(this.mUpClickListener);
        this.mHomeLayout.setClickable(true);
        this.mHomeLayout.setFocusable(true);
        return;
        label493: if ((paramContext instanceof Activity));
        try
        {
          this.mLogo = localPackageManager.getActivityLogo(((Activity)paramContext).getComponentName());
          if (this.mLogo != null)
            continue;
          this.mLogo = localApplicationInfo.loadLogo(localPackageManager);
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException2)
        {
          while (true)
            Log.e("ActionBarView", "Activity component name not found!", localNameNotFoundException2);
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException1)
      {
        while (true)
          Log.e("ActionBarView", "Activity component name not found!", localNameNotFoundException1);
      }
    }
  }

  private void configPresenters(MenuBuilder paramMenuBuilder)
  {
    if (paramMenuBuilder != null)
    {
      paramMenuBuilder.addMenuPresenter(this.mActionMenuPresenter);
      paramMenuBuilder.addMenuPresenter(this.mExpandedMenuPresenter);
    }
    while (true)
    {
      return;
      this.mActionMenuPresenter.initForMenu(this.mContext, null);
      this.mExpandedMenuPresenter.initForMenu(this.mContext, null);
      this.mActionMenuPresenter.updateMenuView(true);
      this.mExpandedMenuPresenter.updateMenuView(true);
    }
  }

  private void initTitle()
  {
    boolean bool1 = true;
    boolean bool2;
    boolean bool3;
    label200: int i;
    label217: LinearLayout localLinearLayout;
    if (this.mTitleLayout == null)
    {
      this.mTitleLayout = ((LinearLayout)LayoutInflater.from(getContext()).inflate(R.layout.abs__action_bar_title_item, this, false));
      this.mTitleView = ((TextView)this.mTitleLayout.findViewById(R.id.abs__action_bar_title));
      this.mSubtitleView = ((TextView)this.mTitleLayout.findViewById(R.id.abs__action_bar_subtitle));
      this.mTitleUpView = this.mTitleLayout.findViewById(R.id.abs__up);
      this.mTitleLayout.setOnClickListener(this.mUpClickListener);
      if (this.mTitleStyleRes != 0)
        this.mTitleView.setTextAppearance(this.mContext, this.mTitleStyleRes);
      if (this.mTitle != null)
        this.mTitleView.setText(this.mTitle);
      if (this.mSubtitleStyleRes != 0)
        this.mSubtitleView.setTextAppearance(this.mContext, this.mSubtitleStyleRes);
      if (this.mSubtitle != null)
      {
        this.mSubtitleView.setText(this.mSubtitle);
        this.mSubtitleView.setVisibility(0);
      }
      if ((0x4 & this.mDisplayOptions) == 0)
        break label289;
      bool2 = bool1;
      if ((0x2 & this.mDisplayOptions) == 0)
        break label294;
      bool3 = bool1;
      View localView = this.mTitleUpView;
      if (bool3)
        break label305;
      if (!bool2)
        break label299;
      i = 0;
      localView.setVisibility(i);
      localLinearLayout = this.mTitleLayout;
      if ((!bool2) || (bool3))
        break label312;
    }
    while (true)
    {
      localLinearLayout.setEnabled(bool1);
      addView(this.mTitleLayout);
      if ((this.mExpandedActionView != null) || ((TextUtils.isEmpty(this.mTitle)) && (TextUtils.isEmpty(this.mSubtitle))))
        this.mTitleLayout.setVisibility(8);
      return;
      label289: bool2 = false;
      break;
      label294: bool3 = false;
      break label200;
      label299: i = 4;
      break label217;
      label305: i = 8;
      break label217;
      label312: bool1 = false;
    }
  }

  private static int loadLogoFromManifest(Activity paramActivity)
  {
    int i = 0;
    label279: label285: label287: label295: label299: label301: label306: label314: label318: 
    while (true)
    {
      int j;
      int n;
      int k;
      int m;
      try
      {
        String str1 = paramActivity.getClass().getName();
        String str2 = paramActivity.getApplicationInfo().packageName;
        XmlResourceParser localXmlResourceParser = paramActivity.createPackageContext(str2, 0).getAssets().openXmlResourceParser("AndroidManifest.xml");
        j = localXmlResourceParser.getEventType();
        break label279;
        if (j != 2)
          continue;
        String str3 = localXmlResourceParser.getName();
        if (!"application".equals(str3))
          continue;
        n = -1 + localXmlResourceParser.getAttributeCount();
        break label287;
        j = localXmlResourceParser.nextToken();
        break label279;
        if (!"logo".equals(localXmlResourceParser.getAttributeName(n)))
          break label295;
        i = localXmlResourceParser.getAttributeResourceValue(n, 0);
        continue;
        if (!"activity".equals(str3))
          continue;
        Integer localInteger = null;
        String str4 = null;
        k = 0;
        m = -1 + localXmlResourceParser.getAttributeCount();
        break label301;
        String str5 = localXmlResourceParser.getAttributeName(m);
        if (!"logo".equals(str5))
          continue;
        localInteger = Integer.valueOf(localXmlResourceParser.getAttributeResourceValue(m, 0));
        if ((localInteger == null) || (str4 == null))
          break label314;
        i = localInteger.intValue();
        break label314;
        if (!"name".equals(str5))
          continue;
        str4 = ActionBarSherlockCompat.cleanActivityName(str2, localXmlResourceParser.getAttributeValue(m));
        boolean bool = str1.equals(str4);
        if (!bool)
          break label306;
        k = 1;
        continue;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      if (j == 1)
      {
        return i;
        while (true)
        {
          if (n >= 0)
            break label299;
          break;
          n--;
        }
        continue;
        while (true)
        {
          if (m >= 0)
            break label318;
          if (k == 0)
            break;
          break label285;
          m--;
        }
      }
    }
  }

  private void setTitleImpl(CharSequence paramCharSequence)
  {
    int i = 0;
    this.mTitle = paramCharSequence;
    int j;
    LinearLayout localLinearLayout;
    if (this.mTitleView != null)
    {
      this.mTitleView.setText(paramCharSequence);
      if ((this.mExpandedActionView != null) || ((0x8 & this.mDisplayOptions) == 0) || ((TextUtils.isEmpty(this.mTitle)) && (TextUtils.isEmpty(this.mSubtitle))))
        break label96;
      j = 1;
      localLinearLayout = this.mTitleLayout;
      if (j == 0)
        break label102;
    }
    while (true)
    {
      localLinearLayout.setVisibility(i);
      if (this.mLogoNavItem != null)
        this.mLogoNavItem.setTitle(paramCharSequence);
      return;
      label96: j = 0;
      break;
      label102: i = 8;
    }
  }

  public void collapseActionView()
  {
    if (this.mExpandedMenuPresenter == null);
    for (MenuItemImpl localMenuItemImpl = null; ; localMenuItemImpl = this.mExpandedMenuPresenter.mCurrentExpandedItem)
    {
      if (localMenuItemImpl != null)
        localMenuItemImpl.collapseActionView();
      return;
    }
  }

  protected ViewGroup.LayoutParams generateDefaultLayoutParams()
  {
    return new ActionBar.LayoutParams(19);
  }

  public ViewGroup.LayoutParams generateLayoutParams(AttributeSet paramAttributeSet)
  {
    return new ActionBar.LayoutParams(getContext(), paramAttributeSet);
  }

  public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    if (paramLayoutParams == null)
      paramLayoutParams = generateDefaultLayoutParams();
    return paramLayoutParams;
  }

  public View getCustomNavigationView()
  {
    return this.mCustomNavView;
  }

  public int getDisplayOptions()
  {
    return this.mDisplayOptions;
  }

  public SpinnerAdapter getDropdownAdapter()
  {
    return this.mSpinnerAdapter;
  }

  public int getDropdownSelectedPosition()
  {
    return this.mSpinner.getSelectedItemPosition();
  }

  public int getNavigationMode()
  {
    return this.mNavigationMode;
  }

  public CharSequence getSubtitle()
  {
    return this.mSubtitle;
  }

  public CharSequence getTitle()
  {
    return this.mTitle;
  }

  public boolean hasEmbeddedTabs()
  {
    return this.mIncludeTabs;
  }

  public boolean hasExpandedActionView()
  {
    if ((this.mExpandedMenuPresenter != null) && (this.mExpandedMenuPresenter.mCurrentExpandedItem != null));
    for (int i = 1; ; i = 0)
      return i;
  }

  public void initIndeterminateProgress()
  {
    this.mIndeterminateProgressView = new IcsProgressBar(this.mContext, null, 0, this.mIndeterminateProgressStyle);
    this.mIndeterminateProgressView.setId(R.id.abs__progress_circular);
    addView(this.mIndeterminateProgressView);
  }

  public void initProgress()
  {
    this.mProgressView = new IcsProgressBar(this.mContext, null, 0, this.mProgressStyle);
    this.mProgressView.setId(R.id.abs__progress_horizontal);
    this.mProgressView.setMax(10000);
    addView(this.mProgressView);
  }

  public boolean isCollapsed()
  {
    return this.mIsCollapsed;
  }

  public boolean isSplitActionBar()
  {
    return this.mSplitActionBar;
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    this.mTitleView = null;
    this.mSubtitleView = null;
    this.mTitleUpView = null;
    if ((this.mTitleLayout != null) && (this.mTitleLayout.getParent() == this))
      removeView(this.mTitleLayout);
    this.mTitleLayout = null;
    if ((0x8 & this.mDisplayOptions) != 0)
      initTitle();
    if ((this.mTabScrollView != null) && (this.mIncludeTabs))
    {
      ViewGroup.LayoutParams localLayoutParams = this.mTabScrollView.getLayoutParams();
      if (localLayoutParams != null)
      {
        localLayoutParams.width = -2;
        localLayoutParams.height = -1;
      }
      this.mTabScrollView.setAllowCollapse(true);
    }
  }

  public void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    if (this.mActionMenuPresenter != null)
    {
      this.mActionMenuPresenter.hideOverflowMenu();
      this.mActionMenuPresenter.hideSubMenus();
    }
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    addView(this.mHomeLayout);
    if ((this.mCustomNavView != null) && ((0x10 & this.mDisplayOptions) != 0))
    {
      ViewParent localViewParent = this.mCustomNavView.getParent();
      if (localViewParent != this)
      {
        if ((localViewParent instanceof ViewGroup))
          ((ViewGroup)localViewParent).removeView(this.mCustomNavView);
        addView(this.mCustomNavView);
      }
    }
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = getPaddingLeft();
    int j = getPaddingTop();
    int k = paramInt4 - paramInt2 - getPaddingTop() - getPaddingBottom();
    if (k <= 0)
      return;
    HomeView localHomeView;
    label47: int i14;
    label126: int m;
    label180: View localView;
    label298: ActionBar.LayoutParams localLayoutParams1;
    label325: int i1;
    label337: int i2;
    int i3;
    int i4;
    int i5;
    int i13;
    label428: int i6;
    label468: int i8;
    if (this.mExpandedActionView != null)
    {
      localHomeView = this.mExpandedHomeLayout;
      if (localHomeView.getVisibility() != 8)
      {
        int i15 = localHomeView.getLeftOffset();
        i += i15 + positionChild(localHomeView, i + i15, j, k);
      }
      if (this.mExpandedActionView == null)
      {
        if ((this.mTitleLayout == null) || (this.mTitleLayout.getVisibility() == 8) || ((0x8 & this.mDisplayOptions) == 0))
          break label632;
        i14 = 1;
        if (i14 != 0)
          i += positionChild(this.mTitleLayout, i, j, k);
      }
      switch (this.mNavigationMode)
      {
      case 0:
      default:
        m = paramInt3 - paramInt1 - getPaddingRight();
        if ((this.mMenuView != null) && (this.mMenuView.getParent() == this))
        {
          positionChildInverse(this.mMenuView, m, j, k);
          m -= this.mMenuView.getMeasuredWidth();
        }
        if ((this.mIndeterminateProgressView != null) && (this.mIndeterminateProgressView.getVisibility() != 8))
        {
          positionChildInverse(this.mIndeterminateProgressView, m, j, k);
          m -= this.mIndeterminateProgressView.getMeasuredWidth();
        }
        localView = null;
        if (this.mExpandedActionView != null)
        {
          localView = this.mExpandedActionView;
          if (localView == null)
            break;
          ViewGroup.LayoutParams localLayoutParams = localView.getLayoutParams();
          if (!(localLayoutParams instanceof ActionBar.LayoutParams))
            break label760;
          localLayoutParams1 = (ActionBar.LayoutParams)localLayoutParams;
          if (localLayoutParams1 == null)
            break label766;
          i1 = localLayoutParams1.gravity;
          i2 = localView.getMeasuredWidth();
          i3 = 0;
          i4 = 0;
          if (localLayoutParams1 != null)
          {
            i += localLayoutParams1.leftMargin;
            m -= localLayoutParams1.rightMargin;
            i3 = localLayoutParams1.topMargin;
            i4 = localLayoutParams1.bottomMargin;
          }
          i5 = i1 & 0x7;
          if (i5 != 1)
            break label789;
          i13 = (getRight() - getLeft() - i2) / 2;
          if (i13 >= i)
            break label773;
          i5 = 3;
          i6 = 0;
          switch (i5)
          {
          case 2:
          case 4:
          default:
            int i7 = i1 & 0x70;
            if (i1 == -1)
              i7 = 16;
            i8 = 0;
            switch (i7)
            {
            default:
            case 16:
            case 48:
            case 80:
            }
          case 1:
          case 3:
          case 5:
          }
        }
      case 1:
      case 2:
      }
    }
    while (true)
    {
      int i9 = localView.getMeasuredWidth();
      int i10 = i6 + i9;
      int i11 = i8 + localView.getMeasuredHeight();
      localView.layout(i6, i8, i10, i11);
      (i + i9);
      if (this.mProgressView == null)
        break;
      this.mProgressView.bringToFront();
      int n = this.mProgressView.getMeasuredHeight() / 2;
      this.mProgressView.layout(this.mProgressBarPadding, -n, this.mProgressBarPadding + this.mProgressView.getMeasuredWidth(), n);
      break;
      localHomeView = this.mHomeLayout;
      break label47;
      label632: i14 = 0;
      break label126;
      if (this.mListNavLayout == null)
        break label180;
      if (i14 != 0)
        i += this.mItemPadding;
      i += positionChild(this.mListNavLayout, i, j, k) + this.mItemPadding;
      break label180;
      if (this.mTabScrollView == null)
        break label180;
      if (i14 != 0)
        i += this.mItemPadding;
      i += positionChild(this.mTabScrollView, i, j, k) + this.mItemPadding;
      break label180;
      if (((0x10 & this.mDisplayOptions) == 0) || (this.mCustomNavView == null))
        break label298;
      localView = this.mCustomNavView;
      break label298;
      label760: localLayoutParams1 = null;
      break label325;
      label766: i1 = 19;
      break label337;
      label773: if (i13 + i2 <= m)
        break label428;
      i5 = 5;
      break label428;
      label789: if (i1 != -1)
        break label428;
      i5 = 3;
      break label428;
      i6 = (getRight() - getLeft() - i2) / 2;
      break label468;
      i6 = i;
      break label468;
      i6 = m - i2;
      break label468;
      int i12 = getPaddingTop();
      i8 = (getBottom() - getTop() - getPaddingBottom() - i12 - localView.getMeasuredHeight()) / 2;
      continue;
      i8 = i3 + getPaddingTop();
      continue;
      i8 = getHeight() - getPaddingBottom() - localView.getMeasuredHeight() - i4;
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = getChildCount();
    if (this.mIsCollapsable)
    {
      int i30 = 0;
      for (int i31 = 0; ; i31++)
      {
        if (i31 >= i)
        {
          if (i30 != 0)
            break;
          setMeasuredDimension(0, 0);
          this.mIsCollapsed = true;
          return;
        }
        View localView2 = getChildAt(i31);
        if ((localView2.getVisibility() == 8) || ((localView2 == this.mMenuView) && (this.mMenuView.getChildCount() == 0)))
          continue;
        i30++;
      }
    }
    this.mIsCollapsed = false;
    if (View.MeasureSpec.getMode(paramInt1) != 1073741824)
      throw new IllegalStateException(getClass().getSimpleName() + " can only be used " + "with android:layout_width=\"match_parent\" (or fill_parent)");
    if (View.MeasureSpec.getMode(paramInt2) != -2147483648)
      throw new IllegalStateException(getClass().getSimpleName() + " can only be used " + "with android:layout_height=\"wrap_content\"");
    int j = View.MeasureSpec.getSize(paramInt1);
    int k;
    label211: int m;
    int i2;
    int i4;
    int i5;
    HomeView localHomeView;
    label284: ViewGroup.LayoutParams localLayoutParams2;
    int i27;
    label319: int i7;
    label504: View localView1;
    label536: label552: ViewGroup.LayoutParams localLayoutParams1;
    ActionBar.LayoutParams localLayoutParams;
    label583: int i13;
    int i15;
    label678: int i16;
    label698: int i18;
    label721: int i8;
    int i9;
    if (this.mContentHeight > 0)
    {
      k = this.mContentHeight;
      m = getPaddingTop() + getPaddingBottom();
      int n = getPaddingLeft();
      int i1 = getPaddingRight();
      i2 = k - m;
      int i3 = View.MeasureSpec.makeMeasureSpec(i2, -2147483648);
      i4 = j - n - i1;
      i5 = i4 / 2;
      int i6 = i5;
      if (this.mExpandedActionView == null)
        break label940;
      localHomeView = this.mExpandedHomeLayout;
      if (localHomeView.getVisibility() != 8)
      {
        localLayoutParams2 = localHomeView.getLayoutParams();
        if (localLayoutParams2.width >= 0)
          break label949;
        i27 = View.MeasureSpec.makeMeasureSpec(i4, -2147483648);
        int i28 = View.MeasureSpec.makeMeasureSpec(i2, 1073741824);
        localHomeView.measure(i27, i28);
        int i29 = localHomeView.getMeasuredWidth() + localHomeView.getLeftOffset();
        i4 = Math.max(0, i4 - i29);
        i5 = Math.max(0, i4 - i29);
      }
      if ((this.mMenuView != null) && (this.mMenuView.getParent() == this))
      {
        i4 = measureChildView(this.mMenuView, i4, i3, 0);
        i6 = Math.max(0, i6 - this.mMenuView.getMeasuredWidth());
      }
      if ((this.mIndeterminateProgressView != null) && (this.mIndeterminateProgressView.getVisibility() != 8))
      {
        i4 = measureChildView(this.mIndeterminateProgressView, i4, i3, 0);
        i6 = Math.max(0, i6 - this.mIndeterminateProgressView.getMeasuredWidth());
      }
      if ((this.mTitleLayout == null) || (this.mTitleLayout.getVisibility() == 8) || ((0x8 & this.mDisplayOptions) == 0))
        break label965;
      i7 = 1;
      if (this.mExpandedActionView == null);
      switch (this.mNavigationMode)
      {
      default:
        localView1 = null;
        if (this.mExpandedActionView == null)
          break;
        localView1 = this.mExpandedActionView;
        if (localView1 != null)
        {
          localLayoutParams1 = generateLayoutParams(localView1.getLayoutParams());
          if ((localLayoutParams1 instanceof ActionBar.LayoutParams))
          {
            localLayoutParams = (ActionBar.LayoutParams)localLayoutParams1;
            int i11 = 0;
            int i12 = 0;
            if (localLayoutParams != null)
            {
              i11 = localLayoutParams.leftMargin + localLayoutParams.rightMargin;
              i12 = localLayoutParams.topMargin + localLayoutParams.bottomMargin;
            }
            if (this.mContentHeight > 0)
              break label1219;
            i13 = -2147483648;
            if (localLayoutParams1.height >= 0)
              i2 = Math.min(localLayoutParams1.height, i2);
            int i14 = Math.max(0, i2 - i12);
            if (localLayoutParams1.width == -2)
              break label1245;
            i15 = 1073741824;
            if (localLayoutParams1.width < 0)
              break label1253;
            i16 = Math.min(localLayoutParams1.width, i4);
            int i17 = Math.max(0, i16 - i11);
            if (localLayoutParams == null)
              break label1260;
            i18 = localLayoutParams.gravity;
            if (((i18 & 0x7) == 1) && (localLayoutParams1.width == -1))
              i17 = 2 * Math.min(i5, i6);
            localView1.measure(View.MeasureSpec.makeMeasureSpec(i17, i15), View.MeasureSpec.makeMeasureSpec(i14, i13));
            i4 -= i11 + localView1.getMeasuredWidth();
          }
        }
        else
        {
          if ((this.mExpandedActionView == null) && (i7 != 0))
          {
            measureChildView(this.mTitleLayout, i4, View.MeasureSpec.makeMeasureSpec(this.mContentHeight, 1073741824), 0);
            Math.max(0, i5 - this.mTitleLayout.getMeasuredWidth());
          }
          if (this.mContentHeight > 0)
            break label1298;
          i8 = 0;
          i9 = 0;
          label845: if (i9 < i)
            break label1267;
          setMeasuredDimension(j, i8);
        }
      case 1:
      case 2:
      }
    }
    while (true)
    {
      if (this.mContextView != null)
        this.mContextView.setContentHeight(getMeasuredHeight());
      if ((this.mProgressView == null) || (this.mProgressView.getVisibility() == 8))
        break;
      this.mProgressView.measure(View.MeasureSpec.makeMeasureSpec(j - 2 * this.mProgressBarPadding, 1073741824), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), -2147483648));
      break;
      k = View.MeasureSpec.getSize(paramInt2);
      break label211;
      label940: localHomeView = this.mHomeLayout;
      break label284;
      label949: i27 = View.MeasureSpec.makeMeasureSpec(localLayoutParams2.width, 1073741824);
      break label319;
      label965: i7 = 0;
      break label504;
      if (this.mListNavLayout == null)
        break label536;
      if (i7 != 0);
      for (int i23 = 2 * this.mItemPadding; ; i23 = this.mItemPadding)
      {
        int i24 = Math.max(0, i4 - i23);
        int i25 = Math.max(0, i5 - i23);
        this.mListNavLayout.measure(View.MeasureSpec.makeMeasureSpec(i24, -2147483648), View.MeasureSpec.makeMeasureSpec(i2, 1073741824));
        int i26 = this.mListNavLayout.getMeasuredWidth();
        i4 = Math.max(0, i24 - i26);
        i5 = Math.max(0, i25 - i26);
        break;
      }
      if (this.mTabScrollView == null)
        break label536;
      if (i7 != 0);
      for (int i19 = 2 * this.mItemPadding; ; i19 = this.mItemPadding)
      {
        int i20 = Math.max(0, i4 - i19);
        int i21 = Math.max(0, i5 - i19);
        this.mTabScrollView.measure(View.MeasureSpec.makeMeasureSpec(i20, -2147483648), View.MeasureSpec.makeMeasureSpec(i2, 1073741824));
        int i22 = this.mTabScrollView.getMeasuredWidth();
        i4 = Math.max(0, i20 - i22);
        i5 = Math.max(0, i21 - i22);
        break;
      }
      if (((0x10 & this.mDisplayOptions) == 0) || (this.mCustomNavView == null))
        break label552;
      localView1 = this.mCustomNavView;
      break label552;
      localLayoutParams = null;
      break label583;
      label1219: if (localLayoutParams1.height != -2);
      for (i13 = 1073741824; ; i13 = -2147483648)
        break;
      label1245: i15 = -2147483648;
      break label678;
      label1253: i16 = i4;
      break label698;
      label1260: i18 = 19;
      break label721;
      label1267: int i10 = m + getChildAt(i9).getMeasuredHeight();
      if (i10 > i8)
        i8 = i10;
      i9++;
      break label845;
      label1298: setMeasuredDimension(j, k);
    }
  }

  public void onRestoreInstanceState(Parcelable paramParcelable)
  {
    SavedState localSavedState = (SavedState)paramParcelable;
    super.onRestoreInstanceState(localSavedState.getSuperState());
    if ((localSavedState.expandedMenuItemId != 0) && (this.mExpandedMenuPresenter != null) && (this.mOptionsMenu != null))
    {
      MenuItem localMenuItem = this.mOptionsMenu.findItem(localSavedState.expandedMenuItemId);
      if (localMenuItem != null)
        localMenuItem.expandActionView();
    }
    if (localSavedState.isOverflowOpen)
      postShowOverflowMenu();
  }

  public Parcelable onSaveInstanceState()
  {
    SavedState localSavedState = new SavedState(super.onSaveInstanceState());
    if ((this.mExpandedMenuPresenter != null) && (this.mExpandedMenuPresenter.mCurrentExpandedItem != null))
      localSavedState.expandedMenuItemId = this.mExpandedMenuPresenter.mCurrentExpandedItem.getItemId();
    localSavedState.isOverflowOpen = isOverflowMenuShowing();
    return localSavedState;
  }

  public void setCallback(ActionBar.OnNavigationListener paramOnNavigationListener)
  {
    this.mCallback = paramOnNavigationListener;
  }

  public void setCollapsable(boolean paramBoolean)
  {
    this.mIsCollapsable = paramBoolean;
  }

  public void setContextView(ActionBarContextView paramActionBarContextView)
  {
    this.mContextView = paramActionBarContextView;
  }

  public void setCustomNavigationView(View paramView)
  {
    if ((0x10 & this.mDisplayOptions) != 0);
    for (int i = 1; ; i = 0)
    {
      if ((this.mCustomNavView != null) && (i != 0))
        removeView(this.mCustomNavView);
      this.mCustomNavView = paramView;
      if ((this.mCustomNavView != null) && (i != 0))
        addView(this.mCustomNavView);
      return;
    }
  }

  public void setDisplayOptions(int paramInt)
  {
    int i = 8;
    int j = -1;
    boolean bool1 = true;
    boolean bool2;
    label39: int k;
    label54: boolean bool5;
    label79: boolean bool4;
    label122: Drawable localDrawable;
    label139: label164: boolean bool3;
    if (this.mDisplayOptions == j)
    {
      this.mDisplayOptions = paramInt;
      if ((j & 0x1F) == 0)
        break label372;
      if ((paramInt & 0x2) == 0)
        break label300;
      bool2 = bool1;
      if ((!bool2) || (this.mExpandedActionView != null))
        break label306;
      k = 0;
      this.mHomeLayout.setVisibility(k);
      if ((j & 0x4) != 0)
      {
        if ((paramInt & 0x4) == 0)
          break label312;
        bool5 = bool1;
        this.mHomeLayout.setUp(bool5);
        if (bool5)
          setHomeButtonEnabled(bool1);
      }
      if ((j & 0x1) != 0)
      {
        if ((this.mLogo == null) || ((paramInt & 0x1) == 0))
          break label318;
        bool4 = bool1;
        HomeView localHomeView = this.mHomeLayout;
        if (!bool4)
          break label324;
        localDrawable = this.mLogo;
        localHomeView.setIcon(localDrawable);
      }
      if ((j & 0x8) != 0)
      {
        if ((paramInt & 0x8) == 0)
          break label333;
        initTitle();
      }
      if ((this.mTitleLayout != null) && ((j & 0x6) != 0))
      {
        if ((0x4 & this.mDisplayOptions) == 0)
          break label344;
        bool3 = bool1;
        label191: View localView = this.mTitleUpView;
        if (!bool2)
        {
          if (!bool3)
            break label350;
          i = 0;
        }
        label209: localView.setVisibility(i);
        LinearLayout localLinearLayout = this.mTitleLayout;
        if ((bool2) || (!bool3))
          break label355;
        label231: localLinearLayout.setEnabled(bool1);
      }
      if (((j & 0x10) != 0) && (this.mCustomNavView != null))
      {
        if ((paramInt & 0x10) == 0)
          break label361;
        addView(this.mCustomNavView);
      }
      label267: requestLayout();
      label271: if (this.mHomeLayout.isEnabled())
        break label379;
      this.mHomeLayout.setContentDescription(null);
    }
    while (true)
    {
      return;
      j = paramInt ^ this.mDisplayOptions;
      break;
      label300: bool2 = false;
      break label39;
      label306: k = i;
      break label54;
      label312: bool5 = false;
      break label79;
      label318: bool4 = false;
      break label122;
      label324: localDrawable = this.mIcon;
      break label139;
      label333: removeView(this.mTitleLayout);
      break label164;
      label344: bool3 = false;
      break label191;
      label350: i = 4;
      break label209;
      label355: bool1 = false;
      break label231;
      label361: removeView(this.mCustomNavView);
      break label267;
      label372: invalidate();
      break label271;
      label379: if ((paramInt & 0x4) != 0)
      {
        this.mHomeLayout.setContentDescription(this.mContext.getResources().getText(R.string.abs__action_bar_up_description));
        continue;
      }
      this.mHomeLayout.setContentDescription(this.mContext.getResources().getText(R.string.abs__action_bar_home_description));
    }
  }

  public void setDropdownAdapter(SpinnerAdapter paramSpinnerAdapter)
  {
    this.mSpinnerAdapter = paramSpinnerAdapter;
    if (this.mSpinner != null)
      this.mSpinner.setAdapter(paramSpinnerAdapter);
  }

  public void setDropdownSelectedPosition(int paramInt)
  {
    this.mSpinner.setSelection(paramInt);
  }

  public void setEmbeddedTabView(ScrollingTabContainerView paramScrollingTabContainerView)
  {
    if (this.mTabScrollView != null)
      removeView(this.mTabScrollView);
    this.mTabScrollView = paramScrollingTabContainerView;
    if (paramScrollingTabContainerView != null);
    for (boolean bool = true; ; bool = false)
    {
      this.mIncludeTabs = bool;
      if ((this.mIncludeTabs) && (this.mNavigationMode == 2))
      {
        addView(this.mTabScrollView);
        ViewGroup.LayoutParams localLayoutParams = this.mTabScrollView.getLayoutParams();
        localLayoutParams.width = -2;
        localLayoutParams.height = -1;
        paramScrollingTabContainerView.setAllowCollapse(true);
      }
      return;
    }
  }

  public void setHomeButtonEnabled(boolean paramBoolean)
  {
    this.mHomeLayout.setEnabled(paramBoolean);
    this.mHomeLayout.setFocusable(paramBoolean);
    if (!paramBoolean)
      this.mHomeLayout.setContentDescription(null);
    while (true)
    {
      return;
      if ((0x4 & this.mDisplayOptions) != 0)
      {
        this.mHomeLayout.setContentDescription(this.mContext.getResources().getText(R.string.abs__action_bar_up_description));
        continue;
      }
      this.mHomeLayout.setContentDescription(this.mContext.getResources().getText(R.string.abs__action_bar_home_description));
    }
  }

  public void setIcon(int paramInt)
  {
    setIcon(this.mContext.getResources().getDrawable(paramInt));
  }

  public void setIcon(Drawable paramDrawable)
  {
    this.mIcon = paramDrawable;
    if ((paramDrawable != null) && (((0x1 & this.mDisplayOptions) == 0) || (this.mLogo == null)))
      this.mHomeLayout.setIcon(paramDrawable);
  }

  public void setLogo(int paramInt)
  {
    setLogo(this.mContext.getResources().getDrawable(paramInt));
  }

  public void setLogo(Drawable paramDrawable)
  {
    this.mLogo = paramDrawable;
    if ((paramDrawable != null) && ((0x1 & this.mDisplayOptions) != 0))
      this.mHomeLayout.setIcon(paramDrawable);
  }

  public void setMenu(Menu paramMenu, MenuPresenter.Callback paramCallback)
  {
    if (paramMenu == this.mOptionsMenu)
      return;
    if (this.mOptionsMenu != null)
    {
      this.mOptionsMenu.removeMenuPresenter(this.mActionMenuPresenter);
      this.mOptionsMenu.removeMenuPresenter(this.mExpandedMenuPresenter);
    }
    MenuBuilder localMenuBuilder = (MenuBuilder)paramMenu;
    this.mOptionsMenu = localMenuBuilder;
    if (this.mMenuView != null)
    {
      ViewGroup localViewGroup3 = (ViewGroup)this.mMenuView.getParent();
      if (localViewGroup3 != null)
        localViewGroup3.removeView(this.mMenuView);
    }
    if (this.mActionMenuPresenter == null)
    {
      this.mActionMenuPresenter = new ActionMenuPresenter(this.mContext);
      this.mActionMenuPresenter.setCallback(paramCallback);
      this.mActionMenuPresenter.setId(R.id.abs__action_menu_presenter);
      this.mExpandedMenuPresenter = new ExpandedActionViewMenuPresenter(null);
    }
    ViewGroup.LayoutParams localLayoutParams = new ViewGroup.LayoutParams(-2, -1);
    ActionMenuView localActionMenuView;
    if (!this.mSplitActionBar)
    {
      this.mActionMenuPresenter.setExpandedActionViewsExclusive(ResourcesCompat.getResources_getBoolean(getContext(), R.bool.abs__action_bar_expanded_action_views_exclusive));
      configPresenters(localMenuBuilder);
      localActionMenuView = (ActionMenuView)this.mActionMenuPresenter.getMenuView(this);
      ViewGroup localViewGroup2 = (ViewGroup)localActionMenuView.getParent();
      if ((localViewGroup2 != null) && (localViewGroup2 != this))
        localViewGroup2.removeView(localActionMenuView);
      addView(localActionMenuView, localLayoutParams);
    }
    while (true)
    {
      this.mMenuView = localActionMenuView;
      break;
      this.mActionMenuPresenter.setExpandedActionViewsExclusive(false);
      this.mActionMenuPresenter.setWidthLimit(getContext().getResources().getDisplayMetrics().widthPixels, true);
      this.mActionMenuPresenter.setItemLimit(2147483647);
      localLayoutParams.width = -1;
      configPresenters(localMenuBuilder);
      localActionMenuView = (ActionMenuView)this.mActionMenuPresenter.getMenuView(this);
      if (this.mSplitView != null)
      {
        ViewGroup localViewGroup1 = (ViewGroup)localActionMenuView.getParent();
        if ((localViewGroup1 != null) && (localViewGroup1 != this.mSplitView))
          localViewGroup1.removeView(localActionMenuView);
        localActionMenuView.setVisibility(getAnimatedVisibility());
        this.mSplitView.addView(localActionMenuView, localLayoutParams);
        continue;
      }
      localActionMenuView.setLayoutParams(localLayoutParams);
    }
  }

  public void setNavigationMode(int paramInt)
  {
    int i = this.mNavigationMode;
    if (paramInt != i)
      switch (i)
      {
      default:
        switch (paramInt)
        {
        default:
        case 1:
        case 2:
        }
      case 1:
      case 2:
      }
    while (true)
    {
      this.mNavigationMode = paramInt;
      requestLayout();
      return;
      if (this.mListNavLayout == null)
        break;
      removeView(this.mListNavLayout);
      break;
      if ((this.mTabScrollView == null) || (!this.mIncludeTabs))
        break;
      removeView(this.mTabScrollView);
      break;
      if (this.mSpinner == null)
      {
        this.mSpinner = new IcsSpinner(this.mContext, null, R.attr.actionDropDownStyle);
        this.mListNavLayout = ((IcsLinearLayout)LayoutInflater.from(this.mContext).inflate(R.layout.abs__action_bar_tab_bar_view, null));
        LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-2, -1);
        localLayoutParams.gravity = 17;
        this.mListNavLayout.addView(this.mSpinner, localLayoutParams);
      }
      if (this.mSpinner.getAdapter() != this.mSpinnerAdapter)
        this.mSpinner.setAdapter(this.mSpinnerAdapter);
      this.mSpinner.setOnItemSelectedListener(this.mNavItemSelectedListener);
      addView(this.mListNavLayout);
      continue;
      if ((this.mTabScrollView == null) || (!this.mIncludeTabs))
        continue;
      addView(this.mTabScrollView);
    }
  }

  public void setSplitActionBar(boolean paramBoolean)
  {
    ActionBarContainer localActionBarContainer;
    if (this.mSplitActionBar != paramBoolean)
    {
      if (this.mMenuView != null)
      {
        ViewGroup localViewGroup = (ViewGroup)this.mMenuView.getParent();
        if (localViewGroup != null)
          localViewGroup.removeView(this.mMenuView);
        if (!paramBoolean)
          break label92;
        if (this.mSplitView != null)
          this.mSplitView.addView(this.mMenuView);
      }
      if (this.mSplitView != null)
      {
        localActionBarContainer = this.mSplitView;
        if (!paramBoolean)
          break label103;
      }
    }
    label92: label103: for (int i = 0; ; i = 8)
    {
      localActionBarContainer.setVisibility(i);
      super.setSplitActionBar(paramBoolean);
      return;
      addView(this.mMenuView);
      break;
    }
  }

  public void setSubtitle(CharSequence paramCharSequence)
  {
    int i = 0;
    this.mSubtitle = paramCharSequence;
    int j;
    int k;
    label80: LinearLayout localLinearLayout;
    if (this.mSubtitleView != null)
    {
      this.mSubtitleView.setText(paramCharSequence);
      TextView localTextView = this.mSubtitleView;
      if (paramCharSequence == null)
        break label98;
      j = 0;
      localTextView.setVisibility(j);
      if ((this.mExpandedActionView != null) || ((0x8 & this.mDisplayOptions) == 0) || ((TextUtils.isEmpty(this.mTitle)) && (TextUtils.isEmpty(this.mSubtitle))))
        break label105;
      k = 1;
      localLinearLayout = this.mTitleLayout;
      if (k == 0)
        break label111;
    }
    while (true)
    {
      localLinearLayout.setVisibility(i);
      return;
      label98: j = 8;
      break;
      label105: k = 0;
      break label80;
      label111: i = 8;
    }
  }

  public void setTitle(CharSequence paramCharSequence)
  {
    this.mUserTitle = true;
    setTitleImpl(paramCharSequence);
  }

  public void setWindowCallback(Window.Callback paramCallback)
  {
    this.mWindowCallback = paramCallback;
  }

  public void setWindowTitle(CharSequence paramCharSequence)
  {
    if (!this.mUserTitle)
      setTitleImpl(paramCharSequence);
  }

  public boolean shouldDelayChildPressedState()
  {
    return false;
  }

  private class ExpandedActionViewMenuPresenter
    implements MenuPresenter
  {
    MenuItemImpl mCurrentExpandedItem;
    MenuBuilder mMenu;

    private ExpandedActionViewMenuPresenter()
    {
    }

    public boolean collapseItemActionView(MenuBuilder paramMenuBuilder, MenuItemImpl paramMenuItemImpl)
    {
      if ((ActionBarView.this.mExpandedActionView instanceof CollapsibleActionView))
        ((CollapsibleActionView)ActionBarView.this.mExpandedActionView).onActionViewCollapsed();
      ActionBarView.this.removeView(ActionBarView.this.mExpandedActionView);
      ActionBarView.this.removeView(ActionBarView.this.mExpandedHomeLayout);
      ActionBarView.this.mExpandedActionView = null;
      if ((0x2 & ActionBarView.this.mDisplayOptions) != 0)
        ActionBarView.this.mHomeLayout.setVisibility(0);
      if ((0x8 & ActionBarView.this.mDisplayOptions) != 0)
      {
        if (ActionBarView.this.mTitleLayout != null)
          break label245;
        ActionBarView.this.initTitle();
      }
      while (true)
      {
        if ((ActionBarView.this.mTabScrollView != null) && (ActionBarView.this.mNavigationMode == 2))
          ActionBarView.this.mTabScrollView.setVisibility(0);
        if ((ActionBarView.this.mSpinner != null) && (ActionBarView.this.mNavigationMode == 1))
          ActionBarView.this.mSpinner.setVisibility(0);
        if ((ActionBarView.this.mCustomNavView != null) && ((0x10 & ActionBarView.this.mDisplayOptions) != 0))
          ActionBarView.this.mCustomNavView.setVisibility(0);
        ActionBarView.this.mExpandedHomeLayout.setIcon(null);
        this.mCurrentExpandedItem = null;
        ActionBarView.this.requestLayout();
        paramMenuItemImpl.setActionViewExpanded(false);
        return true;
        label245: ActionBarView.this.mTitleLayout.setVisibility(0);
      }
    }

    public boolean expandItemActionView(MenuBuilder paramMenuBuilder, MenuItemImpl paramMenuItemImpl)
    {
      ActionBarView.this.mExpandedActionView = paramMenuItemImpl.getActionView();
      ActionBarView.this.mExpandedHomeLayout.setIcon(ActionBarView.this.mIcon.getConstantState().newDrawable());
      this.mCurrentExpandedItem = paramMenuItemImpl;
      if (ActionBarView.this.mExpandedActionView.getParent() != ActionBarView.this)
        ActionBarView.this.addView(ActionBarView.this.mExpandedActionView);
      if (ActionBarView.this.mExpandedHomeLayout.getParent() != ActionBarView.this)
        ActionBarView.this.addView(ActionBarView.this.mExpandedHomeLayout);
      ActionBarView.this.mHomeLayout.setVisibility(8);
      if (ActionBarView.this.mTitleLayout != null)
        ActionBarView.this.mTitleLayout.setVisibility(8);
      if (ActionBarView.this.mTabScrollView != null)
        ActionBarView.this.mTabScrollView.setVisibility(8);
      if (ActionBarView.this.mSpinner != null)
        ActionBarView.this.mSpinner.setVisibility(8);
      if (ActionBarView.this.mCustomNavView != null)
        ActionBarView.this.mCustomNavView.setVisibility(8);
      ActionBarView.this.requestLayout();
      paramMenuItemImpl.setActionViewExpanded(true);
      if ((ActionBarView.this.mExpandedActionView instanceof CollapsibleActionView))
        ((CollapsibleActionView)ActionBarView.this.mExpandedActionView).onActionViewExpanded();
      return true;
    }

    public boolean flagActionItems()
    {
      return false;
    }

    public int getId()
    {
      return 0;
    }

    public MenuView getMenuView(ViewGroup paramViewGroup)
    {
      return null;
    }

    public void initForMenu(Context paramContext, MenuBuilder paramMenuBuilder)
    {
      if ((this.mMenu != null) && (this.mCurrentExpandedItem != null))
        this.mMenu.collapseItemActionView(this.mCurrentExpandedItem);
      this.mMenu = paramMenuBuilder;
    }

    public void onCloseMenu(MenuBuilder paramMenuBuilder, boolean paramBoolean)
    {
    }

    public void onRestoreInstanceState(Parcelable paramParcelable)
    {
    }

    public Parcelable onSaveInstanceState()
    {
      return null;
    }

    public boolean onSubMenuSelected(SubMenuBuilder paramSubMenuBuilder)
    {
      return false;
    }

    public void setCallback(MenuPresenter.Callback paramCallback)
    {
    }

    public void updateMenuView(boolean paramBoolean)
    {
      int i;
      int j;
      if (this.mCurrentExpandedItem != null)
      {
        i = 0;
        if (this.mMenu != null)
          j = this.mMenu.size();
      }
      for (int k = 0; ; k++)
      {
        if (k >= j);
        while (true)
        {
          if (i == 0)
            collapseItemActionView(this.mMenu, this.mCurrentExpandedItem);
          return;
          if (this.mMenu.getItem(k) != this.mCurrentExpandedItem)
            break;
          i = 1;
        }
      }
    }
  }

  public static class HomeView extends FrameLayout
  {
    private ImageView mIconView;
    private View mUpView;
    private int mUpWidth;

    public HomeView(Context paramContext)
    {
      this(paramContext, null);
    }

    public HomeView(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
    }

    public boolean dispatchHoverEvent(MotionEvent paramMotionEvent)
    {
      return onHoverEvent(paramMotionEvent);
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
    {
      onPopulateAccessibilityEvent(paramAccessibilityEvent);
      return true;
    }

    public int getLeftOffset()
    {
      if (this.mUpView.getVisibility() == 8);
      for (int i = this.mUpWidth; ; i = 0)
        return i;
    }

    protected void onFinishInflate()
    {
      this.mUpView = findViewById(R.id.abs__up);
      this.mIconView = ((ImageView)findViewById(R.id.abs__home));
    }

    protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      int i = (paramInt4 - paramInt2) / 2;
      int j = 0;
      if (this.mUpView.getVisibility() != 8)
      {
        FrameLayout.LayoutParams localLayoutParams2 = (FrameLayout.LayoutParams)this.mUpView.getLayoutParams();
        int i3 = this.mUpView.getMeasuredHeight();
        int i4 = this.mUpView.getMeasuredWidth();
        int i5 = i - i3 / 2;
        this.mUpView.layout(0, i5, i4, i5 + i3);
        j = i4 + localLayoutParams2.leftMargin + localLayoutParams2.rightMargin;
        paramInt1 += j;
      }
      FrameLayout.LayoutParams localLayoutParams1 = (FrameLayout.LayoutParams)this.mIconView.getLayoutParams();
      int k = this.mIconView.getMeasuredHeight();
      int m = this.mIconView.getMeasuredWidth();
      int n = (paramInt3 - paramInt1) / 2;
      int i1 = j + Math.max(localLayoutParams1.leftMargin, n - m / 2);
      int i2 = Math.max(localLayoutParams1.topMargin, i - k / 2);
      this.mIconView.layout(i1, i2, i1 + m, i2 + k);
    }

    protected void onMeasure(int paramInt1, int paramInt2)
    {
      measureChildWithMargins(this.mUpView, paramInt1, 0, paramInt2, 0);
      FrameLayout.LayoutParams localLayoutParams1 = (FrameLayout.LayoutParams)this.mUpView.getLayoutParams();
      this.mUpWidth = (localLayoutParams1.leftMargin + this.mUpView.getMeasuredWidth() + localLayoutParams1.rightMargin);
      int i;
      int k;
      int m;
      int i2;
      int i3;
      if (this.mUpView.getVisibility() == 8)
      {
        i = 0;
        int j = localLayoutParams1.topMargin + this.mUpView.getMeasuredHeight() + localLayoutParams1.bottomMargin;
        measureChildWithMargins(this.mIconView, paramInt1, i, paramInt2, 0);
        FrameLayout.LayoutParams localLayoutParams2 = (FrameLayout.LayoutParams)this.mIconView.getLayoutParams();
        k = i + (localLayoutParams2.leftMargin + this.mIconView.getMeasuredWidth() + localLayoutParams2.rightMargin);
        m = Math.max(j, localLayoutParams2.topMargin + this.mIconView.getMeasuredHeight() + localLayoutParams2.bottomMargin);
        int n = View.MeasureSpec.getMode(paramInt1);
        int i1 = View.MeasureSpec.getMode(paramInt2);
        i2 = View.MeasureSpec.getSize(paramInt1);
        i3 = View.MeasureSpec.getSize(paramInt2);
        switch (n)
        {
        default:
          label204: switch (i1)
          {
          default:
          case -2147483648:
          case 1073741824:
          }
        case -2147483648:
        case 1073741824:
        }
      }
      while (true)
      {
        setMeasuredDimension(k, m);
        return;
        i = this.mUpWidth;
        break;
        k = Math.min(k, i2);
        break label204;
        k = i2;
        break label204;
        m = Math.min(m, i3);
        continue;
        m = i3;
      }
    }

    public void onPopulateAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
    {
      if (Build.VERSION.SDK_INT >= 14)
        super.onPopulateAccessibilityEvent(paramAccessibilityEvent);
      CharSequence localCharSequence = getContentDescription();
      if (!TextUtils.isEmpty(localCharSequence))
        paramAccessibilityEvent.getText().add(localCharSequence);
    }

    public void setIcon(Drawable paramDrawable)
    {
      this.mIconView.setImageDrawable(paramDrawable);
    }

    public void setUp(boolean paramBoolean)
    {
      View localView = this.mUpView;
      if (paramBoolean);
      for (int i = 0; ; i = 8)
      {
        localView.setVisibility(i);
        return;
      }
    }
  }

  static class SavedState extends View.BaseSavedState
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new ActionBarView.SavedState.1();
    int expandedMenuItemId;
    boolean isOverflowOpen;

    private SavedState(Parcel paramParcel)
    {
      super();
      this.expandedMenuItemId = paramParcel.readInt();
      if (paramParcel.readInt() != 0);
      for (boolean bool = true; ; bool = false)
      {
        this.isOverflowOpen = bool;
        return;
      }
    }

    SavedState(Parcelable paramParcelable)
    {
      super();
    }

    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeInt(this.expandedMenuItemId);
      if (this.isOverflowOpen);
      for (int i = 1; ; i = 0)
      {
        paramParcel.writeInt(i);
        return;
      }
    }
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.actionbarsherlock.internal.widget.ActionBarView
 * JD-Core Version:    0.6.0
 */