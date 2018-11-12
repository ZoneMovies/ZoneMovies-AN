package com.mac.zonemovies.view.home;

import com.mac.zonemovies.data.remote.movieapi.to.common.Result;

import java.util.List;

public interface HomeContract {

    interface View {

        void showNowShowingMovies(List<Result> nowShowingMovies );

        void showPopularMovies(List<Result> popularMovies);

        void showUpcomingMovies(List<Result> upcomingMovies);

        void navigateToMovie(int movieId);
    }


    interface Presenter {

        void getNowShowingMovies();

        void getPopularMovies();

        void getUpcomingMovies();

    }

}
