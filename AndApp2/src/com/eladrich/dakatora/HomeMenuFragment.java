package com.eladrich.dakatora;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.actionbarsherlock.app.SherlockFragment;
import java.util.List;

public class HomeMenuFragment extends SherlockFragment
{
  List<String[]> menuArrayList;

  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2130903078, paramViewGroup, false);
  }

  public void rowClick(View paramView)
  {
    switch (paramView.getId())
    {
    case 2131099761:
    default:
    case 2131099760:
    case 2131099762:
    case 2131099763:
    case 2131099764:
    }
    while (true)
    {
      return;
      startActivity(new Intent(getActivity().getApplicationContext(), LimudActivity.class));
      continue;
      startActivity(new Intent(getActivity().getApplicationContext(), CompassActivity.class));
      continue;
      startActivity(new Intent(getActivity().getApplicationContext(), ZmanimActivity.class));
      continue;
      startActivity(new Intent(getActivity().getApplicationContext(), InfoListActivity.class));
    }
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.eladrich.dakatora.HomeMenuFragment
 * JD-Core Version:    0.6.0
 */