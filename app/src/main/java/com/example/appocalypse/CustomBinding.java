package com.example.appocalypse;

import android.annotation.SuppressLint;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class CustomBinding {
    public static final String TAG = "custom binding";

    @BindingAdapter({"data", "bind:clickHandler"})
    public static void configureRecyclerView(RecyclerView recyclerView, List<Books> list,
                                             MyBooksAdapter.OnBookListener onBookListener) {
        MyBooksAdapter adapter = new MyBooksAdapter(onBookListener, list);
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter({"fdata", "fclick"})
    public static void foundRecyclerView(RecyclerView recyclerView, List<Work> list,
                                             foundBookAdapter.OnItemClickListener onBookListener) {
        foundBookAdapter adapter = new foundBookAdapter(list, onBookListener);
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView imageView, String v) {
        Picasso.with(imageView.getContext()).load(v).into(imageView);
    }

    @SuppressLint("SetTextI18n")
    @BindingAdapter({"bind:setStart", "bind:setEnd"})
    public static void setPeriod(TextView view, String start, String end) {
        if (end != null && end.length() > 1) {
            view.setText(ChronoUnit.DAYS.between(LocalDate.parse(start), LocalDate.parse(end)) +
                    "days");
        } else if (start != null) {
            view.setText(ChronoUnit.DAYS.between(LocalDate.parse(start), LocalDate.now()) + "days");
        } else view.setText(R.string.not_started);
    }

    @BindingAdapter("onQuery")
    public static void onQuery(SearchView searchView,
                               SearchView.OnQueryTextListener onQueryTextListener) {
        searchView.setOnQueryTextListener(onQueryTextListener);
    }
}