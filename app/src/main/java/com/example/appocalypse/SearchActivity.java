package com.example.appocalypse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.appocalypse.databinding.ActivitySearchBinding;

public class SearchActivity extends AppCompatActivity{
    private static final String TAG = "SearchActivity";
    private String query;
    private FoundBookViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        query = intent.getStringExtra("query");
        viewModel = ViewModelProviders.of(this, new foundBookVMFactory(
                getApplication(), getSupportFragmentManager(), query)).get(com.example.appocalypse.
                FoundBookViewModel.class);

        final ActivitySearchBinding binding = DataBindingUtil.setContentView(this,
                R.layout.activity_search);

        binding.setVm(viewModel);
        binding.setLifecycleOwner(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile:
                Intent intent = new Intent(this, LibraryActivity.class);
                this.startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
