package com.example.appocalypse;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appocalypse.databinding.FragmentSelectedBookBinding;
import com.squareup.picasso.Picasso;


public class selectedBookFrag extends Fragment {
    private static final String ARG_BOOK = "param0";
    private Books pos;
    private FoundBookViewModel viewModel;

    private static final String TAG = "selected book fragment";


    public static selectedBookFrag newInstance(Books position) {
        selectedBookFrag fragment = new selectedBookFrag();
        Bundle args = new Bundle();
        args.putParcelable(ARG_BOOK, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate ");
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pos = getArguments().getParcelable(ARG_BOOK);
        }
        viewModel = ViewModelProviders.of(this, new foundBookVMFactory(getActivity().
                getApplication(), getFragmentManager(), null)).get(FoundBookViewModel.class);
        viewModel.loadData(pos);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentSelectedBookBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_selected_book, container, false);
        binding.setVm(viewModel);
        binding.setLifecycleOwner(this);

        Log.d(TAG, "onCreateView: ");
        return binding.getRoot();
    }

}
