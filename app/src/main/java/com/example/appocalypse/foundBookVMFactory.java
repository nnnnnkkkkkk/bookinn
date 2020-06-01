package com.example.appocalypse;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class foundBookVMFactory extends ViewModelProvider.NewInstanceFactory {
    private Application application;
    private FragmentManager fragmentManager;
    private String query;

    public foundBookVMFactory(Application application, FragmentManager fragmentManager, String query) {
        this.application = application;
        this.fragmentManager = fragmentManager;
        this.query = query;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == FoundBookViewModel.class) {
            return (T) new FoundBookViewModel(application, fragmentManager, query);
        }
        return null;
    }
}
