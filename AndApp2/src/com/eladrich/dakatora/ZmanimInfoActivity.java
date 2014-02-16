package com.eladrich.dakatora;

import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.actionbarsherlock.app.SherlockActivity;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class ZmanimInfoActivity extends SherlockActivity
{
  WebView zmanimWebView;

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903072);
    this.zmanimWebView = ((WebView)findViewById(2131099744));
    readUnicodeFileAsyncTask localreadUnicodeFileAsyncTask = new readUnicodeFileAsyncTask(null);
    String[] arrayOfString = new String[1];
    arrayOfString[0] = "zmanim_info.html";
    localreadUnicodeFileAsyncTask.execute(arrayOfString);
    this.zmanimWebView.getSettings().setBuiltInZoomControls(false);
  }

  public String readUnicodeFile(String paramString)
  {
    Object localObject = null;
    AssetManager localAssetManager = getAssets();
    InputStream localInputStream;
    try
    {
      localInputStream = localAssetManager.open(paramString);
      if (localInputStream != null)
        break label44;
      throw new Exception("File Does Not Exist");
    }
    catch (Exception localException1)
    {
    }
    System.out.println(localException1);
    while (true)
    {
      label44: StringBuffer localStringBuffer;
      while (true)
      {
        return localObject.toString();
        InputStreamReader localInputStreamReader = new InputStreamReader(localInputStream, "UTF-8");
        try
        {
          localStringBuffer = new StringBuffer();
          try
          {
            while (true)
            {
              int i = localInputStreamReader.read();
              if (i <= -1)
              {
                if (localInputStreamReader == null)
                  break label121;
                localInputStreamReader.close();
                localObject = localStringBuffer;
                break;
              }
              localStringBuffer.append((char)i);
            }
          }
          catch (Exception localException2)
          {
            localObject = localStringBuffer;
          }
        }
        catch (Exception localException3)
        {
        }
      }
      break;
      label121: localObject = localStringBuffer;
    }
  }

  private class readUnicodeFileAsyncTask extends AsyncTask<String, Void, String>
  {
    private readUnicodeFileAsyncTask()
    {
    }

    protected String doInBackground(String[] paramArrayOfString)
    {
      return ZmanimInfoActivity.this.readUnicodeFile(paramArrayOfString[0]);
    }

    protected void onPostExecute(String paramString)
    {
      if ((paramString != null) && (ZmanimInfoActivity.this.zmanimWebView != null))
        ZmanimInfoActivity.this.zmanimWebView.loadDataWithBaseURL(null, paramString, "text/html", "utf-8", null);
    }
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.eladrich.dakatora.ZmanimInfoActivity
 * JD-Core Version:    0.6.0
 */