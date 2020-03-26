package com.cn29.aac.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

/**
 * Created by charlesng0209 on 10/9/2017.
 */
object ImageBindingUtil {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView,
                  url: String?) {
        Picasso.with(view.context).load(url).into(view)
    }

    @BindingAdapter("imageUrl")
    fun loadCircleImage(view: CircleImageView,
                        url: String?) {
        Picasso.with(view.context).load(url).into(view)
    }
}