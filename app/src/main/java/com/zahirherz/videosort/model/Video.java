package com.zahirherz.videosort.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zahirh on 5/9/17.
 */

public class Video {
    @SerializedName("Title")
    public String title;

    @SerializedName("Year")
    public String year;

    @SerializedName("imdbID")
    public String imdbID;

    @SerializedName("Poster")
    public String poster;

}
