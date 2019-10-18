package ru.demo.catapplication.viewmodel;

import android.arch.lifecycle.ViewModel;

public abstract class BaseItemViewModel extends ViewModel {
    public static final int NO_VARIABLE_ID = -1;

    public abstract int getVariableId();
}
