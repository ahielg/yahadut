package com.eladrich.dakatora;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

class AboutActivity$3
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType("text/plain");
    localIntent.putExtra("android.intent.extra.SUBJECT", "דקה תורה - לימוד תורה יומי לחייל");
    localIntent.putExtra("android.intent.extra.TEXT", "השתמשתי באפליקציית דקה תורה וחשבתי שאולי תרצה לנסות אותה \n http://dakatora.co.il");
    this.this$0.startActivity(localIntent);
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.eladrich.dakatora.AboutActivity.3
 * JD-Core Version:    0.6.0
 */