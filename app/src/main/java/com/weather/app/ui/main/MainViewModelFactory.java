package com.weather.app.ui.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.Objects;

public class MainViewModelFactory implements ViewModelProvider.Factory {
    private final Application application;

    public MainViewModelFactory(Application application) {
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return Objects.requireNonNull(modelClass.cast(new MainViewModel(application)));
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}