package com.mac.zonemovies.view.trailer;

import com.mac.zonemovies.base.BaseView;

/**
 * Defines the MVP contract for the Trailer feature.
 */
public interface TrailerContract {

    /**
     * View defines the contract methods the TrailerContract.View class would need to implement
     * concretely.
     */
    interface View extends BaseView {

        void showVideo(String videoUrl);

    }

    /**
     * Presenter defines the contract methods the TrailerContract.View class would need to implement
     * concretely.
     */
    interface Presenter {

        /**
         * Retrieves the available movie videos from the API using a given Movie ID
         * @param movieId {@link int} movieID the ID that identifies a movie uniquely
         */
        void getMovieVideos(int movieId);

        /**
         * Loads the given video URL into the View
         * @param videoUrl
         */
        void loadMovieVideo(String videoUrl);

    }

}
