package com.mac.zonemovies.view.movie;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Singleton;

@Documented
@Singleton
@Retention(RetentionPolicy.RUNTIME)
public @interface MovieScope {
}
