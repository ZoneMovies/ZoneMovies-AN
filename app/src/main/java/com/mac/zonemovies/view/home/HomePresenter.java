package com.mac.zonemovies.view.home;

import com.mac.zonemovies.data.remote.movieapi.MovieService;
import com.mac.zonemovies.data.remote.movieapi.to.showing.NowShowingResponse;

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
                .subscribe(new Observer<NowShowingResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // left blank
                    }

                    @Override
                    public void onNext(NowShowingResponse nowShowingResponse) {
                        // log analytics request
                        homeView.showMovies(nowShowingResponse.getResults());
                    }

                    @Override
                    public void onError(Throwable e) {
                        // log productive error
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        // log to analytics success
                    }
                });
    }

}
