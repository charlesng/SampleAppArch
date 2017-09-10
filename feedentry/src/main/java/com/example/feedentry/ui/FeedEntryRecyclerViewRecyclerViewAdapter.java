package com.example.feedentry.ui;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.feedentry.R;
import com.example.feedentry.databinding.FragmentItemBinding;
import com.example.feedentry.repository.bean.FeedEntry;
import com.example.feedentry.ui.common.BaseRecyclerViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link FeedEntry} and makes a call to the
 * specified {@link }.
 * TODO: Replace the implementation with code for your data type.
 */
public class FeedEntryRecyclerViewRecyclerViewAdapter extends
        BaseRecyclerViewAdapter<FeedEntry, FragmentItemBinding> {

    private final List<FeedEntry> mValues;

    FeedEntryRecyclerViewRecyclerViewAdapter(List<FeedEntry> mValues,
                                             OnItemClickListener<FeedEntry> itemClickListener) {
        super(itemClickListener);
        this.mValues = mValues;
    }

    @Override
    protected FeedEntry getItemForPosition(int position) {
        return mValues.get(position);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    @Override
    protected int getLayoutIdForPosition(int position) {
        return R.layout.fragment_item;
    }

    @Override
    protected void bind(FragmentItemBinding binding, FeedEntry item) {
        binding.setFeedEntry(item);
//    binding.setImageUrl("http://i.imgur.com/DvpvklR.png");
//    binding.setErrorMsg("Cannot get the image from url ");

        new Picasso.Builder(binding.imageView.getContext()).listener(new Picasso.Listener() {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                Log.e("Piccasso ", exception.getMessage());
            }
        }).build().load("http://i.imgur.com/DvpvklR.png").resize(50, 50)
                .centerCrop().
                error(android.R.drawable.ic_dialog_email).into(binding.imageView);
    }
}
