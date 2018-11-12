package com.mac.zonemovies.view.home;

import com.mac.zonemovies.data.remote.movieapi.MovieService;
import com.mac.zonemovies.data.remote.movieapi.to.common.Result;
import com.mac.zonemovies.data.remote.movieapi.to.popular.PopularMoviesResponse;
import com.mac.zonemovies.data.remote.movieapi.to.showing.NowShowingResponse;
import com.mac.zonemovies.data.remote.movieapi.to.upcoming.UpcomingResponse;

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

    @Inject
    public HomePresenter(HomeContract.View homeView) {
        this.homeView = homeView;
    }

    @Override
    public void getNowShowingMovies() {
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
                        homeView.showNowShowingMovies(nowShowingResponse.getResults());
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

    @Override
    public void getPopularMovies() {
        movieService.getPopularMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PopularMoviesResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //
                    }

                    @Override
                    public void onNext(PopularMoviesResponse popularMoviesResponse) {
                        homeView.showPopularMovies(popularMoviesResponse.getResults());
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        //
                    }
                });
    }

    @Override
    public void getUpcomingMovies() {
        movieService.getUpcomingMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpcomingResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //
                    }

                    @Override
                    public void onNext(UpcomingResponse upcomingResponse) {
                        List<Result> homeList = new ArrayList<>(3);
                        for (int i = 0; i < 3; i++) {
                            homeList.add(upcomingResponse.getResults().get(i));
                        }
                        homeView.showUpcomingMovies(homeList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        //
                    }
                });
    }

}
