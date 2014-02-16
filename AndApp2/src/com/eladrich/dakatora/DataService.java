package com.eladrich.dakatora;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DataService extends Service
{
  private final IBinder binder = new MyBinder();
  private String currentDate;
  private boolean cursorAvailable;
  private Cursor dataCursor;
  private String queryDate;
  private ToraDBOpenHelper toraDBOpenHelper;

  private static String pad(int paramInt)
  {
    if (paramInt >= 10);
    for (String str = String.valueOf(paramInt); ; str = "0" + String.valueOf(paramInt))
      return str;
  }

  public Calendar calendarFromString(String paramString)
  {
    int i = Integer.valueOf(paramString.substring(0, 4)).intValue();
    int j = -1 + Integer.valueOf(paramString.substring(5, 7)).intValue();
    int k = Integer.valueOf(paramString.substring(8)).intValue();
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.clear();
    localCalendar.set(i, j, k);
    return localCalendar;
  }

  public Cursor getCursor(String paramString)
  {
    String[] arrayOfString1 = new String[6];
    arrayOfString1[0] = "_id";
    arrayOfString1[1] = "KEY_UNIQUE_DATE";
    arrayOfString1[2] = "KEY_TITLE_DAY";
    arrayOfString1[3] = "KEY_CONTENT_1";
    arrayOfString1[4] = "KEY_TITLE_1";
    arrayOfString1[5] = "KEY_SOURCE_1";
    String str = "KEY_UNIQUE_DATE = '" + paramString + "'";
    String[] arrayOfString2 = (String[])null;
    Cursor localCursor = this.toraDBOpenHelper.getWritableDatabase().query("datatora", arrayOfString1, str, arrayOfString2, null, null, null);
    if (localCursor.moveToFirst());
    while (true)
    {
      return localCursor;
      localCursor = null;
    }
  }

  public boolean getData(String paramString)
  {
    this.dataCursor = getCursor(paramString);
    if (this.dataCursor != null);
    for (int i = 1; ; i = 0)
      return i;
  }

  public String getMainTitle()
  {
    String str;
    if (this.cursorAvailable)
    {
      int i = this.dataCursor.getColumnIndex("KEY_TITLE_DAY");
      if ((i > -1) && (this.dataCursor.getCount() > 0))
        str = this.dataCursor.getString(i);
    }
    while (true)
    {
      return str;
      str = "not found";
      continue;
      str = "not found";
    }
  }

  public boolean getQuery(String paramString1, String paramString2)
  {
    try
    {
      localInputStream = new DefaultHttpClient().execute(new HttpPost("http://www.dakatora.co.il/android/dakatext.php?date=" + paramString1 + "&counter=" + paramString2 + "&phonetype=android")).getEntity().getContent();
    }
    catch (Exception localException2)
    {
      try
      {
        InputStream localInputStream;
        InputStreamReader localInputStreamReader = new InputStreamReader(localInputStream, "iso-8859-1");
        BufferedReader localBufferedReader = new BufferedReader(localInputStreamReader, 8);
        localStringBuilder = new StringBuilder();
        str1 = localBufferedReader.readLine();
        if (str1 == null)
        {
          localInputStream.close();
          str2 = localStringBuilder.toString();
        }
      }
      catch (Exception localException2)
      {
        try
        {
          StringBuilder localStringBuilder;
          String str1;
          String str2;
          JSONArray localJSONArray = new JSONArray(str2);
          for (int j = 0; ; j++)
          {
            int k = localJSONArray.length();
            if (j >= k)
            {
              i = 1;
              while (true)
              {
                return i;
                localException1 = localException1;
                i = 0;
                continue;
                localStringBuilder.append(str1 + "\n");
                break;
                localException2 = localException2;
                i = 0;
              }
            }
            JSONObject localJSONObject = localJSONArray.getJSONObject(j);
            ContentValues localContentValues = new ContentValues();
            localContentValues.put("KEY_UNIQUE_DATE", localJSONObject.getString("RText_date"));
            localContentValues.put("KEY_PARASHA", localJSONObject.getString("RText_prasha"));
            localContentValues.put("KEY_HOLIDAY", localJSONObject.getString("RText_holiday"));
            localContentValues.put("KEY_TITLE_DAY", localJSONObject.getString("RText_titleDay"));
            localContentValues.put("KEY_TITLE_1", localJSONObject.getString("RText_titleRate-1"));
            localContentValues.put("KEY_CONTENT_1", localJSONObject.getString("RText_contentRate-1"));
            localContentValues.put("KEY_SOURCE_1", localJSONObject.getString("RText_Source-1"));
            localContentValues.put("KEY_TITLE_2", localJSONObject.getString("RText_titleRate-2"));
            localContentValues.put("KEY_CONTENT_2", localJSONObject.getString("RText_contentRate-2"));
            localContentValues.put("KEY_SOURCE_2", localJSONObject.getString("RText_Source-2"));
            localContentValues.put("KEY_TITLE_3", localJSONObject.getString("RText_titleRate-3"));
            localContentValues.put("KEY_CONTENT_3", localJSONObject.getString("RText_contentRate-3"));
            localContentValues.put("KEY_SOURCE_3", localJSONObject.getString("RText_Source-3"));
            localContentValues.put("KEY_REMINDER", localJSONObject.getString("RText_Reminder"));
            long l = this.toraDBOpenHelper.getWritableDatabase().insert("datatora", null, localContentValues);
            Log.d("Value", "Key " + l);
          }
        }
        catch (JSONException localJSONException)
        {
          while (true)
            int i = 0;
        }
      }
    }
  }

  public void nextQueryDate()
  {
    calendarFromString(this.queryDate).roll(5, 1);
    this.cursorAvailable = getData(this.queryDate);
  }

  public boolean offLimit(String paramString1, String paramString2)
  {
    int i = 0;
    Calendar localCalendar1 = calendarFromString(paramString1);
    Calendar localCalendar2 = calendarFromString(paramString2);
    int j = localCalendar2.compareTo(localCalendar1);
    if (j == 0);
    while (true)
    {
      return i;
      if (j == 1)
      {
        localCalendar2.roll(5, -14);
        if (localCalendar2.compareTo(localCalendar1) != 1)
          continue;
        i = 1;
        continue;
      }
      if (j != -1)
        continue;
      localCalendar2.roll(5, 7);
      if (localCalendar2.compareTo(localCalendar1) != -1)
        continue;
      i = 1;
    }
  }

  public IBinder onBind(Intent paramIntent)
  {
    Calendar localCalendar = Calendar.getInstance();
    this.currentDate = (Integer.toString(localCalendar.get(1)) + '-' + pad(1 + localCalendar.get(2)) + '-' + pad(localCalendar.get(5)));
    this.queryDate = this.currentDate;
    this.toraDBOpenHelper = new ToraDBOpenHelper(getApplicationContext());
    this.cursorAvailable = getData(this.queryDate);
    if (!this.cursorAvailable)
      getQuery(this.queryDate, "open");
    return this.binder;
  }

  public void onCreate()
  {
    super.onCreate();
  }

  public void prevQueryDate()
  {
    calendarFromString(this.queryDate).roll(5, -1);
    this.cursorAvailable = getData(this.queryDate);
  }

  public void setQueryDate(String paramString)
  {
    this.queryDate = paramString;
    this.cursorAvailable = getData(this.queryDate);
  }

  public String stringFromCalendar(Calendar paramCalendar)
  {
    return Integer.toString(paramCalendar.get(1)) + '-' + pad(1 + paramCalendar.get(2)) + '-' + pad(paramCalendar.get(5));
  }

  public class MyBinder extends Binder
  {
    public MyBinder()
    {
    }

    DataService getService()
    {
      return DataService.this;
    }
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.eladrich.dakatora.DataService
 * JD-Core Version:    0.6.0
 */