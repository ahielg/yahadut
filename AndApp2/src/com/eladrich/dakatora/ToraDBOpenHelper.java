package com.eladrich.dakatora;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ToraDBOpenHelper extends SQLiteOpenHelper
{
  private static final String DATABASE_CREATE = "create table datatora(_id integer primary key autoincrement, KEY_UNIQUE_DATE text not null, KEY_PARASHA text not null, KEY_HOLIDAY text not null, KEY_REMINDER text not null, KEY_TITLE_DAY text not null, KEY_TITLE_1 text not null, KEY_CONTENT_1 text not null, KEY_SOURCE_1 text not null, KEY_TITLE_2 text not null, KEY_CONTENT_2 text not null, KEY_SOURCE_2 text not null, KEY_TITLE_3 text not null, KEY_CONTENT_3 text not null, KEY_SOURCE_3 text not null);";
  private static final String DATABASE_NAME = "toraDatabase.db";
  public static final String DATABASE_TABLE = "datatora";
  private static final int DATABASE_VERSION = 1;
  public static final String KEY_CONTENT_1 = "KEY_CONTENT_1";
  public static final String KEY_CONTENT_2 = "KEY_CONTENT_2";
  public static final String KEY_CONTENT_3 = "KEY_CONTENT_3";
  public static final String KEY_HOLIDAY = "KEY_HOLIDAY";
  public static final String KEY_ID = "_id";
  public static final String KEY_PARASHA = "KEY_PARASHA";
  public static final String KEY_REMINDER = "KEY_REMINDER";
  public static final String KEY_SOURCE_1 = "KEY_SOURCE_1";
  public static final String KEY_SOURCE_2 = "KEY_SOURCE_2";
  public static final String KEY_SOURCE_3 = "KEY_SOURCE_3";
  public static final String KEY_TITLE_1 = "KEY_TITLE_1";
  public static final String KEY_TITLE_2 = "KEY_TITLE_2";
  public static final String KEY_TITLE_3 = "KEY_TITLE_3";
  public static final String KEY_TITLE_DAY = "KEY_TITLE_DAY";
  public static final String KEY_UNIQUE_DATE = "KEY_UNIQUE_DATE";

  public ToraDBOpenHelper(Context paramContext)
  {
    super(paramContext, "toraDatabase.db", null, 1);
  }

  public void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    paramSQLiteDatabase.execSQL("create table datatora(_id integer primary key autoincrement, KEY_UNIQUE_DATE text not null, KEY_PARASHA text not null, KEY_HOLIDAY text not null, KEY_REMINDER text not null, KEY_TITLE_DAY text not null, KEY_TITLE_1 text not null, KEY_CONTENT_1 text not null, KEY_SOURCE_1 text not null, KEY_TITLE_2 text not null, KEY_CONTENT_2 text not null, KEY_SOURCE_2 text not null, KEY_TITLE_3 text not null, KEY_CONTENT_3 text not null, KEY_SOURCE_3 text not null);");
  }

  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    paramSQLiteDatabase.execSQL("DROP TABLE IF IT EXISTS datatora");
    onCreate(paramSQLiteDatabase);
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.eladrich.dakatora.ToraDBOpenHelper
 * JD-Core Version:    0.6.0
 */