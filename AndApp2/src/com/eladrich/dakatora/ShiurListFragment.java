package com.eladrich.dakatora;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockListFragment;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ShiurListFragment extends SherlockListFragment
{
  final int ADD_EACH_TIME = 10;
  final String ADD_UPDATE = "add_update";
  final String FIRST_UPDATE = "first_ update";
  final String UPDATE_FAILED = "update_failed";
  CustomAdapter adapter;
  int fromPosition;
  boolean listUpdating;
  int pressedPosition;
  View pressedView;
  int prevPressedPosition;
  List<String[]> shiurArrayList;
  ListView shiurList;
  TextView shuirListEmpty;
  ProgressBar shuirListProgress;

  public static String[] copyStringArray(String[] paramArrayOfString, int paramInt)
  {
    String[] arrayOfString = new String[paramInt];
    int i = 0;
    if (i >= arrayOfString.length)
      return arrayOfString;
    if (i < paramArrayOfString.length)
      arrayOfString[i] = paramArrayOfString[i];
    while (true)
    {
      i++;
      break;
      arrayOfString[i] = "";
    }
  }

  public static int findDatePosition(String paramString, List<String[]> paramList)
  {
    for (int i = 0; ; i++)
    {
      if (i >= paramList.size())
        i = -1;
      do
        return i;
      while (((String[])paramList.get(i))[4].equals(paramString));
    }
  }

  private static String pad(int paramInt)
  {
    if (paramInt >= 10);
    for (String str = String.valueOf(paramInt); ; str = "0" + String.valueOf(paramInt))
      return str;
  }

  public void addShiurs(String paramString)
  {
    if (this.shuirListProgress != null)
      this.shuirListProgress.setVisibility(0);
    getListAsyncTask localgetListAsyncTask = new getListAsyncTask(null);
    String[] arrayOfString = new String[3];
    arrayOfString[0] = String.valueOf(this.fromPosition);
    arrayOfString[1] = String.valueOf(10);
    arrayOfString[2] = paramString;
    localgetListAsyncTask.execute(arrayOfString);
    this.fromPosition = (10 + this.fromPosition);
  }

  public void onActivityCreated(Bundle paramBundle)
  {
    this.shiurArrayList = new ArrayList();
    this.pressedPosition = -1;
    this.fromPosition = 0;
    this.listUpdating = false;
    super.onActivityCreated(paramBundle);
  }

  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    View localView = paramLayoutInflater.inflate(2130903085, paramViewGroup, false);
    this.shuirListEmpty = ((TextView)localView.findViewById(2131099777));
    this.shuirListProgress = ((ProgressBar)localView.findViewById(2131099778));
    return localView;
  }

  public void onDestroy()
  {
    super.onDestroy();
  }

  public void onDestroyView()
  {
    super.onDestroyView();
  }

  public void onDetach()
  {
    super.onDetach();
  }

  public void onListItemClick(ListView paramListView, View paramView, int paramInt, long paramLong)
  {
    if (this.pressedPosition != paramInt)
    {
      ((HomeActivity)getActivity()).startSpecificFragmentFile(copyStringArray((String[])this.shiurArrayList.get(paramInt), 4));
      paramView.setBackgroundColor(getResources().getColor(2131230746));
      this.pressedPosition = paramInt;
      if ((this.pressedView != null) && (!this.pressedView.equals(paramView)))
        this.pressedView.setBackgroundResource(2130837694);
      this.pressedView = paramView;
    }
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
    addShiurs("first_ update");
    super.onStart();
  }

  public void onStop()
  {
    getListView().setOnScrollListener(null);
    this.shiurArrayList.clear();
    if (this.adapter != null)
      this.adapter.notifyDataSetInvalidated();
    ((HomeActivity)getActivity()).stopSpecificFragmentFile();
    this.fromPosition = 0;
    this.pressedView = null;
    this.pressedPosition = -1;
    super.onStop();
  }

  public void populateList()
  {
    if (getActivity() != null)
    {
      if (this.pressedPosition == -1)
      {
        Calendar localCalendar = Calendar.getInstance();
        this.pressedPosition = findDatePosition(Integer.toString(localCalendar.get(1)) + '-' + pad(1 + localCalendar.get(2)) + '-' + pad(localCalendar.get(5)), this.shiurArrayList);
      }
      this.adapter = new CustomAdapter(getActivity().getApplicationContext(), this.shiurArrayList);
      setListAdapter(this.adapter);
      getListView().setOnScrollListener(new AbsListView.OnScrollListener()
      {
        public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3)
        {
          if (paramInt1 + paramInt2 >= paramInt3);
          for (int i = 1; ; i = 0)
          {
            if ((i != 0) && (!ShiurListFragment.this.listUpdating))
            {
              ShiurListFragment.this.listUpdating = true;
              ShiurListFragment.this.addShiurs("add_update");
            }
            return;
          }
        }

        public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
        {
        }
      });
    }
  }

  public void updateList()
  {
    this.adapter.notifyDataSetChanged();
    this.listUpdating = false;
  }

  public class CustomAdapter extends BaseAdapter
  {
    private final Context context;
    private final List<String[]> items;

    public CustomAdapter(List<String[]> arg2)
    {
      Object localObject1;
      this.context = localObject1;
      Object localObject2;
      this.items = localObject2;
    }

    public int getCount()
    {
      return this.items.size();
    }

    public Object getItem(int paramInt)
    {
      return this.items.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return getItem(paramInt).hashCode();
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      View localView = paramView;
      ShiurListFragment.ViewHolder localViewHolder;
      if (localView == null)
      {
        localView = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(2130903084, paramViewGroup, false);
        if (paramInt == ShiurListFragment.this.pressedPosition)
          ShiurListFragment.this.pressedView = localView;
        localViewHolder = new ShiurListFragment.ViewHolder();
        localViewHolder.shiurName = ((TextView)localView.findViewById(2131099774));
        localViewHolder.ravName = ((TextView)localView.findViewById(2131099776));
        localViewHolder.dateEntry = ((TextView)localView.findViewById(2131099775));
        localView.setTag(localViewHolder);
      }
      while (true)
      {
        String[] arrayOfString = (String[])getItem(paramInt);
        localViewHolder.shiurName.setText(arrayOfString[3]);
        localViewHolder.ravName.setText(arrayOfString[2]);
        localViewHolder.dateEntry.setText(arrayOfString[4]);
        if (paramInt != ShiurListFragment.this.pressedPosition)
          localView.setBackgroundResource(2130837694);
        if (paramInt == ShiurListFragment.this.pressedPosition)
          localView.setBackgroundColor(ShiurListFragment.this.getResources().getColor(2131230746));
        return localView;
        localViewHolder = (ShiurListFragment.ViewHolder)localView.getTag();
      }
    }
  }

  public static class ViewHolder
  {
    public TextView dateEntry;
    public TextView ravName;
    public TextView shiurName;
  }

  private class getListAsyncTask extends AsyncTask<String, Void, String>
  {
    private getListAsyncTask()
    {
    }

    // ERROR //
    protected String doInBackground(String[] paramArrayOfString)
    {
      // Byte code:
      //   0: aload_1
      //   1: iconst_2
      //   2: aaload
      //   3: astore_2
      //   4: aload_1
      //   5: iconst_0
      //   6: aaload
      //   7: astore_3
      //   8: aload_1
      //   9: iconst_1
      //   10: aaload
      //   11: astore 4
      //   13: new 35	org/apache/http/impl/client/DefaultHttpClient
      //   16: dup
      //   17: invokespecial 36	org/apache/http/impl/client/DefaultHttpClient:<init>	()V
      //   20: new 38	org/apache/http/client/methods/HttpPost
      //   23: dup
      //   24: new 40	java/lang/StringBuilder
      //   27: dup
      //   28: ldc 42
      //   30: invokespecial 45	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   33: aload_3
      //   34: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   37: ldc 51
      //   39: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   42: aload 4
      //   44: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   47: ldc 53
      //   49: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   52: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   55: invokespecial 58	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
      //   58: invokeinterface 64 2 0
      //   63: invokeinterface 70 1 0
      //   68: invokeinterface 76 1 0
      //   73: astore 6
      //   75: new 78	java/io/InputStreamReader
      //   78: dup
      //   79: aload 6
      //   81: ldc 80
      //   83: invokespecial 83	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
      //   86: astore 7
      //   88: new 85	java/io/BufferedReader
      //   91: dup
      //   92: aload 7
      //   94: bipush 8
      //   96: invokespecial 88	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
      //   99: astore 8
      //   101: new 40	java/lang/StringBuilder
      //   104: dup
      //   105: invokespecial 89	java/lang/StringBuilder:<init>	()V
      //   108: astore 9
      //   110: aload 8
      //   112: invokevirtual 92	java/io/BufferedReader:readLine	()Ljava/lang/String;
      //   115: astore 11
      //   117: aload 11
      //   119: ifnonnull +53 -> 172
      //   122: aload 6
      //   124: invokevirtual 97	java/io/InputStream:close	()V
      //   127: aload 9
      //   129: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   132: astore 13
      //   134: new 99	org/json/JSONArray
      //   137: dup
      //   138: aload 13
      //   140: invokespecial 100	org/json/JSONArray:<init>	(Ljava/lang/String;)V
      //   143: astore 14
      //   145: iconst_0
      //   146: istore 15
      //   148: aload 14
      //   150: invokevirtual 104	org/json/JSONArray:length	()I
      //   153: istore 17
      //   155: iload 15
      //   157: iload 17
      //   159: if_icmplt +50 -> 209
      //   162: aload_2
      //   163: areturn
      //   164: astore 5
      //   166: ldc 106
      //   168: astore_2
      //   169: goto -7 -> 162
      //   172: aload 9
      //   174: new 40	java/lang/StringBuilder
      //   177: dup
      //   178: aload 11
      //   180: invokestatic 112	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
      //   183: invokespecial 45	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   186: ldc 114
      //   188: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   191: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   194: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   197: pop
      //   198: goto -88 -> 110
      //   201: astore 10
      //   203: ldc 106
      //   205: astore_2
      //   206: goto -44 -> 162
      //   209: aload 14
      //   211: iload 15
      //   213: invokevirtual 118	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
      //   216: astore 18
      //   218: iconst_5
      //   219: anewarray 108	java/lang/String
      //   222: astore 19
      //   224: aload 19
      //   226: iconst_1
      //   227: new 40	java/lang/StringBuilder
      //   230: dup
      //   231: ldc 120
      //   233: invokespecial 45	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   236: aload 18
      //   238: ldc 122
      //   240: invokevirtual 128	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   243: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   246: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   249: aastore
      //   250: aload 19
      //   252: iconst_1
      //   253: aload 19
      //   255: iconst_1
      //   256: aaload
      //   257: ldc 130
      //   259: ldc 132
      //   261: invokevirtual 136	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
      //   264: aastore
      //   265: aload 18
      //   267: ldc 138
      //   269: invokevirtual 128	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   272: astore 20
      //   274: aload 20
      //   276: ldc 80
      //   278: invokevirtual 142	java/lang/String:getBytes	(Ljava/lang/String;)[B
      //   281: astore 30
      //   283: new 108	java/lang/String
      //   286: dup
      //   287: aload 30
      //   289: ldc 144
      //   291: invokespecial 147	java/lang/String:<init>	([BLjava/lang/String;)V
      //   294: astore 22
      //   296: aload 19
      //   298: iconst_3
      //   299: aload 22
      //   301: aastore
      //   302: aload 18
      //   304: ldc 149
      //   306: invokevirtual 128	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   309: astore 23
      //   311: aload 23
      //   313: ldc 151
      //   315: invokevirtual 155	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   318: ifne +129 -> 447
      //   321: aload 19
      //   323: iconst_0
      //   324: new 40	java/lang/StringBuilder
      //   327: dup
      //   328: ldc 157
      //   330: invokespecial 45	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   333: aload 23
      //   335: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   338: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   341: aastore
      //   342: new 40	java/lang/StringBuilder
      //   345: dup
      //   346: aload 18
      //   348: ldc 159
      //   350: invokevirtual 128	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   353: invokestatic 112	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
      //   356: invokespecial 45	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   359: ldc 130
      //   361: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   364: aload 18
      //   366: ldc 161
      //   368: invokevirtual 128	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   371: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   374: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   377: astore 24
      //   379: aload 24
      //   381: astore 25
      //   383: aload 25
      //   385: ldc 80
      //   387: invokevirtual 142	java/lang/String:getBytes	(Ljava/lang/String;)[B
      //   390: astore 28
      //   392: new 108	java/lang/String
      //   395: dup
      //   396: aload 28
      //   398: ldc 144
      //   400: invokespecial 147	java/lang/String:<init>	([BLjava/lang/String;)V
      //   403: astore 29
      //   405: aload 29
      //   407: astore 25
      //   409: aload 19
      //   411: iconst_2
      //   412: aload 25
      //   414: aastore
      //   415: aload 19
      //   417: iconst_4
      //   418: aload 18
      //   420: ldc 163
      //   422: invokevirtual 128	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   425: aastore
      //   426: aload_0
      //   427: getfield 14	com/eladrich/dakatora/ShiurListFragment$getListAsyncTask:this$0	Lcom/eladrich/dakatora/ShiurListFragment;
      //   430: getfield 167	com/eladrich/dakatora/ShiurListFragment:shiurArrayList	Ljava/util/List;
      //   433: aload 19
      //   435: invokeinterface 172 2 0
      //   440: pop
      //   441: iinc 15 1
      //   444: goto -296 -> 148
      //   447: aload 19
      //   449: iconst_0
      //   450: ldc 174
      //   452: aastore
      //   453: goto -111 -> 342
      //   456: astore 16
      //   458: ldc 106
      //   460: astore_2
      //   461: goto -299 -> 162
      //   464: astore 26
      //   466: goto -57 -> 409
      //   469: astore 21
      //   471: aload 20
      //   473: astore 22
      //   475: goto -179 -> 296
      //
      // Exception table:
      //   from	to	target	type
      //   13	75	164	java/lang/Exception
      //   75	134	201	java/lang/Exception
      //   172	198	201	java/lang/Exception
      //   134	155	456	org/json/JSONException
      //   209	274	456	org/json/JSONException
      //   274	296	456	org/json/JSONException
      //   296	379	456	org/json/JSONException
      //   383	405	456	org/json/JSONException
      //   409	453	456	org/json/JSONException
      //   383	405	464	java/io/UnsupportedEncodingException
      //   274	296	469	java/io/UnsupportedEncodingException
    }

    protected void onPostExecute(String paramString)
    {
      if (ShiurListFragment.this.shuirListProgress != null)
        ShiurListFragment.this.shuirListProgress.setVisibility(8);
      if (paramString.equals("first_ update"))
      {
        ShiurListFragment.this.populateList();
        ShiurListFragment.this.shuirListEmpty.setVisibility(8);
      }
      while (true)
      {
        return;
        if (paramString.equals("add_update"))
        {
          ShiurListFragment.this.updateList();
          ShiurListFragment.this.shuirListEmpty.setVisibility(8);
          continue;
        }
        if (!paramString.equals("update_failed"))
          continue;
        ShiurListFragment.this.shuirListEmpty.setVisibility(0);
      }
    }
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.eladrich.dakatora.ShiurListFragment
 * JD-Core Version:    0.6.0
 */