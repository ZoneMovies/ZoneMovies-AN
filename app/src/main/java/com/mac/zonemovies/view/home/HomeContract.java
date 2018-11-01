package com.mac.zonemovies.view.home;

import java.util.List;

public interface HomeContract {

    interface View {
        // TODO - Define view responsibility
        void showMovies(List<String> movies);
    }


    interface Presenter {
        //TODO - Define presenter responsibility
        void getMovies();
    }

}
