package com.mac.zonemovies.view.trailer;

import android.util.Log;

import com.mac.zonemovies.data.remote.movieapi.MovieService;
import com.mac.zonemovies.data.remote.movieapi.to.MovieVideosResponse;
import com.mac.zonemovies.data.remote.movieapi.to.VideoResult;
import com.mac.zonemovies.util.resources.StringManager;

import java.net.UnknownHostException;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class TrailerPresenter implements TrailerContract.Presenter {

    private static final String TAG = "TrailerPresenterTAG";

    @Inject
    MovieService movieService;

    @Inject
    StringManager stringManager;

    private final TrailerContract.View trailerView;

    @Inject
    public TrailerPresenter(TrailerContract.View trailerView) {
        this.trailerView = trailerView;
    }

    @Override
    public void getMovieVideos(int movieId) {
        movieService.getMovieVideos(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieVideosResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //
                    }

                    @Override
                    public void onNext(MovieVideosResponse movieVideosResponse) {
                        for(VideoResult result:movieVideosResponse.getResults()) {
                            Log.d(TAG, "onNext: Vide url " + result.getVideoUrl());
                        }
                        trailerView.showVideo(movieVideosResponse.getResults().get(0).getVideoUrl());
                    }

                    @Override
                    public void onError(Throwable e) {
                        handleError(e);
                    }

                    @Override
                    public void onComplete() {
                        // log analytics
                    }
                });
    }

    @Override
    public void loadMovieVideo(String videoUrl) {
        // from here we would start the mediaplayer
    }

    private void handleError(Throwable e) {
        if(e instanceof UnknownHostException) {
            trailerView.showError("Network is disabled");
        } else if(e instanceof HttpException) {
            handleHttpError((HttpException)e);
        } else {
            trailerView.showError(e.getMessage());
        }
    }

    private void handleHttpError(HttpException e) {
        trailerView.showError(stringManager.getErrorMessage(e.code()));
    }
}
