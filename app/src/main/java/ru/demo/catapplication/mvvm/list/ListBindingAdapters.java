package ru.demo.catapplication.mvvm.list;

import android.arch.lifecycle.ViewModel;
import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.Collections;
import java.util.List;

/**
 * Набор биндинг адаптеров для работы со списками вью моделей
 *
 * @author Emelyanov Leonid
 */
public final class ListBindingAdapters {

    /**
     * Установить класс соответствий классов элементов и их id разметок и список элементов
     * <br>
     * Если у списка ещё нет адаптера, ему будет установлен {@link BaseViewModelsAdapter}
     * <br>
     * Типичное использование:
     * <br>
     * <pre>
     *     &lt;RecycleView&gt;
     *         viewTypes={viewModel.viewTypes}
     *         items={viewModel.items}
     *     &lt;/RecycleView&gt;
     * </pre>
     *
     * @param recyclerView вью списка
     * @param viewTypes    класс соответствий классов элементов и их разметок
     * @param items        список элементов, может быть null
     */
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

    /**
     * Установить видимость элемента
     *
     * @param view      вью
     * @param isVisible видим ли элемент
     */
    @BindingAdapter("visibility")
    public static void setVisibility(@NonNull View view,
                                     @Nullable Boolean isVisible) {
        view.setVisibility(isVisible != null && isVisible
                ? View.VISIBLE
                : View.GONE);
    }
}
