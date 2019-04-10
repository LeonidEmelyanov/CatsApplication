package ru.demo.catapplication.core;

import android.support.annotation.NonNull;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class RxSchedulersStub implements IRxSchedulers {
    @Override
    @NonNull
    public Scheduler getMainThreadScheduler() {
        return Schedulers.trampoline();
    }

    @Override
    @NonNull
    public Scheduler getIOScheduler() {
        return Schedulers.trampoline();
    }

    @NonNull
    @Override
    public Scheduler getComputationScheduler() {
        return Schedulers.trampoline();
    }
}
