package com.actionbarsherlock.internal.widget;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

class IcsSpinner$DropdownPopup$1
  implements AdapterView.OnItemClickListener
{
  public void onItemClick(AdapterView paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    IcsSpinner.DropdownPopup.access$0(this.this$1).setSelection(paramInt);
    this.this$1.dismiss();
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.actionbarsherlock.internal.widget.IcsSpinner.DropdownPopup.1
 * JD-Core Version:    0.6.0
 */