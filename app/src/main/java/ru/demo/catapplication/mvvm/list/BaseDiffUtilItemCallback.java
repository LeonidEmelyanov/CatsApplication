package ru.demo.catapplication.mvvm.list;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

public class BaseDiffUtilItemCallback<T extends ViewModel> extends DiffUtil.ItemCallback<T> {
    @Override
    public boolean areItemsTheSame(@NonNull T oldItem, @NonNull T newItem) {
        return oldItem == newItem;
    }

    @Override
    public boolean areContentsTheSame(@NonNull T oldItem, @NonNull T newItem) {
        return oldItem.equals(newItem);
    }
}
