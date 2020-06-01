package com.example.appocalypse;

import android.app.Application;
import android.content.Intent;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BookViewModel extends AndroidViewModel {
    private repository repository = new repository();;
    private LiveData<List<Books>> plannedBooks;
    private LiveData<List<Books>> completedBooks;
    private LiveData<Books> someBook;
    private FragmentManager fragmentManager;

    public BookViewModel(@NonNull Application application, FragmentManager fm) {
        super(application);
        fragmentManager = fm;
        someBook = repository.getCurrentBook();
        plannedBooks = repository.getPlannedBooks();
        completedBooks = repository.getCompletedBooks();
    }

    public LiveData<List<Books>> getCompletedBooks() {
        return completedBooks;
    }

    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    public com.example.appocalypse.repository getRepository() {
        return repository;
    }

    public void insert(Books books) {
        repository.putData(books);
    }

    public LiveData<List<Books>> getPlannedBooks() {
        return plannedBooks;
    }

    public boolean isReading() {
        return repository.isReading();
    }

    public void loadById(int id) {
        someBook = repository.getBookById(id);
    }

    public LiveData<Books> getBook() {
        return someBook;
    }

    public View.OnClickListener complete = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (someBook.getValue().getStatus().equals("Reading")) {
                repository.updateStatus("Completed", someBook.getValue().getId());
                repository.updateEnd(LocalDate.now().format(DateTimeFormatter.ISO_DATE),
                        someBook.getValue().getId());
            } else Toast.makeText(getApplication().getApplicationContext(),
                    "You need to start book first!",
                    Toast.LENGTH_LONG).show();
        }
    };

    public View.OnClickListener start = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            repository.updateCurrent(someBook.getValue().getId());
            repository.updateStart(LocalDate.now().format(DateTimeFormatter.ISO_DATE), someBook.
                    getValue().getId());
            repository.updateEnd(null, someBook.getValue().getId());
        }
    };

    public View.OnClickListener put_off = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            repository.updateStatus("Planning", someBook.getValue().getId());
        }
    };

    public SearchView.OnQueryTextListener onQueryTextListenerMain = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            Intent intent = new Intent(getApplication().getApplicationContext(), SearchActivity.class);
            intent.putExtra("query", query);
            getApplication().getApplicationContext().startActivity(intent);
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };

    public MyBooksAdapter.OnBookListener onBookListenerMain = new MyBooksAdapter.OnBookListener() {
        @Override
        public void onBookClick(int id) {
                fragmentManager.beginTransaction().replace(R.id.homeScreen,
                    myBookFrag.newInstance(id))
                    .addToBackStack(myBookFrag.class.getName()).commit();
        }
    };

    public MyBooksAdapter.OnBookListener onBookListenerLibrary = new MyBooksAdapter.OnBookListener() {
        @Override
        public void onBookClick(int id) {
            fragmentManager.beginTransaction().replace(R.id.library_layout,
                    myBookFrag.newInstance(id))
                    .addToBackStack(myBookFrag.class.getName()).commit();
        }
    };

    public View.OnClickListener deleteBookListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            repository.deleteBook(someBook.getValue());
            Toast.makeText(getApplication().getApplicationContext(), R.string.removes,
                    Toast.LENGTH_LONG).show();
            fragmentManager.popBackStack();
        }
    };
}