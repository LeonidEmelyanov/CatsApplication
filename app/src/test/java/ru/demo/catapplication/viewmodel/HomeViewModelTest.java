package ru.demo.catapplication.viewmodel;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;

import com.android.databinding.library.baseAdapters.BR;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import io.reactivex.Single;
import ru.demo.catapplication.R;
import ru.demo.catapplication.core.RxSchedulersStub;
import ru.demo.catapplication.data.CatModel;
import ru.demo.catapplication.data.CatType;
import ru.demo.catapplication.domain.CatsInteractor;
import ru.demo.catapplication.mvvm.list.BaseViewTypes;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HomeViewModelTest {
    private HomeViewModel mViewModel;

    @Rule
    public InstantTaskExecutorRule mRule = new InstantTaskExecutorRule();

    @Mock
    CatsInteractor mInteractor;

    @Mock
    Observer<List<ViewModel>> mCatsObserver;

    @Mock
    Observer<Boolean> mLoadingObserver;

    @Mock
    Observer<String> mErrorObserver;

    @Mock
    Observer<CatModel> mClickEventObserver;

    @Before
    public void setUp() {
        when(mInteractor.getCats())
                .thenReturn(Single.just(Collections.singletonList(getModel())));

        mViewModel = new HomeViewModel(new RxSchedulersStub(), mInteractor);

        mViewModel.getCats().observeForever(mCatsObserver);
        mViewModel.getLoading().observeForever(mLoadingObserver);
        mViewModel.getError().observeForever(mErrorObserver);
        mViewModel.catClickEvent().observeForever(mClickEventObserver);
    }

    @Test
    public void getCatTypes() {
        assertThat(mViewModel.getCatTypes(), is(new BaseViewTypes(BR.viewModel)
                .add(PusheenViewModel.class, R.layout.pusheen_layout)
                .add(UnicorViewModel.class, R.layout.unicorn_layout)
                .add(PixelCatViewModel.class, R.layout.pixel_cat_layout)
                .add(FireAddCatViewModel.class, R.layout.fire_ass_cat_layout)));
    }

    @Test
    public void getCats() {
        verify(mCatsObserver).onChanged(
                Collections.singletonList(
                        new PusheenViewModel(getModel(), mViewModel.mCatClickEvent)
                )
        );
    }

    @Test
    public void getLoading() {
        verify(mLoadingObserver).onChanged(false);
    }

    @Test
    public void getError() {
        when(mInteractor.getCats())
                .thenReturn(Single.error(new Exception("Message")));
        mViewModel.loadingCats();

        verify(mErrorObserver).onChanged("Message");
    }

    @Test
    public void catClickEvent() {
        ((PusheenViewModel) mViewModel.getCats().getValue().get(0)).onClick();

        verify(mClickEventObserver).onChanged(getModel());
    }

    private CatModel getModel() {
        return new CatModel(CatType.PUSHEEN, "Pusheen", "This is cat!");
    }
}