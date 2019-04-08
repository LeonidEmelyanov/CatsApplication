package ru.demo.catapplication.mvvm.list;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BaseViewTypes {
    private static final int NO_LAYOUT = -1;
    private static final int NO_VIEW_TYPE = -1;

    private final int mViewModelVariableId;
    private final List<Pair<Class<?>, Integer>> mStorage = new ArrayList<>();

    public BaseViewTypes(int viewModelVariableId) {
        mViewModelVariableId = viewModelVariableId;
    }

    public BaseViewTypes add(@NonNull Class<?> clazz, @LayoutRes int layoutId) {
        mStorage.add(new Pair<>(clazz, layoutId));
        return this;
    }

    public int getViewModelVariableId() {
        return mViewModelVariableId;
    }

    @LayoutRes
    public final int getLayoutId(int viewType) {
        if (viewType >= 0 && viewType < mStorage.size()) {
            return mStorage.get(viewType).second;
        }
        return NO_LAYOUT;
    }

    public final int getViewType(@NonNull Class<?> clazz) {
        for (int i = 0; i < mStorage.size(); i++) {
            if (mStorage.get(i).first.equals(clazz)) {
                return i;
            }
        }
        return NO_VIEW_TYPE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BaseViewTypes that = (BaseViewTypes) o;
        return mViewModelVariableId == that.mViewModelVariableId &&
                Objects.equals(mStorage, that.mStorage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mViewModelVariableId, mStorage);
    }

    @Override
    public String toString() {
        return "BaseViewTypes{" +
                "mViewModelVariableId=" + mViewModelVariableId +
                ", mStorage=" + mStorage +
                '}';
    }
}
