package com.mac.zonemovies.util.resources;

import android.content.Context;

import com.mac.zonemovies.R;

public class StringManager {

    final Context context;

    public StringManager(Context context) {
        this.context = context;
    }

    public String getErrorMessage(int errorCode) {
        switch (errorCode) {
            case 404:
                return context.getResources().getString(R.string.lbl_not_found);
            default:
                return context.getResources().getString(R.string.lbl_error);
        }
    }

}
