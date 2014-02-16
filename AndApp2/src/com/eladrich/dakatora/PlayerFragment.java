package com.eladrich.dakatora;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaScannerConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import com.actionbarsherlock.app.SherlockFragment;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

public class PlayerFragment extends SherlockFragment
{
  static final String PREF_LAST_LISTEN = "daka_prefs_listen";
  static final String PREF_NAME = "daka_prefs";
  public final String ADVERT_COUNT = "advert";
  public final String DATA_BY_DATE = "getByDate";
  public final String DATA_BY_FILE = "getByFile";
  final int DATA_LENGTH = 4;
  public final String DOWNLOAD_COUNT = "download";
  public final String IGNORE_COUNT = "reopen";
  public final String LISTEN_COUNT = "listen";
  final int NOTIF_ID = 2;
  public final String OPEN_COUNT = "open";
  public final String PHONE_TYPE = "android";
  public final String UNIQUE_COUNT = "unique";
  public final String VERIFY_CODE = "#fgD!F@Gh";
  boolean activityIsVisible;
  LinearLayout buttonsLayout;
  ImageView clockIV;
  String currentDate = "";
  String[] dataString;
  boolean downloadFinished;
  boolean fileFromIntent;
  String fileNameLink;
  ImageView iconIV;
  LinearLayout infoLayout;
  MediaController mediaController;
  MediaPlayer mediaPlayer;
  boolean mpReady;
  boolean playWhenReady;
  ImageButton playerButton;
  LinearLayout playerLayout;
  TextView playerSeperator;
  Thread playerThread;
  Timer playerTimer;
  ImageView ravIV;
  TextView ravTV;
  String reminder;
  ImageButton saveButton;
  Runnable saveFileFailed = new Runnable()
  {
    public void run()
    {
      PlayerFragment.this.stopProgressAnimation();
      if (PlayerFragment.this.getActivity() != null)
        Toast.makeText(PlayerFragment.this.getActivity().getBaseContext(), "שמירת השיעור נכשלה", 0).show();
    }
  };
  Runnable saveFileThreadFinish = new Runnable()
  {
    public void run()
    {
      PlayerFragment.this.stopProgressAnimation();
      if (PlayerFragment.this.getActivity() != null)
        Toast.makeText(PlayerFragment.this.getActivity().getBaseContext(), "השיעור נשמר", 0).show();
    }
  };
  private SharedPreferences settings;
  ImageButton shareButton;
  ImageView shareIV;
  TextView shiurTV;
  ImageView sponsorIV;
  TextView timeTV;
  TextView titleTV;
  Runnable updateThread = new Runnable()
  {
    public void run()
    {
      PlayerFragment.this.updatePlayer();
    }
  };
  boolean viewsRefenced;

  private static String pad(int paramInt)
  {
    if (paramInt >= 10);
    for (String str = String.valueOf(paramInt); ; str = "0" + String.valueOf(paramInt))
      return str;
  }

  public void downloadFile(String paramString)
  {
    downloadRavAsyncTask localdownloadRavAsyncTask = new downloadRavAsyncTask(null);
    String[] arrayOfString = new String[1];
    arrayOfString[0] = paramString;
    localdownloadRavAsyncTask.execute(arrayOfString);
  }

  public void finishPlay()
  {
    getActivity().getWindow().clearFlags(128);
    this.playerButton.setImageResource(2130837686);
    this.clockIV.setImageResource(2130837656);
    this.infoLayout.setVisibility(8);
    this.ravIV.setVisibility(8);
    this.ravTV.setVisibility(8);
    this.shiurTV.setVisibility(8);
    this.shareButton.setVisibility(0);
    this.saveButton.setVisibility(0);
    this.buttonsLayout.setVisibility(0);
    this.titleTV.setText(getString(2131427431));
    if (!this.currentDate.equals(""))
    {
      updateCountThread("listen");
      if (!this.fileFromIntent)
      {
        String str = this.settings.getString("daka_prefs_listen", "");
        if (!this.currentDate.equals(str))
        {
          updateCountThread("unique");
          SharedPreferences.Editor localEditor = this.settings.edit();
          localEditor.putString("daka_prefs_listen", this.currentDate);
          localEditor.commit();
        }
      }
    }
  }

  public void getDataByFileThread(String paramString1, String paramString2)
  {
    getDataAsyncTask localgetDataAsyncTask = new getDataAsyncTask(null);
    String[] arrayOfString = new String[3];
    arrayOfString[0] = "getByFile";
    arrayOfString[1] = paramString1;
    arrayOfString[2] = paramString2;
    localgetDataAsyncTask.execute(arrayOfString);
  }

  public void getDataThread(String paramString)
  {
    getDataAsyncTask localgetDataAsyncTask = new getDataAsyncTask(null);
    String[] arrayOfString = new String[2];
    arrayOfString[0] = "getByDate";
    arrayOfString[1] = paramString;
    localgetDataAsyncTask.execute(arrayOfString);
  }

