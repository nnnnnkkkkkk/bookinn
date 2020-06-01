package com.example.appocalypse;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appocalypse.databinding.FBookAdapterBinding;

import java.util.List;


public class foundBookAdapter extends RecyclerView.Adapter<foundBookViewHolder> {
    private static final String TAG = "found book adapter";
    private List<Work> books;
    private OnItemClickListener listener;

    foundBookAdapter(List<Work> books, OnItemClickListener nListener) {
        this.books = books;
        this.listener = nListener;
    }

    @NonNull
    @Override
    public foundBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        FBookAdapterBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.f_book_adapter, parent, false);

        return new foundBookViewHolder(binding, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull foundBookViewHolder holder, int position) {
        holder.bind(books.get(position).getBest_book(), position);
    }


    @Override
    public int getItemCount() {
        return books == null ? 0 : books.size();
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}

class foundBookViewHolder extends RecyclerView.ViewHolder {
    private FBookAdapterBinding adapterBinding;
    private foundBookAdapter.OnItemClickListener onItemClickListener;

    public foundBookViewHolder(@NonNull FBookAdapterBinding binding,
                               foundBookAdapter.OnItemClickListener onItemClick) {
        super(binding.getRoot());
        adapterBinding = binding;
        this.onItemClickListener = onItemClick;

        adapterBinding.setOnBookClick(onItemClickListener);
    }

    public void bind(Books book, int pos) {
        adapterBinding.setFoundBook(book);
        adapterBinding.setPos(pos);
        adapterBinding.executePendingBindings();
    }
}
