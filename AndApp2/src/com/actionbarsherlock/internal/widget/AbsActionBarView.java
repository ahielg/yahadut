package com.actionbarsherlock.internal.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import com.actionbarsherlock.R.attr;
import com.actionbarsherlock.R.bool;
import com.actionbarsherlock.R.styleable;
import com.actionbarsherlock.internal.ResourcesCompat;
import com.actionbarsherlock.internal.nineoldandroids.animation.Animator;
import com.actionbarsherlock.internal.nineoldandroids.animation.Animator.AnimatorListener;
import com.actionbarsherlock.internal.nineoldandroids.animation.AnimatorSet;
import com.actionbarsherlock.internal.nineoldandroids.animation.AnimatorSet.Builder;
import com.actionbarsherlock.internal.nineoldandroids.animation.ObjectAnimator;
import com.actionbarsherlock.internal.nineoldandroids.view.NineViewGroup;
import com.actionbarsherlock.internal.view.menu.ActionMenuPresenter;
import com.actionbarsherlock.internal.view.menu.ActionMenuView;

public abstract class AbsActionBarView extends NineViewGroup
{
  private static final int FADE_DURATION = 200;
  private static final Interpolator sAlphaInterpolator = new DecelerateInterpolator();
  protected ActionMenuPresenter mActionMenuPresenter;
  protected int mContentHeight;
  final Context mContext;
  protected ActionMenuView mMenuView;
  protected boolean mSplitActionBar;
  protected ActionBarContainer mSplitView;
  protected boolean mSplitWhenNarrow;
  protected final VisibilityAnimListener mVisAnimListener = new VisibilityAnimListener();
  protected Animator mVisibilityAnim;

  public AbsActionBarView(Context paramContext)
  {
    super(paramContext);
    this.mContext = paramContext;
  }

