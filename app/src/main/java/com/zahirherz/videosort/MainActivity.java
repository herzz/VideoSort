package com.zahirherz.videosort;

import android.databinding.DataBindingUtil;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.zahirherz.videosort.databinding.ActivityMainBinding;
import com.zahirherz.videosort.http.VideosParser;
import com.zahirherz.videosort.model.VideoSearchResult;
import com.zahirherz.videosort.ui.adapter.VideosAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity
    implements SearchView.OnQueryTextListener{

    ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        try{
            Observable<VideoSearchResult> result = VideosParser.searchByTitle(query);
            Subscription subscription = result
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            videoSearchResult -> EventBus.getDefault().postSticky(videoSearchResult),
                            Throwable::printStackTrace,
                            () -> Log.d("XXX ", "Complete!")
                    );

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(VideoSearchResult event) {
        updateListView(event);
    }

    private void updateListView(VideoSearchResult videoSearchResult) {
        mBinding.listVideos.setAdapter(new VideosAdapter(this, videoSearchResult.videos));
    }

}
