package com.actionbarsherlock.widget;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.DataSetObservable;
import android.os.Handler;
import android.text.TextUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

class ActivityChooserModel extends DataSetObservable
{
  private static final String ATTRIBUTE_ACTIVITY = "activity";
  private static final String ATTRIBUTE_TIME = "time";
  private static final String ATTRIBUTE_WEIGHT = "weight";
  private static final boolean DEBUG = false;
  private static final int DEFAULT_ACTIVITY_INFLATION = 5;
  private static final float DEFAULT_HISTORICAL_RECORD_WEIGHT = 1.0F;
  public static final String DEFAULT_HISTORY_FILE_NAME = "activity_choser_model_history.xml";
  public static final int DEFAULT_HISTORY_MAX_LENGTH = 50;
  private static final String HISTORY_FILE_EXTENSION = ".xml";
  private static final int INVALID_INDEX = -1;
  private static final String LOG_TAG = ActivityChooserModel.class.getSimpleName();
  private static final SerialExecutor SERIAL_EXECUTOR;
  private static final String TAG_HISTORICAL_RECORD = "historical-record";
  private static final String TAG_HISTORICAL_RECORDS = "historical-records";
  private static final Map<String, ActivityChooserModel> sDataModelRegistry;
  private static final Object sRegistryLock = new Object();
  private final List<ActivityResolveInfo> mActivites = new ArrayList();
  private OnChooseActivityListener mActivityChoserModelPolicy;
  private ActivitySorter mActivitySorter = new DefaultSorter(null);
  private boolean mCanReadHistoricalData = true;
  private final Context mContext;
  private final Handler mHandler = new Handler();
  private final List<HistoricalRecord> mHistoricalRecords = new ArrayList();
  private boolean mHistoricalRecordsChanged = true;
  private final String mHistoryFileName;
  private int mHistoryMaxSize = 50;
  private final Object mInstanceLock = new Object();
  private Intent mIntent;
  private boolean mReadShareHistoryCalled = false;

  static
  {
    sDataModelRegistry = new HashMap();
    SERIAL_EXECUTOR = new SerialExecutor(null);
  }

  private ActivityChooserModel(Context paramContext, String paramString)
  {
    this.mContext = paramContext.getApplicationContext();
    if ((!TextUtils.isEmpty(paramString)) && (!paramString.endsWith(".xml")));
    for (this.mHistoryFileName = (paramString + ".xml"); ; this.mHistoryFileName = paramString)
      return;
  }

  private boolean addHisoricalRecord(HistoricalRecord paramHistoricalRecord)
  {
    synchronized (this.mInstanceLock)
    {
      boolean bool = this.mHistoricalRecords.add(paramHistoricalRecord);
      if (bool)
      {
        this.mHistoricalRecordsChanged = true;
        pruneExcessiveHistoricalRecordsLocked();
        persistHistoricalData();
        sortActivities();
      }
      return bool;
    }
  }

  public static ActivityChooserModel get(Context paramContext, String paramString)
  {
    synchronized (sRegistryLock)
    {
      ActivityChooserModel localActivityChooserModel = (ActivityChooserModel)sDataModelRegistry.get(paramString);
      if (localActivityChooserModel == null)
      {
        localActivityChooserModel = new ActivityChooserModel(paramContext, paramString);
        sDataModelRegistry.put(paramString, localActivityChooserModel);
      }
      localActivityChooserModel.readHistoricalData();
      return localActivityChooserModel;
    }
  }

  private void loadActivitiesLocked()
  {
    this.mActivites.clear();
    List localList;
    int j;
    if (this.mIntent != null)
    {
      localList = this.mContext.getPackageManager().queryIntentActivities(this.mIntent, 0);
      int i = localList.size();
      j = 0;
      if (j >= i)
        sortActivities();
    }
    while (true)
    {
      return;
      ResolveInfo localResolveInfo = (ResolveInfo)localList.get(j);
      this.mActivites.add(new ActivityResolveInfo(localResolveInfo));
      j++;
      break;
      notifyChanged();
    }
  }

