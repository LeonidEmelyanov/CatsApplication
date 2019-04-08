package ru.demo.catapplication.viewmodel;

import android.arch.lifecycle.ViewModel;

import ru.demo.catapplication.data.CatModel;

public class BaseCatViewModel extends ViewModel {
    private final CatModel mModel;

    public BaseCatViewModel(CatModel model) {
        mModel = model;
    }

    public String getName() {
        return mModel.getName();
    }

    public String getDescription() {
        return mModel.getDescription();
    }
}
