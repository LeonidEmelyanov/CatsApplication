package ru.demo.catapplication.mvvm.list;

import android.arch.lifecycle.ViewModel;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Базовый вью холдер для отображения вью моделей
 * <br>
 * Используется в {@link BaseViewModelsAdapter}
 *
 * @author Leonid Emelyanov
 */
public class BaseViewModelHolder extends RecyclerView.ViewHolder {
    private final ViewDataBinding mBinding;

    public BaseViewModelHolder(@NonNull View itemView) {
        super(itemView);
        mBinding = DataBindingUtil.getBinding(itemView);
    }

    /**
     * Установка вью модели для вью холдера
     *
     * @param variableId идентификатор имения переменной вью модели в xml, например {@code BR.viewModel}
     * @param viewModel  вью модель
     */
    public void setViewModel(int variableId, ViewModel viewModel) {
        if (mBinding != null) {
            mBinding.setVariable(variableId, viewModel);
            mBinding.executePendingBindings();
        }
    }

    /**
     * Обновление отображения при обновлении вью холдера
     */
    public void updateBindings() {
        if (mBinding != null) {
            mBinding.executePendingBindings();
        }
    }
}
