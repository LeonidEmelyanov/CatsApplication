package ru.demo.catapplication.mvvm.list;

import android.arch.lifecycle.ViewModel;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class BaseViewModelHolder extends RecyclerView.ViewHolder {
    private final ViewDataBinding mBinding;

    public BaseViewModelHolder(@NonNull View itemView) {
        super(itemView);
        mBinding = DataBindingUtil.getBinding(itemView);
    }

    public void setViewModel(int variableId, ViewModel viewModel) {
        if (mBinding != null) {
            mBinding.setVariable(variableId, viewModel);
            mBinding.executePendingBindings();
        }
    }

    public void updateBindings() {
        if (mBinding != null) {
            mBinding.executePendingBindings();
        }
    }
}
