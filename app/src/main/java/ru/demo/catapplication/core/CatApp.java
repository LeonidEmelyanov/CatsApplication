package ru.demo.catapplication.core;

import android.app.Application;

import ru.demo.catapplication.di.CatComponent;
import ru.demo.catapplication.di.CatModule;
import ru.demo.catapplication.di.DaggerCatComponent;

public class CatApp extends Application {
    private CatComponent mCatComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mCatComponent = DaggerCatComponent
                .builder()
                .catModule(new CatModule())
                .build();
    }

    public CatComponent getCatComponent() {
        return mCatComponent;
    }
}
