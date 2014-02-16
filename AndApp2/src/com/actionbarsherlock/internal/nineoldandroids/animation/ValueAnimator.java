package com.actionbarsherlock.internal.nineoldandroids.animation;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AndroidRuntimeException;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ValueAnimator extends Animator
{
  static final int ANIMATION_FRAME = 1;
  static final int ANIMATION_START = 0;
  private static final long DEFAULT_FRAME_DELAY = 10L;
  public static final int INFINITE = -1;
  public static final int RESTART = 1;
  public static final int REVERSE = 2;
  static final int RUNNING = 1;
  static final int SEEKED = 2;
  static final int STOPPED;
  private static ThreadLocal<AnimationHandler> sAnimationHandler = new ThreadLocal();
  private static final ThreadLocal<ArrayList<ValueAnimator>> sAnimations = new ThreadLocal()
  {
    protected ArrayList<ValueAnimator> initialValue()
    {
      return new ArrayList();
    }
  };
  private static final Interpolator sDefaultInterpolator;
  private static final ThreadLocal<ArrayList<ValueAnimator>> sDelayedAnims;
  private static final ThreadLocal<ArrayList<ValueAnimator>> sEndingAnims;
  private static long sFrameDelay;
  private static final ThreadLocal<ArrayList<ValueAnimator>> sPendingAnimations = new ThreadLocal()
  {
    protected ArrayList<ValueAnimator> initialValue()
    {
      return new ArrayList();
    }
  };
  private static final ThreadLocal<ArrayList<ValueAnimator>> sReadyAnims;
  private float mCurrentFraction = 0.0F;
  private int mCurrentIteration = 0;
  private long mDelayStartTime;
  private long mDuration = 300L;
  boolean mInitialized = false;
  private Interpolator mInterpolator = sDefaultInterpolator;
  private boolean mPlayingBackwards = false;
  int mPlayingState = 0;
  private int mRepeatCount = 0;
  private int mRepeatMode = 1;
  private boolean mRunning = false;
  long mSeekTime = -1L;
  private long mStartDelay = 0L;
  long mStartTime;
  private boolean mStarted = false;
  private boolean mStartedDelay = false;
  private ArrayList<AnimatorUpdateListener> mUpdateListeners = null;
  PropertyValuesHolder[] mValues;
  HashMap<String, PropertyValuesHolder> mValuesMap;

  static
  {
    sDelayedAnims = new ThreadLocal()
    {
      protected ArrayList<ValueAnimator> initialValue()
      {
        return new ArrayList();
      }
    };
    sEndingAnims = new ThreadLocal()
    {
      protected ArrayList<ValueAnimator> initialValue()
      {
        return new ArrayList();
      }
    };
    sReadyAnims = new ThreadLocal()
    {
      protected ArrayList<ValueAnimator> initialValue()
      {
        return new ArrayList();
      }
    };
    sDefaultInterpolator = new AccelerateDecelerateInterpolator();
    sFrameDelay = 10L;
  }

  public static void clearAllAnimations()
  {
    ((ArrayList)sAnimations.get()).clear();
    ((ArrayList)sPendingAnimations.get()).clear();
    ((ArrayList)sDelayedAnims.get()).clear();
  }

  private boolean delayedAnimationFrame(long paramLong)
  {
    int i = 1;
    if (!this.mStartedDelay)
    {
      this.mStartedDelay = i;
      this.mDelayStartTime = paramLong;
      i = 0;
    }
    while (true)
    {
      return i;
      long l = paramLong - this.mDelayStartTime;
      if (l <= this.mStartDelay)
        break;
      this.mStartTime = (paramLong - (l - this.mStartDelay));
      this.mPlayingState = i;
    }
  }

  private void endAnimation()
  {
    ((ArrayList)sAnimations.get()).remove(this);
    ((ArrayList)sPendingAnimations.get()).remove(this);
    ((ArrayList)sDelayedAnims.get()).remove(this);
    this.mPlayingState = 0;
    ArrayList localArrayList;
    int i;
    if ((this.mRunning) && (this.mListeners != null))
    {
      localArrayList = (ArrayList)this.mListeners.clone();
      i = localArrayList.size();
    }
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        this.mRunning = false;
        this.mStarted = false;
        return;
      }
      ((Animator.AnimatorListener)localArrayList.get(j)).onAnimationEnd(this);
    }
  }

  public static int getCurrentAnimationsCount()
  {
    return ((ArrayList)sAnimations.get()).size();
  }

  public static long getFrameDelay()
  {
    return sFrameDelay;
  }

  public static ValueAnimator ofFloat(float[] paramArrayOfFloat)
  {
    ValueAnimator localValueAnimator = new ValueAnimator();
    localValueAnimator.setFloatValues(paramArrayOfFloat);
    return localValueAnimator;
  }

  public static ValueAnimator ofInt(int[] paramArrayOfInt)
  {
    ValueAnimator localValueAnimator = new ValueAnimator();
    localValueAnimator.setIntValues(paramArrayOfInt);
    return localValueAnimator;
  }

  public static ValueAnimator ofObject(TypeEvaluator paramTypeEvaluator, Object[] paramArrayOfObject)
  {
    ValueAnimator localValueAnimator = new ValueAnimator();
    localValueAnimator.setObjectValues(paramArrayOfObject);
    localValueAnimator.setEvaluator(paramTypeEvaluator);
    return localValueAnimator;
  }

  public static ValueAnimator ofPropertyValuesHolder(PropertyValuesHolder[] paramArrayOfPropertyValuesHolder)
  {
    ValueAnimator localValueAnimator = new ValueAnimator();
    localValueAnimator.setValues(paramArrayOfPropertyValuesHolder);
    return localValueAnimator;
  }

  public static void setFrameDelay(long paramLong)
  {
    sFrameDelay = paramLong;
  }

  private void start(boolean paramBoolean)
  {
    if (Looper.myLooper() == null)
      throw new AndroidRuntimeException("Animators may only be run on Looper threads");
    this.mPlayingBackwards = paramBoolean;
    this.mCurrentIteration = 0;
    this.mPlayingState = 0;
    this.mStarted = true;
    this.mStartedDelay = false;
    ((ArrayList)sPendingAnimations.get()).add(this);
    ArrayList localArrayList;
    int i;
    if (this.mStartDelay == 0L)
    {
      setCurrentPlayTime(getCurrentPlayTime());
      this.mPlayingState = 0;
      this.mRunning = true;
      if (this.mListeners != null)
      {
        localArrayList = (ArrayList)this.mListeners.clone();
        i = localArrayList.size();
      }
    }
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        AnimationHandler localAnimationHandler = (AnimationHandler)sAnimationHandler.get();
        if (localAnimationHandler == null)
        {
          localAnimationHandler = new AnimationHandler(null);
          sAnimationHandler.set(localAnimationHandler);
        }
        localAnimationHandler.sendEmptyMessage(0);
        return;
      }
      ((Animator.AnimatorListener)localArrayList.get(j)).onAnimationStart(this);
    }
  }

  private void startAnimation()
  {
    initAnimation();
    ((ArrayList)sAnimations.get()).add(this);
    ArrayList localArrayList;
    int i;
    if ((this.mStartDelay > 0L) && (this.mListeners != null))
    {
      localArrayList = (ArrayList)this.mListeners.clone();
      i = localArrayList.size();
    }
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return;
      ((Animator.AnimatorListener)localArrayList.get(j)).onAnimationStart(this);
    }
  }

  public void addUpdateListener(AnimatorUpdateListener paramAnimatorUpdateListener)
  {
    if (this.mUpdateListeners == null)
      this.mUpdateListeners = new ArrayList();
    this.mUpdateListeners.add(paramAnimatorUpdateListener);
  }

  void animateValue(float paramFloat)
  {
    float f = this.mInterpolator.getInterpolation(paramFloat);
    this.mCurrentFraction = f;
    int i = this.mValues.length;
    int j = 0;
    int k;
    if (j >= i)
      if (this.mUpdateListeners != null)
        k = this.mUpdateListeners.size();
    for (int m = 0; ; m++)
    {
      if (m >= k)
      {
        return;
        this.mValues[j].calculateValue(f);
        j++;
        break;
      }
      ((AnimatorUpdateListener)this.mUpdateListeners.get(m)).onAnimationUpdate(this);
    }
  }

  boolean animationFrame(long paramLong)
  {
    int i = 0;
    if (this.mPlayingState == 0)
    {
      this.mPlayingState = 1;
      if (this.mSeekTime >= 0L)
        break label58;
      this.mStartTime = paramLong;
    }
    while (true)
      switch (this.mPlayingState)
      {
      default:
        return i;
        label58: this.mStartTime = (paramLong - this.mSeekTime);
        this.mSeekTime = -1L;
      case 1:
      case 2:
      }
    float f;
    label102: int k;
    label148: boolean bool;
    if (this.mDuration > 0L)
    {
      f = (float)(paramLong - this.mStartTime) / (float)this.mDuration;
      if (f >= 1.0F)
      {
        if ((this.mCurrentIteration >= this.mRepeatCount) && (this.mRepeatCount != -1))
          break label268;
        if (this.mListeners != null)
        {
          int j = this.mListeners.size();
          k = 0;
          if (k < j)
            break label238;
        }
        if (this.mRepeatMode == 2)
        {
          if (!this.mPlayingBackwards)
            break label262;
          bool = false;
          label173: this.mPlayingBackwards = bool;
        }
        this.mCurrentIteration += (int)f;
        f %= 1.0F;
        this.mStartTime += this.mDuration;
      }
    }
    while (true)
    {
      if (this.mPlayingBackwards)
        f = 1.0F - f;
      animateValue(f);
      break;
      f = 1.0F;
      break label102;
      label238: ((Animator.AnimatorListener)this.mListeners.get(k)).onAnimationRepeat(this);
      k++;
      break label148;
      label262: bool = true;
      break label173;
      label268: i = 1;
      f = Math.min(f, 1.0F);
    }
  }

  public void cancel()
  {
    Iterator localIterator;
    if ((this.mPlayingState != 0) || (((ArrayList)sPendingAnimations.get()).contains(this)) || (((ArrayList)sDelayedAnims.get()).contains(this)))
      if ((this.mRunning) && (this.mListeners != null))
        localIterator = ((ArrayList)this.mListeners.clone()).iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        endAnimation();
        return;
      }
      ((Animator.AnimatorListener)localIterator.next()).onAnimationCancel(this);
    }
  }

  public ValueAnimator clone()
  {
    ValueAnimator localValueAnimator = (ValueAnimator)super.clone();
    ArrayList localArrayList;
    int m;
    PropertyValuesHolder[] arrayOfPropertyValuesHolder;
    int i;
    if (this.mUpdateListeners != null)
    {
      localArrayList = this.mUpdateListeners;
      localValueAnimator.mUpdateListeners = new ArrayList();
      int k = localArrayList.size();
      m = 0;
      if (m < k);
    }
    else
    {
      localValueAnimator.mSeekTime = -1L;
      localValueAnimator.mPlayingBackwards = false;
      localValueAnimator.mCurrentIteration = 0;
      localValueAnimator.mInitialized = false;
      localValueAnimator.mPlayingState = 0;
      localValueAnimator.mStartedDelay = false;
      arrayOfPropertyValuesHolder = this.mValues;
      if (arrayOfPropertyValuesHolder != null)
      {
        i = arrayOfPropertyValuesHolder.length;
        localValueAnimator.mValues = new PropertyValuesHolder[i];
        localValueAnimator.mValuesMap = new HashMap(i);
      }
    }
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        return localValueAnimator;
        localValueAnimator.mUpdateListeners.add((AnimatorUpdateListener)localArrayList.get(m));
        m++;
        break;
      }
      PropertyValuesHolder localPropertyValuesHolder = arrayOfPropertyValuesHolder[j].clone();
      localValueAnimator.mValues[j] = localPropertyValuesHolder;
      localValueAnimator.mValuesMap.put(localPropertyValuesHolder.getPropertyName(), localPropertyValuesHolder);
    }
  }

  public void end()
  {
    if ((!((ArrayList)sAnimations.get()).contains(this)) && (!((ArrayList)sPendingAnimations.get()).contains(this)))
    {
      this.mStartedDelay = false;
      startAnimation();
      if ((this.mRepeatCount <= 0) || ((0x1 & this.mRepeatCount) != 1))
        break label82;
      animateValue(0.0F);
    }
    while (true)
    {
      endAnimation();
      return;
      if (this.mInitialized)
        break;
      initAnimation();
      break;
      label82: animateValue(1.0F);
    }
  }

  public float getAnimatedFraction()
  {
    return this.mCurrentFraction;
  }

  public Object getAnimatedValue()
  {
    if ((this.mValues != null) && (this.mValues.length > 0));
    for (Object localObject = this.mValues[0].getAnimatedValue(); ; localObject = null)
      return localObject;
  }

  public Object getAnimatedValue(String paramString)
  {
    PropertyValuesHolder localPropertyValuesHolder = (PropertyValuesHolder)this.mValuesMap.get(paramString);
    if (localPropertyValuesHolder != null);
    for (Object localObject = localPropertyValuesHolder.getAnimatedValue(); ; localObject = null)
      return localObject;
  }

  public long getCurrentPlayTime()
  {
    long l;
    if ((!this.mInitialized) || (this.mPlayingState == 0))
      l = 0L;
    while (true)
    {
      return l;
      l = AnimationUtils.currentAnimationTimeMillis() - this.mStartTime;
    }
  }

  public long getDuration()
  {
    return this.mDuration;
  }

  public Interpolator getInterpolator()
  {
    return this.mInterpolator;
  }

  public int getRepeatCount()
  {
    return this.mRepeatCount;
  }

  public int getRepeatMode()
  {
    return this.mRepeatMode;
  }

  public long getStartDelay()
  {
    return this.mStartDelay;
  }

  public PropertyValuesHolder[] getValues()
  {
    return this.mValues;
  }

  void initAnimation()
  {
    int i;
    if (!this.mInitialized)
      i = this.mValues.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        this.mInitialized = true;
        return;
      }
      this.mValues[j].init();
    }
  }

  public boolean isRunning()
  {
    int i = 1;
    if ((this.mPlayingState != i) && (!this.mRunning))
      i = 0;
    return i;
  }

  public boolean isStarted()
  {
    return this.mStarted;
  }

  public void removeAllUpdateListeners()
  {
    if (this.mUpdateListeners == null);
    while (true)
    {
      return;
      this.mUpdateListeners.clear();
      this.mUpdateListeners = null;
    }
  }

  public void removeUpdateListener(AnimatorUpdateListener paramAnimatorUpdateListener)
  {
    if (this.mUpdateListeners == null);
    while (true)
    {
      return;
      this.mUpdateListeners.remove(paramAnimatorUpdateListener);
      if (this.mUpdateListeners.size() != 0)
        continue;
      this.mUpdateListeners = null;
    }
  }

  public void reverse()
  {
    boolean bool;
    if (this.mPlayingBackwards)
    {
      bool = false;
      this.mPlayingBackwards = bool;
      if (this.mPlayingState != 1)
        break label53;
      long l1 = AnimationUtils.currentAnimationTimeMillis();
      long l2 = l1 - this.mStartTime;
      this.mStartTime = (l1 - (this.mDuration - l2));
    }
    while (true)
    {
      return;
      bool = true;
      break;
      label53: start(true);
    }
  }

  public void setCurrentPlayTime(long paramLong)
  {
    initAnimation();
    long l = AnimationUtils.currentAnimationTimeMillis();
    if (this.mPlayingState != 1)
    {
      this.mSeekTime = paramLong;
      this.mPlayingState = 2;
    }
    this.mStartTime = (l - paramLong);
    animationFrame(l);
  }

  public ValueAnimator setDuration(long paramLong)
  {
    if (paramLong < 0L)
      throw new IllegalArgumentException("Animators cannot have negative duration: " + paramLong);
    this.mDuration = paramLong;
    return this;
  }

  public void setEvaluator(TypeEvaluator paramTypeEvaluator)
  {
    if ((paramTypeEvaluator != null) && (this.mValues != null) && (this.mValues.length > 0))
      this.mValues[0].setEvaluator(paramTypeEvaluator);
  }

  public void setFloatValues(float[] paramArrayOfFloat)
  {
    if ((paramArrayOfFloat == null) || (paramArrayOfFloat.length == 0))
      return;
    if ((this.mValues == null) || (this.mValues.length == 0))
    {
      PropertyValuesHolder[] arrayOfPropertyValuesHolder = new PropertyValuesHolder[1];
      arrayOfPropertyValuesHolder[0] = PropertyValuesHolder.ofFloat("", paramArrayOfFloat);
      setValues(arrayOfPropertyValuesHolder);
    }
    while (true)
    {
      this.mInitialized = false;
      break;
      this.mValues[0].setFloatValues(paramArrayOfFloat);
    }
  }

  public void setIntValues(int[] paramArrayOfInt)
  {
    if ((paramArrayOfInt == null) || (paramArrayOfInt.length == 0))
      return;
    if ((this.mValues == null) || (this.mValues.length == 0))
    {
      PropertyValuesHolder[] arrayOfPropertyValuesHolder = new PropertyValuesHolder[1];
      arrayOfPropertyValuesHolder[0] = PropertyValuesHolder.ofInt("", paramArrayOfInt);
      setValues(arrayOfPropertyValuesHolder);
    }
    while (true)
    {
      this.mInitialized = false;
      break;
      this.mValues[0].setIntValues(paramArrayOfInt);
    }
  }

  public void setInterpolator(Interpolator paramInterpolator)
  {
    if (paramInterpolator != null);
    for (this.mInterpolator = paramInterpolator; ; this.mInterpolator = new LinearInterpolator())
      return;
  }

  public void setObjectValues(Object[] paramArrayOfObject)
  {
    if ((paramArrayOfObject == null) || (paramArrayOfObject.length == 0))
      return;
    if ((this.mValues == null) || (this.mValues.length == 0))
    {
      PropertyValuesHolder[] arrayOfPropertyValuesHolder = new PropertyValuesHolder[1];
      arrayOfPropertyValuesHolder[0] = PropertyValuesHolder.ofObject("", null, paramArrayOfObject);
      setValues(arrayOfPropertyValuesHolder);
    }
    while (true)
    {
      this.mInitialized = false;
      break;
      this.mValues[0].setObjectValues(paramArrayOfObject);
    }
  }

  public void setRepeatCount(int paramInt)
  {
    this.mRepeatCount = paramInt;
  }

  public void setRepeatMode(int paramInt)
  {
    this.mRepeatMode = paramInt;
  }

  public void setStartDelay(long paramLong)
  {
    this.mStartDelay = paramLong;
  }

  public void setValues(PropertyValuesHolder[] paramArrayOfPropertyValuesHolder)
  {
    int i = paramArrayOfPropertyValuesHolder.length;
    this.mValues = paramArrayOfPropertyValuesHolder;
    this.mValuesMap = new HashMap(i);
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        this.mInitialized = false;
        return;
      }
      PropertyValuesHolder localPropertyValuesHolder = paramArrayOfPropertyValuesHolder[j];
      this.mValuesMap.put(localPropertyValuesHolder.getPropertyName(), localPropertyValuesHolder);
    }
  }

  public void start()
  {
    start(false);
  }

  public String toString()
  {
    String str = "ValueAnimator@" + Integer.toHexString(hashCode());
    if (this.mValues != null);
    for (int i = 0; ; i++)
    {
      if (i >= this.mValues.length)
        return str;
      str = str + "\n    " + this.mValues[i].toString();
    }
  }

  private static class AnimationHandler extends Handler
  {
    public void handleMessage(Message paramMessage)
    {
      int i = 1;
      ArrayList localArrayList1 = (ArrayList)ValueAnimator.sAnimations.get();
      ArrayList localArrayList2 = (ArrayList)ValueAnimator.sDelayedAnims.get();
      ArrayList localArrayList5;
      long l;
      ArrayList localArrayList3;
      ArrayList localArrayList4;
      int k;
      label122: int i3;
      label144: int n;
      int i1;
      switch (paramMessage.what)
      {
      default:
        return;
      case 0:
        localArrayList5 = (ArrayList)ValueAnimator.sPendingAnimations.get();
        if ((localArrayList1.size() > 0) || (localArrayList2.size() > 0))
          i = 0;
        if (localArrayList5.size() > 0)
          break;
      case 1:
        l = AnimationUtils.currentAnimationTimeMillis();
        localArrayList3 = (ArrayList)ValueAnimator.sReadyAnims.get();
        localArrayList4 = (ArrayList)ValueAnimator.sEndingAnims.get();
        int j = localArrayList2.size();
        k = 0;
        if (k >= j)
        {
          int m = localArrayList3.size();
          if (m > 0)
          {
            i3 = 0;
            if (i3 < m)
              break label352;
            localArrayList3.clear();
          }
          n = localArrayList1.size();
          i1 = 0;
          label165: if (i1 < n)
            break label389;
          if (localArrayList4.size() <= 0);
        }
      }
      for (int i2 = 0; ; i2++)
      {
        if (i2 >= localArrayList4.size())
        {
          localArrayList4.clear();
          if ((i == 0) || ((localArrayList1.isEmpty()) && (localArrayList2.isEmpty())))
            break;
          sendEmptyMessageDelayed(1, Math.max(0L, ValueAnimator.sFrameDelay - (AnimationUtils.currentAnimationTimeMillis() - l)));
          break;
          ArrayList localArrayList6 = (ArrayList)localArrayList5.clone();
          localArrayList5.clear();
          int i4 = localArrayList6.size();
          int i5 = 0;
          label265: ValueAnimator localValueAnimator4;
          if (i5 < i4)
          {
            localValueAnimator4 = (ValueAnimator)localArrayList6.get(i5);
            if (localValueAnimator4.mStartDelay != 0L)
              break label305;
            localValueAnimator4.startAnimation();
          }
          while (true)
          {
            i5++;
            break label265;
            break;
            label305: localArrayList2.add(localValueAnimator4);
          }
          ValueAnimator localValueAnimator1 = (ValueAnimator)localArrayList2.get(k);
          if (localValueAnimator1.delayedAnimationFrame(l))
            localArrayList3.add(localValueAnimator1);
          k++;
          break label122;
          label352: ValueAnimator localValueAnimator3 = (ValueAnimator)localArrayList3.get(i3);
          localValueAnimator3.startAnimation();
          localValueAnimator3.mRunning = true;
          localArrayList2.remove(localValueAnimator3);
          i3++;
          break label144;
          label389: ValueAnimator localValueAnimator2 = (ValueAnimator)localArrayList1.get(i1);
          if (localValueAnimator2.animationFrame(l))
            localArrayList4.add(localValueAnimator2);
          if (localArrayList1.size() == n)
          {
            i1++;
            break label165;
          }
          n--;
          localArrayList4.remove(localValueAnimator2);
          break label165;
        }
        ((ValueAnimator)localArrayList4.get(i2)).endAnimation();
      }
    }
  }

  public static abstract interface AnimatorUpdateListener
  {
    public abstract void onAnimationUpdate(ValueAnimator paramValueAnimator);
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.actionbarsherlock.internal.nineoldandroids.animation.ValueAnimator
 * JD-Core Version:    0.6.0
 */