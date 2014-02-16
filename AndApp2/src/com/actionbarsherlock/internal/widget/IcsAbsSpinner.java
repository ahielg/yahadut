package com.actionbarsherlock.internal.widget;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.SpinnerAdapter;

public abstract class IcsAbsSpinner extends IcsAdapterView<SpinnerAdapter>
{
  private static final boolean IS_HONEYCOMB;
  SpinnerAdapter mAdapter;
  boolean mBlockLayoutRequests;
  private DataSetObserver mDataSetObserver;
  int mHeightMeasureSpec;
  final RecycleBin mRecycler = new RecycleBin();
  int mSelectionBottomPadding = 0;
  int mSelectionLeftPadding = 0;
  int mSelectionRightPadding = 0;
  int mSelectionTopPadding = 0;
  final Rect mSpinnerPadding = new Rect();
  private Rect mTouchFrame;
  int mWidthMeasureSpec;

  static
  {
    if (Build.VERSION.SDK_INT >= 11);
    for (boolean bool = true; ; bool = false)
    {
      IS_HONEYCOMB = bool;
      return;
    }
  }

  public IcsAbsSpinner(Context paramContext)
  {
    super(paramContext);
    initAbsSpinner();
  }

  public IcsAbsSpinner(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public IcsAbsSpinner(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initAbsSpinner();
  }

  private void initAbsSpinner()
  {
    setFocusable(true);
    setWillNotDraw(false);
  }

  protected ViewGroup.LayoutParams generateDefaultLayoutParams()
  {
    return new ViewGroup.LayoutParams(-1, -2);
  }

  public SpinnerAdapter getAdapter()
  {
    return this.mAdapter;
  }

  int getChildHeight(View paramView)
  {
    return paramView.getMeasuredHeight();
  }

  int getChildWidth(View paramView)
  {
    return paramView.getMeasuredWidth();
  }

  public int getCount()
  {
    return this.mItemCount;
  }

  public View getSelectedView()
  {
    if ((this.mItemCount > 0) && (this.mSelectedPosition >= 0));
    for (View localView = getChildAt(this.mSelectedPosition - this.mFirstPosition); ; localView = null)
      return localView;
  }

  abstract void layout(int paramInt, boolean paramBoolean);

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = View.MeasureSpec.getMode(paramInt1);
    int j = getPaddingLeft();
    int k = getPaddingTop();
    int m = getPaddingRight();
    int n = getPaddingBottom();
    Rect localRect1 = this.mSpinnerPadding;
    label66: label88: label110: int i5;
    int i6;
    int i7;
    if (j > this.mSelectionLeftPadding)
    {
      localRect1.left = j;
      Rect localRect2 = this.mSpinnerPadding;
      if (k <= this.mSelectionTopPadding)
        break label424;
      localRect2.top = k;
      Rect localRect3 = this.mSpinnerPadding;
      if (m <= this.mSelectionRightPadding)
        break label433;
      localRect3.right = m;
      Rect localRect4 = this.mSpinnerPadding;
      if (n <= this.mSelectionBottomPadding)
        break label442;
      localRect4.bottom = n;
      if (this.mDataChanged)
        handleDataChanged();
      int i1 = 0;
      int i2 = 0;
      int i3 = 1;
      int i4 = getSelectedItemPosition();
      if ((i4 >= 0) && (this.mAdapter != null) && (i4 < this.mAdapter.getCount()))
      {
        View localView = this.mRecycler.get(i4);
        if (localView == null)
          localView = this.mAdapter.getView(i4, null, this);
        if (localView != null)
          this.mRecycler.put(i4, localView);
        if (localView != null)
        {
          if (localView.getLayoutParams() == null)
          {
            this.mBlockLayoutRequests = true;
            localView.setLayoutParams(generateDefaultLayoutParams());
            this.mBlockLayoutRequests = false;
          }
          measureChild(localView, paramInt1, paramInt2);
          i1 = getChildHeight(localView) + this.mSpinnerPadding.top + this.mSpinnerPadding.bottom;
          i2 = getChildWidth(localView) + this.mSpinnerPadding.left + this.mSpinnerPadding.right;
          i3 = 0;
        }
      }
      if (i3 != 0)
      {
        i1 = this.mSpinnerPadding.top + this.mSpinnerPadding.bottom;
        if (i == 0)
          i2 = this.mSpinnerPadding.left + this.mSpinnerPadding.right;
      }
      i5 = Math.max(i1, getSuggestedMinimumHeight());
      i6 = Math.max(i2, getSuggestedMinimumWidth());
      if (!IS_HONEYCOMB)
        break label451;
      i7 = resolveSizeAndState(i5, paramInt2, 0);
    }
    for (int i8 = resolveSizeAndState(i6, paramInt1, 0); ; i8 = resolveSize(i6, paramInt1))
    {
      setMeasuredDimension(i8, i7);
      this.mHeightMeasureSpec = paramInt2;
      this.mWidthMeasureSpec = paramInt1;
      return;
      j = this.mSelectionLeftPadding;
      break;
      label424: k = this.mSelectionTopPadding;
      break label66;
      label433: m = this.mSelectionRightPadding;
      break label88;
      label442: n = this.mSelectionBottomPadding;
      break label110;
      label451: i7 = resolveSize(i5, paramInt2);
    }
  }

  public void onRestoreInstanceState(Parcelable paramParcelable)
  {
    SavedState localSavedState = (SavedState)paramParcelable;
    super.onRestoreInstanceState(localSavedState.getSuperState());
    if (localSavedState.selectedId >= 0L)
    {
      this.mDataChanged = true;
      this.mNeedSync = true;
      this.mSyncRowId = localSavedState.selectedId;
      this.mSyncPosition = localSavedState.position;
      this.mSyncMode = 0;
      requestLayout();
    }
  }

