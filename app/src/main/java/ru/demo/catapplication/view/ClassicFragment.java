package ru.demo.catapplication.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.demo.catapplication.R;
import ru.demo.catapplication.mvvm.list.BaseViewModelsAdapter;

public class ClassicFragment extends HomeFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_classic, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BaseViewModelsAdapter adapter = new BaseViewModelsAdapter(mViewModel.getCatTypes());

        view.<RecyclerView>findViewById(R.id.recycle_view)
                .setAdapter(adapter);
        view.<SwipeRefreshLayout>findViewById(R.id.swipe_refresh_layout)
                .setOnRefreshListener(() -> mViewModel.loadingCats());

        mViewModel.getCats().observe(this, cats ->
                adapter.submitList(cats));

        mViewModel.getLoading().observe(this, loading ->
                view.<SwipeRefreshLayout>findViewById(R.id.swipe_refresh_layout)
                        .setRefreshing(loading != null && loading));
    }
}
