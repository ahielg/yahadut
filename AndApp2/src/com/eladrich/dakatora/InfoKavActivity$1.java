package com.eladrich.dakatora;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;

class InfoKavActivity$1
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    Intent localIntent = new Intent("android.intent.action.DIAL", Uri.parse("tel:0578175767"));
    this.this$0.startActivity(localIntent);
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.eladrich.dakatora.InfoKavActivity.1
 * JD-Core Version:    0.6.0
 */