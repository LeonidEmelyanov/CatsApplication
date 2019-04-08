package ru.demo.catapplication.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.android.databinding.library.baseAdapters.BR;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import ru.demo.catapplication.R;
import ru.demo.catapplication.core.IRxSchedulers;
import ru.demo.catapplication.data.CatModel;
import ru.demo.catapplication.domain.CatsInteractor;
import ru.demo.catapplication.mvvm.SingleLiveEvent;
import ru.demo.catapplication.mvvm.list.BaseViewTypes;

public class HomeViewModel extends ViewModel {
    private final CompositeDisposable mDisposable = new CompositeDisposable();

    private final BaseViewTypes mCatTypes =
            new BaseViewTypes(BR.viewModel)
                    .add(PusheenViewModel.class, R.layout.pusheen_layout)
                    .add(UnicorViewModel.class, R.layout.unicorn_layout)
                    .add(PixelCatViewModel.class, R.layout.pixel_cat_layout);

    private final MutableLiveData<List<ViewModel>> mCats = new MutableLiveData<>();

    private final MutableLiveData<Boolean> mLoading = new MutableLiveData<>();

    private final MutableLiveData<String> mError = new MutableLiveData<>();

    private final SingleLiveEvent<CatModel> mCatClickListener = new SingleLiveEvent<>();

    private final IRxSchedulers mRxSchedulers;
    private final CatsInteractor mCatsInteractor;

    public HomeViewModel(@NonNull IRxSchedulers rxSchedulers,
                         @NonNull CatsInteractor catsInteractor) {
        mRxSchedulers = rxSchedulers;
        mCatsInteractor = catsInteractor;

        loadingCats();
    }

    @NonNull
    public BaseViewTypes getCatTypes() {
        return mCatTypes;
    }

    @NonNull
    public LiveData<List<ViewModel>> getCats() {
        return mCats;
    }

    @NonNull
    public MutableLiveData<Boolean> getLoading() {
        return mLoading;
    }

    @NonNull
    public MutableLiveData<String> getError() {
        return mError;
    }

    @NonNull
    public LiveData<CatModel> catClickListener() {
        return mCatClickListener;
    }

    private void loadingCats() {
        mDisposable.add(
                mCatsInteractor.getCats()
                        .subscribeOn(mRxSchedulers.getIOScheduler())
                        .subscribe(
                                cats -> mCats.postValue(getCatViewModels(cats)),
                                error -> mError.postValue(error.getMessage()))
        );
    }

    private List<ViewModel> getCatViewModels(@NonNull List<CatModel> catModels) {
        List<ViewModel> cats = new ArrayList<>();

        for (CatModel cat : catModels) {
            switch (cat.getType()) {
                case PUSHEEN:
                    cats.add(new PusheenViewModel(cat));
                    break;
                case UNICORN:
                    cats.add(new UnicorViewModel(cat));
                    break;
                case PIXELS:
                    cats.add(new PixelCatViewModel(cat));
                    break;
            }
        }
        return cats;
    }

    @Override
    protected void onCleared() {
        mDisposable.dispose();
    }
}
