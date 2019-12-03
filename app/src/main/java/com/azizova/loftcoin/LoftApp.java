package com.azizova.loftcoin;

import android.app.Application;
import android.os.StrictMode;
import android.util.Log;

import com.azizova.loftcoin.log.LoftTree;

import java.util.Timer;

import timber.log.Timber;

public class LoftApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            StrictMode.enableDefaults();
            Timber.plant(new LoftTree());
        }
    }


}
