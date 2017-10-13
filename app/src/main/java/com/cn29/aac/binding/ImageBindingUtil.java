package com.cn29.aac.binding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

/**
 * Created by charlesng0209 on 10/9/2017.
 */

public class ImageBindingUtil {

  @BindingAdapter({"imageUrl"})
  public static void loadImage(ImageView view, String url) {
    Picasso.with(view.getContext()).load(url).into(view);
  }



}
