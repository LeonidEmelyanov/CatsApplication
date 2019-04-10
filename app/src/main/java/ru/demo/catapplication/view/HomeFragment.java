package ru.demo.catapplication.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;

import ru.demo.catapplication.core.CatApp;
import ru.demo.catapplication.di.CatComponent;
import ru.demo.catapplication.viewmodel.HomeViewModel;

public class HomeFragment extends Fragment {
    HomeViewModel mViewModel;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        CatComponent component = ((CatApp) context.getApplicationContext()).getCatComponent();
        mViewModel = ViewModelProviders.of(this, component.getHomeProvideFactory()).get(HomeViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mViewModel.catClickEvent().observe(this, cat -> {
            if (cat != null) {
                Snackbar.make(view, cat.getName() + " clicked!", Snackbar.LENGTH_SHORT).show();
            }
        });
        mViewModel.getError().observe(this, error -> {
            if (error != null) {
                Snackbar.make(view, error, Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