  // ERROR //
  public boolean getQuery(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: new 312	org/apache/http/impl/client/DefaultHttpClient
    //   3: dup
    //   4: invokespecial 313	org/apache/http/impl/client/DefaultHttpClient:<init>	()V
    //   7: new 315	org/apache/http/client/methods/HttpPost
    //   10: dup
    //   11: new 179	java/lang/StringBuilder
    //   14: dup
    //   15: ldc_w 317
    //   18: invokespecial 184	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   21: aload_1
    //   22: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   25: ldc_w 319
    //   28: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   31: aload_2
    //   32: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   35: ldc_w 321
    //   38: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   41: ldc 142
    //   43: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   46: ldc_w 323
    //   49: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   52: ldc 146
    //   54: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   57: invokevirtual 192	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   60: invokespecial 324	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
    //   63: invokeinterface 329 2 0
    //   68: invokeinterface 335 1 0
    //   73: invokeinterface 341 1 0
    //   78: astore 5
    //   80: new 343	java/io/InputStreamReader
    //   83: dup
    //   84: aload 5
    //   86: ldc_w 345
    //   89: invokespecial 348	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   92: astore 6
    //   94: new 350	java/io/BufferedReader
    //   97: dup
    //   98: aload 6
    //   100: bipush 8
    //   102: invokespecial 353	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   105: astore 7
    //   107: new 179	java/lang/StringBuilder
    //   110: dup
    //   111: invokespecial 354	java/lang/StringBuilder:<init>	()V
    //   114: astore 8
    //   116: aload 7
    //   118: invokevirtual 357	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   121: astore 10
    //   123: aload 10
    //   125: ifnonnull +118 -> 243
    //   128: aload 5
    //   130: invokevirtual 362	java/io/InputStream:close	()V
    //   133: aload 8
    //   135: invokevirtual 192	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   138: astore 12
    //   140: aload 8
    //   142: iconst_0
    //   143: iconst_1
    //   144: aload 12
    //   146: bipush 93
    //   148: invokevirtual 366	java/lang/String:indexOf	(I)I
    //   151: iadd
    //   152: invokevirtual 370	java/lang/StringBuilder:substring	(II)Ljava/lang/String;
    //   155: astore 13
    //   157: aload 8
    //   159: iconst_1
    //   160: aload 12
    //   162: bipush 93
    //   164: invokevirtual 366	java/lang/String:indexOf	(I)I
    //   167: iadd
    //   168: aload 8
    //   170: invokevirtual 374	java/lang/StringBuilder:length	()I
    //   173: invokevirtual 370	java/lang/StringBuilder:substring	(II)Ljava/lang/String;
    //   176: astore 14
    //   178: new 376	org/json/JSONArray
    //   181: dup
    //   182: aload 13
    //   184: invokespecial 377	org/json/JSONArray:<init>	(Ljava/lang/String;)V
    //   187: astore 15
    //   189: new 376	org/json/JSONArray
    //   192: dup
    //   193: aload 14
    //   195: invokespecial 377	org/json/JSONArray:<init>	(Ljava/lang/String;)V
    //   198: astore 16
    //   200: iconst_0
    //   201: istore 17
    //   203: iload 17
    //   205: aload 15
    //   207: invokevirtual 378	org/json/JSONArray:length	()I
    //   210: if_icmplt +71 -> 281
    //   213: iconst_0
    //   214: istore 24
    //   216: aload 16
    //   218: invokevirtual 378	org/json/JSONArray:length	()I
    //   221: istore 25
    //   223: iload 24
    //   225: iload 25
    //   227: if_icmplt +166 -> 393
    //   230: iconst_1
    //   231: istore 4
    //   233: iload 4
    //   235: ireturn
    //   236: astore_3
    //   237: iconst_0
    //   238: istore 4
    //   240: goto -7 -> 233
    //   243: aload 8
    //   245: new 179	java/lang/StringBuilder
    //   248: dup
    //   249: aload 10
    //   251: invokestatic 381	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   254: invokespecial 184	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   257: ldc_w 383
    //   260: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   263: invokevirtual 192	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   266: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   269: pop
    //   270: goto -154 -> 116
    //   273: astore 9
    //   275: iconst_0
    //   276: istore 4
    //   278: goto -45 -> 233
    //   281: aload 15
    //   283: iload 17
    //   285: invokevirtual 387	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   288: astore 19
    //   290: aload_0
    //   291: getfield 389	com/eladrich/dakatora/PlayerFragment:dataString	[Ljava/lang/String;
    //   294: iconst_1
    //   295: new 179	java/lang/StringBuilder
    //   298: dup
    //   299: ldc_w 391
    //   302: invokespecial 184	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   305: aload 19
    //   307: ldc_w 393
    //   310: invokevirtual 398	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   313: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   316: invokevirtual 192	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   319: aastore
    //   320: aload_0
    //   321: getfield 389	com/eladrich/dakatora/PlayerFragment:dataString	[Ljava/lang/String;
    //   324: iconst_1
    //   325: aload_0
    //   326: getfield 389	com/eladrich/dakatora/PlayerFragment:dataString	[Ljava/lang/String;
    //   329: iconst_1
    //   330: aaload
    //   331: ldc_w 400
    //   334: ldc_w 402
    //   337: invokevirtual 405	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   340: aastore
    //   341: aload 19
    //   343: ldc_w 407
    //   346: invokevirtual 398	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   349: astore 20
    //   351: aload 20
    //   353: astore 21
    //   355: new 174	java/lang/String
    //   358: dup
    //   359: aload 21
    //   361: ldc_w 345
    //   364: invokevirtual 411	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   367: ldc_w 413
    //   370: invokespecial 416	java/lang/String:<init>	([BLjava/lang/String;)V
    //   373: astore 22
    //   375: aload 22
    //   377: astore 21
    //   379: aload_0
    //   380: getfield 389	com/eladrich/dakatora/PlayerFragment:dataString	[Ljava/lang/String;
    //   383: iconst_3
    //   384: aload 21
    //   386: aastore
    //   387: iinc 17 1
    //   390: goto -187 -> 203
    //   393: aload 16
    //   395: iload 24
    //   397: invokevirtual 387	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   400: astore 26
    //   402: aload 26
    //   404: ldc_w 418
    //   407: invokevirtual 398	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   410: astore 27
    //   412: aload 27
    //   414: ldc 154
    //   416: invokevirtual 269	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   419: ifne +109 -> 528
    //   422: aload_0
    //   423: getfield 389	com/eladrich/dakatora/PlayerFragment:dataString	[Ljava/lang/String;
    //   426: iconst_0
    //   427: new 179	java/lang/StringBuilder
    //   430: dup
    //   431: ldc_w 420
    //   434: invokespecial 184	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   437: aload 27
    //   439: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   442: invokevirtual 192	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   445: aastore
    //   446: new 179	java/lang/StringBuilder
    //   449: dup
    //   450: aload 26
    //   452: ldc_w 422
    //   455: invokevirtual 398	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   458: invokestatic 381	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   461: invokespecial 184	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   464: ldc_w 400
    //   467: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   470: aload 26
    //   472: ldc_w 424
    //   475: invokevirtual 398	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   478: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   481: invokevirtual 192	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   484: astore 28
    //   486: aload 28
    //   488: astore 29
    //   490: new 174	java/lang/String
    //   493: dup
    //   494: aload 29
    //   496: ldc_w 345
    //   499: invokevirtual 411	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   502: ldc_w 413
    //   505: invokespecial 416	java/lang/String:<init>	([BLjava/lang/String;)V
    //   508: astore 30
    //   510: aload 30
    //   512: astore 29
    //   514: aload_0
    //   515: getfield 389	com/eladrich/dakatora/PlayerFragment:dataString	[Ljava/lang/String;
    //   518: iconst_2
    //   519: aload 29
    //   521: aastore
    //   522: iinc 24 1
    //   525: goto -309 -> 216
    //   528: aload_0
    //   529: getfield 389	com/eladrich/dakatora/PlayerFragment:dataString	[Ljava/lang/String;
    //   532: iconst_0
    //   533: ldc_w 426
    //   536: aastore
    //   537: goto -91 -> 446
    //   540: astore 18
    //   542: iconst_0
    //   543: istore 4
    //   545: goto -312 -> 233
    //   548: astore 31
    //   550: goto -36 -> 514
    //   553: astore 23
    //   555: goto -176 -> 379
    //
    // Exception table:
    //   from	to	target	type
    //   0	80	236	java/lang/Exception
    //   80	178	273	java/lang/Exception
    //   243	270	273	java/lang/Exception
    //   178	223	540	org/json/JSONException
    //   281	351	540	org/json/JSONException
    //   355	375	540	org/json/JSONException
    //   379	486	540	org/json/JSONException
    //   490	510	540	org/json/JSONException
    //   514	537	540	org/json/JSONException
    //   490	510	548	java/io/UnsupportedEncodingException
    //   355	375	553	java/io/UnsupportedEncodingException
  }

