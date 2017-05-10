package com.zahirherz.videosort.http;

import com.zahirherz.videosort.model.VideoSearchResult;

import java.io.IOException;
import rx.Observable;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

/**
 * Created by zahirh on 5/9/17.
 */

public class VideosParser {

    public static Observable<VideoSearchResult> searchByTitle(String q) throws IOException {
        RxJavaCallAdapterFactory rxAdapter =
                RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.omdbapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(rxAdapter)
                .build();

        VideosRetrofit api = retrofit.create(VideosRetrofit.class);

        return api.search(q, "json");
    }
}
