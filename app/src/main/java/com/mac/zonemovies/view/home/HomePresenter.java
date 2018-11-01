package com.mac.zonemovies.view.home;

import com.mac.zonemovies.data.remote.movieapi.MovieService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter implements HomeContract.Presenter {

    @Inject
    MovieService movieService;

    private final HomeContract.View homeView;
    private List<String> movies;

    @Inject
    public HomePresenter(HomeContract.View homeView) {
        this.homeView = homeView;
        movies = new ArrayList<>();
    }

    @Override
    public void getMovies() {
        movieService.getMovieList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // this is added since rxjava
                    }

                    @Override
                    public void onNext(List<String> strings) {
                        homeView.showMovies(strings);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        /// log to crash report
                    }

                    @Override
                    public void onComplete() {
                        // log to analytics success
                    }
                });
    }
}
