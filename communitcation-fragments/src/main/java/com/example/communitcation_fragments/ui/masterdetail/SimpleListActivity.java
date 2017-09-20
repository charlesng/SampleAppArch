package com.example.communitcation_fragments.ui.masterdetail;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.communitcation_fragments.R;

import com.example.communitcation_fragments.repository.DummyContent;

import com.example.communitcation_fragments.viewmodel.masterdetail.SimpleMasterDetailShareViewModel;
import java.util.List;

/**
 * An activity representing a list of FeedEntrys. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link SimpleDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * https://developer.android.com/topic/libraries/architecture/viewmodel.html
 */
public class SimpleListActivity extends AppCompatActivity {

  /**
   * Whether or not the activity is in two-pane mode, i.e. running on a tablet
   * device.
   */
  private boolean mTwoPane;
  private SimpleMasterDetailShareViewModel masterDetailShareViewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_simple_list);

    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    toolbar.setTitle(getTitle());

    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        .setAction("Action", null).show());

    View recyclerView = findViewById(R.id.feedentry_list);
    assert recyclerView != null;
    setupRecyclerView((RecyclerView) recyclerView);
    masterDetailShareViewModel = ViewModelProviders.of(this).get(SimpleMasterDetailShareViewModel.class);
    masterDetailShareViewModel.init();
    if (findViewById(R.id.feedentry_detail_container) != null) {
      // The detail container view will be present only in the
      // large-screen layouts (res/values-w900dp).
      // If this view is present, then the
      // activity should be in two-pane mode.
      mTwoPane = true;
    }
  }

  private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
    recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(DummyContent.ITEMS));
  }

  public class SimpleItemRecyclerViewAdapter
      extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

    private final List<DummyContent.DummyItem> mValues;

    public SimpleItemRecyclerViewAdapter(List<DummyContent.DummyItem> items) {
      mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext())
          .inflate(R.layout.simple_list_content, parent, false);
      return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
      holder.mItem = mValues.get(position);
      holder.mIdView.setText(mValues.get(position).id);
      holder.mContentView.setText(mValues.get(position).content);
      holder.mView.setOnClickListener(v -> {
        masterDetailShareViewModel.selectFeedEntry(holder.mItem.id);
        if (mTwoPane) {
          Bundle arguments = new Bundle();
          arguments.putString(SimpleDetailFragment.ARG_ITEM_ID, holder.mItem.id);
          SimpleDetailFragment fragment = new SimpleDetailFragment();
          fragment.setArguments(arguments);
          getSupportFragmentManager().beginTransaction()
              .replace(R.id.feedentry_detail_container, fragment)
              .commit();
        } else {

          Context context = v.getContext();
          Intent intent = new Intent(context, SimpleDetailActivity.class);
          intent.putExtra(SimpleDetailFragment.ARG_ITEM_ID, holder.mItem.id);
          context.startActivity(intent);
        }
      });
    }

    @Override
    public int getItemCount() {
      return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

      public final View mView;
      public final TextView mIdView;
      public final TextView mContentView;
      public DummyContent.DummyItem mItem;

      public ViewHolder(View view) {
        super(view);
        mView = view;
        mIdView = view.findViewById(R.id.id);
        mContentView = view.findViewById(R.id.content);
      }

      @Override
      public String toString() {
        return super.toString() + " '" + mContentView.getText() + "'";
      }
    }
  }
}
