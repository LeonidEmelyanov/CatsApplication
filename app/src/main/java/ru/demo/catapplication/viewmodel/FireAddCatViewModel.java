package ru.demo.catapplication.viewmodel;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.demo.catapplication.R;
import ru.demo.catapplication.data.CatModel;
import ru.demo.catapplication.mvvm.SingleLiveEvent;

public class FireAddCatViewModel extends BaseCatViewModel {
    public FireAddCatViewModel(@NonNull CatModel model,
                               @Nullable SingleLiveEvent<CatModel> clickEvent) {
        super(model, clickEvent);
    }

    public int getGif() {
        return R.raw.fire_ass_cat;
    }
}