  public Parcelable onSaveInstanceState()
  {
    SavedState localSavedState = new SavedState(super.onSaveInstanceState());
    localSavedState.selectedId = getSelectedItemId();
    if (localSavedState.selectedId >= 0L);
    for (localSavedState.position = getSelectedItemPosition(); ; localSavedState.position = -1)
      return localSavedState;
  }

  public int pointToPosition(int paramInt1, int paramInt2)
  {
    Rect localRect = this.mTouchFrame;
    if (localRect == null)
    {
      this.mTouchFrame = new Rect();
      localRect = this.mTouchFrame;
    }
    for (int i = -1 + getChildCount(); ; i--)
    {
      if (i < 0);
      for (int j = -1; ; j = i + this.mFirstPosition)
      {
        return j;
        View localView = getChildAt(i);
        if (localView.getVisibility() != 0)
          break;
        localView.getHitRect(localRect);
        if (!localRect.contains(paramInt1, paramInt2))
          break;
      }
    }
  }

  void recycleAllViews()
  {
    int i = getChildCount();
    RecycleBin localRecycleBin = this.mRecycler;
    int j = this.mFirstPosition;
    for (int k = 0; ; k++)
    {
      if (k >= i)
        return;
      View localView = getChildAt(k);
      localRecycleBin.put(j + k, localView);
    }
  }

  public void requestLayout()
  {
    if (!this.mBlockLayoutRequests)
      super.requestLayout();
  }

  void resetList()
  {
    this.mDataChanged = false;
    this.mNeedSync = false;
    removeAllViewsInLayout();
    this.mOldSelectedPosition = -1;
    this.mOldSelectedRowId = -9223372036854775808L;
    setSelectedPositionInt(-1);
    setNextSelectedPositionInt(-1);
    invalidate();
  }

  public void setAdapter(SpinnerAdapter paramSpinnerAdapter)
  {
    int i = -1;
    if (this.mAdapter != null)
    {
      this.mAdapter.unregisterDataSetObserver(this.mDataSetObserver);
      resetList();
    }
    this.mAdapter = paramSpinnerAdapter;
    this.mOldSelectedPosition = i;
    this.mOldSelectedRowId = -9223372036854775808L;
    if (this.mAdapter != null)
    {
      this.mOldItemCount = this.mItemCount;
      this.mItemCount = this.mAdapter.getCount();
      checkFocus();
      this.mDataSetObserver = new IcsAdapterView.AdapterDataSetObserver(this);
      this.mAdapter.registerDataSetObserver(this.mDataSetObserver);
      if (this.mItemCount > 0)
        i = 0;
      setSelectedPositionInt(i);
      setNextSelectedPositionInt(i);
      if (this.mItemCount == 0)
        checkSelectionChanged();
    }
    while (true)
    {
      requestLayout();
      return;
      checkFocus();
      resetList();
      checkSelectionChanged();
    }
  }

  public void setSelection(int paramInt)
  {
    setNextSelectedPositionInt(paramInt);
    requestLayout();
    invalidate();
  }

  public void setSelection(int paramInt, boolean paramBoolean)
  {
    if ((paramBoolean) && (this.mFirstPosition <= paramInt) && (paramInt <= -1 + (this.mFirstPosition + getChildCount())));
    for (boolean bool = true; ; bool = false)
    {
      setSelectionInt(paramInt, bool);
      return;
    }
  }

  void setSelectionInt(int paramInt, boolean paramBoolean)
  {
    if (paramInt != this.mOldSelectedPosition)
    {
      this.mBlockLayoutRequests = true;
      int i = paramInt - this.mSelectedPosition;
      setNextSelectedPositionInt(paramInt);
      layout(i, paramBoolean);
      this.mBlockLayoutRequests = false;
    }
  }

  class RecycleBin
  {
    private final SparseArray<View> mScrapHeap = new SparseArray();

    RecycleBin()
    {
    }

    void clear()
    {
      SparseArray localSparseArray = this.mScrapHeap;
      int i = localSparseArray.size();
      for (int j = 0; ; j++)
      {
        if (j >= i)
        {
          localSparseArray.clear();
          return;
        }
        View localView = (View)localSparseArray.valueAt(j);
        if (localView == null)
          continue;
        IcsAbsSpinner.this.removeDetachedView(localView, true);
      }
    }

    View get(int paramInt)
    {
      View localView = (View)this.mScrapHeap.get(paramInt);
      if (localView != null)
        this.mScrapHeap.delete(paramInt);
      return localView;
    }

    public void put(int paramInt, View paramView)
    {
      this.mScrapHeap.put(paramInt, paramView);
    }
  }

  static class SavedState extends View.BaseSavedState
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new IcsAbsSpinner.SavedState.1();
    int position;
    long selectedId;

    private SavedState(Parcel paramParcel)
    {
      super();
      this.selectedId = paramParcel.readLong();
      this.position = paramParcel.readInt();
    }

    SavedState(Parcelable paramParcelable)
    {
      super();
    }

    public String toString()
    {
      return "AbsSpinner.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " selectedId=" + this.selectedId + " position=" + this.position + "}";
    }

    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeLong(this.selectedId);
      paramParcel.writeInt(this.position);
    }
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.actionbarsherlock.internal.widget.IcsAbsSpinner
 * JD-Core Version:    0.6.0
 */