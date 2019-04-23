package ru.demo.catapplication.view;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.demo.catapplication.R;
import ru.demo.catapplication.databinding.FragmentMvvmBinding;

public class MvvmFragment extends HomeFragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentMvvmBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_mvvm,
                container,
                false);
        binding.setViewModel(mViewModel);
        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }
}
