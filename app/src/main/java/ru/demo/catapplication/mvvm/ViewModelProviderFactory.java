package ru.demo.catapplication.mvvm;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Consumer;

import java.util.function.Supplier;

public class ViewModelProviderFactory<VM extends ViewModel> implements ViewModelProvider.Factory {
    private final Supplier<VM> mViewModelCreator;
    private final Consumer<VM> mOnCreateHook;

    /**
     * Конструктор фабрики, который в случае необходимости получает экзмепляр вью модели через коллбэк
     *
     * @param viewModelCreator коллбэк для получения экземпляра вью модели
     */
    public ViewModelProviderFactory(@NonNull Supplier<VM> viewModelCreator) {
        this(viewModelCreator, null);
    }

    /**
     * Конструктор фабрики, который в случае необходимости получает экзмепляр вью модели через коллбэк
     * и вызовет указанную лямбду сразу после создания экземпляра
     *
     * @param viewModelCreator коллбэк для получения экземпляра вью модели
     * @param onCreateHook     хук для выполнения каких-либо действий после создания вью модели
     */
    public ViewModelProviderFactory(@NonNull Supplier<VM> viewModelCreator,
                                    @Nullable Consumer<VM> onCreateHook) {
        mViewModelCreator = viewModelCreator;
        mOnCreateHook = onCreateHook;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        VM viewModel = mViewModelCreator.get();

        if (mOnCreateHook != null) {
            mOnCreateHook.accept(viewModel);
        }
        //noinspection unchecked
        return (T) viewModel;
    }
}