  // ERROR //
  private void persistHistoricalData()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 126	com/actionbarsherlock/widget/ActivityChooserModel:mInstanceLock	Ljava/lang/Object;
    //   4: astore_1
    //   5: aload_1
    //   6: monitorenter
    //   7: aload_0
    //   8: getfield 144	com/actionbarsherlock/widget/ActivityChooserModel:mReadShareHistoryCalled	Z
    //   11: ifne +19 -> 30
    //   14: new 271	java/lang/IllegalStateException
    //   17: dup
    //   18: ldc_w 273
    //   21: invokespecial 274	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   24: athrow
    //   25: astore_2
    //   26: aload_1
    //   27: monitorexit
    //   28: aload_2
    //   29: athrow
    //   30: aload_0
    //   31: getfield 146	com/actionbarsherlock/widget/ActivityChooserModel:mHistoricalRecordsChanged	Z
    //   34: ifne +8 -> 42
    //   37: aload_1
    //   38: monitorexit
    //   39: goto +40 -> 79
    //   42: aload_0
    //   43: iconst_0
    //   44: putfield 146	com/actionbarsherlock/widget/ActivityChooserModel:mHistoricalRecordsChanged	Z
    //   47: aload_0
    //   48: iconst_1
    //   49: putfield 142	com/actionbarsherlock/widget/ActivityChooserModel:mCanReadHistoricalData	Z
    //   52: aload_0
    //   53: getfield 189	com/actionbarsherlock/widget/ActivityChooserModel:mHistoryFileName	Ljava/lang/String;
    //   56: invokestatic 165	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   59: ifne +18 -> 77
    //   62: getstatic 122	com/actionbarsherlock/widget/ActivityChooserModel:SERIAL_EXECUTOR	Lcom/actionbarsherlock/widget/ActivityChooserModel$SerialExecutor;
    //   65: new 24	com/actionbarsherlock/widget/ActivityChooserModel$HistoryPersister
    //   68: dup
    //   69: aload_0
    //   70: aconst_null
    //   71: invokespecial 277	com/actionbarsherlock/widget/ActivityChooserModel$HistoryPersister:<init>	(Lcom/actionbarsherlock/widget/ActivityChooserModel;Lcom/actionbarsherlock/widget/ActivityChooserModel$HistoryPersister;)V
    //   74: invokevirtual 281	com/actionbarsherlock/widget/ActivityChooserModel$SerialExecutor:execute	(Ljava/lang/Runnable;)V
    //   77: aload_1
    //   78: monitorexit
    //   79: return
    //
    // Exception table:
    //   from	to	target	type
    //   7	28	25	finally
    //   30	79	25	finally
  }

  private void pruneExcessiveHistoricalRecordsLocked()
  {
    List localList = this.mHistoricalRecords;
    int i = localList.size() - this.mHistoryMaxSize;
    if (i <= 0);
    while (true)
    {
      return;
      this.mHistoricalRecordsChanged = true;
      for (int j = 0; j < i; j++)
        ((HistoricalRecord)localList.remove(0));
    }
  }

  private void readHistoricalData()
  {
    synchronized (this.mInstanceLock)
    {
      if ((!this.mCanReadHistoricalData) || (this.mHistoricalRecordsChanged))
      {
        this.mCanReadHistoricalData = false;
        this.mReadShareHistoryCalled = true;
        if (!TextUtils.isEmpty(this.mHistoryFileName))
          SERIAL_EXECUTOR.execute(new HistoryLoader(null));
      }
    }
  }

  private void sortActivities()
  {
    synchronized (this.mInstanceLock)
    {
      if ((this.mActivitySorter != null) && (!this.mActivites.isEmpty()))
      {
        this.mActivitySorter.sort(this.mIntent, this.mActivites, Collections.unmodifiableList(this.mHistoricalRecords));
        notifyChanged();
      }
      return;
    }
  }

  public Intent chooseActivity(int paramInt)
  {
    ActivityResolveInfo localActivityResolveInfo = (ActivityResolveInfo)this.mActivites.get(paramInt);
    ComponentName localComponentName = new ComponentName(localActivityResolveInfo.resolveInfo.activityInfo.packageName, localActivityResolveInfo.resolveInfo.activityInfo.name);
    Intent localIntent1 = new Intent(this.mIntent);
    localIntent1.setComponent(localComponentName);
    if (this.mActivityChoserModelPolicy != null)
    {
      Intent localIntent2 = new Intent(localIntent1);
      if (this.mActivityChoserModelPolicy.onChooseActivity(this, localIntent2))
        localIntent1 = null;
    }
    while (true)
    {
      return localIntent1;
      addHisoricalRecord(new HistoricalRecord(localComponentName, System.currentTimeMillis(), 1.0F));
    }
  }

  public ResolveInfo getActivity(int paramInt)
  {
    synchronized (this.mInstanceLock)
    {
      ResolveInfo localResolveInfo = ((ActivityResolveInfo)this.mActivites.get(paramInt)).resolveInfo;
      return localResolveInfo;
    }
  }

  public int getActivityCount()
  {
    synchronized (this.mInstanceLock)
    {
      int i = this.mActivites.size();
      return i;
    }
  }

  public int getActivityIndex(ResolveInfo paramResolveInfo)
  {
    List localList = this.mActivites;
    int i = localList.size();
    for (int j = 0; ; j++)
    {
      if (j >= i)
        j = -1;
      do
        return j;
      while (((ActivityResolveInfo)localList.get(j)).resolveInfo == paramResolveInfo);
    }
  }

  public ResolveInfo getDefaultActivity()
  {
    ResolveInfo localResolveInfo;
    synchronized (this.mInstanceLock)
    {
      if (!this.mActivites.isEmpty())
        localResolveInfo = ((ActivityResolveInfo)this.mActivites.get(0)).resolveInfo;
      else
        localResolveInfo = null;
    }
    return localResolveInfo;
  }

  public int getHistoryMaxSize()
  {
    synchronized (this.mInstanceLock)
    {
      int i = this.mHistoryMaxSize;
      return i;
    }
  }

  public int getHistorySize()
  {
    synchronized (this.mInstanceLock)
    {
      int i = this.mHistoricalRecords.size();
      return i;
    }
  }

  public Intent getIntent()
  {
    synchronized (this.mInstanceLock)
    {
      Intent localIntent = this.mIntent;
      return localIntent;
    }
  }

  public void setActivitySorter(ActivitySorter paramActivitySorter)
  {
    synchronized (this.mInstanceLock)
    {
      if (this.mActivitySorter != paramActivitySorter)
      {
        this.mActivitySorter = paramActivitySorter;
        sortActivities();
      }
    }
  }

  public void setDefaultActivity(int paramInt)
  {
    ActivityResolveInfo localActivityResolveInfo1 = (ActivityResolveInfo)this.mActivites.get(paramInt);
    ActivityResolveInfo localActivityResolveInfo2 = (ActivityResolveInfo)this.mActivites.get(0);
    float f;
    if (localActivityResolveInfo2 != null)
      f = 5.0F + (localActivityResolveInfo2.weight - localActivityResolveInfo1.weight);
    while (true)
    {
      addHisoricalRecord(new HistoricalRecord(new ComponentName(localActivityResolveInfo1.resolveInfo.activityInfo.packageName, localActivityResolveInfo1.resolveInfo.activityInfo.name), System.currentTimeMillis(), f));
      return;
      f = 1.0F;
    }
  }

  public void setHistoryMaxSize(int paramInt)
  {
    synchronized (this.mInstanceLock)
    {
      if (this.mHistoryMaxSize != paramInt)
      {
        this.mHistoryMaxSize = paramInt;
        pruneExcessiveHistoricalRecordsLocked();
        sortActivities();
      }
    }
  }

  public void setIntent(Intent paramIntent)
  {
    synchronized (this.mInstanceLock)
    {
      if (this.mIntent != paramIntent)
      {
        this.mIntent = paramIntent;
        loadActivitiesLocked();
      }
    }
  }

  public void setOnChooseActivityListener(OnChooseActivityListener paramOnChooseActivityListener)
  {
    this.mActivityChoserModelPolicy = paramOnChooseActivityListener;
  }

  public static abstract interface ActivityChooserModelClient
  {
    public abstract void setActivityChooserModel(ActivityChooserModel paramActivityChooserModel);
  }

  public final class ActivityResolveInfo
    implements Comparable<ActivityResolveInfo>
  {
    public final ResolveInfo resolveInfo;
    public float weight;

    public ActivityResolveInfo(ResolveInfo arg2)
    {
      Object localObject;
      this.resolveInfo = localObject;
    }

    public int compareTo(ActivityResolveInfo paramActivityResolveInfo)
    {
      return Float.floatToIntBits(paramActivityResolveInfo.weight) - Float.floatToIntBits(this.weight);
    }

    public boolean equals(Object paramObject)
    {
      int i = 1;
      if (this == paramObject);
      while (true)
      {
        return i;
        if (paramObject == null)
        {
          i = 0;
          continue;
        }
        if (getClass() != paramObject.getClass())
        {
          i = 0;
          continue;
        }
        ActivityResolveInfo localActivityResolveInfo = (ActivityResolveInfo)paramObject;
        if (Float.floatToIntBits(this.weight) == Float.floatToIntBits(localActivityResolveInfo.weight))
          continue;
        i = 0;
      }
    }

    public int hashCode()
    {
      return 31 + Float.floatToIntBits(this.weight);
    }

    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("[");
      localStringBuilder.append("resolveInfo:").append(this.resolveInfo.toString());
      localStringBuilder.append("; weight:").append(new BigDecimal(this.weight));
      localStringBuilder.append("]");
      return localStringBuilder.toString();
    }
  }

  public static abstract interface ActivitySorter
  {
    public abstract void sort(Intent paramIntent, List<ActivityChooserModel.ActivityResolveInfo> paramList, List<ActivityChooserModel.HistoricalRecord> paramList1);
  }

  private final class DefaultSorter
    implements ActivityChooserModel.ActivitySorter
  {
    private static final float WEIGHT_DECAY_COEFFICIENT = 0.95F;
    private final Map<String, ActivityChooserModel.ActivityResolveInfo> mPackageNameToActivityMap = new HashMap();

    private DefaultSorter()
    {
    }

    public void sort(Intent paramIntent, List<ActivityChooserModel.ActivityResolveInfo> paramList, List<ActivityChooserModel.HistoricalRecord> paramList1)
    {
      Map localMap = this.mPackageNameToActivityMap;
      localMap.clear();
      int i = paramList.size();
      int j = 0;
      int k;
      float f;
      if (j >= i)
      {
        k = -1 + paramList1.size();
        f = 1.0F;
      }
      for (int m = k; ; m--)
      {
        if (m < 0)
        {
          Collections.sort(paramList);
          return;
          ActivityChooserModel.ActivityResolveInfo localActivityResolveInfo1 = (ActivityChooserModel.ActivityResolveInfo)paramList.get(j);
          localActivityResolveInfo1.weight = 0.0F;
          localMap.put(localActivityResolveInfo1.resolveInfo.activityInfo.packageName, localActivityResolveInfo1);
          j++;
          break;
        }
        ActivityChooserModel.HistoricalRecord localHistoricalRecord = (ActivityChooserModel.HistoricalRecord)paramList1.get(m);
        ActivityChooserModel.ActivityResolveInfo localActivityResolveInfo2 = (ActivityChooserModel.ActivityResolveInfo)localMap.get(localHistoricalRecord.activity.getPackageName());
        if (localActivityResolveInfo2 == null)
          continue;
        localActivityResolveInfo2.weight += f * localHistoricalRecord.weight;
        f *= 0.95F;
      }
    }
  }

  public static final class HistoricalRecord
  {
    public final ComponentName activity;
    public final long time;
    public final float weight;

    public HistoricalRecord(ComponentName paramComponentName, long paramLong, float paramFloat)
    {
      this.activity = paramComponentName;
      this.time = paramLong;
      this.weight = paramFloat;
    }

    public HistoricalRecord(String paramString, long paramLong, float paramFloat)
    {
      this(ComponentName.unflattenFromString(paramString), paramLong, paramFloat);
    }

    public boolean equals(Object paramObject)
    {
      int i = 1;
      if (this == paramObject);
      while (true)
      {
        return i;
        if (paramObject == null)
        {
          i = 0;
          continue;
        }
        if (getClass() != paramObject.getClass())
        {
          i = 0;
          continue;
        }
        HistoricalRecord localHistoricalRecord = (HistoricalRecord)paramObject;
        if (this.activity == null)
        {
          if (localHistoricalRecord.activity != null)
          {
            i = 0;
            continue;
          }
        }
        else if (!this.activity.equals(localHistoricalRecord.activity))
        {
          i = 0;
          continue;
        }
        if (this.time != localHistoricalRecord.time)
        {
          i = 0;
          continue;
        }
        if (Float.floatToIntBits(this.weight) == Float.floatToIntBits(localHistoricalRecord.weight))
          continue;
        i = 0;
      }
    }

    public int hashCode()
    {
      if (this.activity == null);
      for (int i = 0; ; i = this.activity.hashCode())
        return 31 * (31 * (i + 31) + (int)(this.time ^ this.time >>> 32)) + Float.floatToIntBits(this.weight);
    }

    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("[");
      localStringBuilder.append("; activity:").append(this.activity);
      localStringBuilder.append("; time:").append(this.time);
      localStringBuilder.append("; weight:").append(new BigDecimal(this.weight));
      localStringBuilder.append("]");
      return localStringBuilder.toString();
    }
  }

  private final class HistoryLoader
    implements Runnable
  {
    private HistoryLoader()
    {
    }

    // ERROR //
    public void run()
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 15	com/actionbarsherlock/widget/ActivityChooserModel$HistoryLoader:this$0	Lcom/actionbarsherlock/widget/ActivityChooserModel;
      //   4: invokestatic 34	com/actionbarsherlock/widget/ActivityChooserModel:access$0	(Lcom/actionbarsherlock/widget/ActivityChooserModel;)Landroid/content/Context;
      //   7: aload_0
      //   8: getfield 15	com/actionbarsherlock/widget/ActivityChooserModel$HistoryLoader:this$0	Lcom/actionbarsherlock/widget/ActivityChooserModel;
      //   11: invokestatic 37	com/actionbarsherlock/widget/ActivityChooserModel:access$1	(Lcom/actionbarsherlock/widget/ActivityChooserModel;)Ljava/lang/String;
      //   14: invokevirtual 43	android/content/Context:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
      //   17: astore_2
      //   18: invokestatic 49	android/util/Xml:newPullParser	()Lorg/xmlpull/v1/XmlPullParser;
      //   21: astore 11
      //   23: aload 11
      //   25: aload_2
      //   26: aconst_null
      //   27: invokeinterface 55 3 0
      //   32: iconst_0
      //   33: istore 12
      //   35: goto +453 -> 488
      //   38: ldc 57
      //   40: aload 11
      //   42: invokeinterface 61 1 0
      //   47: invokevirtual 67	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   50: ifne +71 -> 121
      //   53: new 28	org/xmlpull/v1/XmlPullParserException
      //   56: dup
      //   57: ldc 69
      //   59: invokespecial 72	org/xmlpull/v1/XmlPullParserException:<init>	(Ljava/lang/String;)V
      //   62: athrow
      //   63: astore 8
      //   65: invokestatic 75	com/actionbarsherlock/widget/ActivityChooserModel:access$8	()Ljava/lang/String;
      //   68: new 77	java/lang/StringBuilder
      //   71: dup
      //   72: ldc 79
      //   74: invokespecial 80	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   77: aload_0
      //   78: getfield 15	com/actionbarsherlock/widget/ActivityChooserModel$HistoryLoader:this$0	Lcom/actionbarsherlock/widget/ActivityChooserModel;
      //   81: invokestatic 37	com/actionbarsherlock/widget/ActivityChooserModel:access$1	(Lcom/actionbarsherlock/widget/ActivityChooserModel;)Ljava/lang/String;
      //   84: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   87: invokevirtual 87	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   90: aload 8
      //   92: invokestatic 93	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   95: pop
      //   96: aload_2
      //   97: ifnull +7 -> 104
      //   100: aload_2
      //   101: invokevirtual 98	java/io/FileInputStream:close	()V
      //   104: return
      //   105: astore_1
      //   106: goto -2 -> 104
      //   109: aload 11
      //   111: invokeinterface 102 1 0
      //   116: istore 12
      //   118: goto +370 -> 488
      //   121: new 104	java/util/ArrayList
      //   124: dup
      //   125: invokespecial 105	java/util/ArrayList:<init>	()V
      //   128: astore 13
      //   130: aload 11
      //   132: invokeinterface 102 1 0
      //   137: istore 14
      //   139: iload 14
      //   141: iconst_1
      //   142: if_icmpne +88 -> 230
      //   145: aload_0
      //   146: getfield 15	com/actionbarsherlock/widget/ActivityChooserModel$HistoryLoader:this$0	Lcom/actionbarsherlock/widget/ActivityChooserModel;
      //   149: invokestatic 109	com/actionbarsherlock/widget/ActivityChooserModel:access$2	(Lcom/actionbarsherlock/widget/ActivityChooserModel;)Ljava/lang/Object;
      //   152: astore 16
      //   154: aload 16
      //   156: monitorenter
      //   157: new 111	java/util/LinkedHashSet
      //   160: dup
      //   161: aload 13
      //   163: invokespecial 114	java/util/LinkedHashSet:<init>	(Ljava/util/Collection;)V
      //   166: astore 17
      //   168: aload_0
      //   169: getfield 15	com/actionbarsherlock/widget/ActivityChooserModel$HistoryLoader:this$0	Lcom/actionbarsherlock/widget/ActivityChooserModel;
      //   172: invokestatic 118	com/actionbarsherlock/widget/ActivityChooserModel:access$3	(Lcom/actionbarsherlock/widget/ActivityChooserModel;)Ljava/util/List;
      //   175: astore 19
      //   177: bipush 255
      //   179: aload 19
      //   181: invokeinterface 123 1 0
      //   186: iadd
      //   187: istore 20
      //   189: iload 20
      //   191: ifge +190 -> 381
      //   194: aload 19
      //   196: invokeinterface 123 1 0
      //   201: aload 17
      //   203: invokeinterface 126 1 0
      //   208: if_icmpne +199 -> 407
      //   211: aload 16
      //   213: monitorexit
      //   214: aload_2
      //   215: ifnull -111 -> 104
      //   218: aload_2
      //   219: invokevirtual 98	java/io/FileInputStream:close	()V
      //   222: goto -118 -> 104
      //   225: astore 25
      //   227: goto -123 -> 104
      //   230: iload 14
      //   232: iconst_3
      //   233: if_icmpeq -103 -> 130
      //   236: iload 14
      //   238: iconst_4
      //   239: if_icmpeq -109 -> 130
      //   242: ldc 128
      //   244: aload 11
      //   246: invokeinterface 61 1 0
      //   251: invokevirtual 67	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   254: ifne +62 -> 316
      //   257: new 28	org/xmlpull/v1/XmlPullParserException
      //   260: dup
      //   261: ldc 130
      //   263: invokespecial 72	org/xmlpull/v1/XmlPullParserException:<init>	(Ljava/lang/String;)V
      //   266: athrow
      //   267: astore 5
      //   269: invokestatic 75	com/actionbarsherlock/widget/ActivityChooserModel:access$8	()Ljava/lang/String;
      //   272: new 77	java/lang/StringBuilder
      //   275: dup
      //   276: ldc 79
      //   278: invokespecial 80	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   281: aload_0
      //   282: getfield 15	com/actionbarsherlock/widget/ActivityChooserModel$HistoryLoader:this$0	Lcom/actionbarsherlock/widget/ActivityChooserModel;
      //   285: invokestatic 37	com/actionbarsherlock/widget/ActivityChooserModel:access$1	(Lcom/actionbarsherlock/widget/ActivityChooserModel;)Ljava/lang/String;
      //   288: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   291: invokevirtual 87	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   294: aload 5
      //   296: invokestatic 93	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   299: pop
      //   300: aload_2
      //   301: ifnull -197 -> 104
      //   304: aload_2
      //   305: invokevirtual 98	java/io/FileInputStream:close	()V
      //   308: goto -204 -> 104
      //   311: astore 7
      //   313: goto -209 -> 104
      //   316: aload 13
      //   318: new 132	com/actionbarsherlock/widget/ActivityChooserModel$HistoricalRecord
      //   321: dup
      //   322: aload 11
      //   324: aconst_null
      //   325: ldc 134
      //   327: invokeinterface 138 3 0
      //   332: aload 11
      //   334: aconst_null
      //   335: ldc 140
      //   337: invokeinterface 138 3 0
      //   342: invokestatic 146	java/lang/Long:parseLong	(Ljava/lang/String;)J
      //   345: aload 11
      //   347: aconst_null
      //   348: ldc 148
      //   350: invokeinterface 138 3 0
      //   355: invokestatic 154	java/lang/Float:parseFloat	(Ljava/lang/String;)F
      //   358: invokespecial 157	com/actionbarsherlock/widget/ActivityChooserModel$HistoricalRecord:<init>	(Ljava/lang/String;JF)V
      //   361: invokeinterface 160 2 0
      //   366: pop
      //   367: goto -237 -> 130
      //   370: astore_3
      //   371: aload_2
      //   372: ifnull +7 -> 379
      //   375: aload_2
      //   376: invokevirtual 98	java/io/FileInputStream:close	()V
      //   379: aload_3
      //   380: athrow
      //   381: aload 17
      //   383: aload 19
      //   385: iload 20
      //   387: invokeinterface 164 2 0
      //   392: checkcast 132	com/actionbarsherlock/widget/ActivityChooserModel$HistoricalRecord
      //   395: invokeinterface 165 2 0
      //   400: pop
      //   401: iinc 20 255
      //   404: goto -215 -> 189
      //   407: aload 19
      //   409: invokeinterface 168 1 0
      //   414: aload 19
      //   416: aload 17
      //   418: invokeinterface 172 2 0
      //   423: pop
      //   424: aload_0
      //   425: getfield 15	com/actionbarsherlock/widget/ActivityChooserModel$HistoryLoader:this$0	Lcom/actionbarsherlock/widget/ActivityChooserModel;
      //   428: iconst_1
      //   429: invokestatic 176	com/actionbarsherlock/widget/ActivityChooserModel:access$4	(Lcom/actionbarsherlock/widget/ActivityChooserModel;Z)V
      //   432: aload_0
      //   433: getfield 15	com/actionbarsherlock/widget/ActivityChooserModel$HistoryLoader:this$0	Lcom/actionbarsherlock/widget/ActivityChooserModel;
      //   436: invokestatic 180	com/actionbarsherlock/widget/ActivityChooserModel:access$5	(Lcom/actionbarsherlock/widget/ActivityChooserModel;)Landroid/os/Handler;
      //   439: new 182	com/actionbarsherlock/widget/ActivityChooserModel$HistoryLoader$1
      //   442: dup
      //   443: aload_0
      //   444: invokespecial 185	com/actionbarsherlock/widget/ActivityChooserModel$HistoryLoader$1:<init>	(Lcom/actionbarsherlock/widget/ActivityChooserModel$HistoryLoader;)V
      //   447: invokevirtual 191	android/os/Handler:post	(Ljava/lang/Runnable;)Z
      //   450: pop
      //   451: aload 16
      //   453: monitorexit
      //   454: aload_2
      //   455: ifnull -351 -> 104
      //   458: aload_2
      //   459: invokevirtual 98	java/io/FileInputStream:close	()V
      //   462: goto -358 -> 104
      //   465: astore 24
      //   467: goto -363 -> 104
      //   470: astore 18
      //   472: aload 16
      //   474: monitorexit
      //   475: aload 18
      //   477: athrow
      //   478: astore 10
      //   480: goto -376 -> 104
      //   483: astore 4
      //   485: goto -106 -> 379
      //   488: iload 12
      //   490: iconst_1
      //   491: if_icmpeq -453 -> 38
      //   494: iload 12
      //   496: iconst_2
      //   497: if_icmpne -388 -> 109
      //   500: goto -462 -> 38
      //
      // Exception table:
      //   from	to	target	type
      //   18	63	63	org/xmlpull/v1/XmlPullParserException
      //   109	157	63	org/xmlpull/v1/XmlPullParserException
      //   242	267	63	org/xmlpull/v1/XmlPullParserException
      //   316	367	63	org/xmlpull/v1/XmlPullParserException
      //   475	478	63	org/xmlpull/v1/XmlPullParserException
      //   0	18	105	java/io/FileNotFoundException
      //   218	222	225	java/io/IOException
      //   18	63	267	java/io/IOException
      //   109	157	267	java/io/IOException
      //   242	267	267	java/io/IOException
      //   316	367	267	java/io/IOException
      //   475	478	267	java/io/IOException
      //   304	308	311	java/io/IOException
      //   18	63	370	finally
      //   65	96	370	finally
      //   109	157	370	finally
      //   242	267	370	finally
      //   269	300	370	finally
      //   316	367	370	finally
      //   475	478	370	finally
      //   458	462	465	java/io/IOException
      //   157	214	470	finally
      //   381	454	470	finally
      //   472	475	470	finally
      //   100	104	478	java/io/IOException
      //   375	379	483	java/io/IOException
    }
  }

  private final class HistoryPersister
    implements Runnable
  {
    private HistoryPersister()
    {
    }

    // ERROR //
    public void run()
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 15	com/actionbarsherlock/widget/ActivityChooserModel$HistoryPersister:this$0	Lcom/actionbarsherlock/widget/ActivityChooserModel;
      //   4: invokestatic 34	com/actionbarsherlock/widget/ActivityChooserModel:access$2	(Lcom/actionbarsherlock/widget/ActivityChooserModel;)Ljava/lang/Object;
      //   7: astore_1
      //   8: aload_1
      //   9: monitorenter
      //   10: new 36	java/util/ArrayList
      //   13: dup
      //   14: aload_0
      //   15: getfield 15	com/actionbarsherlock/widget/ActivityChooserModel$HistoryPersister:this$0	Lcom/actionbarsherlock/widget/ActivityChooserModel;
      //   18: invokestatic 40	com/actionbarsherlock/widget/ActivityChooserModel:access$3	(Lcom/actionbarsherlock/widget/ActivityChooserModel;)Ljava/util/List;
      //   21: invokespecial 43	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
      //   24: astore_2
      //   25: aload_1
      //   26: monitorexit
      //   27: aload_0
      //   28: getfield 15	com/actionbarsherlock/widget/ActivityChooserModel$HistoryPersister:this$0	Lcom/actionbarsherlock/widget/ActivityChooserModel;
      //   31: invokestatic 47	com/actionbarsherlock/widget/ActivityChooserModel:access$0	(Lcom/actionbarsherlock/widget/ActivityChooserModel;)Landroid/content/Context;
      //   34: aload_0
      //   35: getfield 15	com/actionbarsherlock/widget/ActivityChooserModel$HistoryPersister:this$0	Lcom/actionbarsherlock/widget/ActivityChooserModel;
      //   38: invokestatic 51	com/actionbarsherlock/widget/ActivityChooserModel:access$1	(Lcom/actionbarsherlock/widget/ActivityChooserModel;)Ljava/lang/String;
      //   41: iconst_0
      //   42: invokevirtual 57	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
      //   45: astore 6
      //   47: invokestatic 63	android/util/Xml:newSerializer	()Lorg/xmlpull/v1/XmlSerializer;
      //   50: astore 7
      //   52: aload 7
      //   54: aload 6
      //   56: aconst_null
      //   57: invokeinterface 69 3 0
      //   62: aload 7
      //   64: ldc 71
      //   66: iconst_1
      //   67: invokestatic 77	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   70: invokeinterface 81 3 0
      //   75: aload 7
      //   77: aconst_null
      //   78: ldc 83
      //   80: invokeinterface 87 3 0
      //   85: pop
      //   86: aload_2
      //   87: invokeinterface 93 1 0
      //   92: istore 20
      //   94: iconst_0
      //   95: istore 21
      //   97: iload 21
      //   99: iload 20
      //   101: if_icmplt +73 -> 174
      //   104: aload 7
      //   106: aconst_null
      //   107: ldc 83
      //   109: invokeinterface 96 3 0
      //   114: pop
      //   115: aload 7
      //   117: invokeinterface 99 1 0
      //   122: aload 6
      //   124: ifnull +8 -> 132
      //   127: aload 6
      //   129: invokevirtual 104	java/io/FileOutputStream:close	()V
      //   132: return
      //   133: astore_3
      //   134: aload_1
      //   135: monitorexit
      //   136: aload_3
      //   137: athrow
      //   138: astore 4
      //   140: invokestatic 108	com/actionbarsherlock/widget/ActivityChooserModel:access$8	()Ljava/lang/String;
      //   143: new 110	java/lang/StringBuilder
      //   146: dup
      //   147: ldc 112
      //   149: invokespecial 115	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   152: aload_0
      //   153: getfield 15	com/actionbarsherlock/widget/ActivityChooserModel$HistoryPersister:this$0	Lcom/actionbarsherlock/widget/ActivityChooserModel;
      //   156: invokestatic 51	com/actionbarsherlock/widget/ActivityChooserModel:access$1	(Lcom/actionbarsherlock/widget/ActivityChooserModel;)Ljava/lang/String;
      //   159: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   162: invokevirtual 122	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   165: aload 4
      //   167: invokestatic 128	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   170: pop
      //   171: goto -39 -> 132
      //   174: aload_2
      //   175: iconst_0
      //   176: invokeinterface 132 2 0
      //   181: checkcast 134	com/actionbarsherlock/widget/ActivityChooserModel$HistoricalRecord
      //   184: astore 22
      //   186: aload 7
      //   188: aconst_null
      //   189: ldc 136
      //   191: invokeinterface 87 3 0
      //   196: pop
      //   197: aload 7
      //   199: aconst_null
      //   200: ldc 138
      //   202: aload 22
      //   204: getfield 141	com/actionbarsherlock/widget/ActivityChooserModel$HistoricalRecord:activity	Landroid/content/ComponentName;
      //   207: invokevirtual 146	android/content/ComponentName:flattenToString	()Ljava/lang/String;
      //   210: invokeinterface 150 4 0
      //   215: pop
      //   216: aload 7
      //   218: aconst_null
      //   219: ldc 152
      //   221: aload 22
      //   223: getfield 155	com/actionbarsherlock/widget/ActivityChooserModel$HistoricalRecord:time	J
      //   226: invokestatic 160	java/lang/String:valueOf	(J)Ljava/lang/String;
      //   229: invokeinterface 150 4 0
      //   234: pop
      //   235: aload 7
      //   237: aconst_null
      //   238: ldc 162
      //   240: aload 22
      //   242: getfield 165	com/actionbarsherlock/widget/ActivityChooserModel$HistoricalRecord:weight	F
      //   245: invokestatic 168	java/lang/String:valueOf	(F)Ljava/lang/String;
      //   248: invokeinterface 150 4 0
      //   253: pop
      //   254: aload 7
      //   256: aconst_null
      //   257: ldc 136
      //   259: invokeinterface 96 3 0
      //   264: pop
      //   265: iinc 21 1
      //   268: goto -171 -> 97
      //   271: astore 16
      //   273: invokestatic 108	com/actionbarsherlock/widget/ActivityChooserModel:access$8	()Ljava/lang/String;
      //   276: new 110	java/lang/StringBuilder
      //   279: dup
      //   280: ldc 112
      //   282: invokespecial 115	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   285: aload_0
      //   286: getfield 15	com/actionbarsherlock/widget/ActivityChooserModel$HistoryPersister:this$0	Lcom/actionbarsherlock/widget/ActivityChooserModel;
      //   289: invokestatic 51	com/actionbarsherlock/widget/ActivityChooserModel:access$1	(Lcom/actionbarsherlock/widget/ActivityChooserModel;)Ljava/lang/String;
      //   292: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   295: invokevirtual 122	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   298: aload 16
      //   300: invokestatic 128	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   303: pop
      //   304: aload 6
      //   306: ifnull -174 -> 132
      //   309: aload 6
      //   311: invokevirtual 104	java/io/FileOutputStream:close	()V
      //   314: goto -182 -> 132
      //   317: astore 18
      //   319: goto -187 -> 132
      //   322: astore 13
      //   324: invokestatic 108	com/actionbarsherlock/widget/ActivityChooserModel:access$8	()Ljava/lang/String;
      //   327: new 110	java/lang/StringBuilder
      //   330: dup
      //   331: ldc 112
      //   333: invokespecial 115	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   336: aload_0
      //   337: getfield 15	com/actionbarsherlock/widget/ActivityChooserModel$HistoryPersister:this$0	Lcom/actionbarsherlock/widget/ActivityChooserModel;
      //   340: invokestatic 51	com/actionbarsherlock/widget/ActivityChooserModel:access$1	(Lcom/actionbarsherlock/widget/ActivityChooserModel;)Ljava/lang/String;
      //   343: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   346: invokevirtual 122	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   349: aload 13
      //   351: invokestatic 128	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   354: pop
      //   355: aload 6
      //   357: ifnull -225 -> 132
      //   360: aload 6
      //   362: invokevirtual 104	java/io/FileOutputStream:close	()V
      //   365: goto -233 -> 132
      //   368: astore 15
      //   370: goto -238 -> 132
      //   373: astore 10
      //   375: invokestatic 108	com/actionbarsherlock/widget/ActivityChooserModel:access$8	()Ljava/lang/String;
      //   378: new 110	java/lang/StringBuilder
      //   381: dup
      //   382: ldc 112
      //   384: invokespecial 115	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   387: aload_0
      //   388: getfield 15	com/actionbarsherlock/widget/ActivityChooserModel$HistoryPersister:this$0	Lcom/actionbarsherlock/widget/ActivityChooserModel;
      //   391: invokestatic 51	com/actionbarsherlock/widget/ActivityChooserModel:access$1	(Lcom/actionbarsherlock/widget/ActivityChooserModel;)Ljava/lang/String;
      //   394: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   397: invokevirtual 122	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   400: aload 10
      //   402: invokestatic 128	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   405: pop
      //   406: aload 6
      //   408: ifnull -276 -> 132
      //   411: aload 6
      //   413: invokevirtual 104	java/io/FileOutputStream:close	()V
      //   416: goto -284 -> 132
      //   419: astore 12
      //   421: goto -289 -> 132
      //   424: astore 8
      //   426: aload 6
      //   428: ifnull +8 -> 436
      //   431: aload 6
      //   433: invokevirtual 104	java/io/FileOutputStream:close	()V
      //   436: aload 8
      //   438: athrow
      //   439: astore 9
      //   441: goto -5 -> 436
      //   444: astore 29
      //   446: goto -314 -> 132
      //   449: astore_3
      //   450: goto -316 -> 134
      //
      // Exception table:
      //   from	to	target	type
      //   10	25	133	finally
      //   134	136	133	finally
      //   27	47	138	java/io/FileNotFoundException
      //   52	122	271	java/lang/IllegalArgumentException
      //   174	265	271	java/lang/IllegalArgumentException
      //   309	314	317	java/io/IOException
      //   52	122	322	java/lang/IllegalStateException
      //   174	265	322	java/lang/IllegalStateException
      //   360	365	368	java/io/IOException
      //   52	122	373	java/io/IOException
      //   174	265	373	java/io/IOException
      //   411	416	419	java/io/IOException
      //   52	122	424	finally
      //   174	265	424	finally
      //   273	304	424	finally
      //   324	355	424	finally
      //   375	406	424	finally
      //   431	436	439	java/io/IOException
      //   127	132	444	java/io/IOException
      //   25	27	449	finally
    }
  }

  public static abstract interface OnChooseActivityListener
  {
    public abstract boolean onChooseActivity(ActivityChooserModel paramActivityChooserModel, Intent paramIntent);
  }

  private static class SerialExecutor
    implements Executor
  {
    Runnable mActive;
    final LinkedList<Runnable> mTasks = new LinkedList();

    public void execute(Runnable paramRunnable)
    {
      monitorenter;
      try
      {
        this.mTasks.offer(new ActivityChooserModel.SerialExecutor.1(this, paramRunnable));
        if (this.mActive == null)
          scheduleNext();
        monitorexit;
        return;
      }
      finally
      {
        localObject = finally;
        monitorexit;
      }
      throw localObject;
    }

    protected void scheduleNext()
    {
      monitorenter;
      try
      {
        Runnable localRunnable = (Runnable)this.mTasks.poll();
        this.mActive = localRunnable;
        if (localRunnable != null)
          this.mActive.run();
        monitorexit;
        return;
      }
      finally
      {
        localObject = finally;
        monitorexit;
      }
      throw localObject;
    }
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.actionbarsherlock.widget.ActivityChooserModel
 * JD-Core Version:    0.6.0
 */