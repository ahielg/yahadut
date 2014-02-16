package com.eladrich.dakatora;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

public class SqlDataClass
{
  private String currentDate;
  private boolean dataAvailable;
  SQLiteDatabase db;
  String[] delete_result_columns;
  private String queryDate;
  String[] result_columns;
  String[] result_data;
  private ToraDBOpenHelper toraDBOpenHelper;

  public SqlDataClass(Context paramContext)
  {
    String[] arrayOfString1 = new String[15];
    arrayOfString1[0] = "_id";
    arrayOfString1[1] = "KEY_UNIQUE_DATE";
    arrayOfString1[2] = "KEY_PARASHA";
    arrayOfString1[3] = "KEY_HOLIDAY";
    arrayOfString1[4] = "KEY_REMINDER";
    arrayOfString1[5] = "KEY_TITLE_DAY";
    arrayOfString1[6] = "KEY_CONTENT_1";
    arrayOfString1[7] = "KEY_TITLE_1";
    arrayOfString1[8] = "KEY_SOURCE_1";
    arrayOfString1[9] = "KEY_CONTENT_2";
    arrayOfString1[10] = "KEY_TITLE_2";
    arrayOfString1[11] = "KEY_SOURCE_2";
    arrayOfString1[12] = "KEY_CONTENT_3";
    arrayOfString1[13] = "KEY_TITLE_3";
    arrayOfString1[14] = "KEY_SOURCE_3";
    this.result_columns = arrayOfString1;
    String[] arrayOfString2 = new String[2];
    arrayOfString2[0] = "_id";
    arrayOfString2[1] = "KEY_UNIQUE_DATE";
    this.delete_result_columns = arrayOfString2;
    this.result_data = new String[this.result_columns.length];
    for (int i = 0; ; i++)
    {
      if (i >= this.result_data.length)
      {
        Calendar localCalendar = Calendar.getInstance();
        this.currentDate = (Integer.toString(localCalendar.get(1)) + '-' + pad(1 + localCalendar.get(2)) + '-' + pad(localCalendar.get(5)));
        this.queryDate = this.currentDate;
        this.toraDBOpenHelper = new ToraDBOpenHelper(paramContext);
        this.db = this.toraDBOpenHelper.getWritableDatabase();
        this.dataAvailable = getData(this.queryDate);
        return;
      }
      this.result_data[i] = "";
    }
  }

  public SqlDataClass(String paramString, Context paramContext)
  {
    String[] arrayOfString1 = new String[15];
    arrayOfString1[0] = "_id";
    arrayOfString1[1] = "KEY_UNIQUE_DATE";
    arrayOfString1[2] = "KEY_PARASHA";
    arrayOfString1[3] = "KEY_HOLIDAY";
    arrayOfString1[4] = "KEY_REMINDER";
    arrayOfString1[5] = "KEY_TITLE_DAY";
    arrayOfString1[6] = "KEY_CONTENT_1";
    arrayOfString1[7] = "KEY_TITLE_1";
    arrayOfString1[8] = "KEY_SOURCE_1";
    arrayOfString1[9] = "KEY_CONTENT_2";
    arrayOfString1[10] = "KEY_TITLE_2";
    arrayOfString1[11] = "KEY_SOURCE_2";
    arrayOfString1[12] = "KEY_CONTENT_3";
    arrayOfString1[13] = "KEY_TITLE_3";
    arrayOfString1[14] = "KEY_SOURCE_3";
    this.result_columns = arrayOfString1;
    String[] arrayOfString2 = new String[2];
    arrayOfString2[0] = "_id";
    arrayOfString2[1] = "KEY_UNIQUE_DATE";
    this.delete_result_columns = arrayOfString2;
    this.result_data = new String[this.result_columns.length];
    for (int i = 0; ; i++)
    {
      if (i >= this.result_data.length)
      {
        this.queryDate = paramString;
        Calendar localCalendar = Calendar.getInstance();
        this.currentDate = (Integer.toString(localCalendar.get(1)) + '-' + pad(1 + localCalendar.get(2)) + '-' + pad(localCalendar.get(5)));
        this.toraDBOpenHelper = new ToraDBOpenHelper(paramContext);
        this.db = this.toraDBOpenHelper.getWritableDatabase();
        this.dataAvailable = getData(this.queryDate);
        return;
      }
      this.result_data[i] = "";
    }
  }

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

  public void cleanDatabase()
  {
    String[] arrayOfString = (String[])null;
    Cursor localCursor = this.db.query("datatora", this.delete_result_columns, null, arrayOfString, null, null, null);
    String str1 = "";
    while (true)
    {
      if (!localCursor.moveToNext())
      {
        if (!str1.equals(""))
          this.db.delete("datatora", str1, null);
        localCursor.close();
        return;
      }
      int i = localCursor.getColumnIndex("KEY_UNIQUE_DATE");
      if ((i <= -1) || (localCursor.getCount() <= 0))
        continue;
      String str2 = localCursor.getString(i);
      if (!offLimit(this.currentDate, str2))
        continue;
      if (str1.equals(""))
      {
        str1 = "KEY_UNIQUE_DATE = '" + str2 + "'";
        continue;
      }
      str1 = str1 + " OR KEY_UNIQUE_DATE = '" + str2 + "'";
    }
  }

