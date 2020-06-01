package com.example.appocalypse;

import android.app.Application;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import java.util.List;

public class FoundBookViewModel extends BookViewModel {
    private LiveData<List<Work>> foundBooks;
    private Books book;
    private foundBookRepository bookRepository = new foundBookRepository();
    private static final String TAG = "found book VM";

    public FoundBookViewModel(@NonNull Application application, FragmentManager fm, String query) {
        super(application, fm);
        foundBooks = bookRepository.getFoundBooks(query);
    }

    public void loadData(Books book) { this.book = book; }

    public Books getThisBook() { return book; }

    public SearchView.OnQueryTextListener onQueryTextListenerSearch = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            foundBooks = bookRepository.getFoundBooks(query);
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };

    public View.OnClickListener add = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getRepository().putData(book);
            Toast.makeText(getApplication().getApplicationContext(),
                    book.getTitle()+" has been added to your library", Toast.LENGTH_SHORT).
                    show();
            getFragmentManager().popBackStack();
        }
    };

    public LiveData<List<Work>> getData() {
        return foundBooks;
    }

    public foundBookAdapter.OnItemClickListener onItemClickListener = new foundBookAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(int position) {
            Log.d(TAG, "onItemClick: ");
            getFragmentManager().beginTransaction().replace(R.id.searchActivity,
                    selectedBookFrag.newInstance(foundBooks.getValue().get(position).getBest_book()))
                    .addToBackStack(selectedBookFrag.class.getName()).commit();
        }
    };

}
