package com.actionbarsherlock.internal.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;
import com.actionbarsherlock.R.styleable;

public class FakeDialogPhoneWindow extends LinearLayout
{
  final TypedValue mMinWidthMajor = new TypedValue();
  final TypedValue mMinWidthMinor = new TypedValue();

  public FakeDialogPhoneWindow(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.SherlockTheme);
    localTypedArray.getValue(34, this.mMinWidthMajor);
    localTypedArray.getValue(35, this.mMinWidthMinor);
    localTypedArray.recycle();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    DisplayMetrics localDisplayMetrics = getContext().getResources().getDisplayMetrics();
    int i;
    int j;
    int k;
    int m;
    TypedValue localTypedValue;
    label60: int n;
    if (localDisplayMetrics.widthPixels < localDisplayMetrics.heightPixels)
    {
      i = 1;
      super.onMeasure(paramInt1, paramInt2);
      j = getMeasuredWidth();
      k = 0;
      m = View.MeasureSpec.makeMeasureSpec(j, 1073741824);
      if (i == 0)
        break label124;
      localTypedValue = this.mMinWidthMinor;
      if (localTypedValue.type != 0)
      {
        if (localTypedValue.type != 5)
          break label133;
        n = (int)localTypedValue.getDimension(localDisplayMetrics);
      }
    }
    while (true)
    {
      if (j < n)
      {
        m = View.MeasureSpec.makeMeasureSpec(n, 1073741824);
        k = 1;
      }
      if (k != 0)
        super.onMeasure(m, paramInt2);
      return;
      i = 0;
      break;
      label124: localTypedValue = this.mMinWidthMajor;
      break label60;
      label133: if (localTypedValue.type == 6)
      {
        n = (int)localTypedValue.getFraction(localDisplayMetrics.widthPixels, localDisplayMetrics.widthPixels);
        continue;
      }
      n = 0;
    }
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.actionbarsherlock.internal.widget.FakeDialogPhoneWindow
 * JD-Core Version:    0.6.0
 */