package ru.demo.catapplication.mvvm.list;

import android.arch.lifecycle.LifecycleOwner;
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

public class BaseViewModelsAdapter extends ListAdapter<ViewModel, BaseViewModelHolder> {
    private final LifecycleOwner mLifecycleOwner;
    private final BaseViewTypes mViewTypes;

    public BaseViewModelsAdapter(@NonNull LifecycleOwner lifecycleOwner,
                                 @NonNull BaseViewTypes viewTypes) {
        this(lifecycleOwner, viewTypes, new BaseDiffUtilItemCallback<>());
    }

    public BaseViewModelsAdapter(@NonNull LifecycleOwner lifecycleOwner,
                                 @NonNull BaseViewTypes viewTypes,
                                 @NonNull DiffUtil.ItemCallback<ViewModel> diffCallback) {
        super(diffCallback);
        mLifecycleOwner = lifecycleOwner;
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
        viewHolder.setLifeCycleOwner(mLifecycleOwner);
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
