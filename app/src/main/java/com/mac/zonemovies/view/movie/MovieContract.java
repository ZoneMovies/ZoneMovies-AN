package com.mac.zonemovies.view.movie;

import com.mac.zonemovies.data.remote.movieapi.to.MovieResponse;

public interface MovieContract {

    interface View {

        void showMovieDetail(MovieResponse movie);

        void showVideo(String videoID);

        void showError(String message);
    }


    interface Presenter {

        void getMovie(int movieId);

        void getMovieVideos(int movieId);

    }

}
