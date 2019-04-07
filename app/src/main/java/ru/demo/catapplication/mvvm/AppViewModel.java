package ru.demo.catapplication.mvvm;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.CallSuper;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Базовый класс {@link ViewModel} для приложения.
 */
public class AppViewModel extends ViewModel {

    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    /**
     * Возвращает {@link CompositeDisposable}, который очистит ссылки на {@link #onCleared()}.
     *
     * @return {@link CompositeDisposable}
     */
    public CompositeDisposable getRxCompositeDisposable() {
        return mCompositeDisposable;
    }

    @Override
    @CallSuper
    protected void onCleared() {
        super.onCleared();
        mCompositeDisposable.dispose();
    }
}
