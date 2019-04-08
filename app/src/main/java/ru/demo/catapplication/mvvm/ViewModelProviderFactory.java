package ru.demo.catapplication.mvvm;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import java.util.function.Supplier;

public class ViewModelProviderFactory<VM extends ViewModel> implements ViewModelProvider.Factory {
    private final Supplier<VM> mViewModelCreator;

    /**
     * Конструктор фабрики, который в случае необходимости получает экзмепляр вью модели через коллбэк
     *
     * @param viewModelCreator коллбэк для получения экземпляра вью модели
     */
    public ViewModelProviderFactory(@NonNull Supplier<VM> viewModelCreator) {
        mViewModelCreator = viewModelCreator;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) mViewModelCreator.get();
    }
}
