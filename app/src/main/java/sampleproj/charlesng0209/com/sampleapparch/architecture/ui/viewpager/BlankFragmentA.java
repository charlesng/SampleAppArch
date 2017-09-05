package sampleproj.charlesng0209.com.sampleapparch.architecture.ui.viewpager;


import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import sampleproj.charlesng0209.com.sampleapparch.R;
import sampleproj.charlesng0209.com.sampleapparch.architecture.viewmodel.viewpager.PagerAgentViewModel;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragmentA#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragmentA extends LifecycleFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView textView;


    public BlankFragmentA() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragmentA.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragmentA newInstance(String param1, String param2) {
        BlankFragmentA fragment = new BlankFragmentA();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //setup the listener for the fragment A
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onChanged(@Nullable String msg) {
                textView.setText(msg);
            }
        };
        ViewModelProviders.of(getActivity()).get(PagerAgentViewModel.class).getMessageContainerA().observe(this, observer);
        ViewModelProviders.of(getActivity()).get(PagerAgentViewModel.class).getMessageContainerA().removeObserver(observer);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank_a, container, false);
        textView = (TextView) view.findViewById(R.id.fragment_textA);
        // set the onclick listener
        Button button = (Button) view.findViewById(R.id.btnA);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewModelProviders.of(getActivity()).get(PagerAgentViewModel.class).sendMessageToB("Hello B");
            }
        });
        return view;
    }

}
