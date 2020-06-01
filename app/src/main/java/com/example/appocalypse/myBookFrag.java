package com.example.appocalypse;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;


import com.example.appocalypse.databinding.MyBookFragmentBinding;

public class myBookFrag extends Fragment {
    private static final String ARG_MID = "param1";
    private static final String TAG = "MyBookFragment";
    private int mId;
    private BookViewModel bookViewModel;

    public static myBookFrag newInstance(int cId) {
        myBookFrag fragment = new myBookFrag();
        Bundle args = new Bundle();
        args.putInt(ARG_MID, cId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        if (getArguments() != null) {
            mId = getArguments().getInt(ARG_MID);
        }
        bookViewModel = ViewModelProviders.of(this, new BookVMFactory(
                getActivity().getApplication(), getFragmentManager()))
                .get(BookViewModel.class);
        bookViewModel.loadById(mId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MyBookFragmentBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.my_book_fragment, container, false);
        binding.setVm(bookViewModel);
        binding.setLifecycleOwner(this);
        View view = binding.getRoot();

        return view;
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);

    }
}