package sampleproj.charlesng0209.com.sampleapparch.architecture.ui.masterdetail;

import android.app.Activity;
import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import sampleproj.charlesng0209.com.sampleapparch.R;
import sampleproj.charlesng0209.com.sampleapparch.architecture.ui.masterdetail.dummy.DummyContent;
import sampleproj.charlesng0209.com.sampleapparch.architecture.viewModel.masterdetail.MasterDetailShareViewModel;

/**
 * A fragment representing a single Email detail screen.
 * This fragment is either contained in a {@link EmailListActivity}
 * in two-pane mode (on tablets) or a {@link EmailDetailActivity}
 * on handsets.
 */
public class EmailDetailFragment extends LifecycleFragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public EmailDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            final CollapsingToolbarLayout appBarLayout = activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.content);
            }
            MasterDetailShareViewModel masterDetailShareViewModel = ViewModelProviders.of(getActivity()).get(MasterDetailShareViewModel.class);

            if(masterDetailShareViewModel != null)
            {
                /*
                    In this section, if it is triggered from other fragment in EmailListActivity. It will be fine.
                    But if it is triggered from EmailDetailActivity. It will be firstly get the default view model profile (which is empty but not null)
                    It cannot identify which one is empty.
                 */
                masterDetailShareViewModel.getSelectedEmail().observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        appBarLayout.setTitle(s);

                    }
                });
            }

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.email_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.email_detail)).setText(mItem.details);
        }

        return rootView;
    }
}
