package com.zahirherz.videosort.binding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by zahirh on 5/9/17.
 */

public class ImageBinding {
    @BindingAdapter({"android:src"})
    public static void setImage(ImageView imageView, String url) {
        Picasso.with(imageView.getContext())
                .load(url)
                .into(imageView);
    }
}
