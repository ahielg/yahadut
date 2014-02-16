package com.eladrich.dakatora;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;

class AboutActivity$1
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    Intent localIntent = new Intent("android.intent.action.SENDTO");
    localIntent.setData(Uri.parse("mailto:info@dakatora.co.il"));
    this.this$0.startActivity(Intent.createChooser(localIntent, "שלח אימייל"));
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.eladrich.dakatora.AboutActivity.1
 * JD-Core Version:    0.6.0
 */