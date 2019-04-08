package ru.demo.catapplication.core;

import android.support.annotation.NonNull;

import io.reactivex.Scheduler;

public interface IRxSchedulers {

    @NonNull
    Scheduler getMainThreadScheduler();

    @NonNull
    Scheduler getIOScheduler();

    @NonNull
    Scheduler getComputationScheduler();
}
