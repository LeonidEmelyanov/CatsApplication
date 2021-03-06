package ru.demo.catapplication.viewmodel;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import ru.demo.catapplication.BR;
import ru.demo.catapplication.data.CatModel;
import ru.demo.catapplication.mvvm.SingleLiveEvent;

import java.util.Objects;

public class BaseCatViewModel extends BaseItemViewModel {
    private final CatModel mModel;
    private final SingleLiveEvent<CatModel> mClickEvent;

    protected BaseCatViewModel(@NonNull CatModel model,
                               @Nullable SingleLiveEvent<CatModel> clickEvent) {
        mModel = model;
        mClickEvent = clickEvent;
    }

    @Override
    public int getVariableId() {
        return BR.viewModel;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BaseCatViewModel that = (BaseCatViewModel) o;
        return Objects.equals(mModel, that.mModel) &&
                Objects.equals(mClickEvent, that.mClickEvent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mModel, mClickEvent);
    }

    @Override
    public String toString() {
        return "BaseCatViewModel{" +
                "mModel=" + mModel +
                ", mClickEvent=" + mClickEvent +
                '}';
    }
}
