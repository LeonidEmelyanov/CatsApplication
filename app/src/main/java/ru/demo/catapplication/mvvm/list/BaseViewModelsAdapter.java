package ru.demo.catapplication.mvvm.list;

import android.arch.lifecycle.ViewModel;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Базовый адаптер для списков {@link android.arch.lifecycle.ViewModel}
 * <br>
 * Для корректной работы ему необходим класс для сопоставления типов элементов и их разметок BaseViewTypes
 *
 * @author Leonid Emelyanov
 */
public class BaseViewModelsAdapter extends ListAdapter<ViewModel, BaseViewModelHolder> {
    private final BaseViewTypes mViewTypes;

    /**
     * Конструктор, который устанавливает DiffUtil.ItemCallback по умолчанию
     *
     * @param viewTypes класс для сопоставления типов вью моделей и их разметок
     */
    public BaseViewModelsAdapter(@NonNull BaseViewTypes viewTypes) {
        this(viewTypes, new BaseDiffUtilItemCallback<>());
    }

    /**
     * Конструктор, позволяющий максимально кастомизировать поведение при анализе различий списков элементов,
     * например при изменении первого элемента можно прокрутить его в начало списка
     *
     * @param viewTypes    класс для сопоставления типов вью моделей и их разметок
     * @param diffCallback коллбэк для обработки различий списков
     */
    public BaseViewModelsAdapter(@NonNull BaseViewTypes viewTypes,
                                 @NonNull DiffUtil.ItemCallback<ViewModel> diffCallback) {
        super(diffCallback);
        mViewTypes = viewTypes;
    }

    @NonNull
    @Override
    public BaseViewModelHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new BaseViewModelHolder(inflateViewHolder(viewGroup, mViewTypes.getLayoutId(viewType)));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewModelHolder viewHolder, int position) {
        viewHolder.setViewModel(mViewTypes.getViewModelVariableId(), getItem(position));
        viewHolder.updateBindings();
    }

    @Override
    public int getItemViewType(int position) {
        return mViewTypes.getViewType(getItem(position).getClass());
    }

    private View inflateViewHolder(@NonNull ViewGroup parent,
                                   @LayoutRes int layoutId) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(inflater,
                layoutId,
                parent,
                false);

        if (binding == null) {
            return inflater.inflate(layoutId, parent, false);
        }
        return binding.getRoot();
    }
}
