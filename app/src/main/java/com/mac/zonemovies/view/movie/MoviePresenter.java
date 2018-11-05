package com.mac.zonemovies.view.movie;

import com.mac.zonemovies.data.remote.movieapi.MovieService;
import com.mac.zonemovies.data.remote.movieapi.to.MovieResponse;
import com.mac.zonemovies.util.resources.StringManager;

import java.net.UnknownHostException;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class MoviePresenter implements MovieContract.Presenter {

    private static final String TAG = "MoviePresenterTAG";

    @Inject
    MovieService movieService;

    @Inject
    StringManager stringManager;

    private final MovieContract.View movieView;

    @Inject
    public MoviePresenter(MovieContract.View movieView) {
        this.movieView = movieView;
    }

    @Override
    public void getMovie(int movieId) {
        movieService.getMovie(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //
                    }

                    @Override
                    public void onNext(MovieResponse movieResponse) {
                        movieView.showMovieDetail(movieResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        handleError(e);
                    }

                    @Override
                    public void onComplete() {
                        // Log to analytics movie detail consumed
                    }
                });
    }

    private void handleError(Throwable e) {
        if(e instanceof UnknownHostException) {
            movieView.showError("Network is disabled");
        } else if(e instanceof HttpException) {
            handleHttpError((HttpException)e);
        } else {
            movieView.showError(e.getMessage());
        }
    }

    private void handleHttpError(HttpException e) {
        movieView.showError(stringManager.getErrorMessage(e.code()));
    }

}
