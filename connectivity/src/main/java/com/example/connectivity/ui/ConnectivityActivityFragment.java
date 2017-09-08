package com.example.connectivity.ui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.connectivity.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class ConnectivityActivityFragment extends Fragment {

  public ConnectivityActivityFragment() {
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_connectivity, container, false);
  }
}