  public AbsActionBarView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
  }

  public AbsActionBarView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.mContext = paramContext;
  }

  public void animateToVisibility(int paramInt)
  {
    if (this.mVisibilityAnim != null)
      this.mVisibilityAnim.cancel();
    ObjectAnimator localObjectAnimator3;
    if (paramInt == 0)
    {
      if (getVisibility() != 0)
      {
        setAlpha(0.0F);
        if ((this.mSplitView != null) && (this.mMenuView != null))
          this.mMenuView.setAlpha(0.0F);
      }
      float[] arrayOfFloat3 = new float[1];
      arrayOfFloat3[0] = 1.0F;
      localObjectAnimator3 = ObjectAnimator.ofFloat(this, "alpha", arrayOfFloat3);
      localObjectAnimator3.setDuration(200L);
      localObjectAnimator3.setInterpolator(sAlphaInterpolator);
      if ((this.mSplitView != null) && (this.mMenuView != null))
      {
        AnimatorSet localAnimatorSet2 = new AnimatorSet();
        ActionMenuView localActionMenuView2 = this.mMenuView;
        float[] arrayOfFloat4 = new float[1];
        arrayOfFloat4[0] = 1.0F;
        ObjectAnimator localObjectAnimator4 = ObjectAnimator.ofFloat(localActionMenuView2, "alpha", arrayOfFloat4);
        localObjectAnimator4.setDuration(200L);
        localAnimatorSet2.addListener(this.mVisAnimListener.withFinalVisibility(paramInt));
        localAnimatorSet2.play(localObjectAnimator3).with(localObjectAnimator4);
        localAnimatorSet2.start();
      }
    }
    while (true)
    {
      return;
      localObjectAnimator3.addListener(this.mVisAnimListener.withFinalVisibility(paramInt));
      localObjectAnimator3.start();
      continue;
      float[] arrayOfFloat1 = new float[1];
      arrayOfFloat1[0] = 0.0F;
      ObjectAnimator localObjectAnimator1 = ObjectAnimator.ofFloat(this, "alpha", arrayOfFloat1);
      localObjectAnimator1.setDuration(200L);
      localObjectAnimator1.setInterpolator(sAlphaInterpolator);
      if ((this.mSplitView != null) && (this.mMenuView != null))
      {
        AnimatorSet localAnimatorSet1 = new AnimatorSet();
        ActionMenuView localActionMenuView1 = this.mMenuView;
        float[] arrayOfFloat2 = new float[1];
        arrayOfFloat2[0] = 0.0F;
        ObjectAnimator localObjectAnimator2 = ObjectAnimator.ofFloat(localActionMenuView1, "alpha", arrayOfFloat2);
        localObjectAnimator2.setDuration(200L);
        localAnimatorSet1.addListener(this.mVisAnimListener.withFinalVisibility(paramInt));
        localAnimatorSet1.play(localObjectAnimator1).with(localObjectAnimator2);
        localAnimatorSet1.start();
        continue;
      }
      localObjectAnimator1.addListener(this.mVisAnimListener.withFinalVisibility(paramInt));
      localObjectAnimator1.start();
    }
  }

  public void dismissPopupMenus()
  {
    if (this.mActionMenuPresenter != null)
      this.mActionMenuPresenter.dismissPopupMenus();
  }

  public int getAnimatedVisibility()
  {
    if (this.mVisibilityAnim != null);
    for (int i = this.mVisAnimListener.mFinalVisibility; ; i = getVisibility())
      return i;
  }

  public int getContentHeight()
  {
    return this.mContentHeight;
  }

  public boolean hideOverflowMenu()
  {
    if (this.mActionMenuPresenter != null);
    for (boolean bool = this.mActionMenuPresenter.hideOverflowMenu(); ; bool = false)
      return bool;
  }

  public boolean isOverflowMenuShowing()
  {
    if (this.mActionMenuPresenter != null);
    for (boolean bool = this.mActionMenuPresenter.isOverflowMenuShowing(); ; bool = false)
      return bool;
  }

  public boolean isOverflowReserved()
  {
    if ((this.mActionMenuPresenter != null) && (this.mActionMenuPresenter.isOverflowReserved()));
    for (int i = 1; ; i = 0)
      return i;
  }

  protected int measureChildView(View paramView, int paramInt1, int paramInt2, int paramInt3)
  {
    paramView.measure(View.MeasureSpec.makeMeasureSpec(paramInt1, -2147483648), paramInt2);
    return Math.max(0, paramInt1 - paramView.getMeasuredWidth() - paramInt3);
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    if (Build.VERSION.SDK_INT >= 8)
      super.onConfigurationChanged(paramConfiguration);
    while (true)
    {
      TypedArray localTypedArray = getContext().obtainStyledAttributes(null, R.styleable.SherlockActionBar, R.attr.actionBarStyle, 0);
      setContentHeight(localTypedArray.getLayoutDimension(4, 0));
      localTypedArray.recycle();
      if (this.mSplitWhenNarrow)
        setSplitActionBar(ResourcesCompat.getResources_getBoolean(getContext(), R.bool.abs__split_action_bar_is_narrow));
      if (this.mActionMenuPresenter != null)
        this.mActionMenuPresenter.onConfigurationChanged(paramConfiguration);
      return;
      if (this.mMenuView == null)
        continue;
      this.mMenuView.onConfigurationChanged(paramConfiguration);
    }
  }

  protected int positionChild(View paramView, int paramInt1, int paramInt2, int paramInt3)
  {
    int i = paramView.getMeasuredWidth();
    int j = paramView.getMeasuredHeight();
    int k = paramInt2 + (paramInt3 - j) / 2;
    paramView.layout(paramInt1, k, paramInt1 + i, k + j);
    return i;
  }

  protected int positionChildInverse(View paramView, int paramInt1, int paramInt2, int paramInt3)
  {
    int i = paramView.getMeasuredWidth();
    int j = paramView.getMeasuredHeight();
    int k = paramInt2 + (paramInt3 - j) / 2;
    paramView.layout(paramInt1 - i, k, paramInt1, k + j);
    return i;
  }

  public void postShowOverflowMenu()
  {
    post(new Runnable()
    {
      public void run()
      {
        AbsActionBarView.this.showOverflowMenu();
      }
    });
  }

  public void setContentHeight(int paramInt)
  {
    this.mContentHeight = paramInt;
    requestLayout();
  }

  public void setSplitActionBar(boolean paramBoolean)
  {
    this.mSplitActionBar = paramBoolean;
  }

  public void setSplitView(ActionBarContainer paramActionBarContainer)
  {
    this.mSplitView = paramActionBarContainer;
  }

  public void setSplitWhenNarrow(boolean paramBoolean)
  {
    this.mSplitWhenNarrow = paramBoolean;
  }

  public void setVisibility(int paramInt)
  {
    if (this.mVisibilityAnim != null)
      this.mVisibilityAnim.end();
    super.setVisibility(paramInt);
  }

  public boolean showOverflowMenu()
  {
    if (this.mActionMenuPresenter != null);
    for (boolean bool = this.mActionMenuPresenter.showOverflowMenu(); ; bool = false)
      return bool;
  }

  protected class VisibilityAnimListener
    implements Animator.AnimatorListener
  {
    private boolean mCanceled = false;
    int mFinalVisibility;

    protected VisibilityAnimListener()
    {
    }

    public void onAnimationCancel(Animator paramAnimator)
    {
      this.mCanceled = true;
    }

    public void onAnimationEnd(Animator paramAnimator)
    {
      if (this.mCanceled);
      while (true)
      {
        return;
        AbsActionBarView.this.mVisibilityAnim = null;
        AbsActionBarView.this.setVisibility(this.mFinalVisibility);
        if ((AbsActionBarView.this.mSplitView == null) || (AbsActionBarView.this.mMenuView == null))
          continue;
        AbsActionBarView.this.mMenuView.setVisibility(this.mFinalVisibility);
      }
    }

    public void onAnimationRepeat(Animator paramAnimator)
    {
    }

    public void onAnimationStart(Animator paramAnimator)
    {
      AbsActionBarView.this.setVisibility(0);
      AbsActionBarView.this.mVisibilityAnim = paramAnimator;
      this.mCanceled = false;
    }

    public VisibilityAnimListener withFinalVisibility(int paramInt)
    {
      this.mFinalVisibility = paramInt;
      return this;
    }
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.actionbarsherlock.internal.widget.AbsActionBarView
 * JD-Core Version:    0.6.0
 */