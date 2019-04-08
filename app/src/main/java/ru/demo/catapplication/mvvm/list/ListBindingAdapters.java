package ru.demo.catapplication.mvvm.list;

import android.arch.lifecycle.ViewModel;
import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public final class ListBindingAdapters {

    @BindingAdapter({"viewTypes", "items"})
    public static void setAdapterViewModels(@NonNull RecyclerView recyclerView,
                                            @NonNull BaseViewTypes viewTypes,
                                            @Nullable List<ViewModel> items) {
        BaseViewModelsAdapter adapter = (BaseViewModelsAdapter) recyclerView.getAdapter();

        if (adapter == null) {
            recyclerView.setAdapter(adapter = new BaseViewModelsAdapter(viewTypes));
        }
        adapter.submitList(items != null ? items : Collections.emptyList());
    }
}