  public void closeDatabase()
  {
    if ((this.db != null) && (this.db.isOpen()))
      this.db.close();
  }

  public boolean cursorExist(String paramString)
  {
    int i = 0;
    String str = "KEY_UNIQUE_DATE = '" + paramString + "'";
    String[] arrayOfString = (String[])null;
    Cursor localCursor = this.db.query("datatora", this.result_columns, str, arrayOfString, null, null, null);
    if (localCursor.moveToFirst())
      i = 1;
    localCursor.close();
    return i;
  }

  public Cursor getCursor(String paramString)
  {
    Object localObject = null;
    String str = "KEY_UNIQUE_DATE = '" + paramString + "'";
    String[] arrayOfString = (String[])null;
    Cursor localCursor = this.db.query("datatora", this.result_columns, str, arrayOfString, null, null, null);
    if (localCursor.moveToFirst())
      localObject = localCursor;
    return localObject;
  }

  public boolean getData(String paramString)
  {
    int i = 0;
    String str1 = "KEY_UNIQUE_DATE = '" + paramString + "'";
    String[] arrayOfString = (String[])null;
    Cursor localCursor = this.db.query("datatora", this.result_columns, str1, arrayOfString, null, null, null);
    int k;
    if (localCursor.moveToFirst())
    {
      i = 1;
      if (i == 0)
        break label192;
      k = 0;
      label70: if (k < this.result_columns.length)
        break label140;
    }
    while (true)
    {
      localCursor.close();
      return i;
      localCursor.close();
      getQuery(paramString, "open");
      localCursor = this.db.query("datatora", this.result_columns, str1, arrayOfString, null, null, null);
      if (!localCursor.moveToFirst())
        break;
      i = 1;
      break;
      label140: String str2 = localCursor.getString(localCursor.getColumnIndex(this.result_columns[k]));
      if (str2.equals("(ללא)"))
        str2 = "";
      this.result_data[k] = str2;
      k++;
      break label70;
      label192: for (int j = 0; j < this.result_data.length; j++)
        this.result_data[j] = "";
    }
  }

  public String getField(String paramString)
  {
    String str = "not found";
    int i;
    int j;
    if (this.dataAvailable)
    {
      i = 0;
      j = 0;
    }
    while (true)
    {
      if (i != 0)
      {
        str = this.result_data[j];
        return str;
      }
      if (this.result_columns[j].equals(paramString))
      {
        i = 1;
        continue;
      }
      j++;
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
            this.db.insert("datatora", null, localContentValues);
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

  public String getQueryDate()
  {
    return this.queryDate;
  }

  public void nextQueryDate()
  {
    Calendar localCalendar = calendarFromString(this.queryDate);
    localCalendar.roll(6, 1);
    this.queryDate = stringFromCalendar(localCalendar);
    this.dataAvailable = getData(this.queryDate);
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
        localCalendar2.roll(6, -15);
        if (localCalendar2.compareTo(localCalendar1) != 1)
          continue;
        i = 1;
        continue;
      }
      if (j != -1)
        continue;
      localCalendar2.roll(6, 8);
      if (localCalendar2.compareTo(localCalendar1) != -1)
        continue;
      i = 1;
    }
  }

  public void prevQueryDate()
  {
    Calendar localCalendar = calendarFromString(this.queryDate);
    localCalendar.roll(6, -1);
    this.queryDate = stringFromCalendar(localCalendar);
    this.dataAvailable = getData(this.queryDate);
  }

  public void resetQueryDate()
  {
    this.queryDate = this.currentDate;
    this.dataAvailable = getData(this.queryDate);
  }

  public void saveDatabase()
  {
    for (int i = -7; ; i++)
    {
      if (i > 14)
        return;
      Calendar localCalendar = calendarFromString(this.currentDate);
      localCalendar.roll(6, i);
      String str = stringFromCalendar(localCalendar);
      if (cursorExist(str))
        continue;
      getQuery(str, "open");
    }
  }

  public void setQueryDate(String paramString)
  {
    this.queryDate = paramString;
    this.dataAvailable = getData(this.queryDate);
  }

  public String stringFromCalendar(Calendar paramCalendar)
  {
    return Integer.toString(paramCalendar.get(1)) + '-' + pad(1 + paramCalendar.get(2)) + '-' + pad(paramCalendar.get(5));
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.eladrich.dakatora.SqlDataClass
 * JD-Core Version:    0.6.0
 */