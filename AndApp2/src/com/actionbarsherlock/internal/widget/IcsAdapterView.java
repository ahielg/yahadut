package com.actionbarsherlock.internal.widget;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewDebug.CapturedViewProperty;
import android.view.ViewDebug.ExportedProperty;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Adapter;
import android.widget.AdapterView.OnItemClickListener;

public abstract class IcsAdapterView<T extends Adapter> extends ViewGroup
{
  public static final int INVALID_POSITION = -1;
  public static final long INVALID_ROW_ID = -9223372036854775808L;
  public static final int ITEM_VIEW_TYPE_HEADER_OR_FOOTER = -2;
  public static final int ITEM_VIEW_TYPE_IGNORE = -1;
  static final int SYNC_FIRST_POSITION = 1;
  static final int SYNC_MAX_DURATION_MILLIS = 100;
  static final int SYNC_SELECTED_POSITION;
  boolean mBlockLayoutRequests = false;
  boolean mDataChanged;
  private boolean mDesiredFocusableInTouchModeState;
  private boolean mDesiredFocusableState;
  private View mEmptyView;

  @ViewDebug.ExportedProperty(category="scrolling")
  int mFirstPosition = 0;
  boolean mInLayout = false;

  @ViewDebug.ExportedProperty(category="list")
  int mItemCount;
  private int mLayoutHeight;
  boolean mNeedSync = false;

  @ViewDebug.ExportedProperty(category="list")
  int mNextSelectedPosition = -1;
  long mNextSelectedRowId = -9223372036854775808L;
  int mOldItemCount;
  int mOldSelectedPosition = -1;
  long mOldSelectedRowId = -9223372036854775808L;
  AdapterView.OnItemClickListener mOnItemClickListener;
  OnItemLongClickListener mOnItemLongClickListener;
  OnItemSelectedListener mOnItemSelectedListener;

  @ViewDebug.ExportedProperty(category="list")
  int mSelectedPosition = -1;
  long mSelectedRowId = -9223372036854775808L;
  private IcsAdapterView<T>.SelectionNotifier mSelectionNotifier;
  int mSpecificTop;
  long mSyncHeight;
  int mSyncMode;
  int mSyncPosition;
  long mSyncRowId = -9223372036854775808L;

  public IcsAdapterView(Context paramContext)
  {
    super(paramContext);
  }

  public IcsAdapterView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public IcsAdapterView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  private void fireOnSelected()
  {
    if (this.mOnItemSelectedListener == null);
    while (true)
    {
      return;
      int i = getSelectedItemPosition();
      if (i >= 0)
      {
        View localView = getSelectedView();
        this.mOnItemSelectedListener.onItemSelected(this, localView, i, getAdapter().getItemId(i));
        continue;
      }
      this.mOnItemSelectedListener.onNothingSelected(this);
    }
  }

  private boolean isScrollableForAccessibility()
  {
    int i = 0;
    Adapter localAdapter = getAdapter();
    if (localAdapter != null)
    {
      int j = localAdapter.getCount();
      if ((j > 0) && ((getFirstVisiblePosition() > 0) || (getLastVisiblePosition() < j - 1)))
        i = 1;
    }
    return i;
  }

  private void updateEmptyStatus(boolean paramBoolean)
  {
    if (isInFilterMode())
      paramBoolean = false;
    if (paramBoolean)
      if (this.mEmptyView != null)
      {
        this.mEmptyView.setVisibility(0);
        setVisibility(8);
        if (this.mDataChanged)
          onLayout(false, getLeft(), getTop(), getRight(), getBottom());
      }
    while (true)
    {
      return;
      setVisibility(0);
      break;
      if (this.mEmptyView != null)
        this.mEmptyView.setVisibility(8);
      setVisibility(0);
    }
  }

  public void addView(View paramView)
  {
    throw new UnsupportedOperationException("addView(View) is not supported in AdapterView");
  }

  public void addView(View paramView, int paramInt)
  {
    throw new UnsupportedOperationException("addView(View, int) is not supported in AdapterView");
  }

  public void addView(View paramView, int paramInt, ViewGroup.LayoutParams paramLayoutParams)
  {
    throw new UnsupportedOperationException("addView(View, int, LayoutParams) is not supported in AdapterView");
  }

