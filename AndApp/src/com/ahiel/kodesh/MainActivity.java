package com.ahiel.kodesh;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;
import com.ahiel.kodesh.activities.BaseActivity;
import com.ahiel.kodesh.activities.PrefsActivity;

public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "starting create main screen");
        setContentView(R.layout.main);

        String[] values = getParys();

        ListView listView = (ListView) findViewById(R.id.mylist);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, android.R.id.text1, values);
                R.layout.aligen_righ, android.R.id.text1, values);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "Click ListItem Number " + position, Toast.LENGTH_SHORT)

                        .show();
            }

        });
    }

    private String[] getParys() {
        return new String[]{"שחרית",
                    "מנחה",
                    "ערבית",
                    "קריאת שמע על המיטה",
                    "ברכת הלבנה",
                    "ברכת המזון",
                    "מעין שלוש ובורא נפשות",
                    "ברכות הנהנין",
                    "שבע ברכות",
                    "תפילת הדרך",
                    "תיקון חצות",
                    "סליחות",
                    "סדר הדלקת נרות חנוכה"};
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemPrefs:
                startActivity(new Intent(this, PrefsActivity.class));
                break;
        }
        return true;
    }
}
