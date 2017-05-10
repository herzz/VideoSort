package com.zahirherz.videosort.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by zahirh on 5/9/17.
 */

public class VideoSearchResult {
    @SerializedName("Search")
    public List<Video> videos;
}
