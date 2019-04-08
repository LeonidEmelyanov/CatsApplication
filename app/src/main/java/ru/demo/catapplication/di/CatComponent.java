package ru.demo.catapplication.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.demo.catapplication.core.IRxSchedulers;
import ru.demo.catapplication.domain.CatsInteractor;
import ru.demo.catapplication.mvvm.ViewModelProviderFactory;
import ru.demo.catapplication.viewmodel.HomeViewModel;

@Singleton
@Component(modules = CatModule.class)
public interface CatComponent {

    IRxSchedulers getRxSchedulers();

    CatsInteractor getCatsInteractor();

    ViewModelProviderFactory<HomeViewModel> getHomeProvideFactory();
}
