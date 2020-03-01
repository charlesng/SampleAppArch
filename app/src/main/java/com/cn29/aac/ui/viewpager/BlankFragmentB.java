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
 * Use the {@link BlankFragmentB#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragmentB extends DaggerFragment {

    @Inject
    PagerAgentViewModel pagerAgentViewModel;
    private TextView textView;

    public BlankFragmentB() {
        // Required empty public constructor
    }

    public static BlankFragmentB newInstance() {
        BlankFragmentB fragment = new BlankFragmentB();

        return fragment;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //setup the listener for the fragment B
        Observer<String> observer = msg -> textView.setText(msg);
        pagerAgentViewModel.getMessageContainerB().observe(getViewLifecycleOwner(), observer);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank_b, container, false);
        textView = view.findViewById(R.id.fragment_textB);
        //set the on click listener
        Button button = view.findViewById(R.id.btnB);
        button.setOnClickListener(
            v -> pagerAgentViewModel.sendMessageToA("Hello A"));
        return view;
    }

}
