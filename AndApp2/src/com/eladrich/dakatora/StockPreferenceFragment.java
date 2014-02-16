package com.eladrich.dakatora;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceFragment;

public class StockPreferenceFragment extends PreferenceFragment
{
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    addPreferencesFromResource(getActivity().getResources().getIdentifier(getArguments().getString("resource"), "xml", getActivity().getPackageName()));
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.eladrich.dakatora.StockPreferenceFragment
 * JD-Core Version:    0.6.0
 */