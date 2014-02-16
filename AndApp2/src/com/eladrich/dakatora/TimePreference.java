package com.eladrich.dakatora;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TimePicker;

public class TimePreference extends DialogPreference
{
  private int lastHour = 0;
  private int lastMinute = 0;
  private TimePicker picker = null;

  public TimePreference(Context paramContext)
  {
    this(paramContext, null);
  }

  public TimePreference(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public TimePreference(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    setPositiveButtonText("Set");
    setNegativeButtonText("Cancel");
  }

  public static int getHour(String paramString)
  {
    return Integer.parseInt(paramString.split(":")[0]);
  }

  public static int getMinute(String paramString)
  {
    return Integer.parseInt(paramString.split(":")[1]);
  }

  protected void onBindDialogView(View paramView)
  {
    super.onBindDialogView(paramView);
    this.picker.setCurrentHour(Integer.valueOf(this.lastHour));
    this.picker.setCurrentMinute(Integer.valueOf(this.lastMinute));
  }

  protected View onCreateDialogView()
  {
    this.picker = new TimePicker(getContext());
    return this.picker;
  }

  protected void onDialogClosed(boolean paramBoolean)
  {
    super.onDialogClosed(paramBoolean);
    this.picker.clearFocus();
    if (paramBoolean)
    {
      this.lastHour = this.picker.getCurrentHour().intValue();
      this.lastMinute = this.picker.getCurrentMinute().intValue();
      String str = String.valueOf(this.lastHour) + ":" + String.valueOf(this.lastMinute);
      if (callChangeListener(str))
        persistString(str);
    }
  }

  protected Object onGetDefaultValue(TypedArray paramTypedArray, int paramInt)
  {
    return paramTypedArray.getString(paramInt);
  }

  protected void onSetInitialValue(boolean paramBoolean, Object paramObject)
  {
    String str;
    if (paramBoolean)
      if (paramObject == null)
        str = getPersistedString("00:00");
    while (true)
    {
      this.lastHour = getHour(str);
      this.lastMinute = getMinute(str);
      return;
      str = getPersistedString(paramObject.toString());
      continue;
      str = paramObject.toString();
    }
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.eladrich.dakatora.TimePreference
 * JD-Core Version:    0.6.0
 */