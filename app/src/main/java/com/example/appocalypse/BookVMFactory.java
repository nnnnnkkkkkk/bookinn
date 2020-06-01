package com.example.appocalypse;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class BookVMFactory extends ViewModelProvider.NewInstanceFactory {
    private Application application;
    private FragmentManager fragmentManager;

    public BookVMFactory(Application application, FragmentManager fragmentManager) {
        this.application = application;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == BookViewModel.class) {
            return (T) new BookViewModel(application, fragmentManager);
        }
        return null;
    }
}
