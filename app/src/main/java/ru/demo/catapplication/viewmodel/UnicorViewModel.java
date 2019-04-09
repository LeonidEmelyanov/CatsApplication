package ru.demo.catapplication.viewmodel;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.demo.catapplication.data.CatModel;
import ru.demo.catapplication.mvvm.SingleLiveEvent;

public class UnicorViewModel extends BaseCatViewModel {

    public UnicorViewModel(@NonNull CatModel model,
                           @Nullable SingleLiveEvent<CatModel> clickEvent) {
        super(model, clickEvent);
    }
}
