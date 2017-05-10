package com.zahirherz.videosort.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.zahirherz.videosort.R;
import com.zahirherz.videosort.databinding.ItemVideoBinding;
import com.zahirherz.videosort.model.Video;

import java.util.List;

/**
 * Created by zahirh on 5/9/17.
 */

public class VideosAdapter extends ArrayAdapter<Video> {

    public VideosAdapter(Context context, List<Video> videos) {
        super(context,0,videos);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Video video = getItem(position);

        ItemVideoBinding binding = null;
        if (convertView == null) {
            binding = DataBindingUtil.inflate(
                    LayoutInflater.from(getContext()),
                    R.layout.item_video, parent, false);
        } else {
            binding = DataBindingUtil.bind(convertView);
        }
        binding.setVideo(video);
        return binding.getRoot();

    }
}
