package com.actionbarsherlock.internal.nineoldandroids.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.actionbarsherlock.internal.nineoldandroids.view.animation.AnimatorProxy;

public class NineLinearLayout extends LinearLayout
{
  private final AnimatorProxy mProxy;

  public NineLinearLayout(Context paramContext)
  {
    super(paramContext);
    if (AnimatorProxy.NEEDS_PROXY);
    for (AnimatorProxy localAnimatorProxy = AnimatorProxy.wrap(this); ; localAnimatorProxy = null)
    {
      this.mProxy = localAnimatorProxy;
      return;
    }
  }

  public NineLinearLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    if (AnimatorProxy.NEEDS_PROXY);
    for (AnimatorProxy localAnimatorProxy = AnimatorProxy.wrap(this); ; localAnimatorProxy = null)
    {
      this.mProxy = localAnimatorProxy;
      return;
    }
  }

  public NineLinearLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    if (AnimatorProxy.NEEDS_PROXY);
    for (AnimatorProxy localAnimatorProxy = AnimatorProxy.wrap(this); ; localAnimatorProxy = null)
    {
      this.mProxy = localAnimatorProxy;
      return;
    }
  }

  public float getAlpha()
  {
    float f;
    if (AnimatorProxy.NEEDS_PROXY)
      f = this.mProxy.getAlpha();
    while (true)
    {
      return f;
      f = super.getAlpha();
    }
  }

  public float getTranslationX()
  {
    float f;
    if (AnimatorProxy.NEEDS_PROXY)
      f = this.mProxy.getTranslationX();
    while (true)
    {
      return f;
      f = super.getTranslationX();
    }
  }

  public void setAlpha(float paramFloat)
  {
    if (AnimatorProxy.NEEDS_PROXY)
      this.mProxy.setAlpha(paramFloat);
    while (true)
    {
      return;
      super.setAlpha(paramFloat);
    }
  }

  public void setTranslationX(float paramFloat)
  {
    if (AnimatorProxy.NEEDS_PROXY)
      this.mProxy.setTranslationX(paramFloat);
    while (true)
    {
      return;
      super.setTranslationX(paramFloat);
    }
  }

  public void setVisibility(int paramInt)
  {
    if (this.mProxy != null)
    {
      if (paramInt != 8)
        break label23;
      clearAnimation();
    }
    while (true)
    {
      super.setVisibility(paramInt);
      return;
      label23: if (paramInt != 0)
        continue;
      setAnimation(this.mProxy);
    }
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.actionbarsherlock.internal.nineoldandroids.widget.NineLinearLayout
 * JD-Core Version:    0.6.0
 */