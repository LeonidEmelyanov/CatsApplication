package ru.demo.catapplication.mvvm.list;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.content.ContextWrapper;
import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import com.cunoraz.gifview.library.GifView;
import ru.demo.catapplication.viewmodel.BaseItemViewModel;

import java.util.Collections;
import java.util.List;

public final class ListBindingAdapters {

    @BindingAdapter({"viewTypes", "items"})
    public static void setAdapterViewModels(@NonNull RecyclerView recyclerView,
                                            @NonNull BaseViewTypes viewTypes,
                                            @Nullable List<BaseItemViewModel> items) {
        BaseViewModelsAdapter adapter = (BaseViewModelsAdapter) recyclerView.getAdapter();

        if (adapter == null) {
            recyclerView.setAdapter(adapter =
                    new BaseViewModelsAdapter(getLifecycleOwner(recyclerView.getContext()), viewTypes));
        }
        adapter.submitList(items != null ? items : Collections.emptyList());
    }

    @BindingAdapter("gif")
    public static void playGif(@NonNull GifView gifView,
                               @Nullable Integer gif) {
        if (gif != null) {
            gifView.setGifResource(gif);
            gifView.play();
        }
    }

    private static LifecycleOwner getLifecycleOwner(Context context) {
        return context instanceof LifecycleOwner
                ? (LifecycleOwner) context
                : getLifecycleOwner(((ContextWrapper) context).getBaseContext());
    }
}
