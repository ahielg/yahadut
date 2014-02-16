package com.actionbarsherlock.internal.nineoldandroids.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.actionbarsherlock.internal.nineoldandroids.view.animation.AnimatorProxy;

public class NineFrameLayout extends FrameLayout
{
  private final AnimatorProxy mProxy;

  public NineFrameLayout(Context paramContext)
  {
    super(paramContext);
    if (AnimatorProxy.NEEDS_PROXY);
    for (AnimatorProxy localAnimatorProxy = AnimatorProxy.wrap(this); ; localAnimatorProxy = null)
    {
      this.mProxy = localAnimatorProxy;
      return;
    }
  }

  public NineFrameLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    if (AnimatorProxy.NEEDS_PROXY);
    for (AnimatorProxy localAnimatorProxy = AnimatorProxy.wrap(this); ; localAnimatorProxy = null)
    {
      this.mProxy = localAnimatorProxy;
      return;
    }
  }

  public NineFrameLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
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

  public float getTranslationY()
  {
    float f;
    if (AnimatorProxy.NEEDS_PROXY)
      f = this.mProxy.getTranslationY();
    while (true)
    {
      return f;
      f = super.getTranslationY();
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

  public void setTranslationY(float paramFloat)
  {
    if (AnimatorProxy.NEEDS_PROXY)
      this.mProxy.setTranslationY(paramFloat);
    while (true)
    {
      return;
      super.setTranslationY(paramFloat);
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
 * Qualified Name:     com.actionbarsherlock.internal.nineoldandroids.widget.NineFrameLayout
 * JD-Core Version:    0.6.0
 */