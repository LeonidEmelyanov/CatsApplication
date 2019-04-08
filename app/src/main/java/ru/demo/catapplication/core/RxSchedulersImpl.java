package ru.demo.catapplication.core;

import android.support.annotation.NonNull;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxSchedulersImpl implements IRxSchedulers {

    @Override
    @NonNull
    public Scheduler getMainThreadScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Override
    @NonNull
    public Scheduler getIOScheduler() {
        return Schedulers.io();
    }

    @NonNull
    @Override
    public Scheduler getComputationScheduler() {
        return Schedulers.computation();
    }
}
