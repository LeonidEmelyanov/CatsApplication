package ru.demo.catapplication.mvvm.list;

import android.arch.lifecycle.LifecycleOwner;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import ru.demo.catapplication.viewmodel.BaseItemViewModel;

public class BaseViewModelHolder extends RecyclerView.ViewHolder {
    private final ViewDataBinding mBinding;

    public BaseViewModelHolder(@NonNull View itemView) {
        super(itemView);
        mBinding = DataBindingUtil.getBinding(itemView);
    }

    public void setViewModel(@NonNull BaseItemViewModel viewModel) {
        if (mBinding != null) {
            mBinding.setVariable(viewModel.getVariableId(), viewModel);
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
