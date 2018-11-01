package com.mac.zonemovies.view.home;

import com.mac.zonemovies.data.remote.movieapi.to.Result;

import java.util.List;

public interface HomeContract {

    interface View {
        // TODO - Define view responsibility
        void showMovies(List<Result> results );
    }


    interface Presenter {
        //TODO - Define presenter responsibility
        void getMovies();
    }

}
