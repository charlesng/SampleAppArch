package com.example.common.components.binding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

/**
 * Created by charlesng0209 on 10/9/2017.
 */

public class ImageBindingUtil {

    @BindingAdapter({"app:imageUrl"})
    public static void loadImage(ImageView view, String url) {
//        Picasso.with(view.getContext()).load(url).into(view);
    }


}
