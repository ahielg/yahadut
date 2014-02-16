package com.ahiel.kodesh.activities;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import com.ahiel.kodesh.R;

/**
 * User: ahiel
 * Date: 28/11/12
 * Time: 22:33
 */
public class PrefsActivity extends PreferenceActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);
    }

    @Override
    public void setPreferenceScreen(PreferenceScreen preferenceScreen) {
        super.setPreferenceScreen(preferenceScreen);    //To change body of overridden methods use File | Settings | File Templates.
    }
}