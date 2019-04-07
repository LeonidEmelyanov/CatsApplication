package ru.demo.catapplication.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.demo.catapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MvvmFragment extends Fragment {

    public MvvmFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mvvm, container, false);
    }
}