  public void addView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    throw new UnsupportedOperationException("addView(View, LayoutParams) is not supported in AdapterView");
  }

  protected boolean canAnimate()
  {
    if ((super.canAnimate()) && (this.mItemCount > 0));
    for (int i = 1; ; i = 0)
      return i;
  }

  void checkFocus()
  {
    boolean bool1 = false;
    Adapter localAdapter = getAdapter();
    int i;
    int j;
    label36: boolean bool2;
    label51: boolean bool3;
    if ((localAdapter != null) && (localAdapter.getCount() != 0))
    {
      i = 0;
      if ((i == 0) || (isInFilterMode()))
        break label109;
      j = 0;
      if ((j == 0) || (!this.mDesiredFocusableInTouchModeState))
        break label115;
      bool2 = true;
      super.setFocusableInTouchMode(bool2);
      if ((j == 0) || (!this.mDesiredFocusableState))
        break label121;
      bool3 = true;
      label72: super.setFocusable(bool3);
      if (this.mEmptyView != null)
        if ((localAdapter == null) || (localAdapter.isEmpty()))
          break label127;
    }
    while (true)
    {
      updateEmptyStatus(bool1);
      return;
      i = 1;
      break;
      label109: j = 1;
      break label36;
      label115: bool2 = false;
      break label51;
      label121: bool3 = false;
      break label72;
      label127: bool1 = true;
    }
  }

  void checkSelectionChanged()
  {
    if ((this.mSelectedPosition != this.mOldSelectedPosition) || (this.mSelectedRowId != this.mOldSelectedRowId))
    {
      selectionChanged();
      this.mOldSelectedPosition = this.mSelectedPosition;
      this.mOldSelectedRowId = this.mSelectedRowId;
    }
  }

  public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    View localView = getSelectedView();
    if ((localView != null) && (localView.getVisibility() == 0) && (localView.dispatchPopulateAccessibilityEvent(paramAccessibilityEvent)));
    for (int i = 1; ; i = 0)
      return i;
  }

  protected void dispatchRestoreInstanceState(SparseArray<Parcelable> paramSparseArray)
  {
    dispatchThawSelfOnly(paramSparseArray);
  }

  protected void dispatchSaveInstanceState(SparseArray<Parcelable> paramSparseArray)
  {
    dispatchFreezeSelfOnly(paramSparseArray);
  }

  int findSyncPosition()
  {
    int i = this.mItemCount;
    int m;
    if (i == 0)
      m = -1;
    long l1;
    long l2;
    int n;
    int i1;
    int i2;
    Adapter localAdapter;
    while (true)
    {
      return m;
      l1 = this.mSyncRowId;
      int j = this.mSyncPosition;
      if (l1 == -9223372036854775808L)
      {
        m = -1;
        continue;
      }
      int k = Math.max(0, j);
      m = Math.min(i - 1, k);
      l2 = 100L + SystemClock.uptimeMillis();
      n = m;
      i1 = m;
      i2 = 0;
      localAdapter = getAdapter();
      if (localAdapter != null)
        break;
      m = -1;
    }
    label154: label160: label223: 
    while (localAdapter.getItemId(m) != l1)
    {
      int i3;
      if (i1 == i - 1)
      {
        i3 = 1;
        label123: if (n != 0)
          break label154;
      }
      for (int i4 = 1; ; i4 = 0)
      {
        if ((i3 == 0) || (i4 == 0))
          break label160;
        m = -1;
        break;
        i3 = 0;
        break label123;
      }
      if ((i4 != 0) || ((i2 != 0) && (i3 == 0)))
      {
        i1++;
        m = i1;
      }
      for (i2 = 0; ; i2 = 1)
      {
        do
        {
          if (SystemClock.uptimeMillis() <= l2)
            break label223;
          break;
        }
        while ((i3 == 0) && ((i2 != 0) || (i4 != 0)));
        n--;
        m = n;
      }
    }
  }

  public abstract T getAdapter();

  @ViewDebug.CapturedViewProperty
  public int getCount()
  {
    return this.mItemCount;
  }

  public View getEmptyView()
  {
    return this.mEmptyView;
  }

  public int getFirstVisiblePosition()
  {
    return this.mFirstPosition;
  }

  public Object getItemAtPosition(int paramInt)
  {
    Adapter localAdapter = getAdapter();
    if ((localAdapter == null) || (paramInt < 0));
    for (Object localObject = null; ; localObject = localAdapter.getItem(paramInt))
      return localObject;
  }

  public long getItemIdAtPosition(int paramInt)
  {
    Adapter localAdapter = getAdapter();
    long l;
    if ((localAdapter == null) || (paramInt < 0))
      l = -9223372036854775808L;
    while (true)
    {
      return l;
      l = localAdapter.getItemId(paramInt);
    }
  }

  public int getLastVisiblePosition()
  {
    return -1 + (this.mFirstPosition + getChildCount());
  }

  public final AdapterView.OnItemClickListener getOnItemClickListener()
  {
    return this.mOnItemClickListener;
  }

  public final OnItemLongClickListener getOnItemLongClickListener()
  {
    return this.mOnItemLongClickListener;
  }

  public final OnItemSelectedListener getOnItemSelectedListener()
  {
    return this.mOnItemSelectedListener;
  }

  public int getPositionForView(View paramView)
  {
    int i = -1;
    Object localObject = paramView;
    try
    {
      while (true)
      {
        View localView = (View)((View)localObject).getParent();
        boolean bool = localView.equals(this);
        if (bool)
        {
          int j = getChildCount();
          k = 0;
          if (k < j)
            break;
          return i;
        }
        localObject = localView;
      }
    }
    catch (ClassCastException localClassCastException)
    {
      while (true)
      {
        int k;
        continue;
        if (getChildAt(k).equals(localObject))
        {
          i = k + this.mFirstPosition;
          continue;
        }
        k++;
      }
    }
  }

  public Object getSelectedItem()
  {
    Adapter localAdapter = getAdapter();
    int i = getSelectedItemPosition();
    if ((localAdapter != null) && (localAdapter.getCount() > 0) && (i >= 0));
    for (Object localObject = localAdapter.getItem(i); ; localObject = null)
      return localObject;
  }

  @ViewDebug.CapturedViewProperty
  public long getSelectedItemId()
  {
    return this.mNextSelectedRowId;
  }

  @ViewDebug.CapturedViewProperty
  public int getSelectedItemPosition()
  {
    return this.mNextSelectedPosition;
  }

  public abstract View getSelectedView();

  void handleDataChanged()
  {
    int i = this.mItemCount;
    int j = 0;
    if (i > 0)
    {
      if (this.mNeedSync)
      {
        this.mNeedSync = false;
        int n = findSyncPosition();
        if ((n >= 0) && (lookForSelectablePosition(n, true) == n))
        {
          setNextSelectedPositionInt(n);
          j = 1;
        }
      }
      if (j == 0)
      {
        int k = getSelectedItemPosition();
        if (k >= i)
          k = i - 1;
        if (k < 0)
          k = 0;
        int m = lookForSelectablePosition(k, true);
        if (m < 0)
          m = lookForSelectablePosition(k, false);
        if (m >= 0)
        {
          setNextSelectedPositionInt(m);
          checkSelectionChanged();
          j = 1;
        }
      }
    }
    if (j == 0)
    {
      this.mSelectedPosition = -1;
      this.mSelectedRowId = -9223372036854775808L;
      this.mNextSelectedPosition = -1;
      this.mNextSelectedRowId = -9223372036854775808L;
      this.mNeedSync = false;
      checkSelectionChanged();
    }
  }

  boolean isInFilterMode()
  {
    return false;
  }

  int lookForSelectablePosition(int paramInt, boolean paramBoolean)
  {
    return paramInt;
  }

  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    removeCallbacks(this.mSelectionNotifier);
  }

  public void onInitializeAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    super.onInitializeAccessibilityEvent(paramAccessibilityEvent);
    paramAccessibilityEvent.setScrollable(isScrollableForAccessibility());
    View localView = getSelectedView();
    if (localView != null)
      paramAccessibilityEvent.setEnabled(localView.isEnabled());
    paramAccessibilityEvent.setCurrentItemIndex(getSelectedItemPosition());
    paramAccessibilityEvent.setFromIndex(getFirstVisiblePosition());
    paramAccessibilityEvent.setToIndex(getLastVisiblePosition());
    paramAccessibilityEvent.setItemCount(getCount());
  }

  public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo paramAccessibilityNodeInfo)
  {
    super.onInitializeAccessibilityNodeInfo(paramAccessibilityNodeInfo);
    paramAccessibilityNodeInfo.setScrollable(isScrollableForAccessibility());
    View localView = getSelectedView();
    if (localView != null)
      paramAccessibilityNodeInfo.setEnabled(localView.isEnabled());
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mLayoutHeight = getHeight();
  }

  public boolean onRequestSendAccessibilityEvent(View paramView, AccessibilityEvent paramAccessibilityEvent)
  {
    if (super.onRequestSendAccessibilityEvent(paramView, paramAccessibilityEvent))
    {
      AccessibilityEvent localAccessibilityEvent = AccessibilityEvent.obtain();
      onInitializeAccessibilityEvent(localAccessibilityEvent);
      paramView.dispatchPopulateAccessibilityEvent(localAccessibilityEvent);
      paramAccessibilityEvent.appendRecord(localAccessibilityEvent);
    }
    for (int i = 1; ; i = 0)
      return i;
  }

  public boolean performItemClick(View paramView, int paramInt, long paramLong)
  {
    int i = 0;
    if (this.mOnItemClickListener != null)
    {
      playSoundEffect(0);
      if (paramView != null)
        paramView.sendAccessibilityEvent(1);
      this.mOnItemClickListener.onItemClick(null, paramView, paramInt, paramLong);
      i = 1;
    }
    return i;
  }

  void rememberSyncState()
  {
    if (getChildCount() > 0)
    {
      this.mNeedSync = true;
      this.mSyncHeight = this.mLayoutHeight;
      if (this.mSelectedPosition >= 0)
      {
        View localView2 = getChildAt(this.mSelectedPosition - this.mFirstPosition);
        this.mSyncRowId = this.mNextSelectedRowId;
        this.mSyncPosition = this.mNextSelectedPosition;
        if (localView2 != null)
          this.mSpecificTop = localView2.getTop();
        this.mSyncMode = 0;
      }
    }
    else
    {
      return;
    }
    View localView1 = getChildAt(0);
    Adapter localAdapter = getAdapter();
    if ((this.mFirstPosition >= 0) && (this.mFirstPosition < localAdapter.getCount()));
    for (this.mSyncRowId = localAdapter.getItemId(this.mFirstPosition); ; this.mSyncRowId = -1L)
    {
      this.mSyncPosition = this.mFirstPosition;
      if (localView1 != null)
        this.mSpecificTop = localView1.getTop();
      this.mSyncMode = 1;
      break;
    }
  }

  public void removeAllViews()
  {
    throw new UnsupportedOperationException("removeAllViews() is not supported in AdapterView");
  }

  public void removeView(View paramView)
  {
    throw new UnsupportedOperationException("removeView(View) is not supported in AdapterView");
  }

  public void removeViewAt(int paramInt)
  {
    throw new UnsupportedOperationException("removeViewAt(int) is not supported in AdapterView");
  }

  void selectionChanged()
  {
    if (this.mOnItemSelectedListener != null)
    {
      if ((!this.mInLayout) && (!this.mBlockLayoutRequests))
        break label79;
      if (this.mSelectionNotifier == null)
        this.mSelectionNotifier = new SelectionNotifier(null);
      post(this.mSelectionNotifier);
    }
    while (true)
    {
      if ((this.mSelectedPosition != -1) && (isShown()) && (!isInTouchMode()))
        sendAccessibilityEvent(4);
      return;
      label79: fireOnSelected();
    }
  }

  public abstract void setAdapter(T paramT);

  public void setEmptyView(View paramView)
  {
    this.mEmptyView = paramView;
    Adapter localAdapter = getAdapter();
    if ((localAdapter != null) && (!localAdapter.isEmpty()));
    for (boolean bool = false; ; bool = true)
    {
      updateEmptyStatus(bool);
      return;
    }
  }

  public void setFocusable(boolean paramBoolean)
  {
    boolean bool = true;
    Adapter localAdapter = getAdapter();
    int i;
    if ((localAdapter != null) && (localAdapter.getCount() != 0))
    {
      i = 0;
      this.mDesiredFocusableState = paramBoolean;
      if (!paramBoolean)
        this.mDesiredFocusableInTouchModeState = false;
      if ((!paramBoolean) || ((i != 0) && (!isInFilterMode())))
        break label65;
    }
    while (true)
    {
      super.setFocusable(bool);
      return;
      i = bool;
      break;
      label65: bool = false;
    }
  }

  public void setFocusableInTouchMode(boolean paramBoolean)
  {
    boolean bool = true;
    Adapter localAdapter = getAdapter();
    int i;
    if ((localAdapter != null) && (localAdapter.getCount() != 0))
    {
      i = 0;
      this.mDesiredFocusableInTouchModeState = paramBoolean;
      if (paramBoolean)
        this.mDesiredFocusableState = bool;
      if ((!paramBoolean) || ((i != 0) && (!isInFilterMode())))
        break label65;
    }
    while (true)
    {
      super.setFocusableInTouchMode(bool);
      return;
      i = bool;
      break;
      label65: bool = false;
    }
  }

  void setNextSelectedPositionInt(int paramInt)
  {
    this.mNextSelectedPosition = paramInt;
    this.mNextSelectedRowId = getItemIdAtPosition(paramInt);
    if ((this.mNeedSync) && (this.mSyncMode == 0) && (paramInt >= 0))
    {
      this.mSyncPosition = paramInt;
      this.mSyncRowId = this.mNextSelectedRowId;
    }
  }

  public void setOnClickListener(View.OnClickListener paramOnClickListener)
  {
    throw new RuntimeException("Don't call setOnClickListener for an AdapterView. You probably want setOnItemClickListener instead");
  }

  public void setOnItemClickListener(AdapterView.OnItemClickListener paramOnItemClickListener)
  {
    this.mOnItemClickListener = paramOnItemClickListener;
  }

  public void setOnItemLongClickListener(OnItemLongClickListener paramOnItemLongClickListener)
  {
    if (!isLongClickable())
      setLongClickable(true);
    this.mOnItemLongClickListener = paramOnItemLongClickListener;
  }

  public void setOnItemSelectedListener(OnItemSelectedListener paramOnItemSelectedListener)
  {
    this.mOnItemSelectedListener = paramOnItemSelectedListener;
  }

  void setSelectedPositionInt(int paramInt)
  {
    this.mSelectedPosition = paramInt;
    this.mSelectedRowId = getItemIdAtPosition(paramInt);
  }

  public abstract void setSelection(int paramInt);

  public static class AdapterContextMenuInfo
    implements ContextMenu.ContextMenuInfo
  {
    public long id;
    public int position;
    public View targetView;

    public AdapterContextMenuInfo(View paramView, int paramInt, long paramLong)
    {
      this.targetView = paramView;
      this.position = paramInt;
      this.id = paramLong;
    }
  }

  class AdapterDataSetObserver extends DataSetObserver
  {
    private Parcelable mInstanceState = null;

    AdapterDataSetObserver()
    {
    }

    public void clearSavedState()
    {
      this.mInstanceState = null;
    }

    public void onChanged()
    {
      IcsAdapterView.this.mDataChanged = true;
      IcsAdapterView.this.mOldItemCount = IcsAdapterView.this.mItemCount;
      IcsAdapterView.this.mItemCount = IcsAdapterView.this.getAdapter().getCount();
      if ((IcsAdapterView.this.getAdapter().hasStableIds()) && (this.mInstanceState != null) && (IcsAdapterView.this.mOldItemCount == 0) && (IcsAdapterView.this.mItemCount > 0))
      {
        IcsAdapterView.this.onRestoreInstanceState(this.mInstanceState);
        this.mInstanceState = null;
      }
      while (true)
      {
        IcsAdapterView.this.checkFocus();
        IcsAdapterView.this.requestLayout();
        return;
        IcsAdapterView.this.rememberSyncState();
      }
    }

    public void onInvalidated()
    {
      IcsAdapterView.this.mDataChanged = true;
      if (IcsAdapterView.this.getAdapter().hasStableIds())
        this.mInstanceState = IcsAdapterView.this.onSaveInstanceState();
      IcsAdapterView.this.mOldItemCount = IcsAdapterView.this.mItemCount;
      IcsAdapterView.this.mItemCount = 0;
      IcsAdapterView.this.mSelectedPosition = -1;
      IcsAdapterView.this.mSelectedRowId = -9223372036854775808L;
      IcsAdapterView.this.mNextSelectedPosition = -1;
      IcsAdapterView.this.mNextSelectedRowId = -9223372036854775808L;
      IcsAdapterView.this.mNeedSync = false;
      IcsAdapterView.this.checkFocus();
      IcsAdapterView.this.requestLayout();
    }
  }

  public static abstract interface OnItemLongClickListener
  {
    public abstract boolean onItemLongClick(IcsAdapterView<?> paramIcsAdapterView, View paramView, int paramInt, long paramLong);
  }

  public static abstract interface OnItemSelectedListener
  {
    public abstract void onItemSelected(IcsAdapterView<?> paramIcsAdapterView, View paramView, int paramInt, long paramLong);

    public abstract void onNothingSelected(IcsAdapterView<?> paramIcsAdapterView);
  }

  private class SelectionNotifier
    implements Runnable
  {
    private SelectionNotifier()
    {
    }

    public void run()
    {
      if (IcsAdapterView.this.mDataChanged)
        if (IcsAdapterView.this.getAdapter() != null)
          IcsAdapterView.this.post(this);
      while (true)
      {
        return;
        IcsAdapterView.this.fireOnSelected();
      }
    }
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.actionbarsherlock.internal.widget.IcsAdapterView
 * JD-Core Version:    0.6.0
 */