  // ERROR //
  public boolean getQueryByFile(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_1
    //   1: ldc_w 400
    //   4: ldc_w 402
    //   7: invokevirtual 405	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   10: astore_3
    //   11: new 312	org/apache/http/impl/client/DefaultHttpClient
    //   14: dup
    //   15: invokespecial 313	org/apache/http/impl/client/DefaultHttpClient:<init>	()V
    //   18: new 315	org/apache/http/client/methods/HttpPost
    //   21: dup
    //   22: new 179	java/lang/StringBuilder
    //   25: dup
    //   26: ldc_w 429
    //   29: invokespecial 184	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   32: aload_3
    //   33: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   36: ldc_w 319
    //   39: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   42: aload_2
    //   43: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   46: invokevirtual 192	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   49: invokespecial 324	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
    //   52: invokeinterface 329 2 0
    //   57: invokeinterface 335 1 0
    //   62: invokeinterface 341 1 0
    //   67: astore 6
    //   69: new 343	java/io/InputStreamReader
    //   72: dup
    //   73: aload 6
    //   75: ldc_w 345
    //   78: invokespecial 348	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   81: astore 7
    //   83: new 350	java/io/BufferedReader
    //   86: dup
    //   87: aload 7
    //   89: bipush 8
    //   91: invokespecial 353	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   94: astore 8
    //   96: new 179	java/lang/StringBuilder
    //   99: dup
    //   100: invokespecial 354	java/lang/StringBuilder:<init>	()V
    //   103: astore 9
    //   105: aload 8
    //   107: invokevirtual 357	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   110: astore 11
    //   112: aload 11
    //   114: ifnonnull +119 -> 233
    //   117: aload 6
    //   119: invokevirtual 362	java/io/InputStream:close	()V
    //   122: aload 9
    //   124: invokevirtual 192	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   127: astore 13
    //   129: aload 9
    //   131: iconst_0
    //   132: iconst_1
    //   133: aload 13
    //   135: bipush 93
    //   137: invokevirtual 366	java/lang/String:indexOf	(I)I
    //   140: iadd
    //   141: invokevirtual 370	java/lang/StringBuilder:substring	(II)Ljava/lang/String;
    //   144: astore 14
    //   146: aload 9
    //   148: iconst_1
    //   149: aload 13
    //   151: bipush 93
    //   153: invokevirtual 366	java/lang/String:indexOf	(I)I
    //   156: iadd
    //   157: aload 9
    //   159: invokevirtual 374	java/lang/StringBuilder:length	()I
    //   162: invokevirtual 370	java/lang/StringBuilder:substring	(II)Ljava/lang/String;
    //   165: astore 15
    //   167: new 376	org/json/JSONArray
    //   170: dup
    //   171: aload 14
    //   173: invokespecial 377	org/json/JSONArray:<init>	(Ljava/lang/String;)V
    //   176: astore 16
    //   178: new 376	org/json/JSONArray
    //   181: dup
    //   182: aload 15
    //   184: invokespecial 377	org/json/JSONArray:<init>	(Ljava/lang/String;)V
    //   187: astore 17
    //   189: iconst_0
    //   190: istore 18
    //   192: iload 18
    //   194: aload 16
    //   196: invokevirtual 378	org/json/JSONArray:length	()I
    //   199: if_icmplt +72 -> 271
    //   202: iconst_0
    //   203: istore 25
    //   205: aload 17
    //   207: invokevirtual 378	org/json/JSONArray:length	()I
    //   210: istore 26
    //   212: iload 25
    //   214: iload 26
    //   216: if_icmplt +179 -> 395
    //   219: iconst_1
    //   220: istore 5
    //   222: iload 5
    //   224: ireturn
    //   225: astore 4
    //   227: iconst_0
    //   228: istore 5
    //   230: goto -8 -> 222
    //   233: aload 9
    //   235: new 179	java/lang/StringBuilder
    //   238: dup
    //   239: aload 11
    //   241: invokestatic 381	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   244: invokespecial 184	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   247: ldc_w 383
    //   250: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   253: invokevirtual 192	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   256: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   259: pop
    //   260: goto -155 -> 105
    //   263: astore 10
    //   265: iconst_0
    //   266: istore 5
    //   268: goto -46 -> 222
    //   271: aload 16
    //   273: iload 18
    //   275: invokevirtual 387	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   278: astore 20
    //   280: aload_0
    //   281: getfield 389	com/eladrich/dakatora/PlayerFragment:dataString	[Ljava/lang/String;
    //   284: iconst_1
    //   285: new 179	java/lang/StringBuilder
    //   288: dup
    //   289: ldc_w 391
    //   292: invokespecial 184	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   295: aload 20
    //   297: ldc_w 393
    //   300: invokevirtual 398	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   303: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   306: invokevirtual 192	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   309: aastore
    //   310: aload_0
    //   311: getfield 389	com/eladrich/dakatora/PlayerFragment:dataString	[Ljava/lang/String;
    //   314: iconst_1
    //   315: aload_0
    //   316: getfield 389	com/eladrich/dakatora/PlayerFragment:dataString	[Ljava/lang/String;
    //   319: iconst_1
    //   320: aaload
    //   321: ldc_w 400
    //   324: ldc_w 402
    //   327: invokevirtual 405	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   330: aastore
    //   331: aload 20
    //   333: ldc_w 407
    //   336: invokevirtual 398	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   339: astore 21
    //   341: aload 21
    //   343: astore 22
    //   345: new 174	java/lang/String
    //   348: dup
    //   349: aload 22
    //   351: ldc_w 345
    //   354: invokevirtual 411	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   357: ldc_w 413
    //   360: invokespecial 416	java/lang/String:<init>	([BLjava/lang/String;)V
    //   363: astore 23
    //   365: aload 23
    //   367: astore 22
    //   369: aload_0
    //   370: getfield 389	com/eladrich/dakatora/PlayerFragment:dataString	[Ljava/lang/String;
    //   373: iconst_3
    //   374: aload 22
    //   376: aastore
    //   377: aload_0
    //   378: aload 20
    //   380: ldc_w 431
    //   383: invokevirtual 398	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   386: putfield 156	com/eladrich/dakatora/PlayerFragment:currentDate	Ljava/lang/String;
    //   389: iinc 18 1
    //   392: goto -200 -> 192
    //   395: aload 17
    //   397: iload 25
    //   399: invokevirtual 387	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   402: astore 27
    //   404: aload 27
    //   406: ldc_w 418
    //   409: invokevirtual 398	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   412: astore 28
    //   414: aload 28
    //   416: ldc 154
    //   418: invokevirtual 269	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   421: ifne +109 -> 530
    //   424: aload_0
    //   425: getfield 389	com/eladrich/dakatora/PlayerFragment:dataString	[Ljava/lang/String;
    //   428: iconst_0
    //   429: new 179	java/lang/StringBuilder
    //   432: dup
    //   433: ldc_w 420
    //   436: invokespecial 184	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   439: aload 28
    //   441: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   444: invokevirtual 192	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   447: aastore
    //   448: new 179	java/lang/StringBuilder
    //   451: dup
    //   452: aload 27
    //   454: ldc_w 422
    //   457: invokevirtual 398	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   460: invokestatic 381	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   463: invokespecial 184	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   466: ldc_w 400
    //   469: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   472: aload 27
    //   474: ldc_w 424
    //   477: invokevirtual 398	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   480: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   483: invokevirtual 192	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   486: astore 29
    //   488: aload 29
    //   490: astore 30
    //   492: new 174	java/lang/String
    //   495: dup
    //   496: aload 30
    //   498: ldc_w 345
    //   501: invokevirtual 411	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   504: ldc_w 413
    //   507: invokespecial 416	java/lang/String:<init>	([BLjava/lang/String;)V
    //   510: astore 31
    //   512: aload 31
    //   514: astore 30
    //   516: aload_0
    //   517: getfield 389	com/eladrich/dakatora/PlayerFragment:dataString	[Ljava/lang/String;
    //   520: iconst_2
    //   521: aload 30
    //   523: aastore
    //   524: iinc 25 1
    //   527: goto -322 -> 205
    //   530: aload_0
    //   531: getfield 389	com/eladrich/dakatora/PlayerFragment:dataString	[Ljava/lang/String;
    //   534: iconst_0
    //   535: ldc_w 426
    //   538: aastore
    //   539: goto -91 -> 448
    //   542: astore 19
    //   544: iconst_0
    //   545: istore 5
    //   547: goto -325 -> 222
    //   550: astore 32
    //   552: goto -36 -> 516
    //   555: astore 24
    //   557: goto -188 -> 369
    //
    // Exception table:
    //   from	to	target	type
    //   11	69	225	java/lang/Exception
    //   69	167	263	java/lang/Exception
    //   233	260	263	java/lang/Exception
    //   167	212	542	org/json/JSONException
    //   271	341	542	org/json/JSONException
    //   345	365	542	org/json/JSONException
    //   369	488	542	org/json/JSONException
    //   492	512	542	org/json/JSONException
    //   516	539	542	org/json/JSONException
    //   492	512	550	java/io/UnsupportedEncodingException
    //   345	365	555	java/io/UnsupportedEncodingException
  }

