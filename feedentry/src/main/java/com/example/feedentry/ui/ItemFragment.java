package com.example.feedentry.ui;



import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.feedentry.R;


import com.example.feedentry.datasources.room.AppDatabase;
import com.example.feedentry.datasources.room.FeedEntryDAO;
import com.example.feedentry.datasources.room.FeedEntryDAO_Impl;
import com.example.feedentry.repository.bean.FeedEntry;
import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 * <p />
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ItemFragment extends Fragment {

  // TODO: Customize parameter argument names
  private static final String ARG_COLUMN_COUNT = "column-count";
  // TODO: Customize parameters
  private int mColumnCount = 2;
  private OnListFragmentInteractionListener mListener;

  /**
   * Mandatory empty constructor for the fragment manager to instantiate the
   * fragment (e.g. upon screen orientation changes).
   */
  public ItemFragment() {
  }

  // TODO: Customize parameter initialization
  @SuppressWarnings("unused")
  public static ItemFragment newInstance(int columnCount) {
    ItemFragment fragment = new ItemFragment();
    Bundle args = new Bundle();
    args.putInt(ARG_COLUMN_COUNT, columnCount);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (getArguments() != null) {
      mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_item_list, container, false);

    // Set the adapter
    if (view instanceof RecyclerView) {
      Context context = view.getContext();
      RecyclerView recyclerView = (RecyclerView) view;
      if (mColumnCount <= 1) {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
      } else {
        recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
      }

      ArrayList<FeedEntry> feedEntryArrayList = new ArrayList<>();
      AppDatabase db = Room.databaseBuilder(getActivity(),
          AppDatabase.class, "feedentry-db").build();
      FeedEntryDAO feedEntryDAO = db.feedEntryDao();
//      if(feedEntryDAO.getAll().size() == 0) {
        for (int i = 0; i < 10; i++) {
          feedEntryArrayList.add(
              new FeedEntry(String.valueOf(i + 1), String.format("Test %d", i + 1),
                  String.format("Detail : %d", i + 1)));
        }
//        feedEntryDAO.insertAll(new FeedEntry("1","Testing","Details"));
//      }
      recyclerView.setAdapter(new MyItemRecyclerViewAdapter(feedEntryDAO.getAll(), mListener));
    }
    return view;
  }


  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof OnListFragmentInteractionListener) {
      mListener = (OnListFragmentInteractionListener) context;
    } else {
      throw new RuntimeException(context.toString()
          + " must implement OnListFragmentInteractionListener");
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mListener = null;
  }

  /**
   * This interface must be implemented by activities that contain this
   * fragment to allow an interaction in this fragment to be communicated
   * to the activity and potentially other fragments contained in that
   * activity.
   * <p/>
   * See the Android Training lesson <a href=
   * "http://developer.android.com/training/basics/fragments/communicating.html"
   * >Communicating with Other Fragments</a> for more information.
   */
  public interface OnListFragmentInteractionListener {

    // TODO: Update argument type and name
    void onListFragmentInteraction(FeedEntry item);
  }
}
