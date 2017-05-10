package com.zahirherz.videosort.http;

import com.zahirherz.videosort.model.VideoSearchResult;

import rx.Observable;

import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by zahirh on 5/9/17.
 */

public interface VideosRetrofit {
    @GET("./")
    Observable<VideoSearchResult> search(@Query("s") String q, @Query("r") String format);
}
