package com.cn29.aac.ui.viewpager;


import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.cn29.aac.R;
import com.cn29.aac.ui.viewpager.vm.PagerAgentViewModel;
import dagger.android.support.DaggerFragment;
import javax.inject.Inject;


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