  public void loadPlayer()
  {
    if (this.infoLayout != null)
      this.infoLayout.setVisibility(0);
    if (!this.dataString[0].equals(""))
    {
      downloadFile(this.dataString[0]);
      this.ravIV.setVisibility(0);
      if (this.dataString[2].equals("null"))
        break label214;
      this.ravTV.setText(this.dataString[2]);
      this.ravTV.setVisibility(0);
      label83: if (this.dataString[3].equals("null"))
        break label225;
      this.shiurTV.setText(this.dataString[3]);
      this.shiurTV.setVisibility(0);
      if (this.dataString[1].equals(""))
        break label250;
      this.mediaPlayer = new MediaPlayer();
      this.mediaPlayer.setAudioStreamType(3);
    }
    while (true)
    {
      try
      {
        this.mediaPlayer.setDataSource(this.dataString[1]);
        this.mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
        {
          public void onPrepared(MediaPlayer paramMediaPlayer)
          {
            PlayerFragment.this.mpReady = true;
            if (PlayerFragment.this.viewsRefenced)
            {
              if (PlayerFragment.this.playerButton != null)
                PlayerFragment.this.playerButton.setClickable(true);
              if (PlayerFragment.this.titleTV != null)
                PlayerFragment.this.titleTV.setText(2131427428);
              if (PlayerFragment.this.playWhenReady)
              {
                PlayerFragment.this.ravIV.setVisibility(0);
                PlayerFragment.this.ravTV.setVisibility(0);
                PlayerFragment.this.shiurTV.setVisibility(0);
                PlayerFragment.this.infoLayout.setVisibility(0);
                PlayerFragment.this.shareButton.setVisibility(8);
                PlayerFragment.this.saveButton.setVisibility(8);
                PlayerFragment.this.buttonsLayout.setVisibility(8);
                PlayerFragment.this.playerButton.setImageResource(2130837681);
                PlayerFragment.this.titleTV.setText(PlayerFragment.this.getString(2131427429));
                PlayerFragment.this.getActivity().getWindow().addFlags(128);
                PlayerFragment.this.mediaPlayer.start();
                PlayerFragment.this.startPlayerThread();
              }
            }
          }
        });
        this.mediaPlayer.prepareAsync();
        this.mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
          public void onCompletion(MediaPlayer paramMediaPlayer)
          {
            PlayerFragment.this.finishPlay();
          }
        });
        return;
        this.ravIV.setVisibility(4);
        break;
        label214: this.ravTV.setVisibility(4);
        break label83;
        label225: this.shiurTV.setVisibility(4);
      }
      catch (Exception localException)
      {
        this.titleTV.setText(2131427427);
        continue;
      }
      label250: if (this.titleTV == null)
        continue;
      this.titleTV.setText(2131427427);
    }
  }

  public void musicClick()
  {
    if (this.mediaPlayer != null)
    {
      if (!this.mediaPlayer.isPlaying())
      {
        this.ravIV.setVisibility(0);
        this.ravTV.setVisibility(0);
        this.shiurTV.setVisibility(0);
        this.infoLayout.setVisibility(0);
        this.shareButton.setVisibility(8);
        this.saveButton.setVisibility(8);
        this.buttonsLayout.setVisibility(8);
        this.playerButton.setImageResource(2130837681);
        this.titleTV.setText(getString(2131427429));
        getActivity().getWindow().addFlags(128);
        this.mediaPlayer.start();
        startPlayerThread();
      }
      while (true)
      {
        return;
        this.playerButton.setImageResource(2130837682);
        this.titleTV.setText(getString(2131427430));
        getActivity().getWindow().clearFlags(128);
        try
        {
          this.mediaPlayer.pause();
        }
        catch (IllegalStateException localIllegalStateException)
        {
        }
      }
    }
    this.dataString = new String[4];
    for (int i = 0; ; i++)
    {
      if (i >= this.dataString.length)
      {
        this.playerButton.setClickable(false);
        this.playerButton.setImageResource(2130837682);
        this.clockIV.setImageResource(2130837649);
        this.titleTV.setText(2131427426);
        this.titleTV.setVisibility(0);
        this.timeTV.setText(2131427432);
        getDataThread("reopen");
        break;
      }
      this.dataString[i] = "";
    }
  }

  public void onActivityCreated(Bundle paramBundle)
  {
    this.settings = getActivity().getSharedPreferences("daka_prefs", 0);
    this.dataString = new String[4];
    for (int i = 0; ; i++)
    {
      if (i >= this.dataString.length)
      {
        this.playerButton.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramView)
          {
            PlayerFragment.this.musicClick();
          }
        });
        this.shareButton.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramView)
          {
            Intent localIntent = new Intent("android.intent.action.SEND");
            localIntent.setType("text/plain");
            localIntent.putExtra("android.intent.extra.SUBJECT", "דקה תורה - לימוד יומי");
            localIntent.putExtra("android.intent.extra.TEXT", "האזן לשיעור של " + PlayerFragment.this.dataString[2] + " (" + PlayerFragment.this.dataString[3] + ") \n" + PlayerFragment.this.dataString[1] + "\n \n" + "dakatora.co.il");
            PlayerFragment.this.startActivity(Intent.createChooser(localIntent, "שתף באמצעות"));
          }
        });
        this.saveButton.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramView)
          {
            PlayerFragment.this.updateCountThread("download");
            PlayerFragment.this.downloadFinished = false;
            PlayerFragment.this.startProgressAnimation();
            PlayerFragment.downloadFileAsyncTask localdownloadFileAsyncTask = new PlayerFragment.downloadFileAsyncTask(PlayerFragment.this, null);
            String[] arrayOfString = new String[3];
            arrayOfString[0] = PlayerFragment.this.dataString[1];
            arrayOfString[1] = PlayerFragment.this.dataString[2];
            arrayOfString[2] = PlayerFragment.this.dataString[3];
            localdownloadFileAsyncTask.execute(arrayOfString);
          }
        });
        super.onActivityCreated(paramBundle);
        return;
      }
      this.dataString[i] = "";
    }
  }

  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
  }

  public void onCreate(Bundle paramBundle)
  {
    setHasOptionsMenu(true);
    super.onCreate(paramBundle);
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    View localView = paramLayoutInflater.inflate(2130903077, paramViewGroup, false);
    this.playerButton = ((ImageButton)localView.findViewById(2131099757));
    this.timeTV = ((TextView)localView.findViewById(2131099755));
    this.titleTV = ((TextView)localView.findViewById(2131099746));
    this.ravTV = ((TextView)localView.findViewById(2131099752));
    this.shiurTV = ((TextView)localView.findViewById(2131099753));
    this.ravIV = ((ImageView)localView.findViewById(2131099750));
    this.clockIV = ((ImageView)localView.findViewById(2131099756));
    this.playerLayout = ((LinearLayout)localView.findViewById(2131099745));
    this.saveButton = ((ImageButton)localView.findViewById(2131099749));
    this.shareButton = ((ImageButton)localView.findViewById(2131099748));
    this.buttonsLayout = ((LinearLayout)localView.findViewById(2131099747));
    this.infoLayout = ((LinearLayout)localView.findViewById(2131099751));
    this.viewsRefenced = true;
    return localView;
  }

  public void onDestroy()
  {
    super.onDestroy();
  }

  public void onDestroyView()
  {
    this.playerButton = null;
    this.timeTV = null;
    this.titleTV = null;
    this.ravTV = null;
    this.shiurTV = null;
    this.ravIV = null;
    this.clockIV = null;
    this.playerLayout = null;
    this.saveButton = null;
    this.shareButton = null;
    this.buttonsLayout = null;
    this.infoLayout = null;
    this.viewsRefenced = false;
    super.onDestroyView();
  }

  public void onDetach()
  {
    super.onDetach();
  }

  public void onPause()
  {
    super.onPause();
  }

  public void onResume()
  {
    super.onResume();
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
  }

  public void onStart()
  {
    this.mpReady = false;
    this.ravIV.setVisibility(4);
    this.infoLayout.setVisibility(4);
    this.playerButton.setClickable(false);
    this.playerButton.setImageResource(2130837682);
    this.clockIV.setImageResource(2130837649);
    this.titleTV.setText(2131427426);
    this.titleTV.setVisibility(0);
    this.timeTV.setText(2131427432);
    this.shareButton.setVisibility(8);
    this.saveButton.setVisibility(8);
    this.buttonsLayout.setVisibility(8);
    if (this.fileFromIntent)
    {
      this.playWhenReady = true;
      getDataByFileThread("open", this.fileNameLink);
    }
    while (true)
    {
      super.onStart();
      return;
      getDataThread("open");
    }
  }

  public void onStop()
  {
    if ((this.mediaPlayer == null) || (this.mpReady));
    try
    {
      this.mediaPlayer.stop();
      getActivity().getWindow().clearFlags(128);
      while (true)
      {
        label34: this.mediaPlayer.release();
        this.mediaPlayer = null;
        stopProgressAnimation();
        super.onStop();
        return;
        this.mediaPlayer.reset();
      }
    }
    catch (IllegalStateException localIllegalStateException)
    {
      break label34;
    }
  }

  public void saveFileThread()
  {
    new Thread(new Runnable()
    {
      Handler handler = new Handler();

      public void run()
      {
        if (!PlayerFragment.this.dataString[1].equals(null))
        {
          String str1 = PlayerFragment.this.dataString[1];
          while (true)
          {
            FileOutputStream localFileOutputStream2;
            int j;
            try
            {
              File localFile1 = new File(Environment.getExternalStorageDirectory().getPath() + "/Daka Tora");
              if (PlayerFragment.this.dataString[3].equals(null))
                continue;
              String str2 = PlayerFragment.this.dataString[3] + ".mp3";
              File localFile2 = new File(localFile1, str2);
              localFile1.mkdirs();
              HttpURLConnection localHttpURLConnection = (HttpURLConnection)new URL(str1).openConnection();
              localHttpURLConnection.setDoInput(true);
              localHttpURLConnection.connect();
              InputStream localInputStream1 = localHttpURLConnection.getInputStream();
              FileOutputStream localFileOutputStream1 = new FileOutputStream(localFile2);
              int i = localInputStream1.read();
              if (i != -1)
                continue;
              localInputStream1.close();
              localFileOutputStream1.close();
              File localFile3 = new File(localFile1, "folder.jpg");
              if (localFile3.exists())
                continue;
              InputStream localInputStream2 = PlayerFragment.this.getResources().openRawResource(2130837619);
              localFileOutputStream2 = new FileOutputStream(localFile3);
              j = localInputStream2.read();
              if (j == -1)
              {
                localInputStream2.close();
                localFileOutputStream2.close();
                Context localContext = PlayerFragment.this.getActivity().getBaseContext();
                String[] arrayOfString = new String[1];
                arrayOfString[0] = localFile2.toString();
                MediaScannerConnection.scanFile(localContext, arrayOfString, null, new PlayerFragment.11.1(this));
                this.handler.post(PlayerFragment.this.saveFileThreadFinish);
                break;
                str2 = str1.substring(1 + str1.lastIndexOf('/'), str1.length()).replace("%20", " ");
                continue;
                localFileOutputStream1.write(i);
                continue;
              }
            }
            catch (Exception localException)
            {
              this.handler.post(PlayerFragment.this.saveFileFailed);
            }
            localFileOutputStream2.write(j);
          }
        }
        this.handler.post(PlayerFragment.this.saveFileFailed);
      }
    }).start();
  }

  public void setFileFromIntent(boolean paramBoolean)
  {
    this.fileFromIntent = paramBoolean;
  }

  public void setLink(String paramString)
  {
    this.fileNameLink = paramString;
  }

  public void startPlayerThread()
  {
    this.playerTimer = new Timer("timerPlayer");
    this.playerTimer.schedule(new TimerTask()
    {
      Handler handler = new Handler();

      public void run()
      {
        try
        {
          if ((PlayerFragment.this.mediaPlayer != null) && (PlayerFragment.this.mediaPlayer.isPlaying()))
            this.handler.post(PlayerFragment.this.updateThread);
          else
            PlayerFragment.this.playerTimer.cancel();
        }
        catch (IllegalStateException localIllegalStateException)
        {
          PlayerFragment.this.playerTimer.cancel();
        }
      }
    }
    , 0L, 1000L);
  }

  public void startProgressAnimation()
  {
    this.playerButton.setVisibility(4);
    this.timeTV.setVisibility(4);
    this.shareButton.setVisibility(8);
    this.saveButton.setVisibility(8);
    this.buttonsLayout.setVisibility(8);
    this.infoLayout.setVisibility(0);
    this.ravTV.setText("הורדת שיעור");
    this.shiurTV.setText("מתבצעת כעת");
    this.ravTV.setVisibility(0);
    this.shiurTV.setVisibility(0);
    this.clockIV.setImageResource(2130837610);
    startRotateAnimation();
  }

  public void startRotateAnimation()
  {
    Animation localAnimation = AnimationUtils.loadAnimation(getActivity().getBaseContext(), 2130968577);
    localAnimation.setAnimationListener(new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnimation)
      {
        if (!PlayerFragment.this.downloadFinished)
          PlayerFragment.this.startRotateAnimation();
        while (true)
        {
          return;
          PlayerFragment.this.stopProgressAnimation();
        }
      }

      public void onAnimationRepeat(Animation paramAnimation)
      {
      }

      public void onAnimationStart(Animation paramAnimation)
      {
      }
    });
    this.clockIV.startAnimation(localAnimation);
  }

  public void startSpecificFile(String[] paramArrayOfString)
  {
    this.playWhenReady = true;
    this.dataString = paramArrayOfString;
    if ((this.mediaPlayer == null) || (this.mpReady));
    try
    {
      this.mediaPlayer.stop();
      while (true)
      {
        label31: getActivity().getWindow().clearFlags(128);
        this.mediaPlayer.release();
        this.mediaPlayer = null;
        stopProgressAnimation();
        this.mpReady = false;
        this.ravIV.setVisibility(4);
        this.infoLayout.setVisibility(4);
        this.playerButton.setClickable(false);
        this.playerButton.setImageResource(2130837682);
        this.clockIV.setImageResource(2130837649);
        this.titleTV.setText(2131427426);
        this.titleTV.setVisibility(0);
        this.timeTV.setText(2131427432);
        this.shareButton.setVisibility(8);
        this.saveButton.setVisibility(8);
        this.buttonsLayout.setVisibility(8);
        loadPlayer();
        return;
        this.mediaPlayer.reset();
      }
    }
    catch (IllegalStateException localIllegalStateException)
    {
      break label31;
    }
  }

  public void stopProgressAnimation()
  {
    if (this.clockIV != null)
    {
      this.clockIV.clearAnimation();
      this.clockIV.setImageResource(2130837656);
    }
    if (this.playerButton != null)
      this.playerButton.setVisibility(0);
    if (this.timeTV != null)
      this.timeTV.setVisibility(0);
    if (this.ravTV != null)
    {
      this.ravTV.setVisibility(8);
      if (!this.dataString[2].equals("null"))
        this.ravTV.setText(this.dataString[2]);
    }
    if (this.shiurTV != null)
    {
      this.shiurTV.setVisibility(8);
      if (!this.dataString[3].equals("null"))
        this.shiurTV.setText(this.dataString[3]);
    }
    if (this.infoLayout != null)
      this.infoLayout.setVisibility(8);
    if (this.shareButton != null)
      this.shareButton.setVisibility(0);
    if (this.saveButton != null)
      this.saveButton.setVisibility(0);
    if (this.buttonsLayout != null)
      this.buttonsLayout.setVisibility(0);
  }

  public void stopSpecificFile()
  {
    this.playWhenReady = false;
    if ((this.mediaPlayer == null) || (this.mpReady));
    try
    {
      this.mediaPlayer.stop();
      label26: getActivity().getWindow().clearFlags(128);
      this.mediaPlayer.release();
      this.mediaPlayer = null;
      stopProgressAnimation();
      this.mpReady = false;
      this.ravIV.setVisibility(4);
      this.infoLayout.setVisibility(4);
      this.playerButton.setClickable(false);
      this.playerButton.setImageResource(2130837682);
      this.clockIV.setImageResource(2130837649);
      this.titleTV.setText(2131427426);
      this.titleTV.setVisibility(0);
      this.timeTV.setText(2131427432);
      this.shareButton.setVisibility(8);
      this.saveButton.setVisibility(8);
      this.buttonsLayout.setVisibility(8);
      if (this.fileFromIntent)
        getDataByFileThread("open", this.fileNameLink);
      while (true)
      {
        return;
        this.mediaPlayer.reset();
        break;
        getDataThread("open");
      }
    }
    catch (IllegalStateException localIllegalStateException)
    {
      break label26;
    }
  }

  public void updateCountThread(String paramString)
  {
    updateCountAsyncTask localupdateCountAsyncTask = new updateCountAsyncTask(null);
    String[] arrayOfString = new String[1];
    arrayOfString[0] = paramString;
    localupdateCountAsyncTask.execute(arrayOfString);
  }

  public void updatePlayer()
  {
    int i;
    int j;
    int k;
    if ((this.mediaPlayer != null) && (this.mediaPlayer.isPlaying()))
    {
      i = this.mediaPlayer.getCurrentPosition() / 1000;
      if (i >= 60)
        break label95;
      this.timeTV.setText("0:" + pad(i));
      j = this.mediaPlayer.getDuration() / 7;
      k = i * 1000;
      if (k > j)
        break label141;
      this.clockIV.setImageResource(2130837649);
    }
    while (true)
    {
      return;
      label95: this.timeTV.setText(pad(i / 60) + ":" + pad(i % 60));
      break;
      label141: if (k <= j * 2)
      {
        this.clockIV.setImageResource(2130837650);
        continue;
      }
      if (k <= j * 3)
      {
        this.clockIV.setImageResource(2130837651);
        continue;
      }
      if (k <= j * 4)
      {
        this.clockIV.setImageResource(2130837652);
        continue;
      }
      if (k <= j * 5)
      {
        this.clockIV.setImageResource(2130837653);
        continue;
      }
      if (k <= j * 6)
      {
        this.clockIV.setImageResource(2130837654);
        continue;
      }
      if (k > j * 7)
        continue;
      this.clockIV.setImageResource(2130837655);
    }
  }

  private class downloadFileAsyncTask extends AsyncTask<String, Void, Boolean>
  {
    private downloadFileAsyncTask()
    {
    }

    protected Boolean doInBackground(String[] paramArrayOfString)
    {
      String str1 = paramArrayOfString[0];
      Boolean localBoolean;
      try
      {
        File localFile1 = new File(Environment.getExternalStorageDirectory().getPath() + "/Daka Tora");
        String str2;
        File localFile2;
        InputStream localInputStream1;
        FileOutputStream localFileOutputStream1;
        byte[] arrayOfByte;
        if ((paramArrayOfString[1] != null) && (paramArrayOfString[2] != null))
        {
          str2 = paramArrayOfString[1] + " - " + paramArrayOfString[2] + ".mp3";
          localFile2 = new File(localFile1, str2);
          localFile1.mkdirs();
          HttpURLConnection localHttpURLConnection = (HttpURLConnection)new URL(str1).openConnection();
          localHttpURLConnection.setDoInput(true);
          localHttpURLConnection.connect();
          localInputStream1 = localHttpURLConnection.getInputStream();
          localFileOutputStream1 = new FileOutputStream(localFile2);
          arrayOfByte = new byte[1024];
        }
        while (true)
        {
          int i = localInputStream1.read(arrayOfByte);
          if (i == -1)
          {
            localInputStream1.close();
            localFileOutputStream1.flush();
            localFileOutputStream1.close();
            File localFile3 = new File(localFile1, "folder.jpg");
            if (!localFile3.exists())
            {
              InputStream localInputStream2 = PlayerFragment.this.getResources().openRawResource(2130837619);
              localFileOutputStream2 = new FileOutputStream(localFile3);
              j = localInputStream2.read();
              if (j != -1)
                break label355;
              localInputStream2.close();
              localFileOutputStream2.close();
            }
            Context localContext = PlayerFragment.this.getActivity().getApplicationContext();
            String[] arrayOfString = new String[1];
            arrayOfString[0] = localFile2.toString();
            MediaScannerConnection.scanFile(localContext, arrayOfString, null, new PlayerFragment.downloadFileAsyncTask.1(this));
            localBoolean = Boolean.valueOf(true);
            break label365;
            str2 = str1.substring(1 + str1.lastIndexOf('/'), str1.length()).replace("%20", " ");
            break;
          }
          localFileOutputStream1.write(arrayOfByte, 0, i);
        }
      }
      catch (Exception localException)
      {
        while (true)
        {
          FileOutputStream localFileOutputStream2;
          int j;
          localBoolean = Boolean.valueOf(false);
          break;
          label355: localFileOutputStream2.write(j);
        }
      }
      label365: return localBoolean;
    }

    protected void onPostExecute(Boolean paramBoolean)
    {
      PlayerFragment.this.downloadFinished = true;
    }
  }

  private class downloadRavAsyncTask extends AsyncTask<String, Void, Bitmap>
  {
    private downloadRavAsyncTask()
    {
    }

    protected Bitmap doInBackground(String[] paramArrayOfString)
    {
      Object localObject1 = null;
      Object localObject2 = null;
      try
      {
        URL localURL = new URL(paramArrayOfString[0]);
        localObject2 = localURL;
        try
        {
          label19: HttpURLConnection localHttpURLConnection = (HttpURLConnection)localObject2.openConnection();
          localHttpURLConnection.setDoInput(true);
          localHttpURLConnection.connect();
          Bitmap localBitmap = BitmapFactory.decodeStream(localHttpURLConnection.getInputStream());
          localObject1 = localBitmap;
          label52: return localObject1;
        }
        catch (Exception localException1)
        {
          break label52;
        }
      }
      catch (Exception localException2)
      {
        break label19;
      }
    }

    protected void onPostExecute(Bitmap paramBitmap)
    {
      if ((paramBitmap != null) && (PlayerFragment.this.ravIV != null))
        PlayerFragment.this.ravIV.setImageBitmap(paramBitmap);
    }
  }

  private class getDataAsyncTask extends AsyncTask<String, Void, Boolean>
  {
    private getDataAsyncTask()
    {
    }

    protected Boolean doInBackground(String[] paramArrayOfString)
    {
      boolean bool;
      if (paramArrayOfString[0].equals("getByDate"))
      {
        Calendar localCalendar = Calendar.getInstance();
        PlayerFragment.this.currentDate = (Integer.toString(localCalendar.get(1)) + '-' + PlayerFragment.access$0(1 + localCalendar.get(2)) + '-' + PlayerFragment.access$0(localCalendar.get(5)));
        bool = PlayerFragment.this.getQuery(PlayerFragment.this.currentDate, paramArrayOfString[1]);
      }
      while (true)
      {
        return Boolean.valueOf(bool);
        if (paramArrayOfString[0].equals("getByFile"))
        {
          bool = PlayerFragment.this.getQueryByFile(paramArrayOfString[2], paramArrayOfString[1]);
          continue;
        }
        bool = false;
      }
    }

    protected void onPostExecute(Boolean paramBoolean)
    {
      if ((paramBoolean.booleanValue()) && (PlayerFragment.this.viewsRefenced))
        PlayerFragment.this.loadPlayer();
      while (true)
      {
        return;
        if (PlayerFragment.this.viewsRefenced)
        {
          PlayerFragment.this.titleTV.setText(2131427427);
          continue;
        }
      }
    }
  }

  private class updateCountAsyncTask extends AsyncTask<String, Void, Boolean>
  {
    private updateCountAsyncTask()
    {
    }

    protected Boolean doInBackground(String[] paramArrayOfString)
    {
      try
      {
        new DefaultHttpClient().execute(new HttpPost("http://www.dakatora.co.il/android/androidsql.php?date=" + PlayerFragment.this.currentDate + "&counter=" + paramArrayOfString[0] + "&phonetype=" + "android" + "&Verification=" + "#fgD!F@Gh"));
        bool = true;
        return Boolean.valueOf(bool);
      }
      catch (Exception localException)
      {
        while (true)
          boolean bool = false;
      }
    }
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.eladrich.dakatora.PlayerFragment
 * JD-Core Version:    0.6.0
 */