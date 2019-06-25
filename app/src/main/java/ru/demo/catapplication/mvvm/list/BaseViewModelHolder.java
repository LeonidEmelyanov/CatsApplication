package ru.demo.catapplication.mvvm.list;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModel;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class BaseViewModelHolder extends RecyclerView.ViewHolder {
    private final ViewDataBinding mBinding;

    public BaseViewModelHolder(@NonNull View itemView) {
        super(itemView);
        mBinding = DataBindingUtil.getBinding(itemView);
    }

    public void setViewModel(int variableId, @Nullable ViewModel viewModel) {
        if (mBinding != null) {
            mBinding.setVariable(variableId, viewModel);
        }
    }

    public void setLifeCycleOwner(@NonNull LifecycleOwner lifeCycleOwner) {
        if (mBinding != null) {
            mBinding.setLifecycleOwner(lifeCycleOwner);
        }
    }

    public void updateBindings() {
        if (mBinding != null) {
            mBinding.executePendingBindings();
        }
    }
}
