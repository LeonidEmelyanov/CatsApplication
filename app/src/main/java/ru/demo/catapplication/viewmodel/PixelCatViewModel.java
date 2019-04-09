package ru.demo.catapplication.viewmodel;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.demo.catapplication.data.CatModel;
import ru.demo.catapplication.mvvm.SingleLiveEvent;

public class PixelCatViewModel extends BaseCatViewModel {

    public PixelCatViewModel(@NonNull CatModel model,
                             @Nullable SingleLiveEvent<CatModel> clickEvent) {
        super(model, clickEvent);
    }
}
