package com.example.appocalypse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.example.appocalypse.databinding.ActivityLibraryBinding;
import com.example.appocalypse.databinding.ActivitySearchBinding;

public class LibraryActivity extends AppCompatActivity {
    private BookViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this, new BookVMFactory(getApplication(),
                getSupportFragmentManager())).get(BookViewModel.class);
        ActivityLibraryBinding binding = DataBindingUtil.setContentView(this,
                R.layout.activity_library);

        binding.setVm(viewModel);
        binding.setLifecycleOwner(this);
    }
}
