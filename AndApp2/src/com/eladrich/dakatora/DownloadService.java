package com.eladrich.dakatora;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

public class DownloadService extends IntentService
{
  private static final int NOTIF_ID = 1;

  public DownloadService()
  {
    super("DownloadService");
  }

  protected void onHandleIntent(Intent paramIntent)
  {
    Bundle localBundle = paramIntent.getExtras();
    localBundle.getString("link");
    localBundle.getString("name");
    NotificationManager localNotificationManager = (NotificationManager)getSystemService("notification");
    Notification localNotification = new Notification(2130837629, "השמירה מתבצעת", System.currentTimeMillis());
    localNotification.flags = (0x2 | localNotification.flags);
    Intent localIntent = new Intent(getApplicationContext(), HomeActivity.class);
    PendingIntent localPendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, localIntent, 0);
    localNotification.setLatestEventInfo(getApplicationContext(), "דקה תורה", "השמירה מתבצעת כעת", localPendingIntent);
    localNotificationManager.notify(1, localNotification);
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.eladrich.dakatora.DownloadService
 * JD-Core Version:    0.6.0
 */