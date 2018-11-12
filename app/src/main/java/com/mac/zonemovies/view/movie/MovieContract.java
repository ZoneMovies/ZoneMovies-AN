package com.mac.zonemovies.view.movie;

import com.mac.zonemovies.base.BaseView;
import com.mac.zonemovies.data.remote.movieapi.to.credits.Cast;
import com.mac.zonemovies.data.remote.movieapi.to.movie.MovieResponse;

import java.util.List;

public interface MovieContract {

    interface View extends BaseView {

        void showMovieDetail(MovieResponse movie);

        void showVideo(String videoID);

        void showMovieCredits(List<Cast> cast);

    }


    interface Presenter {

        void getMovie(int movieId);

        void getMovieVideos(int movieId);

        void getMovieCredits(int movieId);

    }

}
