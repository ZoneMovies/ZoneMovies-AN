package com.mac.zonemovies.util.network;

import com.mac.zonemovies.data.remote.movieapi.MovieAPI;
import com.mac.zonemovies.data.remote.otherapi.OtherAPI;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    GsonConverterFactory provideGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    RxJava2CallAdapterFactory provideRxJava2CallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    HttpLoggingInterceptor providesHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    OkHttpClient providesOkHttpClient(HttpLoggingInterceptor interceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }

    @Provides
    @Named("movies")
    Retrofit provideMovieRetrofit(GsonConverterFactory gsonFactory,
                             RxJava2CallAdapterFactory rxJavaFactory,
                             OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(MovieAPI.BASE_URL)
                .addConverterFactory(gsonFactory)
                .addCallAdapterFactory(rxJavaFactory)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Named("other")
    Retrofit provideRetrofit(GsonConverterFactory gsonFactory,
                             RxJava2CallAdapterFactory rxJavaFactory,
                             OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(OtherAPI.BASE_URL)
                .addConverterFactory(gsonFactory)
                .addCallAdapterFactory(rxJavaFactory)
                .client(okHttpClient)
                .build();
    }

}
