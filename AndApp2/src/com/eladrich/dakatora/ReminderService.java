package com.eladrich.dakatora;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;

public class ReminderService extends IntentService
{
  private static final int NOTIF_ID = 1;

  public ReminderService()
  {
    super("ReminderService");
  }

  protected void onHandleIntent(Intent paramIntent)
  {
    NotificationManager localNotificationManager = (NotificationManager)getSystemService("notification");
    Notification localNotification = new Notification(2130837629, "דקה תורה - תזכורת", System.currentTimeMillis());
    localNotification.defaults = (0x1 | localNotification.defaults);
    localNotification.flags = (0x10 | localNotification.flags);
    PendingIntent localPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, HomeActivity.class), 0);
    localNotification.setLatestEventInfo(getApplicationContext(), "דקה תורה", "הגיע הזמן לשיעור היומי", localPendingIntent);
    localNotificationManager.notify(1, localNotification);
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.eladrich.dakatora.ReminderService
 * JD-Core Version:    0.6.0
 */