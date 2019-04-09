package ru.demo.catapplication.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.demo.catapplication.data.CatModel;
import ru.demo.catapplication.mvvm.SingleLiveEvent;

public class BaseCatViewModel extends ViewModel {
    private final CatModel mModel;
    private final SingleLiveEvent<CatModel> mClickEvent;

    public BaseCatViewModel(@NonNull CatModel model,
                            @Nullable SingleLiveEvent<CatModel> clickEvent) {
        mModel = model;
        mClickEvent = clickEvent;
    }

    public String getName() {
        return mModel.getName();
    }

    public String getDescription() {
        return mModel.getDescription();
    }

    public void onClick() {
        if (mClickEvent != null) {
            mClickEvent.setValue(mModel);
        }
    }
}
