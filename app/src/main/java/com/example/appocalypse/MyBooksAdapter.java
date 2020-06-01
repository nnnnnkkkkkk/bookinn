package com.example.appocalypse;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appocalypse.databinding.PlannedBookBinding;

import java.util.List;

public class MyBooksAdapter extends RecyclerView.Adapter<myBookHolder> {
    private static final String TAG = "MyBooksAdapter";
    private List<Books> myBooks;
    private MyBooksAdapter.OnBookListener onBookListener;

    MyBooksAdapter(OnBookListener bookListener, List<Books> list) {
        this.onBookListener = bookListener;
        this.myBooks = list;
    }


    @NonNull
    @Override
    public myBookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        PlannedBookBinding v = PlannedBookBinding.inflate(inflater, parent, false);

        return new myBookHolder(v, onBookListener);
    }

    @Override
    public void onBindViewHolder(@NonNull myBookHolder holder, int position) {
        holder.bind(myBooks.get(position));
    }


    @Override
    public int getItemCount() {
        return myBooks == null ? 0 : myBooks.size();
    }

    public interface OnBookListener {
        void onBookClick(int position);
    }

}

class myBookHolder extends RecyclerView.ViewHolder {
    private PlannedBookBinding binding;
    MyBooksAdapter.OnBookListener onBookListener;

    public myBookHolder(@NonNull PlannedBookBinding viewDataBinding,
                        MyBooksAdapter.OnBookListener onListener) {
        super(viewDataBinding.getRoot());
        onBookListener = onListener;
        binding = viewDataBinding;
        binding.setOnBookClickListener(onListener);
    }

    public void bind(Books book) {
        binding.setPlannedBook(book);
        binding.executePendingBindings();
    }

}
