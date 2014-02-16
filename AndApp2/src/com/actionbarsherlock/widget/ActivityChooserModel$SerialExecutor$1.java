package com.actionbarsherlock.widget;

class ActivityChooserModel$SerialExecutor$1
  implements Runnable
{
  public void run()
  {
    try
    {
      this.val$r.run();
      return;
    }
    finally
    {
      this.this$1.scheduleNext();
    }
    throw localObject;
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.actionbarsherlock.widget.ActivityChooserModel.SerialExecutor.1
 * JD-Core Version:    0.6.0
 */