package com.eladrich.dakatora;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;

class AboutActivity$2
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse("market://details?id=com.eladrich.dakatora"));
    this.this$0.startActivity(localIntent);
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.eladrich.dakatora.AboutActivity.2
 * JD-Core Version:    0.6.0
 */