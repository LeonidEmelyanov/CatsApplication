package ru.demo.catapplication.di;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.demo.catapplication.core.IRxSchedulers;
import ru.demo.catapplication.core.RxSchedulersImpl;
import ru.demo.catapplication.domain.CatsInteractor;
import ru.demo.catapplication.mvvm.ViewModelProviderFactory;
import ru.demo.catapplication.viewmodel.HomeViewModel;

@Module
public class CatModule {

    @Singleton
    @Provides
    static IRxSchedulers provideRxSchedulers() {
        return new RxSchedulersImpl();
    }

    @Singleton
    @Provides
    static CatsInteractor provideCatsInteractor() {
        return new CatsInteractor();
    }

    @Provides
    static ViewModelProviderFactory<HomeViewModel> provideHomeProviderFactory(@NonNull IRxSchedulers rxSchedulers,
                                                                              @NonNull CatsInteractor interactor) {
        return new ViewModelProviderFactory<>(() -> new HomeViewModel(rxSchedulers, interactor));
    }
}
