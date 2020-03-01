package com.cn29.aac.ui.viewpager;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cn29.aac.R;
import com.cn29.aac.ui.viewpager.vm.PagerAgentViewModel;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import dagger.android.support.DaggerFragment;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragmentA#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragmentA extends DaggerFragment {

  @Inject
  PagerAgentViewModel pagerAgentViewModel;
  private TextView textView;


  public BlankFragmentA() {
    // Required empty public constructor
  }

  // TODO: Rename and change types and number of parameters
  public static BlankFragmentA newInstance() {
    BlankFragmentA fragment = new BlankFragmentA();
    return fragment;
  }


  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    //setup the listener for the fragment A
    Observer<String> observer = msg -> textView.setText(msg);
    pagerAgentViewModel.getMessageContainerA().observe(this, observer);

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_blank_a, container, false);
    textView = view.findViewById(R.id.fragment_textA);
    // set the onclick listener
    Button button = view.findViewById(R.id.btnA);
    button.setOnClickListener(
        v -> pagerAgentViewModel.sendMessageToB("Hello B"));
    return view;
  }

}
